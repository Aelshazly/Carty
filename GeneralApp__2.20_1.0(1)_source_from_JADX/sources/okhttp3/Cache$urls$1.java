package okhttp3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.internal.cache.DiskLruCache.Snapshot;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0013\u001a\u00020\u0004H\u0002J\t\u0010\u0014\u001a\u00020\u0002H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\f\u0012\b\u0012\u00060\nR\u00020\u000b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, mo24952d2 = {"okhttp3/Cache$urls$1", "", "", "canRemove", "", "getCanRemove", "()Z", "setCanRemove", "(Z)V", "delegate", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "getDelegate", "()Ljava/util/Iterator;", "nextUrl", "getNextUrl", "()Ljava/lang/String;", "setNextUrl", "(Ljava/lang/String;)V", "hasNext", "next", "remove", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Cache.kt */
public final class Cache$urls$1 implements Iterator<String>, KMutableIterator {
    private boolean canRemove;
    private final Iterator<Snapshot> delegate;
    private String nextUrl;
    final /* synthetic */ Cache this$0;

    Cache$urls$1(Cache $outer) {
        this.this$0 = $outer;
        this.delegate = $outer.getCache$okhttp().snapshots();
    }

    public final Iterator<Snapshot> getDelegate() {
        return this.delegate;
    }

    public final String getNextUrl() {
        return this.nextUrl;
    }

    public final void setNextUrl(String str) {
        this.nextUrl = str;
    }

    public final boolean getCanRemove() {
        return this.canRemove;
    }

    public final void setCanRemove(boolean z) {
        this.canRemove = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.p014io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003b, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasNext() {
        /*
            r8 = this;
            java.lang.String r0 = r8.nextUrl
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = 0
            r8.canRemove = r0
        L_0x0009:
            java.util.Iterator<okhttp3.internal.cache.DiskLruCache$Snapshot> r2 = r8.delegate
            boolean r2 = r2.hasNext()
            if (r2 == 0) goto L_0x003e
            java.util.Iterator<okhttp3.internal.cache.DiskLruCache$Snapshot> r2 = r8.delegate     // Catch:{ IOException -> 0x003b }
            java.lang.Object r2 = r2.next()     // Catch:{ IOException -> 0x003b }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x003b }
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ IOException -> 0x003b }
            r4 = r2
            okhttp3.internal.cache.DiskLruCache$Snapshot r4 = (okhttp3.internal.cache.DiskLruCache.Snapshot) r4     // Catch:{ all -> 0x0034 }
            r5 = 0
            okio.Source r6 = r4.getSource(r0)     // Catch:{ all -> 0x0034 }
            okio.BufferedSource r6 = okio.Okio.buffer(r6)     // Catch:{ all -> 0x0034 }
            java.lang.String r7 = r6.readUtf8LineStrict()     // Catch:{ all -> 0x0034 }
            r8.nextUrl = r7     // Catch:{ all -> 0x0034 }
            kotlin.p014io.CloseableKt.closeFinally(r2, r3)     // Catch:{ IOException -> 0x003b }
            return r1
        L_0x0034:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r4 = move-exception
            kotlin.p014io.CloseableKt.closeFinally(r2, r3)     // Catch:{ IOException -> 0x003b }
            throw r4     // Catch:{ IOException -> 0x003b }
        L_0x003b:
            r2 = move-exception
            goto L_0x0009
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache$urls$1.hasNext():boolean");
    }

    public String next() {
        if (hasNext()) {
            String result = this.nextUrl;
            if (result == null) {
                Intrinsics.throwNpe();
            }
            this.nextUrl = null;
            this.canRemove = true;
            return result;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.canRemove) {
            this.delegate.remove();
            return;
        }
        throw new IllegalStateException("remove() before next()".toString());
    }
}
