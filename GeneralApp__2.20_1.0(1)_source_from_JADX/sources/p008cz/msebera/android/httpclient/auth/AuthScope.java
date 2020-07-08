package p008cz.msebera.android.httpclient.auth;

import java.util.Locale;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.auth.AuthScope */
public class AuthScope {
    public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
    public static final String ANY_HOST = null;
    public static final int ANY_PORT = -1;
    public static final String ANY_REALM = null;
    public static final String ANY_SCHEME = null;
    private final String host;
    private final int port;
    private final String realm;
    private final String scheme;

    public AuthScope(String host2, int port2, String realm2, String scheme2) {
        this.host = host2 == null ? ANY_HOST : host2.toLowerCase(Locale.ENGLISH);
        this.port = port2 < 0 ? -1 : port2;
        this.realm = realm2 == null ? ANY_REALM : realm2;
        this.scheme = scheme2 == null ? ANY_SCHEME : scheme2.toUpperCase(Locale.ENGLISH);
    }

    public AuthScope(HttpHost host2, String realm2, String schemeName) {
        this(host2.getHostName(), host2.getPort(), realm2, schemeName);
    }

    public AuthScope(HttpHost host2) {
        this(host2, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(String host2, int port2, String realm2) {
        this(host2, port2, realm2, ANY_SCHEME);
    }

    public AuthScope(String host2, int port2) {
        this(host2, port2, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(AuthScope authscope) {
        Args.notNull(authscope, "Scope");
        this.host = authscope.getHost();
        this.port = authscope.getPort();
        this.realm = authscope.getRealm();
        this.scheme = authscope.getScheme();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getScheme() {
        return this.scheme;
    }

    public int match(AuthScope that) {
        int factor = 0;
        if (LangUtils.equals((Object) this.scheme, (Object) that.scheme)) {
            factor = 0 + 1;
        } else {
            String str = this.scheme;
            String str2 = ANY_SCHEME;
            if (!(str == str2 || that.scheme == str2)) {
                return -1;
            }
        }
        if (LangUtils.equals((Object) this.realm, (Object) that.realm)) {
            factor += 2;
        } else {
            String str3 = this.realm;
            String str4 = ANY_REALM;
            if (!(str3 == str4 || that.realm == str4)) {
                return -1;
            }
        }
        int i = this.port;
        int i2 = that.port;
        if (i == i2) {
            factor += 4;
        } else if (!(i == -1 || i2 == -1)) {
            return -1;
        }
        if (LangUtils.equals((Object) this.host, (Object) that.host)) {
            factor += 8;
        } else {
            String str5 = this.host;
            String str6 = ANY_HOST;
            if (str5 == str6 || that.host == str6) {
                return factor;
            }
            return -1;
        }
        return factor;
    }

    public boolean equals(Object o) {
        boolean z = false;
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthScope)) {
            return super.equals(o);
        }
        AuthScope that = (AuthScope) o;
        if (LangUtils.equals((Object) this.host, (Object) that.host) && this.port == that.port && LangUtils.equals((Object) this.realm, (Object) that.realm) && LangUtils.equals((Object) this.scheme, (Object) that.scheme)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        String str = this.scheme;
        if (str != null) {
            buffer.append(str.toUpperCase(Locale.ENGLISH));
            buffer.append(' ');
        }
        if (this.realm != null) {
            buffer.append('\'');
            buffer.append(this.realm);
            buffer.append('\'');
        } else {
            buffer.append("<any realm>");
        }
        if (this.host != null) {
            buffer.append('@');
            buffer.append(this.host);
            if (this.port >= 0) {
                buffer.append(':');
                buffer.append(this.port);
            }
        }
        return buffer.toString();
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.host), this.port), (Object) this.realm), (Object) this.scheme);
    }
}
