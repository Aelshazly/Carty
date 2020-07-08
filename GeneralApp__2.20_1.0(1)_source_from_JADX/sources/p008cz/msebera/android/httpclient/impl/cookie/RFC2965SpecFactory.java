package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Collection;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.cookie.params.CookieSpecPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory */
public class RFC2965SpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] datepatterns;
    private final boolean oneHeader;

    public RFC2965SpecFactory(String[] datepatterns2, boolean oneHeader2) {
        this.datepatterns = datepatterns2;
        this.oneHeader = oneHeader2;
    }

    public RFC2965SpecFactory() {
        this(null, false);
    }

    public CookieSpec newInstance(HttpParams params) {
        if (params == null) {
            return new RFC2965Spec();
        }
        String[] patterns = null;
        Collection<?> param = (Collection) params.getParameter(CookieSpecPNames.DATE_PATTERNS);
        if (param != null) {
            patterns = (String[]) param.toArray(new String[param.size()]);
        }
        return new RFC2965Spec(patterns, params.getBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, false));
    }

    public CookieSpec create(HttpContext context) {
        return new RFC2965Spec(this.datepatterns, this.oneHeader);
    }
}
