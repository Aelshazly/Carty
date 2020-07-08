package p008cz.msebera.android.httpclient.entity.mime;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.content.ByteArrayBody;
import p008cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import p008cz.msebera.android.httpclient.entity.mime.content.FileBody;
import p008cz.msebera.android.httpclient.entity.mime.content.InputStreamBody;
import p008cz.msebera.android.httpclient.entity.mime.content.StringBody;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder */
public class MultipartEntityBuilder {
    private static final String DEFAULT_SUBTYPE = "form-data";
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private List<FormBodyPart> bodyParts = null;
    private String boundary = null;
    private Charset charset = null;
    private HttpMultipartMode mode = HttpMultipartMode.STRICT;
    private String subType = DEFAULT_SUBTYPE;

    /* renamed from: cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder$1 */
    static /* synthetic */ class C09721 {

        /* renamed from: $SwitchMap$cz$msebera$android$httpclient$entity$mime$HttpMultipartMode */
        static final /* synthetic */ int[] f127xc867cd5c = new int[HttpMultipartMode.values().length];

        static {
            try {
                f127xc867cd5c[HttpMultipartMode.BROWSER_COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f127xc867cd5c[HttpMultipartMode.RFC6532.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static MultipartEntityBuilder create() {
        return new MultipartEntityBuilder();
    }

    MultipartEntityBuilder() {
    }

    public MultipartEntityBuilder setMode(HttpMultipartMode mode2) {
        this.mode = mode2;
        return this;
    }

    public MultipartEntityBuilder setLaxMode() {
        this.mode = HttpMultipartMode.BROWSER_COMPATIBLE;
        return this;
    }

    public MultipartEntityBuilder setStrictMode() {
        this.mode = HttpMultipartMode.STRICT;
        return this;
    }

    public MultipartEntityBuilder setBoundary(String boundary2) {
        this.boundary = boundary2;
        return this;
    }

    public MultipartEntityBuilder setCharset(Charset charset2) {
        this.charset = charset2;
        return this;
    }

    /* access modifiers changed from: 0000 */
    public MultipartEntityBuilder addPart(FormBodyPart bodyPart) {
        if (bodyPart == null) {
            return this;
        }
        if (this.bodyParts == null) {
            this.bodyParts = new ArrayList();
        }
        this.bodyParts.add(bodyPart);
        return this;
    }

    public MultipartEntityBuilder addPart(String name, ContentBody contentBody) {
        Args.notNull(name, "Name");
        Args.notNull(contentBody, "Content body");
        return addPart(new FormBodyPart(name, contentBody));
    }

    public MultipartEntityBuilder addTextBody(String name, String text, ContentType contentType) {
        return addPart(name, new StringBody(text, contentType));
    }

    public MultipartEntityBuilder addTextBody(String name, String text) {
        return addTextBody(name, text, ContentType.DEFAULT_TEXT);
    }

    public MultipartEntityBuilder addBinaryBody(String name, byte[] b, ContentType contentType, String filename) {
        return addPart(name, new ByteArrayBody(b, contentType, filename));
    }

    public MultipartEntityBuilder addBinaryBody(String name, byte[] b) {
        return addBinaryBody(name, b, ContentType.DEFAULT_BINARY, (String) null);
    }

    public MultipartEntityBuilder addBinaryBody(String name, File file, ContentType contentType, String filename) {
        return addPart(name, new FileBody(file, contentType, filename));
    }

    public MultipartEntityBuilder addBinaryBody(String name, File file) {
        return addBinaryBody(name, file, ContentType.DEFAULT_BINARY, file != null ? file.getName() : null);
    }

    public MultipartEntityBuilder addBinaryBody(String name, InputStream stream, ContentType contentType, String filename) {
        return addPart(name, new InputStreamBody(stream, contentType, filename));
    }

    public MultipartEntityBuilder addBinaryBody(String name, InputStream stream) {
        return addBinaryBody(name, stream, ContentType.DEFAULT_BINARY, (String) null);
    }

    private String generateContentType(String boundary2, Charset charset2) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("multipart/form-data; boundary=");
        buffer.append(boundary2);
        if (charset2 != null) {
            buffer.append(HTTP.CHARSET_PARAM);
            buffer.append(charset2.name());
        }
        return buffer.toString();
    }

    private String generateBoundary() {
        StringBuilder buffer = new StringBuilder();
        Random rand = new Random();
        int count = rand.nextInt(11) + 30;
        for (int i = 0; i < count; i++) {
            char[] cArr = MULTIPART_CHARS;
            buffer.append(cArr[rand.nextInt(cArr.length)]);
        }
        return buffer.toString();
    }

    /* access modifiers changed from: 0000 */
    public MultipartFormEntity buildEntity() {
        List list;
        AbstractMultipartForm form;
        String st = this.subType;
        if (st == null) {
            st = DEFAULT_SUBTYPE;
        }
        Charset cs = this.charset;
        String b = this.boundary;
        if (b == null) {
            b = generateBoundary();
        }
        List<FormBodyPart> list2 = this.bodyParts;
        if (list2 != null) {
            list = new ArrayList(list2);
        } else {
            list = Collections.emptyList();
        }
        List list3 = list;
        HttpMultipartMode m = this.mode;
        if (m == null) {
            m = HttpMultipartMode.STRICT;
        }
        int i = C09721.f127xc867cd5c[m.ordinal()];
        if (i == 1) {
            form = new HttpBrowserCompatibleMultipart(st, cs, b, list3);
        } else if (i != 2) {
            form = new HttpStrictMultipart(st, cs, b, list3);
        } else {
            form = new HttpRFC6532Multipart(st, cs, b, list3);
        }
        return new MultipartFormEntity(form, generateContentType(b, cs), form.getTotalLength());
    }

    public HttpEntity build() {
        return buildEntity();
    }
}
