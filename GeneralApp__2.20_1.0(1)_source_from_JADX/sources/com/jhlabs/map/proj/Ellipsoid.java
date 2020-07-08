package com.jhlabs.map.proj;

public class Ellipsoid implements Cloneable {
    public static final Ellipsoid AIRY;
    public static final Ellipsoid AUSTRALIAN;
    public static final Ellipsoid BESSEL;
    public static final Ellipsoid CLARKE_1866;
    public static final Ellipsoid CLARKE_1880;
    public static final Ellipsoid EVEREST;
    public static final Ellipsoid GRS_1980;
    public static final Ellipsoid INTERNATIONAL_1967;
    public static final Ellipsoid KRASOVSKY;
    public static final Ellipsoid SPHERE;
    public static final Ellipsoid WGS_1960;
    public static final Ellipsoid WGS_1966;
    public static final Ellipsoid WGS_1972;
    public static final Ellipsoid WGS_1984;
    public static final Ellipsoid[] ellipsoids;
    public double eccentricity = 1.0d;
    public double eccentricity2 = 1.0d;
    public double equatorRadius = 1.0d;
    public String name;
    public double poleRadius = 1.0d;
    public String shortName;

    static {
        Ellipsoid ellipsoid = new Ellipsoid("sphere", 6371008.7714d, 6371008.7714d, 0.0d, "Sphere");
        SPHERE = ellipsoid;
        Ellipsoid ellipsoid2 = new Ellipsoid("bessel", 6377397.155d, 0.0d, 299.1528128d, "Bessel 1841");
        BESSEL = ellipsoid2;
        Ellipsoid ellipsoid3 = new Ellipsoid("clrk66", 6378206.4d, 6356583.8d, 0.0d, "Clarke 1866");
        CLARKE_1866 = ellipsoid3;
        Ellipsoid ellipsoid4 = new Ellipsoid("clrk80", 6378249.145d, 0.0d, 293.4663d, "Clarke 1880 mod.");
        CLARKE_1880 = ellipsoid4;
        Ellipsoid ellipsoid5 = new Ellipsoid("airy", 6377563.396d, 6356256.91d, 0.0d, "Airy 1830");
        AIRY = ellipsoid5;
        Ellipsoid ellipsoid6 = new Ellipsoid("WGS60", 6378165.0d, 0.0d, 298.3d, "WGS 60");
        WGS_1960 = ellipsoid6;
        Ellipsoid ellipsoid7 = new Ellipsoid("WGS66", 6378145.0d, 0.0d, 298.25d, "WGS 66");
        WGS_1966 = ellipsoid7;
        Ellipsoid ellipsoid8 = new Ellipsoid("WGS72", 6378135.0d, 0.0d, 298.26d, "WGS 72");
        WGS_1972 = ellipsoid8;
        Ellipsoid ellipsoid9 = new Ellipsoid("WGS84", 6378137.0d, 0.0d, 298.257223563d, "WGS 84");
        WGS_1984 = ellipsoid9;
        Ellipsoid ellipsoid10 = new Ellipsoid("krass", 6378245.0d, 298.3d, 0.0d, "Krassovsky, 1942");
        KRASOVSKY = ellipsoid10;
        Ellipsoid ellipsoid11 = new Ellipsoid("evrst30", 6377276.345d, 0.0d, 300.8017d, "Everest 1830");
        EVEREST = ellipsoid11;
        Ellipsoid ellipsoid12 = new Ellipsoid("new_intl", 6378157.5d, 6356772.2d, 0.0d, "New International 1967");
        INTERNATIONAL_1967 = ellipsoid12;
        Ellipsoid ellipsoid13 = new Ellipsoid("GRS80", 6378137.0d, 0.0d, 298.257222101d, "GRS 1980 (IUGG, 1980)");
        GRS_1980 = ellipsoid13;
        Ellipsoid ellipsoid14 = new Ellipsoid("australian", 6378160.0d, 6356774.7d, 298.25d, "Australian");
        AUSTRALIAN = ellipsoid14;
        Ellipsoid ellipsoid15 = new Ellipsoid("MERIT", 6378137.0d, 0.0d, 298.257d, "MERIT 1983");
        Ellipsoid ellipsoid16 = new Ellipsoid("SGS85", 6378136.0d, 0.0d, 298.257d, "Soviet Geodetic System 85");
        Ellipsoid ellipsoid17 = new Ellipsoid("IAU76", 6378140.0d, 0.0d, 298.257d, "IAU 1976");
        Ellipsoid ellipsoid18 = new Ellipsoid("APL4.9", 6378137.0d, 0.0d, 298.25d, "Appl. Physics. 1965");
        Ellipsoid ellipsoid19 = new Ellipsoid("NWL9D", 6378145.0d, 298.25d, 0.0d, "Naval Weapons Lab., 1965");
        Ellipsoid ellipsoid20 = new Ellipsoid("mod_airy", 6377340.189d, 6356034.446d, 0.0d, "Modified Airy");
        Ellipsoid ellipsoid21 = new Ellipsoid("andrae", 6377104.43d, 300.0d, 0.0d, "Andrae 1876 (Den., Iclnd.)");
        Ellipsoid ellipsoid22 = new Ellipsoid("aust_SA", 6378160.0d, 0.0d, 298.25d, "Australian Natl & S. Amer. 1969");
        Ellipsoid ellipsoid23 = new Ellipsoid("GRS67", 6378160.0d, 0.0d, 298.247167427d, "GRS 67 (IUGG 1967)");
        Ellipsoid ellipsoid24 = new Ellipsoid("bess_nam", 6377483.865d, 0.0d, 299.1528128d, "Bessel 1841 (Namibia)");
        Ellipsoid ellipsoid25 = new Ellipsoid("CPM", 6375738.7d, 0.0d, 334.29d, "Comm. des Poids et Mesures 1799");
        Ellipsoid ellipsoid26 = new Ellipsoid("delmbr", 6376428.0d, 0.0d, 311.5d, "Delambre 1810 (Belgium)");
        Ellipsoid ellipsoid27 = new Ellipsoid("engelis", 6378136.05d, 0.0d, 298.2566d, "Engelis 1985");
        Ellipsoid ellipsoid28 = new Ellipsoid("evrst48", 6377304.063d, 0.0d, 300.8017d, "Everest 1948");
        Ellipsoid ellipsoid29 = new Ellipsoid("evrst56", 6377301.243d, 0.0d, 300.8017d, "Everest 1956");
        Ellipsoid ellipsoid30 = new Ellipsoid("evrst69", 6377295.664d, 0.0d, 300.8017d, "Everest 1969");
        Ellipsoid ellipsoid31 = new Ellipsoid("evrstSS", 6377298.556d, 0.0d, 300.8017d, "Everest (Sabah & Sarawak)");
        Ellipsoid ellipsoid32 = new Ellipsoid("fschr60", 6378166.0d, 0.0d, 298.3d, "Fischer (Mercury Datum) 1960");
        Ellipsoid ellipsoid33 = new Ellipsoid("fschr60m", 6378155.0d, 0.0d, 298.3d, "Modified Fischer 1960");
        Ellipsoid ellipsoid34 = new Ellipsoid("fschr68", 6378150.0d, 0.0d, 298.3d, "Fischer 1968");
        Ellipsoid ellipsoid35 = new Ellipsoid("helmert", 6378200.0d, 0.0d, 298.3d, "Helmert 1906");
        Ellipsoid ellipsoid36 = new Ellipsoid("hough", 6378270.0d, 0.0d, 297.0d, "Hough");
        Ellipsoid ellipsoid37 = new Ellipsoid("intl", 6378388.0d, 0.0d, 297.0d, "International 1909 (Hayford)");
        Ellipsoid ellipsoid38 = new Ellipsoid("kaula", 6378163.0d, 0.0d, 298.24d, "Kaula 1961");
        Ellipsoid ellipsoid39 = new Ellipsoid("lerch", 6378139.0d, 0.0d, 298.257d, "Lerch 1979");
        Ellipsoid ellipsoid40 = new Ellipsoid("mprts", 6397300.0d, 0.0d, 191.0d, "Maupertius 1738");
        Ellipsoid ellipsoid41 = new Ellipsoid("plessis", 6376523.0d, 6355863.0d, 0.0d, "Plessis 1817 France)");
        Ellipsoid ellipsoid42 = new Ellipsoid("SEasia", 6378155.0d, 6356773.3205d, 0.0d, "Southeast Asia");
        Ellipsoid ellipsoid43 = new Ellipsoid("walbeck", 6376896.0d, 6355834.8467d, 0.0d, "Walbeck");
        Ellipsoid ellipsoid44 = new Ellipsoid("NAD27", 6378249.145d, 0.0d, 293.4663d, "NAD27: Clarke 1880 mod.");
        Ellipsoid ellipsoid45 = new Ellipsoid("NAD83", 6378137.0d, 0.0d, 298.257222101d, "NAD83: GRS 1980 (IUGG, 1980)");
        ellipsoids = new Ellipsoid[]{SPHERE, ellipsoid15, ellipsoid16, GRS_1980, ellipsoid17, AIRY, ellipsoid18, ellipsoid19, ellipsoid20, ellipsoid21, ellipsoid22, ellipsoid23, BESSEL, ellipsoid24, CLARKE_1866, CLARKE_1880, ellipsoid25, ellipsoid26, ellipsoid27, EVEREST, ellipsoid28, ellipsoid29, ellipsoid30, ellipsoid31, ellipsoid32, ellipsoid33, ellipsoid34, ellipsoid35, ellipsoid36, ellipsoid37, KRASOVSKY, ellipsoid38, ellipsoid39, ellipsoid40, INTERNATIONAL_1967, ellipsoid41, ellipsoid42, ellipsoid43, WGS_1960, WGS_1966, WGS_1972, WGS_1984, ellipsoid44, ellipsoid45};
    }

