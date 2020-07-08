package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.RequestLine;
import p008cz.msebera.android.httpclient.client.cache.CacheResponseStatus;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheContext;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.VersionInfo;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CachingExec */
public class CachingExec implements ClientExecChain {
    private static final boolean SUPPORTS_RANGE_AND_CONTENT_RANGE_HEADERS = false;
    private final AsynchronousValidator asynchRevalidator;
    private final ClientExecChain backend;
    private final CacheConfig cacheConfig;
    private final AtomicLong cacheHits;
    private final AtomicLong cacheMisses;
    private final AtomicLong cacheUpdates;
    private final CacheableRequestPolicy cacheableRequestPolicy;
    private final ConditionalRequestBuilder conditionalRequestBuilder;
    public HttpClientAndroidLog log;
    private final RequestProtocolCompliance requestCompliance;
    private final HttpCache responseCache;
    private final ResponseCachingPolicy responseCachingPolicy;
    private final ResponseProtocolCompliance responseCompliance;
    private final CachedHttpResponseGenerator responseGenerator;
    private final CachedResponseSuitabilityChecker suitabilityChecker;
    private final CacheValidityPolicy validityPolicy;
    private final Map<ProtocolVersion, String> viaHeaders;

    public CachingExec(ClientExecChain backend2, HttpCache cache, CacheConfig config) {
        this(backend2, cache, config, (AsynchronousValidator) null);
    }

    public CachingExec(ClientExecChain backend2, HttpCache cache, CacheConfig config, AsynchronousValidator asynchRevalidator2) {
        this.cacheHits = new AtomicLong();
        this.cacheMisses = new AtomicLong();
        this.cacheUpdates = new AtomicLong();
        this.viaHeaders = new HashMap(4);
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(backend2, "HTTP backend");
        Args.notNull(cache, "HttpCache");
        this.cacheConfig = config != null ? config : CacheConfig.DEFAULT;
        this.backend = backend2;
        this.responseCache = cache;
        this.validityPolicy = new CacheValidityPolicy();
        this.responseGenerator = new CachedHttpResponseGenerator(this.validityPolicy);
        this.cacheableRequestPolicy = new CacheableRequestPolicy();
        this.suitabilityChecker = new CachedResponseSuitabilityChecker(this.validityPolicy, this.cacheConfig);
        this.conditionalRequestBuilder = new ConditionalRequestBuilder();
        this.responseCompliance = new ResponseProtocolCompliance();
        this.requestCompliance = new RequestProtocolCompliance(this.cacheConfig.isWeakETagOnPutDeleteAllowed());
        ResponseCachingPolicy responseCachingPolicy2 = new ResponseCachingPolicy(this.cacheConfig.getMaxObjectSize(), this.cacheConfig.isSharedCache(), this.cacheConfig.isNeverCacheHTTP10ResponsesWithQuery(), this.cacheConfig.is303CachingEnabled());
        this.responseCachingPolicy = responseCachingPolicy2;
        this.asynchRevalidator = asynchRevalidator2;
    }

    public CachingExec(ClientExecChain backend2, ResourceFactory resourceFactory, HttpCacheStorage storage, CacheConfig config) {
        this(backend2, new BasicHttpCache(resourceFactory, storage, config), config);
    }

    public CachingExec(ClientExecChain backend2) {
        this(backend2, new BasicHttpCache(), CacheConfig.DEFAULT);
    }

    CachingExec(ClientExecChain backend2, HttpCache responseCache2, CacheValidityPolicy validityPolicy2, ResponseCachingPolicy responseCachingPolicy2, CachedHttpResponseGenerator responseGenerator2, CacheableRequestPolicy cacheableRequestPolicy2, CachedResponseSuitabilityChecker suitabilityChecker2, ConditionalRequestBuilder conditionalRequestBuilder2, ResponseProtocolCompliance responseCompliance2, RequestProtocolCompliance requestCompliance2, CacheConfig config, AsynchronousValidator asynchRevalidator2) {
        this.cacheHits = new AtomicLong();
        this.cacheMisses = new AtomicLong();
        this.cacheUpdates = new AtomicLong();
        this.viaHeaders = new HashMap(4);
        this.log = new HttpClientAndroidLog(getClass());
        this.cacheConfig = config != null ? config : CacheConfig.DEFAULT;
        this.backend = backend2;
        this.responseCache = responseCache2;
        this.validityPolicy = validityPolicy2;
        this.responseCachingPolicy = responseCachingPolicy2;
        this.responseGenerator = responseGenerator2;
        this.cacheableRequestPolicy = cacheableRequestPolicy2;
        this.suitabilityChecker = suitabilityChecker2;
        this.conditionalRequestBuilder = conditionalRequestBuilder2;
        this.responseCompliance = responseCompliance2;
        this.requestCompliance = requestCompliance2;
        this.asynchRevalidator = asynchRevalidator2;
    }

