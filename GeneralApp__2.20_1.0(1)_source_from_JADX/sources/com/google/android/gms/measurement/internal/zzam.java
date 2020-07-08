package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public final class zzam extends AbstractSafeParcelable implements Iterable<String> {
    public static final Creator<zzam> CREATOR = new zzao();
    /* access modifiers changed from: private */
    public final Bundle zza;

    zzam(Bundle bundle) {
        this.zza = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzb(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(String str) {
        return this.zza.get(str);
    }

    /* access modifiers changed from: 0000 */
    public final Long zzb(String str) {
        return Long.valueOf(this.zza.getLong(str));
    }

    /* access modifiers changed from: 0000 */
    public final Double zzc(String str) {
        return Double.valueOf(this.zza.getDouble(str));
    }

    /* access modifiers changed from: 0000 */
    public final String zzd(String str) {
        return this.zza.getString(str);
    }

    public final int zza() {
        return this.zza.size();
    }

    public final String toString() {
        return this.zza.toString();
    }

    public final Bundle zzb() {
        return new Bundle(this.zza);
    }

    public final Iterator<String> iterator() {
        return new zzal(this);
    }
}
