package com.google.firebase.dynamiclinks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_dynamic_links.zzb;
import com.google.android.gms.internal.firebase_dynamic_links.zzd;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class zzp extends zzb implements zzm {
    zzp(IBinder iBinder) {
        super(iBinder, "com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
    }

    public final void zza(zzk zzk, String str) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzk);
        zza.writeString(str);
        zza(1, zza);
    }

    public final void zza(zzk zzk, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzk);
        zzd.zza(zza, (Parcelable) bundle);
        zza(2, zza);
    }
}
