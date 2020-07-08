package com.google.android.gms.vision.face.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzp;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public interface zzh extends IInterface {
    FaceParcel[] zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, int i, int i2, int i3, int i4, int i5, int i6, zzp zzp) throws RemoteException;

    FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzp zzp) throws RemoteException;

    boolean zzd(int i) throws RemoteException;

    void zzm() throws RemoteException;
}
