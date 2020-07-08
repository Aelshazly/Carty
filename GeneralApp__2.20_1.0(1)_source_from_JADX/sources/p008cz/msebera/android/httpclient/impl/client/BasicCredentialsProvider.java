package p008cz.msebera.android.httpclient.impl.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider */
public class BasicCredentialsProvider implements CredentialsProvider {
    private final ConcurrentHashMap<AuthScope, Credentials> credMap = new ConcurrentHashMap<>();

    public void setCredentials(AuthScope authscope, Credentials credentials) {
        Args.notNull(authscope, "Authentication scope");
        this.credMap.put(authscope, credentials);
    }

    private static Credentials matchCredentials(Map<AuthScope, Credentials> map, AuthScope authscope) {
        Credentials creds = (Credentials) map.get(authscope);
        if (creds != null) {
            return creds;
        }
        int bestMatchFactor = -1;
        AuthScope bestMatch = null;
        for (AuthScope current : map.keySet()) {
            int factor = authscope.match(current);
            if (factor > bestMatchFactor) {
                bestMatchFactor = factor;
                bestMatch = current;
            }
        }
        if (bestMatch != null) {
            return (Credentials) map.get(bestMatch);
        }
        return creds;
    }

    public Credentials getCredentials(AuthScope authscope) {
        Args.notNull(authscope, "Authentication scope");
        return matchCredentials(this.credMap, authscope);
    }

    public void clear() {
        this.credMap.clear();
    }

    public String toString() {
        return this.credMap.toString();
    }
}
