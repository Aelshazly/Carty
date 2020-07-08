package com.navibees.core.model.postioning.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationRestriction;
import com.navibees.core.model.metadata.json.LineIndoorLocationRestriction;
import com.navibees.core.model.postioning.C1697c;

public class Compass implements SensorEventListener {

    /* renamed from: k */
    private static final String f1440k = "Compass";

    /* renamed from: a */
    private SensorManager f1441a;

    /* renamed from: b */
    private Sensor f1442b;

    /* renamed from: c */
    private Sensor f1443c;

    /* renamed from: d */
    private boolean f1444d = false;

    /* renamed from: e */
    private boolean f1445e = false;

    /* renamed from: f */
    private float[] f1446f;

    /* renamed from: g */
    private float[] f1447g;

    /* renamed from: h */
    private float f1448h = -1.0f;

    /* renamed from: i */
    private long f1449i = 0;

    /* renamed from: j */
    private CompassChangedListener f1450j;

    public interface CompassChangedListener {
        void onCompassChangedListener(float f);
    }

    public double getMapRotationAngle(double d, IndoorLocationRestriction indoorLocationRestriction) {
        double d2;
        IndoorLocationRestriction indoorLocationRestriction2 = indoorLocationRestriction;
        if (indoorLocationRestriction2 != null && (indoorLocationRestriction2 instanceof LineIndoorLocationRestriction)) {
            LineIndoorLocationRestriction lineIndoorLocationRestriction = (LineIndoorLocationRestriction) indoorLocationRestriction2;
            double e = C1697c.m1066e(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end);
            IndoorLocation indoorLocation = lineIndoorLocationRestriction.end;
            double e2 = C1697c.m1066e(indoorLocation, indoorLocation);
            if (d < e) {
                d2 = d;
            } else {
                d2 = e;
                e = d;
            }
            double min = Math.min(e - d2, (360.0d - e) + d2);
            if (e < e2) {
                double d3 = e;
                e = e2;
                e2 = d3;
            }
            double min2 = Math.min(e - e2, (360.0d - e) + e2);
            if (min < min2 && min <= 10.0d) {
                return C1697c.m1066e(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end);
            }
            if (min2 < min && min2 <= 10.0d) {
                return C1697c.m1066e(lineIndoorLocationRestriction.end, lineIndoorLocationRestriction.start);
            }
        }
        return d;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor == this.f1442b) {
            float[] fArr = sensorEvent.values;
            System.arraycopy(fArr, 0, this.f1446f, 0, fArr.length);
            this.f1444d = true;
        } else if (sensor == this.f1443c) {
            float[] fArr2 = sensorEvent.values;
            System.arraycopy(fArr2, 0, this.f1447g, 0, fArr2.length);
            this.f1445e = true;
        }
        if (this.f1444d && this.f1445e) {
            float[] fArr3 = new float[9];
            SensorManager.getRotationMatrix(fArr3, null, this.f1446f, this.f1447g);
            float[] fArr4 = new float[9];
            SensorManager.getOrientation(fArr3, fArr4);
            float degrees = (((float) Math.toDegrees((double) fArr4[0])) + 360.0f) % 360.0f;
            float f = this.f1448h;
            if (f == -1.0f || Math.abs(degrees - f) >= 300.0f) {
                this.f1448h = degrees;
            } else {
                this.f1448h = (degrees * 0.5f) + (this.f1448h * 0.5f);
            }
            if (sensorEvent.timestamp - this.f1449i >= 1000000000) {
                this.f1450j.onCompassChangedListener(this.f1448h);
                this.f1449i = sensorEvent.timestamp;
                StringBuilder sb = new StringBuilder();
                sb.append("Delivered at: ");
                sb.append(this.f1449i / 1000000000);
                Log.w(f1440k, sb.toString());
            }
        }
    }

    public void registerSensor(Context context, CompassChangedListener compassChangedListener) {
        this.f1441a = (SensorManager) context.getSystemService("sensor");
        this.f1442b = this.f1441a.getDefaultSensor(1);
        this.f1443c = this.f1441a.getDefaultSensor(2);
        this.f1446f = new float[3];
        this.f1447g = new float[3];
        this.f1444d = false;
        this.f1445e = false;
        this.f1441a.registerListener(this, this.f1442b, 3);
        this.f1441a.registerListener(this, this.f1443c, 3);
        this.f1450j = compassChangedListener;
    }

    public void unregisterSensor() {
        SensorManager sensorManager = this.f1441a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        this.f1446f = null;
        this.f1447g = null;
        this.f1444d = false;
        this.f1445e = false;
        this.f1441a = null;
        this.f1442b = null;
        this.f1443c = null;
        this.f1450j = null;
    }
}
