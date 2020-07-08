package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzac extends AbstractSafeParcelable {
    public static final Creator<zzac> CREATOR = new zzab();
    private final float zzdp;
    public final String zzed;
    public final zzaj[] zzei;
    public final zzw zzej;
    private final zzw zzek;
    private final zzw zzel;
    public final String zzem;
    private final int zzen;
    public final boolean zzeo;
    public final int zzep;
    public final int zzeq;

    public zzac(zzaj[] zzajArr, zzw zzw, zzw zzw2, zzw zzw3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.zzei = zzajArr;
        this.zzej = zzw;
        this.zzek = zzw2;
        this.zzel = zzw3;
        this.zzem = str;
        this.zzdp = f;
        this.zzed = str2;
        this.zzen = i;
        this.zzeo = z;
        this.zzep = i2;
        this.zzeq = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzei, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzej, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzek, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzel, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzem, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzdp);
        SafeParcelWriter.writeString(parcel, 8, this.zzed, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzen);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzeo);
        SafeParcelWriter.writeInt(parcel, 11, this.zzep);
        SafeParcelWriter.writeInt(parcel, 12, this.zzeq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
