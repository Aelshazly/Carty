package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.io.IdentityOutputStream */
public class IdentityOutputStream extends OutputStream {
    private boolean closed = false;
    private final SessionOutputBuffer out;

    public IdentityOutputStream(SessionOutputBuffer out2) {
        this.out = (SessionOutputBuffer) Args.notNull(out2, "Session output buffer");
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.closed) {
            this.out.write(b, off, len);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(int b) throws IOException {
        if (!this.closed) {
            this.out.write(b);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}
