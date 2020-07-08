package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzaj extends AbstractSafeParcelable {
    public static final Creator<zzaj> CREATOR = new zzam();
    private final float zzdp;
    public final String zzed;
    public final zzw zzej;
    private final zzw zzek;
    public final String zzem;
    private final zzag[] zzes;
    private final boolean zzet;

    public zzaj(zzag[] zzagArr, zzw zzw, zzw zzw2, String str, float f, String str2, boolean z) {
        this.zzes = zzagArr;
        this.zzej = zzw;
        this.zzek = zzw2;
        this.zzem = str;
        this.zzdp = f;
        this.zzed = str2;
        this.zzet = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzes, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzej, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzek, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzem, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzdp);
        SafeParcelWriter.writeString(parcel, 7, this.zzed, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzet);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
