package com.neovisionaries.bluetooth.ble.util;

import kotlin.UByte;

public class Bytes {
    private static final char[] LOWER_HEX_CHARS = "0123456789abcdef".toCharArray();
    private static final char[] UPPER_HEX_CHARS = "0123456789ABCDEF".toCharArray();

    private Bytes() {
    }

    public static int parseBE2BytesAsInt(byte[] data, int offset) {
        return ((data[offset + 0] & UByte.MAX_VALUE) << 8) | ((data[offset + 1] & UByte.MAX_VALUE) << 0);
    }

    public static int parseBE4BytesAsInt(byte[] data, int offset) {
        return ((data[offset + 0] & UByte.MAX_VALUE) << 24) | ((data[offset + 1] & UByte.MAX_VALUE) << 16) | ((data[offset + 2] & UByte.MAX_VALUE) << 8) | (data[offset + 3] & 255);
    }

    public static long parseBE4BytesAsUnsigned(byte[] data, int offset) {
        return (((long) (data[offset + 0] & UByte.MAX_VALUE)) << 24) | (((long) (data[offset + 1] & UByte.MAX_VALUE)) << 16) | (((long) (data[offset + 2] & UByte.MAX_VALUE)) << 8) | (((long) (data[offset + 3] & UByte.MAX_VALUE)) << 0);
    }

    public static float convertFixedPointToFloat(byte[] data, int offset) {
        return ((float) data[offset]) + (((float) (data[offset + 1] & UByte.MAX_VALUE)) / 256.0f);
    }

    public static String toHexString(byte[] data, boolean upper) {
        if (data == null) {
            return null;
        }
        char[] table = upper ? UPPER_HEX_CHARS : LOWER_HEX_CHARS;
        char[] chars = new char[(data.length * 2)];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = table[(data[i] & 240) >> 4];
            chars[(i * 2) + 1] = table[data[i] & 15];
        }
        return new String(chars);
    }

    public static byte[] copyOfRange(byte[] source, int from, int to) {
        if (source == null || from < 0 || to < 0) {
            return null;
        }
        int length = to - from;
        if (length < 0 || source.length < from + length) {
            return null;
        }
        byte[] destination = new byte[length];
        System.arraycopy(source, from, destination, 0, length);
        return destination;
    }
}
