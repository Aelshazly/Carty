package p008cz.msebera.android.httpclient.client.protocol;

import java.util.Queue;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.auth.AuthOption;
import p008cz.msebera.android.httpclient.auth.AuthProtocolState;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAuthenticationBase */
abstract class RequestAuthenticationBase implements HttpRequestInterceptor {
    final HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAuthenticationBase$1 */
    static /* synthetic */ class C09661 {
        static final /* synthetic */ int[] $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState = new int[AuthProtocolState.values().length];

        static {
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void process(AuthState authState, HttpRequest request, HttpContext context) {
        AuthScheme authScheme = authState.getAuthScheme();
        Credentials creds = authState.getCredentials();
        int i = C09661.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
        if (i != 1) {
            String str = " authentication error: ";
            if (i == 2) {
                ensureAuthScheme(authScheme);
                if (authScheme.isConnectionBased()) {
                    return;
                }
            } else if (i == 3) {
                Queue<AuthOption> authOptions = authState.getAuthOptions();
                if (authOptions != null) {
                    while (!authOptions.isEmpty()) {
                        AuthOption authOption = (AuthOption) authOptions.remove();
                        AuthScheme authScheme2 = authOption.getAuthScheme();
                        Credentials creds2 = authOption.getCredentials();
                        authState.update(authScheme2, creds2);
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog = this.log;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Generating response to an authentication challenge using ");
                            sb.append(authScheme2.getSchemeName());
                            sb.append(" scheme");
                            httpClientAndroidLog.debug(sb.toString());
                        }
                        try {
                            request.addHeader(authenticate(authScheme2, creds2, request, context));
                            break;
                        } catch (AuthenticationException ex) {
                            if (this.log.isWarnEnabled()) {
                                HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(authScheme2);
                                sb2.append(str);
                                sb2.append(ex.getMessage());
                                httpClientAndroidLog2.warn(sb2.toString());
                            }
                        }
                    }
                    return;
                }
                ensureAuthScheme(authScheme);
            }
            if (authScheme != null) {
                try {
                    request.addHeader(authenticate(authScheme, creds, request, context));
                } catch (AuthenticationException ex2) {
                    if (this.log.isErrorEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(authScheme);
                        sb3.append(str);
                        sb3.append(ex2.getMessage());
                        httpClientAndroidLog3.error(sb3.toString());
                    }
                }
            }
        }
    }

    private void ensureAuthScheme(AuthScheme authScheme) {
        Asserts.notNull(authScheme, "Auth scheme");
    }

    private Header authenticate(AuthScheme authScheme, Credentials creds, HttpRequest request, HttpContext context) throws AuthenticationException {
        Asserts.notNull(authScheme, "Auth scheme");
        if (authScheme instanceof ContextAwareAuthScheme) {
            return ((ContextAwareAuthScheme) authScheme).authenticate(creds, request, context);
        }
        return authScheme.authenticate(creds, request);
    }
}
