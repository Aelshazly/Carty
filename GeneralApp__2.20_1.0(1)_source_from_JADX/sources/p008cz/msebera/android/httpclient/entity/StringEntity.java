package p008cz.msebera.android.httpclient.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.StringEntity */
public class StringEntity extends AbstractHttpEntity implements Cloneable {
    protected final byte[] content;

    public StringEntity(String string, ContentType contentType) throws UnsupportedCharsetException {
        Args.notNull(string, "Source string");
        Charset charset = contentType != null ? contentType.getCharset() : null;
        if (charset == null) {
            charset = HTTP.DEF_CONTENT_CHARSET;
        }
        try {
            this.content = string.getBytes(charset.name());
            if (contentType != null) {
                setContentType(contentType.toString());
            }
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedCharsetException(charset.name());
        }
    }

    @Deprecated
    public StringEntity(String string, String mimeType, String charset) throws UnsupportedEncodingException {
        Args.notNull(string, "Source string");
        String mt = mimeType != null ? mimeType : HTTP.PLAIN_TEXT_TYPE;
        String cs = charset != null ? charset : "ISO-8859-1";
        this.content = string.getBytes(cs);
        StringBuilder sb = new StringBuilder();
        sb.append(mt);
        sb.append(HTTP.CHARSET_PARAM);
        sb.append(cs);
        setContentType(sb.toString());
    }

    public StringEntity(String string, String charset) throws UnsupportedCharsetException {
        this(string, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset));
    }

    public StringEntity(String string, Charset charset) {
        this(string, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset));
    }

    public StringEntity(String string) throws UnsupportedEncodingException {
        this(string, ContentType.DEFAULT_TEXT);
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public InputStream getContent() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        outstream.write(this.content);
        outstream.flush();
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