    public long getCacheHits() {
        return this.cacheHits.get();
    }

    public long getCacheMisses() {
        return this.cacheMisses.get();
    }

    public long getCacheUpdates() {
        return this.cacheUpdates.get();
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request) throws IOException, HttpException {
        return execute(route, request, HttpClientContext.create(), null);
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context) throws IOException, HttpException {
        return execute(route, request, context, null);
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        HttpHost target = context.getTargetHost();
        String via = generateViaHeader(request.getOriginal());
        setResponseStatus(context, CacheResponseStatus.CACHE_MISS);
        if (clientRequestsOurOptions(request)) {
            setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            return Proxies.enhanceResponse(new OptionsHttp11Response());
        }
        HttpResponse fatalErrorResponse = getFatallyNoncompliantResponse(request, context);
        if (fatalErrorResponse != null) {
            return Proxies.enhanceResponse(fatalErrorResponse);
        }
        this.requestCompliance.makeRequestCompliant(request);
        request.addHeader("Via", via);
        flushEntriesInvalidatedByRequest(context.getTargetHost(), request);
        if (!this.cacheableRequestPolicy.isServableFromCache(request)) {
            this.log.debug("Request is not servable from cache");
            return callBackend(route, request, context, execAware);
        }
        HttpCacheEntry entry = satisfyFromCache(target, request);
        if (entry != null) {
            return handleCacheHit(route, request, context, execAware, entry);
        }
        this.log.debug("Cache miss");
        return handleCacheMiss(route, request, context, execAware);
    }

    private CloseableHttpResponse handleCacheHit(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry) throws IOException, HttpException {
        CloseableHttpResponse out;
        HttpHost target = context.getTargetHost();
        recordCacheHit(target, request);
        Date now = getCurrentDate();
        if (this.suitabilityChecker.canCachedResponseBeUsed(target, request, entry, now)) {
            this.log.debug("Cache hit");
            out = generateCachedResponse(request, context, entry, now);
        } else if (!mayCallBackend(request)) {
            this.log.debug("Cache entry not suitable but only-if-cached requested");
            out = generateGatewayTimeout(context);
        } else if (entry.getStatusCode() != 304 || this.suitabilityChecker.isConditional(request)) {
            this.log.debug("Revalidating cache entry");
            return revalidateCacheEntry(route, request, context, execAware, entry, now);
        } else {
            this.log.debug("Cache entry not usable; calling backend");
            return callBackend(route, request, context, execAware);
        }
        context.setAttribute("http.route", route);
        context.setAttribute("http.target_host", target);
        context.setAttribute("http.request", request);
        context.setAttribute("http.response", out);
        context.setAttribute("http.request_sent", Boolean.TRUE);
        return out;
    }

    private CloseableHttpResponse revalidateCacheEntry(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry, Date now) throws HttpException {
        try {
            if (this.asynchRevalidator == null || staleResponseNotAllowed(request, entry, now) || !this.validityPolicy.mayReturnStaleWhileRevalidating(entry, now)) {
                return revalidateCacheEntry(route, request, context, execAware, entry);
            }
            this.log.trace("Serving stale with asynchronous revalidation");
            CloseableHttpResponse resp = generateCachedResponse(request, context, entry, now);
            this.asynchRevalidator.revalidateCacheEntry(this, route, request, context, execAware, entry);
            return resp;
        } catch (IOException e) {
            return handleRevalidationFailure(request, context, entry, now);
        }
    }

