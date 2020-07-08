package p008cz.msebera.android.httpclient.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.entity.BufferedHttpEntity */
public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] buffer;

    public BufferedHttpEntity(HttpEntity entity) throws IOException {
        super(entity);
        if (!entity.isRepeatable() || entity.getContentLength() < 0) {
            this.buffer = EntityUtils.toByteArray(entity);
        } else {
            this.buffer = null;
        }
    }

    public long getContentLength() {
        byte[] bArr = this.buffer;
        if (bArr != null) {
            return (long) bArr.length;
        }
        return super.getContentLength();
    }

    public InputStream getContent() throws IOException {
        byte[] bArr = this.buffer;
        if (bArr != null) {
            return new ByteArrayInputStream(bArr);
        }
        return super.getContent();
    }

    public boolean isChunked() {
        return this.buffer == null && super.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        byte[] bArr = this.buffer;
        if (bArr != null) {
            outstream.write(bArr);
        } else {
            super.writeTo(outstream);
        }
    }

    public boolean isStreaming() {
        return this.buffer == null && super.isStreaming();
    }
}
