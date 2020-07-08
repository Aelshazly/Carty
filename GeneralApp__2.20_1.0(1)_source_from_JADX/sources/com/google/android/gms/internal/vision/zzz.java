package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzz extends zzb implements zzaa {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
    }

    public final zzy zza(IObjectWrapper iObjectWrapper, zzah zzah) throws RemoteException {
        zzy zzy;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzd.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzd.zza(obtainAndWriteInterfaceToken, (Parcelable) zzah);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            if (queryLocalInterface instanceof zzy) {
                zzy = (zzy) queryLocalInterface;
            } else {
                zzy = new zzx(readStrongBinder);
            }
        }
        zza.recycle();
        return zzy;
    }
}
