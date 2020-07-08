package p008cz.msebera.android.httpclient.impl;

import java.util.Locale;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseFactory;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.ReasonPhraseCatalog;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;
import p008cz.msebera.android.httpclient.message.BasicStatusLine;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.DefaultHttpResponseFactory */
public class DefaultHttpResponseFactory implements HttpResponseFactory {
    public static final DefaultHttpResponseFactory INSTANCE = new DefaultHttpResponseFactory();
    protected final ReasonPhraseCatalog reasonCatalog;

    public DefaultHttpResponseFactory(ReasonPhraseCatalog catalog) {
        this.reasonCatalog = (ReasonPhraseCatalog) Args.notNull(catalog, "Reason phrase catalog");
    }

    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.INSTANCE);
    }

    public HttpResponse newHttpResponse(ProtocolVersion ver, int status, HttpContext context) {
        Args.notNull(ver, "HTTP version");
        Locale loc = determineLocale(context);
        return new BasicHttpResponse(new BasicStatusLine(ver, status, this.reasonCatalog.getReason(status, loc)), this.reasonCatalog, loc);
    }

    public HttpResponse newHttpResponse(StatusLine statusline, HttpContext context) {
        Args.notNull(statusline, "Status line");
        return new BasicHttpResponse(statusline, this.reasonCatalog, determineLocale(context));
    }

    /* access modifiers changed from: protected */
    public Locale determineLocale(HttpContext context) {
        return Locale.getDefault();
    }
}
