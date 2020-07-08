package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzhk extends zzex<String> implements zzhj, RandomAccess {
    private static final zzhk zzyh;
    private static final zzhj zzyi = zzyh;
    private final List<Object> zzyj;

    public zzhk() {
        this(10);
    }

    public zzhk(int i) {
        this(new ArrayList<>(i));
    }

    private zzhk(ArrayList<Object> arrayList) {
        this.zzyj = arrayList;
    }

    public final int size() {
        return this.zzyj.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzdq();
        if (collection instanceof zzhj) {
            collection = ((zzhj) collection).zzgx();
        }
        boolean addAll = this.zzyj.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzdq();
        this.zzyj.clear();
        this.modCount++;
    }

    public final void zzc(zzfh zzfh) {
        zzdq();
        this.zzyj.add(zzfh);
        this.modCount++;
    }

    public final Object getRaw(int i) {
        return this.zzyj.get(i);
    }

    private static String zzk(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            return ((zzfh) obj).zzer();
        }
        return zzgt.zzh((byte[]) obj);
    }

    public final List<?> zzgx() {
        return Collections.unmodifiableList(this.zzyj);
    }

    public final zzhj zzgy() {
        if (zzdo()) {
            return new zzjo(this);
        }
        return this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzdq();
        return zzk(this.zzyj.set(i, str));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        Object remove = this.zzyj.remove(i);
        this.modCount++;
        return zzk(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzdo() {
        return super.zzdo();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzdq();
        this.zzyj.add(i, str);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzyj);
            return new zzhk(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzyj.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            zzfh zzfh = (zzfh) obj;
            String zzer = zzfh.zzer();
            if (zzfh.zzes()) {
                this.zzyj.set(i, zzer);
            }
            return zzer;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzgt.zzh(bArr);
        if (zzgt.zzg(bArr)) {
            this.zzyj.set(i, zzh);
        }
        return zzh;
    }

    static {
        zzhk zzhk = new zzhk();
        zzyh = zzhk;
        zzhk.zzdp();
    }
}
