package p008cz.msebera.android.httpclient.client;

import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.auth.AuthScheme;

/* renamed from: cz.msebera.android.httpclient.client.AuthCache */
public interface AuthCache {
    void clear();

    AuthScheme get(HttpHost httpHost);

    void put(HttpHost httpHost, AuthScheme authScheme);

    void remove(HttpHost httpHost);
}
