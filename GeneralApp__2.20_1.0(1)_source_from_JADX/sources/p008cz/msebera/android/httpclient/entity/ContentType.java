package p008cz.msebera.android.httpclient.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import p008cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import p008cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import p008cz.msebera.android.httpclient.message.ParserCursor;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.entity.ContentType */
public final class ContentType implements Serializable {
    public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_FORM_URLENCODED = create(URLEncodedUtils.CONTENT_TYPE, Consts.ISO_8859_1);
    public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
    public static final ContentType APPLICATION_OCTET_STREAM;
    public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
    public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
    public static final ContentType TEXT_PLAIN = create(HTTP.PLAIN_TEXT_TYPE, Consts.ISO_8859_1);
    public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
    public static final ContentType WILDCARD;
    private static final long serialVersionUID = -7768694718232371896L;
    private final Charset charset;
    private final String mimeType;
    private final NameValuePair[] params;

    static {
        Charset charset2 = null;
        APPLICATION_OCTET_STREAM = create("application/octet-stream", charset2);
        WILDCARD = create("*/*", charset2);
    }

    ContentType(String mimeType2, Charset charset2) {
        this.mimeType = mimeType2;
        this.charset = charset2;
        this.params = null;
    }

    ContentType(String mimeType2, NameValuePair[] params2) throws UnsupportedCharsetException {
        this.mimeType = mimeType2;
        this.params = params2;
        String s = getParameter("charset");
        this.charset = !TextUtils.isBlank(s) ? Charset.forName(s) : null;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getParameter(String name) {
        Args.notEmpty(name, "Parameter name");
        NameValuePair[] nameValuePairArr = this.params;
        if (nameValuePairArr == null) {
            return null;
        }
        for (NameValuePair param : nameValuePairArr) {
            if (param.getName().equalsIgnoreCase(name)) {
                return param.getValue();
            }
        }
        return null;
    }

    public String toString() {
        CharArrayBuffer buf = new CharArrayBuffer(64);
        buf.append(this.mimeType);
        if (this.params != null) {
            buf.append("; ");
            BasicHeaderValueFormatter.INSTANCE.formatParameters(buf, this.params, false);
        } else if (this.charset != null) {
            buf.append(HTTP.CHARSET_PARAM);
            buf.append(this.charset.name());
        }
        return buf.toString();
    }

    private static boolean valid(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '\"' || ch == ',' || ch == ';') {
                return false;
            }
        }
        return true;
    }

    public static ContentType create(String mimeType2, Charset charset2) {
        String type = ((String) Args.notBlank(mimeType2, "MIME type")).toLowerCase(Locale.ENGLISH);
        Args.check(valid(type), "MIME type may not contain reserved characters");
        return new ContentType(type, charset2);
    }

    public static ContentType create(String mimeType2) {
        return new ContentType(mimeType2, (Charset) null);
    }

    public static ContentType create(String mimeType2, String charset2) throws UnsupportedCharsetException {
        return create(mimeType2, !TextUtils.isBlank(charset2) ? Charset.forName(charset2) : null);
    }

    private static ContentType create(HeaderElement helem) {
        String mimeType2 = helem.getName();
        NameValuePair[] params2 = helem.getParameters();
        return new ContentType(mimeType2, (params2 == null || params2.length <= 0) ? null : params2);
    }

    public static ContentType parse(String s) throws ParseException, UnsupportedCharsetException {
        Args.notNull(s, "Content type");
        CharArrayBuffer buf = new CharArrayBuffer(s.length());
        buf.append(s);
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(buf, new ParserCursor(0, s.length()));
        if (elements.length > 0) {
            return create(elements[0]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid content type: ");
        sb.append(s);
        throw new ParseException(sb.toString());
    }

    public static ContentType get(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
        if (entity == null) {
            return null;
        }
        Header header = entity.getContentType();
        if (header != null) {
            HeaderElement[] elements = header.getElements();
            if (elements.length > 0) {
                return create(elements[0]);
            }
        }
        return null;
    }

    public static ContentType getOrDefault(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(entity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public ContentType withCharset(Charset charset2) {
        return create(getMimeType(), charset2);
    }

    public ContentType withCharset(String charset2) {
        return create(getMimeType(), charset2);
    }
}
