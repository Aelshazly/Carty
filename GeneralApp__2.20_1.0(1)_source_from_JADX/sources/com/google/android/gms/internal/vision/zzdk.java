package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzdk<K, V> extends zzdj<Entry<K, V>> {
    /* access modifiers changed from: private */
    public final transient int size;
    private final transient zzdg<K, V> zzma;
    /* access modifiers changed from: private */
    public final transient Object[] zzmb;
    private final transient int zzmc = 0;

    zzdk(zzdg<K, V> zzdg, Object[] objArr, int i, int i2) {
        this.zzma = zzdg;
        this.zzmb = objArr;
        this.size = i2;
    }

    public final zzdr<Entry<K, V>> zzby() {
        return (zzdr) zzcc().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Object[] objArr, int i) {
        return zzcc().zza(objArr, i);
    }

    /* access modifiers changed from: 0000 */
    public final zzdf<Entry<K, V>> zzch() {
        return new zzdn(this);
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (value == null || !value.equals(this.zzma.get(key))) {
            return false;
        }
        return true;
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
