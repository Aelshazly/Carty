package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.Date;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CachedResponseSuitabilityChecker */
class CachedResponseSuitabilityChecker {
    private final float heuristicCoefficient;
    private final long heuristicDefaultLifetime;
    public HttpClientAndroidLog log;
    private final boolean sharedCache;
    private final boolean useHeuristicCaching;
    private final CacheValidityPolicy validityStrategy;

    CachedResponseSuitabilityChecker(CacheValidityPolicy validityStrategy2, CacheConfig config) {
        this.log = new HttpClientAndroidLog(getClass());
        this.validityStrategy = validityStrategy2;
        this.sharedCache = config.isSharedCache();
        this.useHeuristicCaching = config.isHeuristicCachingEnabled();
        this.heuristicCoefficient = config.getHeuristicCoefficient();
        this.heuristicDefaultLifetime = config.getHeuristicDefaultLifetime();
    }

    CachedResponseSuitabilityChecker(CacheConfig config) {
        this(new CacheValidityPolicy(), config);
    }

    private boolean isFreshEnough(HttpCacheEntry entry, HttpRequest request, Date now) {
        boolean z = true;
        if (this.validityStrategy.isResponseFresh(entry, now)) {
            return true;
        }
        if (this.useHeuristicCaching) {
            if (this.validityStrategy.isResponseHeuristicallyFresh(entry, now, this.heuristicCoefficient, this.heuristicDefaultLifetime)) {
                return true;
            }
        }
        if (originInsistsOnFreshness(entry)) {
            return false;
        }
        long maxstale = getMaxStale(request);
        if (maxstale == -1) {
            return false;
        }
        if (maxstale <= this.validityStrategy.getStalenessSecs(entry, now)) {
            z = false;
        }
        return z;
    }

