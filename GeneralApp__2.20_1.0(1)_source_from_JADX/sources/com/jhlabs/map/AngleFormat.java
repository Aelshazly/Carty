package com.jhlabs.map;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class AngleFormat extends NumberFormat {
    public static final String ddmmssLatPattern = "DdM'S\"N";
    public static final String ddmmssLongPattern = "DdM'S\"W";
    public static final String ddmmssPattern = "DdM";
    public static final String ddmmssPattern2 = "DdM'S\"";
    public static final String ddmmssPattern4 = "DdMmSs";
    public static final String decimalPattern = "D.F";
    private DecimalFormat format;
    private boolean isDegrees;
    private String pattern;

    public AngleFormat() {
        this(ddmmssPattern);
    }

    public AngleFormat(String pattern2) {
        this(pattern2, false);
    }

    public AngleFormat(String pattern2, boolean isDegrees2) {
        this.pattern = pattern2;
        this.isDegrees = isDegrees2;
        this.format = new DecimalFormat();
        this.format.setMaximumFractionDigits(0);
        this.format.setGroupingUsed(false);
    }

    public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
        return format((double) number, result, fieldPosition);
    }

    public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
        double number2 = number;
        StringBuffer stringBuffer = result;
        int length = this.pattern.length();
        boolean negative = false;
        int f = 78;
        char c = 'W';
        if (number2 < 0.0d) {
            int i = length - 1;
            while (true) {
                if (i < 0) {
                    break;
                }
                char c2 = this.pattern.charAt(i);
                if (c2 == 'W' || c2 == 'N') {
                    number2 = -number2;
                    negative = true;
                } else {
                    i--;
                }
            }
        }
        double ddmmss = this.isDegrees != 0 ? number2 : Math.toDegrees(number2);
        int iddmmss = (int) Math.round(3600.0d * ddmmss);
        if (iddmmss < 0) {
            iddmmss = -iddmmss;
        }
        int fraction = iddmmss % 3600;
        for (int i2 = 0; i2 < length; i2++) {
            char c3 = this.pattern.charAt(i2);
            if (c3 == 'D') {
                stringBuffer.append((int) ddmmss);
            } else if (c3 == 'F') {
                stringBuffer.append(fraction);
            } else if (c3 != c) {
                if (c3 == 'M') {
                    int f2 = fraction / 60;
                    if (f2 < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(f2);
                    c = 'W';
                } else if (c3 != f) {
                    if (c3 == 'R') {
                        stringBuffer.append(number2);
                        f = 78;
                        c = 'W';
                    } else if (c3 != 'S') {
                        stringBuffer.append(c3);
                        f = 78;
                        c = 'W';
                    } else {
                        int f3 = fraction % 60;
                        if (f3 < 10) {
                            stringBuffer.append('0');
                        }
                        stringBuffer.append(f3);
                        f = 78;
                        c = 'W';
                    }
                } else if (negative) {
                    stringBuffer.append('S');
                    f = 78;
                    c = 'W';
                } else {
                    f = 78;
                    stringBuffer.append('N');
                    c = 'W';
                }
            } else if (negative) {
                c = 'W';
                stringBuffer.append('W');
            } else {
                c = 'W';
                stringBuffer.append('E');
            }
        }
        return stringBuffer;
    }

    public Number parse(String text, ParsePosition parsePosition) {
        double d;
        int i;
        String text2 = text;
        ParsePosition parsePosition2 = parsePosition;
        double m = 0.0d;
        double s = 0.0d;
        boolean negate = false;
        int length = text.length();
        if (length > 0) {
            char c = Character.toUpperCase(text2.charAt(length - 1));
            if (!(c == 'E' || c == 'N')) {
                if (c == 'S' || c == 'W') {
                    negate = true;
                }
            }
            text2 = text2.substring(0, length - 1);
        }
        int i2 = text2.indexOf(100);
        if (i2 == -1) {
            i2 = text2.indexOf(176);
        }
        if (i2 != -1) {
            String dd = text2.substring(0, i2);
            String mmss = text2.substring(i2 + 1);
            double d2 = Double.valueOf(dd).doubleValue();
            int i3 = mmss.indexOf(109);
            if (i3 == -1) {
                i = mmss.indexOf(39);
            } else {
                i = i3;
            }
            if (i != -1) {
                if (i != 0) {
                    m = Double.valueOf(mmss.substring(0, i)).doubleValue();
                }
                if (mmss.endsWith("s") || mmss.endsWith("\"")) {
                    mmss = mmss.substring(0, mmss.length() - 1);
                }
                if (i != mmss.length() - 1) {
                    s = Double.valueOf(mmss.substring(i + 1)).doubleValue();
                }
                if (m < 0.0d || m > 59.0d) {
                    throw new NumberFormatException("Minutes must be between 0 and 59");
                } else if (s < 0.0d || s >= 60.0d) {
                    throw new NumberFormatException("Seconds must be between 0 and 59");
                }
            } else if (i != 0) {
                m = Double.valueOf(mmss).doubleValue();
            }
            if (this.isDegrees) {
                int i4 = i;
                double d3 = s;
                double s2 = m;
                double m2 = d2;
                d = MapMath.dmsToDeg(d2, m, s);
            } else {
                int i5 = i;
                double d4 = s;
                double s3 = m;
                double m3 = d2;
                d = MapMath.dmsToRad(d2, m, s);
            }
        } else {
            double result = Double.parseDouble(text2);
            if (!this.isDegrees) {
                int i6 = i2;
                d = Math.toRadians(result);
            } else {
                int i7 = i2;
                d = result;
            }
        }
        if (parsePosition2 != null) {
            parsePosition2.setIndex(text2.length());
        }
        if (negate) {
            d = -d;
        }
        return new Double(d);
    }
}
