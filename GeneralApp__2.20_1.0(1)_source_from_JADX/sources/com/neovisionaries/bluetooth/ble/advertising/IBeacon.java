package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.util.Bytes;
import com.neovisionaries.bluetooth.ble.util.UUIDCreator;
import java.util.UUID;

public class IBeacon extends ADManufacturerSpecific {
    private static final int MAJOR_INDEX = 20;
    private static final int MINOR_INDEX = 22;
    private static final int POWER_INDEX = 24;
    private static final String STRING_FORMAT = "iBeacon(UUID=%s,Major=%d,Minor=%d,Power=%d)";
    private static final int UUID_INDEX = 4;
    private static final long serialVersionUID = 2;
    private int mMajor;
    private int mMinor;
    private int mPower;
    private UUID mUUID;

    public IBeacon() {
        this(26, 255, new byte[]{76, 0, 2, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 76);
    }

    public IBeacon(int length, int type, byte[] data, int companyId) {
        super(length, type, data, companyId);
        parse(data);
    }

    public UUID getUUID() {
        return this.mUUID;
    }

    public void setUUID(UUID uuid) {
        UUID uuid2 = uuid;
        if (uuid2 != null) {
            this.mUUID = uuid2;
            long msbits = uuid.getMostSignificantBits();
            long lsbits = uuid.getLeastSignificantBits();
            byte[] data = getData();
            data[4] = (byte) ((int) ((msbits >> 56) & 255));
            data[5] = (byte) ((int) ((msbits >> 48) & 255));
            data[6] = (byte) ((int) ((msbits >> 40) & 255));
            data[7] = (byte) ((int) ((msbits >> 32) & 255));
            data[8] = (byte) ((int) ((msbits >> 24) & 255));
            data[9] = (byte) ((int) ((msbits >> 16) & 255));
            data[10] = (byte) ((int) ((msbits >> 8) & 255));
            data[11] = (byte) ((int) (msbits & 255));
            data[12] = (byte) ((int) ((lsbits >> 56) & 255));
            data[13] = (byte) ((int) ((lsbits >> 48) & 255));
            data[14] = (byte) ((int) ((lsbits >> 40) & 255));
            data[15] = (byte) ((int) ((lsbits >> 32) & 255));
            data[16] = (byte) ((int) ((lsbits >> 24) & 255));
            data[17] = (byte) ((int) ((lsbits >> 16) & 255));
            data[18] = (byte) ((int) ((lsbits >> 8) & 255));
            data[19] = (byte) ((int) ((lsbits >> 0) & 255));
            return;
        }
        throw new IllegalArgumentException("'uuid' is null.");
    }

    public int getMajor() {
        return this.mMajor;
    }

    public void setMajor(int major) {
        if (major < 0 || 65535 < major) {
            StringBuilder sb = new StringBuilder();
            sb.append("'major' is out of the valid range: ");
            sb.append(major);
            throw new IllegalArgumentException(sb.toString());
        }
        this.mMajor = major;
        byte[] data = getData();
        data[20] = (byte) ((major >> 8) & 255);
        data[21] = (byte) (major & 255);
    }

    public int getMinor() {
        return this.mMinor;
    }

    public void setMinor(int minor) {
        if (minor < 0 || 65535 < minor) {
            StringBuilder sb = new StringBuilder();
            sb.append("'minor' is out of the valid range: ");
            sb.append(minor);
            throw new IllegalArgumentException(sb.toString());
        }
        this.mMinor = minor;
        byte[] data = getData();
        data[22] = (byte) ((minor >> 8) & 255);
        data[23] = (byte) (minor & 255);
    }

    public int getPower() {
        return this.mPower;
    }

    public void setPower(int power) {
        if (power < -128 || 127 < power) {
            StringBuilder sb = new StringBuilder();
            sb.append("'power' is out of the valid range: ");
            sb.append(power);
            throw new IllegalArgumentException(sb.toString());
        }
        this.mPower = power;
        getData()[24] = (byte) (power & 255);
    }

    private void parse(byte[] data) {
        if (data == null || data.length < 25) {
            throw new IllegalArgumentException("The byte sequence cannot be parsed as an iBeacon.");
        }
        this.mUUID = buildUUID(data);
        this.mMajor = buildMajor(data);
        this.mMinor = buildMinor(data);
        this.mPower = buildPower(data);
    }

    private UUID buildUUID(byte[] data) {
        return UUIDCreator.from128(data, 4, false);
    }

    private int buildMajor(byte[] data) {
        return Bytes.parseBE2BytesAsInt(data, 20);
    }

    private int buildMinor(byte[] data) {
        return Bytes.parseBE2BytesAsInt(data, 22);
    }

    private int buildPower(byte[] data) {
        return data[24];
    }

    public static IBeacon create(int length, int type, byte[] data, int companyId) {
        if (data == null || data.length < 25) {
            return null;
        }
        return new IBeacon(length, type, data, companyId);
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{this.mUUID, Integer.valueOf(this.mMajor), Integer.valueOf(this.mMinor), Integer.valueOf(this.mPower)});
    }
}
