package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Collection;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.cookie.params.CookieSpecPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory */
public class BrowserCompatSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] datepatterns;
    private final SecurityLevel securityLevel;

    /* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory$SecurityLevel */
    public enum SecurityLevel {
        SECURITYLEVEL_DEFAULT,
        SECURITYLEVEL_IE_MEDIUM
    }

    public BrowserCompatSpecFactory(String[] datepatterns2, SecurityLevel securityLevel2) {
        this.datepatterns = datepatterns2;
        this.securityLevel = securityLevel2;
    }

    public BrowserCompatSpecFactory(String[] datepatterns2) {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public BrowserCompatSpecFactory() {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public CookieSpec newInstance(HttpParams params) {
        if (params == null) {
            return new BrowserCompatSpec(null, this.securityLevel);
        }
        String[] patterns = null;
        Collection<?> param = (Collection) params.getParameter(CookieSpecPNames.DATE_PATTERNS);
        if (param != null) {
            patterns = (String[]) param.toArray(new String[param.size()]);
        }
        return new BrowserCompatSpec(patterns, this.securityLevel);
    }

    public CookieSpec create(HttpContext context) {
        return new BrowserCompatSpec(this.datepatterns);
    }
}
