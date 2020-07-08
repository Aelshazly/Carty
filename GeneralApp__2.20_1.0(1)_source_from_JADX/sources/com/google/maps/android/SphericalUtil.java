package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class SphericalUtil {
    private SphericalUtil() {
    }

    public static double computeHeading(LatLng from, LatLng to) {
        LatLng latLng = from;
        LatLng latLng2 = to;
        double fromLat = Math.toRadians(latLng.latitude);
        double fromLng = Math.toRadians(latLng.longitude);
        double toLat = Math.toRadians(latLng2.latitude);
        double dLng = Math.toRadians(latLng2.longitude) - fromLng;
        return MathUtil.wrap(Math.toDegrees(Math.atan2(Math.sin(dLng) * Math.cos(toLat), (Math.cos(fromLat) * Math.sin(toLat)) - ((Math.sin(fromLat) * Math.cos(toLat)) * Math.cos(dLng)))), -180.0d, 180.0d);
    }

    public static LatLng computeOffset(LatLng from, double distance, double heading) {
        LatLng latLng = from;
        double distance2 = distance / 6371009.0d;
        double heading2 = Math.toRadians(heading);
        double fromLat = Math.toRadians(latLng.latitude);
        double fromLng = Math.toRadians(latLng.longitude);
        double cosDistance = Math.cos(distance2);
        double sinDistance = Math.sin(distance2);
        double sinFromLat = Math.sin(fromLat);
        double cosFromLat = Math.cos(fromLat);
        double sinLat = (cosDistance * sinFromLat) + (sinDistance * cosFromLat * Math.cos(heading2));
        double d = distance2;
        double d2 = heading2;
        double dLng = Math.atan2(sinDistance * cosFromLat * Math.sin(heading2), cosDistance - (sinFromLat * sinLat));
        double d3 = dLng;
        return new LatLng(Math.toDegrees(Math.asin(sinLat)), Math.toDegrees(fromLng + dLng));
    }

    public static LatLng computeOffsetOrigin(LatLng to, double distance, double heading) {
        LatLng latLng = to;
        double heading2 = Math.toRadians(heading);
        double distance2 = distance / 6371009.0d;
        double n1 = Math.cos(distance2);
        double n2 = Math.sin(distance2) * Math.cos(heading2);
        double n3 = Math.sin(distance2) * Math.sin(heading2);
        double n4 = Math.sin(Math.toRadians(latLng.latitude));
        double n12 = n1 * n1;
        double discriminant = (((n2 * n2) * n12) + (n12 * n12)) - ((n12 * n4) * n4);
        if (discriminant < 0.0d) {
            return null;
        }
        double d = heading2;
        double b = ((n2 * n4) + Math.sqrt(discriminant)) / ((n1 * n1) + (n2 * n2));
        double d2 = distance2;
        double distance3 = (n4 - (n2 * b)) / n1;
        double fromLatRadians = Math.atan2(distance3, b);
        if (fromLatRadians < -1.5707963267948966d || fromLatRadians > 1.5707963267948966d) {
            b = ((n2 * n4) - Math.sqrt(discriminant)) / ((n1 * n1) + (n2 * n2));
            fromLatRadians = Math.atan2(distance3, b);
        }
        if (fromLatRadians < -1.5707963267948966d) {
            double d3 = distance3;
            double d4 = n1;
            double d5 = n2;
        } else if (fromLatRadians > 1.5707963267948966d) {
            double d6 = b;
            double d7 = distance3;
            double d8 = n1;
            double d9 = n2;
        } else {
            double d10 = b;
            double d11 = distance3;
            double d12 = n1;
            double d13 = n2;
            return new LatLng(Math.toDegrees(fromLatRadians), Math.toDegrees(Math.toRadians(latLng.longitude) - Math.atan2(n3, (Math.cos(fromLatRadians) * n1) - (Math.sin(fromLatRadians) * n2))));
        }
        return null;
    }

    public static LatLng interpolate(LatLng from, LatLng to, double fraction) {
        LatLng latLng = from;
        LatLng latLng2 = to;
        double fromLat = Math.toRadians(latLng.latitude);
        double fromLng = Math.toRadians(latLng.longitude);
        double toLat = Math.toRadians(latLng2.latitude);
        double toLng = Math.toRadians(latLng2.longitude);
        double cosFromLat = Math.cos(fromLat);
        double cosToLat = Math.cos(toLat);
        double angle = computeAngleBetween(from, to);
        double sinAngle = Math.sin(angle);
        if (sinAngle < 1.0E-6d) {
            return latLng;
        }
        double a = Math.sin((1.0d - fraction) * angle) / sinAngle;
        double b = Math.sin(fraction * angle) / sinAngle;
        double x = (a * cosFromLat * Math.cos(fromLng)) + (b * cosToLat * Math.cos(toLng));
        double d = fromLng;
        double fromLng2 = (a * cosFromLat * Math.sin(fromLng)) + (b * cosToLat * Math.sin(toLng));
        double d2 = fromLat;
        double z = (Math.sin(fromLat) * a) + (Math.sin(toLat) * b);
        double d3 = toLat;
        double d4 = x;
        double d5 = z;
        double d6 = fromLng2;
        return new LatLng(Math.toDegrees(Math.atan2(z, Math.sqrt((x * x) + (fromLng2 * fromLng2)))), Math.toDegrees(Math.atan2(fromLng2, x)));
    }

    private static double distanceRadians(double lat1, double lng1, double lat2, double lng2) {
        return MathUtil.arcHav(MathUtil.havDistance(lat1, lat2, lng1 - lng2));
    }

    static double computeAngleBetween(LatLng from, LatLng to) {
        return distanceRadians(Math.toRadians(from.latitude), Math.toRadians(from.longitude), Math.toRadians(to.latitude), Math.toRadians(to.longitude));
    }

    public static double computeDistanceBetween(LatLng from, LatLng to) {
        return computeAngleBetween(from, to) * 6371009.0d;
    }

    public static double computeLength(List<LatLng> path) {
        if (path.size() < 2) {
            return 0.0d;
        }
        double length = 0.0d;
        LatLng prev = (LatLng) path.get(0);
        double prevLat = Math.toRadians(prev.latitude);
        double prevLng = Math.toRadians(prev.longitude);
        for (LatLng point : path) {
            double lat = Math.toRadians(point.latitude);
            double lng = Math.toRadians(point.longitude);
            LatLng latLng = point;
            length += distanceRadians(prevLat, prevLng, lat, lng);
            prevLat = lat;
            prevLng = lng;
        }
        return 6371009.0d * length;
    }

    public static double computeArea(List<LatLng> path) {
        return Math.abs(computeSignedArea(path));
    }

    public static double computeSignedArea(List<LatLng> path) {
        return computeSignedArea(path, 6371009.0d);
    }

    static double computeSignedArea(List<LatLng> path, double radius) {
        int size = path.size();
        if (size < 3) {
            return 0.0d;
        }
        double total = 0.0d;
        LatLng prev = (LatLng) path.get(size - 1);
        double d = 1.5707963267948966d;
        double prevTanLat = Math.tan((1.5707963267948966d - Math.toRadians(prev.latitude)) / 2.0d);
        double prevLng = Math.toRadians(prev.longitude);
        for (LatLng point : path) {
            double tanLat = Math.tan((d - Math.toRadians(point.latitude)) / 2.0d);
            double lng = Math.toRadians(point.longitude);
            LatLng latLng = point;
            total += polarTriangleArea(tanLat, lng, prevTanLat, prevLng);
            prevTanLat = tanLat;
            prevLng = lng;
            d = 1.5707963267948966d;
        }
        return radius * radius * total;
    }

    private static double polarTriangleArea(double tan1, double lng1, double tan2, double lng2) {
        double deltaLng = lng1 - lng2;
        double t = tan1 * tan2;
        return Math.atan2(Math.sin(deltaLng) * t, (Math.cos(deltaLng) * t) + 1.0d) * 2.0d;
    }
}
