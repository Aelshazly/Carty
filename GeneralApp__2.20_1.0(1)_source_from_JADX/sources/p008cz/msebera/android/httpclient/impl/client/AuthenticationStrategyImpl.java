package p008cz.msebera.android.httpclient.impl.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthOption;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.client.AuthCache;
import p008cz.msebera.android.httpclient.client.AuthenticationStrategy;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.client.AuthenticationStrategyImpl */
abstract class AuthenticationStrategyImpl implements AuthenticationStrategy {
    private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[]{"negotiate", "Kerberos", "NTLM", "Digest", "Basic"}));
    private final int challengeCode;
    private final String headerName;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* access modifiers changed from: 0000 */
    public abstract Collection<String> getPreferredAuthSchemes(RequestConfig requestConfig);

    AuthenticationStrategyImpl(int challengeCode2, String headerName2) {
        this.challengeCode = challengeCode2;
        this.headerName = headerName2;
    }

    public boolean isAuthenticationRequested(HttpHost authhost, HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        return response.getStatusLine().getStatusCode() == this.challengeCode;
    }

    public Map<String, Header> getChallenges(HttpHost authhost, HttpResponse response, HttpContext context) throws MalformedChallengeException {
        CharArrayBuffer buffer;
        int pos;
        Args.notNull(response, "HTTP response");
        Header[] headers = response.getHeaders(this.headerName);
        Map<String, Header> map = new HashMap<>(headers.length);
        for (Header header : headers) {
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                pos = ((FormattedHeader) header).getValuePos();
            } else {
                String s = header.getValue();
                if (s != null) {
                    buffer = new CharArrayBuffer(s.length());
                    buffer.append(s);
                    pos = 0;
                } else {
                    throw new MalformedChallengeException("Header value is null");
                }
            }
            while (pos < buffer.length() && HTTP.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            int beginIndex = pos;
            while (pos < buffer.length() && !HTTP.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            map.put(buffer.substring(beginIndex, pos).toLowerCase(Locale.ENGLISH), header);
        }
        return map;
    }

    public Queue<AuthOption> select(Map<String, Header> challenges, HttpHost authhost, HttpResponse response, HttpContext context) throws MalformedChallengeException {
        HttpClientContext clientContext;
        Map<String, Header> map = challenges;
        HttpContext httpContext = context;
        Args.notNull(map, "Map of auth challenges");
        Args.notNull(authhost, "Host");
        Args.notNull(response, "HTTP response");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext clientContext2 = HttpClientContext.adapt(context);
        Queue<AuthOption> options = new LinkedList<>();
        Lookup<AuthSchemeProvider> registry = clientContext2.getAuthSchemeRegistry();
        if (registry == null) {
            this.log.debug("Auth scheme registry not set in the context");
            return options;
        }
        CredentialsProvider credsProvider = clientContext2.getCredentialsProvider();
        if (credsProvider == null) {
            this.log.debug("Credentials provider not set in the context");
            return options;
        }
        Collection<String> authPrefs = getPreferredAuthSchemes(clientContext2.getRequestConfig());
        if (authPrefs == null) {
            authPrefs = DEFAULT_SCHEME_PRIORITY;
        }
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Authentication schemes in the order of preference: ");
            sb.append(authPrefs);
            httpClientAndroidLog.debug(sb.toString());
        }
        for (String id : authPrefs) {
            Header challenge = (Header) map.get(id.toLowerCase(Locale.ENGLISH));
            if (challenge != null) {
                AuthSchemeProvider authSchemeProvider = (AuthSchemeProvider) registry.lookup(id);
                if (authSchemeProvider != null) {
                    clientContext = clientContext2;
                    AuthScheme authScheme = authSchemeProvider.create(httpContext);
                    authScheme.processChallenge(challenge);
                    Credentials credentials = credsProvider.getCredentials(new AuthScope(authhost.getHostName(), authhost.getPort(), authScheme.getRealm(), authScheme.getSchemeName()));
                    if (credentials != null) {
                        options.add(new AuthOption(authScheme, credentials));
                    }
                } else if (this.log.isWarnEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    HttpClientContext clientContext3 = clientContext2;
                    sb2.append("Authentication scheme ");
                    sb2.append(id);
                    sb2.append(" not supported");
                    httpClientAndroidLog2.warn(sb2.toString());
                    map = challenges;
                    clientContext2 = clientContext3;
                } else {
                    map = challenges;
                }
            } else {
                clientContext = clientContext2;
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Challenge for ");
                    sb3.append(id);
                    sb3.append(" authentication scheme not available");
                    httpClientAndroidLog3.debug(sb3.toString());
                }
            }
            map = challenges;
            HttpHost httpHost = authhost;
            HttpResponse httpResponse = response;
            httpContext = context;
            clientContext2 = clientContext;
        }
        return options;
    }

    public void authSucceeded(HttpHost authhost, AuthScheme authScheme, HttpContext context) {
        Args.notNull(authhost, "Host");
        Args.notNull(authScheme, "Auth scheme");
        Args.notNull(context, "HTTP context");
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        if (isCachable(authScheme)) {
            AuthCache authCache = clientContext.getAuthCache();
            if (authCache == null) {
                authCache = new BasicAuthCache();
                clientContext.setAuthCache(authCache);
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

    /* access modifiers changed from: protected */
    public boolean isCachable(AuthScheme authScheme) {
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

    public void authFailed(HttpHost authhost, AuthScheme authScheme, HttpContext context) {
        Args.notNull(authhost, "Host");
        Args.notNull(context, "HTTP context");
        AuthCache authCache = HttpClientContext.adapt(context).getAuthCache();
        if (authCache != null) {
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Clearing cached auth scheme for ");
                sb.append(authhost);
                httpClientAndroidLog.debug(sb.toString());
            }
            authCache.remove(authhost);
        }
    }
}
