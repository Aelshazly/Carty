package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.3.0 */
public abstract class zzfe extends zzc implements zzfb {
    public zzfe() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzan) zzb.zza(parcel, zzan.CREATOR), (zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzkz) zzb.zza(parcel, zzkz.CREATOR), (zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzan) zzb.zza(parcel, zzan.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List zza = zza((zzm) zzb.zza(parcel, zzm.CREATOR), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzan) zzb.zza(parcel, zzan.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzv) zzb.zza(parcel, zzv.CREATOR), (zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zza((zzv) zzb.zza(parcel, zzv.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List zza3 = zza(parcel.readString(), parcel.readString(), zzb.zza(parcel), (zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                break;
            case 15:
                List zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                break;
            case 16:
                List zza5 = zza(parcel.readString(), parcel.readString(), (zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                break;
            case 17:
                List zza6 = zza(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zza6);
                break;
            case 18:
                zzd((zzm) zzb.zza(parcel, zzm.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
