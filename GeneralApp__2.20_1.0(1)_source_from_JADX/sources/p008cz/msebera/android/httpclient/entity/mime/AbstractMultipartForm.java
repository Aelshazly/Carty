package p008cz.msebera.android.httpclient.entity.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.ByteArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.entity.mime.AbstractMultipartForm */
abstract class AbstractMultipartForm {
    private static final ByteArrayBuffer CR_LF = encode(MIME.DEFAULT_CHARSET, "\r\n");
    private static final ByteArrayBuffer FIELD_SEP = encode(MIME.DEFAULT_CHARSET, ": ");
    private static final ByteArrayBuffer TWO_DASHES = encode(MIME.DEFAULT_CHARSET, "--");
    private final String boundary;
    protected final Charset charset;
    private final String subType;

    /* access modifiers changed from: protected */
    public abstract void formatMultipartHeader(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException;

    public abstract List<FormBodyPart> getBodyParts();

    private static ByteArrayBuffer encode(Charset charset2, String string) {
        ByteBuffer encoded = charset2.encode(CharBuffer.wrap(string));
        ByteArrayBuffer bab = new ByteArrayBuffer(encoded.remaining());
        bab.append(encoded.array(), encoded.position(), encoded.remaining());
        return bab;
    }

    private static void writeBytes(ByteArrayBuffer b, OutputStream out) throws IOException {
        out.write(b.buffer(), 0, b.length());
    }

    private static void writeBytes(String s, Charset charset2, OutputStream out) throws IOException {
        writeBytes(encode(charset2, s), out);
    }

    private static void writeBytes(String s, OutputStream out) throws IOException {
        writeBytes(encode(MIME.DEFAULT_CHARSET, s), out);
    }

    protected static void writeField(MinimalField field, OutputStream out) throws IOException {
        writeBytes(field.getName(), out);
        writeBytes(FIELD_SEP, out);
        writeBytes(field.getBody(), out);
        writeBytes(CR_LF, out);
    }

    protected static void writeField(MinimalField field, Charset charset2, OutputStream out) throws IOException {
        writeBytes(field.getName(), charset2, out);
        writeBytes(FIELD_SEP, out);
        writeBytes(field.getBody(), charset2, out);
        writeBytes(CR_LF, out);
    }

    public AbstractMultipartForm(String subType2, Charset charset2, String boundary2) {
        Args.notNull(subType2, "Multipart subtype");
        Args.notNull(boundary2, "Multipart boundary");
        this.subType = subType2;
        this.charset = charset2 != null ? charset2 : MIME.DEFAULT_CHARSET;
        this.boundary = boundary2;
    }

    public AbstractMultipartForm(String subType2, String boundary2) {
        this(subType2, null, boundary2);
    }

    public String getSubType() {
        return this.subType;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getBoundary() {
        return this.boundary;
    }

    /* access modifiers changed from: 0000 */
    public void doWriteTo(OutputStream out, boolean writeContent) throws IOException {
        ByteArrayBuffer boundary2 = encode(this.charset, getBoundary());
        for (FormBodyPart part : getBodyParts()) {
            writeBytes(TWO_DASHES, out);
            writeBytes(boundary2, out);
            writeBytes(CR_LF, out);
            formatMultipartHeader(part, out);
            writeBytes(CR_LF, out);
            if (writeContent) {
                part.getBody().writeTo(out);
            }
            writeBytes(CR_LF, out);
        }
        writeBytes(TWO_DASHES, out);
        writeBytes(boundary2, out);
        writeBytes(TWO_DASHES, out);
        writeBytes(CR_LF, out);
    }

    public void writeTo(OutputStream out) throws IOException {
        doWriteTo(out, true);
    }

    public long getTotalLength() {
        long contentLen = 0;
        Iterator it = getBodyParts().iterator();
        while (true) {
            long j = -1;
            if (it.hasNext()) {
                long len = ((FormBodyPart) it.next()).getBody().getContentLength();
                if (len < 0) {
                    return j;
                }
                contentLen += len;
            } else {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    doWriteTo(out, false);
                    return ((long) out.toByteArray().length) + contentLen;
                } catch (IOException e) {
                    return j;
                }
            }
        }
    }
}
