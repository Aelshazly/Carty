package okhttp3.internal.cache;

import kotlin.Metadata;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo24952d2 = {"<anonymous>", "", "run"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: DiskLruCache.kt */
final class DiskLruCache$cleanupRunnable$1 implements Runnable {
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$cleanupRunnable$1(DiskLruCache diskLruCache) {
        this.this$0 = diskLruCache;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r5 = this;
            okhttp3.internal.cache.DiskLruCache r0 = r5.this$0
            monitor-enter(r0)
            r1 = 0
            okhttp3.internal.cache.DiskLruCache r2 = r5.this$0     // Catch:{ all -> 0x0053 }
            boolean r2 = r2.initialized     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0051
            okhttp3.internal.cache.DiskLruCache r2 = r5.this$0     // Catch:{ all -> 0x0053 }
            boolean r2 = r2.getClosed$okhttp()     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0015
            goto L_0x0051
        L_0x0015:
            r2 = 1
            okhttp3.internal.cache.DiskLruCache r3 = r5.this$0     // Catch:{ IOException -> 0x001d }
            r3.trimToSize()     // Catch:{ IOException -> 0x001d }
            goto L_0x0023
        L_0x001d:
            r3 = move-exception
            okhttp3.internal.cache.DiskLruCache r4 = r5.this$0     // Catch:{ all -> 0x0053 }
            r4.mostRecentTrimFailed = r2     // Catch:{ all -> 0x0053 }
        L_0x0023:
            okhttp3.internal.cache.DiskLruCache r3 = r5.this$0     // Catch:{ IOException -> 0x0039 }
            boolean r3 = r3.journalRebuildRequired()     // Catch:{ IOException -> 0x0039 }
            if (r3 == 0) goto L_0x004c
            okhttp3.internal.cache.DiskLruCache r3 = r5.this$0     // Catch:{ IOException -> 0x0039 }
            r3.rebuildJournal$okhttp()     // Catch:{ IOException -> 0x0039 }
            okhttp3.internal.cache.DiskLruCache r3 = r5.this$0     // Catch:{ IOException -> 0x0039 }
            r4 = 0
            r3.redundantOpCount = r4     // Catch:{ IOException -> 0x0039 }
            goto L_0x004c
        L_0x0039:
            r3 = move-exception
            okhttp3.internal.cache.DiskLruCache r4 = r5.this$0     // Catch:{ all -> 0x0053 }
            r4.mostRecentRebuildFailed = r2     // Catch:{ all -> 0x0053 }
            okhttp3.internal.cache.DiskLruCache r2 = r5.this$0     // Catch:{ all -> 0x0053 }
            okio.Sink r4 = okio.Okio.blackhole()     // Catch:{ all -> 0x0053 }
            okio.BufferedSink r4 = okio.Okio.buffer(r4)     // Catch:{ all -> 0x0053 }
            r2.journalWriter = r4     // Catch:{ all -> 0x0053 }
        L_0x004c:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0053 }
            monitor-exit(r0)
            return
        L_0x0051:
            monitor-exit(r0)
            return
        L_0x0053:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache$cleanupRunnable$1.run():void");
    }
}