    private boolean originInsistsOnFreshness(HttpCacheEntry entry) {
        boolean z = true;
        if (this.validityStrategy.mustRevalidate(entry)) {
            return true;
        }
        if (!this.sharedCache) {
            return false;
        }
        if (!this.validityStrategy.proxyRevalidate(entry) && !this.validityStrategy.hasCacheControlDirective(entry, "s-maxage")) {
            z = false;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        if ("".equals(r7.getValue().trim()) != false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getMaxStale(p008cz.msebera.android.httpclient.HttpRequest r20) {
        /*
            r19 = this;
            r0 = -1
            java.lang.String r2 = "Cache-Control"
            r3 = r20
            cz.msebera.android.httpclient.Header[] r2 = r3.getHeaders(r2)
            int r4 = r2.length
            r5 = 0
            r6 = r0
            r1 = 0
        L_0x000e:
            if (r1 >= r4) goto L_0x006f
            r8 = r2[r1]
            cz.msebera.android.httpclient.HeaderElement[] r9 = r8.getElements()
            int r10 = r9.length
            r11 = r6
            r6 = 0
        L_0x0019:
            if (r6 >= r10) goto L_0x006b
            r7 = r9[r6]
            java.lang.String r0 = r7.getName()
            java.lang.String r13 = "max-stale"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0068
            java.lang.String r0 = r7.getValue()
            r13 = -1
            if (r0 == 0) goto L_0x0041
            java.lang.String r0 = r7.getValue()
            java.lang.String r0 = r0.trim()
            java.lang.String r15 = ""
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x004b
        L_0x0041:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 != 0) goto L_0x004b
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            goto L_0x0068
        L_0x004b:
            java.lang.String r0 = r7.getValue()     // Catch:{ NumberFormatException -> 0x0065 }
            long r15 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0065 }
            r17 = 0
            int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r0 >= 0) goto L_0x005b
            r15 = 0
        L_0x005b:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0063
            int r0 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x0064
        L_0x0063:
            r11 = r15
        L_0x0064:
            goto L_0x0068
        L_0x0065:
            r0 = move-exception
            r11 = 0
        L_0x0068:
            int r6 = r6 + 1
            goto L_0x0019
        L_0x006b:
            int r1 = r1 + 1
            r6 = r11
            goto L_0x000e
        L_0x006f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.cache.CachedResponseSuitabilityChecker.getMaxStale(cz.msebera.android.httpclient.HttpRequest):long");
    }

    public boolean canCachedResponseBeUsed(HttpHost host, HttpRequest request, HttpCacheEntry entry, Date now) {
        int i;
        HeaderElement[] headerElementArr;
        Header ccHdr;
        HttpRequest httpRequest = request;
        HttpCacheEntry httpCacheEntry = entry;
        Date date = now;
        boolean z = false;
        if (!isFreshEnough(httpCacheEntry, httpRequest, date)) {
            this.log.trace("Cache entry was not fresh enough");
            return false;
        } else if (!this.validityStrategy.contentLengthHeaderMatchesActualLength(httpCacheEntry)) {
            this.log.debug("Cache entry Content-Length and header information do not match");
            return false;
        } else if (hasUnsupportedConditionalHeaders(httpRequest)) {
            this.log.debug("Request contained conditional headers we don't handle");
            return false;
        } else if (!isConditional(httpRequest) && entry.getStatusCode() == 304) {
            return false;
        } else {
            if (isConditional(httpRequest) && !allConditionalsMatch(httpRequest, httpCacheEntry, date)) {
                return false;
            }
            Header[] headers = httpRequest.getHeaders("Cache-Control");
            int length = headers.length;
            for (int i2 = 0; i2 < length; i2++) {
                Header ccHdr2 = headers[i2];
                HeaderElement[] elements = ccHdr2.getElements();
                int length2 = elements.length;
                int i3 = 0;
                while (i3 < length2) {
                    HeaderElement elt = elements[i3];
                    if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(elt.getName())) {
                        this.log.trace("Response contained NO CACHE directive, cache was not suitable");
                        return z;
                    }
                    if (HeaderConstants.CACHE_CONTROL_NO_STORE.equals(elt.getName())) {
                        this.log.trace("Response contained NO STORE directive, cache was not suitable");
                        return z;
                    }
                    if ("max-age".equals(elt.getName())) {
                        try {
                            i = length;
                            if (this.validityStrategy.getCurrentAgeSecs(httpCacheEntry, date) > ((long) Integer.parseInt(elt.getValue()))) {
                                this.log.trace("Response from cache was NOT suitable due to max age");
                                return false;
                            }
                        } catch (NumberFormatException ex) {
                            HttpClientAndroidLog httpClientAndroidLog = this.log;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Response from cache was malformed");
                            sb.append(ex.getMessage());
                            httpClientAndroidLog.debug(sb.toString());
                            return false;
                        }
                    } else {
                        i = length;
                    }
                    String str = "Response from cache was malformed: ";
                    if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(elt.getName())) {
                        try {
                            ccHdr = ccHdr2;
                            headerElementArr = elements;
                            if (this.validityStrategy.getFreshnessLifetimeSecs(httpCacheEntry) > ((long) Integer.parseInt(elt.getValue()))) {
                                try {
                                    this.log.trace("Response from cache was not suitable due to Max stale freshness");
                                    return false;
                                } catch (NumberFormatException e) {
                                    ex = e;
                                    HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str);
                                    sb2.append(ex.getMessage());
                                    httpClientAndroidLog2.debug(sb2.toString());
                                    return false;
                                }
                            }
                        } catch (NumberFormatException e2) {
                            ex = e2;
                            Header header = ccHdr2;
                            HttpClientAndroidLog httpClientAndroidLog22 = this.log;
                            StringBuilder sb22 = new StringBuilder();
                            sb22.append(str);
                            sb22.append(ex.getMessage());
                            httpClientAndroidLog22.debug(sb22.toString());
                            return false;
                        }
                    } else {
                        ccHdr = ccHdr2;
                        headerElementArr = elements;
                    }
                    if (HeaderConstants.CACHE_CONTROL_MIN_FRESH.equals(elt.getName())) {
                        try {
                            long minfresh = Long.parseLong(elt.getValue());
                            if (minfresh < 0) {
                                return false;
                            }
                            if (this.validityStrategy.getFreshnessLifetimeSecs(httpCacheEntry) - this.validityStrategy.getCurrentAgeSecs(httpCacheEntry, date) < minfresh) {
                                this.log.trace("Response from cache was not suitable due to min fresh freshness requirement");
                                return false;
                            }
                        } catch (NumberFormatException ex2) {
                            HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str);
                            sb3.append(ex2.getMessage());
                            httpClientAndroidLog3.debug(sb3.toString());
                            return false;
                        }
                    }
                    z = false;
                    i3++;
                    ccHdr2 = ccHdr;
                    length = i;
                    elements = headerElementArr;
                }
                int i4 = length;
                Header header2 = ccHdr2;
            }
            this.log.trace("Response from cache was suitable");
            return true;
        }
    }

    public boolean isConditional(HttpRequest request) {
        return hasSupportedEtagValidator(request) || hasSupportedLastModifiedValidator(request);
    }

    public boolean allConditionalsMatch(HttpRequest request, HttpCacheEntry entry, Date now) {
        boolean hasEtagValidator = hasSupportedEtagValidator(request);
        boolean hasLastModifiedValidator = hasSupportedLastModifiedValidator(request);
        boolean etagValidatorMatches = hasEtagValidator && etagValidatorMatches(request, entry);
        boolean lastModifiedValidatorMatches = hasLastModifiedValidator && lastModifiedValidatorMatches(request, entry, now);
        if (hasEtagValidator && hasLastModifiedValidator && (!etagValidatorMatches || !lastModifiedValidatorMatches)) {
            return false;
        }
        if (!hasEtagValidator || etagValidatorMatches) {
            return !hasLastModifiedValidator || lastModifiedValidatorMatches;
        }
        return false;
    }

    private boolean hasUnsupportedConditionalHeaders(HttpRequest request) {
        return (request.getFirstHeader("If-Range") == null && request.getFirstHeader("If-Match") == null && !hasValidDateField(request, "If-Unmodified-Since")) ? false : true;
    }

    private boolean hasSupportedEtagValidator(HttpRequest request) {
        return request.containsHeader("If-None-Match");
    }

    private boolean hasSupportedLastModifiedValidator(HttpRequest request) {
        return hasValidDateField(request, "If-Modified-Since");
    }

    private boolean etagValidatorMatches(HttpRequest request, HttpCacheEntry entry) {
        Header etagHeader = entry.getFirstHeader("ETag");
        String etag = etagHeader != null ? etagHeader.getValue() : null;
        Header[] ifNoneMatch = request.getHeaders("If-None-Match");
        if (ifNoneMatch != null) {
            for (Header h : ifNoneMatch) {
                for (HeaderElement elt : h.getElements()) {
                    String reqEtag = elt.toString();
                    if (("*".equals(reqEtag) && etag != null) || reqEtag.equals(etag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean lastModifiedValidatorMatches(HttpRequest request, HttpCacheEntry entry, Date now) {
        Header lastModifiedHeader = entry.getFirstHeader("Last-Modified");
        Date lastModified = null;
        if (lastModifiedHeader != null) {
            lastModified = DateUtils.parseDate(lastModifiedHeader.getValue());
        }
        if (lastModified == null) {
            return false;
        }
        for (Header h : request.getHeaders("If-Modified-Since")) {
            Date ifModifiedSince = DateUtils.parseDate(h.getValue());
            if (ifModifiedSince != null && (ifModifiedSince.after(now) || lastModified.after(ifModifiedSince))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasValidDateField(HttpRequest request, String headerName) {
        Header[] headers = request.getHeaders(headerName);
        boolean z = false;
        if (headers.length <= 0) {
            return false;
        }
        if (DateUtils.parseDate(headers[0].getValue()) != null) {
            z = true;
        }
        return z;
    }
}
