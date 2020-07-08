package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.HttpClient;
import p008cz.msebera.android.httpclient.client.ResponseHandler;
import p008cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.AutoRetryHttpClient */
public class AutoRetryHttpClient implements HttpClient {
    private final HttpClient backend;
    public HttpClientAndroidLog log;
    private final ServiceUnavailableRetryStrategy retryStrategy;

    public AutoRetryHttpClient(HttpClient client, ServiceUnavailableRetryStrategy retryStrategy2) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(client, "HttpClient");
        Args.notNull(retryStrategy2, "ServiceUnavailableRetryStrategy");
        this.backend = client;
        this.retryStrategy = retryStrategy2;
    }

    public AutoRetryHttpClient() {
        this(new DefaultHttpClient(), new DefaultServiceUnavailableRetryStrategy());
    }

    public AutoRetryHttpClient(ServiceUnavailableRetryStrategy config) {
        this(new DefaultHttpClient(), config);
    }

    public AutoRetryHttpClient(HttpClient client) {
        this(client, new DefaultServiceUnavailableRetryStrategy());
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        return execute(target, request, (HttpContext) null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return execute(target, request, responseHandler, null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return responseHandler.handleResponse(execute(target, request, context));
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException {
        return execute(request, (HttpContext) null);
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        URI uri = request.getURI();
        return execute(new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), (HttpRequest) request, context);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return execute(request, responseHandler, (HttpContext) null);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return responseHandler.handleResponse(execute(request, context));
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        int c = 1;
        while (true) {
            HttpResponse response = this.backend.execute(target, request, context);
            try {
                if (!this.retryStrategy.retryRequest(response, c, context)) {
                    return response;
                }
                EntityUtils.consume(response.getEntity());
                long nextInterval = this.retryStrategy.getRetryInterval();
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Wait for ");
                sb.append(nextInterval);
                httpClientAndroidLog.trace(sb.toString());
                Thread.sleep(nextInterval);
                c++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (RuntimeException ex) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ioex) {
                    this.log.warn("I/O error consuming response content", ioex);
                }
                throw ex;
            }
        }
    }

    public ClientConnectionManager getConnectionManager() {
        return this.backend.getConnectionManager();
    }

    public HttpParams getParams() {
        return this.backend.getParams();
    }
}
