package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.io.ContentLengthOutputStream */
public class ContentLengthOutputStream extends OutputStream {
    private boolean closed = false;
    private final long contentLength;
    private final SessionOutputBuffer out;
    private long total = 0;

    public ContentLengthOutputStream(SessionOutputBuffer out2, long contentLength2) {
        this.out = (SessionOutputBuffer) Args.notNull(out2, "Session output buffer");
        this.contentLength = Args.notNegative(contentLength2, "Content length");
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
            long j = this.total;
            long j2 = this.contentLength;
            if (j < j2) {
                long max = j2 - j;
                int chunk = len;
                if (((long) chunk) > max) {
                    chunk = (int) max;
                }
                this.out.write(b, off, chunk);
                this.total += (long) chunk;
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(int b) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.total < this.contentLength) {
            this.out.write(b);
            this.total++;
        }
    }
}
