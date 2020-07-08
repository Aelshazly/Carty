package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class BizcardResultParser extends AbstractDoCoMoResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("BIZCARD:")) {
            return null;
        }
        String fullName = buildName(matchSingleDoCoMoPrefixedField("N:", rawText, true), matchSingleDoCoMoPrefixedField("X:", rawText, true));
        String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("T:", rawText, true);
        String matchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("C:", rawText, true);
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("A:", rawText, true);
        String phoneNumber1 = matchSingleDoCoMoPrefixedField("B:", rawText, true);
        String phoneNumber2 = matchSingleDoCoMoPrefixedField("M:", rawText, true);
        String phoneNumber3 = matchSingleDoCoMoPrefixedField("F:", rawText, true);
        String str = phoneNumber3;
        String str2 = phoneNumber2;
        String str3 = phoneNumber1;
        AddressBookParsedResult addressBookParsedResult = new AddressBookParsedResult(maybeWrap(fullName), null, null, buildPhoneNumbers(phoneNumber1, phoneNumber2, phoneNumber3), null, maybeWrap(matchSingleDoCoMoPrefixedField("E:", rawText, true)), null, null, null, matchDoCoMoPrefixedField, null, matchSingleDoCoMoPrefixedField2, null, matchSingleDoCoMoPrefixedField, null, null);
        return addressBookParsedResult;
    }

    private static String[] buildPhoneNumbers(String number1, String number2, String number3) {
        List<String> numbers = new ArrayList<>(3);
        if (number1 != null) {
            numbers.add(number1);
        }
        if (number2 != null) {
            numbers.add(number2);
        }
        if (number3 != null) {
            numbers.add(number3);
        }
        int size = numbers.size();
        int size2 = size;
        if (size == 0) {
            return null;
        }
        return (String[]) numbers.toArray(new String[size2]);
    }

    private static String buildName(String firstName, String lastName) {
        if (firstName == null) {
            return lastName;
        }
        if (lastName == null) {
            return firstName;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(' ');
        sb.append(lastName);
        return sb.toString();
    }
}
