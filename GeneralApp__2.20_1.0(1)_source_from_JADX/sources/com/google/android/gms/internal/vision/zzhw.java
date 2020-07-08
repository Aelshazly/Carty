package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzhw<K, V> extends LinkedHashMap<K, V> {
    private static final zzhw zzyv;
    private boolean zzrp = true;

    private zzhw() {
    }

    private zzhw(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzhw<K, V> zzhc() {
        return zzyv;
    }

    public final void zza(zzhw<K, V> zzhw) {
        zzhe();
        if (!zzhw.isEmpty()) {
            putAll(zzhw);
        }
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final void clear() {
        zzhe();
        super.clear();
    }

    public final V put(K k, V v) {
        zzhe();
        zzgt.checkNotNull(k);
        zzgt.checkNotNull(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzhe();
        for (Object next : map.keySet()) {
            zzgt.checkNotNull(next);
            zzgt.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzhe();
        return super.remove(obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof java.util.Map
            r1 = 0
            if (r0 == 0) goto L_0x0060
            java.util.Map r7 = (java.util.Map) r7
            r0 = 1
            if (r6 == r7) goto L_0x005c
            int r2 = r6.size()
            int r3 = r7.size()
            if (r2 == r3) goto L_0x0016
            r7 = 0
            goto L_0x005d
        L_0x0016:
            java.util.Set r2 = r6.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x001e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x005c
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            boolean r4 = r7.containsKey(r4)
            if (r4 != 0) goto L_0x0036
            r7 = 0
            goto L_0x005d
        L_0x0036:
            java.lang.Object r4 = r3.getValue()
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r7.get(r3)
            boolean r5 = r4 instanceof byte[]
            if (r5 == 0) goto L_0x0053
            boolean r5 = r3 instanceof byte[]
            if (r5 == 0) goto L_0x0053
            byte[] r4 = (byte[]) r4
            byte[] r3 = (byte[]) r3
            boolean r3 = java.util.Arrays.equals(r4, r3)
            goto L_0x0057
        L_0x0053:
            boolean r3 = r4.equals(r3)
        L_0x0057:
            if (r3 != 0) goto L_0x005b
            r7 = 0
            goto L_0x005d
        L_0x005b:
            goto L_0x001e
        L_0x005c:
            r7 = 1
        L_0x005d:
            if (r7 == 0) goto L_0x0060
            return r0
        L_0x0060:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhw.equals(java.lang.Object):boolean");
    }

    private static int zzr(Object obj) {
        if (obj instanceof byte[]) {
            return zzgt.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzgw)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        int i = 0;
        for (Entry entry : entrySet()) {
            i += zzr(entry.getValue()) ^ zzr(entry.getKey());
        }
        return i;
    }

    public final zzhw<K, V> zzhd() {
        return isEmpty() ? new zzhw<>() : new zzhw<>(this);
    }

    public final void zzdp() {
        this.zzrp = false;
    }

    public final boolean isMutable() {
        return this.zzrp;
    }

    private final void zzhe() {
        if (!this.zzrp) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzhw zzhw = new zzhw();
        zzyv = zzhw;
        zzhw.zzrp = false;
    }
}
