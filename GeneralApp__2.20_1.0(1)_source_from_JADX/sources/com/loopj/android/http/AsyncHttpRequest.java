package com.loopj.android.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.impl.client.AbstractHttpClient;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

public class AsyncHttpRequest implements Runnable {
    private boolean cancelIsNotified;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private final AtomicBoolean isCancelled = new AtomicBoolean();
    private volatile boolean isFinished;
    private boolean isRequestPreProcessed;
    private final HttpUriRequest request;
    private final ResponseHandlerInterface responseHandler;

    public AsyncHttpRequest(AbstractHttpClient client2, HttpContext context2, HttpUriRequest request2, ResponseHandlerInterface responseHandler2) {
        this.client = (AbstractHttpClient) Utils.notNull(client2, "client");
        this.context = (HttpContext) Utils.notNull(context2, "context");
        this.request = (HttpUriRequest) Utils.notNull(request2, "request");
        this.responseHandler = (ResponseHandlerInterface) Utils.notNull(responseHandler2, "responseHandler");
    }

    public void onPreProcessRequest(AsyncHttpRequest request2) {
    }

    public void onPostProcessRequest(AsyncHttpRequest request2) {
    }

    public void run() {
        if (!isCancelled()) {
            if (!this.isRequestPreProcessed) {
                this.isRequestPreProcessed = true;
                onPreProcessRequest(this);
            }
            if (!isCancelled()) {
                this.responseHandler.sendStartMessage();
                if (!isCancelled()) {
                    try {
                        makeRequestWithRetries();
                    } catch (IOException e) {
                        if (!isCancelled()) {
                            this.responseHandler.sendFailureMessage(0, null, null, e);
                        } else {
                            AsyncHttpClient.log.mo22450e("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        }
                    }
                    if (!isCancelled()) {
                        this.responseHandler.sendFinishMessage();
                        if (!isCancelled()) {
                            onPostProcessRequest(this);
                            this.isFinished = true;
                        }
                    }
                }
            }
        }
    }

    private void makeRequest() throws IOException {
        if (!isCancelled()) {
            if (this.request.getURI().getScheme() != null) {
                ResponseHandlerInterface responseHandlerInterface = this.responseHandler;
                if (responseHandlerInterface instanceof RangeFileAsyncHttpResponseHandler) {
                    ((RangeFileAsyncHttpResponseHandler) responseHandlerInterface).updateRequestHeaders(this.request);
                }
                HttpResponse response = this.client.execute(this.request, this.context);
                if (!isCancelled()) {
                    ResponseHandlerInterface responseHandlerInterface2 = this.responseHandler;
                    responseHandlerInterface2.onPreProcessResponse(responseHandlerInterface2, response);
                    if (!isCancelled()) {
                        this.responseHandler.sendResponseMessage(response);
                        if (!isCancelled()) {
                            ResponseHandlerInterface responseHandlerInterface3 = this.responseHandler;
                            responseHandlerInterface3.onPostProcessResponse(responseHandlerInterface3, response);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new MalformedURLException("No valid URI scheme was provided");
        }
    }

    private void makeRequestWithRetries() throws IOException {
        boolean retry = true;
        IOException cause = null;
        HttpRequestRetryHandler retryHandler = this.client.getHttpRequestRetryHandler();
        while (retry) {
            boolean z = true;
            try {
                makeRequest();
                return;
            } catch (UnknownHostException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("UnknownHostException exception: ");
                sb.append(e.getMessage());
                cause = new IOException(sb.toString());
                if (this.executionCount > 0) {
                    int i = this.executionCount + 1;
                    this.executionCount = i;
                    if (retryHandler.retryRequest(e, i, this.context)) {
                        retry = z;
                    }
                }
                z = false;
                retry = z;
            } catch (NullPointerException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("NPE in HttpClient: ");
                sb2.append(e2.getMessage());
                cause = new IOException(sb2.toString());
                int i2 = this.executionCount + 1;
                this.executionCount = i2;
                retry = retryHandler.retryRequest(cause, i2, this.context);
            } catch (IOException e3) {
                try {
                    if (!isCancelled()) {
                        cause = e3;
                        int i3 = this.executionCount + 1;
                        this.executionCount = i3;
                        retry = retryHandler.retryRequest(cause, i3, this.context);
                    } else {
                        return;
                    }
                } catch (Exception e4) {
                    AsyncHttpClient.log.mo22450e("AsyncHttpRequest", "Unhandled exception origin cause", e4);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unhandled exception: ");
                    sb3.append(e4.getMessage());
                    cause = new IOException(sb3.toString());
                }
            }
        }
        throw cause;
        if (retry) {
            this.responseHandler.sendRetryMessage(this.executionCount);
        }
    }

    public boolean isCancelled() {
        boolean cancelled = this.isCancelled.get();
        if (cancelled) {
            sendCancelNotification();
        }
        return cancelled;
    }

    private synchronized void sendCancelNotification() {
        if (!this.isFinished && this.isCancelled.get() && !this.cancelIsNotified) {
            this.cancelIsNotified = true;
            this.responseHandler.sendCancelMessage();
        }
    }

    public boolean isDone() {
        return isCancelled() || this.isFinished;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isCancelled.set(true);
        this.request.abort();
        return isCancelled();
    }

    public AsyncHttpRequest setRequestTag(Object TAG) {
        this.responseHandler.setTag(TAG);
        return this;
    }

    public Object getTag() {
        return this.responseHandler.getTag();
    }
}
