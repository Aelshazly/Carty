package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs.zzf;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzip implements zzia {
    private final int flags;
    private final String info;
    private final Object[] zzzb;
    private final zzic zzze;

    zzip(zzic zzic, String str, Object[] objArr) {
        this.zzze = zzic;
        this.info = str;
        this.zzzb = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zzhp() {
        return this.info;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzhq() {
        return this.zzzb;
    }

    public final zzic zzhk() {
        return this.zzze;
    }

    public final int zzhi() {
        return (this.flags & 1) == 1 ? zzf.zzwz : zzf.zzxa;
    }

    public final boolean zzhj() {
        return (this.flags & 2) == 2;
    }
}
