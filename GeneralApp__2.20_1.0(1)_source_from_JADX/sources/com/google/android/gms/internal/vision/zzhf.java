package com.google.android.gms.internal.vision;

import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhf<K> implements Entry<K, Object> {
    private Entry<K, zzhd> zzyc;

    private zzhf(Entry<K, zzhd> entry) {
        this.zzyc = entry;
    }

    public final K getKey() {
        return this.zzyc.getKey();
    }

    public final Object getValue() {
        if (((zzhd) this.zzyc.getValue()) == null) {
            return null;
        }
        return zzhd.zzgu();
    }

    public final zzhd zzgw() {
        return (zzhd) this.zzyc.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzic) {
            return ((zzhd) this.zzyc.getValue()).zzi((zzic) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
