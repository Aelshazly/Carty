package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.client.UserTokenHandler;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.impl.client.NoopUserTokenHandler */
public class NoopUserTokenHandler implements UserTokenHandler {
    public static final NoopUserTokenHandler INSTANCE = new NoopUserTokenHandler();

    public Object getUserToken(HttpContext context) {
        return null;
    }
}
