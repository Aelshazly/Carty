package com.loopj.android.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.client.HttpResponseException;
import p008cz.msebera.android.httpclient.util.ByteArrayBuffer;

public abstract class AsyncHttpResponseHandler implements ResponseHandlerInterface {
    protected static final int BUFFER_SIZE = 4096;
    protected static final int CANCEL_MESSAGE = 6;
    public static final String DEFAULT_CHARSET = "UTF-8";
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int FINISH_MESSAGE = 3;
    private static final String LOG_TAG = "AsyncHttpRH";
    protected static final int PROGRESS_MESSAGE = 4;
    protected static final int RETRY_MESSAGE = 5;
    protected static final int START_MESSAGE = 2;
    protected static final int SUCCESS_MESSAGE = 0;
    public static final String UTF8_BOM = "﻿";
    private WeakReference<Object> TAG;
    private Handler handler;
    private Looper looper;
    private Header[] requestHeaders;
    private URI requestURI;
    private String responseCharset;
    private boolean usePoolThread;
    private boolean useSynchronousMode;

    private static class ResponderHandler extends Handler {
        private final AsyncHttpResponseHandler mResponder;

        ResponderHandler(AsyncHttpResponseHandler mResponder2, Looper looper) {
            super(looper);
            this.mResponder = mResponder2;
        }

        public void handleMessage(Message msg) {
            this.mResponder.handleMessage(msg);
        }
    }

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public AsyncHttpResponseHandler() {
        this((Looper) null);
    }

    public AsyncHttpResponseHandler(Looper looper2) {
        this.responseCharset = "UTF-8";
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference<>(null);
        this.looper = looper2 == null ? Looper.myLooper() : looper2;
        setUseSynchronousMode(false);
        setUsePoolThread(false);
    }

    public AsyncHttpResponseHandler(boolean usePoolThread2) {
        this.responseCharset = "UTF-8";
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference<>(null);
        setUsePoolThread(usePoolThread2);
        if (!getUsePoolThread()) {
            this.looper = Looper.myLooper();
            setUseSynchronousMode(false);
        }
    }

    public Object getTag() {
        return this.TAG.get();
    }

    public void setTag(Object TAG2) {
        this.TAG = new WeakReference<>(TAG2);
    }

    public URI getRequestURI() {
        return this.requestURI;
    }

    public void setRequestURI(URI requestURI2) {
        this.requestURI = requestURI2;
    }

    public Header[] getRequestHeaders() {
        return this.requestHeaders;
    }

    public void setRequestHeaders(Header[] requestHeaders2) {
        this.requestHeaders = requestHeaders2;
    }

    public boolean getUseSynchronousMode() {
        return this.useSynchronousMode;
    }

    public void setUseSynchronousMode(boolean sync) {
        if (!sync && this.looper == null) {
            sync = true;
            AsyncHttpClient.log.mo22462w(LOG_TAG, "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (!sync && this.handler == null) {
            this.handler = new ResponderHandler(this, this.looper);
        } else if (sync && this.handler != null) {
            this.handler = null;
        }
        this.useSynchronousMode = sync;
    }

    public boolean getUsePoolThread() {
        return this.usePoolThread;
    }

    public void setUsePoolThread(boolean pool) {
        if (pool) {
            this.looper = null;
            this.handler = null;
        }
        this.usePoolThread = pool;
    }

    public String getCharset() {
        String str = this.responseCharset;
        return str == null ? "UTF-8" : str;
    }

    public void setCharset(String charset) {
        this.responseCharset = charset;
    }

    public void onProgress(long bytesWritten, long totalSize) {
        LogInterface logInterface = AsyncHttpClient.log;
        Object[] objArr = new Object[3];
        objArr[0] = Long.valueOf(bytesWritten);
        objArr[1] = Long.valueOf(totalSize);
        objArr[2] = Double.valueOf(totalSize > 0 ? ((((double) bytesWritten) * 1.0d) / ((double) totalSize)) * 100.0d : -1.0d);
        logInterface.mo22460v(LOG_TAG, String.format("Progress %d from %d (%2.0f%%)", objArr));
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onPreProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
    }

    public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
    }

    public void onRetry(int retryNo) {
        AsyncHttpClient.log.mo22447d(LOG_TAG, String.format("Request retry no. %d", new Object[]{Integer.valueOf(retryNo)}));
    }

    public void onCancel() {
        AsyncHttpClient.log.mo22447d(LOG_TAG, "Request got cancelled");
    }

    public void onUserException(Throwable error) {
        AsyncHttpClient.log.mo22450e(LOG_TAG, "User-space exception detected!", error);
        throw new RuntimeException(error);
    }

    public final void sendProgressMessage(long bytesWritten, long bytesTotal) {
        sendMessage(obtainMessage(4, new Object[]{Long.valueOf(bytesWritten), Long.valueOf(bytesTotal)}));
    }

    public final void sendSuccessMessage(int statusCode, Header[] headers, byte[] responseBytes) {
        sendMessage(obtainMessage(0, new Object[]{Integer.valueOf(statusCode), headers, responseBytes}));
    }

