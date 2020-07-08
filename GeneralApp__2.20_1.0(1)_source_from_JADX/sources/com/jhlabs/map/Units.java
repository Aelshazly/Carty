package com.jhlabs.map;

public class Units {
    public static final Unit ARC_MINUTES;
    public static final Unit ARC_SECONDS;
    public static final Unit CENTIMETRES;
    public static final Unit CHAINS;
    public static final Unit DECIMETRES;
    public static final Unit DEGREES = new DegreeUnit();
    public static final Unit FATHOMS;
    public static final Unit FEET;
    public static final Unit INCHES;
    public static final Unit KILOMETRES;
    public static final Unit LINKS;
    public static final Unit METRES;
    public static final Unit MILES;
    public static final Unit MILLIMETRES;
    public static final Unit NAUTICAL_MILES;
    public static final Unit POINTS;
    public static final Unit RADIANS;
    public static final Unit US_CHAINS;
    public static final Unit US_FEET;
    public static final Unit US_INCHES;
    public static final Unit US_MILES;
    public static final Unit US_YARDS;
    public static final Unit YARDS;
    public static Unit[] units = {DEGREES, KILOMETRES, METRES, DECIMETRES, CENTIMETRES, MILLIMETRES, MILES, YARDS, FEET, INCHES, US_MILES, US_YARDS, US_FEET, US_INCHES, NAUTICAL_MILES};

    static {
        Unit unit = new Unit("radian", "radians", "rad", Math.toDegrees(1.0d));
        RADIANS = unit;
        Unit unit2 = new Unit("arc minute", "arc minutes", "min", 0.016666666666666666d);
        ARC_MINUTES = unit2;
        Unit unit3 = new Unit("arc second", "arc seconds", "sec", 2.777777777777778E-4d);
        ARC_SECONDS = unit3;
        Unit unit4 = new Unit("kilometre", "kilometres", "km", 1000.0d);
        KILOMETRES = unit4;
        Unit unit5 = new Unit("metre", "metres", "m", 1.0d);
        METRES = unit5;
        Unit unit6 = new Unit("decimetre", "decimetres", "dm", 0.1d);
        DECIMETRES = unit6;
        Unit unit7 = new Unit("centimetre", "centimetres", "cm", 0.01d);
        CENTIMETRES = unit7;
        Unit unit8 = new Unit("millimetre", "millimetres", "mm", 0.001d);
        MILLIMETRES = unit8;
        Unit unit9 = new Unit("nautical mile", "nautical miles", "kmi", 1852.0d);
        NAUTICAL_MILES = unit9;
        Unit unit10 = new Unit("mile", "miles", "mi", 1609.344d);
        MILES = unit10;
        Unit unit11 = new Unit("chain", "chains", "ch", 20.1168d);
        CHAINS = unit11;
        Unit unit12 = new Unit("yard", "yards", "yd", 0.9144d);
        YARDS = unit12;
        Unit unit13 = new Unit("foot", "feet", "ft", 0.3048d);
        FEET = unit13;
        Unit unit14 = new Unit("inch", "inches", "in", 0.0254d);
        INCHES = unit14;
        Unit unit15 = new Unit("U.S. mile", "U.S. miles", "us-mi", 1609.347218694437d);
        US_MILES = unit15;
        Unit unit16 = new Unit("U.S. chain", "U.S. chains", "us-ch", 20.11684023368047d);
        US_CHAINS = unit16;
        Unit unit17 = new Unit("U.S. yard", "U.S. yards", "us-yd", 0.914401828803658d);
        US_YARDS = unit17;
        Unit unit18 = new Unit("U.S. foot", "U.S. feet", "us-ft", 0.304800609601219d);
        US_FEET = unit18;
        Unit unit19 = new Unit("U.S. inch", "U.S. inches", "us-in", 0.025400050800101603d);
        US_INCHES = unit19;
        Unit unit20 = new Unit("fathom", "fathoms", "fath", 1.8288d);
        FATHOMS = unit20;
        Unit unit21 = new Unit("link", "links", "link", 0.201168d);
        LINKS = unit21;
        Unit unit22 = new Unit("point", "points", "point", 3.5145980351459805E-4d);
        POINTS = unit22;
    }

    public static Unit findUnits(String name) {
        int i = 0;
        while (true) {
            Unit[] unitArr = units;
            if (i >= unitArr.length) {
                return METRES;
            }
            if (!name.equals(unitArr[i].name) && !name.equals(units[i].plural) && !name.equals(units[i].abbreviation)) {
                i++;
            }
        }
        return units[i];
    }

    public static double convert(double value, Unit from, Unit to) {
        if (from == to) {
            return value;
        }
        return to.fromBase(from.toBase(value));
    }
}
