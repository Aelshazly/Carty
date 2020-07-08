package p008cz.msebera.android.httpclient.impl.client;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.net.URI;
import java.net.URISyntaxException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.client.CircularRedirectException;
import p008cz.msebera.android.httpclient.client.RedirectHandler;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.DefaultRedirectHandler */
public class DefaultRedirectHandler implements RedirectHandler {
    private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public boolean isRedirectRequested(HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        int statusCode = response.getStatusLine().getStatusCode();
        boolean z = true;
        if (statusCode != 307) {
            switch (statusCode) {
                case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
                case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                    break;
                case HttpStatus.SC_SEE_OTHER /*303*/:
                    return true;
                default:
                    return false;
            }
        }
        String method = ((HttpRequest) context.getAttribute("http.request")).getRequestLine().getMethod();
        if (!method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("HEAD")) {
            z = false;
        }
        return z;
    }

    public URI getLocationURI(HttpResponse response, HttpContext context) throws ProtocolException {
        URI redirectURI;
        Args.notNull(response, "HTTP response");
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
            try {
                URI uri = new URI(location);
                HttpParams params = response.getParams();
                if (!uri.isAbsolute()) {
                    if (!params.isParameterTrue(ClientPNames.REJECT_RELATIVE_REDIRECT)) {
                        HttpHost target = (HttpHost) context.getAttribute("http.target_host");
                        Asserts.notNull(target, "Target host");
                        try {
                            uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest) context.getAttribute("http.request")).getRequestLine().getUri()), target, true), uri);
                        } catch (URISyntaxException ex) {
                            throw new ProtocolException(ex.getMessage(), ex);
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Relative redirect location '");
                        sb2.append(uri);
                        sb2.append("' not allowed");
                        throw new ProtocolException(sb2.toString());
                    }
                }
                if (params.isParameterFalse(ClientPNames.ALLOW_CIRCULAR_REDIRECTS)) {
                    String str2 = "http.protocol.redirect-locations";
                    RedirectLocations redirectLocations = (RedirectLocations) context.getAttribute(str2);
                    if (redirectLocations == null) {
                        redirectLocations = new RedirectLocations();
                        context.setAttribute(str2, redirectLocations);
                    }
                    if (uri.getFragment() != null) {
                        try {
                            redirectURI = URIUtils.rewriteURI(uri, new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), true);
                        } catch (URISyntaxException ex2) {
                            throw new ProtocolException(ex2.getMessage(), ex2);
                        }
                    } else {
                        redirectURI = uri;
                    }
                    if (!redirectLocations.contains(redirectURI)) {
                        redirectLocations.add(redirectURI);
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Circular redirect to '");
                        sb3.append(redirectURI);
                        sb3.append(str);
                        throw new CircularRedirectException(sb3.toString());
                    }
                }
                return uri;
            } catch (URISyntaxException ex3) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Invalid redirect URI: ");
                sb4.append(location);
                throw new ProtocolException(sb4.toString(), ex3);
            }
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Received redirect response ");
            sb5.append(response.getStatusLine());
            sb5.append(" but no location header");
            throw new ProtocolException(sb5.toString());
        }
    }
}
