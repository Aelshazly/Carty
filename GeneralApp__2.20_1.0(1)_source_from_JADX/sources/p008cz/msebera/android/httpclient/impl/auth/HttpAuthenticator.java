package p008cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthOption;
import p008cz.msebera.android.httpclient.auth.AuthProtocolState;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Asserts;

/* renamed from: cz.msebera.android.httpclient.impl.auth.HttpAuthenticator */
public class HttpAuthenticator {
    public HttpClientAndroidLog log;

    /* renamed from: cz.msebera.android.httpclient.impl.auth.HttpAuthenticator$1 */
    static /* synthetic */ class C09731 {
        static final /* synthetic */ int[] $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState = new int[AuthProtocolState.values().length];

        static {
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public HttpAuthenticator(HttpClientAndroidLog log2) {
        this.log = log2 != null ? log2 : new HttpClientAndroidLog(getClass());
    }

    public HttpAuthenticator() {
        this(null);
    }

    public boolean isAuthenticationRequested(HttpHost host, HttpResponse response, AuthenticationStrategy authStrategy, AuthState authState, HttpContext context) {
        if (authStrategy.isAuthenticationRequested(host, response, context)) {
            this.log.debug("Authentication required");
            if (authState.getState() == AuthProtocolState.SUCCESS) {
                authStrategy.authFailed(host, authState.getAuthScheme(), context);
            }
            return true;
        }
        int i = C09731.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
        if (i == 1 || i == 2) {
            this.log.debug("Authentication succeeded");
            authState.setState(AuthProtocolState.SUCCESS);
            authStrategy.authSucceeded(host, authState.getAuthScheme(), context);
        } else if (i != 3) {
            authState.setState(AuthProtocolState.UNCHALLENGED);
        }
        return false;
    }

    public boolean handleAuthChallenge(HttpHost host, HttpResponse response, AuthenticationStrategy authStrategy, AuthState authState, HttpContext context) {
        Queue<AuthOption> authOptions;
        try {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append(host.toHostString());
                sb.append(" requested authentication");
                httpClientAndroidLog.debug(sb.toString());
            }
            Map<String, Header> challenges = authStrategy.getChallenges(host, response, context);
            if (challenges.isEmpty()) {
                this.log.debug("Response contains no authentication challenges");
                return false;
            }
            AuthScheme authScheme = authState.getAuthScheme();
            int i = C09731.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
            if (i != 1 && i != 2) {
                if (i == 3) {
                    authState.reset();
                } else if (i == 4) {
                    return false;
                } else {
                    if (i != 5) {
                    }
                }
                authOptions = authStrategy.select(challenges, host, response, context);
                if (authOptions != null || authOptions.isEmpty()) {
                    return false;
                }
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Selected authentication options: ");
                    sb2.append(authOptions);
                    httpClientAndroidLog2.debug(sb2.toString());
                }
                authState.setState(AuthProtocolState.CHALLENGED);
                authState.update(authOptions);
                return true;
            } else if (authScheme == null) {
                this.log.debug("Auth scheme is null");
                authStrategy.authFailed(host, null, context);
                authState.reset();
                authState.setState(AuthProtocolState.FAILURE);
                return false;
            }
            if (authScheme != null) {
                Header challenge = (Header) challenges.get(authScheme.getSchemeName().toLowerCase(Locale.ENGLISH));
                if (challenge != null) {
                    this.log.debug("Authorization challenge processed");
                    authScheme.processChallenge(challenge);
                    if (authScheme.isComplete()) {
                        this.log.debug("Authentication failed");
                        authStrategy.authFailed(host, authState.getAuthScheme(), context);
                        authState.reset();
                        authState.setState(AuthProtocolState.FAILURE);
                        return false;
                    }
                    authState.setState(AuthProtocolState.HANDSHAKE);
                    return true;
                }
                authState.reset();
            }
            authOptions = authStrategy.select(challenges, host, response, context);
            if (authOptions != null) {
            }
            return false;
        } catch (MalformedChallengeException ex) {
            if (this.log.isWarnEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Malformed challenge: ");
                sb3.append(ex.getMessage());
                httpClientAndroidLog3.warn(sb3.toString());
            }
            authState.reset();
            return false;
        }
    }

    public void generateAuthResponse(HttpRequest request, AuthState authState, HttpContext context) throws HttpException, IOException {
        AuthScheme authScheme = authState.getAuthScheme();
        Credentials creds = authState.getCredentials();
        int i = C09731.$SwitchMap$cz$msebera$android$httpclient$auth$AuthProtocolState[authState.getState().ordinal()];
        String str = " authentication error: ";
        if (i == 1) {
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
                        request.addHeader(doAuth(authScheme2, creds2, request, context));
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
        } else if (i == 3) {
            ensureAuthScheme(authScheme);
            if (authScheme.isConnectionBased()) {
                return;
            }
        } else if (i == 4) {
            return;
        }
        if (authScheme != null) {
            try {
                request.addHeader(doAuth(authScheme, creds, request, context));
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

    private void ensureAuthScheme(AuthScheme authScheme) {
        Asserts.notNull(authScheme, "Auth scheme");
    }

    private Header doAuth(AuthScheme authScheme, Credentials creds, HttpRequest request, HttpContext context) throws AuthenticationException {
        if (authScheme instanceof ContextAwareAuthScheme) {
            return ((ContextAwareAuthScheme) authScheme).authenticate(creds, request, context);
        }
        return authScheme.authenticate(creds, request);
    }
}
