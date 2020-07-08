package p008cz.msebera.android.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: cz.msebera.android.httpclient.HttpEntity */
public interface HttpEntity {
    @Deprecated
    void consumeContent() throws IOException;

    InputStream getContent() throws IOException, IllegalStateException;

    Header getContentEncoding();

    long getContentLength();

    Header getContentType();

    boolean isChunked();

    boolean isRepeatable();

    boolean isStreaming();

    void writeTo(OutputStream outputStream) throws IOException;
}
