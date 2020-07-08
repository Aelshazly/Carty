package com.neovisionaries.bluetooth.ble.advertising;

public class ADManufacturerSpecific extends ADStructure {
    private static final String STRING_FORMAT = "ADManufacturerSpecific(Length=%d,Type=0x%02X,CompanyID=0x%04X)";
    private static final long serialVersionUID = 1;
    private int mCompanyId;

    public ADManufacturerSpecific() {
        this(3, 255, new byte[]{-1, -1}, 65535);
    }

    public ADManufacturerSpecific(int length, int type, byte[] data, int companyId) {
        super(length, type, data);
        this.mCompanyId = companyId;
    }

    public int getCompanyId() {
        return this.mCompanyId;
    }

    public void setCompanyId(int companyId) {
        this.mCompanyId = companyId;
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{Integer.valueOf(getLength()), Integer.valueOf(getType()), Integer.valueOf(this.mCompanyId)});
    }
}
