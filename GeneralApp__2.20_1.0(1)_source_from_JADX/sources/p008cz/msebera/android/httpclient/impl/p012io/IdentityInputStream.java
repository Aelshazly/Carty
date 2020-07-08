package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.p013io.BufferInfo;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.io.IdentityInputStream */
public class IdentityInputStream extends InputStream {
    private boolean closed = false;

    /* renamed from: in */
    private final SessionInputBuffer f243in;

    public IdentityInputStream(SessionInputBuffer in) {
        this.f243in = (SessionInputBuffer) Args.notNull(in, "Session input buffer");
    }

    public int available() throws IOException {
        SessionInputBuffer sessionInputBuffer = this.f243in;
        if (sessionInputBuffer instanceof BufferInfo) {
            return ((BufferInfo) sessionInputBuffer).length();
        }
        return 0;
    }

    public void close() throws IOException {
        this.closed = true;
    }

    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f243in.read();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f243in.read(b, off, len);
    }
}
