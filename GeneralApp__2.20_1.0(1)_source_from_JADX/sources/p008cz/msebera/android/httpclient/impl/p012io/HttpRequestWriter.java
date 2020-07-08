package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.message.LineFormatter;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.io.HttpRequestWriter */
public class HttpRequestWriter extends AbstractMessageWriter<HttpRequest> {
    public HttpRequestWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
        super(buffer, formatter, params);
    }

    /* access modifiers changed from: protected */
    public void writeHeadLine(HttpRequest message) throws IOException {
        this.lineFormatter.formatRequestLine(this.lineBuf, message.getRequestLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
