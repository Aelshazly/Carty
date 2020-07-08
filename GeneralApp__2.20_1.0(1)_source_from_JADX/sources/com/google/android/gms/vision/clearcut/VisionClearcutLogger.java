package com.google.android.gms.vision.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.internal.vision.zzds;
import com.google.android.gms.internal.vision.zzea.zzo;
import com.google.android.gms.internal.vision.zzea.zzo.zza;
import com.google.android.gms.internal.vision.zzgd;
import com.google.android.gms.vision.C0548L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class VisionClearcutLogger {
    private final ClearcutLogger zzbw;
    private boolean zzbx = true;

    public VisionClearcutLogger(Context context) {
        this.zzbw = new ClearcutLogger(context, "VISION", null);
    }

    public final void zzb(int i, zzo zzo) {
        byte[] byteArray = zzo.toByteArray();
        if (i < 0 || i > 3) {
            C0548L.m65i("Illegal event code: %d", Integer.valueOf(i));
            return;
        }
        try {
            if (this.zzbx) {
                this.zzbw.newEvent(byteArray).setEventCode(i).log();
                return;
            }
            zza zzdi = zzo.zzdi();
            try {
                zzdi.zza(byteArray, 0, byteArray.length, zzgd.zzfm());
                C0548L.m63e("Would have logged:\n%s", zzdi.toString());
            } catch (Exception e) {
                C0548L.m64e(e, "Parsing error", new Object[0]);
            }
        } catch (Exception e2) {
            zzds.zza(e2);
            C0548L.m64e(e2, "Failed to log", new Object[0]);
        }
    }
}
