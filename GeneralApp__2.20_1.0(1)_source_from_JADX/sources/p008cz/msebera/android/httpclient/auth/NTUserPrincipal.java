package p008cz.msebera.android.httpclient.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.LangUtils;

/* renamed from: cz.msebera.android.httpclient.auth.NTUserPrincipal */
public class NTUserPrincipal implements Principal, Serializable {
    private static final long serialVersionUID = -6870169797924406894L;
    private final String domain;
    private final String ntname;
    private final String username;

    public NTUserPrincipal(String domain2, String username2) {
        Args.notNull(username2, "User name");
        this.username = username2;
        if (domain2 != null) {
            this.domain = domain2.toUpperCase(Locale.ENGLISH);
        } else {
            this.domain = null;
        }
        String str = this.domain;
        if (str == null || str.length() <= 0) {
            this.ntname = this.username;
            return;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.domain);
        buffer.append('\\');
        buffer.append(this.username);
        this.ntname = buffer.toString();
    }

    public String getName() {
        return this.ntname;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getUsername() {
        return this.username;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.username), (Object) this.domain);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof NTUserPrincipal) {
            NTUserPrincipal that = (NTUserPrincipal) o;
            if (LangUtils.equals((Object) this.username, (Object) that.username) && LangUtils.equals((Object) this.domain, (Object) that.domain)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.ntname;
    }
}
