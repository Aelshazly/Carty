package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheUpdateException;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.entity.ByteArrayEntity;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.BasicHttpCache */
class BasicHttpCache implements HttpCache {
    private static final Set<String> safeRequestMethods = new HashSet(Arrays.asList(new String[]{"HEAD", "GET", "OPTIONS", "TRACE"}));
    private final CacheEntryUpdater cacheEntryUpdater;
    private final HttpCacheInvalidator cacheInvalidator;
    public HttpClientAndroidLog log;
    private final long maxObjectSizeBytes;
    private final ResourceFactory resourceFactory;
    private final CachedHttpResponseGenerator responseGenerator;
    private final HttpCacheStorage storage;
    /* access modifiers changed from: private */
    public final CacheKeyGenerator uriExtractor;

    public BasicHttpCache(ResourceFactory resourceFactory2, HttpCacheStorage storage2, CacheConfig config, CacheKeyGenerator uriExtractor2, HttpCacheInvalidator cacheInvalidator2) {
        this.log = new HttpClientAndroidLog(getClass());
        this.resourceFactory = resourceFactory2;
        this.uriExtractor = uriExtractor2;
        this.cacheEntryUpdater = new CacheEntryUpdater(resourceFactory2);
        this.maxObjectSizeBytes = config.getMaxObjectSize();
        this.responseGenerator = new CachedHttpResponseGenerator();
        this.storage = storage2;
        this.cacheInvalidator = cacheInvalidator2;
    }

    public BasicHttpCache(ResourceFactory resourceFactory2, HttpCacheStorage storage2, CacheConfig config, CacheKeyGenerator uriExtractor2) {
        this(resourceFactory2, storage2, config, uriExtractor2, new CacheInvalidator(uriExtractor2, storage2));
    }

    public BasicHttpCache(ResourceFactory resourceFactory2, HttpCacheStorage storage2, CacheConfig config) {
        this(resourceFactory2, storage2, config, new CacheKeyGenerator());
    }

    public BasicHttpCache(CacheConfig config) {
        this(new HeapResourceFactory(), new BasicHttpCacheStorage(config), config);
    }

    public BasicHttpCache() {
        this(CacheConfig.DEFAULT);
    }

    public void flushCacheEntriesFor(HttpHost host, HttpRequest request) throws IOException {
        if (!safeRequestMethods.contains(request.getRequestLine().getMethod())) {
            this.storage.removeEntry(this.uriExtractor.getURI(host, request));
        }
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost host, HttpRequest request, HttpResponse response) {
        if (!safeRequestMethods.contains(request.getRequestLine().getMethod())) {
            this.cacheInvalidator.flushInvalidatedCacheEntries(host, request, response);
        }
    }

    /* access modifiers changed from: 0000 */
    public void storeInCache(HttpHost target, HttpRequest request, HttpCacheEntry entry) throws IOException {
        if (entry.hasVariants()) {
            storeVariantEntry(target, request, entry);
        } else {
            storeNonVariantEntry(target, request, entry);
        }
    }

    /* access modifiers changed from: 0000 */
    public void storeNonVariantEntry(HttpHost target, HttpRequest req, HttpCacheEntry entry) throws IOException {
        this.storage.putEntry(this.uriExtractor.getURI(target, req), entry);
    }

    /* access modifiers changed from: 0000 */
    public void storeVariantEntry(HttpHost target, final HttpRequest req, final HttpCacheEntry entry) throws IOException {
        String parentURI = this.uriExtractor.getURI(target, req);
        final String variantURI = this.uriExtractor.getVariantURI(target, req, entry);
        this.storage.putEntry(variantURI, entry);
        try {
            this.storage.updateEntry(parentURI, new HttpCacheUpdateCallback() {
                public HttpCacheEntry update(HttpCacheEntry existing) throws IOException {
                    return BasicHttpCache.this.doGetUpdatedParentEntry(req.getRequestLine().getUri(), existing, entry, BasicHttpCache.this.uriExtractor.getVariantKey(req, entry), variantURI);
                }
            });
        } catch (HttpCacheUpdateException e) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not update key [");
            sb.append(parentURI);
            sb.append("]");
            httpClientAndroidLog.warn(sb.toString(), e);
        }
    }

