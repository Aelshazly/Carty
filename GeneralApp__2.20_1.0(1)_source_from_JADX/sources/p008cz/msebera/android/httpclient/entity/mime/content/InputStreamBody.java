package p008cz.msebera.android.httpclient.entity.mime.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.MIME;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.InputStreamBody */
public class InputStreamBody extends AbstractContentBody {
    private final String filename;

    /* renamed from: in */
    private final InputStream f128in;

    @Deprecated
    public InputStreamBody(InputStream in, String mimeType, String filename2) {
        this(in, ContentType.create(mimeType), filename2);
    }

    public InputStreamBody(InputStream in, String filename2) {
        this(in, ContentType.DEFAULT_BINARY, filename2);
    }

    public InputStreamBody(InputStream in, ContentType contentType, String filename2) {
        super(contentType);
        Args.notNull(in, "Input stream");
        this.f128in = in;
        this.filename = filename2;
    }

    public InputStreamBody(InputStream in, ContentType contentType) {
        this(in, contentType, (String) null);
    }

    public InputStream getInputStream() {
        return this.f128in;
    }

    public void writeTo(OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int read = this.f128in.read(tmp);
                int l = read;
                if (read != -1) {
                    out.write(tmp, 0, l);
                } else {
                    out.flush();
                    return;
                }
            }
        } finally {
            this.f128in.close();
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public long getContentLength() {
        return -1;
    }

    public String getFilename() {
        return this.filename;
    }
}