    public Ellipsoid() {
    }

    public Ellipsoid(String shortName2, double equatorRadius2, double poleRadius2, double reciprocalFlattening, String name2) {
        double d = equatorRadius2;
        double poleRadius3 = poleRadius2;
        this.shortName = shortName2;
        this.name = name2;
        this.equatorRadius = d;
        this.poleRadius = poleRadius3;
        if (reciprocalFlattening != 0.0d) {
            double f = 1.0d / reciprocalFlattening;
            this.eccentricity2 = (2.0d * f) - (f * f);
            double sqrt = d * Math.sqrt(1.0d - this.eccentricity2);
        } else {
            this.eccentricity2 = 1.0d - ((poleRadius3 * poleRadius3) / (d * d));
        }
        this.eccentricity = Math.sqrt(this.eccentricity2);
    }

    public Ellipsoid(String shortName2, double equatorRadius2, double eccentricity22, String name2) {
        this.shortName = shortName2;
        this.name = name2;
        this.equatorRadius = equatorRadius2;
        setEccentricitySquared(eccentricity22);
    }

    public Object clone() {
        try {
            return (Ellipsoid) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }

    public void setShortName(String shortName2) {
        this.shortName = shortName2;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setEquatorRadius(double equatorRadius2) {
        this.equatorRadius = equatorRadius2;
    }

    public double getEquatorRadius() {
        return this.equatorRadius;
    }

    public void setEccentricitySquared(double eccentricity22) {
        this.eccentricity2 = eccentricity22;
        this.poleRadius = this.equatorRadius * Math.sqrt(1.0d - eccentricity22);
        this.eccentricity = Math.sqrt(eccentricity22);
    }

    public double getEccentricitySquared() {
        return this.eccentricity2;
    }

    public String toString() {
        return this.name;
    }
}
