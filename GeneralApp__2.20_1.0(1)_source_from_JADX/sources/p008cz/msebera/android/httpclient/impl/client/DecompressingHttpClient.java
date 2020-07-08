package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.client.ClientProtocolException;
import p008cz.msebera.android.httpclient.client.HttpClient;
import p008cz.msebera.android.httpclient.client.ResponseHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding;
import p008cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.EntityUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DecompressingHttpClient */
public class DecompressingHttpClient implements HttpClient {
    private final HttpRequestInterceptor acceptEncodingInterceptor;
    private final HttpClient backend;
    private final HttpResponseInterceptor contentEncodingInterceptor;

    public DecompressingHttpClient() {
        this(new DefaultHttpClient());
    }

    public DecompressingHttpClient(HttpClient backend2) {
        this(backend2, new RequestAcceptEncoding(), new ResponseContentEncoding());
    }

    DecompressingHttpClient(HttpClient backend2, HttpRequestInterceptor requestInterceptor, HttpResponseInterceptor responseInterceptor) {
        this.backend = backend2;
        this.acceptEncodingInterceptor = requestInterceptor;
        this.contentEncodingInterceptor = responseInterceptor;
    }

    public HttpParams getParams() {
        return this.backend.getParams();
    }

    public ClientConnectionManager getConnectionManager() {
        return this.backend.getConnectionManager();
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
        return execute(getHttpHost(request), (HttpRequest) request, (HttpContext) null);
    }

    public HttpClient getHttpClient() {
        return this.backend;
    }

    /* access modifiers changed from: 0000 */
    public HttpHost getHttpHost(HttpUriRequest request) {
        return URIUtils.extractHost(request.getURI());
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return execute(getHttpHost(request), (HttpRequest) request, context);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
        return execute(target, request, (HttpContext) null);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        HttpContext localContext;
        HttpRequest wrapped;
        HttpResponse response;
        if (context != null) {
            localContext = context;
        } else {
            try {
                localContext = new BasicHttpContext();
            } catch (HttpException ex) {
                EntityUtils.consume(response.getEntity());
                throw ex;
            } catch (IOException ex2) {
                EntityUtils.consume(response.getEntity());
                throw ex2;
            } catch (RuntimeException ex3) {
                EntityUtils.consume(response.getEntity());
                throw ex3;
            } catch (HttpException e) {
                throw new ClientProtocolException((Throwable) e);
            }
        }
        if (request instanceof HttpEntityEnclosingRequest) {
            wrapped = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
        } else {
            wrapped = new RequestWrapper(request);
        }
        this.acceptEncodingInterceptor.process(wrapped, localContext);
        response = this.backend.execute(target, wrapped, localContext);
        this.contentEncodingInterceptor.process(response, localContext);
        if (Boolean.TRUE.equals(localContext.getAttribute(ResponseContentEncoding.UNCOMPRESSED))) {
            response.removeHeaders("Content-Length");
            response.removeHeaders("Content-Encoding");
            response.removeHeaders(HttpHeaders.CONTENT_MD5);
        }
        return response;
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(getHttpHost(request), (HttpRequest) request, responseHandler);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return execute(getHttpHost(request), request, responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(target, request, responseHandler, null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        HttpResponse response = execute(target, request, context);
        try {
            return responseHandler.handleResponse(response);
        } finally {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        }
    }
}
