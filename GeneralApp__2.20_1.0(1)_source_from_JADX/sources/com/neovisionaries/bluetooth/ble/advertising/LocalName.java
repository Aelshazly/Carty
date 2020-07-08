package com.neovisionaries.bluetooth.ble.advertising;

import java.io.UnsupportedEncodingException;

public class LocalName extends ADStructure {
    private static final int COMPLETE = 9;
    private static final int SHORTENED = 8;
    private static final String STRING_FORMAT = "LocalName(%s,%s)";
    private static final long serialVersionUID = 1;
    private String mLocalName;

    public LocalName() {
        this(1, 9, null);
    }

    public LocalName(int length, int type, byte[] data) {
        super(length, type, data);
        parse(data);
    }

    private void parse(byte[] data) {
        if (data != null && data.length >= 1) {
            try {
                this.mLocalName = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
    }

    public boolean isShortened() {
        return getType() == 8;
    }

    public boolean isComplete() {
        return getType() == 9;
    }

    public String getLocalName() {
        return this.mLocalName;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = isShortened() ? "SHORTENED" : "COMPLETE";
        objArr[1] = this.mLocalName;
        return String.format(STRING_FORMAT, objArr);
    }
}
