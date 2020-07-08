package com.neovisionaries.bluetooth.ble.advertising;

import java.util.regex.Pattern;
import kotlin.UByte;
import p008cz.msebera.android.httpclient.HttpStatus;

public class Ucode extends ADManufacturerSpecific {
    private static final int COUNT_INDEX = 21;
    private static final int LOW_BATTERY_BIT = 32;
    private static final int POWER_INDEX = 20;
    private static final int STATUS_INDEX = 19;
    private static final String STRING_FORMAT = "ucode(Version=%d,Ucode=%s,Status=%d,BatteryLow=%s,Interval=%d,Power=%d,Count=%d)";
    private static final String UCODE_FORMAT = "%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X";
    private static final int UCODE_INDEX = 3;
    private static final Pattern UCODE_PATTERN = Pattern.compile("^[0-9A-Fa-f]{32}$");
    private static final int VERSION_INDEX = 2;
    private static final long serialVersionUID = 1;
    private int mCount;
    private int mPower;
    private int mStatus;
    private String mUcode;
    private int mVersion;

    public Ucode() {
        this(26, 255, new byte[]{-102, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, HttpStatus.SC_GONE);
    }

    public Ucode(int length, int type, byte[] data, int companyId) {
        super(length, type, data, companyId);
        parse(data);
    }

    public int getVersion() {
        return this.mVersion;
    }

    public void setVersion(int version) {
        if (version < 0 || 255 < version) {
            StringBuilder sb = new StringBuilder();
            sb.append("'version' is out of the valid range: ");
            sb.append(version);
            throw new IllegalArgumentException(sb.toString());
        }
        this.mVersion = version;
        getData()[2] = (byte) (version & 255);
    }

    public String getUcode() {
        return this.mUcode;
    }

    public void setUcode(String ucode) {
        if (ucode == null) {
            throw new IllegalArgumentException("'ucode' is null.");
        } else if (UCODE_PATTERN.matcher(ucode).matches()) {
            this.mUcode = ucode;
            byte[] data = getData();
            for (int i = 0; i < 32; i += 2) {
                data[(15 - (i / 2)) + 3] = readHex(ucode, i);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("The format of 'ucode' is wrong: ");
            sb.append(ucode);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private byte readHex(String string, int index) {
        return (byte) (((toInt(string.charAt(index)) << 4) | toInt(string.charAt(index + 1))) & 255);
    }

    private int toInt(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        }
        if ('A' <= ch && ch <= 'F') {
            return (ch - 'A') + 10;
        }
        if ('a' > ch || ch > 'f') {
            return 0;
        }
        return (ch - 'a') + 10;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
        getData()[19] = (byte) (status & 255);
    }

    public boolean isBatteryLow() {
        return (this.mStatus & 32) != 0;
    }

    public int getInterval() {
        switch (this.mStatus & 15) {
            case 0:
                return 10;
            case 1:
                return 20;
            case 2:
                return 40;
            case 3:
                return 80;
            case 4:
                return 160;
            case 5:
                return 320;
            case 6:
                return 640;
            case 7:
                return 1280;
            case 8:
                return 2560;
            case 9:
                return 5120;
            default:
                return 10240;
        }
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
        getData()[20] = (byte) power;
    }

    public int getCount() {
        return this.mCount;
    }

    public void setCount(int count) {
        if (count < 0 || 255 < count) {
            StringBuilder sb = new StringBuilder();
            sb.append("'count' is out of the valid range: ");
            sb.append(count);
            throw new IllegalArgumentException(sb.toString());
        }
        this.mCount = count;
        getData()[21] = (byte) count;
    }

    private void parse(byte[] data) {
        if (data == null || data.length < 22) {
            throw new IllegalArgumentException("The byte sequence cannot be parsed as a ucode.");
        }
        this.mVersion = buildVersion(data);
        this.mUcode = buildUcode(data);
        this.mStatus = buildStatus(data);
        this.mPower = buildPower(data);
        this.mCount = buildCount(data);
    }

    private int buildVersion(byte[] data) {
        return data[2] & UByte.MAX_VALUE;
    }

    private String buildUcode(byte[] data) {
        return String.format(UCODE_FORMAT, new Object[]{Integer.valueOf(data[18] & UByte.MAX_VALUE), Integer.valueOf(data[17] & UByte.MAX_VALUE), Integer.valueOf(data[16] & UByte.MAX_VALUE), Integer.valueOf(data[15] & UByte.MAX_VALUE), Integer.valueOf(data[14] & UByte.MAX_VALUE), Integer.valueOf(data[13] & UByte.MAX_VALUE), Integer.valueOf(data[12] & UByte.MAX_VALUE), Integer.valueOf(data[11] & UByte.MAX_VALUE), Integer.valueOf(data[10] & UByte.MAX_VALUE), Integer.valueOf(data[9] & UByte.MAX_VALUE), Integer.valueOf(data[8] & UByte.MAX_VALUE), Integer.valueOf(data[7] & UByte.MAX_VALUE), Integer.valueOf(data[6] & UByte.MAX_VALUE), Integer.valueOf(data[5] & UByte.MAX_VALUE), Integer.valueOf(data[4] & UByte.MAX_VALUE), Integer.valueOf(data[3] & UByte.MAX_VALUE)});
    }

    private int buildStatus(byte[] data) {
        return data[19] & UByte.MAX_VALUE;
    }

    private int buildPower(byte[] data) {
        return data[20];
    }

    private int buildCount(byte[] data) {
        return data[21] & UByte.MAX_VALUE;
    }

    public static Ucode create(int length, int type, byte[] data, int companyId) {
        if (data == null || data.length < 22) {
            return null;
        }
        return new Ucode(length, type, data, companyId);
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{Integer.valueOf(this.mVersion), this.mUcode, Integer.valueOf(this.mStatus), Boolean.valueOf(isBatteryLow()), Integer.valueOf(getInterval()), Integer.valueOf(this.mPower), Integer.valueOf(this.mCount)});
    }
}
