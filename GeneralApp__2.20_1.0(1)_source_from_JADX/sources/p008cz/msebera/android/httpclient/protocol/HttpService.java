package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.ConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseFactory;
import p008cz.msebera.android.httpclient.HttpServerConnection;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.MethodNotSupportedException;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.UnsupportedHttpVersionException;
import p008cz.msebera.android.httpclient.entity.ByteArrayEntity;
import p008cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import p008cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EncodingUtils;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.protocol.HttpService */
public class HttpService {
    private volatile ConnectionReuseStrategy connStrategy;
    private volatile HttpExpectationVerifier expectationVerifier;
    private volatile HttpRequestHandlerMapper handlerMapper;
    private volatile HttpParams params;
    private volatile HttpProcessor processor;
    private volatile HttpResponseFactory responseFactory;

    @Deprecated
    /* renamed from: cz.msebera.android.httpclient.protocol.HttpService$HttpRequestHandlerResolverAdapter */
    private static class HttpRequestHandlerResolverAdapter implements HttpRequestHandlerMapper {
        private final HttpRequestHandlerResolver resolver;

        public HttpRequestHandlerResolverAdapter(HttpRequestHandlerResolver resolver2) {
            this.resolver = resolver2;
        }

        public HttpRequestHandler lookup(HttpRequest request) {
            return this.resolver.lookup(request.getRequestLine().getUri());
        }
    }

    @Deprecated
    public HttpService(HttpProcessor processor2, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2, HttpRequestHandlerResolver handlerResolver, HttpExpectationVerifier expectationVerifier2, HttpParams params2) {
        this(processor2, connStrategy2, responseFactory2, (HttpRequestHandlerMapper) new HttpRequestHandlerResolverAdapter(handlerResolver), expectationVerifier2);
        this.params = params2;
    }

    @Deprecated
    public HttpService(HttpProcessor processor2, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2, HttpRequestHandlerResolver handlerResolver, HttpParams params2) {
        this(processor2, connStrategy2, responseFactory2, (HttpRequestHandlerMapper) new HttpRequestHandlerResolverAdapter(handlerResolver), (HttpExpectationVerifier) null);
        this.params = params2;
    }

    @Deprecated
    public HttpService(HttpProcessor proc, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2) {
        this.params = null;
        this.processor = null;
        this.handlerMapper = null;
        this.connStrategy = null;
        this.responseFactory = null;
        this.expectationVerifier = null;
        setHttpProcessor(proc);
        setConnReuseStrategy(connStrategy2);
        setResponseFactory(responseFactory2);
    }

    public HttpService(HttpProcessor processor2, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2, HttpRequestHandlerMapper handlerMapper2, HttpExpectationVerifier expectationVerifier2) {
        this.params = null;
        this.processor = null;
        this.handlerMapper = null;
        this.connStrategy = null;
        this.responseFactory = null;
        this.expectationVerifier = null;
        this.processor = (HttpProcessor) Args.notNull(processor2, "HTTP processor");
        this.connStrategy = connStrategy2 != null ? connStrategy2 : DefaultConnectionReuseStrategy.INSTANCE;
        this.responseFactory = responseFactory2 != null ? responseFactory2 : DefaultHttpResponseFactory.INSTANCE;
        this.handlerMapper = handlerMapper2;
        this.expectationVerifier = expectationVerifier2;
    }

    public HttpService(HttpProcessor processor2, ConnectionReuseStrategy connStrategy2, HttpResponseFactory responseFactory2, HttpRequestHandlerMapper handlerMapper2) {
        this(processor2, connStrategy2, responseFactory2, handlerMapper2, (HttpExpectationVerifier) null);
    }

    public HttpService(HttpProcessor processor2, HttpRequestHandlerMapper handlerMapper2) {
        this(processor2, (ConnectionReuseStrategy) null, (HttpResponseFactory) null, handlerMapper2, (HttpExpectationVerifier) null);
    }

