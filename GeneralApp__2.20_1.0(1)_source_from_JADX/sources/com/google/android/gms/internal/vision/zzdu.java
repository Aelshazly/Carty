package com.google.android.gms.internal.vision;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdu {
    private final ConcurrentHashMap<zzdx, List<Throwable>> zzmi = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzmj = new ReferenceQueue<>();

    zzdu() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference poll = this.zzmj.poll();
        while (poll != null) {
            this.zzmi.remove(poll);
            poll = this.zzmj.poll();
        }
        List<Throwable> list = (List) this.zzmi.get(new zzdx(th, null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> list2 = (List) this.zzmi.putIfAbsent(new zzdx(th, this.zzmj), vector);
        return list2 == null ? vector : list2;
    }
}
