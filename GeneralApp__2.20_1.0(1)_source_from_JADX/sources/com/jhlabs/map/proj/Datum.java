package com.jhlabs.map.proj;

public class Datum {
    public static Datum[] datums;
    double deltaX;
    double deltaY;
    double deltaZ;
    Ellipsoid ellipsoid;
    String name;

    static {
        Datum datum = new Datum("ADINDAN", Ellipsoid.CLARKE_1880, -162.0d, -12.0d, -206.0d);
        Datum datum2 = new Datum("ARC1950", Ellipsoid.CLARKE_1880, -143.0d, -90.0d, -294.0d);
        Datum datum3 = new Datum("ARC1960", Ellipsoid.CLARKE_1880, -160.0d, -8.0d, -300.0d);
        Datum datum4 = new Datum("Australian Geodetic 1966", Ellipsoid.AUSTRALIAN, -133.0d, -48.0d, 148.0d);
        Datum datum5 = new Datum("Australian Geodetic 984", Ellipsoid.AUSTRALIAN, -134.0d, -48.0d, 149.0d);
        Datum datum6 = new Datum("CAMP_AREA_ASTRO", Ellipsoid.INTERNATIONAL_1967, -104.0d, -129.0d, 239.0d);
        Datum datum7 = new Datum("Cape", Ellipsoid.CLARKE_1880, -136.0d, -108.0d, -292.0d);
        Datum datum8 = new Datum("European Datum 1950", Ellipsoid.INTERNATIONAL_1967, -87.0d, -98.0d, -121.0d);
        Datum datum9 = new Datum("European Datum 1979", Ellipsoid.INTERNATIONAL_1967, -86.0d, -98.0d, -119.0d);
        Datum datum10 = new Datum("Geodetic Datum 1949", Ellipsoid.INTERNATIONAL_1967, 84.0d, -22.0d, 209.0d);
        Datum datum11 = new Datum("Hong Kong 1963", Ellipsoid.INTERNATIONAL_1967, -156.0d, -271.0d, -189.0d);
        Datum datum12 = new Datum("Hu Tzu Shan", Ellipsoid.INTERNATIONAL_1967, -634.0d, -549.0d, -201.0d);
        Datum datum13 = new Datum("NAD27", Ellipsoid.CLARKE_1866, -8.0d, 160.0d, 176.0d);
        Datum datum14 = new Datum("NAD83", Ellipsoid.GRS_1980, 0.0d, 0.0d, 0.0d);
        Datum datum15 = new Datum("Old Hawaiian mean", Ellipsoid.CLARKE_1866, 89.0d, -279.0d, -183.0d);
        Datum datum16 = new Datum("OMAN", Ellipsoid.CLARKE_1880, -346.0d, -1.0d, 224.0d);
        Datum datum17 = new Datum("Ordnance Survey 1936", Ellipsoid.AIRY, 375.0d, -111.0d, 431.0d);
        Datum datum18 = new Datum("Puerto Rico", Ellipsoid.CLARKE_1866, 11.0d, 72.0d, -101.0d);
        Datum datum19 = new Datum("Pulkovo 1942", Ellipsoid.KRASOVSKY, 27.0d, -135.0d, -89.0d);
        Datum datum20 = new Datum("PROVISIONAL_S_AMERICAN_1956", Ellipsoid.INTERNATIONAL_1967, -288.0d, 175.0d, -376.0d);
        Datum datum21 = new Datum("Tokyo", Ellipsoid.BESSEL, -128.0d, 481.0d, 664.0d);
        Datum datum22 = new Datum("WGS72", Ellipsoid.WGS_1972, 0.0d, 0.0d, -4.5d);
        Datum datum23 = new Datum("WGS84", Ellipsoid.WGS_1984, 0.0d, 0.0d, 0.0d);
        datums = new Datum[]{datum, datum2, datum3, datum4, datum5, datum6, datum7, datum8, datum9, datum10, datum11, datum12, datum13, datum14, datum15, datum16, datum17, datum18, datum19, datum20, datum21, datum22, datum23};
    }

    public Datum(String name2, Ellipsoid ellipsoid2, double deltaX2, double deltaY2, double deltaZ2) {
        this.name = name2;
        this.ellipsoid = ellipsoid2;
        this.deltaX = deltaX2;
        this.deltaY = deltaY2;
        this.deltaZ = deltaZ2;
    }
}
