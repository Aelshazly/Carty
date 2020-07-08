package com.neovisionaries.bluetooth.ble;

import java.util.HashMap;
import java.util.Map;

public enum GattStatusCode {
    SUCCESS(0),
    INVALID_HANDLE(1),
    READ_NOT_PERMIT(2),
    WRITE_NOT_PERMIT(3),
    INVALID_PDU(4),
    INSUF_AUTHENTICATION(5),
    REQ_NOT_SUPPORTED(6),
    INVALID_OFFSET(7),
    INSUF_AUTHORIZATION(8),
    PREPARE_Q_FULL(9),
    NOT_FOUND(10),
    NOT_LONG(11),
    INSUF_KEY_SIZE(12),
    INVALID_ATTR_LEN(13),
    ERR_UNLIKELY(14),
    INSUF_ENCRYPTION(15),
    UNSUPPORT_GRP_TYPE(16),
    INSUF_RESOURCE(17),
    NO_RESOURCES(128),
    INTERNAL_ERROR(129),
    WRONG_STATE(130),
    DB_FULL(131),
    BUSY(132),
    ERROR(133),
    CMD_STARTED(134),
    ILLEGAL_PARAMETER(135),
    PENDING(136),
    AUTH_FAIL(137),
    MORE(138),
    INVALID_CFG(139),
    SERVICE_STARTED(140),
    ENCRYPTED_NO_MITM(141),
    NOT_ENCRYPTED(142),
    CONGESTED(143),
    CCC_CFG_ERR(253),
    PRC_IN_PROGRESS(254),
    OUT_OF_RANGE(255);
    
    private static final Map<Integer, GattStatusCode> sValueToCodeMap = null;
    private final int mValue;

    static {
        int i;
        GattStatusCode[] values;
        sValueToCodeMap = new HashMap();
        for (GattStatusCode code : values()) {
            sValueToCodeMap.put(Integer.valueOf(code.getValue()), code);
        }
    }

    private GattStatusCode(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static GattStatusCode getByValue(int value) {
        return (GattStatusCode) sValueToCodeMap.get(Integer.valueOf(value));
    }
}
