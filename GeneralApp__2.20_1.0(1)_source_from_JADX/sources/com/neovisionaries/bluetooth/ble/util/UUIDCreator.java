package com.neovisionaries.bluetooth.ble.util;

import java.util.UUID;
import kotlin.UByte;

public class UUIDCreator {
    private static final String BASE_UUID_FORMAT = "%02x%02x%02x%02x-0000-1000-8000-00805f9b34fb";
    private static final String GENERIC_UUID_FORMAT = "%02x%02x%02x%02x-%02x%02x-%02x%02x-%02x%02x-%02x%02x%02x%02x%02x%02x";

    private UUIDCreator() {
    }

    public static UUID from16(byte[] data) {
        return from16(data, 0);
    }

    public static UUID from16(byte[] data, int offset) {
        return from16(data, offset, true);
    }

    public static UUID from16(byte[] data, int offset, boolean littleEndian) {
        int v3;
        int v2;
        if (data == null || offset < 0 || data.length <= offset + 1 || Integer.MAX_VALUE == offset) {
            return null;
        }
        if (littleEndian) {
            v2 = data[offset + 1] & 255;
            v3 = data[offset + 0] & 255;
        } else {
            v2 = data[offset + 0] & 255;
            v3 = data[offset + 1] & 255;
        }
        return fromBase(0, 0, v2, v3);
    }

    public static UUID from32(byte[] data) {
        return from32(data, 0);
    }

    public static UUID from32(byte[] data, int offset) {
        return from32(data, offset, true);
    }

    public static UUID from32(byte[] data, int offset, boolean littleEndian) {
        int v3;
        int v2;
        int v1;
        int v0;
        if (data == null || offset < 0 || data.length <= offset + 3 || 2147483644 < offset) {
            return null;
        }
        if (littleEndian) {
            v0 = data[offset + 3] & 255;
            v1 = data[offset + 2] & 255;
            v2 = data[offset + 1] & 255;
            v3 = data[offset + 0] & 255;
        } else {
            v0 = data[offset + 0] & 255;
            v1 = data[offset + 1] & 255;
            v2 = data[offset + 2] & 255;
            v3 = data[offset + 3] & 255;
        }
        return fromBase(v0, v1, v2, v3);
    }

    private static UUID fromBase(int v0, int v1, int v2, int v3) {
        return UUID.fromString(String.format(BASE_UUID_FORMAT, new Object[]{Integer.valueOf(v0), Integer.valueOf(v1), Integer.valueOf(v2), Integer.valueOf(v3)}));
    }

    public static UUID from128(byte[] data) {
        return from128(data, 0);
    }

    public static UUID from128(byte[] data, int offset) {
        return from128(data, offset, true);
    }

    public static UUID from128(byte[] data, int offset, boolean littleEndian) {
        String uuid;
        byte[] bArr = data;
        int i = offset;
        if (bArr == null || i < 0 || bArr.length <= i + 15 || 2147483632 < i) {
            return null;
        }
        if (littleEndian) {
            uuid = String.format(GENERIC_UUID_FORMAT, new Object[]{Integer.valueOf(bArr[i + 15] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 14] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 13] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 12] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 11] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 10] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 9] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 8] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 7] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 6] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 5] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 4] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 3] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 2] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 1] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 0] & UByte.MAX_VALUE)});
        } else {
            uuid = String.format(GENERIC_UUID_FORMAT, new Object[]{Integer.valueOf(bArr[i + 0] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 1] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 2] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 3] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 4] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 5] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 6] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 7] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 8] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 9] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 10] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 11] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 12] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 13] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 14] & UByte.MAX_VALUE), Integer.valueOf(bArr[i + 15] & UByte.MAX_VALUE)});
        }
        return UUID.fromString(uuid);
    }
}
