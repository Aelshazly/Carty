package com.google.android.gms.internal.vision;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
class zziw<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zzaab;
    /* access modifiers changed from: private */
    public List<zzjb> zzaac;
    /* access modifiers changed from: private */
    public Map<K, V> zzaad;
    private volatile zzjd zzaae;
    /* access modifiers changed from: private */
    public Map<K, V> zzaaf;
    private volatile zzix zzaag;
    private boolean zzti;

    static <FieldDescriptorType extends zzgk<FieldDescriptorType>> zziw<FieldDescriptorType, Object> zzbu(int i) {
        return new zziv(i);
    }

    private zziw(int i) {
        this.zzaab = i;
        this.zzaac = Collections.emptyList();
        this.zzaad = Collections.emptyMap();
        this.zzaaf = Collections.emptyMap();
    }

    public void zzdp() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzti) {
            if (this.zzaad.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzaad);
            }
            this.zzaad = map;
            if (this.zzaaf.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzaaf);
            }
            this.zzaaf = map2;
            this.zzti = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzti;
    }

    public final int zzhx() {
        return this.zzaac.size();
    }

    public final Entry<K, V> zzbv(int i) {
        return (Entry) this.zzaac.get(i);
    }

    public final Iterable<Entry<K, V>> zzhy() {
        if (this.zzaad.isEmpty()) {
            return zzja.zzid();
        }
        return this.zzaad.entrySet();
    }

    public int size() {
        return this.zzaac.size() + this.zzaad.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzaad.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return ((zzjb) this.zzaac.get(zza)).getValue();
        }
        return this.zzaad.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzia();
        int zza = zza(k);
        if (zza >= 0) {
            return ((zzjb) this.zzaac.get(zza)).setValue(v);
        }
        zzia();
        if (this.zzaac.isEmpty() && !(this.zzaac instanceof ArrayList)) {
            this.zzaac = new ArrayList(this.zzaab);
        }
        int i = -(zza + 1);
        if (i >= this.zzaab) {
            return zzib().put(k, v);
        }
        int size = this.zzaac.size();
        int i2 = this.zzaab;
        if (size == i2) {
            zzjb zzjb = (zzjb) this.zzaac.remove(i2 - 1);
            zzib().put((Comparable) zzjb.getKey(), zzjb.getValue());
        }
        this.zzaac.add(i, new zzjb(this, k, v));
        return null;
    }

    public void clear() {
        zzia();
        if (!this.zzaac.isEmpty()) {
            this.zzaac.clear();
        }
        if (!this.zzaad.isEmpty()) {
            this.zzaad.clear();
        }
    }

    public V remove(Object obj) {
        zzia();
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return zzbw(zza);
        }
        if (this.zzaad.isEmpty()) {
            return null;
        }
        return this.zzaad.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzbw(int i) {
        zzia();
        V value = ((zzjb) this.zzaac.remove(i)).getValue();
        if (!this.zzaad.isEmpty()) {
            Iterator it = zzib().entrySet().iterator();
            this.zzaac.add(new zzjb(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzaac.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzjb) this.zzaac.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzjb) this.zzaac.get(i2)).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzaae == null) {
            this.zzaae = new zzjd(this, null);
        }
        return this.zzaae;
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> zzhz() {
        if (this.zzaag == null) {
            this.zzaag = new zzix(this, null);
        }
        return this.zzaag;
    }

    /* access modifiers changed from: private */
    public final void zzia() {
        if (this.zzti) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzib() {
        zzia();
        if (this.zzaad.isEmpty() && !(this.zzaad instanceof TreeMap)) {
            this.zzaad = new TreeMap();
            this.zzaaf = ((TreeMap) this.zzaad).descendingMap();
        }
        return (SortedMap) this.zzaad;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zziw)) {
            return super.equals(obj);
        }
        zziw zziw = (zziw) obj;
        int size = size();
        if (size != zziw.size()) {
            return false;
        }
        int zzhx = zzhx();
        if (zzhx != zziw.zzhx()) {
            return entrySet().equals(zziw.entrySet());
        }
        for (int i = 0; i < zzhx; i++) {
            if (!zzbv(i).equals(zziw.zzbv(i))) {
                return false;
            }
        }
        if (zzhx != size) {
            return this.zzaad.equals(zziw.zzaad);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzhx(); i2++) {
            i += ((zzjb) this.zzaac.get(i2)).hashCode();
        }
        if (this.zzaad.size() > 0) {
            return i + this.zzaad.hashCode();
        }
        return i;
    }

    /* synthetic */ zziw(int i, zziv zziv) {
        this(i);
    }
}
