package com.jhlabs.map.proj;

import com.jhlabs.map.MapMath;
import com.jhlabs.map.Point2D.Double;

public class EquidistantConicProjection extends ConicProjection {
    private double eccentricity = 0.822719d;
    private double eccentricity2;
    private double eccentricity4;
    private double eccentricity6;

    /* renamed from: f */
    private double f1683f;

    /* renamed from: n */
    private double f1684n;
    private boolean northPole;
    private double radius;
    private double rho0;
    private double standardLatitude1;
    private double standardLatitude2;

    public EquidistantConicProjection() {
        double d = this.eccentricity;
        this.eccentricity2 = d * d;
        double d2 = this.eccentricity2;
        this.eccentricity4 = d2 * d2;
        this.eccentricity6 = d2 * this.eccentricity4;
        this.radius = 1.0d;
        this.minLatitude = MapMath.degToRad(10.0d);
        this.maxLatitude = MapMath.degToRad(70.0d);
        this.minLongitude = MapMath.degToRad(-90.0d);
        this.maxLongitude = MapMath.degToRad(90.0d);
        this.standardLatitude1 = Math.toDegrees(60.0d);
        this.standardLatitude2 = Math.toDegrees(20.0d);
        initialize(MapMath.degToRad(0.0d), MapMath.degToRad(37.5d), this.standardLatitude1, this.standardLatitude2);
    }

    public Double transform(Double in, Double out) {
        Double doubleR = in;
        Double doubleR2 = out;
        double lon = MapMath.normalizeLongitude(doubleR.f1626x - this.projectionLongitude);
        double lat = doubleR.f1627y;
        double hold2 = Math.pow((1.0d - (this.eccentricity * Math.sin(lat))) / ((this.eccentricity * Math.sin(lat)) + 1.0d), this.eccentricity * 0.5d);
        double hold3 = Math.tan(0.7853981633974483d - (0.5d * lat));
        double hold1 = 0.0d;
        if (hold3 != 0.0d) {
            hold1 = Math.pow(hold3 / hold2, this.f1684n);
        }
        double d = lat;
        double rho = this.radius * this.f1683f * hold1;
        double theta = this.f1684n * lon;
        double d2 = lon;
        doubleR2.f1626x = rho * Math.sin(theta);
        doubleR2.f1627y = this.rho0 - (Math.cos(theta) * rho);
        return doubleR2;
    }

    public Double inverseTransform(Double in, Double out) {
        Double doubleR = in;
        Double doubleR2 = out;
        double theta = Math.atan(doubleR.f1626x / (this.rho0 - doubleR.f1627y));
        doubleR2.f1626x = (theta / this.f1684n) + this.projectionLongitude;
        double temp = (doubleR.f1626x * doubleR.f1626x) + ((this.rho0 - doubleR.f1627y) * (this.rho0 - doubleR.f1627y));
        double rho = Math.sqrt(temp);
        if (this.f1684n < 0.0d) {
            rho = -rho;
        }
        double t = Math.pow(rho / (this.radius * this.f1683f), 1.0d / this.f1684n);
        double tphi = 1.5707963267948966d - (Math.atan(t) * 2.0d);
        double delta = 1.0d;
        int i = 0;
        double d = theta;
        double phi = 0.0d;
        while (true) {
            if (i >= 100) {
                double d2 = temp;
                double d3 = rho;
                break;
            } else if (delta <= 1.0E-8d) {
                double d4 = temp;
                double d5 = rho;
                break;
            } else {
                double d6 = temp;
                double rho2 = rho;
                temp = (1.0d - (this.eccentricity * Math.sin(tphi))) / ((this.eccentricity * Math.sin(tphi)) + 1.0d);
                phi = 1.5707963267948966d - (Math.atan(Math.pow(temp, this.eccentricity * 0.5d) * t) * 2.0d);
                delta = Math.abs(Math.abs(tphi) - Math.abs(phi));
                tphi = phi;
                i++;
                Double doubleR3 = in;
                Double doubleR4 = out;
                rho = rho2;
            }
        }
        Double doubleR5 = out;
        doubleR5.f1627y = phi;
        return doubleR5;
    }

    private void initialize(double rlong0, double rlat0, double standardLatitude12, double standardLatitude22) {
        super.initialize();
        this.northPole = rlat0 > 0.0d;
        this.projectionLatitude = this.northPole ? 1.5707963267948966d : -1.5707963267948966d;
        double t_standardLatitude1 = Math.tan(0.7853981633974483d - (standardLatitude12 * 0.5d)) / Math.pow((1.0d - (this.eccentricity * Math.sin(standardLatitude12))) / ((this.eccentricity * Math.sin(standardLatitude12)) + 1.0d), this.eccentricity * 0.5d);
        double m_standardLatitude1 = Math.cos(standardLatitude12) / Math.sqrt(1.0d - (this.eccentricity2 * Math.pow(Math.sin(standardLatitude12), 2.0d)));
        double t_standardLatitude2 = Math.tan(0.7853981633974483d - (standardLatitude22 * 0.5d)) / Math.pow((1.0d - (this.eccentricity * Math.sin(standardLatitude22))) / ((this.eccentricity * Math.sin(standardLatitude22)) + 1.0d), this.eccentricity * 0.5d);
        double m_standardLatitude2 = Math.cos(standardLatitude22) / Math.sqrt(1.0d - (this.eccentricity2 * Math.pow(Math.sin(standardLatitude22), 2.0d)));
        double t_rlat0 = Math.tan(0.7853981633974483d - (rlat0 * 0.5d)) / Math.pow((1.0d - (this.eccentricity * Math.sin(rlat0))) / ((this.eccentricity * Math.sin(rlat0)) + 1.0d), this.eccentricity * 0.5d);
        if (standardLatitude12 != standardLatitude22) {
            this.f1684n = (Math.log(m_standardLatitude1) - Math.log(m_standardLatitude2)) / (Math.log(t_standardLatitude1) - Math.log(t_standardLatitude2));
        } else {
            this.f1684n = Math.sin(standardLatitude12);
        }
        double d = this.f1684n;
        this.f1683f = m_standardLatitude1 / (d * Math.pow(t_standardLatitude1, d));
        this.projectionLongitude = rlong0;
        double d2 = t_standardLatitude1;
        this.rho0 = this.radius * this.f1683f * Math.pow(t_rlat0, this.f1684n);
    }

    public boolean hasInverse() {
        return true;
    }

    public String toString() {
        return "Equidistant Conic";
    }
}
