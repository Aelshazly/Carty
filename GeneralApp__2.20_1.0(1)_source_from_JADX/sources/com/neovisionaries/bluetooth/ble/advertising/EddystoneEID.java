package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.advertising.Eddystone.FrameType;
import com.neovisionaries.bluetooth.ble.util.Bytes;

public class EddystoneEID extends Eddystone {
    private static final String STRING_FORMAT = "EddyStoneEID(TxPower=%d,EID=%s)";
    private static final long serialVersionUID = 1;
    private transient byte[] mEID;
    private transient String mEIDAsString;
    private transient String mString;
    private final int mTxPower;

    public EddystoneEID() {
        this(13, 22, new byte[]{-86, -2, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public EddystoneEID(int length, int type, byte[] data) {
        super(length, type, data, FrameType.EID);
        this.mTxPower = extractTxPower(data);
    }

    private int extractTxPower(byte[] data) {
        if (4 <= data.length) {
            return data[3];
        }
        return 0;
    }

    public int getTxPower() {
        return this.mTxPower;
    }

    public byte[] getEID() {
        if (this.mEID == null) {
            this.mEID = Bytes.copyOfRange(getData(), 4, 12);
        }
        return this.mEID;
    }

    public String getEIDAsString() {
        if (this.mEIDAsString == null) {
            this.mEIDAsString = Bytes.toHexString(getEID(), true);
        }
        return this.mEIDAsString;
    }

    public String toString() {
        if (this.mString == null) {
            this.mString = String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mTxPower), getEIDAsString()});
        }
        return this.mString;
    }
}
