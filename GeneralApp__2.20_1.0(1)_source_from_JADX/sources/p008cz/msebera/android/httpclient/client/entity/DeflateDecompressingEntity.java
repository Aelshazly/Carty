package p008cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;

/* renamed from: cz.msebera.android.httpclient.client.entity.DeflateDecompressingEntity */
public class DeflateDecompressingEntity extends DecompressingEntity {
    public /* bridge */ /* synthetic */ InputStream getContent() throws IOException {
        return super.getContent();
    }

    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }

    public DeflateDecompressingEntity(HttpEntity entity) {
        super(entity);
    }

    /* access modifiers changed from: 0000 */
    public InputStream decorate(InputStream wrapped) throws IOException {
        return new DeflateInputStream(wrapped);
    }

    public Header getContentEncoding() {
        return null;
    }

    public long getContentLength() {
        return -1;
    }
}
