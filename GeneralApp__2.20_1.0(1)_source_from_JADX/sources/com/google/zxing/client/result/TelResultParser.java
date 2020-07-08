package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class TelResultParser extends ResultParser {
    public TelParsedResult parse(Result result) {
        String telURI;
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        String str = "tel:";
        String str2 = "TEL:";
        if (!massagedText.startsWith(str) && !rawText.startsWith(str2)) {
            return null;
        }
        if (rawText.startsWith(str2)) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(rawText.substring(4));
            telURI = sb.toString();
        } else {
            telURI = rawText;
        }
        int indexOf = rawText.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? rawText.substring(4) : rawText.substring(4, indexOf), telURI, null);
    }
}
