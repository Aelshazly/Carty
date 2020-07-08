package p008cz.msebera.android.httpclient.cookie;

import java.util.Locale;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.cookie.CookieOrigin */
public final class CookieOrigin {
    private final String host;
    private final String path;
    private final int port;
    private final boolean secure;

    public CookieOrigin(String host2, int port2, String path2, boolean secure2) {
        Args.notBlank(host2, "Host");
        Args.notNegative(port2, "Port");
        Args.notNull(path2, "Path");
        this.host = host2.toLowerCase(Locale.ENGLISH);
        this.port = port2;
        if (path2.trim().length() != 0) {
            this.path = path2;
        } else {
            this.path = "/";
        }
        this.secure = secure2;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public int getPort() {
        return this.port;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        if (this.secure) {
            buffer.append("(secure)");
        }
        buffer.append(this.host);
        buffer.append(':');
        buffer.append(Integer.toString(this.port));
        buffer.append(this.path);
        buffer.append(']');
        return buffer.toString();
    }
}
