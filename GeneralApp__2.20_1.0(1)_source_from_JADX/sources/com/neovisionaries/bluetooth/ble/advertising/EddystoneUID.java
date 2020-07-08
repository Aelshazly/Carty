package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.advertising.Eddystone.FrameType;
import com.neovisionaries.bluetooth.ble.util.Bytes;

public class EddystoneUID extends Eddystone {
    private static final String STRING_FORMAT = "EddyStoneUID(TxPower=%d,NamespaceId=%s,InstanceId=%s)";
    private static final long serialVersionUID = 1;
    private transient byte[] mBeaconId;
    private transient String mBeaconIdAsString;
    private transient byte[] mInstanceId;
    private transient String mInstanceIdAsString;
    private transient byte[] mNamespaceId;
    private transient String mNamespaceIdAsString;
    private transient String mString;
    private final int mTxPower;

    public EddystoneUID() {
        this(23, 22, new byte[]{-86, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public EddystoneUID(int length, int type, byte[] data) {
        super(length, type, data, FrameType.UID);
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

    public byte[] getNamespaceId() {
        if (this.mNamespaceId == null) {
            this.mNamespaceId = Bytes.copyOfRange(getData(), 4, 14);
        }
        return this.mNamespaceId;
    }

    public String getNamespaceIdAsString() {
        if (this.mNamespaceIdAsString == null) {
            this.mNamespaceIdAsString = Bytes.toHexString(getNamespaceId(), true);
        }
        return this.mNamespaceIdAsString;
    }

    public byte[] getInstanceId() {
        if (this.mInstanceId == null) {
            this.mInstanceId = Bytes.copyOfRange(getData(), 14, 20);
        }
        return this.mInstanceId;
    }

    public String getInstanceIdAsString() {
        if (this.mInstanceIdAsString == null) {
            this.mInstanceIdAsString = Bytes.toHexString(getInstanceId(), true);
        }
        return this.mInstanceIdAsString;
    }

    public byte[] getBeaconId() {
        if (this.mBeaconId == null) {
            this.mBeaconId = Bytes.copyOfRange(getData(), 4, 20);
        }
        return this.mBeaconId;
    }

    public String getBeaconIdAsString() {
        if (this.mBeaconIdAsString == null) {
            this.mBeaconIdAsString = Bytes.toHexString(getBeaconId(), true);
        }
        return this.mBeaconIdAsString;
    }

    public String toString() {
        String str = this.mString;
        if (str != null) {
            return str;
        }
        this.mString = String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mTxPower), getNamespaceIdAsString(), getInstanceIdAsString()});
        return this.mString;
    }
}
