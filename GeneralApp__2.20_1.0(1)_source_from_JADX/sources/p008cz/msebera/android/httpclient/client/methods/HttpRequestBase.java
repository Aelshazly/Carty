package p008cz.msebera.android.httpclient.client.methods;

import java.net.URI;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.RequestLine;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.message.BasicRequestLine;
import p008cz.msebera.android.httpclient.params.HttpProtocolParams;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpRequestBase */
public abstract class HttpRequestBase extends AbstractExecutionAwareRequest implements HttpUriRequest, Configurable {
    private RequestConfig config;
    private URI uri;
    private ProtocolVersion version;

    public abstract String getMethod();

    public void setProtocolVersion(ProtocolVersion version2) {
        this.version = version2;
    }

    public ProtocolVersion getProtocolVersion() {
        ProtocolVersion protocolVersion = this.version;
        return protocolVersion != null ? protocolVersion : HttpProtocolParams.getVersion(getParams());
    }

    public URI getURI() {
        return this.uri;
    }

    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion ver = getProtocolVersion();
        URI uri2 = getURI();
        String uritext = null;
        if (uri2 != null) {
            uritext = uri2.toASCIIString();
        }
        if (uritext == null || uritext.length() == 0) {
            uritext = "/";
        }
        return new BasicRequestLine(method, uritext, ver);
    }

    public RequestConfig getConfig() {
        return this.config;
    }

    public void setConfig(RequestConfig config2) {
        this.config = config2;
    }

    public void setURI(URI uri2) {
        this.uri = uri2;
    }

    public void started() {
    }

    public void releaseConnection() {
        reset();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMethod());
        String str = " ";
        sb.append(str);
        sb.append(getURI());
        sb.append(str);
        sb.append(getProtocolVersion());
        return sb.toString();
    }
}
