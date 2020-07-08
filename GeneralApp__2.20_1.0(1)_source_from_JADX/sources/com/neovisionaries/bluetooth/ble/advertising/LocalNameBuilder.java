package com.neovisionaries.bluetooth.ble.advertising;

class LocalNameBuilder implements ADStructureBuilder {
    LocalNameBuilder() {
    }

    public ADStructure build(int length, int type, byte[] data) {
        return new LocalName(length, type, data);
    }
}
