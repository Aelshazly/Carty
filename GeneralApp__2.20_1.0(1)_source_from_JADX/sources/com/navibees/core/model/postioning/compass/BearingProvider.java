package com.navibees.core.model.postioning.compass;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.navibees.core.util.NaviBeesUtils;

public class BearingProvider implements SensorEventListener, LocationListener {
    public static final String TAG = "BearingProvider";

    /* renamed from: a */
    private Context f1415a;

    /* renamed from: b */
    private final SensorManager f1416b;

    /* renamed from: c */
    private final LocationManager f1417c;

    /* renamed from: d */
    private final Sensor f1418d;

    /* renamed from: e */
    private final Sensor f1419e;

    /* renamed from: f */
    private float[] f1420f;

    /* renamed from: g */
    private float[] f1421g;

    /* renamed from: h */
    private float[] f1422h;

    /* renamed from: i */
    private float[] f1423i;

    /* renamed from: j */
    private float[] f1424j;

    /* renamed from: k */
    private final double f1425k;

    /* renamed from: l */
    private final double f1426l;

    /* renamed from: m */
    private ChangeEventListener f1427m;

    /* renamed from: n */
    private AverageAngle f1428n;

    /* renamed from: o */
    private double f1429o;

    /* renamed from: p */
    private double f1430p;

    /* renamed from: q */
    private double f1431q;

    /* renamed from: r */
    private Location f1432r;

    /* renamed from: s */
    private long f1433s;

    public class AverageAngle {

        /* renamed from: a */
        private double[] f1434a;

        /* renamed from: b */
        private int f1435b;

        /* renamed from: c */
        private int f1436c;

        /* renamed from: d */
        private boolean f1437d;

        /* renamed from: e */
        private double f1438e = Double.NaN;

        public AverageAngle(int i) {
            this.f1436c = i;
            this.f1435b = 0;
            this.f1434a = new double[i];
        }

        /* renamed from: a */
        private void m1074a() {
            int i = this.f1436c;
            if (!this.f1437d) {
                i = this.f1435b + 1;
            }
            if (i == 1) {
                this.f1438e = this.f1434a[0];
                return;
            }
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i2 = 0; i2 < i; i2++) {
                double d3 = this.f1434a[i2];
                d += Math.sin(d3);
                d2 += Math.cos(d3);
            }
            this.f1438e = Math.atan2(d, d2);
        }

        public double getAverage() {
            return this.f1438e;
        }

        public void putValue(double d) {
            double[] dArr = this.f1434a;
            int i = this.f1435b;
            dArr[i] = d;
            if (i == this.f1436c - 1) {
                this.f1435b = 0;
                this.f1437d = true;
            } else {
                this.f1435b = i + 1;
            }
            m1074a();
        }
    }

    public interface ChangeEventListener {
        void onBearingChanged(double d);
    }

    public BearingProvider(Context context) {
        this(context, 10, 0.5d, 50);
    }

    /* renamed from: a */
    private void m1072a() {
        if (!Double.isNaN(this.f1429o)) {
            Location location = this.f1432r;
            if (location == null) {
                Log.w(TAG, "Location is NULL bearing is not true north!");
                this.f1430p = this.f1429o;
            } else {
                this.f1430p = m1071a(location);
            }
            if (((double) (System.currentTimeMillis() - this.f1433s)) <= this.f1426l) {
                return;
            }
            if (Double.isNaN(this.f1431q) || Math.abs(this.f1431q - this.f1430p) >= this.f1425k) {
                double d = this.f1430p;
                this.f1431q = d;
                ChangeEventListener changeEventListener = this.f1427m;
                if (changeEventListener != null) {
                    changeEventListener.onBearingChanged(d);
                }
                this.f1433s = System.currentTimeMillis();
            }
        }
    }

    /* renamed from: b */
    private GeomagneticField m1073b(Location location) {
        GeomagneticField geomagneticField = new GeomagneticField((float) location.getLatitude(), (float) location.getLongitude(), (float) location.getAltitude(), System.currentTimeMillis());
        return geomagneticField;
    }

    public double getBearing() {
        return this.f1430p;
    }

    public ChangeEventListener getChangeEventListener() {
        return this.f1427m;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onLocationChanged(Location location) {
        this.f1432r = location;
        m1072a();
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            System.arraycopy(sensorEvent.values, 0, this.f1420f, 0, 3);
        } else if (type == 2) {
            System.arraycopy(sensorEvent.values, 0, this.f1421g, 0, 3);
        }
        if (SensorManager.getRotationMatrix(this.f1422h, this.f1423i, this.f1420f, this.f1421g)) {
            SensorManager.getOrientation(this.f1422h, this.f1424j);
            this.f1428n.putValue((double) this.f1424j[0]);
            this.f1429o = Math.toDegrees(this.f1428n.getAverage());
        }
        m1072a();
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void setChangeEventListener(ChangeEventListener changeEventListener) {
        this.f1427m = changeEventListener;
    }

    public void start() {
        this.f1416b.registerListener(this, this.f1418d, 2);
        this.f1416b.registerListener(this, this.f1419e, 2);
        for (String str : this.f1417c.getProviders(true)) {
            if (("gps".equals(str) || "passive".equals(str) || "network".equals(str)) && NaviBeesUtils.isLocationServicesGranted(this.f1415a) && NaviBeesUtils.isLocationServicesEnabled(this.f1415a)) {
                if (this.f1432r == null) {
                    this.f1432r = this.f1417c.getLastKnownLocation(str);
                }
                this.f1417c.requestLocationUpdates(str, 0, 100.0f, this);
            }
        }
    }

    public void stop() {
        this.f1416b.unregisterListener(this, this.f1418d);
        this.f1416b.unregisterListener(this, this.f1419e);
        this.f1417c.removeUpdates(this);
    }

    public BearingProvider(Context context, int i, double d, int i2) {
        this.f1429o = Double.NaN;
        this.f1430p = Double.NaN;
        this.f1431q = Double.NaN;
        this.f1433s = -1;
        this.f1415a = context;
        this.f1416b = (SensorManager) context.getSystemService("sensor");
        this.f1418d = this.f1416b.getDefaultSensor(1);
        this.f1417c = (LocationManager) context.getSystemService(Param.LOCATION);
        this.f1419e = this.f1416b.getDefaultSensor(2);
        this.f1420f = new float[3];
        this.f1421g = new float[3];
        this.f1422h = new float[9];
        this.f1423i = new float[9];
        this.f1424j = new float[3];
        this.f1425k = d;
        this.f1426l = (double) i2;
        this.f1428n = new AverageAngle(i);
    }

    /* renamed from: a */
    private double m1071a(Location location) {
        return this.f1429o + ((double) m1073b(location).getDeclination());
    }
}
