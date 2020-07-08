package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode.CalendarDateTime;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzf implements Creator<CalendarEvent> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new CalendarEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        CalendarDateTime calendarDateTime = null;
        CalendarDateTime calendarDateTime2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    calendarDateTime = (CalendarDateTime) SafeParcelReader.createParcelable(parcel, readHeader, CalendarDateTime.CREATOR);
                    break;
                case 8:
                    calendarDateTime2 = (CalendarDateTime) SafeParcelReader.createParcelable(parcel, readHeader, CalendarDateTime.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        CalendarEvent calendarEvent = new CalendarEvent(str, str2, str3, str4, str5, calendarDateTime, calendarDateTime2);
        return calendarEvent;
    }
}
