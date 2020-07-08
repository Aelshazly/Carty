package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.util.UUIDCreator;
import java.util.UUID;

public class ServiceData extends ADStructure {
    private static final String STRING_FORMAT = "ServiceData(ServiceUUID=%s)";
    private static final long serialVersionUID = 1;
    private final UUID mServiceUUID;

    public ServiceData() {
        this(1, 22, null);
    }

    public ServiceData(int length, int type, byte[] data) {
        super(length, type, data);
        this.mServiceUUID = extractServiceUUID(type, data);
    }

    private UUID extractServiceUUID(int type, byte[] data) {
        if (type == 22) {
            return UUIDCreator.from16(data);
        }
        if (type == 32) {
            return UUIDCreator.from32(data);
        }
        if (type != 33) {
            return null;
        }
        return UUIDCreator.from128(data);
    }

    public UUID getServiceUUID() {
        return this.mServiceUUID;
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{this.mServiceUUID});
    }
}
