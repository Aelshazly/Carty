package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzam implements Creator<zzaj> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaj[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzag[] zzagArr = null;
        zzw zzw = null;
        zzw zzw2 = null;
        String str = null;
        String str2 = null;
        float f = 0.0f;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    zzagArr = (zzag[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzag.CREATOR);
                    break;
                case 3:
                    zzw = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    break;
                case 4:
                    zzw2 = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    break;
                case 5:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        zzaj zzaj = new zzaj(zzagArr, zzw, zzw2, str, f, str2, z);
        return zzaj;
    }
}