    @Deprecated
    public void setHttpProcessor(HttpProcessor processor2) {
        Args.notNull(processor2, "HTTP processor");
        this.processor = processor2;
    }

    @Deprecated
    public void setConnReuseStrategy(ConnectionReuseStrategy connStrategy2) {
        Args.notNull(connStrategy2, "Connection reuse strategy");
        this.connStrategy = connStrategy2;
    }

    @Deprecated
    public void setResponseFactory(HttpResponseFactory responseFactory2) {
        Args.notNull(responseFactory2, "Response factory");
        this.responseFactory = responseFactory2;
    }

    @Deprecated
    public void setParams(HttpParams params2) {
        this.params = params2;
    }

    @Deprecated
    public void setHandlerResolver(HttpRequestHandlerResolver handlerResolver) {
        this.handlerMapper = new HttpRequestHandlerResolverAdapter(handlerResolver);
    }

    @Deprecated
    public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier2) {
        this.expectationVerifier = expectationVerifier2;
    }

    @Deprecated
    public HttpParams getParams() {
        return this.params;
    }

    public void handleRequest(HttpServerConnection conn, HttpContext context) throws IOException, HttpException {
        context.setAttribute("http.connection", conn);
        HttpResponse response = null;
        try {
            HttpRequest request = conn.receiveRequestHeader();
            if (request instanceof HttpEntityEnclosingRequest) {
                if (((HttpEntityEnclosingRequest) request).expectContinue()) {
                    response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_1, 100, context);
                    if (this.expectationVerifier != null) {
                        try {
                            this.expectationVerifier.verify(request, response, context);
                        } catch (HttpException ex) {
                            response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, HttpStatus.SC_INTERNAL_SERVER_ERROR, context);
                            handleException(ex, response);
                        }
                    }
                    if (response.getStatusLine().getStatusCode() < 200) {
                        conn.sendResponseHeader(response);
                        conn.flush();
                        response = null;
                        conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
                    }
                } else {
                    conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
                }
            }
            context.setAttribute("http.request", request);
            if (response == null) {
                response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_1, 200, context);
                this.processor.process(request, context);
                doService(request, response, context);
            }
            if (request instanceof HttpEntityEnclosingRequest) {
                EntityUtils.consume(((HttpEntityEnclosingRequest) request).getEntity());
            }
        } catch (HttpException ex2) {
            response = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, HttpStatus.SC_INTERNAL_SERVER_ERROR, context);
            handleException(ex2, response);
        }
        context.setAttribute("http.response", response);
        this.processor.process(response, context);
        conn.sendResponseHeader(response);
        conn.sendResponseEntity(response);
        conn.flush();
        if (!this.connStrategy.keepAlive(response, context)) {
            conn.close();
        }
    }

    /* access modifiers changed from: protected */
    public void handleException(HttpException ex, HttpResponse response) {
        if (ex instanceof MethodNotSupportedException) {
            response.setStatusCode(HttpStatus.SC_NOT_IMPLEMENTED);
        } else if (ex instanceof UnsupportedHttpVersionException) {
            response.setStatusCode(HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED);
        } else if (ex instanceof ProtocolException) {
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
        } else {
            response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        String message = ex.getMessage();
        if (message == null) {
            message = ex.toString();
        }
        ByteArrayEntity entity = new ByteArrayEntity(EncodingUtils.getAsciiBytes(message));
        entity.setContentType("text/plain; charset=US-ASCII");
        response.setEntity(entity);
    }

    /* access modifiers changed from: protected */
    public void doService(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        HttpRequestHandler handler = null;
        if (this.handlerMapper != null) {
            handler = this.handlerMapper.lookup(request);
        }
        if (handler != null) {
            handler.handle(request, response, context);
        } else {
            response.setStatusCode(HttpStatus.SC_NOT_IMPLEMENTED);
        }
    }
}
