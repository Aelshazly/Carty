package p008cz.msebera.android.httpclient.impl.client;

import java.net.URI;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.client.RedirectHandler;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.methods.HttpGet;
import p008cz.msebera.android.httpclient.client.methods.HttpHead;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultRedirectStrategyAdaptor */
class DefaultRedirectStrategyAdaptor implements RedirectStrategy {
    private final RedirectHandler handler;

    public DefaultRedirectStrategyAdaptor(RedirectHandler handler2) {
        this.handler = handler2;
    }

    public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        return this.handler.isRedirectRequested(response, context);
    }

    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        URI uri = this.handler.getLocationURI(response, context);
        if (request.getRequestLine().getMethod().equalsIgnoreCase("HEAD")) {
            return new HttpHead(uri);
        }
        return new HttpGet(uri);
    }

    public RedirectHandler getHandler() {
        return this.handler;
    }
}
