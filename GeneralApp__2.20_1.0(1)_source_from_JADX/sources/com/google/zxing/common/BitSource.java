package com.google.zxing.common;

public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bytes2) {
        this.bytes = bytes2;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int numBits) {
        if (numBits <= 0 || numBits > 32 || numBits > available()) {
            throw new IllegalArgumentException(String.valueOf(numBits));
        }
        int result = 0;
        int i = this.bitOffset;
        if (i > 0) {
            int bitsLeft = 8 - i;
            int toRead = numBits < bitsLeft ? numBits : bitsLeft;
            int bitsToNotRead = bitsLeft - toRead;
            int mask = (255 >> (8 - toRead)) << bitsToNotRead;
            byte[] bArr = this.bytes;
            int i2 = this.byteOffset;
            result = (bArr[i2] & mask) >> bitsToNotRead;
            numBits -= toRead;
            this.bitOffset += toRead;
            if (this.bitOffset == 8) {
                this.bitOffset = 0;
                this.byteOffset = i2 + 1;
            }
        }
        if (numBits <= 0) {
            return result;
        }
        while (numBits >= 8) {
            int i3 = result << 8;
            byte[] bArr2 = this.bytes;
            int i4 = this.byteOffset;
            result = i3 | (bArr2[i4] & 255);
            this.byteOffset = i4 + 1;
            numBits -= 8;
        }
        if (numBits <= 0) {
            return result;
        }
        int bitsToNotRead2 = 8 - numBits;
        int result2 = (result << numBits) | ((this.bytes[this.byteOffset] & ((255 >> bitsToNotRead2) << bitsToNotRead2)) >> bitsToNotRead2);
        this.bitOffset += numBits;
        return result2;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
