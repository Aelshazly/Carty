package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.util.UUIDCreator;
import java.util.UUID;

class UUIDsBuilder implements ADStructureBuilder {
    UUIDsBuilder() {
    }

    public ADStructure build(int length, int type, byte[] data) {
        UUID[] uuids;
        if (type != 20) {
            if (type != 21) {
                if (type != 31) {
                    switch (type) {
                        case 2:
                        case 3:
                            break;
                        case 4:
                        case 5:
                            break;
                        case 6:
                        case 7:
                            break;
                        default:
                            return null;
                    }
                }
                uuids = extract32(data);
                return new UUIDs(length, type, data, uuids);
            }
            uuids = extract128(data);
            return new UUIDs(length, type, data, uuids);
        }
        uuids = extract16(data);
        return new UUIDs(length, type, data, uuids);
    }

    private UUID[] extract16(byte[] data) {
        if (data == null) {
            return null;
        }
        int count = data.length / 2;
        UUID[] uuids = new UUID[count];
        for (int i = 0; i < count; i++) {
            uuids[i] = UUIDCreator.from16(data, i * 2);
        }
        return uuids;
    }

    private UUID[] extract32(byte[] data) {
        if (data == null) {
            return null;
        }
        int count = data.length / 4;
        UUID[] uuids = new UUID[count];
        for (int i = 0; i < count; i++) {
            uuids[i] = UUIDCreator.from32(data, i * 4);
        }
        return uuids;
    }

    private UUID[] extract128(byte[] data) {
        if (data == null) {
            return null;
        }
        int count = data.length / 16;
        UUID[] uuids = new UUID[count];
        for (int i = 0; i < count; i++) {
            uuids[i] = UUIDCreator.from128(data, i * 16);
        }
        return uuids;
    }
}
