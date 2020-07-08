package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zze;
import com.google.android.gms.internal.vision.zzgs.zzg;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgh extends zzgf<zzd> {
    zzgh() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzic zzic) {
        return zzic instanceof zze;
    }

    /* access modifiers changed from: 0000 */
    public final zzgi<zzd> zzf(Object obj) {
        return ((zze) obj).zzwq;
    }

    /* access modifiers changed from: 0000 */
    public final zzgi<zzd> zzg(Object obj) {
        return ((zze) obj).zzgk();
    }

    /* access modifiers changed from: 0000 */
    public final void zzh(Object obj) {
        zzf(obj).zzdp();
    }

    /* access modifiers changed from: 0000 */
    public final <UT, UB> UB zza(zzis zzis, Object obj, zzgd zzgd, zzgi<zzd> zzgi, UB ub, zzjj<UT, UB> zzjj) throws IOException {
        ArrayList arrayList;
        zzg zzg = (zzg) obj;
        int i = zzg.zzxh.number;
        if (!zzg.zzxh.zzwo || !zzg.zzxh.zzwp) {
            Object obj2 = null;
            if (zzg.zzxh.zzwn != zzka.ENUM) {
                switch (zzg.zzxh.zzwn) {
                    case DOUBLE:
                        obj2 = Double.valueOf(zzis.readDouble());
                        break;
                    case FLOAT:
                        obj2 = Float.valueOf(zzis.readFloat());
                        break;
                    case INT64:
                        obj2 = Long.valueOf(zzis.zzdx());
                        break;
                    case UINT64:
                        obj2 = Long.valueOf(zzis.zzdw());
                        break;
                    case INT32:
                        obj2 = Integer.valueOf(zzis.zzdy());
                        break;
                    case FIXED64:
                        obj2 = Long.valueOf(zzis.zzdz());
                        break;
                    case FIXED32:
                        obj2 = Integer.valueOf(zzis.zzea());
                        break;
                    case BOOL:
                        obj2 = Boolean.valueOf(zzis.zzeb());
                        break;
                    case UINT32:
                        obj2 = Integer.valueOf(zzis.zzee());
                        break;
                    case SFIXED32:
                        obj2 = Integer.valueOf(zzis.zzeg());
                        break;
                    case SFIXED64:
                        obj2 = Long.valueOf(zzis.zzeh());
                        break;
                    case SINT32:
                        obj2 = Integer.valueOf(zzis.zzei());
                        break;
                    case SINT64:
                        obj2 = Long.valueOf(zzis.zzej());
                        break;
                    case ENUM:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case BYTES:
                        obj2 = zzis.zzed();
                        break;
                    case STRING:
                        obj2 = zzis.readString();
                        break;
                    case GROUP:
                        obj2 = zzis.zzb(zzg.zzxg.getClass(), zzgd);
                        break;
                    case MESSAGE:
                        obj2 = zzis.zza(zzg.zzxg.getClass(), zzgd);
                        break;
                }
            } else {
                int zzdy = zzis.zzdy();
                if (zzg.zzxh.zzwm.zzh(zzdy) == null) {
                    return zzit.zza(i, zzdy, ub, zzjj);
                }
                obj2 = Integer.valueOf(zzdy);
            }
            if (zzg.zzxh.zzwo) {
                zzgi.zzb(zzg.zzxh, obj2);
            } else {
                int i2 = zzgg.zzrx[zzg.zzxh.zzwn.ordinal()];
                if (i2 == 17 || i2 == 18) {
                    Object zza = zzgi.zza(zzg.zzxh);
                    if (zza != null) {
                        obj2 = zzgt.zzb(zza, obj2);
                    }
                }
                zzgi.zza(zzg.zzxh, obj2);
            }
        } else {
            switch (zzg.zzxh.zzwn) {
                case DOUBLE:
                    arrayList = new ArrayList();
                    zzis.zza(arrayList);
                    break;
                case FLOAT:
                    arrayList = new ArrayList();
                    zzis.zzb(arrayList);
                    break;
                case INT64:
                    arrayList = new ArrayList();
                    zzis.zzd(arrayList);
                    break;
                case UINT64:
                    arrayList = new ArrayList();
                    zzis.zzc(arrayList);
                    break;
                case INT32:
                    arrayList = new ArrayList();
                    zzis.zze(arrayList);
                    break;
                case FIXED64:
                    arrayList = new ArrayList();
                    zzis.zzf(arrayList);
                    break;
                case FIXED32:
                    arrayList = new ArrayList();
                    zzis.zzg(arrayList);
                    break;
                case BOOL:
                    arrayList = new ArrayList();
                    zzis.zzh(arrayList);
                    break;
                case UINT32:
                    arrayList = new ArrayList();
                    zzis.zzk(arrayList);
                    break;
                case SFIXED32:
                    arrayList = new ArrayList();
                    zzis.zzm(arrayList);
                    break;
                case SFIXED64:
                    arrayList = new ArrayList();
                    zzis.zzn(arrayList);
                    break;
                case SINT32:
                    arrayList = new ArrayList();
                    zzis.zzo(arrayList);
                    break;
                case SINT64:
                    arrayList = new ArrayList();
                    zzis.zzp(arrayList);
                    break;
                case ENUM:
                    arrayList = new ArrayList();
                    zzis.zzl(arrayList);
                    ub = zzit.zza(i, (List<Integer>) arrayList, zzg.zzxh.zzwm, ub, zzjj);
                    break;
                default:
                    String valueOf = String.valueOf(zzg.zzxh.zzwn);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
                    sb.append("Type cannot be packed: ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
            }
            zzgi.zza(zzg.zzxh, (Object) arrayList);
        }
        return ub;
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        return ((zzd) entry.getKey()).number;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzkg zzkg, Entry<?, ?> entry) throws IOException {
        zzd zzd = (zzd) entry.getKey();
        if (zzd.zzwo) {
            switch (zzd.zzwn) {
                case DOUBLE:
                    zzit.zza(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case FLOAT:
                    zzit.zzb(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case INT64:
                    zzit.zzc(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case UINT64:
                    zzit.zzd(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case INT32:
                    zzit.zzh(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case FIXED64:
                    zzit.zzf(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case FIXED32:
                    zzit.zzk(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case BOOL:
                    zzit.zzn(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case UINT32:
                    zzit.zzi(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case SFIXED32:
                    zzit.zzl(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case SFIXED64:
                    zzit.zzg(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case SINT32:
                    zzit.zzj(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case SINT64:
                    zzit.zze(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case ENUM:
                    zzit.zzh(zzd.number, (List) entry.getValue(), zzkg, zzd.zzwp);
                    return;
                case BYTES:
                    zzit.zzb(zzd.number, (List) entry.getValue(), zzkg);
                    return;
                case STRING:
                    zzit.zza(zzd.number, (List) entry.getValue(), zzkg);
                    return;
                case GROUP:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        zzit.zzb(zzd.number, (List) entry.getValue(), zzkg, zzin.zzho().zzf(list.get(0).getClass()));
                    }
                    return;
                case MESSAGE:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        zzit.zza(zzd.number, (List) entry.getValue(), zzkg, zzin.zzho().zzf(list2.get(0).getClass()));
                        break;
                    }
            }
            return;
        }
        switch (zzd.zzwn) {
            case DOUBLE:
                zzkg.zza(zzd.number, ((Double) entry.getValue()).doubleValue());
                return;
            case FLOAT:
                zzkg.zza(zzd.number, ((Float) entry.getValue()).floatValue());
                return;
            case INT64:
                zzkg.zzi(zzd.number, ((Long) entry.getValue()).longValue());
                return;
            case UINT64:
                zzkg.zza(zzd.number, ((Long) entry.getValue()).longValue());
                return;
            case INT32:
                zzkg.zzg(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case FIXED64:
                zzkg.zzc(zzd.number, ((Long) entry.getValue()).longValue());
                return;
            case FIXED32:
                zzkg.zzj(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case BOOL:
                zzkg.zza(zzd.number, ((Boolean) entry.getValue()).booleanValue());
                return;
            case UINT32:
                zzkg.zzh(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED32:
                zzkg.zzq(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED64:
                zzkg.zzj(zzd.number, ((Long) entry.getValue()).longValue());
                return;
            case SINT32:
                zzkg.zzi(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case SINT64:
                zzkg.zzb(zzd.number, ((Long) entry.getValue()).longValue());
                return;
            case ENUM:
                zzkg.zzg(zzd.number, ((Integer) entry.getValue()).intValue());
                return;
            case BYTES:
                zzkg.zza(zzd.number, (zzfh) entry.getValue());
                return;
            case STRING:
                zzkg.zza(zzd.number, (String) entry.getValue());
                return;
            case GROUP:
                zzkg.zzb(zzd.number, entry.getValue(), zzin.zzho().zzf(entry.getValue().getClass()));
                return;
            case MESSAGE:
                zzkg.zza(zzd.number, entry.getValue(), zzin.zzho().zzf(entry.getValue().getClass()));
                break;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(zzgd zzgd, zzic zzic, int i) {
        return zzgd.zza(zzic, i);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzis zzis, Object obj, zzgd zzgd, zzgi<zzd> zzgi) throws IOException {
        zzg zzg = (zzg) obj;
        zzgi.zza(zzg.zzxh, zzis.zza(zzg.zzxg.getClass(), zzgd));
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfh zzfh, Object obj, zzgd zzgd, zzgi<zzd> zzgi) throws IOException {
        byte[] bArr;
        zzg zzg = (zzg) obj;
        zzic zzgb = zzg.zzxg.zzgj().zzgb();
        int size = zzfh.size();
        if (size == 0) {
            bArr = zzgt.zzxi;
        } else {
            byte[] bArr2 = new byte[size];
            zzfh.zza(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzfg zzfg = new zzfg(wrap, true);
            zzin.zzho().zzv(zzgb).zza(zzgb, zzfg, zzgd);
            zzgi.zza(zzg.zzxh, (Object) zzgb);
            if (zzfg.zzdu() != Integer.MAX_VALUE) {
                throw zzhc.zzgq();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }
}
