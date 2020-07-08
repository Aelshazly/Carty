package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.Date;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpHeaders;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.message.BasicHeader;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CachedHttpResponseGenerator */
class CachedHttpResponseGenerator {
    private final CacheValidityPolicy validityStrategy;

    CachedHttpResponseGenerator(CacheValidityPolicy validityStrategy2) {
        this.validityStrategy = validityStrategy2;
    }

    CachedHttpResponseGenerator() {
        this(new CacheValidityPolicy());
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse generateResponse(HttpCacheEntry entry) {
        Date now = new Date();
        HttpResponse response = new BasicHttpResponse((ProtocolVersion) HttpVersion.HTTP_1_1, entry.getStatusCode(), entry.getReasonPhrase());
        response.setHeaders(entry.getAllHeaders());
        if (entry.getResource() != null) {
            HttpEntity entity = new CacheEntity(entry);
            addMissingContentLengthHeader(response, entity);
            response.setEntity(entity);
        }
        long age = this.validityStrategy.getCurrentAgeSecs(entry, now);
        if (age > 0) {
            String str = "Age";
            if (age >= 2147483647L) {
                response.setHeader(str, "2147483648");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append((int) age);
                response.setHeader(str, sb.toString());
            }
        }
        return Proxies.enhanceResponse(response);
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse generateNotModifiedResponse(HttpCacheEntry entry) {
        HttpResponse response = new BasicHttpResponse((ProtocolVersion) HttpVersion.HTTP_1_1, (int) HttpStatus.SC_NOT_MODIFIED, "Not Modified");
        String str = "Date";
        Header dateHeader = entry.getFirstHeader(str);
        if (dateHeader == null) {
            dateHeader = new BasicHeader(str, DateUtils.formatDate(new Date()));
        }
        response.addHeader(dateHeader);
        Header etagHeader = entry.getFirstHeader("ETag");
        if (etagHeader != null) {
            response.addHeader(etagHeader);
        }
        Header contentLocationHeader = entry.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (contentLocationHeader != null) {
            response.addHeader(contentLocationHeader);
        }
        Header expiresHeader = entry.getFirstHeader("Expires");
        if (expiresHeader != null) {
            response.addHeader(expiresHeader);
        }
        Header cacheControlHeader = entry.getFirstHeader("Cache-Control");
        if (cacheControlHeader != null) {
            response.addHeader(cacheControlHeader);
        }
        Header varyHeader = entry.getFirstHeader("Vary");
        if (varyHeader != null) {
            response.addHeader(varyHeader);
        }
        return Proxies.enhanceResponse(response);
    }

    private void addMissingContentLengthHeader(HttpResponse response, HttpEntity entity) {
        if (!transferEncodingIsPresent(response)) {
            String str = "Content-Length";
            if (response.getFirstHeader(str) == null) {
                response.setHeader(new BasicHeader(str, Long.toString(entity.getContentLength())));
            }
        }
    }

    private boolean transferEncodingIsPresent(HttpResponse response) {
        return response.getFirstHeader("Transfer-Encoding") != null;
    }
}
