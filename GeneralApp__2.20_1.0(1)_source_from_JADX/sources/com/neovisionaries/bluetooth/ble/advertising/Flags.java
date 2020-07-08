package com.neovisionaries.bluetooth.ble.advertising;

import kotlin.UByte;

public class Flags extends ADStructure {
    private static final int CONTROLLER_SIMULTANEITY_SUPPORTED_BIT = 8;
    private static final int GENERAL_DISCOVERABLE_BIT = 2;
    private static final int HOST_SIMULTANEITY_SUPPORTED_BIT = 16;
    private static final int LEGACY_NOT_SUPPORTED_BIT = 4;
    private static final int LIMITED_DISCOVERABLE_BIT = 1;
    private static final String STRING_FORMAT = "Flags(LimitedDiscoverable=%s,GeneralDiscoverable=%s,LegacySupported=%s,ControllerSimultaneitySupported=%s,HostSimultaneitySupported=%s)";
    private static final long serialVersionUID = 2;
    private boolean mControllerSimultaneitySupported;
    private boolean mGeneralDiscoverable;
    private boolean mHostSimultaneitySupported;
    private boolean mLegacySupported;
    private boolean mLimitedDiscoverable;

    public Flags() {
        this(2, 1, new byte[]{0});
    }

    public Flags(int length, int type, byte[] data) {
        super(length, type, data);
        parse(data);
    }

    public boolean isLimitedDiscoverable() {
        return this.mLimitedDiscoverable;
    }

    public void setLimitedDiscoverable(boolean discoverable) {
        this.mLimitedDiscoverable = discoverable;
        setBit(0, 1, discoverable);
    }

    public boolean isGeneralDiscoverable() {
        return this.mGeneralDiscoverable;
    }

    public void setGeneralDiscoverable(boolean discoverable) {
        this.mGeneralDiscoverable = discoverable;
        setBit(0, 2, discoverable);
    }

    public boolean isLegacySupported() {
        return this.mLegacySupported;
    }

    public void setLegacySupported(boolean supported) {
        this.mLegacySupported = supported;
        setBit(0, 4, !supported);
    }

    public boolean isControllerSimultaneitySupported() {
        return this.mControllerSimultaneitySupported;
    }

    public void setControllerSimultaneitySupported(boolean supported) {
        this.mControllerSimultaneitySupported = supported;
        setBit(0, 8, supported);
    }

    public boolean isHostSimultaneitySupported() {
        return this.mHostSimultaneitySupported;
    }

    public void setHostSimultaneitySupported(boolean supported) {
        this.mHostSimultaneitySupported = supported;
        setBit(0, 16, supported);
    }

    private void parse(byte[] data) {
        if (data != null && data.length >= 1) {
            boolean z = false;
            this.mLimitedDiscoverable = (data[0] & 1) != 0;
            this.mGeneralDiscoverable = (data[0] & 2) != 0;
            this.mLegacySupported = (data[0] & 4) == 0;
            this.mControllerSimultaneitySupported = (data[0] & 8) != 0;
            if ((data[0] & 16) != 0) {
                z = true;
            }
            this.mHostSimultaneitySupported = z;
        }
    }

    private void setBit(int byteIndex, int bitIndex, boolean value) {
        if (byteIndex >= 0 && bitIndex >= 0 && 7 >= bitIndex) {
            byte[] data = getData();
            if (data != null && data.length > byteIndex) {
                if (value) {
                    data[byteIndex] = (byte) (((1 << bitIndex) | data[byteIndex]) & UByte.MAX_VALUE);
                } else {
                    data[byteIndex] = (byte) ((~(1 << bitIndex)) & data[byteIndex] & UByte.MAX_VALUE);
                }
            }
        }
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{Boolean.valueOf(this.mLimitedDiscoverable), Boolean.valueOf(this.mGeneralDiscoverable), Boolean.valueOf(this.mLegacySupported), Boolean.valueOf(this.mControllerSimultaneitySupported), Boolean.valueOf(this.mHostSimultaneitySupported)});
    }
}
