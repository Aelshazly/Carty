package com.neovisionaries.bluetooth.ble.advertising;

class UcodeBuilder implements ADManufacturerSpecificBuilder {
    UcodeBuilder() {
    }

    public ADManufacturerSpecific build(int length, int type, byte[] data, int companyId) {
        return Ucode.create(length, type, data, companyId);
    }
}
