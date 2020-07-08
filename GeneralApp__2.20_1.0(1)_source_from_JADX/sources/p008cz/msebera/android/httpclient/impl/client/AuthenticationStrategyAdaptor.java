package p008cz.msebera.android.httpclient.impl.client;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthOption;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.client.AuthCache;
import p008cz.msebera.android.httpclient.client.AuthenticationHandler;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.AuthenticationStrategyAdaptor */
class AuthenticationStrategyAdaptor implements AuthenticationStrategy {
    private final AuthenticationHandler handler;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public AuthenticationStrategyAdaptor(AuthenticationHandler handler2) {
        this.handler = handler2;
    }

    public boolean isAuthenticationRequested(HttpHost authhost, HttpResponse response, HttpContext context) {
        return this.handler.isAuthenticationRequested(response, context);
    }

    public Map<String, Header> getChallenges(HttpHost authhost, HttpResponse response, HttpContext context) throws MalformedChallengeException {
        return this.handler.getChallenges(response, context);
    }

    public Queue<AuthOption> select(Map<String, Header> challenges, HttpHost authhost, HttpResponse response, HttpContext context) throws MalformedChallengeException {
        Args.notNull(challenges, "Map of auth challenges");
        Args.notNull(authhost, "Host");
        Args.notNull(response, "HTTP response");
        Args.notNull(context, "HTTP context");
        Queue<AuthOption> options = new LinkedList<>();
        CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute("http.auth.credentials-provider");
        if (credsProvider == null) {
            this.log.debug("Credentials provider not set in the context");
            return options;
        }
        try {
            AuthScheme authScheme = this.handler.selectScheme(challenges, response, context);
            authScheme.processChallenge((Header) challenges.get(authScheme.getSchemeName().toLowerCase(Locale.ENGLISH)));
            Credentials credentials = credsProvider.getCredentials(new AuthScope(authhost.getHostName(), authhost.getPort(), authScheme.getRealm(), authScheme.getSchemeName()));
            if (credentials != null) {
                options.add(new AuthOption(authScheme, credentials));
            }
            return options;
        } catch (AuthenticationException ex) {
            if (this.log.isWarnEnabled()) {
                this.log.warn(ex.getMessage(), ex);
            }
            return options;
        }
    }

    public void authSucceeded(HttpHost authhost, AuthScheme authScheme, HttpContext context) {
        String str = "http.auth.auth-cache";
        AuthCache authCache = (AuthCache) context.getAttribute(str);
        if (isCachable(authScheme)) {
            if (authCache == null) {
                authCache = new BasicAuthCache();
                context.setAttribute(str, authCache);
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Caching '");
                sb.append(authScheme.getSchemeName());
                sb.append("' auth scheme for ");
                sb.append(authhost);
                httpClientAndroidLog.debug(sb.toString());
            }
            authCache.put(authhost, authScheme);
        }
    }

    public void authFailed(HttpHost authhost, AuthScheme authScheme, HttpContext context) {
        AuthCache authCache = (AuthCache) context.getAttribute("http.auth.auth-cache");
        if (authCache != null) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Removing from cache '");
                sb.append(authScheme.getSchemeName());
                sb.append("' auth scheme for ");
                sb.append(authhost);
                httpClientAndroidLog.debug(sb.toString());
            }
            authCache.remove(authhost);
        }
    }

    private boolean isCachable(AuthScheme authScheme) {
        boolean z = false;
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String schemeName = authScheme.getSchemeName();
        if (schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest")) {
            z = true;
        }
        return z;
    }

    public AuthenticationHandler getHandler() {
        return this.handler;
    }
}
