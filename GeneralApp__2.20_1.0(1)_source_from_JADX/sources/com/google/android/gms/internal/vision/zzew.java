package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzet;
import com.google.android.gms.internal.vision.zzew;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzew<MessageType extends zzet<MessageType, BuilderType>, BuilderType extends zzew<MessageType, BuilderType>> implements zzib {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzft zzft, zzgd zzgd) throws IOException;

    /* renamed from: zzdn */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2, zzgd zzgd) throws zzhc {
        try {
            zzft zza = zzft.zza(bArr, 0, i2, false);
            zza(zza, zzgd);
            zza.zzar(0);
            return this;
        } catch (zzhc e) {
            throw e;
        } catch (IOException e2) {
            String str = "byte array";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + String.valueOf(str).length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    public final /* synthetic */ zzib zza(zzic zzic) {
        if (zzgd().getClass().isInstance(zzic)) {
            return zza((MessageType) (zzet) zzic);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
