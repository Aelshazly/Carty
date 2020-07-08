package p008cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import p008cz.msebera.android.httpclient.message.BasicHeader;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.entity.GzipCompressingEntity */
public class GzipCompressingEntity extends HttpEntityWrapper {
    private static final String GZIP_CODEC = "gzip";

    public GzipCompressingEntity(HttpEntity entity) {
        super(entity);
    }

    public Header getContentEncoding() {
        return new BasicHeader("Content-Encoding", "gzip");
    }

    public long getContentLength() {
        return -1;
    }

    public boolean isChunked() {
        return true;
    }

    public InputStream getContent() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        GZIPOutputStream gzip = new GZIPOutputStream(outstream);
        this.wrappedEntity.writeTo(gzip);
        gzip.close();
    }
}
