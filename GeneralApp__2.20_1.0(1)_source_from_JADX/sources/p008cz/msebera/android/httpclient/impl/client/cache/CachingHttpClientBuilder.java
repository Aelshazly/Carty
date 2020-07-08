package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.File;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;
import p008cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CachingHttpClientBuilder */
public class CachingHttpClientBuilder extends HttpClientBuilder {
    private CacheConfig cacheConfig;
    private File cacheDir;
    private HttpCacheInvalidator httpCacheInvalidator;
    private ResourceFactory resourceFactory;
    private SchedulingStrategy schedulingStrategy;
    private HttpCacheStorage storage;

    public static CachingHttpClientBuilder create() {
        return new CachingHttpClientBuilder();
    }

    protected CachingHttpClientBuilder() {
    }

    public final CachingHttpClientBuilder setResourceFactory(ResourceFactory resourceFactory2) {
        this.resourceFactory = resourceFactory2;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheStorage(HttpCacheStorage storage2) {
        this.storage = storage2;
        return this;
    }

    public final CachingHttpClientBuilder setCacheDir(File cacheDir2) {
        this.cacheDir = cacheDir2;
        return this;
    }

    public final CachingHttpClientBuilder setCacheConfig(CacheConfig cacheConfig2) {
        this.cacheConfig = cacheConfig2;
        return this;
    }

    public final CachingHttpClientBuilder setSchedulingStrategy(SchedulingStrategy schedulingStrategy2) {
        this.schedulingStrategy = schedulingStrategy2;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheInvalidator(HttpCacheInvalidator cacheInvalidator) {
        this.httpCacheInvalidator = cacheInvalidator;
        return this;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [cz.msebera.android.httpclient.client.cache.HttpCacheStorage] */
    /* JADX WARNING: type inference failed for: r8v0, types: [cz.msebera.android.httpclient.client.cache.HttpCacheStorage] */
    /* JADX WARNING: type inference failed for: r3v0, types: [cz.msebera.android.httpclient.client.cache.HttpCacheStorage] */
    /* JADX WARNING: type inference failed for: r8v1 */
    /* JADX WARNING: type inference failed for: r2v3, types: [cz.msebera.android.httpclient.impl.client.cache.ManagedHttpCacheStorage, java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r8v2 */
    /* JADX WARNING: type inference failed for: r2v4, types: [cz.msebera.android.httpclient.impl.client.cache.BasicHttpCacheStorage] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r8v3 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.impl.execchain.ClientExecChain decorateMainExec(p008cz.msebera.android.httpclient.impl.execchain.ClientExecChain r15) {
        /*
            r14 = this;
            cz.msebera.android.httpclient.impl.client.cache.CacheConfig r0 = r14.cacheConfig
            if (r0 == 0) goto L_0x0005
            goto L_0x0007
        L_0x0005:
            cz.msebera.android.httpclient.impl.client.cache.CacheConfig r0 = p008cz.msebera.android.httpclient.impl.client.cache.CacheConfig.DEFAULT
        L_0x0007:
            cz.msebera.android.httpclient.client.cache.ResourceFactory r1 = r14.resourceFactory
            if (r1 != 0) goto L_0x001f
            java.io.File r2 = r14.cacheDir
            if (r2 != 0) goto L_0x0017
            cz.msebera.android.httpclient.impl.client.cache.HeapResourceFactory r2 = new cz.msebera.android.httpclient.impl.client.cache.HeapResourceFactory
            r2.<init>()
            r1 = r2
            r7 = r1
            goto L_0x0020
        L_0x0017:
            cz.msebera.android.httpclient.impl.client.cache.FileResourceFactory r3 = new cz.msebera.android.httpclient.impl.client.cache.FileResourceFactory
            r3.<init>(r2)
            r1 = r3
            r7 = r1
            goto L_0x0020
        L_0x001f:
            r7 = r1
        L_0x0020:
            cz.msebera.android.httpclient.client.cache.HttpCacheStorage r1 = r14.storage
            if (r1 != 0) goto L_0x003b
            java.io.File r2 = r14.cacheDir
            if (r2 != 0) goto L_0x0030
            cz.msebera.android.httpclient.impl.client.cache.BasicHttpCacheStorage r2 = new cz.msebera.android.httpclient.impl.client.cache.BasicHttpCacheStorage
            r2.<init>(r0)
            r1 = r2
            r8 = r1
            goto L_0x003c
        L_0x0030:
            cz.msebera.android.httpclient.impl.client.cache.ManagedHttpCacheStorage r2 = new cz.msebera.android.httpclient.impl.client.cache.ManagedHttpCacheStorage
            r2.<init>(r0)
            r14.addCloseable(r2)
            r1 = r2
            r8 = r1
            goto L_0x003c
        L_0x003b:
            r8 = r1
        L_0x003c:
            cz.msebera.android.httpclient.impl.client.cache.AsynchronousValidator r9 = r14.createAsynchronousRevalidator(r0)
            cz.msebera.android.httpclient.impl.client.cache.CacheKeyGenerator r1 = new cz.msebera.android.httpclient.impl.client.cache.CacheKeyGenerator
            r1.<init>()
            r10 = r1
            cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator r1 = r14.httpCacheInvalidator
            if (r1 != 0) goto L_0x0052
            cz.msebera.android.httpclient.impl.client.cache.CacheInvalidator r2 = new cz.msebera.android.httpclient.impl.client.cache.CacheInvalidator
            r2.<init>(r10, r8)
            r1 = r2
            r11 = r1
            goto L_0x0053
        L_0x0052:
            r11 = r1
        L_0x0053:
            cz.msebera.android.httpclient.impl.client.cache.CachingExec r12 = new cz.msebera.android.httpclient.impl.client.cache.CachingExec
            cz.msebera.android.httpclient.impl.client.cache.BasicHttpCache r13 = new cz.msebera.android.httpclient.impl.client.cache.BasicHttpCache
            r1 = r13
            r2 = r7
            r3 = r8
            r4 = r0
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r12.<init>(r15, r13, r0, r9)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.impl.client.cache.CachingHttpClientBuilder.decorateMainExec(cz.msebera.android.httpclient.impl.execchain.ClientExecChain):cz.msebera.android.httpclient.impl.execchain.ClientExecChain");
    }

    private AsynchronousValidator createAsynchronousRevalidator(CacheConfig config) {
        if (config.getAsynchronousWorkersMax() <= 0) {
            return null;
        }
        AsynchronousValidator revalidator = new AsynchronousValidator(createSchedulingStrategy(config));
        addCloseable(revalidator);
        return revalidator;
    }

    private SchedulingStrategy createSchedulingStrategy(CacheConfig config) {
        SchedulingStrategy schedulingStrategy2 = this.schedulingStrategy;
        return schedulingStrategy2 != null ? schedulingStrategy2 : new ImmediateSchedulingStrategy(config);
    }
}
