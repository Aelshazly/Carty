package com.neovisionaries.bluetooth.ble.advertising;

class ServiceDataBuilder implements ADStructureBuilder {
    ServiceDataBuilder() {
    }

    public ADStructure build(int length, int type, byte[] data) {
        return new ServiceData(length, type, data);
    }
}
