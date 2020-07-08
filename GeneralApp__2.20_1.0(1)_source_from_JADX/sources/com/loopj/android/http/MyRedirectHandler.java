package com.loopj.android.http;

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
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;
import p008cz.msebera.android.httpclient.impl.client.DefaultRedirectHandler;
import p008cz.msebera.android.httpclient.impl.client.RedirectLocations;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

class MyRedirectHandler extends DefaultRedirectHandler {
    private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private final boolean enableRedirects;

    public MyRedirectHandler(boolean allowRedirects) {
        this.enableRedirects = allowRedirects;
    }

    public boolean isRedirectRequested(HttpResponse response, HttpContext context) {
        if (!this.enableRedirects) {
            return false;
        }
        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 307) {
                switch (statusCode) {
                    case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
                    case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                    case HttpStatus.SC_SEE_OTHER /*303*/:
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }
        throw new IllegalArgumentException("HTTP response may not be null");
    }

    public URI getLocationURI(HttpResponse response, HttpContext context) throws ProtocolException {
        URI redirectURI;
        if (response != null) {
            Header locationHeader = response.getFirstHeader(Param.LOCATION);
            if (locationHeader != null) {
                String location = locationHeader.getValue().replaceAll(" ", "%20");
                try {
                    URI uri = new URI(location);
                    HttpParams params = response.getParams();
                    if (!uri.isAbsolute()) {
                        if (!params.isParameterTrue(ClientPNames.REJECT_RELATIVE_REDIRECT)) {
                            HttpHost target = (HttpHost) context.getAttribute("http.target_host");
                            if (target != null) {
                                try {
                                    uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest) context.getAttribute("http.request")).getRequestLine().getUri()), target, true), uri);
                                } catch (URISyntaxException ex) {
                                    throw new ProtocolException(ex.getMessage(), ex);
                                }
                            } else {
                                throw new IllegalStateException("Target host not available in the HTTP context");
                            }
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Relative redirect location '");
                            sb.append(uri);
                            sb.append("' not allowed");
                            throw new ProtocolException(sb.toString());
                        }
                    }
                    if (params.isParameterFalse(ClientPNames.ALLOW_CIRCULAR_REDIRECTS)) {
                        String str = "http.protocol.redirect-locations";
                        RedirectLocations redirectLocations = (RedirectLocations) context.getAttribute(str);
                        if (redirectLocations == null) {
                            redirectLocations = new RedirectLocations();
                            context.setAttribute(str, redirectLocations);
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
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Circular redirect to '");
                            sb2.append(redirectURI);
                            sb2.append("'");
                            throw new CircularRedirectException(sb2.toString());
                        }
                    }
                    return uri;
                } catch (URISyntaxException ex3) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Invalid redirect URI: ");
                    sb3.append(location);
                    throw new ProtocolException(sb3.toString(), ex3);
                }
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Received redirect response ");
                sb4.append(response.getStatusLine());
                sb4.append(" but no location header");
                throw new ProtocolException(sb4.toString());
            }
        } else {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
    }
}
