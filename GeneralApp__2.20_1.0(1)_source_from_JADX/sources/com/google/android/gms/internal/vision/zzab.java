package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzab implements Creator<zzac> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzac[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzaj[] zzajArr = null;
        zzw zzw = null;
        zzw zzw2 = null;
        zzw zzw3 = null;
        String str = null;
        String str2 = null;
        float f = 0.0f;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    zzajArr = (zzaj[]) SafeParcelReader.createTypedArray(parcel2, readHeader, zzaj.CREATOR);
                    break;
                case 3:
                    zzw = (zzw) SafeParcelReader.createParcelable(parcel2, readHeader, zzw.CREATOR);
                    break;
                case 4:
                    zzw2 = (zzw) SafeParcelReader.createParcelable(parcel2, readHeader, zzw.CREATOR);
                    break;
                case 5:
                    zzw3 = (zzw) SafeParcelReader.createParcelable(parcel2, readHeader, zzw.CREATOR);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 8:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 9:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        zzac zzac = new zzac(zzajArr, zzw, zzw2, zzw3, str, f, str2, i, z, i2, i3);
        return zzac;
    }
}
