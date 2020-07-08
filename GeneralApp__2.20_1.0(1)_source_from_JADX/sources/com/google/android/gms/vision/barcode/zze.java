package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode.Address;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.PersonName;
import com.google.android.gms.vision.barcode.Barcode.Phone;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zze implements Creator<ContactInfo> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new ContactInfo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        PersonName personName = null;
        String str = null;
        String str2 = null;
        Phone[] phoneArr = null;
        Email[] emailArr = null;
        String[] strArr = null;
        Address[] addressArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    personName = (PersonName) SafeParcelReader.createParcelable(parcel, readHeader, PersonName.CREATOR);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    phoneArr = (Phone[]) SafeParcelReader.createTypedArray(parcel, readHeader, Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Email[]) SafeParcelReader.createTypedArray(parcel, readHeader, Email.CREATOR);
                    break;
                case 7:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 8:
                    addressArr = (Address[]) SafeParcelReader.createTypedArray(parcel, readHeader, Address.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        ContactInfo contactInfo = new ContactInfo(personName, str, str2, phoneArr, emailArr, strArr, addressArr);
        return contactInfo;
    }
}
