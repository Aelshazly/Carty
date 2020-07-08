package com.jhlabs.map;

import java.text.ParseException;

public class DegreeUnit extends Unit {
    private static AngleFormat format = new AngleFormat(AngleFormat.ddmmssPattern, true);
    static final long serialVersionUID = -3212757578604686538L;

    public DegreeUnit() {
        super("degree", "degrees", "deg", 1.0d);
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
}
