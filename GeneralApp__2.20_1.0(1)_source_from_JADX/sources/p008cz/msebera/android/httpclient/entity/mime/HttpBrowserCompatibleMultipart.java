package p008cz.msebera.android.httpclient.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/* renamed from: cz.msebera.android.httpclient.entity.mime.HttpBrowserCompatibleMultipart */
class HttpBrowserCompatibleMultipart extends AbstractMultipartForm {
    private final List<FormBodyPart> parts;

    public HttpBrowserCompatibleMultipart(String subType, Charset charset, String boundary, List<FormBodyPart> parts2) {
        super(subType, charset, boundary);
        this.parts = parts2;
    }

    public List<FormBodyPart> getBodyParts() {
        return this.parts;
    }

    /* access modifiers changed from: protected */
    public void formatMultipartHeader(FormBodyPart part, OutputStream out) throws IOException {
        Header header = part.getHeader();
        writeField(header.getField("Content-Disposition"), this.charset, out);
        if (part.getBody().getFilename() != null) {
            writeField(header.getField("Content-Type"), this.charset, out);
        }
    }
}
