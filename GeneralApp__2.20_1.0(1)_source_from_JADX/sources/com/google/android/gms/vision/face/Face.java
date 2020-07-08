package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public class Face {
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;
    private float height;

    /* renamed from: id */
    private int f69id;
    private float width;
    private PointF zzbz;
    private float zzca;
    private float zzcb;
    private float zzcc;
    private List<Landmark> zzcd;
    private final List<Contour> zzce;
    private float zzcf;
    private float zzcg;
    private float zzch;
    private final float zzci;

    public PointF getPosition() {
        return new PointF(this.zzbz.x - (this.width / 2.0f), this.zzbz.y - (this.height / 2.0f));
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getEulerY() {
        return this.zzca;
    }

    public float getEulerZ() {
        return this.zzcb;
    }

    public List<Landmark> getLandmarks() {
        return this.zzcd;
    }

    public List<Contour> getContours() {
        return this.zzce;
    }

    public float getIsLeftEyeOpenProbability() {
        return this.zzcf;
    }

    public float getIsRightEyeOpenProbability() {
        return this.zzcg;
    }

    public float getIsSmilingProbability() {
        return this.zzch;
    }

    public int getId() {
        return this.f69id;
    }

    public Face(int i, PointF pointF, float f, float f2, float f3, float f4, float f5, Landmark[] landmarkArr, Contour[] contourArr, float f6, float f7, float f8, float f9) {
        this.f69id = i;
        this.zzbz = pointF;
        this.width = f;
        this.height = f2;
        this.zzca = f3;
        this.zzcb = f4;
        this.zzcc = f5;
        this.zzcd = Arrays.asList(landmarkArr);
        this.zzce = Arrays.asList(contourArr);
        this.zzcf = zza(f6);
        this.zzcg = zza(f7);
        this.zzch = zza(f8);
        this.zzci = zza(f9);
    }

    private static float zza(float f) {
        if (f < 0.0f || f > 1.0f) {
            return -1.0f;
        }
        return f;
    }
}
