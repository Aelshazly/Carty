package com.navibees.core.model.postioning;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import java.util.Arrays;

/* renamed from: com.navibees.core.model.postioning.f */
/* compiled from: SensorFusionManager */
public class C1708f implements SensorEventListener {

    /* renamed from: u */
    private static C1708f f1495u = null;

    /* renamed from: v */
    private static final String f1496v = "SensorFusionManager";

    /* renamed from: a */
    private SensorManager f1497a;

    /* renamed from: b */
    private Sensor f1498b;

    /* renamed from: c */
    private Sensor f1499c;

    /* renamed from: d */
    private Sensor f1500d;

    /* renamed from: e */
    private boolean f1501e = false;

    /* renamed from: f */
    private boolean f1502f = false;

    /* renamed from: g */
    private float[] f1503g;

    /* renamed from: h */
    private float[] f1504h;

    /* renamed from: i */
    private float[] f1505i;

    /* renamed from: j */
    private float[] f1506j;

    /* renamed from: k */
    private float[] f1507k;

    /* renamed from: l */
    private long f1508l = 0;

    /* renamed from: m */
    private final float f1509m = 1.0E-9f;

    /* renamed from: n */
    private final float f1510n = 0.01f;

    /* renamed from: o */
    private final int f1511o = 5;

    /* renamed from: p */
    private final int f1512p = 3;

    /* renamed from: q */
    private final int f1513q = 3;

    /* renamed from: r */
    private final double f1514r = 0.05d;

    /* renamed from: s */
    private IndoorLocation[] f1515s = new IndoorLocation[5];

    /* renamed from: t */
    private C1709a f1516t;

    /* renamed from: com.navibees.core.model.postioning.f$a */
    /* compiled from: SensorFusionManager */
    private enum C1709a {
        N,
        S,
        E,
        W,
        NE,
        NW,
        SE,
        SW,
        C
    }

    private C1708f() {
    }

    /* renamed from: b */
    public static C1708f m1101b() {
        if (f1495u == null) {
            f1495u = new C1708f();
        }
        return f1495u;
    }

