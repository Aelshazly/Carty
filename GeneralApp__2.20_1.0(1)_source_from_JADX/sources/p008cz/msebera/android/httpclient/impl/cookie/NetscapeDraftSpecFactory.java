package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Collection;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.cookie.params.CookieSpecPNames;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory */
public class NetscapeDraftSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final String[] datepatterns;

    public NetscapeDraftSpecFactory(String[] datepatterns2) {
        this.datepatterns = datepatterns2;
    }

    public NetscapeDraftSpecFactory() {
        this(null);
    }

    public CookieSpec newInstance(HttpParams params) {
        if (params == null) {
            return new NetscapeDraftSpec();
        }
        String[] patterns = null;
        Collection<?> param = (Collection) params.getParameter(CookieSpecPNames.DATE_PATTERNS);
        if (param != null) {
            patterns = (String[]) param.toArray(new String[param.size()]);
        }
        return new NetscapeDraftSpec(patterns);
    }

    public CookieSpec create(HttpContext context) {
        return new NetscapeDraftSpec(this.datepatterns);
    }
}
