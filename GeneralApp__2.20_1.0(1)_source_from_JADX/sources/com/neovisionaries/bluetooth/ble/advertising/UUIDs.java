package com.neovisionaries.bluetooth.ble.advertising;

import java.util.UUID;

public class UUIDs extends ADStructure {
    private static final String STRING_FORMAT = "UUIDs(%s)";
    private static final long serialVersionUID = 1;
    private UUID[] mUUIDs;

    public UUIDs() {
    }

    public UUIDs(int length, int type, byte[] data, UUID... uuids) {
        super(length, type, data);
        this.mUUIDs = uuids;
    }

    public UUID[] getUUIDs() {
        return this.mUUIDs;
    }

    public void setUUIDs(UUID[] uuids) {
        this.mUUIDs = uuids;
    }

    public String toString() {
        UUID[] uuidArr = this.mUUIDs;
        String str = STRING_FORMAT;
        if (uuidArr == null) {
            return String.format(str, new Object[]{"null"});
        }
        StringBuilder builder = new StringBuilder();
        for (UUID uuid : this.mUUIDs) {
            builder.append(uuid);
            builder.append(",");
        }
        if (builder.length() != 0) {
            builder.setLength(builder.length() - 1);
        }
        return String.format(str, new Object[]{builder.toString()});
    }
}