    public void reuseVariantEntryFor(HttpHost target, HttpRequest req, Variant variant) throws IOException {
        String parentCacheKey = this.uriExtractor.getURI(target, req);
        HttpCacheEntry entry = variant.getEntry();
        final HttpRequest httpRequest = req;
        final HttpCacheEntry httpCacheEntry = entry;
        final String variantKey = this.uriExtractor.getVariantKey(req, entry);
        final String cacheKey = variant.getCacheKey();
        C12842 r1 = new HttpCacheUpdateCallback() {
            public HttpCacheEntry update(HttpCacheEntry existing) throws IOException {
                return BasicHttpCache.this.doGetUpdatedParentEntry(httpRequest.getRequestLine().getUri(), existing, httpCacheEntry, variantKey, cacheKey);
            }
        };
        try {
            this.storage.updateEntry(parentCacheKey, r1);
        } catch (HttpCacheUpdateException e) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not update key [");
            sb.append(parentCacheKey);
            sb.append("]");
            httpClientAndroidLog.warn(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isIncompleteResponse(HttpResponse resp, Resource resource) {
        int status = resp.getStatusLine().getStatusCode();
        boolean z = false;
        if (status != 200 && status != 206) {
            return false;
        }
        Header hdr = resp.getFirstHeader("Content-Length");
        if (hdr == null) {
            return false;
        }
        try {
            if (resource.length() < ((long) Integer.parseInt(hdr.getValue()))) {
                z = true;
            }
            return z;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse generateIncompleteResponseError(HttpResponse response, Resource resource) {
        String str = "Content-Length";
        int contentLength = Integer.parseInt(response.getFirstHeader(str).getValue());
        HttpResponse error = new BasicHttpResponse((ProtocolVersion) HttpVersion.HTTP_1_1, (int) HttpStatus.SC_BAD_GATEWAY, "Bad Gateway");
        error.setHeader("Content-Type", "text/plain;charset=UTF-8");
        byte[] msgBytes = String.format("Received incomplete response with Content-Length %d but actual body length %d", new Object[]{Integer.valueOf(contentLength), Long.valueOf(resource.length())}).getBytes();
        error.setHeader(str, Integer.toString(msgBytes.length));
        error.setEntity(new ByteArrayEntity(msgBytes));
        return Proxies.enhanceResponse(error);
    }

    /* access modifiers changed from: 0000 */
    public HttpCacheEntry doGetUpdatedParentEntry(String requestId, HttpCacheEntry existing, HttpCacheEntry entry, String variantKey, String variantCacheKey) throws IOException {
        HttpCacheEntry src = existing;
        if (src == null) {
            src = entry;
        }
        Resource resource = null;
        if (src.getResource() != null) {
            resource = this.resourceFactory.copy(requestId, src.getResource());
        } else {
            String str = requestId;
        }
        HashMap hashMap = new HashMap(src.getVariantMap());
        hashMap.put(variantKey, variantCacheKey);
        HttpCacheEntry httpCacheEntry = new HttpCacheEntry(src.getRequestDate(), src.getResponseDate(), src.getStatusLine(), src.getAllHeaders(), resource, hashMap);
        return httpCacheEntry;
    }

    public HttpCacheEntry updateCacheEntry(HttpHost target, HttpRequest request, HttpCacheEntry stale, HttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        HttpCacheEntry updatedEntry = this.cacheEntryUpdater.updateCacheEntry(request.getRequestLine().getUri(), stale, requestSent, responseReceived, originResponse);
        storeInCache(target, request, updatedEntry);
        return updatedEntry;
    }

    public HttpCacheEntry updateVariantCacheEntry(HttpHost target, HttpRequest request, HttpCacheEntry stale, HttpResponse originResponse, Date requestSent, Date responseReceived, String cacheKey) throws IOException {
        HttpCacheEntry updatedEntry = this.cacheEntryUpdater.updateCacheEntry(request.getRequestLine().getUri(), stale, requestSent, responseReceived, originResponse);
        this.storage.putEntry(cacheKey, updatedEntry);
        return updatedEntry;
    }

    public HttpResponse cacheAndReturnResponse(HttpHost host, HttpRequest request, HttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        return cacheAndReturnResponse(host, request, Proxies.enhanceResponse(originResponse), requestSent, responseReceived);
    }

    /* JADX INFO: finally extract failed */
    public CloseableHttpResponse cacheAndReturnResponse(HttpHost host, HttpRequest request, CloseableHttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        SizeLimitedResponseReader responseReader = getResponseReader(request, originResponse);
        try {
            responseReader.readResponse();
            if (responseReader.isLimitReached()) {
                CloseableHttpResponse reconstructedResponse = responseReader.getReconstructedResponse();
                if (0 != 0) {
                    originResponse.close();
                }
                return reconstructedResponse;
            }
            Resource resource = responseReader.getResource();
            if (isIncompleteResponse(originResponse, resource)) {
                CloseableHttpResponse generateIncompleteResponseError = generateIncompleteResponseError(originResponse, resource);
                if (1 != 0) {
                    originResponse.close();
                }
                return generateIncompleteResponseError;
            }
            HttpCacheEntry httpCacheEntry = new HttpCacheEntry(requestSent, responseReceived, originResponse.getStatusLine(), originResponse.getAllHeaders(), resource);
            HttpCacheEntry entry = httpCacheEntry;
            storeInCache(host, request, entry);
            CloseableHttpResponse generateResponse = this.responseGenerator.generateResponse(entry);
            if (1 != 0) {
                originResponse.close();
            }
            return generateResponse;
        } catch (Throwable th) {
            if (1 != 0) {
                originResponse.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    public SizeLimitedResponseReader getResponseReader(HttpRequest request, CloseableHttpResponse backEndResponse) {
        SizeLimitedResponseReader sizeLimitedResponseReader = new SizeLimitedResponseReader(this.resourceFactory, this.maxObjectSizeBytes, request, backEndResponse);
        return sizeLimitedResponseReader;
    }

    public HttpCacheEntry getCacheEntry(HttpHost host, HttpRequest request) throws IOException {
        HttpCacheEntry root = this.storage.getEntry(this.uriExtractor.getURI(host, request));
        if (root == null) {
            return null;
        }
        if (!root.hasVariants()) {
            return root;
        }
        String variantCacheKey = (String) root.getVariantMap().get(this.uriExtractor.getVariantKey(request, root));
        if (variantCacheKey == null) {
            return null;
        }
        return this.storage.getEntry(variantCacheKey);
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost host, HttpRequest request) throws IOException {
        this.cacheInvalidator.flushInvalidatedCacheEntries(host, request);
    }

    public Map<String, Variant> getVariantCacheEntriesWithEtags(HttpHost host, HttpRequest request) throws IOException {
        Map<String, Variant> variants = new HashMap<>();
        HttpCacheEntry root = this.storage.getEntry(this.uriExtractor.getURI(host, request));
        if (root == null || !root.hasVariants()) {
            return variants;
        }
        for (Entry<String, String> variant : root.getVariantMap().entrySet()) {
            addVariantWithEtag((String) variant.getKey(), (String) variant.getValue(), variants);
        }
        return variants;
    }

    private void addVariantWithEtag(String variantKey, String variantCacheKey, Map<String, Variant> variants) throws IOException {
        HttpCacheEntry entry = this.storage.getEntry(variantCacheKey);
        if (entry != null) {
            Header etagHeader = entry.getFirstHeader("ETag");
            if (etagHeader != null) {
                variants.put(etagHeader.getValue(), new Variant(variantKey, variantCacheKey, entry));
            }
        }
    }
}
