package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class WifiResultParser extends ResultParser {
    public WifiParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("WIFI:")) {
            return null;
        }
        String matchSinglePrefixedField = matchSinglePrefixedField("S:", rawText, ';', false);
        String ssid = matchSinglePrefixedField;
        if (matchSinglePrefixedField == null || ssid.isEmpty()) {
            return null;
        }
        String pass = matchSinglePrefixedField("P:", rawText, ';', false);
        String matchSinglePrefixedField2 = matchSinglePrefixedField("T:", rawText, ';', false);
        String type = matchSinglePrefixedField2;
        if (matchSinglePrefixedField2 == null) {
            type = "nopass";
        }
        return new WifiParsedResult(type, ssid, pass, Boolean.parseBoolean(matchSinglePrefixedField("H:", rawText, ';', false)));
    }
}
