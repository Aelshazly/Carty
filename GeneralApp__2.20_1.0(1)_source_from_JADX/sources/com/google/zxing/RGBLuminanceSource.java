package com.google.zxing;

public final class RGBLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;

    public RGBLuminanceSource(int width, int height, int[] pixels) {
        super(width, height);
        this.dataWidth = width;
        this.dataHeight = height;
        this.left = 0;
        this.top = 0;
        int size = width * height;
        this.luminances = new byte[size];
        for (int offset = 0; offset < size; offset++) {
            int i = pixels[offset];
            int pixel = i;
            int b = pixel & 255;
            this.luminances[offset] = (byte) (((((i >> 16) & 255) + ((pixel >> 7) & 510)) + b) / 4);
        }
    }

    private RGBLuminanceSource(byte[] pixels, int dataWidth2, int dataHeight2, int left2, int top2, int width, int height) {
        super(width, height);
        if (left2 + width > dataWidth2 || top2 + height > dataHeight2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.luminances = pixels;
        this.dataWidth = dataWidth2;
        this.dataHeight = dataHeight2;
        this.left = left2;
        this.top = top2;
    }

    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            StringBuilder sb = new StringBuilder("Requested row is outside the image: ");
            sb.append(y);
            throw new IllegalArgumentException(sb.toString());
        }
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        System.arraycopy(this.luminances, ((this.top + y) * this.dataWidth) + this.left, row, 0, width);
        return row;
    }

    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        if (width == this.dataWidth && height == this.dataHeight) {
            return this.luminances;
        }
        int i = width * height;
        int area = i;
        byte[] matrix = new byte[i];
        int i2 = this.top;
        int i3 = this.dataWidth;
        int inputOffset = (i2 * i3) + this.left;
        if (width == i3) {
            System.arraycopy(this.luminances, inputOffset, matrix, 0, area);
            return matrix;
        }
        for (int y = 0; y < height; y++) {
            System.arraycopy(this.luminances, inputOffset, matrix, y * width, width);
            inputOffset += this.dataWidth;
        }
        return matrix;
    }

    public boolean isCropSupported() {
        return true;
    }

    public LuminanceSource crop(int left2, int top2, int width, int height) {
        RGBLuminanceSource rGBLuminanceSource = new RGBLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, this.left + left2, this.top + top2, width, height);
        return rGBLuminanceSource;
    }
}
