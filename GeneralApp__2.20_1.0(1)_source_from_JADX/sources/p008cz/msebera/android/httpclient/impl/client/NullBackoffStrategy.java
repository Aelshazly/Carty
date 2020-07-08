package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;

/* renamed from: cz.msebera.android.httpclient.impl.client.NullBackoffStrategy */
public class NullBackoffStrategy implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(Throwable t) {
        return false;
    }

    public boolean shouldBackoff(HttpResponse resp) {
        return false;
    }
}
