package p008cz.msebera.android.httpclient.impl.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.auth.AuthScheme;
import p008cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import p008cz.msebera.android.httpclient.auth.AuthenticationException;
import p008cz.msebera.android.httpclient.auth.MalformedChallengeException;
import p008cz.msebera.android.httpclient.client.AuthenticationHandler;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.AbstractAuthenticationHandler */
public abstract class AbstractAuthenticationHandler implements AuthenticationHandler {
    private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[]{"negotiate", "NTLM", "Digest", "Basic"}));
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    /* access modifiers changed from: protected */
    public Map<String, Header> parseChallenges(Header[] headers) throws MalformedChallengeException {
        CharArrayBuffer buffer;
        int pos;
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

    /* access modifiers changed from: protected */
    public List<String> getAuthPreferences() {
        return DEFAULT_SCHEME_PRIORITY;
    }

    /* access modifiers changed from: protected */
    public List<String> getAuthPreferences(HttpResponse response, HttpContext context) {
        return getAuthPreferences();
    }

    public AuthScheme selectScheme(Map<String, Header> challenges, HttpResponse response, HttpContext context) throws AuthenticationException {
        AuthSchemeRegistry registry = (AuthSchemeRegistry) context.getAttribute("http.authscheme-registry");
        Asserts.notNull(registry, "AuthScheme registry");
        Collection<String> authPrefs = getAuthPreferences(response, context);
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
        AuthScheme authScheme = null;
        Iterator it = authPrefs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String id = (String) it.next();
            if (((Header) challenges.get(id.toLowerCase(Locale.ENGLISH))) != null) {
                if (this.log.isDebugEnabled()) {
                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(id);
                    sb2.append(" authentication scheme selected");
                    httpClientAndroidLog2.debug(sb2.toString());
                }
                try {
                    authScheme = registry.getAuthScheme(id, response.getParams());
                    break;
                } catch (IllegalStateException e) {
                    if (this.log.isWarnEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Authentication scheme ");
                        sb3.append(id);
                        sb3.append(" not supported");
                        httpClientAndroidLog3.warn(sb3.toString());
                    }
                }
            } else if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog4 = this.log;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Challenge for ");
                sb4.append(id);
                sb4.append(" authentication scheme not available");
                httpClientAndroidLog4.debug(sb4.toString());
            }
        }
        if (authScheme != null) {
            return authScheme;
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Unable to respond to any of these challenges: ");
        sb5.append(challenges);
        throw new AuthenticationException(sb5.toString());
    }
}
