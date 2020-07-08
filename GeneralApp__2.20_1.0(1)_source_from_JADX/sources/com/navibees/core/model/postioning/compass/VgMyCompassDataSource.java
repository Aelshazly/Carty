package com.navibees.core.model.postioning.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.navibees.core.interfaces.CompassChangedListener;
import com.navibees.core.model.postioning.compass.VgMyQuaternion.Euler.Order;

public class VgMyCompassDataSource implements SensorEventListener {

    /* renamed from: n */
    private static final float f1451n = 1.0E-6f;

    /* renamed from: o */
    private static final float f1452o = 1.0E-12f;

    /* renamed from: a */
    private SensorManager f1453a;

    /* renamed from: b */
    private Sensor f1454b;

    /* renamed from: c */
    private CompassChangedListener f1455c;

    /* renamed from: d */
    private long f1456d;

    /* renamed from: e */
    private VgMyVector3 f1457e = new VgMyVector3();

    /* renamed from: f */
    private VgMyQuaternion f1458f = new VgMyQuaternion();

    /* renamed from: g */
    private VgMyVector3 f1459g = new VgMyVector3();

    /* renamed from: h */
    private VgMyVector3 f1460h = new VgMyVector3();

    /* renamed from: i */
    private VgMyVector3 f1461i = new VgMyVector3();

    /* renamed from: j */
    private VgMyVector3 f1462j = new VgMyVector3();

    /* renamed from: k */
    private VgMyVector3 f1463k = new VgMyVector3();

    /* renamed from: l */
    private VgMyMatrix3 f1464l = new VgMyMatrix3();

    /* renamed from: m */
    private VgMyVector3 f1465m = new VgMyVector3();

    public void disableCompass() {
        SensorManager sensorManager = this.f1453a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        this.f1453a = null;
        this.f1454b = null;
        this.f1455c = null;
    }

    public void enableCompass(Context context, CompassChangedListener compassChangedListener) {
        this.f1453a = (SensorManager) context.getSystemService("sensor");
        this.f1454b = this.f1453a.getDefaultSensor(11);
        this.f1455c = compassChangedListener;
        this.f1453a.registerListener(this, this.f1454b, 3);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 11) {
            this.f1457e.set(sensorEvent.values);
            float sqNorm = this.f1457e.sqNorm();
            if (sqNorm > f1452o) {
                float sqrt = (float) Math.sqrt((double) sqNorm);
                this.f1457e.div(sqrt);
                this.f1458f.setFromAxisSinHalfTheta(this.f1457e, sqrt);
                this.f1459g.set(VgMyVector3.UNITz).mult(this.f1458f);
                this.f1460h.set(VgMyVector3.UNITY).mult(this.f1458f);
                this.f1461i.set(VgMyVector3.UNITx).mult(this.f1458f);
                if (((double) Math.abs(this.f1461i.mVals[2])) <= 0.9d) {
                    VgMyVector3 vgMyVector3 = this.f1460h;
                    if (((double) vgMyVector3.mVals[2]) >= -0.1d) {
                        this.f1462j.set(vgMyVector3).mult(0.1f);
                        this.f1463k.set(this.f1460h).mult(-0.1f);
                        VgMyVector3 vgMyVector32 = this.f1459g;
                        if (((double) vgMyVector32.mVals[2]) < -0.9d) {
                            vgMyVector32.add(this.f1462j).normalize();
                        }
                        VgMyVector3 vgMyVector33 = this.f1459g;
                        if (((double) vgMyVector33.mVals[2]) > 0.9d) {
                            vgMyVector33.add(this.f1463k).normalize();
                        }
                        VgMyVector3 vgMyVector34 = this.f1461i;
                        vgMyVector34.mVals[2] = 0.0f;
                        VgMyVector3.cross(this.f1460h, this.f1459g, vgMyVector34).normalize();
                        VgMyVector3.cross(this.f1459g, this.f1461i, this.f1460h).normalize();
                        VgMyMatrix3 vgMyMatrix3 = this.f1464l;
                        float[] fArr = this.f1461i.mVals;
                        float f = -fArr[0];
                        float[] fArr2 = this.f1459g.mVals;
                        float f2 = fArr2[0];
                        float[] fArr3 = this.f1460h.mVals;
                        vgMyMatrix3.set(f, f2, fArr3[0], -fArr[1], fArr2[1], fArr3[1], -fArr[2], fArr2[2], fArr3[2]);
                        this.f1458f.setFromMatrix3(this.f1464l);
                        this.f1458f.toEuler(this.f1465m, Order.cEO_ZXY);
                        this.f1465m.radiansToDegrees();
                        this.f1455c.onCompassChangedListener((double) this.f1465m.mVals[0]);
                    }
                }
            }
        }
    }
}
