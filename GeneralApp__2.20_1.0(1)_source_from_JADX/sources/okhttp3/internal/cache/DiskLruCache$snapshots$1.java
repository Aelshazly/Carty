package okhttp3.internal.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.internal.cache.DiskLruCache.Entry;
import okhttp3.internal.cache.DiskLruCache.Snapshot;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u0002J\r\u0010\u0013\u001a\u00060\u0002R\u00020\u0003H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016R'\u0010\u0004\u001a\u0018\u0012\u0014\u0012\u0012 \u0006*\b\u0018\u00010\u0005R\u00020\u00030\u0005R\u00020\u00030\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0018\u00010\u0002R\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0018\u00010\u0002R\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\r¨\u0006\u0016"}, mo24952d2 = {"okhttp3/internal/cache/DiskLruCache$snapshots$1", "", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "delegate", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "kotlin.jvm.PlatformType", "getDelegate", "()Ljava/util/Iterator;", "nextSnapshot", "getNextSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "setNextSnapshot", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;)V", "removeSnapshot", "getRemoveSnapshot", "setRemoveSnapshot", "hasNext", "", "next", "remove", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: DiskLruCache.kt */
public final class DiskLruCache$snapshots$1 implements Iterator<Snapshot>, KMutableIterator {
    private final Iterator<Entry> delegate;
    private Snapshot nextSnapshot;
    private Snapshot removeSnapshot;
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$snapshots$1(DiskLruCache $outer) {
        this.this$0 = $outer;
        Iterator<Entry> it = new ArrayList($outer.getLruEntries$okhttp().values()).iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "ArrayList(lruEntries.values).iterator()");
        this.delegate = it;
    }

    public final Iterator<Entry> getDelegate() {
        return this.delegate;
    }

    public final Snapshot getNextSnapshot() {
        return this.nextSnapshot;
    }

    public final void setNextSnapshot(Snapshot snapshot) {
        this.nextSnapshot = snapshot;
    }

    public final Snapshot getRemoveSnapshot() {
        return this.removeSnapshot;
    }

    public final void setRemoveSnapshot(Snapshot snapshot) {
        this.removeSnapshot = snapshot;
    }

    public boolean hasNext() {
        if (this.nextSnapshot != null) {
            return true;
        }
        synchronized (this.this$0) {
            if (this.this$0.getClosed$okhttp()) {
                return false;
            }
            while (this.delegate.hasNext()) {
                Entry entry = (Entry) this.delegate.next();
                if (entry != null) {
                    if (entry.getReadable$okhttp()) {
                        Snapshot snapshot$okhttp = entry.snapshot$okhttp();
                        if (snapshot$okhttp != null) {
                            this.nextSnapshot = snapshot$okhttp;
                            return true;
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            return false;
        }
    }

    public Snapshot next() {
        if (hasNext()) {
            this.removeSnapshot = this.nextSnapshot;
            this.nextSnapshot = null;
            Snapshot snapshot = this.removeSnapshot;
            if (snapshot == null) {
                Intrinsics.throwNpe();
            }
            return snapshot;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        Snapshot removeSnapshot2 = this.removeSnapshot;
        if (removeSnapshot2 != null) {
            try {
                this.this$0.remove(removeSnapshot2.key());
            } catch (IOException e) {
            } catch (Throwable th) {
                this.removeSnapshot = null;
                throw th;
            }
            this.removeSnapshot = null;
            return;
        }
        throw new IllegalStateException("remove() before next()".toString());
    }
}