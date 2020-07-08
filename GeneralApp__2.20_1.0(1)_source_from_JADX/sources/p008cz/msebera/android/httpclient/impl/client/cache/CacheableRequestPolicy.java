package p008cz.msebera.android.httpclient.impl.client.cache;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheableRequestPolicy */
class CacheableRequestPolicy {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    CacheableRequestPolicy() {
    }

    public boolean isServableFromCache(HttpRequest request) {
        HeaderElement[] elements;
        String method = request.getRequestLine().getMethod();
        if (HttpVersion.HTTP_1_1.compareToVersion(request.getRequestLine().getProtocolVersion()) != 0) {
            this.log.trace("non-HTTP/1.1 request was not serveable from cache");
            return false;
        } else if (!method.equals("GET")) {
            this.log.trace("non-GET request was not serveable from cache");
            return false;
        } else if (request.getHeaders("Pragma").length > 0) {
            this.log.trace("request with Pragma header was not serveable from cache");
            return false;
        } else {
            for (Header cacheControl : request.getHeaders("Cache-Control")) {
                for (HeaderElement cacheControlElement : cacheControl.getElements()) {
                    if (HeaderConstants.CACHE_CONTROL_NO_STORE.equalsIgnoreCase(cacheControlElement.getName())) {
                        this.log.trace("Request with no-store was not serveable from cache");
                        return false;
                    }
                    if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(cacheControlElement.getName())) {
                        this.log.trace("Request with no-cache was not serveable from cache");
                        return false;
                    }
                }
            }
            this.log.trace("Request was serveable from cache");
            return true;
        }
    }
}
