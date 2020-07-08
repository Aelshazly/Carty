package p008cz.msebera.android.httpclient.auth.params;

import p008cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.auth.params.AuthParamBean */
public class AuthParamBean extends HttpAbstractParamBean {
    public AuthParamBean(HttpParams params) {
        super(params);
    }

    public void setCredentialCharset(String charset) {
        AuthParams.setCredentialCharset(this.params, charset);
    }
}
