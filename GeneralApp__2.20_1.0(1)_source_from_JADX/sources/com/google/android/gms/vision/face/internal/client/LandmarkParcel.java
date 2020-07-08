package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Creator<LandmarkParcel> CREATOR = new zzm();
    public final int type;
    private final int versionCode;

    /* renamed from: x */
    public final float f71x;

    /* renamed from: y */
    public final float f72y;

    public LandmarkParcel(int i, float f, float f2, int i2) {
        this.versionCode = i;
        this.f71x = f;
        this.f72y = f2;
        this.type = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeFloat(parcel, 2, this.f71x);
        SafeParcelWriter.writeFloat(parcel, 3, this.f72y);
        SafeParcelWriter.writeInt(parcel, 4, this.type);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
