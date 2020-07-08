package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public class DynamicLinkData extends AbstractSafeParcelable {
    public static final Creator<DynamicLinkData> CREATOR = new zza();
    private String zzj;
    private String zzk;
    private int zzl;
    private long zzm = 0;
    private Bundle zzn = null;
    private Uri zzo;

    public final String zzd() {
        return this.zzk;
    }

    public final int zze() {
        return this.zzl;
    }

    public final long getClickTimestamp() {
        return this.zzm;
    }

    public final void zza(long j) {
        this.zzm = j;
    }

    public final Bundle zzf() {
        Bundle bundle = this.zzn;
        return bundle == null ? new Bundle() : bundle;
    }

    public final Uri zzc() {
        return this.zzo;
    }

    public DynamicLinkData(String str, String str2, int i, long j, Bundle bundle, Uri uri) {
        this.zzj = str;
        this.zzk = str2;
        this.zzl = i;
        this.zzm = j;
        this.zzn = bundle;
        this.zzo = uri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzl);
        SafeParcelWriter.writeLong(parcel, 4, this.zzm);
        SafeParcelWriter.writeBundle(parcel, 5, zzf(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzo, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
