package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;

/* renamed from: cz.msebera.android.httpclient.impl.io.ChunkedOutputStream */
public class ChunkedOutputStream extends OutputStream {
    private final byte[] cache;
    private int cachePosition;
    private boolean closed;
    private final SessionOutputBuffer out;
    private boolean wroteLastChunk;

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer out2, int bufferSize) throws IOException {
        this(bufferSize, out2);
    }

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer out2) throws IOException {
        this(2048, out2);
    }

    public ChunkedOutputStream(int bufferSize, SessionOutputBuffer out2) {
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.closed = false;
        this.cache = new byte[bufferSize];
        this.out = out2;
    }

    /* access modifiers changed from: protected */
    public void flushCache() throws IOException {
        int i = this.cachePosition;
        if (i > 0) {
            this.out.writeLine(Integer.toHexString(i));
            this.out.write(this.cache, 0, this.cachePosition);
            this.out.writeLine("");
            this.cachePosition = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void flushCacheWithAppend(byte[] bufferToAppend, int off, int len) throws IOException {
        this.out.writeLine(Integer.toHexString(this.cachePosition + len));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bufferToAppend, off, len);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    /* access modifiers changed from: protected */
    public void writeClosingChunk() throws IOException {
        this.out.writeLine("0");
        this.out.writeLine("");
    }

    public void finish() throws IOException {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    public void write(int b) throws IOException {
        if (!this.closed) {
            byte[] bArr = this.cache;
            int i = this.cachePosition;
            bArr[i] = (byte) b;
            this.cachePosition = i + 1;
            if (this.cachePosition == bArr.length) {
                flushCache();
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] src, int off, int len) throws IOException {
        if (!this.closed) {
            byte[] bArr = this.cache;
            int length = bArr.length;
            int i = this.cachePosition;
            if (len >= length - i) {
                flushCacheWithAppend(src, off, len);
                return;
            }
            System.arraycopy(src, off, bArr, i, len);
            this.cachePosition += len;
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void flush() throws IOException {
        flushCache();
        this.out.flush();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            finish();
            this.out.flush();
        }
    }
}
