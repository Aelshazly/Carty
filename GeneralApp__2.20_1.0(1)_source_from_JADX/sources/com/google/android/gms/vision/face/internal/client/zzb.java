package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.internal.vision.zzbe;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public final class zzb extends zzn<zzh> {
    private final zzf zzcz;

    public zzb(Context context, zzf zzf) {
        super(context, "FaceNativeHandle", "face");
        zzbe.init(context);
        this.zzcz = zzf;
        zzp();
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzp zzp) {
        Face[] faceArr;
        FaceParcel[] faceParcelArr;
        Landmark[] landmarkArr;
        Contour[] contourArr;
        int i = 0;
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zzh) zzp()).zzc(ObjectWrapper.wrap(byteBuffer), zzp);
            Face[] faceArr2 = new Face[zzc.length];
            int i2 = 0;
            while (i2 < zzc.length) {
                FaceParcel faceParcel = zzc[i2];
                int i3 = faceParcel.f70id;
                PointF pointF = new PointF(faceParcel.centerX, faceParcel.centerY);
                float f = faceParcel.width;
                float f2 = faceParcel.height;
                float f3 = faceParcel.zzda;
                float f4 = faceParcel.zzdb;
                float f5 = faceParcel.zzdc;
                LandmarkParcel[] landmarkParcelArr = faceParcel.zzdd;
                if (landmarkParcelArr == null) {
                    faceParcelArr = zzc;
                    faceArr = faceArr2;
                    landmarkArr = new Landmark[i];
                } else {
                    landmarkArr = new Landmark[landmarkParcelArr.length];
                    int i4 = 0;
                    while (i4 < landmarkParcelArr.length) {
                        LandmarkParcel landmarkParcel = landmarkParcelArr[i4];
                        FaceParcel[] faceParcelArr2 = zzc;
                        LandmarkParcel[] landmarkParcelArr2 = landmarkParcelArr;
                        Face[] faceArr3 = faceArr2;
                        landmarkArr[i4] = new Landmark(new PointF(landmarkParcel.f71x, landmarkParcel.f72y), landmarkParcel.type);
                        i4++;
                        zzc = faceParcelArr2;
                        faceArr2 = faceArr3;
                        landmarkParcelArr = landmarkParcelArr2;
                    }
                    faceParcelArr = zzc;
                    faceArr = faceArr2;
                }
                zza[] zzaArr = faceParcel.zzde;
                if (zzaArr == null) {
                    contourArr = new Contour[0];
                } else {
                    Contour[] contourArr2 = new Contour[zzaArr.length];
                    for (int i5 = 0; i5 < zzaArr.length; i5++) {
                        zza zza = zzaArr[i5];
                        contourArr2[i5] = new Contour(zza.zzcy, zza.type);
                    }
                    contourArr = contourArr2;
                }
                Face face = new Face(i3, pointF, f, f2, f3, f4, f5, landmarkArr, contourArr, faceParcel.zzcf, faceParcel.zzcg, faceParcel.zzch, faceParcel.zzci);
                faceArr[i2] = face;
                i2++;
                zzc = faceParcelArr;
                faceArr2 = faceArr;
                i = 0;
            }
            return faceArr2;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    public final boolean zzd(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zzh) zzp()).zzd(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzn() throws RemoteException {
        ((zzh) zzp()).zzm();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException {
        zzi zzi;
        if (zzr.zza(context, "com.google.android.gms.vision.dynamite.face")) {
            zzi = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            zzi = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        if (zzi == null) {
            return null;
        }
        return zzi.newFaceDetector(ObjectWrapper.wrap(context), this.zzcz);
    }
}
