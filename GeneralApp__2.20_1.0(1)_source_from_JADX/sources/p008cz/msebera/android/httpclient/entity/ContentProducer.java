package p008cz.msebera.android.httpclient.entity;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: cz.msebera.android.httpclient.entity.ContentProducer */
public interface ContentProducer {
    void writeTo(OutputStream outputStream) throws IOException;
}
