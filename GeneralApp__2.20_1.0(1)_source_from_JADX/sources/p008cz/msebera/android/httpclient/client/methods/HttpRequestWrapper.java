package p008cz.msebera.android.httpclient.client.methods;

import java.net.URI;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.RequestLine;
import p008cz.msebera.android.httpclient.message.AbstractHttpMessage;
import p008cz.msebera.android.httpclient.message.BasicRequestLine;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpRequestWrapper */
public class HttpRequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private final String method;
    private final HttpRequest original;
    private URI uri;
    private ProtocolVersion version;

    /* renamed from: cz.msebera.android.httpclient.client.methods.HttpRequestWrapper$HttpEntityEnclosingRequestWrapper */
    static class HttpEntityEnclosingRequestWrapper extends HttpRequestWrapper implements HttpEntityEnclosingRequest {
        private HttpEntity entity;

        public HttpEntityEnclosingRequestWrapper(HttpEntityEnclosingRequest request) {
            super(request);
            this.entity = request.getEntity();
        }

        public HttpEntity getEntity() {
            return this.entity;
        }

        public void setEntity(HttpEntity entity2) {
            this.entity = entity2;
        }

        public boolean expectContinue() {
            Header expect = getFirstHeader("Expect");
            if (expect != null) {
                if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    private HttpRequestWrapper(HttpRequest request) {
        this.original = request;
        this.version = this.original.getRequestLine().getProtocolVersion();
        this.method = this.original.getRequestLine().getMethod();
        if (request instanceof HttpUriRequest) {
            this.uri = ((HttpUriRequest) request).getURI();
        } else {
            this.uri = null;
        }
        setHeaders(request.getAllHeaders());
    }

    public ProtocolVersion getProtocolVersion() {
        ProtocolVersion protocolVersion = this.version;
        return protocolVersion != null ? protocolVersion : this.original.getProtocolVersion();
    }

    public void setProtocolVersion(ProtocolVersion version2) {
        this.version = version2;
    }

    public URI getURI() {
        return this.uri;
    }

    public void setURI(URI uri2) {
        this.uri = uri2;
    }

    public String getMethod() {
        return this.method;
    }

    public void abort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean isAborted() {
        return false;
    }

    public RequestLine getRequestLine() {
        String requestUri;
        URI uri2 = this.uri;
        if (uri2 != null) {
            requestUri = uri2.toASCIIString();
        } else {
            requestUri = this.original.getRequestLine().getUri();
        }
        if (requestUri == null || requestUri.length() == 0) {
            requestUri = "/";
        }
        return new BasicRequestLine(this.method, requestUri, getProtocolVersion());
    }

    public HttpRequest getOriginal() {
        return this.original;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRequestLine());
        sb.append(" ");
        sb.append(this.headergroup);
        return sb.toString();
    }

    public static HttpRequestWrapper wrap(HttpRequest request) {
        if (request == null) {
            return null;
        }
        if (request instanceof HttpEntityEnclosingRequest) {
            return new HttpEntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
        }
        return new HttpRequestWrapper(request);
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.params == null) {
            this.params = this.original.getParams().copy();
        }
        return this.params;
    }
}
