package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzdg<K, V> implements Serializable, Map<K, V> {
    private static final Entry<?, ?>[] zzls = new Entry[0];
    private transient zzdj<Entry<K, V>> zzlt;
    private transient zzdj<K> zzlu;
    private transient zzdc<V> zzlv;

    public static <K, V> zzdg<K, V> zza(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        zzda.zza(k, v);
        zzda.zza(k2, v2);
        zzda.zza(k3, v3);
        zzda.zza(k4, v4);
        return zzdl.zza(4, new Object[]{k, v, k2, v2, k3, v3, k4, v4});
    }

    public abstract V get(@NullableDecl Object obj);

    /* access modifiers changed from: 0000 */
    public abstract zzdj<Entry<K, V>> zzce();

    /* access modifiers changed from: 0000 */
    public abstract zzdj<K> zzcf();

    /* access modifiers changed from: 0000 */
    public abstract zzdc<V> zzcg();

    zzdg() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzdc) values()).contains(obj);
    }

    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public int hashCode() {
        return zzdo.zza((zzdj) entrySet());
    }

    public String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append('}');
            return sb.toString();
        }
        String str = "size";
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 40);
        sb2.append(str);
        sb2.append(" cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    public /* synthetic */ Set entrySet() {
        zzdj<Entry<K, V>> zzdj = this.zzlt;
        if (zzdj != null) {
            return zzdj;
        }
        zzdj<Entry<K, V>> zzce = zzce();
        this.zzlt = zzce;
        return zzce;
    }

    public /* synthetic */ Collection values() {
        zzdc<V> zzdc = this.zzlv;
        if (zzdc != null) {
            return zzdc;
        }
        zzdc<V> zzcg = zzcg();
        this.zzlv = zzcg;
        return zzcg;
    }

    public /* synthetic */ Set keySet() {
        zzdj<K> zzdj = this.zzlu;
        if (zzdj != null) {
            return zzdj;
        }
        zzdj<K> zzcf = zzcf();
        this.zzlu = zzcf;
        return zzcf;
    }
}
