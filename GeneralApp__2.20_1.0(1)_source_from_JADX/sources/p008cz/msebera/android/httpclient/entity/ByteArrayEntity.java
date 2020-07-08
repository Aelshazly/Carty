package p008cz.msebera.android.httpclient.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.ByteArrayEntity */
public class ByteArrayEntity extends AbstractHttpEntity implements Cloneable {

    /* renamed from: b */
    private final byte[] f126b;
    @Deprecated
    protected final byte[] content;
    private final int len;
    private final int off;

    public ByteArrayEntity(byte[] b, ContentType contentType) {
        Args.notNull(b, "Source byte array");
        this.content = b;
        this.f126b = b;
        this.off = 0;
        this.len = this.f126b.length;
        if (contentType != null) {
            setContentType(contentType.toString());
        }
    }

    public ByteArrayEntity(byte[] b, int off2, int len2, ContentType contentType) {
        Args.notNull(b, "Source byte array");
        if (off2 < 0 || off2 > b.length || len2 < 0 || off2 + len2 < 0 || off2 + len2 > b.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("off: ");
            sb.append(off2);
            sb.append(" len: ");
            sb.append(len2);
            sb.append(" b.length: ");
            sb.append(b.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        this.content = b;
        this.f126b = b;
        this.off = off2;
        this.len = len2;
        if (contentType != null) {
            setContentType(contentType.toString());
        }
    }

    public ByteArrayEntity(byte[] b) {
        this(b, null);
    }

    public ByteArrayEntity(byte[] b, int off2, int len2) {
        this(b, off2, len2, null);
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return (long) this.len;
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.f126b, this.off, this.len);
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        outstream.write(this.f126b, this.off, this.len);
        outstream.flush();
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
