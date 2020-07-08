package com.google.firebase.dynamiclinks.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.dynamiclinks.ShortDynamicLink.Warning;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class zzr extends AbstractSafeParcelable implements Warning {
    public static final Creator<zzr> CREATOR = new zzs();
    private final String zzx;

    public zzr(String str) {
        this.zzx = str;
    }

    public final String getCode() {
        return null;
    }

    public final String getMessage() {
        return this.zzx;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
