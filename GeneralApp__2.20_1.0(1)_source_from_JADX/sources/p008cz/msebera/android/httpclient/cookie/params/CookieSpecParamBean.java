package p008cz.msebera.android.httpclient.cookie.params;

import java.util.Collection;
import p008cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.cookie.params.CookieSpecParamBean */
public class CookieSpecParamBean extends HttpAbstractParamBean {
    public CookieSpecParamBean(HttpParams params) {
        super(params);
    }

    public void setDatePatterns(Collection<String> patterns) {
        this.params.setParameter(CookieSpecPNames.DATE_PATTERNS, patterns);
    }

    public void setSingleHeader(boolean singleHeader) {
        this.params.setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, singleHeader);
    }
}
