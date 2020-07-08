package p008cz.msebera.android.httpclient.impl.client.cache;

import com.navibees.navigatorapp.BuildConfig;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.ResponseCachingPolicy */
class ResponseCachingPolicy {
    private static final String[] AUTH_CACHEABLE_PARAMS = {"s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.PUBLIC};
    private static final Set<Integer> cacheableStatuses = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(200), Integer.valueOf(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION), Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES), Integer.valueOf(HttpStatus.SC_MOVED_PERMANENTLY), Integer.valueOf(HttpStatus.SC_GONE)}));
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final long maxObjectSizeBytes;
    private final boolean neverCache1_0ResponsesWithQueryString;
    private final boolean sharedCache;
    private final Set<Integer> uncacheableStatuses;

    public ResponseCachingPolicy(long maxObjectSizeBytes2, boolean sharedCache2, boolean neverCache1_0ResponsesWithQueryString2, boolean allow303Caching) {
        this.maxObjectSizeBytes = maxObjectSizeBytes2;
        this.sharedCache = sharedCache2;
        this.neverCache1_0ResponsesWithQueryString = neverCache1_0ResponsesWithQueryString2;
        Integer valueOf = Integer.valueOf(HttpStatus.SC_PARTIAL_CONTENT);
        if (allow303Caching) {
            this.uncacheableStatuses = new HashSet(Arrays.asList(new Integer[]{valueOf}));
            return;
        }
        this.uncacheableStatuses = new HashSet(Arrays.asList(new Integer[]{valueOf, Integer.valueOf(HttpStatus.SC_SEE_OTHER)}));
    }

    public boolean isResponseCacheable(String httpMethod, HttpResponse response) {
        HttpResponse httpResponse = response;
        boolean cacheable = false;
        if (!"GET".equals(httpMethod)) {
            this.log.debug("Response was not cacheable.");
            return false;
        }
        int status = response.getStatusLine().getStatusCode();
        if (cacheableStatuses.contains(Integer.valueOf(status))) {
            cacheable = true;
        } else if (this.uncacheableStatuses.contains(Integer.valueOf(status)) || unknownStatusCode(status)) {
            return false;
        }
        Header contentLength = httpResponse.getFirstHeader("Content-Length");
        if ((contentLength != null && ((long) Integer.parseInt(contentLength.getValue())) > this.maxObjectSizeBytes) || httpResponse.getHeaders("Age").length > 1 || httpResponse.getHeaders("Expires").length > 1) {
            return false;
        }
        Header[] dateHeaders = httpResponse.getHeaders("Date");
        if (dateHeaders.length != 1 || DateUtils.parseDate(dateHeaders[0].getValue()) == null) {
            return false;
        }
        Header[] headers = httpResponse.getHeaders("Vary");
        int length = headers.length;
        int i = 0;
        while (i < length) {
            HeaderElement[] elements = headers[i].getElements();
            int length2 = elements.length;
            int status2 = status;
            int status3 = 0;
            while (status3 < length2) {
                int i2 = length2;
                if ("*".equals(elements[status3].getName())) {
                    return false;
                }
                status3++;
                String str = httpMethod;
                length2 = i2;
            }
            i++;
            String str2 = httpMethod;
            status = status2;
        }
        if (isExplicitlyNonCacheable(httpResponse) != 0) {
            return false;
        }
        return cacheable || isExplicitlyCacheable(httpResponse);
    }

    private boolean unknownStatusCode(int status) {
        if (status >= 100 && status <= 101) {
            return false;
        }
        if (status >= 200 && status <= 206) {
            return false;
        }
        if (status >= 300 && status <= 307) {
            return false;
        }
        if (status >= 400 && status <= 417) {
            return false;
        }
        if (status < 500 || status > 505) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isExplicitlyNonCacheable(HttpResponse response) {
        for (Header header : response.getHeaders("Cache-Control")) {
            HeaderElement[] elements = header.getElements();
            int length = elements.length;
            int i = 0;
            while (i < length) {
                HeaderElement elem = elements[i];
                if (!HeaderConstants.CACHE_CONTROL_NO_STORE.equals(elem.getName())) {
                    if (!HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(elem.getName())) {
                        if (this.sharedCache) {
                            if (HeaderConstants.PRIVATE.equals(elem.getName())) {
                            }
                        }
                        i++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean hasCacheControlParameterFrom(HttpMessage msg, String[] params) {
        HeaderElement[] elements;
        for (Header header : msg.getHeaders("Cache-Control")) {
            for (HeaderElement elem : header.getElements()) {
                for (String param : params) {
                    if (param.equalsIgnoreCase(elem.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isExplicitlyCacheable(HttpResponse response) {
        if (response.getFirstHeader("Expires") != null) {
            return true;
        }
        return hasCacheControlParameterFrom(response, new String[]{"max-age", "s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE, HeaderConstants.PUBLIC});
    }

    public boolean isResponseCacheable(HttpRequest request, HttpResponse response) {
        if (requestProtocolGreaterThanAccepted(request)) {
            this.log.debug("Response was not cacheable.");
            return false;
        } else if (hasCacheControlParameterFrom(request, new String[]{HeaderConstants.CACHE_CONTROL_NO_STORE})) {
            return false;
        } else {
            if (request.getRequestLine().getUri().contains("?")) {
                if (this.neverCache1_0ResponsesWithQueryString && from1_0Origin(response)) {
                    this.log.debug("Response was not cacheable as it had a query string.");
                    return false;
                } else if (!isExplicitlyCacheable(response)) {
                    this.log.debug("Response was not cacheable as it is missing explicit caching headers.");
                    return false;
                }
            }
            if (expiresHeaderLessOrEqualToDateHeaderAndNoCacheControl(response)) {
                return false;
            }
            if (this.sharedCache) {
                Header[] authNHeaders = request.getHeaders("Authorization");
                if (authNHeaders != null && authNHeaders.length > 0 && !hasCacheControlParameterFrom(response, AUTH_CACHEABLE_PARAMS)) {
                    return false;
                }
            }
            return isResponseCacheable(request.getRequestLine().getMethod(), response);
        }
    }

    private boolean expiresHeaderLessOrEqualToDateHeaderAndNoCacheControl(HttpResponse response) {
        boolean z = false;
        if (response.getFirstHeader("Cache-Control") != null) {
            return false;
        }
        Header expiresHdr = response.getFirstHeader("Expires");
        Header dateHdr = response.getFirstHeader("Date");
        if (expiresHdr == null || dateHdr == null) {
            return false;
        }
        Date expires = DateUtils.parseDate(expiresHdr.getValue());
        Date date = DateUtils.parseDate(dateHdr.getValue());
        if (expires == null || date == null) {
            return false;
        }
        if (expires.equals(date) || expires.before(date)) {
            z = true;
        }
        return z;
    }

    private boolean from1_0Origin(HttpResponse response) {
        Header via = response.getFirstHeader("Via");
        if (via != null) {
            HeaderElement[] elements = via.getElements();
            if (elements.length > 0) {
                String proto = elements[0].toString().split("\\s")[0];
                if (proto.contains("/")) {
                    return proto.equals("HTTP/1.0");
                }
                return proto.equals(BuildConfig.VERSION_NAME);
            }
        }
        return HttpVersion.HTTP_1_0.equals(response.getProtocolVersion());
    }

    private boolean requestProtocolGreaterThanAccepted(HttpRequest req) {
        return req.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) > 0;
    }
}
