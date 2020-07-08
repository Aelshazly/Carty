package com.neovisionaries.bluetooth.ble.advertising;

import com.neovisionaries.bluetooth.ble.advertising.Eddystone.FrameType;
import com.neovisionaries.bluetooth.ble.util.Bytes;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

public class EddystoneTLM extends Eddystone {
    private static final String STRING_FORMAT = "EddystoneTLM(Version=%d,BatteryVoltage=%d,BeaconTemperature=%f,AdvertisementCount=%d,ElapsedTime=%d)";
    private static final long serialVersionUID = 1;
    private final long mAdvertisementCount;
    private final int mBatteryVoltage;
    private final float mBeaconTemperature;
    private final long mElapsedTime;
    private transient String mString;
    private final int mTLMVersion;

    public EddystoneTLM() {
        this(17, 22, new byte[]{-86, -2, 32, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public EddystoneTLM(int length, int type, byte[] data) {
        super(length, type, data, FrameType.TLM);
        this.mTLMVersion = extractTLMVersion(data);
        this.mBatteryVoltage = extractBatteryVoltage(data);
        this.mBeaconTemperature = extractBeaconTemperature(data);
        this.mAdvertisementCount = extractAdvertisementCount(data);
        this.mElapsedTime = extractElapsedTime(data);
    }

    private int extractTLMVersion(byte[] data) {
        if (data.length < 4) {
            return 0;
        }
        return data[3] & UByte.MAX_VALUE;
    }

    private int extractBatteryVoltage(byte[] data) {
        if (data.length < 6) {
            return 0;
        }
        return Bytes.parseBE2BytesAsInt(data, 4);
    }

    private float extractBeaconTemperature(byte[] data) {
        if (data.length < 8) {
            return -128.0f;
        }
        return Bytes.convertFixedPointToFloat(data, 6);
    }

    private long extractAdvertisementCount(byte[] data) {
        if (data.length < 12) {
            return 0;
        }
        return Bytes.parseBE4BytesAsUnsigned(data, 8);
    }

    private long extractElapsedTime(byte[] data) {
        if (data.length < 16) {
            return 0;
        }
        return Bytes.parseBE4BytesAsUnsigned(data, 12) * 100;
    }

    public int getTLMVersion() {
        return this.mTLMVersion;
    }

    public int getBatteryVoltage() {
        return this.mBatteryVoltage;
    }

    public float getBeaconTemperature() {
        return this.mBeaconTemperature;
    }

    public long getAdvertisementCount() {
        return this.mAdvertisementCount;
    }

    public long getElapsedTime() {
        return this.mElapsedTime;
    }

    public String toString() {
        String str = this.mString;
        if (str != null) {
            return str;
        }
        this.mString = String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mTLMVersion), Integer.valueOf(this.mBatteryVoltage), Float.valueOf(this.mBeaconTemperature), Long.valueOf(this.mAdvertisementCount), Long.valueOf(this.mElapsedTime)});
        return this.mString;
    }
}
