package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzk extends zzb implements zzi {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final zzg zza(IObjectWrapper iObjectWrapper, zzf zzf) throws RemoteException {
        zzg zzg;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzd.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzd.zza(obtainAndWriteInterfaceToken, (Parcelable) zzf);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzg = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            if (queryLocalInterface instanceof zzg) {
                zzg = (zzg) queryLocalInterface;
            } else {
                zzg = new zzj(readStrongBinder);
            }
        }
        zza.recycle();
        return zzg;
    }
}
