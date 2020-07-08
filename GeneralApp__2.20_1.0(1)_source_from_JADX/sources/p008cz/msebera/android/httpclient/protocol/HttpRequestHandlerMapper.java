package p008cz.msebera.android.httpclient.protocol;

import p008cz.msebera.android.httpclient.HttpRequest;

/* renamed from: cz.msebera.android.httpclient.protocol.HttpRequestHandlerMapper */
public interface HttpRequestHandlerMapper {
    HttpRequestHandler lookup(HttpRequest httpRequest);
}