    private CloseableHttpResponse handleCacheMiss(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        HttpHost target = context.getTargetHost();
        recordCacheMiss(target, request);
        if (!mayCallBackend(request)) {
            return Proxies.enhanceResponse(new BasicHttpResponse((ProtocolVersion) HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
        }
        Map<String, Variant> variants = getExistingCacheVariants(target, request);
        if (variants == null || variants.size() <= 0) {
            return callBackend(route, request, context, execAware);
        }
        return negotiateResponseFromVariants(route, request, context, execAware, variants);
    }

    private HttpCacheEntry satisfyFromCache(HttpHost target, HttpRequestWrapper request) {
        try {
            return this.responseCache.getCacheEntry(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to retrieve entries from cache", ioe);
            return null;
        }
    }

    private HttpResponse getFatallyNoncompliantResponse(HttpRequestWrapper request, HttpContext context) {
        HttpResponse fatalErrorResponse = null;
        for (RequestProtocolError error : this.requestCompliance.requestIsFatallyNonCompliant(request)) {
            setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            fatalErrorResponse = this.requestCompliance.getErrorForRequest(error);
        }
        return fatalErrorResponse;
    }

    private Map<String, Variant> getExistingCacheVariants(HttpHost target, HttpRequestWrapper request) {
        try {
            return this.responseCache.getVariantCacheEntriesWithEtags(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to retrieve variant entries from cache", ioe);
            return null;
        }
    }

    private void recordCacheMiss(HttpHost target, HttpRequestWrapper request) {
        this.cacheMisses.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            RequestLine rl = request.getRequestLine();
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Cache miss [host: ");
            sb.append(target);
            sb.append("; uri: ");
            sb.append(rl.getUri());
            sb.append("]");
            httpClientAndroidLog.trace(sb.toString());
        }
    }

    private void recordCacheHit(HttpHost target, HttpRequestWrapper request) {
        this.cacheHits.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            RequestLine rl = request.getRequestLine();
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Cache hit [host: ");
            sb.append(target);
            sb.append("; uri: ");
            sb.append(rl.getUri());
            sb.append("]");
            httpClientAndroidLog.trace(sb.toString());
        }
    }

    private void recordCacheUpdate(HttpContext context) {
        this.cacheUpdates.getAndIncrement();
        setResponseStatus(context, CacheResponseStatus.VALIDATED);
    }

    private void flushEntriesInvalidatedByRequest(HttpHost target, HttpRequestWrapper request) {
        try {
            this.responseCache.flushInvalidatedCacheEntriesFor(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to flush invalidated entries from cache", ioe);
        }
    }

    private CloseableHttpResponse generateCachedResponse(HttpRequestWrapper request, HttpContext context, HttpCacheEntry entry, Date now) {
        CloseableHttpResponse cachedResponse;
        if (request.containsHeader("If-None-Match") || request.containsHeader("If-Modified-Since")) {
            cachedResponse = this.responseGenerator.generateNotModifiedResponse(entry);
        } else {
            cachedResponse = this.responseGenerator.generateResponse(entry);
        }
        setResponseStatus(context, CacheResponseStatus.CACHE_HIT);
        if (this.validityPolicy.getStalenessSecs(entry, now) > 0) {
            cachedResponse.addHeader("Warning", "110 localhost \"Response is stale\"");
        }
        return cachedResponse;
    }

    private CloseableHttpResponse handleRevalidationFailure(HttpRequestWrapper request, HttpContext context, HttpCacheEntry entry, Date now) {
        if (staleResponseNotAllowed(request, entry, now)) {
            return generateGatewayTimeout(context);
        }
        return unvalidatedCacheHit(context, entry);
    }

    private CloseableHttpResponse generateGatewayTimeout(HttpContext context) {
        setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
        return Proxies.enhanceResponse(new BasicHttpResponse((ProtocolVersion) HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
    }

    private CloseableHttpResponse unvalidatedCacheHit(HttpContext context, HttpCacheEntry entry) {
        CloseableHttpResponse cachedResponse = this.responseGenerator.generateResponse(entry);
        setResponseStatus(context, CacheResponseStatus.CACHE_HIT);
        cachedResponse.addHeader("Warning", "111 localhost \"Revalidation failed\"");
        return cachedResponse;
    }

    private boolean staleResponseNotAllowed(HttpRequestWrapper request, HttpCacheEntry entry, Date now) {
        return this.validityPolicy.mustRevalidate(entry) || (this.cacheConfig.isSharedCache() && this.validityPolicy.proxyRevalidate(entry)) || explicitFreshnessRequest(request, entry, now);
    }

    private boolean mayCallBackend(HttpRequestWrapper request) {
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if ("only-if-cached".equals(elt.getName())) {
                    this.log.trace("Request marked only-if-cached");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean explicitFreshnessRequest(HttpRequestWrapper request, HttpCacheEntry entry, Date now) {
        int i;
        HttpCacheEntry httpCacheEntry = entry;
        Header[] headers = request.getHeaders("Cache-Control");
        int length = headers.length;
        int i2 = 0;
        while (i2 < length) {
            HeaderElement[] elements = headers[i2].getElements();
            int length2 = elements.length;
            int i3 = 0;
            while (i3 < length2) {
                HeaderElement elt = elements[i3];
                if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(elt.getName())) {
                    try {
                        try {
                            i = i2;
                            if (this.validityPolicy.getCurrentAgeSecs(httpCacheEntry, now) - this.validityPolicy.getFreshnessLifetimeSecs(httpCacheEntry) > ((long) Integer.parseInt(elt.getValue()))) {
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    } catch (NumberFormatException e2) {
                        Date date = now;
                        return true;
                    }
                } else {
                    Date date2 = now;
                    i = i2;
                    if (!HeaderConstants.CACHE_CONTROL_MIN_FRESH.equals(elt.getName())) {
                        if ("max-age".equals(elt.getName())) {
                        }
                    }
                    return true;
                }
                i3++;
                i2 = i;
            }
            Date date3 = now;
            i2++;
        }
        Date date4 = now;
        return false;
    }

    private String generateViaHeader(HttpMessage msg) {
        String value;
        ProtocolVersion pv = msg.getProtocolVersion();
        String existingEntry = (String) this.viaHeaders.get(pv);
        if (existingEntry != null) {
            return existingEntry;
        }
        VersionInfo vi = VersionInfo.loadVersionInfo("cz.msebera.android.httpclient.client", getClass().getClassLoader());
        String release = vi != null ? vi.getRelease() : VersionInfo.UNAVAILABLE;
        if (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(pv.getProtocol())) {
            value = String.format("%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{Integer.valueOf(pv.getMajor()), Integer.valueOf(pv.getMinor()), release});
        } else {
            value = String.format("%s/%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{pv.getProtocol(), Integer.valueOf(pv.getMajor()), Integer.valueOf(pv.getMinor()), release});
        }
        this.viaHeaders.put(pv, value);
        return value;
    }

    private void setResponseStatus(HttpContext context, CacheResponseStatus value) {
        if (context != null) {
            context.setAttribute(HttpCacheContext.CACHE_RESPONSE_STATUS, value);
        }
    }

    public boolean supportsRangeAndContentRangeHeaders() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Date getCurrentDate() {
        return new Date();
    }

    /* access modifiers changed from: 0000 */
    public boolean clientRequestsOurOptions(HttpRequest request) {
        RequestLine line = request.getRequestLine();
        if (!"OPTIONS".equals(line.getMethod())) {
            return false;
        }
        if (!"*".equals(line.getUri())) {
            return false;
        }
        if (!"0".equals(request.getFirstHeader("Max-Forwards").getValue())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse callBackend(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Date requestDate = getCurrentDate();
        this.log.trace("Calling the backend");
        CloseableHttpResponse backendResponse = this.backend.execute(route, request, context, execAware);
        try {
            backendResponse.addHeader("Via", generateViaHeader(backendResponse));
            return handleBackendResponse(route, request, context, execAware, requestDate, getCurrentDate(), backendResponse);
        } catch (IOException ex) {
            backendResponse.close();
            throw ex;
        } catch (RuntimeException ex2) {
            backendResponse.close();
            throw ex2;
        }
    }

    private boolean revalidationResponseIsTooOld(HttpResponse backendResponse, HttpCacheEntry cacheEntry) {
        String str = "Date";
        Header entryDateHeader = cacheEntry.getFirstHeader(str);
        Header responseDateHeader = backendResponse.getFirstHeader(str);
        if (!(entryDateHeader == null || responseDateHeader == null)) {
            Date entryDate = DateUtils.parseDate(entryDateHeader.getValue());
            Date respDate = DateUtils.parseDate(responseDateHeader.getValue());
            if (!(entryDate == null || respDate == null || !respDate.before(entryDate))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse negotiateResponseFromVariants(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, Map<String, Variant> variants) throws IOException, HttpException {
        CloseableHttpResponse backendResponse;
        HttpRequestWrapper httpRequestWrapper = request;
        HttpClientContext httpClientContext = context;
        Map<String, Variant> map = variants;
        HttpRequestWrapper conditionalRequest = this.conditionalRequestBuilder.buildConditionalRequestFromVariants(httpRequestWrapper, map);
        Date requestDate = getCurrentDate();
        CloseableHttpResponse backendResponse2 = this.backend.execute(route, conditionalRequest, httpClientContext, execAware);
        try {
            Date responseDate = getCurrentDate();
            backendResponse2.addHeader("Via", generateViaHeader(backendResponse2));
            if (backendResponse2.getStatusLine().getStatusCode() != 304) {
                CloseableHttpResponse backendResponse3 = backendResponse2;
                try {
                    return handleBackendResponse(route, request, context, execAware, requestDate, responseDate, backendResponse2);
                } catch (IOException e) {
                    ex = e;
                    HttpRequestWrapper httpRequestWrapper2 = conditionalRequest;
                    HttpRequestWrapper httpRequestWrapper3 = httpRequestWrapper;
                    backendResponse = backendResponse3;
                    backendResponse.close();
                    throw ex;
                } catch (RuntimeException e2) {
                    ex = e2;
                    HttpRequestWrapper httpRequestWrapper4 = conditionalRequest;
                    HttpRequestWrapper httpRequestWrapper5 = httpRequestWrapper;
                    backendResponse = backendResponse3;
                    backendResponse.close();
                    throw ex;
                }
            } else {
                backendResponse2 = backendResponse2;
                Header resultEtagHeader = backendResponse2.getFirstHeader("ETag");
                if (resultEtagHeader == null) {
                    try {
                        this.log.warn("304 response did not contain ETag");
                        IOUtils.consume(backendResponse2.getEntity());
                        backendResponse2.close();
                        return callBackend(route, request, context, execAware);
                    } catch (IOException e3) {
                        ex = e3;
                        backendResponse = backendResponse2;
                        HttpRequestWrapper httpRequestWrapper6 = conditionalRequest;
                        HttpRequestWrapper httpRequestWrapper7 = httpRequestWrapper;
                        backendResponse.close();
                        throw ex;
                    } catch (RuntimeException e4) {
                        ex = e4;
                        backendResponse = backendResponse2;
                        HttpRequestWrapper httpRequestWrapper8 = conditionalRequest;
                        HttpRequestWrapper httpRequestWrapper9 = httpRequestWrapper;
                        backendResponse.close();
                        throw ex;
                    }
                } else {
                    String resultEtag = resultEtagHeader.getValue();
                    Variant matchingVariant = (Variant) map.get(resultEtag);
                    if (matchingVariant == null) {
                        this.log.debug("304 response did not contain ETag matching one sent in If-None-Match");
                        IOUtils.consume(backendResponse2.getEntity());
                        backendResponse2.close();
                        return callBackend(route, request, context, execAware);
                    }
                    HttpCacheEntry matchedEntry = matchingVariant.getEntry();
                    if (revalidationResponseIsTooOld(backendResponse2, matchedEntry)) {
                        IOUtils.consume(backendResponse2.getEntity());
                        backendResponse2.close();
                        Variant variant = matchingVariant;
                        String str = resultEtag;
                        return retryRequestUnconditionally(route, request, context, execAware, matchedEntry);
                    }
                    HttpCacheEntry matchedEntry2 = matchedEntry;
                    Variant matchingVariant2 = matchingVariant;
                    String str2 = resultEtag;
                    recordCacheUpdate(httpClientContext);
                    backendResponse = backendResponse2;
                    HttpRequestWrapper httpRequestWrapper10 = conditionalRequest;
                    HttpRequestWrapper httpRequestWrapper11 = httpRequestWrapper;
                    try {
                        HttpCacheEntry responseEntry = getUpdatedVariantEntry(context.getTargetHost(), conditionalRequest, requestDate, responseDate, backendResponse, matchingVariant2, matchedEntry2);
                        backendResponse.close();
                        CloseableHttpResponse resp = this.responseGenerator.generateResponse(responseEntry);
                        tryToUpdateVariantMap(context.getTargetHost(), httpRequestWrapper11, matchingVariant2);
                        if (shouldSendNotModifiedResponse(httpRequestWrapper11, responseEntry)) {
                            return this.responseGenerator.generateNotModifiedResponse(responseEntry);
                        }
                        return resp;
                    } catch (IOException e5) {
                        ex = e5;
                        backendResponse.close();
                        throw ex;
                    } catch (RuntimeException e6) {
                        ex = e6;
                        backendResponse.close();
                        throw ex;
                    }
                }
            }
        } catch (IOException e7) {
            ex = e7;
            backendResponse = backendResponse2;
            HttpRequestWrapper httpRequestWrapper12 = conditionalRequest;
            HttpRequestWrapper httpRequestWrapper13 = httpRequestWrapper;
            backendResponse.close();
            throw ex;
        } catch (RuntimeException e8) {
            ex = e8;
            backendResponse = backendResponse2;
            HttpRequestWrapper httpRequestWrapper14 = conditionalRequest;
            HttpRequestWrapper httpRequestWrapper15 = httpRequestWrapper;
            backendResponse.close();
            throw ex;
        }
    }

    private CloseableHttpResponse retryRequestUnconditionally(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry matchedEntry) throws IOException, HttpException {
        return callBackend(route, this.conditionalRequestBuilder.buildUnconditionalRequest(request, matchedEntry), context, execAware);
    }

    private HttpCacheEntry getUpdatedVariantEntry(HttpHost target, HttpRequestWrapper conditionalRequest, Date requestDate, Date responseDate, CloseableHttpResponse backendResponse, Variant matchingVariant, HttpCacheEntry matchedEntry) throws IOException {
        HttpCacheEntry responseEntry = matchedEntry;
        try {
            responseEntry = this.responseCache.updateVariantCacheEntry(target, conditionalRequest, matchedEntry, backendResponse, requestDate, responseDate, matchingVariant.getCacheKey());
        } catch (IOException ioe) {
            this.log.warn("Could not update cache entry", ioe);
        } catch (Throwable th) {
            backendResponse.close();
            throw th;
        }
        backendResponse.close();
        return responseEntry;
    }

    private void tryToUpdateVariantMap(HttpHost target, HttpRequestWrapper request, Variant matchingVariant) {
        try {
            this.responseCache.reuseVariantEntryFor(target, request, matchingVariant);
        } catch (IOException ioe) {
            this.log.warn("Could not update cache entry to reuse variant", ioe);
        }
    }

    private boolean shouldSendNotModifiedResponse(HttpRequestWrapper request, HttpCacheEntry responseEntry) {
        return this.suitabilityChecker.isConditional(request) && this.suitabilityChecker.allConditionalsMatch(request, responseEntry, new Date());
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse revalidateCacheEntry(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry cacheEntry) throws IOException, HttpException {
        Date requestDate;
        CloseableHttpResponse backendResponse;
        Date responseDate;
        Date responseDate2;
        HttpRoute httpRoute = route;
        HttpRequestWrapper httpRequestWrapper = request;
        HttpClientContext httpClientContext = context;
        HttpExecutionAware httpExecutionAware = execAware;
        HttpCacheEntry httpCacheEntry = cacheEntry;
        HttpRequestWrapper conditionalRequest = this.conditionalRequestBuilder.buildConditionalRequest(httpRequestWrapper, httpCacheEntry);
        URI uri = conditionalRequest.getURI();
        if (uri != null) {
            try {
                conditionalRequest.setURI(InternalURIUtils.rewriteURIForRoute(uri, httpRoute));
            } catch (URISyntaxException ex) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid URI: ");
                sb.append(uri);
                throw new ProtocolException(sb.toString(), ex);
            }
        }
        Date requestDate2 = getCurrentDate();
        CloseableHttpResponse backendResponse2 = this.backend.execute(httpRoute, conditionalRequest, httpClientContext, httpExecutionAware);
        Date responseDate3 = getCurrentDate();
        if (revalidationResponseIsTooOld(backendResponse2, httpCacheEntry)) {
            backendResponse2.close();
            HttpRequestWrapper unconditional = this.conditionalRequestBuilder.buildUnconditionalRequest(httpRequestWrapper, httpCacheEntry);
            Date requestDate3 = getCurrentDate();
            requestDate = requestDate3;
            backendResponse = this.backend.execute(httpRoute, unconditional, httpClientContext, httpExecutionAware);
            responseDate = getCurrentDate();
        } else {
            requestDate = requestDate2;
            backendResponse = backendResponse2;
            responseDate = responseDate3;
        }
        backendResponse.addHeader("Via", generateViaHeader(backendResponse));
        int statusCode = backendResponse.getStatusLine().getStatusCode();
        if (statusCode == 304 || statusCode == 200) {
            recordCacheUpdate(httpClientContext);
        }
        if (statusCode == 304) {
            int i = statusCode;
            CloseableHttpResponse closeableHttpResponse = backendResponse;
            HttpCacheEntry updatedEntry = this.responseCache.updateCacheEntry(context.getTargetHost(), request, cacheEntry, backendResponse, requestDate, responseDate);
            if (!this.suitabilityChecker.isConditional(httpRequestWrapper) || !this.suitabilityChecker.allConditionalsMatch(httpRequestWrapper, updatedEntry, new Date())) {
                return this.responseGenerator.generateResponse(updatedEntry);
            }
            return this.responseGenerator.generateNotModifiedResponse(updatedEntry);
        }
        Date responseDate4 = responseDate;
        CloseableHttpResponse backendResponse3 = backendResponse;
        if (!staleIfErrorAppliesTo(statusCode)) {
            responseDate2 = responseDate4;
        } else if (!staleResponseNotAllowed(httpRequestWrapper, httpCacheEntry, getCurrentDate())) {
            responseDate2 = responseDate4;
            if (this.validityPolicy.mayReturnStaleIfError(httpRequestWrapper, httpCacheEntry, responseDate2)) {
                try {
                    CloseableHttpResponse cachedResponse = this.responseGenerator.generateResponse(httpCacheEntry);
                    cachedResponse.addHeader("Warning", "110 localhost \"Response is stale\"");
                    return cachedResponse;
                } finally {
                    backendResponse3.close();
                }
            }
        } else {
            responseDate2 = responseDate4;
        }
        Date date = responseDate2;
        URI uri2 = uri;
        return handleBackendResponse(route, conditionalRequest, context, execAware, requestDate, responseDate2, backendResponse3);
    }

    private boolean staleIfErrorAppliesTo(int statusCode) {
        return statusCode == 500 || statusCode == 502 || statusCode == 503 || statusCode == 504;
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse handleBackendResponse(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, Date requestDate, Date responseDate, CloseableHttpResponse backendResponse) throws IOException {
        this.log.trace("Handling Backend response");
        this.responseCompliance.ensureProtocolCompliance(request, backendResponse);
        HttpHost target = context.getTargetHost();
        boolean cacheable = this.responseCachingPolicy.isResponseCacheable((HttpRequest) request, (HttpResponse) backendResponse);
        this.responseCache.flushInvalidatedCacheEntriesFor(target, request, backendResponse);
        if (!cacheable || alreadyHaveNewerCacheEntry(target, request, backendResponse)) {
            if (!cacheable) {
                try {
                    this.responseCache.flushCacheEntriesFor(target, request);
                } catch (IOException ioe) {
                    this.log.warn("Unable to flush invalid cache entries", ioe);
                }
            }
            return backendResponse;
        }
        storeRequestIfModifiedSinceFor304Response(request, backendResponse);
        return this.responseCache.cacheAndReturnResponse(target, (HttpRequest) request, backendResponse, requestDate, responseDate);
    }

    private void storeRequestIfModifiedSinceFor304Response(HttpRequest request, HttpResponse backendResponse) {
        if (backendResponse.getStatusLine().getStatusCode() == 304) {
            Header h = request.getFirstHeader("If-Modified-Since");
            if (h != null) {
                backendResponse.addHeader("Last-Modified", h.getValue());
            }
        }
    }

    private boolean alreadyHaveNewerCacheEntry(HttpHost target, HttpRequestWrapper request, HttpResponse backendResponse) {
        HttpCacheEntry existing = null;
        try {
            existing = this.responseCache.getCacheEntry(target, request);
        } catch (IOException e) {
        }
        if (existing == null) {
            return false;
        }
        String str = "Date";
        Header entryDateHeader = existing.getFirstHeader(str);
        if (entryDateHeader == null) {
            return false;
        }
        Header responseDateHeader = backendResponse.getFirstHeader(str);
        if (responseDateHeader == null) {
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
