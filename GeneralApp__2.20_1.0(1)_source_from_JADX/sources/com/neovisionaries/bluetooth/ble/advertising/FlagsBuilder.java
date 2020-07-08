package com.neovisionaries.bluetooth.ble.advertising;

class FlagsBuilder implements ADStructureBuilder {
    FlagsBuilder() {
    }

    public ADStructure build(int length, int type, byte[] data) {
        return new Flags(length, type, data);
    }
}
