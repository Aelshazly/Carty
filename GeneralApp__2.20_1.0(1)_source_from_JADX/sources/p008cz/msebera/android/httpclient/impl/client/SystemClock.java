package p008cz.msebera.android.httpclient.impl.client;

/* renamed from: cz.msebera.android.httpclient.impl.client.SystemClock */
class SystemClock implements Clock {
    SystemClock() {
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
