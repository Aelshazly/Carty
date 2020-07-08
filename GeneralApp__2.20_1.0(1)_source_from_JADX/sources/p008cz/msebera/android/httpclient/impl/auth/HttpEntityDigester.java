package p008cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* renamed from: cz.msebera.android.httpclient.impl.auth.HttpEntityDigester */
class HttpEntityDigester extends OutputStream {
    private boolean closed;
    private byte[] digest;
    private final MessageDigest digester;

    HttpEntityDigester(MessageDigest digester2) {
        this.digester = digester2;
        this.digester.reset();
    }

    public void write(int b) throws IOException {
        if (!this.closed) {
            this.digester.update((byte) b);
            return;
        }
        throw new IOException("Stream has been already closed");
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.closed) {
            this.digester.update(b, off, len);
            return;
        }
        throw new IOException("Stream has been already closed");
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.digest = this.digester.digest();
            super.close();
        }
    }

    public byte[] getDigest() {
        return this.digest;
    }
}
