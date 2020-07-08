package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    private class ColorInterval {
        /* access modifiers changed from: private */
        public final int color1;
        /* access modifiers changed from: private */
        public final int color2;
        /* access modifiers changed from: private */
        public final float duration;

        private ColorInterval(int color12, int color22, float duration2) {
            this.color1 = color12;
            this.color2 = color22;
            this.duration = duration2;
        }
    }

    public Gradient(int[] colors, float[] startPoints) {
        this(colors, startPoints, 1000);
    }

    public Gradient(int[] colors, float[] startPoints, int colorMapSize) {
        if (colors.length != startPoints.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        } else if (colors.length != 0) {
            int i = 1;
            while (i < startPoints.length) {
                if (startPoints[i] > startPoints[i - 1]) {
                    i++;
                } else {
                    throw new IllegalArgumentException("startPoints should be in increasing order");
                }
            }
            this.mColorMapSize = colorMapSize;
            this.mColors = new int[colors.length];
            this.mStartPoints = new float[startPoints.length];
            System.arraycopy(colors, 0, this.mColors, 0, colors.length);
            System.arraycopy(startPoints, 0, this.mStartPoints, 0, startPoints.length);
        } else {
            throw new IllegalArgumentException("No colors have been defined");
        }
    }

    private HashMap<Integer, ColorInterval> generateColorIntervals() {
        HashMap<Integer, ColorInterval> colorIntervals = new HashMap<>();
        if (this.mStartPoints[0] != 0.0f) {
            int initialColor = Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0]));
            Integer valueOf = Integer.valueOf(0);
            ColorInterval colorInterval = new ColorInterval(initialColor, this.mColors[0], ((float) this.mColorMapSize) * this.mStartPoints[0]);
            colorIntervals.put(valueOf, colorInterval);
        }
        for (int i = 1; i < this.mColors.length; i++) {
            Integer valueOf2 = Integer.valueOf((int) (((float) this.mColorMapSize) * this.mStartPoints[i - 1]));
            int[] iArr = this.mColors;
            int i2 = iArr[i - 1];
            int i3 = iArr[i];
            float f = (float) this.mColorMapSize;
            float[] fArr = this.mStartPoints;
            ColorInterval colorInterval2 = new ColorInterval(i2, i3, (fArr[i] - fArr[i - 1]) * f);
            colorIntervals.put(valueOf2, colorInterval2);
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int i4 = fArr2.length - 1;
            Integer valueOf3 = Integer.valueOf((int) (((float) this.mColorMapSize) * fArr2[i4]));
            int[] iArr2 = this.mColors;
            ColorInterval colorInterval3 = new ColorInterval(iArr2[i4], iArr2[i4], ((float) this.mColorMapSize) * (1.0f - this.mStartPoints[i4]));
            colorIntervals.put(valueOf3, colorInterval3);
        }
        return colorIntervals;
    }

    /* access modifiers changed from: 0000 */
    public int[] generateColorMap(double opacity) {
        HashMap<Integer, ColorInterval> colorIntervals = generateColorIntervals();
        int[] colorMap = new int[this.mColorMapSize];
        ColorInterval interval = (ColorInterval) colorIntervals.get(Integer.valueOf(0));
        int start = 0;
        for (int i = 0; i < this.mColorMapSize; i++) {
            if (colorIntervals.containsKey(Integer.valueOf(i))) {
                interval = (ColorInterval) colorIntervals.get(Integer.valueOf(i));
                start = i;
            }
            colorMap[i] = interpolateColor(interval.color1, interval.color2, ((float) (i - start)) / interval.duration);
        }
        if (opacity != 1.0d) {
            for (int i2 = 0; i2 < this.mColorMapSize; i2++) {
                int c = colorMap[i2];
                colorMap[i2] = Color.argb((int) (((double) Color.alpha(c)) * opacity), Color.red(c), Color.green(c), Color.blue(c));
            }
        }
        return colorMap;
    }

    static int interpolateColor(int color1, int color2, float ratio) {
        int alpha = (int) ((((float) (Color.alpha(color2) - Color.alpha(color1))) * ratio) + ((float) Color.alpha(color1)));
        float[] hsv1 = new float[3];
        Color.RGBToHSV(Color.red(color1), Color.green(color1), Color.blue(color1), hsv1);
        float[] hsv2 = new float[3];
        Color.RGBToHSV(Color.red(color2), Color.green(color2), Color.blue(color2), hsv2);
        if (hsv1[0] - hsv2[0] > 180.0f) {
            hsv2[0] = hsv2[0] + 360.0f;
        } else if (hsv2[0] - hsv1[0] > 180.0f) {
            hsv1[0] = hsv1[0] + 360.0f;
        }
        float[] result = new float[3];
        for (int i = 0; i < 3; i++) {
            result[i] = ((hsv2[i] - hsv1[i]) * ratio) + hsv1[i];
        }
        return Color.HSVToColor(alpha, result);
    }
}
