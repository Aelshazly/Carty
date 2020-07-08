package p008cz.msebera.android.httpclient.impl.client;

import java.util.List;
import java.util.Map;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.auth.params.AuthPNames;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultTargetAuthenticationHandler */
public class DefaultTargetAuthenticationHandler extends AbstractAuthenticationHandler {
    public boolean isAuthenticationRequested(HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        return response.getStatusLine().getStatusCode() == 401;
    }

    public Map<String, Header> getChallenges(HttpResponse response, HttpContext context) throws MalformedChallengeException {
        Args.notNull(response, "HTTP response");
        return parseChallenges(response.getHeaders("WWW-Authenticate"));
    }

    /* access modifiers changed from: protected */
    public List<String> getAuthPreferences(HttpResponse response, HttpContext context) {
        List<String> authpref = (List) response.getParams().getParameter(AuthPNames.TARGET_AUTH_PREF);
        if (authpref != null) {
            return authpref;
        }
        return super.getAuthPreferences(response, context);
    }
}
