package p008cz.msebera.android.httpclient.params;

import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.params.HttpAbstractParamBean */
public abstract class HttpAbstractParamBean {
    protected final HttpParams params;

    public HttpAbstractParamBean(HttpParams params2) {
        this.params = (HttpParams) Args.notNull(params2, "HTTP parameters");
    }
}
