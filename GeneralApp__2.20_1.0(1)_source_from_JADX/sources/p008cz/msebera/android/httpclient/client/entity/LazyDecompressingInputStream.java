package p008cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: cz.msebera.android.httpclient.client.entity.LazyDecompressingInputStream */
class LazyDecompressingInputStream extends InputStream {
    private final DecompressingEntity decompressingEntity;
    private final InputStream wrappedStream;
    private InputStream wrapperStream;

    public LazyDecompressingInputStream(InputStream wrappedStream2, DecompressingEntity decompressingEntity2) {
        this.wrappedStream = wrappedStream2;
        this.decompressingEntity = decompressingEntity2;
    }

    private void initWrapper() throws IOException {
        if (this.wrapperStream == null) {
            this.wrapperStream = this.decompressingEntity.decorate(this.wrappedStream);
        }
    }

    public int read() throws IOException {
        initWrapper();
        return this.wrapperStream.read();
    }

    public int read(byte[] b) throws IOException {
        initWrapper();
        return this.wrapperStream.read(b);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        initWrapper();
        return this.wrapperStream.read(b, off, len);
    }

    public long skip(long n) throws IOException {
        initWrapper();
        return this.wrapperStream.skip(n);
    }

    public boolean markSupported() {
        return false;
    }

    public int available() throws IOException {
        initWrapper();
        return this.wrapperStream.available();
    }

    public void close() throws IOException {
        try {
            if (this.wrapperStream != null) {
                this.wrapperStream.close();
            }
        } finally {
            this.wrappedStream.close();
        }
    }
}
