package p008cz.msebera.android.httpclient.entity.mime.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.MIME;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.content.StringBody */
public class StringBody extends AbstractContentBody {
    private final byte[] content;

    @Deprecated
    public static StringBody create(String text, String mimeType, Charset charset) throws IllegalArgumentException {
        try {
            return new StringBody(text, mimeType, charset);
        } catch (UnsupportedEncodingException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Charset ");
            sb.append(charset);
            sb.append(" is not supported");
            throw new IllegalArgumentException(sb.toString(), ex);
        }
    }

    @Deprecated
    public static StringBody create(String text, Charset charset) throws IllegalArgumentException {
        return create(text, null, charset);
    }

    @Deprecated
    public static StringBody create(String text) throws IllegalArgumentException {
        return create(text, null, null);
    }

    @Deprecated
    public StringBody(String text, String mimeType, Charset charset) throws UnsupportedEncodingException {
        this(text, ContentType.create(mimeType, charset));
    }

    @Deprecated
    public StringBody(String text, Charset charset) throws UnsupportedEncodingException {
        this(text, HTTP.PLAIN_TEXT_TYPE, charset);
    }

    @Deprecated
    public StringBody(String text) throws UnsupportedEncodingException {
        this(text, HTTP.PLAIN_TEXT_TYPE, Consts.ASCII);
    }

    public StringBody(String text, ContentType contentType) {
        super(contentType);
        Args.notNull(text, "Text");
        Charset charset = contentType.getCharset();
        String csname = charset != null ? charset.name() : Consts.ASCII.name();
        try {
            this.content = text.getBytes(csname);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedCharsetException(csname);
        }
    }

    public Reader getReader() {
        Charset charset = getContentType().getCharset();
        return new InputStreamReader(new ByteArrayInputStream(this.content), charset != null ? charset : Consts.ASCII);
    }

    public void writeTo(OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        InputStream in = new ByteArrayInputStream(this.content);
        byte[] tmp = new byte[4096];
        while (true) {
            int read = in.read(tmp);
            int l = read;
            if (read != -1) {
                out.write(tmp, 0, l);
            } else {
                out.flush();
                return;
            }
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_8BIT;
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public String getFilename() {
        return null;
    }
}
