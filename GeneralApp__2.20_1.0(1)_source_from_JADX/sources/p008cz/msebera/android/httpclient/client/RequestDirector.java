package p008cz.msebera.android.httpclient.client;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.client.RequestDirector */
public interface RequestDirector {
    HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException;
}
