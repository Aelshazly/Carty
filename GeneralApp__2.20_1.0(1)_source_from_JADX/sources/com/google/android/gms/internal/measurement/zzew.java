package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzew<T extends zzey<T>> {
    private static final zzew zzd = new zzew(true);
    final zzhi<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzew() {
        this.zza = zzhi.zza(16);
    }

    private zzew(boolean z) {
        this(zzhi.zza(0));
        zzb();
    }

    private zzew(zzhi<T, Object> zzhi) {
        this.zza = zzhi;
        zzb();
    }

    public static <T extends zzey<T>> zzew<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzew)) {
            return false;
        }
        return this.zza.equals(((zzew) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzfu(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final Iterator<Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzfu(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzfp)) {
            return obj;
        }
        zzfp zzfp = (zzfp) obj;
        return zzfp.zza();
    }

    private final void zzb(T t, Object obj) {
        if (!t.zzd()) {
            zza(t.zzb(), obj);
            r7 = obj;
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzb(), obj2);
            }
            r7 = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (r7 instanceof zzfp) {
            this.zzc = true;
        }
        this.zza.put(t, r7);
    }

    private static void zza(zzim zzim, Object obj) {
        zzff.zza(obj);
        boolean z = true;
        switch (zzim.zza()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if (!(obj instanceof zzdu) && !(obj instanceof byte[])) {
                    z = false;
                    break;
                }
            case ENUM:
                if (!(obj instanceof Integer) && !(obj instanceof zzfi)) {
                    z = false;
                    break;
                }
            case MESSAGE:
                if (!(obj instanceof zzgo) && !(obj instanceof zzfp)) {
                    z = false;
                    break;
                }
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza(this.zza.zzb(i))) {
                return false;
            }
        }
        for (Entry zza2 : this.zza.zzd()) {
            if (!zza(zza2)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzey<T>> boolean zza(Entry<T, Object> entry) {
        zzey zzey = (zzey) entry.getKey();
        if (zzey.zzc() == zzip.MESSAGE) {
            if (zzey.zzd()) {
                for (zzgo zzbl : (List) entry.getValue()) {
                    if (!zzbl.zzbl()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgo) {
                    if (!((zzgo) value).zzbl()) {
                        return false;
                    }
                } else if (value instanceof zzfp) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzew<T> zzew) {
        for (int i = 0; i < zzew.zza.zzc(); i++) {
            zzb(zzew.zza.zzb(i));
        }
        for (Entry zzb2 : zzew.zza.zzd()) {
            zzb(zzb2);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzgt) {
            return ((zzgt) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Entry<T, Object> entry) {
        Object obj;
        zzey zzey = (zzey) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzfp) {
            zzfp zzfp = (zzfp) value;
            value = zzfp.zza();
        }
        if (zzey.zzd()) {
            Object zza2 = zza((T) zzey);
            if (zza2 == null) {
                zza2 = new ArrayList();
            }
            for (Object zza3 : (List) value) {
                ((List) zza2).add(zza(zza3));
            }
            this.zza.put(zzey, zza2);
        } else if (zzey.zzc() == zzip.MESSAGE) {
            Object zza4 = zza((T) zzey);
            if (zza4 == null) {
                this.zza.put(zzey, zza(value));
                return;
            }
            if (zza4 instanceof zzgt) {
                obj = zzey.zza((zzgt) zza4, (zzgt) value);
            } else {
                obj = zzey.zza(((zzgo) zza4).zzbr(), (zzgo) value).zzu();
            }
            this.zza.put(zzey, obj);
        } else {
            this.zza.put(zzey, zza(value));
        }
    }

    static void zza(zzen zzen, zzim zzim, int i, Object obj) throws IOException {
        if (zzim == zzim.GROUP) {
            zzgo zzgo = (zzgo) obj;
            zzff.zza(zzgo);
            zzen.zza(i, 3);
            zzgo.zza(zzen);
            zzen.zza(i, 4);
            return;
        }
        zzen.zza(i, zzim.zzb());
        switch (zzim) {
            case DOUBLE:
                zzen.zza(((Double) obj).doubleValue());
                return;
            case FLOAT:
                zzen.zza(((Float) obj).floatValue());
                return;
            case INT64:
                zzen.zza(((Long) obj).longValue());
                return;
            case UINT64:
                zzen.zza(((Long) obj).longValue());
                return;
            case INT32:
                zzen.zza(((Integer) obj).intValue());
                return;
            case FIXED64:
                zzen.zzc(((Long) obj).longValue());
                return;
            case FIXED32:
                zzen.zzd(((Integer) obj).intValue());
                return;
            case BOOL:
                zzen.zza(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                ((zzgo) obj).zza(zzen);
                return;
            case MESSAGE:
                zzen.zza((zzgo) obj);
                return;
            case STRING:
                if (obj instanceof zzdu) {
                    zzen.zza((zzdu) obj);
                    return;
                } else {
                    zzen.zza((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof zzdu) {
                    zzen.zza((zzdu) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzen.zzb(bArr, 0, bArr.length);
                return;
            case UINT32:
                zzen.zzb(((Integer) obj).intValue());
                return;
            case SFIXED32:
                zzen.zzd(((Integer) obj).intValue());
                return;
            case SFIXED64:
                zzen.zzc(((Long) obj).longValue());
                return;
            case SINT32:
                zzen.zzc(((Integer) obj).intValue());
                return;
            case SINT64:
                zzen.zzb(((Long) obj).longValue());
                return;
            case ENUM:
                if (!(obj instanceof zzfi)) {
                    zzen.zza(((Integer) obj).intValue());
                    break;
                } else {
                    zzen.zza(((zzfi) obj).zza());
                    return;
                }
        }
    }

    public final int zzg() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzc(); i2++) {
            i += zzc(this.zza.zzb(i2));
        }
        for (Entry zzc2 : this.zza.zzd()) {
            i += zzc(zzc2);
        }
        return i;
    }

    private static int zzc(Entry<T, Object> entry) {
        zzey zzey = (zzey) entry.getKey();
        Object value = entry.getValue();
        if (zzey.zzc() != zzip.MESSAGE || zzey.zzd() || zzey.zze()) {
            return zza(zzey, value);
        }
        if (value instanceof zzfp) {
            return zzen.zzb(((zzey) entry.getKey()).zza(), (zzft) (zzfp) value);
        }
        return zzen.zzb(((zzey) entry.getKey()).zza(), (zzgo) value);
    }

    static int zza(zzim zzim, int i, Object obj) {
        int zze = zzen.zze(i);
        if (zzim == zzim.GROUP) {
            zzff.zza((zzgo) obj);
            zze <<= 1;
        }
        return zze + zzb(zzim, obj);
    }

    private static int zzb(zzim zzim, Object obj) {
        switch (zzim) {
            case DOUBLE:
                return zzen.zzb(((Double) obj).doubleValue());
            case FLOAT:
                return zzen.zzb(((Float) obj).floatValue());
            case INT64:
                return zzen.zzd(((Long) obj).longValue());
            case UINT64:
                return zzen.zze(((Long) obj).longValue());
            case INT32:
                return zzen.zzf(((Integer) obj).intValue());
            case FIXED64:
                return zzen.zzg(((Long) obj).longValue());
            case FIXED32:
                return zzen.zzi(((Integer) obj).intValue());
            case BOOL:
                return zzen.zzb(((Boolean) obj).booleanValue());
            case GROUP:
                return zzen.zzc((zzgo) obj);
            case MESSAGE:
                if (obj instanceof zzfp) {
                    return zzen.zza((zzft) (zzfp) obj);
                }
                return zzen.zzb((zzgo) obj);
            case STRING:
                if (obj instanceof zzdu) {
                    return zzen.zzb((zzdu) obj);
                }
                return zzen.zzb((String) obj);
            case BYTES:
                if (obj instanceof zzdu) {
                    return zzen.zzb((zzdu) obj);
                }
                return zzen.zzb((byte[]) obj);
            case UINT32:
                return zzen.zzg(((Integer) obj).intValue());
            case SFIXED32:
                return zzen.zzj(((Integer) obj).intValue());
            case SFIXED64:
                return zzen.zzh(((Long) obj).longValue());
            case SINT32:
                return zzen.zzh(((Integer) obj).intValue());
            case SINT64:
                return zzen.zzf(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof zzfi) {
                    return zzen.zzk(((zzfi) obj).zza());
                }
                return zzen.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzey<?> zzey, Object obj) {
        zzim zzb2 = zzey.zzb();
        int zza2 = zzey.zza();
        if (!zzey.zzd()) {
            return zza(zzb2, zza2, obj);
        }
        int i = 0;
        if (zzey.zze()) {
            for (Object zzb3 : (List) obj) {
                i += zzb(zzb2, zzb3);
            }
            return zzen.zze(zza2) + i + zzen.zzl(i);
        }
        for (Object zza3 : (List) obj) {
            i += zza(zzb2, zza2, zza3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzew zzew = new zzew();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Entry zzb2 = this.zza.zzb(i);
            zzew.zzb((T) (zzey) zzb2.getKey(), zzb2.getValue());
        }
        for (Entry entry : this.zza.zzd()) {
            zzew.zzb((T) (zzey) entry.getKey(), entry.getValue());
        }
        zzew.zzc = this.zzc;
        return zzew;
    }
}
