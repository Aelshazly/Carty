package p008cz.msebera.android.httpclient.message;

import java.util.Locale;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.ReasonPhraseCatalog;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.message.BasicHttpResponse */
public class BasicHttpResponse extends AbstractHttpMessage implements HttpResponse {
    private int code;
    private HttpEntity entity;
    private Locale locale;
    private final ReasonPhraseCatalog reasonCatalog;
    private String reasonPhrase;
    private StatusLine statusline;
    private ProtocolVersion ver;

    public BasicHttpResponse(StatusLine statusline2, ReasonPhraseCatalog catalog, Locale locale2) {
        this.statusline = (StatusLine) Args.notNull(statusline2, "Status line");
        this.ver = statusline2.getProtocolVersion();
        this.code = statusline2.getStatusCode();
        this.reasonPhrase = statusline2.getReasonPhrase();
        this.reasonCatalog = catalog;
        this.locale = locale2;
    }

    public BasicHttpResponse(StatusLine statusline2) {
        this.statusline = (StatusLine) Args.notNull(statusline2, "Status line");
        this.ver = statusline2.getProtocolVersion();
        this.code = statusline2.getStatusCode();
        this.reasonPhrase = statusline2.getReasonPhrase();
        this.reasonCatalog = null;
        this.locale = null;
    }

    public BasicHttpResponse(ProtocolVersion ver2, int code2, String reason) {
        Args.notNegative(code2, "Status code");
        this.statusline = null;
        this.ver = ver2;
        this.code = code2;
        this.reasonPhrase = reason;
        this.reasonCatalog = null;
        this.locale = null;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.ver;
    }

    public StatusLine getStatusLine() {
        if (this.statusline == null) {
            ProtocolVersion protocolVersion = this.ver;
            if (protocolVersion == null) {
                protocolVersion = HttpVersion.HTTP_1_1;
            }
            int i = this.code;
            String str = this.reasonPhrase;
            if (str == null) {
                str = getReason(i);
            }
            this.statusline = new BasicStatusLine(protocolVersion, i, str);
        }
        return this.statusline;
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setStatusLine(StatusLine statusline2) {
        this.statusline = (StatusLine) Args.notNull(statusline2, "Status line");
        this.ver = statusline2.getProtocolVersion();
        this.code = statusline2.getStatusCode();
        this.reasonPhrase = statusline2.getReasonPhrase();
    }

    public void setStatusLine(ProtocolVersion ver2, int code2) {
        Args.notNegative(code2, "Status code");
        this.statusline = null;
        this.ver = ver2;
        this.code = code2;
        this.reasonPhrase = null;
    }

    public void setStatusLine(ProtocolVersion ver2, int code2, String reason) {
        Args.notNegative(code2, "Status code");
        this.statusline = null;
        this.ver = ver2;
        this.code = code2;
        this.reasonPhrase = reason;
    }

    public void setStatusCode(int code2) {
        Args.notNegative(code2, "Status code");
        this.statusline = null;
        this.code = code2;
        this.reasonPhrase = null;
    }

    public void setReasonPhrase(String reason) {
        this.statusline = null;
        this.reasonPhrase = reason;
    }

    public void setEntity(HttpEntity entity2) {
        this.entity = entity2;
    }

    public void setLocale(Locale locale2) {
        this.locale = (Locale) Args.notNull(locale2, "Locale");
        this.statusline = null;
    }

    /* access modifiers changed from: protected */
    public String getReason(int code2) {
        ReasonPhraseCatalog reasonPhraseCatalog = this.reasonCatalog;
        if (reasonPhraseCatalog == null) {
            return null;
        }
        Locale locale2 = this.locale;
        if (locale2 == null) {
            locale2 = Locale.getDefault();
        }
        return reasonPhraseCatalog.getReason(code2, locale2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusLine());
        sb.append(' ');
        sb.append(this.headergroup);
        if (this.entity != null) {
            sb.append(' ');
            sb.append(this.entity);
        }
        return sb.toString();
    }
}
