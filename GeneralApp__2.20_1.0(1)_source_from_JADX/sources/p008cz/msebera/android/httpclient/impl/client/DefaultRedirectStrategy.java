package p008cz.msebera.android.httpclient.impl.client;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.client.CircularRedirectException;
import p008cz.msebera.android.httpclient.client.RedirectStrategy;
import p008cz.msebera.android.httpclient.client.config.RequestConfig;
import p008cz.msebera.android.httpclient.client.methods.HttpGet;
import p008cz.msebera.android.httpclient.client.methods.HttpHead;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.methods.RequestBuilder;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.utils.URIBuilder;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultRedirectStrategy */
public class DefaultRedirectStrategy implements RedirectStrategy {
    public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
    @Deprecated
    public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private static final String[] REDIRECT_METHODS = {"GET", "HEAD"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull(request, "HTTP request");
        Args.notNull(response, "HTTP response");
        int statusCode = response.getStatusLine().getStatusCode();
        String method = request.getRequestLine().getMethod();
        Header locationHeader = response.getFirstHeader(Param.LOCATION);
        if (statusCode != 307) {
            boolean z = true;
            switch (statusCode) {
                case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
                    break;
                case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                    if (!isRedirectable(method) || locationHeader == null) {
                        z = false;
                    }
                    return z;
                case HttpStatus.SC_SEE_OTHER /*303*/:
                    return true;
                default:
                    return false;
            }
        }
        return isRedirectable(method);
    }

    public URI getLocationURI(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull(request, "HTTP request");
        Args.notNull(response, "HTTP response");
        Args.notNull(context, "HTTP context");
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        Header locationHeader = response.getFirstHeader(Param.LOCATION);
        if (locationHeader != null) {
            String location = locationHeader.getValue();
            String str = "'";
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Redirect requested to location '");
                sb.append(location);
                sb.append(str);
                httpClientAndroidLog.debug(sb.toString());
            }
            RequestConfig config = clientContext.getRequestConfig();
            URI uri = createLocationURI(location);
            try {
                if (!uri.isAbsolute()) {
                    if (config.isRelativeRedirectsAllowed()) {
                        HttpHost target = clientContext.getTargetHost();
                        Asserts.notNull(target, "Target host");
                        uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(request.getRequestLine().getUri()), target, false), uri);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Relative redirect location '");
                        sb2.append(uri);
                        sb2.append("' not allowed");
                        throw new ProtocolException(sb2.toString());
                    }
                }
                String str2 = "http.protocol.redirect-locations";
                RedirectLocations redirectLocations = (RedirectLocations) clientContext.getAttribute(str2);
                if (redirectLocations == null) {
                    redirectLocations = new RedirectLocations();
                    context.setAttribute(str2, redirectLocations);
                }
                if (config.isCircularRedirectsAllowed() || !redirectLocations.contains(uri)) {
                    redirectLocations.add(uri);
                    return uri;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Circular redirect to '");
                sb3.append(uri);
                sb3.append(str);
                throw new CircularRedirectException(sb3.toString());
            } catch (URISyntaxException ex) {
                throw new ProtocolException(ex.getMessage(), ex);
            }
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Received redirect response ");
            sb4.append(response.getStatusLine());
            sb4.append(" but no location header");
            throw new ProtocolException(sb4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public URI createLocationURI(String location) throws ProtocolException {
        try {
            URIBuilder b = new URIBuilder(new URI(location).normalize());
            String host = b.getHost();
            if (host != null) {
                b.setHost(host.toLowerCase(Locale.ENGLISH));
            }
            if (TextUtils.isEmpty(b.getPath())) {
                b.setPath("/");
            }
            return b.build();
        } catch (URISyntaxException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid redirect URI: ");
            sb.append(location);
            throw new ProtocolException(sb.toString(), ex);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isRedirectable(String method) {
        for (String m : REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        URI uri = getLocationURI(request, response, context);
        String method = request.getRequestLine().getMethod();
        if (method.equalsIgnoreCase("HEAD")) {
            return new HttpHead(uri);
        }
        if (method.equalsIgnoreCase("GET")) {
            return new HttpGet(uri);
        }
        if (response.getStatusLine().getStatusCode() == 307) {
            return RequestBuilder.copy(request).setUri(uri).build();
        }
        return new HttpGet(uri);
    }
}
