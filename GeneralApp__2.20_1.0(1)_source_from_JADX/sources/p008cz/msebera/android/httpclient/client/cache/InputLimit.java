package p008cz.msebera.android.httpclient.client.cache;

/* renamed from: cz.msebera.android.httpclient.client.cache.InputLimit */
public class InputLimit {
    private boolean reached = false;
    private final long value;

    public InputLimit(long value2) {
        this.value = value2;
    }

    public long getValue() {
        return this.value;
    }

    public void reached() {
        this.reached = true;
    }

    public boolean isReached() {
        return this.reached;
    }
}
