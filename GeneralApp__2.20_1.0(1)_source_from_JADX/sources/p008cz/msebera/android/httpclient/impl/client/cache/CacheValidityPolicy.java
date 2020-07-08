package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.Date;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheValidityPolicy */
class CacheValidityPolicy {
    public static final long MAX_AGE = 2147483648L;

    CacheValidityPolicy() {
    }

    public long getCurrentAgeSecs(HttpCacheEntry entry, Date now) {
        return getCorrectedInitialAgeSecs(entry) + getResidentTimeSecs(entry, now);
    }

    public long getFreshnessLifetimeSecs(HttpCacheEntry entry) {
        long maxage = getMaxAge(entry);
        if (maxage > -1) {
            return maxage;
        }
        Date dateValue = entry.getDate();
        if (dateValue == null) {
            return 0;
        }
        Date expiry = getExpirationDate(entry);
        if (expiry == null) {
            return 0;
        }
        return (expiry.getTime() - dateValue.getTime()) / 1000;
    }

    public boolean isResponseFresh(HttpCacheEntry entry, Date now) {
        return getCurrentAgeSecs(entry, now) < getFreshnessLifetimeSecs(entry);
    }

    public boolean isResponseHeuristicallyFresh(HttpCacheEntry entry, Date now, float coefficient, long defaultLifetime) {
        return getCurrentAgeSecs(entry, now) < getHeuristicFreshnessLifetimeSecs(entry, coefficient, defaultLifetime);
    }

    public long getHeuristicFreshnessLifetimeSecs(HttpCacheEntry entry, float coefficient, long defaultLifetime) {
        Date dateValue = entry.getDate();
        Date lastModifiedValue = getLastModifiedValue(entry);
        if (dateValue == null || lastModifiedValue == null) {
            return defaultLifetime;
        }
        long diff = dateValue.getTime() - lastModifiedValue.getTime();
        if (diff < 0) {
            return 0;
        }
        return (long) (((float) (diff / 1000)) * coefficient);
    }

    public boolean isRevalidatable(HttpCacheEntry entry) {
        return (entry.getFirstHeader("ETag") == null && entry.getFirstHeader("Last-Modified") == null) ? false : true;
    }

    public boolean mustRevalidate(HttpCacheEntry entry) {
        return hasCacheControlDirective(entry, HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE);
    }

    public boolean proxyRevalidate(HttpCacheEntry entry) {
        return hasCacheControlDirective(entry, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE);
    }

