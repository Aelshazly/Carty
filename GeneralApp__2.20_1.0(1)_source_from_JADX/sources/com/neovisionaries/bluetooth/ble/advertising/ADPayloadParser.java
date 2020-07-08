package com.neovisionaries.bluetooth.ble.advertising;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.UByte;
import p008cz.msebera.android.httpclient.HttpStatus;

public class ADPayloadParser {
    private static final ADPayloadParser sInstance = new ADPayloadParser();
    private final Map<Integer, List<ADStructureBuilder>> mBuilders;
    /* access modifiers changed from: private */
    public final Map<Integer, List<ADManufacturerSpecificBuilder>> mMSBuilders = new HashMap();

    private class MSBuilder implements ADStructureBuilder {
        private MSBuilder() {
        }

        public ADStructure build(int length, int type, byte[] data) {
            if (data.length < 2) {
                return null;
            }
            int companyId = ((data[1] & UByte.MAX_VALUE) << 8) | (data[0] & 255);
            List<ADManufacturerSpecificBuilder> builders = (List) ADPayloadParser.this.mMSBuilders.get(Integer.valueOf(companyId));
            if (builders == null) {
                return new ADManufacturerSpecific(length, type, data, companyId);
            }
            for (ADManufacturerSpecificBuilder builder : builders) {
                ADManufacturerSpecific structure = builder.build(length, type, data, companyId);
                if (structure != null) {
                    return structure;
                }
            }
            return new ADManufacturerSpecific(length, type, data, companyId);
        }
    }

    private ADPayloadParser() {
        registerManufacturerSpecificBuilder(76, new MS004CBuilder());
        registerManufacturerSpecificBuilder(261, new MS0105Builder());
        registerManufacturerSpecificBuilder(HttpStatus.SC_GONE, new MS019ABuilder());
        UUIDsBuilder uuidsBuilder = new UUIDsBuilder();
        LocalNameBuilder localNameBuilder = new LocalNameBuilder();
        ServiceDataBuilder serviceDataBuilder = new ServiceDataBuilder();
        this.mBuilders = new HashMap();
        registerBuilder(1, new FlagsBuilder());
        registerBuilder(2, uuidsBuilder);
        registerBuilder(3, uuidsBuilder);
        registerBuilder(4, uuidsBuilder);
        registerBuilder(5, uuidsBuilder);
        registerBuilder(6, uuidsBuilder);
        registerBuilder(7, uuidsBuilder);
        registerBuilder(8, localNameBuilder);
        registerBuilder(9, localNameBuilder);
        registerBuilder(10, new TxPowerLevelBuilder());
        registerBuilder(20, uuidsBuilder);
        registerBuilder(21, uuidsBuilder);
        registerBuilder(22, serviceDataBuilder);
        registerBuilder(22, new EddystoneBuilder());
        registerBuilder(31, uuidsBuilder);
        registerBuilder(32, serviceDataBuilder);
        registerBuilder(33, serviceDataBuilder);
        registerBuilder(255, new MSBuilder());
    }

    public static ADPayloadParser getInstance() {
        return sInstance;
    }

    public void registerBuilder(int type, ADStructureBuilder builder) {
        if (type < 0 || 255 < type) {
            throw new IllegalArgumentException(String.format("'type' is out of the valid range: %d", new Object[]{Integer.valueOf(type)}));
        } else if (builder != null) {
            Integer key = Integer.valueOf(type);
            List list = (List) this.mBuilders.get(key);
            if (list == null) {
                list = new ArrayList();
                this.mBuilders.put(key, list);
            }
            list.add(0, builder);
        }
    }

    public void registerManufacturerSpecificBuilder(int companyId, ADManufacturerSpecificBuilder builder) {
        if (companyId < 0 || 65535 < companyId) {
            throw new IllegalArgumentException(String.format("'companyId' is out of the valid range: %d", new Object[]{Integer.valueOf(companyId)}));
        } else if (builder != null) {
            Integer key = Integer.valueOf(companyId);
            List list = (List) this.mMSBuilders.get(key);
            if (list == null) {
                list = new ArrayList();
                this.mMSBuilders.put(key, list);
            }
            list.add(0, builder);
        }
    }

    public List<ADStructure> parse(byte[] payload) {
        if (payload == null) {
            return null;
        }
        return parse(payload, 0, payload.length);
    }

    public List<ADStructure> parse(byte[] payload, int offset, int length) {
        if (payload == null) {
            return null;
        }
        List<ADStructure> list = new ArrayList<>();
        if (offset < 0 || length < 0 || payload.length <= offset) {
            return list;
        }
        int end = Math.min(offset + length, payload.length);
        int i = offset;
        while (i < end) {
            int len = payload[i] & 255;
            if (len == 0 || (end - i) - 1 < len) {
                break;
            }
            list.add(buildAds(len, payload[i + 1] & 255, Arrays.copyOfRange(payload, i + 2, i + len + 1)));
            i += len + 1;
        }
        return list;
    }

    private ADStructure buildAds(int length, int type, byte[] data) {
        List<ADStructureBuilder> builders = (List) this.mBuilders.get(Integer.valueOf(type));
        if (builders == null) {
            return new ADStructure(length, type, data);
        }
        for (ADStructureBuilder builder : builders) {
            ADStructure structure = builder.build(length, type, data);
            if (structure != null) {
                return structure;
            }
        }
        return new ADStructure(length, type, data);
    }
}
