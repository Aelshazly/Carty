package p008cz.msebera.android.httpclient;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.HttpHost */
public final class HttpHost implements Cloneable, Serializable {
    public static final String DEFAULT_SCHEME_NAME = "http";
    private static final long serialVersionUID = -7529410654042457626L;
    protected final InetAddress address;
    protected final String hostname;
    protected final String lcHostname;
    protected final int port;
    protected final String schemeName;

    public HttpHost(String hostname2, int port2, String scheme) {
        this.hostname = (String) Args.notBlank(hostname2, "Host name");
        this.lcHostname = hostname2.toLowerCase(Locale.ENGLISH);
        if (scheme != null) {
            this.schemeName = scheme.toLowerCase(Locale.ENGLISH);
        } else {
            this.schemeName = DEFAULT_SCHEME_NAME;
        }
        this.port = port2;
        this.address = null;
    }

    public HttpHost(String hostname2, int port2) {
        this(hostname2, port2, (String) null);
    }

    public HttpHost(String hostname2) {
        this(hostname2, -1, (String) null);
    }

    public HttpHost(InetAddress address2, int port2, String scheme) {
        this.address = (InetAddress) Args.notNull(address2, "Inet address");
        this.hostname = address2.getHostAddress();
        this.lcHostname = this.hostname.toLowerCase(Locale.ENGLISH);
        if (scheme != null) {
            this.schemeName = scheme.toLowerCase(Locale.ENGLISH);
        } else {
            this.schemeName = DEFAULT_SCHEME_NAME;
        }
        this.port = port2;
    }

    public HttpHost(InetAddress address2, int port2) {
        this(address2, port2, (String) null);
    }

    public HttpHost(InetAddress address2) {
        this(address2, -1, (String) null);
    }

    public HttpHost(HttpHost httphost) {
        Args.notNull(httphost, "HTTP host");
        this.hostname = httphost.hostname;
        this.lcHostname = httphost.lcHostname;
        this.schemeName = httphost.schemeName;
        this.port = httphost.port;
        this.address = httphost.address;
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String toURI() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.schemeName);
        buffer.append("://");
        buffer.append(this.hostname);
        if (this.port != -1) {
            buffer.append(':');
            buffer.append(Integer.toString(this.port));
        }
        return buffer.toString();
    }

    public String toHostString() {
        if (this.port == -1) {
            return this.hostname;
        }
        StringBuilder buffer = new StringBuilder(this.hostname.length() + 6);
        buffer.append(this.hostname);
        buffer.append(":");
        buffer.append(Integer.toString(this.port));
        return buffer.toString();
    }

    public String toString() {
        return toURI();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        HttpHost that = (HttpHost) obj;
        if (!this.lcHostname.equals(that.lcHostname) || this.port != that.port || !this.schemeName.equals(that.schemeName)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.lcHostname), this.port), (Object) this.schemeName);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
