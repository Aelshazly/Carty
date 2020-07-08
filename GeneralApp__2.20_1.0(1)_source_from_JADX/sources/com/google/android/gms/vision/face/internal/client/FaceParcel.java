package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public class FaceParcel extends AbstractSafeParcelable {
    public static final Creator<FaceParcel> CREATOR = new zzd();
    public final float centerX;
    public final float centerY;
    public final float height;

    /* renamed from: id */
    public final int f70id;
    private final int versionCode;
    public final float width;
    public final float zzcf;
    public final float zzcg;
    public final float zzch;
    public final float zzci;
    public final float zzda;
    public final float zzdb;
    public final float zzdc;
    public final LandmarkParcel[] zzdd;
    public final zza[] zzde;

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, LandmarkParcel[] landmarkParcelArr, float f8, float f9, float f10, zza[] zzaArr, float f11) {
        this.versionCode = i;
        this.f70id = i2;
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.zzda = f5;
        this.zzdb = f6;
        this.zzdc = f7;
        this.zzdd = landmarkParcelArr;
        this.zzcf = f8;
        this.zzcg = f9;
        this.zzch = f10;
        this.zzde = zzaArr;
        this.zzci = f11;
    }

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, LandmarkParcel[] landmarkParcelArr, float f7, float f8, float f9) {
        this(i, i2, f, f2, f3, f4, f5, f6, 0.0f, landmarkParcelArr, f7, f8, f9, new zza[0], -1.0f);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.f70id);
        SafeParcelWriter.writeFloat(parcel, 3, this.centerX);
        SafeParcelWriter.writeFloat(parcel, 4, this.centerY);
        SafeParcelWriter.writeFloat(parcel, 5, this.width);
        SafeParcelWriter.writeFloat(parcel, 6, this.height);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzda);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzdb);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzdd, i, false);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzcf);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzcg);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzch);
        SafeParcelWriter.writeTypedArray(parcel, 13, this.zzde, i, false);
        SafeParcelWriter.writeFloat(parcel, 14, this.zzdc);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzci);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
