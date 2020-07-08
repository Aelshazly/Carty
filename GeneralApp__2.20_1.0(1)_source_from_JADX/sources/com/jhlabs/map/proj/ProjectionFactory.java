package com.jhlabs.map.proj;

import com.jhlabs.map.AngleFormat;
import com.jhlabs.map.Point2D.Double;
import com.jhlabs.map.Unit;
import com.jhlabs.map.Units;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class ProjectionFactory {
    private static final double RA4 = 0.04722222222222222d;
    private static final double RA6 = 0.022156084656084655d;
    private static final double RV4 = 0.06944444444444445d;
    private static final double RV6 = 0.04243827160493827d;
    private static final double SIXTH = 0.16666666666666666d;
    private static AngleFormat format = new AngleFormat(AngleFormat.ddmmssPattern, true);
    static Hashtable registry;

    public static Projection fromPROJ4Specification(String[] args) {
        double d;
        String[] strArr = args;
        Projection projection = null;
        Ellipsoid ellipsoid = null;
        double a = 0.0d;
        double b = 0.0d;
        double es = 0.0d;
        Hashtable params = new Hashtable();
        for (String ellipsoidName : strArr) {
            if (ellipsoidName.startsWith("+")) {
                int index = ellipsoidName.indexOf(61);
                if (index != -1) {
                    params.put(ellipsoidName.substring(1, index), ellipsoidName.substring(index + 1));
                }
            }
        }
        String s = (String) params.get("proj");
        String str = "Unknown projection: ";
        if (s != null) {
            projection = getNamedPROJ4Projection(s);
            if (projection == null) {
                StringBuilder sb = new StringBuilder(str);
                sb.append(s);
                throw new ProjectionException(sb.toString());
            }
        }
        String s2 = (String) params.get("init");
        if (s2 != null) {
            projection = getNamedPROJ4CoordinateSystem(s2);
            if (projection != null) {
                a = projection.getEquatorRadius();
                es = projection.getEllipsoid().getEccentricitySquared();
            } else {
                StringBuilder sb2 = new StringBuilder(str);
                sb2.append(s2);
                throw new ProjectionException(sb2.toString());
            }
        }
        String ellipsoidName2 = "";
        String s3 = (String) params.get("R");
        if (s3 != null) {
            a = Double.parseDouble(s3);
        } else {
            String s4 = (String) params.get("ellps");
            if (s4 == null) {
                s4 = (String) params.get("datum");
            }
            if (s4 != null) {
                Ellipsoid[] ellipsoids = Ellipsoid.ellipsoids;
                int i = 0;
                while (true) {
                    if (i >= ellipsoids.length) {
                        break;
                    } else if (ellipsoids[i].shortName.equals(s4)) {
                        ellipsoid = ellipsoids[i];
                        break;
                    } else {
                        i++;
                    }
                }
                if (ellipsoid != null) {
                    es = ellipsoid.eccentricity2;
                    a = ellipsoid.equatorRadius;
                    ellipsoidName2 = s4;
                } else {
                    StringBuilder sb3 = new StringBuilder("Unknown ellipsoid: ");
                    sb3.append(s4);
                    throw new ProjectionException(sb3.toString());
                }
            } else {
                String s5 = (String) params.get("a");
                if (s5 != null) {
                    a = Double.parseDouble(s5);
                }
                String s6 = (String) params.get("es");
                if (s6 != null) {
                    es = Double.parseDouble(s6);
                    d = 1.0d;
                } else {
                    String s7 = (String) params.get("rf");
                    if (s7 != null) {
                        double es2 = Double.parseDouble(s7);
                        es = es2 * (2.0d - es2);
                        d = 1.0d;
                    } else {
                        String s8 = (String) params.get("f");
                        if (s8 != null) {
                            double es3 = 1.0d / Double.parseDouble(s8);
                            es = es3 * (2.0d - es3);
                            d = 1.0d;
                        } else {
                            String s9 = (String) params.get("b");
                            if (s9 != null) {
                                b = Double.parseDouble(s9);
                                d = 1.0d;
                                es = 1.0d - ((b * b) / (a * a));
                            } else {
                                d = 1.0d;
                            }
                        }
                    }
                }
                if (b == 0.0d) {
                    b = a * Math.sqrt(d - es);
                }
            }
            String s10 = (String) params.get("R_A");
            if (s10 == null || !Boolean.getBoolean(s10)) {
                String s11 = (String) params.get("R_V");
                if (s11 == null || !Boolean.getBoolean(s11)) {
                    String s12 = (String) params.get("R_a");
                    if (s12 == null || !Boolean.getBoolean(s12)) {
                        String s13 = (String) params.get("R_g");
                        if (s13 == null || !Boolean.getBoolean(s13)) {
                            String s14 = (String) params.get("R_h");
                            if (s14 == null || !Boolean.getBoolean(s14)) {
                                String s15 = (String) params.get("R_lat_a");
                                String str2 = "-11";
                                if (s15 != null) {
                                    double tmp = Math.sin(parseAngle(s15));
                                    if (Math.abs(tmp) <= 1.5707963267948966d) {
                                        double tmp2 = 1.0d - ((es * tmp) * tmp);
                                        a *= (((1.0d - es) + tmp2) * 0.5d) / (Math.sqrt(tmp2) * tmp2);
                                        es = 0.0d;
                                    } else {
                                        throw new ProjectionException(str2);
                                    }
                                } else {
                                    String s16 = (String) params.get("R_lat_g");
                                    if (s16 != null) {
                                        double tmp3 = Math.sin(parseAngle(s16));
                                        if (Math.abs(tmp3) <= 1.5707963267948966d) {
                                            a *= Math.sqrt(1.0d - es) / (1.0d - ((es * tmp3) * tmp3));
                                            es = 0.0d;
                                        } else {
                                            throw new ProjectionException(str2);
                                        }
                                    }
                                }
                            } else {
                                a = ((a * 2.0d) * b) / (a + b);
                                es = 0.0d;
                            }
                        } else {
                            a = Math.sqrt(a * b);
                        }
                    } else {
                        a = (a + b) * 0.5d;
                    }
                } else {
                    a *= 1.0d - (((((RV6 * es) + RV4) * es) + SIXTH) * es);
                }
            } else {
                a *= 1.0d - (((((RA6 * es) + RA4) * es) + SIXTH) * es);
            }
        }
        Ellipsoid ellipsoid2 = new Ellipsoid(ellipsoidName2, a, es, ellipsoidName2);
        projection.setEllipsoid(ellipsoid2);
        String s17 = (String) params.get("lat_0");
        if (s17 != null) {
            projection.setProjectionLatitudeDegrees(parseAngle(s17));
        }
        String s18 = (String) params.get("lon_0");
        if (s18 != null) {
            projection.setProjectionLongitudeDegrees(parseAngle(s18));
        }
        String s19 = (String) params.get("lat_1");
        if (s19 != null) {
            projection.setProjectionLatitude1Degrees(parseAngle(s19));
        }
        String s20 = (String) params.get("lat_2");
        if (s20 != null) {
            projection.setProjectionLatitude2Degrees(parseAngle(s20));
        }
        String s21 = (String) params.get("lat_ts");
        if (s21 != null) {
            projection.setTrueScaleLatitudeDegrees(parseAngle(s21));
        }
        String s22 = (String) params.get("x_0");
        if (s22 != null) {
            projection.setFalseEasting(Double.parseDouble(s22));
        }
        String s23 = (String) params.get("y_0");
        if (s23 != null) {
            projection.setFalseNorthing(Double.parseDouble(s23));
        }
        String s24 = (String) params.get("k_0");
        if (s24 == null) {
            s24 = (String) params.get("k");
        }
        if (s24 != null) {
            projection.setScaleFactor(Double.parseDouble(s24));
        }
        String s25 = (String) params.get("units");
        if (s25 != null) {
            Unit unit = Units.findUnits(s25);
            if (unit != null) {
                projection.setFromMetres(1.0d / unit.value);
            }
        }
        String s26 = (String) params.get("to_meter");
        if (s26 != null) {
            projection.setFromMetres(1.0d / Double.parseDouble(s26));
        }
        if (projection instanceof TransverseMercatorProjection) {
            String s27 = (String) params.get("zone");
            if (s27 != null) {
                ((TransverseMercatorProjection) projection).setUTMZone(Integer.parseInt(s27));
            }
        }
        projection.initialize();
        return projection;
    }

    private static double parseAngle(String s) {
        return format.parse(s, null).doubleValue();
    }

    static void register(String name, Class cls, String description) {
        registry.put(name, cls);
    }

    static Projection getNamedPROJ4Projection(String name) {
        if (registry == null) {
            initialize();
        }
        Class cls = (Class) registry.get(name);
        if (cls != null) {
            try {
                Projection projection = (Projection) cls.newInstance();
                if (projection != null) {
                    projection.setName(name);
                }
                return projection;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    static void initialize() {
        registry = new Hashtable();
        register("aea", AlbersProjection.class, "Albers Equal Area");
        register("aeqd", EquidistantAzimuthalProjection.class, "Azimuthal Equidistant");
        register("airy", AiryProjection.class, "Airy");
        register("aitoff", AitoffProjection.class, "Aitoff");
        register("alsk", Projection.class, "Mod. Stereographics of Alaska");
        register("apian", Projection.class, "Apian Globular I");
        register("august", AugustProjection.class, "August Epicycloidal");
        register("bacon", Projection.class, "Bacon Globular");
        register("bipc", BipolarProjection.class, "Bipolar conic of western hemisphere");
        register("boggs", BoggsProjection.class, "Boggs Eumorphic");
        register("bonne", BonneProjection.class, "Bonne (Werner lat_1=90)");
        register("cass", CassiniProjection.class, "Cassini");
        register("cc", CentralCylindricalProjection.class, "Central Cylindrical");
        register("cea", Projection.class, "Equal Area Cylindrical");
        register("collg", CollignonProjection.class, "Collignon");
        register("crast", CrasterProjection.class, "Craster Parabolic (Putnins P4)");
        register("denoy", DenoyerProjection.class, "Denoyer Semi-Elliptical");
        register("eck1", Eckert1Projection.class, "Eckert I");
        register("eck2", Eckert2Projection.class, "Eckert II");
        register("eck4", Eckert4Projection.class, "Eckert IV");
        register("eck5", Eckert5Projection.class, "Eckert V");
        register("eqc", PlateCarreeProjection.class, "Equidistant Cylindrical (Plate Caree)");
        register("eqdc", EquidistantConicProjection.class, "Equidistant Conic");
        register("euler", EulerProjection.class, "Euler");
        register("fahey", FaheyProjection.class, "Fahey");
        register("fouc", FoucautProjection.class, "Foucaut");
        register("fouc_s", FoucautSinusoidalProjection.class, "Foucaut Sinusoidal");
        register("gall", GallProjection.class, "Gall (Gall Stereographic)");
        register("gnom", GnomonicAzimuthalProjection.class, "Gnomonic");
        register("goode", GoodeProjection.class, "Goode Homolosine");
        register("hammer", HammerProjection.class, "Hammer & Eckert-Greifendorff");
        register("hatano", HatanoProjection.class, "Hatano Asymmetrical Equal Area");
        register("kav5", KavraiskyVProjection.class, "Kavraisky V");
        register("lagrng", LagrangeProjection.class, "Lagrange");
        register("larr", LarriveeProjection.class, "Larrivee");
        register("lask", LaskowskiProjection.class, "Laskowski");
        register("latlong", NullProjection.class, "Lat/Long");
        register("lcc", LambertConformalConicProjection.class, "Lambert Conformal Conic");
        register("leac", LambertEqualAreaConicProjection.class, "Lambert Equal Area Conic");
        register("loxim", LoximuthalProjection.class, "Loximuthal");
        register("lsat", LandsatProjection.class, "Space oblique for LANDSAT");
        register("mbt_fps", MBTFPSProjection.class, "McBryde-Thomas Flat-Pole Sine (No. 2)");
        register("mbtfpp", MBTFPPProjection.class, "McBride-Thomas Flat-Polar Parabolic");
        register("mbtfpq", MBTFPQProjection.class, "McBryde-Thomas Flat-Polar Quartic");
        register("merc", MercatorProjection.class, "Mercator");
        register("mill", MillerProjection.class, "Miller Cylindrical");
        register("moll", MolleweideProjection.class, "Mollweide");
        register("murd1", Murdoch1Projection.class, "Murdoch I");
        register("murd2", Murdoch2Projection.class, "Murdoch II");
        register("murd3", Murdoch3Projection.class, "Murdoch III");
        register("nell", NellProjection.class, "Nell");
        register("nicol", NicolosiProjection.class, "Nicolosi Globular");
        register("nsper", PerspectiveProjection.class, "Near-sided perspective");
        register("omerc", ObliqueMercatorProjection.class, "Oblique Mercator");
        register("ortho", OrthographicAzimuthalProjection.class, "Orthographic");
        register("pconic", PerspectiveConicProjection.class, "Perspective Conic");
        register("poly", PolyconicProjection.class, "Polyconic (American)");
        register("putp2", PutninsP2Projection.class, "Putnins P2");
        register("putp4p", PutninsP4Projection.class, "Putnins P4'");
        register("putp5", PutninsP5Projection.class, "Putnins P5");
        register("putp5p", PutninsP5PProjection.class, "Putnins P5'");
        register("qua_aut", QuarticAuthalicProjection.class, "Quartic Authalic");
        register("robin", RobinsonProjection.class, "Robinson");
        register("rpoly", RectangularPolyconicProjection.class, "Rectangular Polyconic");
        register("sinu", SinusoidalProjection.class, "Sinusoidal (Sanson-Flamsteed)");
        register("stere", StereographicAzimuthalProjection.class, "Stereographic");
        register("tcc", TCCProjection.class, "Transverse Central Cylindrical");
        register("tcea", TCEAProjection.class, "Transverse Cylindrical Equal Area");
        register("tmerc", TransverseMercatorProjection.class, "Transverse Mercator");
        register("urmfps", URMFPSProjection.class, "Urmaev Flat-Polar Sinusoidal");
        register("utm", TransverseMercatorProjection.class, "Universal Transverse Mercator (UTM)");
        register("vandg", VanDerGrintenProjection.class, "van der Grinten (I)");
        register("vitk1", VitkovskyProjection.class, "Vitkovsky I");
        register("wag1", Wagner1Projection.class, "Wagner I (Kavraisky VI)");
        register("wag2", Wagner2Projection.class, "Wagner II");
        register("wag3", Wagner3Projection.class, "Wagner III");
        register("wag4", Wagner4Projection.class, "Wagner IV");
        register("wag5", Wagner5Projection.class, "Wagner V");
        register("wag7", Wagner7Projection.class, "Wagner VII");
        register("weren", WerenskioldProjection.class, "Werenskiold I");
        register("wintri", WinkelTripelProjection.class, "Winkel Tripel");
    }

    public static Projection readProjectionFile(String file, String name) throws IOException {
        StringBuilder sb = new StringBuilder("/nad/");
        sb.append(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ProjectionFactory.class.getResourceAsStream(sb.toString())));
        StreamTokenizer t = new StreamTokenizer(reader);
        t.commentChar(35);
        t.ordinaryChars(48, 57);
        t.ordinaryChars(46, 46);
        t.ordinaryChars(45, 45);
        t.ordinaryChars(43, 43);
        t.wordChars(48, 57);
        t.wordChars(39, 39);
        t.wordChars(34, 34);
        t.wordChars(95, 95);
        t.wordChars(46, 46);
        t.wordChars(45, 45);
        t.wordChars(43, 43);
        t.wordChars(44, 44);
        t.nextToken();
        while (t.ttype == 60) {
            t.nextToken();
            if (t.ttype == -3) {
                String cname = t.sval;
                t.nextToken();
                if (t.ttype == 62) {
                    t.nextToken();
                    Vector v = new Vector();
                    String str = "";
                    while (t.ttype != 60) {
                        if (t.ttype == 43) {
                            t.nextToken();
                        }
                        if (t.ttype == -3) {
                            String key = t.sval;
                            t.nextToken();
                            if (t.ttype == 61) {
                                t.nextToken();
                                String value = t.sval;
                                t.nextToken();
                                String str2 = "+";
                                String str3 = "=";
                                if (key.startsWith(str2)) {
                                    StringBuilder sb2 = new StringBuilder(String.valueOf(key));
                                    sb2.append(str3);
                                    sb2.append(value);
                                    v.add(sb2.toString());
                                } else {
                                    StringBuilder sb3 = new StringBuilder(str2);
                                    sb3.append(key);
                                    sb3.append(str3);
                                    sb3.append(value);
                                    v.add(sb3.toString());
                                }
                            }
                        } else {
                            StringBuilder sb4 = new StringBuilder(String.valueOf(t.lineno()));
                            sb4.append(": Word expected after '+'");
                            throw new IOException(sb4.toString());
                        }
                    }
                    t.nextToken();
                    if (t.ttype == 62) {
                        t.nextToken();
                        if (cname.equals(name)) {
                            String[] args = new String[v.size()];
                            v.copyInto(args);
                            reader.close();
                            return fromPROJ4Specification(args);
                        }
                    } else {
                        StringBuilder sb5 = new StringBuilder(String.valueOf(t.lineno()));
                        sb5.append(": '<>' expected");
                        throw new IOException(sb5.toString());
                    }
                } else {
                    StringBuilder sb6 = new StringBuilder(String.valueOf(t.lineno()));
                    sb6.append(": '>' expected");
                    throw new IOException(sb6.toString());
                }
            } else {
                StringBuilder sb7 = new StringBuilder(String.valueOf(t.lineno()));
                sb7.append(": Word expected after '<'");
                throw new IOException(sb7.toString());
            }
        }
        reader.close();
        return null;
    }

    public static Projection getNamedPROJ4CoordinateSystem(String name) {
        String[] files = {"world", "nad83", "nad27", "esri", "epsg"};
        try {
            int p = name.indexOf(58);
            if (p >= 0) {
                return readProjectionFile(name.substring(0, p), name.substring(p + 1));
            }
            for (String readProjectionFile : files) {
                Projection projection = readProjectionFile(readProjectionFile, name);
                if (projection != null) {
                    return projection;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String str = " ";
        Projection projection = fromPROJ4Specification(args);
        if (projection != null) {
            System.out.println(projection.getPROJ4Description());
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (!arg.startsWith("+") && !arg.startsWith("-")) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(new File(args[i])));
                        Double p = new Double();
                        while (true) {
                            String readLine = reader.readLine();
                            String line = readLine;
                            if (readLine == null) {
                                break;
                            }
                            StringTokenizer t = new StringTokenizer(line, str);
                            String slon = t.nextToken();
                            String slat = t.nextToken();
                            p.f1626x = format.parse(slon, null).doubleValue();
                            p.f1627y = format.parse(slat, null).doubleValue();
                            projection.transform(p, p);
                            PrintStream printStream = System.out;
                            StringBuilder sb = new StringBuilder(String.valueOf(p.f1626x));
                            sb.append(str);
                            sb.append(p.f1627y);
                            printStream.println(sb.toString());
                        }
                    } catch (IOException e) {
                        PrintStream printStream2 = System.out;
                        StringBuilder sb2 = new StringBuilder("IOException: ");
                        sb2.append(args[i]);
                        sb2.append(": ");
                        sb2.append(e.getMessage());
                        printStream2.println(sb2.toString());
                    }
                }
            }
            return;
        }
        PrintStream printStream3 = System.out;
        StringBuilder sb3 = new StringBuilder("Can't find projection ");
        sb3.append(args[0]);
        printStream3.println(sb3.toString());
    }
}
