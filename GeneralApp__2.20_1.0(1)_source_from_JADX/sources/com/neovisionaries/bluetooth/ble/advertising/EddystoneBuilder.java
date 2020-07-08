package com.neovisionaries.bluetooth.ble.advertising;

import kotlin.UByte;

class EddystoneBuilder implements ADStructureBuilder {
    EddystoneBuilder() {
    }

    public ADStructure build(int length, int type, byte[] data) {
        if (data == null || data.length < 3 || (data[0] & UByte.MAX_VALUE) != 170 || (data[1] & UByte.MAX_VALUE) != 254) {
            return null;
        }
        int frameType = data[2] & 240;
        if (frameType == 0) {
            return new EddystoneUID(length, type, data);
        }
        if (frameType == 16) {
            return new EddystoneURL(length, type, data);
        }
        if (frameType == 32) {
            return new EddystoneTLM(length, type, data);
        }
        if (frameType != 48) {
            return null;
        }
        return new EddystoneEID(length, type, data);
    }
}
