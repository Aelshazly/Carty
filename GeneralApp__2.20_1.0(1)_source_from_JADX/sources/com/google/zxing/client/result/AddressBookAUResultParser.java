package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class AddressBookAUResultParser extends ResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String[] strArr = null;
        String rawText = massagedText;
        if (!massagedText.contains("MEMORY") || !rawText.contains("\r\n")) {
            return null;
        }
        String name = matchSinglePrefixedField("NAME1:", rawText, 13, true);
        String pronunciation = matchSinglePrefixedField("NAME2:", rawText, 13, true);
        String[] phoneNumbers = matchMultipleValuePrefix("TEL", 3, rawText, true);
        String[] emails = matchMultipleValuePrefix("MAIL", 3, rawText, true);
        String note = matchSinglePrefixedField("MEMORY:", rawText, 13, false);
        String matchSinglePrefixedField = matchSinglePrefixedField("ADD:", rawText, 13, true);
        String address = matchSinglePrefixedField;
        if (matchSinglePrefixedField != null) {
            strArr = new String[]{address};
        }
        AddressBookParsedResult addressBookParsedResult = new AddressBookParsedResult(maybeWrap(name), null, pronunciation, phoneNumbers, null, emails, null, null, note, strArr, null, null, null, null, null, null);
        return addressBookParsedResult;
    }

    private static String[] matchMultipleValuePrefix(String prefix, int max, String rawText, boolean trim) {
        List list = null;
        for (int i = 1; i <= max; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(i);
            sb.append(':');
            String matchSinglePrefixedField = matchSinglePrefixedField(sb.toString(), rawText, 13, trim);
            String value = matchSinglePrefixedField;
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (list == null) {
                list = new ArrayList(max);
            }
            list.add(value);
        }
        if (list == null) {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}
