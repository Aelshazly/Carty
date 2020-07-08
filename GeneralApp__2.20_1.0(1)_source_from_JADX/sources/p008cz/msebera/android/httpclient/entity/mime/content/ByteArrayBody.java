package p008cz.msebera.android.httpclient.entity.mime.content;

import java.io.IOException;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.MIME;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.ByteArrayBody */
public class ByteArrayBody extends AbstractContentBody {
    private final byte[] data;
    private final String filename;

    @Deprecated
    public ByteArrayBody(byte[] data2, String mimeType, String filename2) {
        this(data2, ContentType.create(mimeType), filename2);
    }

    public ByteArrayBody(byte[] data2, ContentType contentType, String filename2) {
        super(contentType);
        Args.notNull(data2, "byte[]");
        this.data = data2;
        this.filename = filename2;
    }

    public ByteArrayBody(byte[] data2, String filename2) {
        this(data2, "application/octet-stream", filename2);
    }

    public String getFilename() {
        return this.filename;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(this.data);
    }

    public String getCharset() {
        return null;
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public long getContentLength() {
        return (long) this.data.length;
    }
}
