package p008cz.msebera.android.httpclient.impl.p012io;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import p008cz.msebera.android.httpclient.ConnectionClosedException;
import p008cz.msebera.android.httpclient.p013io.BufferInfo;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.io.ContentLengthInputStream */
public class ContentLengthInputStream extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private boolean closed = false;
    private final long contentLength;

    /* renamed from: in */
    private SessionInputBuffer f242in = null;
    private long pos = 0;

    public ContentLengthInputStream(SessionInputBuffer in, long contentLength2) {
        this.f242in = (SessionInputBuffer) Args.notNull(in, "Session input buffer");
        this.contentLength = Args.notNegative(contentLength2, "Content length");
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (this.pos < this.contentLength) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
            } finally {
                this.closed = true;
            }
        }
    }

    public int available() throws IOException {
        SessionInputBuffer sessionInputBuffer = this.f242in;
        if (sessionInputBuffer instanceof BufferInfo) {
            return Math.min(((BufferInfo) sessionInputBuffer).length(), (int) (this.contentLength - this.pos));
        }
        return 0;
    }

    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            int b = this.f242in.read();
            if (b != -1) {
                this.pos++;
            } else if (this.pos < this.contentLength) {
                StringBuilder sb = new StringBuilder();
                sb.append("Premature end of Content-Length delimited message body (expected: ");
                sb.append(this.contentLength);
                sb.append("; received: ");
                sb.append(this.pos);
                throw new ConnectionClosedException(sb.toString());
            }
            return b;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (!this.closed) {
            long j = this.pos;
            long j2 = this.contentLength;
            if (j >= j2) {
                return -1;
            }
            int chunk = len;
            if (((long) len) + j > j2) {
                chunk = (int) (j2 - j);
            }
            int count = this.f242in.read(b, off, chunk);
            if (count != -1 || this.pos >= this.contentLength) {
                if (count > 0) {
                    this.pos += (long) count;
                }
                return count;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Premature end of Content-Length delimited message body (expected: ");
            sb.append(this.contentLength);
            sb.append("; received: ");
            sb.append(this.pos);
            throw new ConnectionClosedException(sb.toString());
        }
        throw new IOException("Attempted read from closed stream.");
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0;
        }
        byte[] buffer = new byte[2048];
        long remaining = Math.min(n, this.contentLength - this.pos);
        long count = 0;
        while (remaining > 0) {
            int l = read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining));
            if (l == -1) {
                break;
            }
            count += (long) l;
            remaining -= (long) l;
        }
        return count;
    }
}
