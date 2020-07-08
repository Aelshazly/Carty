package p008cz.msebera.android.httpclient.entity.mime;

import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.mime.content.AbstractContentBody;
import p008cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.entity.mime.FormBodyPart */
public class FormBodyPart {
    private final ContentBody body;
    private final Header header = new Header();
    private final String name;

    public FormBodyPart(String name2, ContentBody body2) {
        Args.notNull(name2, "Name");
        Args.notNull(body2, "Body");
        this.name = name2;
        this.body = body2;
        generateContentDisp(body2);
        generateContentType(body2);
        generateTransferEncoding(body2);
    }

    public String getName() {
        return this.name;
    }

    public ContentBody getBody() {
        return this.body;
    }

    public Header getHeader() {
        return this.header;
    }

    public void addField(String name2, String value) {
        Args.notNull(name2, "Field name");
        this.header.addField(new MinimalField(name2, value));
    }

    /* access modifiers changed from: protected */
    public void generateContentDisp(ContentBody body2) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("form-data; name=\"");
        buffer.append(getName());
        String str = "\"";
        buffer.append(str);
        if (body2.getFilename() != null) {
            buffer.append("; filename=\"");
            buffer.append(body2.getFilename());
            buffer.append(str);
        }
        addField("Content-Disposition", buffer.toString());
    }

    /* access modifiers changed from: protected */
    public void generateContentType(ContentBody body2) {
        ContentType contentType;
        if (body2 instanceof AbstractContentBody) {
            contentType = ((AbstractContentBody) body2).getContentType();
        } else {
            contentType = null;
        }
        String str = "Content-Type";
        if (contentType != null) {
            addField(str, contentType.toString());
            return;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(body2.getMimeType());
        if (body2.getCharset() != null) {
            buffer.append(HTTP.CHARSET_PARAM);
            buffer.append(body2.getCharset());
        }
        addField(str, buffer.toString());
    }

    /* access modifiers changed from: protected */
    public void generateTransferEncoding(ContentBody body2) {
        addField(MIME.CONTENT_TRANSFER_ENC, body2.getTransferEncoding());
    }
}
