package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("MECARD:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("N:", rawText, true);
        String[] rawName = matchDoCoMoPrefixedField;
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        String name = parseName(rawName[0]);
        String pronunciation = matchSingleDoCoMoPrefixedField("SOUND:", rawText, true);
        String[] phoneNumbers = matchDoCoMoPrefixedField("TEL:", rawText, true);
        String[] emails = matchDoCoMoPrefixedField("EMAIL:", rawText, true);
        String note = matchSingleDoCoMoPrefixedField("NOTE:", rawText, false);
        String[] addresses = matchDoCoMoPrefixedField("ADR:", rawText, true);
        String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("BDAY:", rawText, true);
        String birthday = matchSingleDoCoMoPrefixedField;
        if (!isStringOfDigits(matchSingleDoCoMoPrefixedField, 8)) {
            birthday = null;
        }
        String[] matchDoCoMoPrefixedField2 = matchDoCoMoPrefixedField("URL:", rawText, true);
        AddressBookParsedResult addressBookParsedResult = new AddressBookParsedResult(maybeWrap(name), null, pronunciation, phoneNumbers, null, emails, null, null, note, addresses, null, matchSingleDoCoMoPrefixedField("ORG:", rawText, true), birthday, null, matchDoCoMoPrefixedField2, null);
        return addressBookParsedResult;
    }

    private static String parseName(String name) {
        int indexOf = name.indexOf(44);
        int comma = indexOf;
        if (indexOf < 0) {
            return name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(comma + 1));
        sb.append(' ');
        sb.append(name.substring(0, comma));
        return sb.toString();
    }
}
