package p008cz.msebera.android.httpclient.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/* renamed from: cz.msebera.android.httpclient.entity.mime.HttpStrictMultipart */
class HttpStrictMultipart extends AbstractMultipartForm {
    private final List<FormBodyPart> parts;

    public HttpStrictMultipart(String subType, Charset charset, String boundary, List<FormBodyPart> parts2) {
        super(subType, charset, boundary);
        this.parts = parts2;
    }

    public List<FormBodyPart> getBodyParts() {
        return this.parts;
    }

    /* access modifiers changed from: protected */
    public void formatMultipartHeader(FormBodyPart part, OutputStream out) throws IOException {
        Iterator it = part.getHeader().iterator();
        while (it.hasNext()) {
            writeField((MinimalField) it.next(), out);
        }
    }
}
