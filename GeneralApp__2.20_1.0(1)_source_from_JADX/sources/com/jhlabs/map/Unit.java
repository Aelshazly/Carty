package com.jhlabs.map;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;

public class Unit implements Serializable {
    public static final int ANGLE_UNIT = 0;
    public static final int AREA_UNIT = 2;
    public static final int LENGTH_UNIT = 1;
    public static final int VOLUME_UNIT = 3;
    public static NumberFormat format = NumberFormat.getNumberInstance();
    static final long serialVersionUID = -6704954923429734628L;
    public String abbreviation;
    public String name;
    public String plural;
    public double value;

    static {
        format.setMaximumFractionDigits(2);
        format.setGroupingUsed(false);
    }

    public Unit(String name2, String plural2, String abbreviation2, double value2) {
        this.name = name2;
        this.plural = plural2;
        this.abbreviation = abbreviation2;
        this.value = value2;
    }

    public double toBase(double n) {
        return this.value * n;
    }

    public double fromBase(double n) {
        return n / this.value;
    }

    public double parse(String s) throws NumberFormatException {
        try {
            return format.parse(s).doubleValue();
        } catch (ParseException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    public String format(double n) {
        StringBuilder sb = new StringBuilder(String.valueOf(format.format(n)));
        sb.append(" ");
        sb.append(this.abbreviation);
        return sb.toString();
    }

    public String format(double n, boolean abbrev) {
        if (!abbrev) {
            return format.format(n);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(format.format(n)));
        sb.append(" ");
        sb.append(this.abbreviation);
        return sb.toString();
    }

    public String format(double x, double y, boolean abbrev) {
        String str = "/";
        if (abbrev) {
            StringBuilder sb = new StringBuilder(String.valueOf(format.format(x)));
            sb.append(str);
            sb.append(format.format(y));
            sb.append(" ");
            sb.append(this.abbreviation);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(format.format(x)));
        sb2.append(str);
        sb2.append(format.format(y));
        return sb2.toString();
    }

    public String format(double x, double y) {
        return format(x, y, true);
    }

    public String toString() {
        return this.plural;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Unit) || ((Unit) o).value != this.value) {
            return false;
        }
        return true;
    }
}
