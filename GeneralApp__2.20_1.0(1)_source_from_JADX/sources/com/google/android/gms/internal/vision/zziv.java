package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zziv extends zziw<FieldDescriptorType, Object> {
    zziv(int i) {
        super(i, null);
    }

    public final void zzdp() {
        if (!isImmutable()) {
            for (int i = 0; i < zzhx(); i++) {
                Entry zzbv = zzbv(i);
                if (((zzgk) zzbv.getKey()).zzfu()) {
                    zzbv.setValue(Collections.unmodifiableList((List) zzbv.getValue()));
                }
            }
            for (Entry entry : zzhy()) {
                if (((zzgk) entry.getKey()).zzfu()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzdp();
    }
}
