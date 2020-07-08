package p008cz.msebera.android.httpclient.entity.mime.content;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.ContentBody */
public interface ContentBody extends ContentDescriptor {
    String getFilename();

    void writeTo(OutputStream outputStream) throws IOException;
}
