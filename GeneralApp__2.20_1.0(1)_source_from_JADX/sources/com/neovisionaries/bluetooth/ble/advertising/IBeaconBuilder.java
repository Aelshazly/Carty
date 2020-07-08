package com.neovisionaries.bluetooth.ble.advertising;

class IBeaconBuilder implements ADManufacturerSpecificBuilder {
    IBeaconBuilder() {
    }

    public ADManufacturerSpecific build(int length, int type, byte[] data, int companyId) {
        return IBeacon.create(length, type, data, companyId);
    }
}
