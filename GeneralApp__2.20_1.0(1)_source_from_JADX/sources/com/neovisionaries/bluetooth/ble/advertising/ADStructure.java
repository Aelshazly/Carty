package com.neovisionaries.bluetooth.ble.advertising;

import java.io.Serializable;

public class ADStructure implements Serializable {
    private static final String STRING_FORMAT = "ADStructure(Length=%d,Type=0x%02X)";
    private static final long serialVersionUID = 1;
    private byte[] mData;
    private int mLength;
    private int mType;

    public ADStructure() {
    }

    public ADStructure(int length, int type, byte[] data) {
        this.mLength = length;
        this.mType = type;
        this.mData = data;
    }

    public int getLength() {
        return this.mLength;
    }

    public void setLength(int length) {
        this.mLength = length;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public byte[] getData() {
        return this.mData;
    }

    public void setData(byte[] data) {
        this.mData = data;
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mLength), Integer.valueOf(this.mType)});
    }
}
