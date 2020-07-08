package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public abstract class AbstractExpandedDecoder {
    private final GeneralAppIdDecoder generalDecoder;
    private final BitArray information;

    public abstract String parseInformation() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(BitArray information2) {
        this.information = information2;
        this.generalDecoder = new GeneralAppIdDecoder(information2);
    }

    /* access modifiers changed from: protected */
    public final BitArray getInformation() {
        return this.information;
    }

    /* access modifiers changed from: protected */
    public final GeneralAppIdDecoder getGeneralDecoder() {
        return this.generalDecoder;
    }

    public static AbstractExpandedDecoder createDecoder(BitArray information2) {
        if (information2.get(1)) {
            return new AI01AndOtherAIs(information2);
        }
        if (!information2.get(2)) {
            return new AnyAIDecoder(information2);
        }
        int extractNumericValueFromBitArray = GeneralAppIdDecoder.extractNumericValueFromBitArray(information2, 1, 4);
        if (extractNumericValueFromBitArray == 4) {
            return new AI013103decoder(information2);
        }
        if (extractNumericValueFromBitArray == 5) {
            return new AI01320xDecoder(information2);
        }
        int extractNumericValueFromBitArray2 = GeneralAppIdDecoder.extractNumericValueFromBitArray(information2, 1, 5);
        if (extractNumericValueFromBitArray2 == 12) {
            return new AI01392xDecoder(information2);
        }
        if (extractNumericValueFromBitArray2 == 13) {
            return new AI01393xDecoder(information2);
        }
        String str = "17";
        String str2 = "15";
        String str3 = "13";
        String str4 = "11";
        String str5 = "320";
        String str6 = "310";
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(information2, 1, 7)) {
            case 56:
                return new AI013x0x1xDecoder(information2, str6, str4);
            case 57:
                return new AI013x0x1xDecoder(information2, str5, str4);
            case 58:
                return new AI013x0x1xDecoder(information2, str6, str3);
            case 59:
                return new AI013x0x1xDecoder(information2, str5, str3);
            case 60:
                return new AI013x0x1xDecoder(information2, str6, str2);
            case 61:
                return new AI013x0x1xDecoder(information2, str5, str2);
            case 62:
                return new AI013x0x1xDecoder(information2, str6, str);
            case 63:
                return new AI013x0x1xDecoder(information2, str5, str);
            default:
                StringBuilder sb = new StringBuilder("unknown decoder: ");
                sb.append(information2);
                throw new IllegalStateException(sb.toString());
        }
    }
}
