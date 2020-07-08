package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import androidx.collection.LongSparseArray;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);
    private static final int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
    private static final float[] DEFAULT_GRADIENT_START_POINTS = {0.2f, 1.0f};
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.7d;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    static final double WORLD_WIDTH = 1.0d;
    private Bounds mBounds;
    private int[] mColorMap;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private PointQuadTree<WeightedLatLng> mTree;

    public static class Builder {
        /* access modifiers changed from: private */
        public Collection<WeightedLatLng> data;
        /* access modifiers changed from: private */
        public Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        /* access modifiers changed from: private */
        public double opacity = 0.7d;
        /* access modifiers changed from: private */
        public int radius = 20;

        public Builder data(Collection<LatLng> val) {
            return weightedData(HeatmapTileProvider.wrapData(val));
        }

        public Builder weightedData(Collection<WeightedLatLng> val) {
            this.data = val;
            if (!this.data.isEmpty()) {
                return this;
            }
            throw new IllegalArgumentException("No input points.");
        }

        public Builder radius(int val) {
            this.radius = val;
            int i = this.radius;
            if (i >= 10 && i <= 50) {
                return this;
            }
            throw new IllegalArgumentException("Radius not within bounds.");
        }

        public Builder gradient(Gradient val) {
            this.gradient = val;
            return this;
        }

        public Builder opacity(double val) {
            this.opacity = val;
            double d = this.opacity;
            if (d >= 0.0d && d <= 1.0d) {
                return this;
            }
            throw new IllegalArgumentException("Opacity must be in range [0, 1]");
        }

        public HeatmapTileProvider build() {
            if (this.data != null) {
                return new HeatmapTileProvider(this);
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        this.mOpacity = builder.opacity;
        int i = this.mRadius;
        this.mKernel = generateKernel(i, ((double) i) / 3.0d);
        setGradient(this.mGradient);
        setWeightedData(this.mData);
    }

    public void setWeightedData(Collection<WeightedLatLng> data) {
        this.mData = data;
        if (!this.mData.isEmpty()) {
            this.mBounds = getBounds(this.mData);
            this.mTree = new PointQuadTree<>(this.mBounds);
            for (WeightedLatLng l : this.mData) {
                this.mTree.add(l);
            }
            this.mMaxIntensity = getMaxIntensities(this.mRadius);
            return;
        }
        throw new IllegalArgumentException("No input points.");
    }

    public void setData(Collection<LatLng> data) {
        setWeightedData(wrapData(data));
    }

    /* access modifiers changed from: private */
    public static Collection<WeightedLatLng> wrapData(Collection<LatLng> data) {
        ArrayList<WeightedLatLng> weightedData = new ArrayList<>();
        for (LatLng l : data) {
            weightedData.add(new WeightedLatLng(l));
        }
        return weightedData;
    }

    public Tile getTile(int x, int y, int zoom) {
        int i = x;
        int i2 = y;
        int i3 = zoom;
        double tileWidth = 1.0d / Math.pow(2.0d, (double) i3);
        int i4 = this.mRadius;
        double padding = (((double) i4) * tileWidth) / 512.0d;
        double tileWidthPadded = (2.0d * padding) + tileWidth;
        double bucketWidth = tileWidthPadded / ((double) ((i4 * 2) + 512));
        double minX = (((double) i) * tileWidth) - padding;
        double d = tileWidthPadded;
        double maxX = (((double) (i + 1)) * tileWidth) + padding;
        double bucketWidth2 = bucketWidth;
        double minY = (((double) i2) * tileWidth) - padding;
        double maxY = (((double) (i2 + 1)) * tileWidth) + padding;
        double xOffset = 0.0d;
        Collection<WeightedLatLng> wrappedPoints = new ArrayList<>();
        if (minX < 0.0d) {
            Bounds bounds = new Bounds(minX + 1.0d, 1.0d, minY, maxY);
            xOffset = -1.0d;
            double d2 = tileWidth;
            wrappedPoints = this.mTree.search(bounds);
        } else {
            if (maxX > 1.0d) {
                Bounds bounds2 = new Bounds(0.0d, maxX - 1.0d, minY, maxY);
                xOffset = 1.0d;
                wrappedPoints = this.mTree.search(bounds2);
            }
        }
        Bounds paddedBounds = new Bounds(minX, maxX, minY, maxY);
        Bounds bounds3 = new Bounds(this.mBounds.minX - padding, this.mBounds.maxX + padding, this.mBounds.minY - padding, this.mBounds.maxY + padding);
        Bounds paddedBounds2 = bounds3;
        if (!paddedBounds.intersects(paddedBounds2)) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> search = this.mTree.search(paddedBounds);
        if (search.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        int i5 = this.mRadius;
        double[][] intensity = (double[][]) Array.newInstance(double.class, new int[]{(i5 * 2) + 512, (i5 * 2) + 512});
        for (WeightedLatLng w : search) {
            Bounds paddedBounds3 = paddedBounds2;
            Point p = w.getPoint();
            double maxX2 = maxX;
            double padding2 = padding;
            int bucketY = (int) ((p.f90y - minY) / bucketWidth2);
            double[] dArr = intensity[(int) ((p.f89x - minX) / bucketWidth2)];
            dArr[bucketY] = dArr[bucketY] + w.getIntensity();
            paddedBounds2 = paddedBounds3;
            maxX = maxX2;
            padding = padding2;
        }
        double d3 = maxX;
        double d4 = padding;
        Iterator it = wrappedPoints.iterator();
        while (it.hasNext()) {
            WeightedLatLng w2 = (WeightedLatLng) it.next();
            Point p2 = w2.getPoint();
            Iterator it2 = it;
            Collection collection = search;
            int bucketY2 = (int) ((p2.f90y - minY) / bucketWidth2);
            double[] dArr2 = intensity[(int) (((p2.f89x + xOffset) - minX) / bucketWidth2)];
            dArr2[bucketY2] = dArr2[bucketY2] + w2.getIntensity();
            search = collection;
            it = it2;
        }
        Collection collection2 = search;
        Bounds bounds4 = paddedBounds;
        double[][] dArr3 = intensity;
        return convertBitmap(colorize(convolve(intensity, this.mKernel), this.mColorMap, this.mMaxIntensity[i3]));
    }

    public void setGradient(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        int i = this.mRadius;
        this.mKernel = generateKernel(i, ((double) i) / 3.0d);
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setOpacity(double opacity) {
        this.mOpacity = opacity;
        setGradient(this.mGradient);
    }

    private double[] getMaxIntensities(int radius) {
        double[] maxIntensityArray = new double[22];
        for (int i = 5; i < 11; i++) {
            maxIntensityArray[i] = getMaxValue(this.mData, this.mBounds, radius, (int) (Math.pow(2.0d, (double) (i - 3)) * 1280.0d));
            if (i == 5) {
                for (int j = 0; j < i; j++) {
                    maxIntensityArray[j] = maxIntensityArray[i];
                }
            }
        }
        for (int i2 = 11; i2 < 22; i2++) {
            maxIntensityArray[i2] = maxIntensityArray[10];
        }
        return maxIntensityArray;
    }

    private static Tile convertBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, stream);
        return new Tile(512, 512, stream.toByteArray());
    }

    static Bounds getBounds(Collection<WeightedLatLng> points) {
        Iterator<WeightedLatLng> iter = points.iterator();
        WeightedLatLng first = (WeightedLatLng) iter.next();
        double minX = first.getPoint().f89x;
        double maxX = first.getPoint().f89x;
        double minY = first.getPoint().f90y;
        double maxY = first.getPoint().f90y;
        while (iter.hasNext()) {
            WeightedLatLng l = (WeightedLatLng) iter.next();
            double x = l.getPoint().f89x;
            double y = l.getPoint().f90y;
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
        Bounds bounds = new Bounds(minX, maxX, minY, maxY);
        return bounds;
    }

    static double[] generateKernel(int radius, double sd) {
        double[] kernel = new double[((radius * 2) + 1)];
        for (int i = -radius; i <= radius; i++) {
            kernel[i + radius] = Math.exp(((double) ((-i) * i)) / ((2.0d * sd) * sd));
        }
        return kernel;
    }

    static double[][] convolve(double[][] grid, double[] kernel) {
        double[][] dArr = grid;
        double[] dArr2 = kernel;
        Class<double> cls = double.class;
        int radius = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int dimOld = dArr.length;
        int dim = dimOld - (radius * 2);
        int lowerLimit = radius;
        int upperLimit = (radius + dim) - 1;
        double[][] intermediate = (double[][]) Array.newInstance(cls, new int[]{dimOld, dimOld});
        int x = 0;
        while (true) {
            double d = 0.0d;
            if (x >= dimOld) {
                break;
            }
            int y = 0;
            while (y < dimOld) {
                double val = dArr[x][y];
                if (val != d) {
                    int xUpperLimit = (upperLimit < x + radius ? upperLimit : x + radius) + 1;
                    for (int x2 = lowerLimit > x - radius ? lowerLimit : x - radius; x2 < xUpperLimit; x2++) {
                        double[] dArr3 = intermediate[x2];
                        dArr3[y] = dArr3[y] + (dArr2[x2 - (x - radius)] * val);
                    }
                }
                y++;
                d = 0.0d;
            }
            x++;
        }
        double[][] outputGrid = (double[][]) Array.newInstance(cls, new int[]{dim, dim});
        for (int x3 = lowerLimit; x3 < upperLimit + 1; x3++) {
            int y2 = 0;
            while (y2 < dimOld) {
                double val2 = intermediate[x3][y2];
                if (val2 != 0.0d) {
                    int yUpperLimit = (upperLimit < y2 + radius ? upperLimit : y2 + radius) + 1;
                    for (int y22 = lowerLimit > y2 - radius ? lowerLimit : y2 - radius; y22 < yUpperLimit; y22++) {
                        double[] dArr4 = outputGrid[x3 - radius];
                        int i = y22 - radius;
                        dArr4[i] = dArr4[i] + (dArr2[y22 - (y2 - radius)] * val2);
                    }
                }
                y2++;
            }
        }
        return outputGrid;
    }

    static Bitmap colorize(double[][] grid, int[] colorMap, double max) {
        double[][] dArr = grid;
        int[] iArr = colorMap;
        int maxColor = iArr[iArr.length - 1];
        double colorMapScaling = ((double) (iArr.length - 1)) / max;
        int dim = dArr.length;
        int[] colors = new int[(dim * dim)];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                double val = dArr[j][i];
                int index = (i * dim) + j;
                int col = (int) (val * colorMapScaling);
                if (val == 0.0d) {
                    colors[index] = 0;
                } else if (col < iArr.length) {
                    colors[index] = iArr[col];
                } else {
                    colors[index] = maxColor;
                }
            }
        }
        Bitmap tile = Bitmap.createBitmap(dim, dim, Config.ARGB_8888);
        tile.setPixels(colors, 0, dim, 0, 0, dim, dim);
        return tile;
    }

    static double getMaxValue(Collection<WeightedLatLng> points, Bounds bounds, int radius, int screenDim) {
        Bounds bounds2 = bounds;
        double minX = bounds2.minX;
        double maxX = bounds2.maxX;
        double minY = bounds2.minY;
        double y = bounds2.maxY;
        double scale = ((double) ((int) (((double) (screenDim / (radius * 2))) + 0.5d))) / (maxX - minX > y - minY ? maxX - minX : y - minY);
        LongSparseArray<LongSparseArray<Double>> buckets = new LongSparseArray<>();
        double max = 0.0d;
        for (WeightedLatLng l : points) {
            double maxX2 = maxX;
            double x = l.getPoint().f89x;
            double maxY = y;
            double minX2 = minX;
            int xBucket = (int) ((x - minX) * scale);
            int yBucket = (int) ((l.getPoint().f90y - minY) * scale);
            double d = x;
            LongSparseArray longSparseArray = (LongSparseArray) buckets.get((long) xBucket);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                buckets.put((long) xBucket, longSparseArray);
            }
            Double value = (Double) longSparseArray.get((long) yBucket);
            if (value == null) {
                value = Double.valueOf(0.0d);
            }
            Double value2 = Double.valueOf(value.doubleValue() + l.getIntensity());
            double minY2 = minY;
            longSparseArray.put((long) yBucket, value2);
            if (value2.doubleValue() > max) {
                max = value2.doubleValue();
            }
            Bounds bounds3 = bounds;
            maxX = maxX2;
            y = maxY;
            minX = minX2;
            minY = minY2;
        }
        return max;
    }
}
