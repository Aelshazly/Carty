package p008cz.msebera.android.httpclient.client.methods;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderIterator;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.message.BasicHeader;
import p008cz.msebera.android.httpclient.message.BasicNameValuePair;
import p008cz.msebera.android.httpclient.message.HeaderGroup;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.methods.RequestBuilder */
public class RequestBuilder {
    private RequestConfig config;
    private HttpEntity entity;
    private HeaderGroup headergroup;
    private String method;
    private LinkedList<NameValuePair> parameters;
    private URI uri;
    private ProtocolVersion version;

    /* renamed from: cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalEntityEclosingRequest */
    static class InternalEntityEclosingRequest extends HttpEntityEnclosingRequestBase {
        private final String method;

        InternalEntityEclosingRequest(String method2) {
            this.method = method2;
        }

        public String getMethod() {
            return this.method;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalRequest */
    static class InternalRequest extends HttpRequestBase {
        private final String method;

        InternalRequest(String method2) {
            this.method = method2;
        }

        public String getMethod() {
            return this.method;
        }
    }

    RequestBuilder(String method2) {
        this.method = method2;
    }

    RequestBuilder() {
        this(null);
    }

    public static RequestBuilder create(String method2) {
        Args.notBlank(method2, "HTTP method");
        return new RequestBuilder(method2);
    }

    public static RequestBuilder get() {
        return new RequestBuilder("GET");
    }

    public static RequestBuilder head() {
        return new RequestBuilder("HEAD");
    }

    public static RequestBuilder post() {
        return new RequestBuilder(HttpPost.METHOD_NAME);
    }

    public static RequestBuilder put() {
        return new RequestBuilder("PUT");
    }

    public static RequestBuilder delete() {
        return new RequestBuilder("DELETE");
    }

    public static RequestBuilder trace() {
        return new RequestBuilder("TRACE");
    }

    public static RequestBuilder options() {
        return new RequestBuilder("OPTIONS");
    }

    public static RequestBuilder copy(HttpRequest request) {
        Args.notNull(request, "HTTP request");
        return new RequestBuilder().doCopy(request);
    }

    private RequestBuilder doCopy(HttpRequest request) {
        if (request == null) {
            return this;
        }
        this.method = request.getRequestLine().getMethod();
        this.version = request.getRequestLine().getProtocolVersion();
        if (request instanceof HttpUriRequest) {
            this.uri = ((HttpUriRequest) request).getURI();
        } else {
            this.uri = URI.create(request.getRequestLine().getUri());
        }
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.clear();
        this.headergroup.setHeaders(request.getAllHeaders());
        if (request instanceof HttpEntityEnclosingRequest) {
            this.entity = ((HttpEntityEnclosingRequest) request).getEntity();
        } else {
            this.entity = null;
        }
        if (request instanceof Configurable) {
            this.config = ((Configurable) request).getConfig();
        } else {
            this.config = null;
        }
        this.parameters = null;
        return this;
    }

    public String getMethod() {
        return this.method;
    }

    public ProtocolVersion getVersion() {
        return this.version;
    }

    public RequestBuilder setVersion(ProtocolVersion version2) {
        this.version = version2;
        return this;
    }

    public URI getUri() {
        return this.uri;
    }

    public RequestBuilder setUri(URI uri2) {
        this.uri = uri2;
        return this;
    }

    public RequestBuilder setUri(String uri2) {
        this.uri = uri2 != null ? URI.create(uri2) : null;
        return this;
    }

    public Header getFirstHeader(String name) {
        HeaderGroup headerGroup = this.headergroup;
        if (headerGroup != null) {
            return headerGroup.getFirstHeader(name);
        }
        return null;
    }

    public Header getLastHeader(String name) {
        HeaderGroup headerGroup = this.headergroup;
        if (headerGroup != null) {
            return headerGroup.getLastHeader(name);
        }
        return null;
    }

    public Header[] getHeaders(String name) {
        HeaderGroup headerGroup = this.headergroup;
        if (headerGroup != null) {
            return headerGroup.getHeaders(name);
        }
        return null;
    }

    public RequestBuilder addHeader(Header header) {
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.addHeader(header);
        return this;
    }

    public RequestBuilder addHeader(String name, String value) {
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.addHeader(new BasicHeader(name, value));
        return this;
    }

    public RequestBuilder removeHeader(Header header) {
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.removeHeader(header);
        return this;
    }

    public RequestBuilder removeHeaders(String name) {
        if (name != null) {
            HeaderGroup headerGroup = this.headergroup;
            if (headerGroup != null) {
                HeaderIterator i = headerGroup.iterator();
                while (i.hasNext()) {
                    if (name.equalsIgnoreCase(i.nextHeader().getName())) {
                        i.remove();
                    }
                }
                return this;
            }
        }
        return this;
    }

    public RequestBuilder setHeader(Header header) {
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.updateHeader(header);
        return this;
    }

    public RequestBuilder setHeader(String name, String value) {
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.updateHeader(new BasicHeader(name, value));
        return this;
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public RequestBuilder setEntity(HttpEntity entity2) {
        this.entity = entity2;
        return this;
    }

    public List<NameValuePair> getParameters() {
        ArrayList arrayList;
        LinkedList<NameValuePair> linkedList = this.parameters;
        if (linkedList != null) {
            arrayList = new ArrayList(linkedList);
        } else {
            arrayList = new ArrayList();
        }
        return arrayList;
    }

    public RequestBuilder addParameter(NameValuePair nvp) {
        Args.notNull(nvp, "Name value pair");
        if (this.parameters == null) {
            this.parameters = new LinkedList<>();
        }
        this.parameters.add(nvp);
        return this;
    }

    public RequestBuilder addParameter(String name, String value) {
        return addParameter(new BasicNameValuePair(name, value));
    }

    public RequestBuilder addParameters(NameValuePair... nvps) {
        for (NameValuePair nvp : nvps) {
            addParameter(nvp);
        }
        return this;
    }

    public RequestConfig getConfig() {
        return this.config;
    }

    public RequestBuilder setConfig(RequestConfig config2) {
        this.config = config2;
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if ("PUT".equalsIgnoreCase(r5.method) != false) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.client.methods.HttpUriRequest build() {
        /*
            r5 = this;
            java.net.URI r0 = r5.uri
            if (r0 == 0) goto L_0x0005
            goto L_0x000b
        L_0x0005:
            java.lang.String r0 = "/"
            java.net.URI r0 = java.net.URI.create(r0)
        L_0x000b:
            cz.msebera.android.httpclient.HttpEntity r1 = r5.entity
            java.util.LinkedList<cz.msebera.android.httpclient.NameValuePair> r2 = r5.parameters
            if (r2 == 0) goto L_0x004a
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x004a
            if (r1 != 0) goto L_0x0038
            java.lang.String r2 = r5.method
            java.lang.String r3 = "POST"
            boolean r2 = r3.equalsIgnoreCase(r2)
            if (r2 != 0) goto L_0x002d
            java.lang.String r2 = r5.method
            java.lang.String r3 = "PUT"
            boolean r2 = r3.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x0038
        L_0x002d:
            cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity r2 = new cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity
            java.util.LinkedList<cz.msebera.android.httpclient.NameValuePair> r3 = r5.parameters
            java.nio.charset.Charset r4 = p008cz.msebera.android.httpclient.protocol.HTTP.DEF_CONTENT_CHARSET
            r2.<init>(r3, r4)
            r1 = r2
            goto L_0x004a
        L_0x0038:
            cz.msebera.android.httpclient.client.utils.URIBuilder r2 = new cz.msebera.android.httpclient.client.utils.URIBuilder     // Catch:{ URISyntaxException -> 0x0049 }
            r2.<init>(r0)     // Catch:{ URISyntaxException -> 0x0049 }
            java.util.LinkedList<cz.msebera.android.httpclient.NameValuePair> r3 = r5.parameters     // Catch:{ URISyntaxException -> 0x0049 }
            cz.msebera.android.httpclient.client.utils.URIBuilder r2 = r2.addParameters(r3)     // Catch:{ URISyntaxException -> 0x0049 }
            java.net.URI r2 = r2.build()     // Catch:{ URISyntaxException -> 0x0049 }
            r0 = r2
            goto L_0x004a
        L_0x0049:
            r2 = move-exception
        L_0x004a:
            if (r1 != 0) goto L_0x0054
            cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalRequest r2 = new cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalRequest
            java.lang.String r3 = r5.method
            r2.<init>(r3)
            goto L_0x005f
        L_0x0054:
            cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalEntityEclosingRequest r2 = new cz.msebera.android.httpclient.client.methods.RequestBuilder$InternalEntityEclosingRequest
            java.lang.String r3 = r5.method
            r2.<init>(r3)
            r2.setEntity(r1)
        L_0x005f:
            cz.msebera.android.httpclient.ProtocolVersion r3 = r5.version
            r2.setProtocolVersion(r3)
            r2.setURI(r0)
            cz.msebera.android.httpclient.message.HeaderGroup r3 = r5.headergroup
            if (r3 == 0) goto L_0x0072
            cz.msebera.android.httpclient.Header[] r3 = r3.getAllHeaders()
            r2.setHeaders(r3)
        L_0x0072:
            cz.msebera.android.httpclient.client.config.RequestConfig r3 = r5.config
            r2.setConfig(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.client.methods.RequestBuilder.build():cz.msebera.android.httpclient.client.methods.HttpUriRequest");
    }
}