    public final void sendFailureMessage(int statusCode, Header[] headers, byte[] responseBody, Throwable throwable) {
        sendMessage(obtainMessage(1, new Object[]{Integer.valueOf(statusCode), headers, responseBody, throwable}));
    }

    public final void sendStartMessage() {
        sendMessage(obtainMessage(2, null));
    }

    public final void sendFinishMessage() {
        sendMessage(obtainMessage(3, null));
    }

    public final void sendRetryMessage(int retryNo) {
        sendMessage(obtainMessage(5, new Object[]{Integer.valueOf(retryNo)}));
    }

    public final void sendCancelMessage() {
        sendMessage(obtainMessage(6, null));
    }

    /* access modifiers changed from: protected */
    public void handleMessage(Message message) {
        String str;
        try {
            int i = message.what;
            str = LOG_TAG;
            switch (i) {
                case 0:
                    Object[] response = (Object[]) message.obj;
                    if (response == null || response.length < 3) {
                        AsyncHttpClient.log.mo22449e(str, "SUCCESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onSuccess(((Integer) response[0]).intValue(), (Header[]) response[1], (byte[]) response[2]);
                        return;
                    }
                case 1:
                    Object[] response2 = (Object[]) message.obj;
                    if (response2 == null || response2.length < 4) {
                        AsyncHttpClient.log.mo22449e(str, "FAILURE_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onFailure(((Integer) response2[0]).intValue(), (Header[]) response2[1], (byte[]) response2[2], (Throwable) response2[3]);
                        return;
                    }
                case 2:
                    onStart();
                    return;
                case 3:
                    onFinish();
                    return;
                case 4:
                    Object[] response3 = (Object[]) message.obj;
                    if (response3 == null || response3.length < 2) {
                        AsyncHttpClient.log.mo22449e(str, "PROGRESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onProgress(((Long) response3[0]).longValue(), ((Long) response3[1]).longValue());
                        return;
                    }
                case 5:
                    Object[] response4 = (Object[]) message.obj;
                    if (response4 == null || response4.length != 1) {
                        AsyncHttpClient.log.mo22449e(str, "RETRY_MESSAGE didn't get enough params");
                        return;
                    } else {
                        onRetry(((Integer) response4[0]).intValue());
                        return;
                    }
                case 6:
                    onCancel();
                    return;
                default:
                    return;
            }
        } catch (Throwable error) {
            onUserException(error);
        }
        onUserException(error);
    }

    /* access modifiers changed from: protected */
    public void sendMessage(Message msg) {
        if (getUseSynchronousMode() || this.handler == null) {
            handleMessage(msg);
        } else if (!Thread.currentThread().isInterrupted()) {
            Utils.asserts(this.handler != null, "handler should not be null!");
            this.handler.sendMessage(msg);
        }
    }

    /* access modifiers changed from: protected */
    public void postRunnable(Runnable runnable) {
        if (runnable != null) {
            if (!getUseSynchronousMode()) {
                Handler handler2 = this.handler;
                if (handler2 != null) {
                    handler2.post(runnable);
                    return;
                }
            }
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public Message obtainMessage(int responseMessageId, Object responseMessageData) {
        return Message.obtain(this.handler, responseMessageId, responseMessageData);
    }

    public void sendResponseMessage(HttpResponse response) throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine status = response.getStatusLine();
            byte[] responseBody = getResponseData(response.getEntity());
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (status.getStatusCode() >= 300) {
                sendFailureMessage(status.getStatusCode(), response.getAllHeaders(), responseBody, new HttpResponseException(status.getStatusCode(), status.getReasonPhrase()));
            } else {
                sendSuccessMessage(status.getStatusCode(), response.getAllHeaders(), responseBody);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public byte[] getResponseData(HttpEntity entity) throws IOException {
        if (entity != null) {
            InputStream instream = entity.getContent();
            if (instream != null) {
                long contentLength = entity.getContentLength();
                if (contentLength <= 2147483647L) {
                    try {
                        ByteArrayBuffer buffer = new ByteArrayBuffer(contentLength <= 0 ? 4096 : (int) contentLength);
                        try {
                            byte[] tmp = new byte[4096];
                            long count = 0;
                            while (true) {
                                int read = instream.read(tmp);
                                int l = read;
                                if (read != -1 && !Thread.currentThread().isInterrupted()) {
                                    count += (long) l;
                                    buffer.append(tmp, 0, l);
                                    try {
                                        sendProgressMessage(count, contentLength <= 0 ? 1 : contentLength);
                                    } catch (Throwable th) {
                                        th = th;
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            AsyncHttpClient.silentCloseInputStream(instream);
                            AsyncHttpClient.endEntityViaReflection(entity);
                            throw th;
                        }
                        try {
                            AsyncHttpClient.silentCloseInputStream(instream);
                            AsyncHttpClient.endEntityViaReflection(entity);
                            return buffer.toByteArray();
                        } catch (OutOfMemoryError e) {
                            System.gc();
                            throw new IOException("File too large to fit into available memory");
                        }
                    } catch (OutOfMemoryError e2) {
                        System.gc();
                        throw new IOException("File too large to fit into available memory");
                    }
                } else {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