    /* renamed from: a */
    public void mo29372a(Context context) {
        this.f1497a = (SensorManager) context.getSystemService("sensor");
        this.f1498b = this.f1497a.getDefaultSensor(1);
        this.f1499c = this.f1497a.getDefaultSensor(2);
        this.f1500d = this.f1497a.getDefaultSensor(4);
        this.f1503g = new float[3];
        this.f1504h = new float[3];
        this.f1505i = new float[9];
        this.f1506j = new float[4];
        this.f1507k = new float[9];
        this.f1508l = 0;
        this.f1516t = C1709a.C;
        this.f1501e = false;
        this.f1502f = false;
        this.f1497a.registerListener(this, this.f1498b, 3);
        this.f1497a.registerListener(this, this.f1499c, 3);
        this.f1497a.registerListener(this, this.f1500d, 3);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor == this.f1498b || sensor == this.f1499c) {
            Sensor sensor2 = sensorEvent.sensor;
            if (sensor2 == this.f1498b) {
                float[] fArr = sensorEvent.values;
                System.arraycopy(fArr, 0, this.f1503g, 0, fArr.length);
                this.f1501e = true;
            } else if (sensor2 == this.f1499c) {
                float[] fArr2 = sensorEvent.values;
                System.arraycopy(fArr2, 0, this.f1504h, 0, fArr2.length);
                this.f1502f = true;
            }
            if (this.f1501e && this.f1502f) {
                SensorManager.getRotationMatrix(this.f1505i, null, this.f1503g, this.f1504h);
                Arrays.fill(this.f1507k, 0.0f);
                float[] fArr3 = this.f1507k;
                fArr3[0] = 1.0f;
                fArr3[4] = 1.0f;
                fArr3[8] = 1.0f;
                this.f1507k = m1100a(fArr3, this.f1505i);
                this.f1497a.unregisterListener(this, this.f1499c);
                this.f1497a.unregisterListener(this, this.f1498b);
            }
        } else if (sensor == this.f1500d) {
            long j = this.f1508l;
            if (j != 0 && this.f1501e && this.f1502f) {
                float f = ((float) (sensorEvent.timestamp - j)) * 1.0E-9f;
                float[] fArr4 = sensorEvent.values;
                float f2 = fArr4[0];
                float f3 = fArr4[1];
                float f4 = fArr4[2];
                float sqrt = (float) Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f4 * f4)));
                if (sqrt > 0.01f) {
                    f2 /= sqrt;
                    f3 /= sqrt;
                    f4 /= sqrt;
                }
                double d = (double) ((sqrt * f) / 2.0f);
                float sin = (float) Math.sin(d);
                float cos = (float) Math.cos(d);
                float[] fArr5 = this.f1506j;
                fArr5[0] = f2 * sin;
                fArr5[1] = f3 * sin;
                fArr5[2] = sin * f4;
                fArr5[3] = cos;
                float[] fArr6 = new float[9];
                SensorManager.getRotationMatrixFromVector(fArr6, fArr5);
                this.f1507k = m1100a(this.f1507k, fArr6);
                float[] fArr7 = new float[3];
                SensorManager.getOrientation(this.f1507k, fArr7);
                this.f1516t = m1099a((double) Math.round((Math.toDegrees((double) fArr7[0]) + 360.0d) % 360.0d));
            }
            this.f1508l = sensorEvent.timestamp;
        }
    }

    /* renamed from: a */
    public void mo29371a() {
        SensorManager sensorManager = this.f1497a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        this.f1503g = null;
        this.f1504h = null;
        this.f1505i = null;
        this.f1506j = null;
        this.f1507k = null;
        this.f1508l = 0;
        this.f1516t = C1709a.C;
        this.f1501e = false;
        this.f1502f = false;
        this.f1497a = null;
        this.f1498b = null;
        this.f1499c = null;
        this.f1500d = null;
    }

    /* renamed from: a */
    public double mo29370a(IndoorLocation indoorLocation) {
        double d;
        IndoorLocation indoorLocation2 = indoorLocation;
        if (indoorLocation2 != null && indoorLocation2.confidence == IndoorLocationConfidence.High) {
            if (this.f1515s[0] != null) {
                for (int i = 1; i < 5; i++) {
                    IndoorLocation[] indoorLocationArr = this.f1515s;
                    indoorLocationArr[i - 1] = indoorLocationArr[i];
                }
                IndoorLocation[] indoorLocationArr2 = this.f1515s;
                indoorLocationArr2[4] = indoorLocation2;
                double d2 = indoorLocationArr2[4].f1332x - indoorLocationArr2[3].f1332x;
                double d3 = indoorLocationArr2[4].f1333y - indoorLocationArr2[3].f1333y;
                int i2 = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
                if (i2 != 0) {
                    double round = (double) Math.round(Math.toDegrees(Math.atan(d3 / d2)));
                    if (i2 <= 0 || d3 <= 0.0d) {
                        int i3 = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
                        d = (i3 >= 0 || d3 <= 0.0d) ? (i2 <= 0 || d3 >= 0.0d) ? (i3 >= 0 || d3 >= 0.0d) ? (i2 <= 0 || d3 != 0.0d) ? (i3 >= 0 || d3 != 0.0d) ? round : 270.0d : 90.0d : 270.0d - round : (-round) + 90.0d : (-round) + 270.0d;
                    } else {
                        d = 90.0d - round;
                    }
                } else if (i2 == 0 && d3 == 0.0d) {
                    return -1.0d;
                } else {
                    d = (i2 != 0 || d3 <= 0.0d) ? (i2 != 0 || d3 >= 0.0d) ? -1.0d : 180.0d : 0.0d;
                }
                if (this.f1516t != m1099a(d)) {
                    IndoorLocation[] indoorLocationArr3 = this.f1515s;
                    double a = m1098a(indoorLocationArr3[4], indoorLocationArr3[3]);
                    int i4 = 3;
                    while (i4 > 0) {
                        IndoorLocation[] indoorLocationArr4 = this.f1515s;
                        int i5 = i4 - 1;
                        double a2 = m1098a(indoorLocationArr4[i4], indoorLocationArr4[i5]);
                        if (Math.abs(a2 - a) >= 0.05d) {
                            break;
                        }
                        if (5 - i5 >= 3) {
                            IndoorLocation[] indoorLocationArr5 = this.f1515s;
                            if (C1697c.m1064d(indoorLocationArr5[4], indoorLocationArr5[i5]) > 3.0d) {
                                return d;
                            }
                        }
                        i4--;
                        a = a2;
                    }
                } else {
                    return d;
                }
            } else {
                for (int i6 = 0; i6 < 5; i6++) {
                    this.f1515s[i6] = indoorLocation2;
                }
                return -1.0d;
            }
        }
        return -1.0d;
    }

    /* renamed from: a */
    private C1709a m1099a(double d) {
        C1709a aVar = C1709a.C;
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i > 0 && d < 90.0d) {
            return C1709a.NE;
        }
        int i2 = (d > 90.0d ? 1 : (d == 90.0d ? 0 : -1));
        if (i2 > 0 && d < 180.0d) {
            return C1709a.SE;
        }
        int i3 = (d > 180.0d ? 1 : (d == 180.0d ? 0 : -1));
        if (i3 > 0 && d < 270.0d) {
            return C1709a.SW;
        }
        int i4 = (d > 270.0d ? 1 : (d == 270.0d ? 0 : -1));
        if (i4 > 0 && d < 360.0d) {
            return C1709a.NW;
        }
        if (i == 0 || d == 360.0d) {
            return C1709a.N;
        }
        if (i2 == 0) {
            return C1709a.E;
        }
        if (i3 == 0) {
            return C1709a.S;
        }
        if (i4 == 0) {
            return C1709a.W;
        }
        return aVar;
    }

    /* renamed from: a */
    private double m1098a(IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        double d = indoorLocation2.f1332x;
        double d2 = indoorLocation.f1332x;
        if (d == d2) {
            return 2.147483647E9d;
        }
        return (indoorLocation.f1333y - indoorLocation2.f1333y) / (d2 - d);
    }

    /* renamed from: a */
    private float[] m1100a(float[] fArr, float[] fArr2) {
        return new float[]{(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[3]) + (fArr[2] * fArr2[6]), (fArr[0] * fArr2[1]) + (fArr[1] * fArr2[4]) + (fArr[2] * fArr2[7]), (fArr[0] * fArr2[2]) + (fArr[1] * fArr2[5]) + (fArr[2] * fArr2[8]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[3]) + (fArr[5] * fArr2[6]), (fArr[3] * fArr2[1]) + (fArr[4] * fArr2[4]) + (fArr[5] * fArr2[7]), (fArr[3] * fArr2[2]) + (fArr[4] * fArr2[5]) + (fArr[5] * fArr2[8]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[3]) + (fArr[8] * fArr2[6]), (fArr[6] * fArr2[1]) + (fArr[7] * fArr2[4]) + (fArr[8] * fArr2[7]), (fArr[6] * fArr2[2]) + (fArr[7] * fArr2[5]) + (fArr[8] * fArr2[8])};
    }
}
