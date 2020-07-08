package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;

public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    public EmailAddressParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("MATMSG:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("TO:", rawText, true);
        String[] tos = matchDoCoMoPrefixedField;
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        for (String isBasicallyValidEmailAddress : tos) {
            if (!isBasicallyValidEmailAddress(isBasicallyValidEmailAddress)) {
                return null;
            }
        }
        EmailAddressParsedResult emailAddressParsedResult = new EmailAddressParsedResult(tos, null, null, matchSingleDoCoMoPrefixedField("SUB:", rawText, false), matchSingleDoCoMoPrefixedField("BODY:", rawText, false));
        return emailAddressParsedResult;
    }

    static boolean isBasicallyValidEmailAddress(String email) {
        return email != null && ATEXT_ALPHANUMERIC.matcher(email).matches() && email.indexOf(64) >= 0;
    }
}
