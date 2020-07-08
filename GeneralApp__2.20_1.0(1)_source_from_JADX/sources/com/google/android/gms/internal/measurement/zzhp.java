package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
class zzhp extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzhi zza;

    private zzhp(zzhi zzhi) {
        this.zza = zzhi;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzhq(this.zza, null);
    }

    public int size() {
        return this.zza.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zza.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zza.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zza.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zza.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzhp(zzhi zzhi, zzhh zzhh) {
        this(zzhi);
    }
}
