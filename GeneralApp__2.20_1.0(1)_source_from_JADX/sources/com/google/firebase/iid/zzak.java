package com.google.firebase.iid;

import android.os.Bundle;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final class zzak extends zzan<Void> {
    zzak(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            zza(null);
        } else {
            zza(new zzam(4, "Invalid response to one way request"));
        }
    }
}
