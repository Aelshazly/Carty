package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.client.protocol.RequestAddCookies;
import p008cz.msebera.android.httpclient.client.protocol.RequestAuthCache;
import p008cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import p008cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders;
import p008cz.msebera.android.httpclient.client.protocol.RequestProxyAuthentication;
import p008cz.msebera.android.httpclient.client.protocol.RequestTargetAuthentication;
import p008cz.msebera.android.httpclient.client.protocol.ResponseProcessCookies;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.params.HttpProtocolParams;
import p008cz.msebera.android.httpclient.params.SyncBasicHttpParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpProcessor;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.protocol.RequestContent;
import p008cz.msebera.android.httpclient.protocol.RequestExpectContinue;
import p008cz.msebera.android.httpclient.protocol.RequestTargetHost;
import p008cz.msebera.android.httpclient.protocol.RequestUserAgent;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultHttpClient */
public class DefaultHttpClient extends AbstractHttpClient {
    public DefaultHttpClient(ClientConnectionManager conman, HttpParams params) {
        super(conman, params);
    }

    public DefaultHttpClient(ClientConnectionManager conman) {
        super(conman, null);
    }

    public DefaultHttpClient(HttpParams params) {
        super(null, params);
    }

    public DefaultHttpClient() {
        super(null, null);
    }

    /* access modifiers changed from: protected */
    public HttpParams createHttpParams() {
        HttpParams params = new SyncBasicHttpParams();
        setDefaultHttpParams(params);
        return params;
    }

    public static void setDefaultHttpParams(HttpParams params) {
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEF_CONTENT_CHARSET.name());
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        HttpProtocolParams.setUserAgent(params, HttpClientBuilder.DEFAULT_USER_AGENT);
    }

    /* access modifiers changed from: protected */
    public BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor httpproc = new BasicHttpProcessor();
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestDefaultHeaders());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestContent());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestTargetHost());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestClientConnControl());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestUserAgent());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestExpectContinue());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestAddCookies());
        httpproc.addInterceptor((HttpResponseInterceptor) new ResponseProcessCookies());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestAuthCache());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestTargetAuthentication());
        httpproc.addInterceptor((HttpRequestInterceptor) new RequestProxyAuthentication());
        return httpproc;
    }
}
