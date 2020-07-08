package com.neovisionaries.bluetooth.ble.advertising;

public abstract class Eddystone extends ServiceData {
    private static final String STRING_FORMAT = "EddyStone(FrameType=%s)";
    private static final long serialVersionUID = 1;
    private final FrameType mFrameType;

    public enum FrameType {
        UID,
        URL,
        TLM,
        EID
    }

    public Eddystone() {
        this(3, 22, new byte[]{-86, -2}, null);
    }

    public Eddystone(int length, int type, byte[] data, FrameType frameType) {
        super(length, type, data);
        this.mFrameType = frameType;
    }

    public FrameType getFrameType() {
        return this.mFrameType;
    }

    public String toString() {
        return String.format(STRING_FORMAT, new Object[]{this.mFrameType});
    }
}
