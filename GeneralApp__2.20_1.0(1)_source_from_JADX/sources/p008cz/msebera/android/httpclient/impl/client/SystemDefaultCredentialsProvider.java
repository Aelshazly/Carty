package p008cz.msebera.android.httpclient.impl.client;

import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.NTCredentials;
import p008cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.SystemDefaultCredentialsProvider */
public class SystemDefaultCredentialsProvider implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP = new ConcurrentHashMap();
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    static {
        String str = "Basic";
        SCHEME_MAP.put(str.toUpperCase(Locale.ENGLISH), str);
        String str2 = "Digest";
        SCHEME_MAP.put(str2.toUpperCase(Locale.ENGLISH), str2);
        String str3 = "NTLM";
        SCHEME_MAP.put(str3.toUpperCase(Locale.ENGLISH), str3);
        SCHEME_MAP.put("negotiate".toUpperCase(Locale.ENGLISH), "SPNEGO");
        String str4 = "Kerberos";
        SCHEME_MAP.put(str4.toUpperCase(Locale.ENGLISH), str4);
    }

    private static String translateScheme(String key) {
        if (key == null) {
            return null;
        }
        String s = (String) SCHEME_MAP.get(key);
        return s != null ? s : key;
    }

    public void setCredentials(AuthScope authscope, Credentials credentials) {
        this.internal.setCredentials(authscope, credentials);
    }

    private static PasswordAuthentication getSystemCreds(AuthScope authscope, RequestorType requestorType) {
        String hostname = authscope.getHost();
        int port = authscope.getPort();
        return Authenticator.requestPasswordAuthentication(hostname, null, port, port == 443 ? "https" : HttpHost.DEFAULT_SCHEME_NAME, null, translateScheme(authscope.getScheme()), null, requestorType);
    }

    public Credentials getCredentials(AuthScope authscope) {
        Args.notNull(authscope, "Auth scope");
        Credentials localcreds = this.internal.getCredentials(authscope);
        if (localcreds != null) {
            return localcreds;
        }
        if (authscope.getHost() != null) {
            PasswordAuthentication systemcreds = getSystemCreds(authscope, RequestorType.SERVER);
            if (systemcreds == null) {
                systemcreds = getSystemCreds(authscope, RequestorType.PROXY);
            }
            if (systemcreds != null) {
                String domain = System.getProperty("http.auth.ntlm.domain");
                if (domain != null) {
                    return new NTCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()), null, domain);
                }
                if ("NTLM".equalsIgnoreCase(authscope.getScheme())) {
                    return new NTCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()), null, null);
                }
                return new UsernamePasswordCredentials(systemcreds.getUserName(), new String(systemcreds.getPassword()));
            }
        }
        return null;
    }

    public void clear() {
        this.internal.clear();
    }
}
