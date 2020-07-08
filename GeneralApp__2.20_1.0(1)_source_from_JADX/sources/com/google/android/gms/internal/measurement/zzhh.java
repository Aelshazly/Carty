package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
final class zzhh extends zzhi<FieldDescriptorType, Object> {
    zzhh(int i) {
        super(i, null);
    }

    public final void zza() {
        if (!zzb()) {
            for (int i = 0; i < zzc(); i++) {
                Entry zzb = zzb(i);
                if (((zzey) zzb.getKey()).zzd()) {
                    zzb.setValue(Collections.unmodifiableList((List) zzb.getValue()));
                }
            }
            for (Entry entry : zzd()) {
                if (((zzey) entry.getKey()).zzd()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
