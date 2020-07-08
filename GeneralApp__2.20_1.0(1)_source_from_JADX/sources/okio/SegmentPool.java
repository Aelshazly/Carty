package okio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0007J\b\u0010\u000b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, mo24952d2 = {"Lokio/SegmentPool;", "", "()V", "MAX_SIZE", "", "byteCount", "next", "Lokio/Segment;", "recycle", "", "segment", "take", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: SegmentPool.kt */
public final class SegmentPool {
    public static final SegmentPool INSTANCE = new SegmentPool();
    public static final long MAX_SIZE = 65536;
    public static long byteCount;
    public static Segment next;

    private SegmentPool() {
    }

    @JvmStatic
    public static final Segment take() {
        synchronized (INSTANCE) {
            Segment result = next;
            if (result == null) {
                return new Segment();
            }
            next = result.next;
            result.next = null;
            byteCount -= (long) 8192;
            return result;
        }
    }

    @JvmStatic
    public static final void recycle(Segment segment) {
        Intrinsics.checkParameterIsNotNull(segment, "segment");
        if (!(segment.next == null && segment.prev == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (!segment.shared) {
            synchronized (INSTANCE) {
                long j = (long) 8192;
                if (byteCount + j <= 65536) {
                    byteCount += j;
                    segment.next = next;
                    segment.limit = 0;
                    segment.pos = segment.limit;
                    next = segment;
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }
}
