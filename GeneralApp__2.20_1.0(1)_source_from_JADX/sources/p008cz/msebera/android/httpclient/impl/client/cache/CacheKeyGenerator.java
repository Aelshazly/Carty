package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.utils.URIUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheKeyGenerator */
class CacheKeyGenerator {
    private static final URI BASE_URI = URI.create("http://example.com/");

    CacheKeyGenerator() {
    }

    public String getURI(HttpHost host, HttpRequest req) {
        if (!isRelativeRequest(req)) {
            return canonicalizeUri(req.getRequestLine().getUri());
        }
        return canonicalizeUri(String.format("%s%s", new Object[]{host.toString(), req.getRequestLine().getUri()}));
    }

    public String canonicalizeUri(String uri) {
        String file;
        try {
            URL u = new URL(URIUtils.resolve(BASE_URI, uri).toASCIIString());
            String protocol = u.getProtocol();
            String hostname = u.getHost();
            int port = canonicalizePort(u.getPort(), protocol);
            String path = u.getPath();
            String query = u.getQuery();
            if (query != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(path);
                sb.append("?");
                sb.append(query);
                file = sb.toString();
            } else {
                file = path;
            }
            return new URL(protocol, hostname, port, file).toString();
        } catch (IllegalArgumentException e) {
            return uri;
        } catch (MalformedURLException e2) {
            return uri;
        }
    }

    private int canonicalizePort(int port, String protocol) {
        if (port == -1 && HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(protocol)) {
            return 80;
        }
        if (port != -1 || !"https".equalsIgnoreCase(protocol)) {
            return port;
        }
        return 443;
    }

    private boolean isRelativeRequest(HttpRequest req) {
        String requestUri = req.getRequestLine().getUri();
        return "*".equals(requestUri) || requestUri.startsWith("/");
    }

    /* access modifiers changed from: protected */
    public String getFullHeaderValue(Header[] headers) {
        String str = "";
        if (headers == null) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str);
        boolean first = true;
        for (Header hdr : headers) {
            if (!first) {
                buf.append(", ");
            }
            buf.append(hdr.getValue().trim());
            first = false;
        }
        return buf.toString();
    }

    public String getVariantURI(HttpHost host, HttpRequest req, HttpCacheEntry entry) {
        if (!entry.hasVariants()) {
            return getURI(host, req);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getVariantKey(req, entry));
        sb.append(getURI(host, req));
        return sb.toString();
    }

    public String getVariantKey(HttpRequest req, HttpCacheEntry entry) {
        List<String> variantHeaderNames = new ArrayList<>();
        for (Header varyHdr : entry.getHeaders("Vary")) {
            for (HeaderElement elt : varyHdr.getElements()) {
                variantHeaderNames.add(elt.getName());
            }
        }
        Collections.sort(variantHeaderNames);
        try {
            StringBuilder buf = new StringBuilder("{");
            boolean first = true;
            for (String headerName : variantHeaderNames) {
                if (!first) {
                    buf.append("&");
                }
                buf.append(URLEncoder.encode(headerName, Consts.UTF_8.name()));
                buf.append("=");
                buf.append(URLEncoder.encode(getFullHeaderValue(req.getHeaders(headerName)), Consts.UTF_8.name()));
                first = false;
            }
            buf.append("}");
            return buf.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("couldn't encode to UTF-8", uee);
        }
    }
}
