package com.neovisionaries.bluetooth.ble.advertising;

public class TxPowerLevel extends ADStructure {
    private static final String STRING_FORMAT = "TxPowerLevel(%s%ddBm)";
    private static final long serialVersionUID = 1;

    public TxPowerLevel() {
        this(2, 10, new byte[]{0});
    }

    public TxPowerLevel(int length, int type, byte[] data) {
        super(length, type, data);
    }

    public int getLevel() {
        byte[] data = getData();
        if (data == null || data.length == 0) {
            return 0;
        }
        return data[0];
    }

    public String toString() {
        int level = getLevel();
        Object[] objArr = new Object[2];
        objArr[0] = level >= 0 ? "+" : "";
        objArr[1] = Integer.valueOf(level);
        return String.format(STRING_FORMAT, objArr);
    }
}
