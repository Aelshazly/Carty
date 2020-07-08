package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheInvalidator */
class CacheInvalidator implements HttpCacheInvalidator {
    private final CacheKeyGenerator cacheKeyGenerator;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final HttpCacheStorage storage;

    public CacheInvalidator(CacheKeyGenerator uriExtractor, HttpCacheStorage storage2) {
        this.cacheKeyGenerator = uriExtractor;
        this.storage = storage2;
    }

    public void flushInvalidatedCacheEntries(HttpHost host, HttpRequest req) {
        if (requestShouldNotBeCached(req)) {
            this.log.debug("Request should not be cached");
            String theUri = this.cacheKeyGenerator.getURI(host, req);
            HttpCacheEntry parent = getEntry(theUri);
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("parent entry: ");
            sb.append(parent);
            httpClientAndroidLog.debug(sb.toString());
            if (parent != null) {
                for (String variantURI : parent.getVariantMap().values()) {
                    flushEntry(variantURI);
                }
                flushEntry(theUri);
            }
            URL reqURL = getAbsoluteURL(theUri);
            if (reqURL == null) {
                this.log.error("Couldn't transform request into valid URL");
                return;
            }
            Header clHdr = req.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
            if (clHdr != null) {
                String contentLocation = clHdr.getValue();
                if (!flushAbsoluteUriFromSameHost(reqURL, contentLocation)) {
                    flushRelativeUriFromSameHost(reqURL, contentLocation);
                }
            }
            Header lHdr = req.getFirstHeader(HttpHeaders.LOCATION);
            if (lHdr != null) {
                flushAbsoluteUriFromSameHost(reqURL, lHdr.getValue());
            }
        }
    }

    private void flushEntry(String uri) {
        try {
            this.storage.removeEntry(uri);
        } catch (IOException ioe) {
            this.log.warn("unable to flush cache entry", ioe);
        }
    }

    private HttpCacheEntry getEntry(String theUri) {
        try {
            return this.storage.getEntry(theUri);
        } catch (IOException ioe) {
            this.log.warn("could not retrieve entry from storage", ioe);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void flushUriIfSameHost(URL requestURL, URL targetURL) {
        URL canonicalTarget = getAbsoluteURL(this.cacheKeyGenerator.canonicalizeUri(targetURL.toString()));
        if (canonicalTarget != null && canonicalTarget.getAuthority().equalsIgnoreCase(requestURL.getAuthority())) {
            flushEntry(canonicalTarget.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void flushRelativeUriFromSameHost(URL reqURL, String relUri) {
        URL relURL = getRelativeURL(reqURL, relUri);
        if (relURL != null) {
            flushUriIfSameHost(reqURL, relURL);
        }
    }

    /* access modifiers changed from: protected */
    public boolean flushAbsoluteUriFromSameHost(URL reqURL, String uri) {
        URL absURL = getAbsoluteURL(uri);
        if (absURL == null) {
            return false;
        }
        flushUriIfSameHost(reqURL, absURL);
        return true;
    }

    private URL getAbsoluteURL(String uri) {
        try {
            return new URL(uri);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private URL getRelativeURL(URL reqURL, String relUri) {
        try {
            return new URL(reqURL, relUri);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean requestShouldNotBeCached(HttpRequest req) {
        return notGetOrHeadRequest(req.getRequestLine().getMethod());
    }

    private boolean notGetOrHeadRequest(String method) {
        return !"GET".equals(method) && !"HEAD".equals(method);
    }

    public void flushInvalidatedCacheEntries(HttpHost host, HttpRequest request, HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status <= 299) {
            URL reqURL = getAbsoluteURL(this.cacheKeyGenerator.getURI(host, request));
            if (reqURL != null) {
                URL contentLocation = getContentLocationURL(reqURL, response);
                if (contentLocation != null) {
                    flushLocationCacheEntry(reqURL, response, contentLocation);
                }
                URL location = getLocationURL(reqURL, response);
                if (location != null) {
                    flushLocationCacheEntry(reqURL, response, location);
                }
            }
        }
    }

    private void flushLocationCacheEntry(URL reqURL, HttpResponse response, URL location) {
        HttpCacheEntry entry = getEntry(this.cacheKeyGenerator.canonicalizeUri(location.toString()));
        if (entry != null && !responseDateOlderThanEntryDate(response, entry) && responseAndEntryEtagsDiffer(response, entry)) {
            flushUriIfSameHost(reqURL, location);
        }
    }

    private URL getContentLocationURL(URL reqURL, HttpResponse response) {
        Header clHeader = response.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (clHeader == null) {
            return null;
        }
        String contentLocation = clHeader.getValue();
        URL canonURL = getAbsoluteURL(contentLocation);
        if (canonURL != null) {
            return canonURL;
        }
        return getRelativeURL(reqURL, contentLocation);
    }

    private URL getLocationURL(URL reqURL, HttpResponse response) {
        Header clHeader = response.getFirstHeader(HttpHeaders.LOCATION);
        if (clHeader == null) {
            return null;
        }
        String location = clHeader.getValue();
        URL canonURL = getAbsoluteURL(location);
        if (canonURL != null) {
            return canonURL;
        }
        return getRelativeURL(reqURL, location);
    }

    private boolean responseAndEntryEtagsDiffer(HttpResponse response, HttpCacheEntry entry) {
        String str = "ETag";
        Header entryEtag = entry.getFirstHeader(str);
        Header responseEtag = response.getFirstHeader(str);
        if (entryEtag == null || responseEtag == null) {
            return false;
        }
        return !entryEtag.getValue().equals(responseEtag.getValue());
    }

    private boolean responseDateOlderThanEntryDate(HttpResponse response, HttpCacheEntry entry) {
        String str = "Date";
        Header entryDateHeader = entry.getFirstHeader(str);
        Header responseDateHeader = response.getFirstHeader(str);
        if (entryDateHeader == null || responseDateHeader == null) {
            return false;
        }
        Date entryDate = DateUtils.parseDate(entryDateHeader.getValue());
        Date responseDate = DateUtils.parseDate(responseDateHeader.getValue());
        if (entryDate == null || responseDate == null) {
            return false;
        }
        return responseDate.before(entryDate);
    }
}
