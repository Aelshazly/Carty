package p008cz.msebera.android.httpclient.entity.mime.content;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.ContentDescriptor */
public interface ContentDescriptor {
    String getCharset();

    long getContentLength();

    String getMediaType();

    String getMimeType();

    String getSubType();

    String getTransferEncoding();
}
