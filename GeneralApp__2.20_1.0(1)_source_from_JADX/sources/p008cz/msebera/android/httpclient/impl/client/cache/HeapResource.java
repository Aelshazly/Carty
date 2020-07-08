package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.client.cache.Resource;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.HeapResource */
public class HeapResource implements Resource {
    private static final long serialVersionUID = -2078599905620463394L;

    /* renamed from: b */
    private final byte[] f234b;

    public HeapResource(byte[] b) {
        this.f234b = b;
    }

    /* access modifiers changed from: 0000 */
    public byte[] getByteArray() {
        return this.f234b;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.f234b);
    }

    public long length() {
        return (long) this.f234b.length;
    }

    public void dispose() {
    }
}