    public boolean mayReturnStaleWhileRevalidating(HttpCacheEntry entry, Date now) {
        HeaderElement[] elements;
        for (Header h : entry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.STALE_WHILE_REVALIDATE.equalsIgnoreCase(elt.getName())) {
                    try {
                        if (getStalenessSecs(entry, now) <= ((long) Integer.parseInt(elt.getValue()))) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return false;
    }

    public boolean mayReturnStaleIfError(HttpRequest request, HttpCacheEntry entry, Date now) {
        long stalenessSecs = getStalenessSecs(entry, now);
        String str = "Cache-Control";
        return mayReturnStaleIfError(request.getHeaders(str), stalenessSecs) || mayReturnStaleIfError(entry.getHeaders(str), stalenessSecs);
    }

    private boolean mayReturnStaleIfError(Header[] headers, long stalenessSecs) {
        boolean result = false;
        for (Header h : headers) {
            HeaderElement[] elements = h.getElements();
            int length = elements.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                HeaderElement elt = elements[i];
                if (HeaderConstants.STALE_IF_ERROR.equals(elt.getName())) {
                    try {
                        if (stalenessSecs <= ((long) Integer.parseInt(elt.getValue()))) {
                            result = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                i++;
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Date getDateValue(HttpCacheEntry entry) {
        return entry.getDate();
    }

    /* access modifiers changed from: protected */
    public Date getLastModifiedValue(HttpCacheEntry entry) {
        Header dateHdr = entry.getFirstHeader("Last-Modified");
        if (dateHdr == null) {
            return null;
        }
        return DateUtils.parseDate(dateHdr.getValue());
    }

    /* access modifiers changed from: protected */
    public long getContentLengthValue(HttpCacheEntry entry) {
        Header cl = entry.getFirstHeader("Content-Length");
        if (cl == null) {
            return -1;
        }
        try {
            return Long.parseLong(cl.getValue());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasContentLengthHeader(HttpCacheEntry entry) {
        return entry.getFirstHeader("Content-Length") != null;
    }

    /* access modifiers changed from: protected */
    public boolean contentLengthHeaderMatchesActualLength(HttpCacheEntry entry) {
        return !hasContentLengthHeader(entry) || getContentLengthValue(entry) == entry.getResource().length();
    }

    /* access modifiers changed from: protected */
    public long getApparentAgeSecs(HttpCacheEntry entry) {
        Date dateValue = entry.getDate();
        if (dateValue == null) {
            return MAX_AGE;
        }
        long diff = entry.getResponseDate().getTime() - dateValue.getTime();
        if (diff < 0) {
            return 0;
        }
        return diff / 1000;
    }

    /* access modifiers changed from: protected */
    public long getAgeValue(HttpCacheEntry entry) {
        long hdrAge;
        long ageValue = 0;
        for (Header hdr : entry.getHeaders("Age")) {
            try {
                hdrAge = Long.parseLong(hdr.getValue());
                if (hdrAge < 0) {
                    hdrAge = MAX_AGE;
                }
            } catch (NumberFormatException e) {
                hdrAge = MAX_AGE;
            }
            ageValue = hdrAge > ageValue ? hdrAge : ageValue;
        }
        return ageValue;
    }

    /* access modifiers changed from: protected */
    public long getCorrectedReceivedAgeSecs(HttpCacheEntry entry) {
        long apparentAge = getApparentAgeSecs(entry);
        long ageValue = getAgeValue(entry);
        return apparentAge > ageValue ? apparentAge : ageValue;
    }

    /* access modifiers changed from: protected */
    public long getResponseDelaySecs(HttpCacheEntry entry) {
        return (entry.getResponseDate().getTime() - entry.getRequestDate().getTime()) / 1000;
    }

    /* access modifiers changed from: protected */
    public long getCorrectedInitialAgeSecs(HttpCacheEntry entry) {
        return getCorrectedReceivedAgeSecs(entry) + getResponseDelaySecs(entry);
    }

    /* access modifiers changed from: protected */
    public long getResidentTimeSecs(HttpCacheEntry entry, Date now) {
        return (now.getTime() - entry.getResponseDate().getTime()) / 1000;
    }

    /* access modifiers changed from: protected */
    public long getMaxAge(HttpCacheEntry entry) {
        Header[] headers = entry.getHeaders("Cache-Control");
        int length = headers.length;
        long maxage = -1;
        int i = 0;
        while (i < length) {
            HeaderElement[] elements = headers[i].getElements();
            int length2 = elements.length;
            long maxage2 = maxage;
            int i2 = 0;
            while (i2 < length2) {
                HeaderElement elt = elements[i2];
                if (!"max-age".equals(elt.getName())) {
                    if (!"s-maxage".equals(elt.getName())) {
                        i2++;
                    }
                }
                try {
                    long currMaxAge = Long.parseLong(elt.getValue());
                    if (maxage2 == -1 || currMaxAge < maxage2) {
                        maxage2 = currMaxAge;
                        i2++;
                    } else {
                        i2++;
                    }
                } catch (NumberFormatException e) {
                    maxage2 = 0;
                }
            }
            i++;
            maxage = maxage2;
        }
        return maxage;
    }

    /* access modifiers changed from: protected */
    public Date getExpirationDate(HttpCacheEntry entry) {
        Header expiresHeader = entry.getFirstHeader("Expires");
        if (expiresHeader == null) {
            return null;
        }
        return DateUtils.parseDate(expiresHeader.getValue());
    }

    public boolean hasCacheControlDirective(HttpCacheEntry entry, String directive) {
        for (Header h : entry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (directive.equalsIgnoreCase(elt.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getStalenessSecs(HttpCacheEntry entry, Date now) {
        long age = getCurrentAgeSecs(entry, now);
        long freshness = getFreshnessLifetimeSecs(entry);
        if (age <= freshness) {
            return 0;
        }
        return age - freshness;
    }
}
