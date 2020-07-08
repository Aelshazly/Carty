package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding;
import p008cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpProcessor;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.ContentEncodingHttpClient */
public class ContentEncodingHttpClient extends DefaultHttpClient {
    public ContentEncodingHttpClient(ClientConnectionManager conman, HttpParams params) {
        super(conman, params);
    }

    public ContentEncodingHttpClient(HttpParams params) {
        this(null, params);
    }

    public ContentEncodingHttpClient() {
        this(null);
    }

    /* access modifiers changed from: protected */
    public BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor result = super.createHttpProcessor();
        result.addRequestInterceptor(new RequestAcceptEncoding());
        result.addResponseInterceptor(new ResponseContentEncoding());
        return result;
    }
}
