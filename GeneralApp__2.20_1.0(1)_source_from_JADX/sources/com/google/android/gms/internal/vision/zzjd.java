package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
class zzjd extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zziw zzaah;

    private zzjd(zziw zziw) {
        this.zzaah = zziw;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzje(this.zzaah, null);
    }

    public int size() {
        return this.zzaah.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzaah.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzaah.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzaah.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzaah.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzjd(zziw zziw, zziv zziv) {
        this(zziw);
    }
}
