package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import java.util.List;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class zzo extends AbstractSafeParcelable implements ShortDynamicLink {
    public static final Creator<zzo> CREATOR = new zzq();
    private final Uri zzu;
    private final Uri zzv;
    private final List<zzr> zzw;

    public zzo(Uri uri, Uri uri2, List<zzr> list) {
        this.zzu = uri;
        this.zzv = uri2;
        this.zzw = list;
    }

    public final Uri getShortLink() {
        return this.zzu;
    }

    public final Uri getPreviewLink() {
        return this.zzv;
    }

    public final List<zzr> getWarnings() {
        return this.zzw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getShortLink(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getPreviewLink(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getWarnings(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
