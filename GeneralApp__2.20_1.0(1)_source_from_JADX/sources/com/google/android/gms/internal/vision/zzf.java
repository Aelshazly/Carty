package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzf extends AbstractSafeParcelable {
    public static final Creator<zzf> CREATOR = new zze();
    public int zzbm;
    private boolean zzbn;

    public zzf() {
    }

    public zzf(int i, boolean z) {
        this.zzbm = i;
        this.zzbn = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzbm);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
