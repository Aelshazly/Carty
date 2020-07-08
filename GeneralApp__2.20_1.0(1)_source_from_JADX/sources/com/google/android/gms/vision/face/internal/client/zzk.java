package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzb;
import com.google.android.gms.internal.vision.zzd;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzk extends zzb implements zzi {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    public final zzh newFaceDetector(IObjectWrapper iObjectWrapper, zzf zzf) throws RemoteException {
        zzh zzh;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzd.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzd.zza(obtainAndWriteInterfaceToken, (Parcelable) zzf);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzh = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            if (queryLocalInterface instanceof zzh) {
                zzh = (zzh) queryLocalInterface;
            } else {
                zzh = new zzj(readStrongBinder);
            }
        }
        zza.recycle();
        return zzh;
    }
}
