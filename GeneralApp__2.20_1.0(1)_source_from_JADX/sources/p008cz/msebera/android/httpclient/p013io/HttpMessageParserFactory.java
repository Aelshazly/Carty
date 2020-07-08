package p008cz.msebera.android.httpclient.p013io;

import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.config.MessageConstraints;

/* renamed from: cz.msebera.android.httpclient.io.HttpMessageParserFactory */
public interface HttpMessageParserFactory<T extends HttpMessage> {
    HttpMessageParser<T> create(SessionInputBuffer sessionInputBuffer, MessageConstraints messageConstraints);
}
