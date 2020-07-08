package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzfr<K> implements Entry<K, Object> {
    private Entry<K, zzfp> zza;

    private zzfr(Entry<K, zzfp> entry) {
        this.zza = entry;
    }

    public final K getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzfp) this.zza.getValue()) == null) {
            return null;
        }
        return zzfp.zza();
    }

    public final zzfp zza() {
        return (zzfp) this.zza.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzgo) {
            return ((zzfp) this.zza.getValue()).zza((zzgo) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
