package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    public AddressBookParsedResult parse(Result result) {
        List list;
        String[] geo;
        String rawText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(rawText);
        Matcher m = matcher;
        if (!matcher.find() || m.start() != 0) {
            return null;
        }
        List matchVCardPrefixedField = matchVCardPrefixedField("FN", rawText, true, false);
        List list2 = matchVCardPrefixedField;
        if (matchVCardPrefixedField == null) {
            List matchVCardPrefixedField2 = matchVCardPrefixedField("N", rawText, true, false);
            list2 = matchVCardPrefixedField2;
            formatNames(matchVCardPrefixedField2);
        }
        List matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", rawText, true, false);
        String[] nicknames = matchSingleVCardPrefixedField == null ? null : COMMA.split((CharSequence) matchSingleVCardPrefixedField.get(0));
        List<List<String>> phoneNumbers = matchVCardPrefixedField("TEL", rawText, true, false);
        List<List<String>> emails = matchVCardPrefixedField("EMAIL", rawText, true, false);
        List<String> note = matchSingleVCardPrefixedField("NOTE", rawText, false, false);
        List<List<String>> addresses = matchVCardPrefixedField("ADR", rawText, true, true);
        List<String> org2 = matchSingleVCardPrefixedField("ORG", rawText, true, true);
        List matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("BDAY", rawText, true, false);
        List list3 = matchSingleVCardPrefixedField2;
        if (matchSingleVCardPrefixedField2 == null || isLikeVCardDate((CharSequence) list3.get(0))) {
            list = list3;
        } else {
            list = null;
        }
        List<String> title = matchSingleVCardPrefixedField("TITLE", rawText, true, false);
        List<List<String>> urls = matchVCardPrefixedField("URL", rawText, true, false);
        List<String> instantMessenger = matchSingleVCardPrefixedField("IMPP", rawText, true, false);
        List matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("GEO", rawText, true, false);
        List list4 = matchSingleVCardPrefixedField3;
        String[] split = matchSingleVCardPrefixedField3 == null ? null : SEMICOLON_OR_COMMA.split((CharSequence) list4.get(0));
        String[] geo2 = split;
        if (split == null || geo2.length == 2) {
            geo = geo2;
        } else {
            geo = null;
        }
        List list5 = list4;
        AddressBookParsedResult addressBookParsedResult = new AddressBookParsedResult(toPrimaryValues(list2), nicknames, null, toPrimaryValues(phoneNumbers), toTypes(phoneNumbers), toPrimaryValues(emails), toTypes(emails), toPrimaryValue(instantMessenger), toPrimaryValue(note), toPrimaryValues(addresses), toTypes(addresses), toPrimaryValue(org2), toPrimaryValue(list), toPrimaryValue(title), toPrimaryValues(urls), geo);
        return addressBookParsedResult;
    }

    static List<List<String>> matchVCardPrefixedField(CharSequence prefix, String rawText, boolean trim, boolean parseFieldDivider) {
        int i;
        String element;
        Matcher matcher;
        String str = rawText;
        List<List<String>> matches = null;
        int i2 = 0;
        int max = rawText.length();
        String[] strArr = null;
        while (true) {
            if (i2 >= max) {
                break;
            }
            StringBuilder sb = new StringBuilder("(?:^|\n)");
            sb.append(prefix);
            sb.append("(?:;([^:]*))?:");
            Matcher matcher2 = Pattern.compile(sb.toString(), 2).matcher(str);
            if (i2 > 0) {
                i2--;
            }
            if (!matcher2.find(i2)) {
                Matcher matcher3 = matcher2;
                break;
            }
            int i3 = matcher2.end(0);
            int i4 = 1;
            String metadataString = matcher2.group(1);
            List<String> metadata = null;
            boolean quotedPrintable = false;
            String quotedPrintableCharset = null;
            if (metadataString != null) {
                String[] split = SEMICOLON.split(metadataString);
                int length = split.length;
                String quotedPrintableCharset2 = null;
                boolean quotedPrintable2 = false;
                String[] metadatumTokens = strArr;
                int i5 = 0;
                while (i5 < length) {
                    String metadatum = split[i5];
                    if (metadata == null) {
                        matcher = matcher2;
                        metadata = new ArrayList<>(i4);
                    } else {
                        matcher = matcher2;
                    }
                    metadata.add(metadatum);
                    String[] split2 = EQUALS.split(metadatum, 2);
                    String[] strArr2 = metadatumTokens;
                    metadatumTokens = split2;
                    if (split2.length > 1) {
                        String key = metadatumTokens[0];
                        String value = metadatumTokens[1];
                        if ("ENCODING".equalsIgnoreCase(key) && "QUOTED-PRINTABLE".equalsIgnoreCase(value)) {
                            quotedPrintable2 = true;
                        } else if ("CHARSET".equalsIgnoreCase(key)) {
                            quotedPrintableCharset2 = value;
                        }
                    }
                    i5++;
                    matcher2 = matcher;
                    i4 = 1;
                    CharSequence charSequence = prefix;
                }
                strArr = metadatumTokens;
                quotedPrintable = quotedPrintable2;
                quotedPrintableCharset = quotedPrintableCharset2;
            }
            int matchStart = i3;
            while (true) {
                int indexOf = str.indexOf(10, i3);
                i = indexOf;
                if (indexOf < 0) {
                    break;
                } else if (i < rawText.length() - 1 && (str.charAt(i + 1) == ' ' || str.charAt(i + 1) == 9)) {
                    i3 = i + 2;
                } else if (!quotedPrintable) {
                    break;
                } else {
                    if (i <= 0 || str.charAt(i - 1) != '=') {
                        if (i >= 2) {
                            if (str.charAt(i - 2) != '=') {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i3 = i + 1;
                }
            }
            if (i < 0) {
                i2 = max;
            } else if (i > matchStart) {
                if (matches == null) {
                    matches = new ArrayList<>(1);
                }
                if (i > 0 && str.charAt(i - 1) == 13) {
                    i--;
                }
                String element2 = str.substring(matchStart, i);
                if (trim) {
                    element2 = element2.trim();
                }
                String str2 = "\n";
                if (quotedPrintable) {
                    element = decodeQuotedPrintable(element2, quotedPrintableCharset);
                    if (parseFieldDivider) {
                        element = UNESCAPED_SEMICOLONS.matcher(element).replaceAll(str2).trim();
                    }
                } else {
                    if (parseFieldDivider) {
                        element2 = UNESCAPED_SEMICOLONS.matcher(element2).replaceAll(str2).trim();
                    }
                    element = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(element2).replaceAll("")).replaceAll(str2)).replaceAll("$1");
                }
                if (metadata == null) {
                    List<String> arrayList = new ArrayList<>(1);
                    List<String> match = arrayList;
                    arrayList.add(element);
                    matches.add(match);
                } else {
                    metadata.add(0, element);
                    matches.add(metadata);
                }
                i2 = i + 1;
            } else {
                i2 = i + 1;
            }
        }
        return matches;
    }

    private static String decodeQuotedPrintable(CharSequence value, String charset) {
        int length = value.length();
        StringBuilder result = new StringBuilder(length);
        ByteArrayOutputStream fragmentBuffer = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt = value.charAt(i);
            char c = charAt;
            if (!(charAt == 10 || charAt == 13)) {
                if (charAt != '=') {
                    maybeAppendFragment(fragmentBuffer, charset, result);
                    result.append(c);
                } else if (i < length - 2) {
                    char charAt2 = value.charAt(i + 1);
                    char nextChar = charAt2;
                    if (!(charAt2 == 13 || nextChar == 10)) {
                        char nextNextChar = value.charAt(i + 2);
                        int firstDigit = parseHexDigit(nextChar);
                        int secondDigit = parseHexDigit(nextNextChar);
                        if (firstDigit >= 0 && secondDigit >= 0) {
                            fragmentBuffer.write((firstDigit << 4) + secondDigit);
                        }
                        i += 2;
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(fragmentBuffer, charset, result);
        return result.toString();
    }

    private static void maybeAppendFragment(ByteArrayOutputStream fragmentBuffer, String charset, StringBuilder result) {
        String fragment;
        if (fragmentBuffer.size() > 0) {
            byte[] fragmentBytes = fragmentBuffer.toByteArray();
            String str = "UTF-8";
            if (charset == null) {
                fragment = new String(fragmentBytes, Charset.forName(str));
            } else {
                try {
                    fragment = new String(fragmentBytes, charset);
                } catch (UnsupportedEncodingException e) {
                    fragment = new String(fragmentBytes, Charset.forName(str));
                }
            }
            fragmentBuffer.reset();
            result.append(fragment);
        }
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence prefix, String rawText, boolean trim, boolean parseFieldDivider) {
        List matchVCardPrefixedField = matchVCardPrefixedField(prefix, rawText, trim, parseFieldDivider);
        List list = matchVCardPrefixedField;
        if (matchVCardPrefixedField == null || list.isEmpty()) {
            return null;
        }
        return (List) list.get(0);
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>(lists.size());
        for (List list : lists) {
            String str = (String) list.get(0);
            String value = str;
            if (str != null && !value.isEmpty()) {
                result.add(value);
            }
        }
        return (String[]) result.toArray(new String[lists.size()]);
    }

    private static String[] toTypes(Collection<List<String>> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>(lists.size());
        for (List<String> list : lists) {
            String type = null;
            int i = 1;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                String str = (String) list.get(i);
                String metadatum = str;
                int indexOf = str.indexOf(61);
                int equals = indexOf;
                if (indexOf < 0) {
                    type = metadatum;
                    break;
                }
                if ("TYPE".equalsIgnoreCase(metadatum.substring(0, equals))) {
                    type = metadatum.substring(equals + 1);
                    break;
                }
                i++;
            }
            result.add(type);
        }
        return (String[]) result.toArray(new String[lists.size()]);
    }

    private static boolean isLikeVCardDate(CharSequence value) {
        return value == null || VCARD_LIKE_DATE.matcher(value).matches();
    }

    private static void formatNames(Iterable<List<String>> names) {
        if (names != null) {
            for (List list : names) {
                List list2 = list;
                String name = (String) list.get(0);
                String[] components = new String[5];
                int start = 0;
                int componentIndex = 0;
                while (componentIndex < 4) {
                    int indexOf = name.indexOf(59, start);
                    int end = indexOf;
                    if (indexOf < 0) {
                        break;
                    }
                    components[componentIndex] = name.substring(start, end);
                    componentIndex++;
                    start = end + 1;
                }
                components[componentIndex] = name.substring(start);
                StringBuilder newName = new StringBuilder(100);
                maybeAppendComponent(components, 3, newName);
                maybeAppendComponent(components, 1, newName);
                maybeAppendComponent(components, 2, newName);
                maybeAppendComponent(components, 0, newName);
                maybeAppendComponent(components, 4, newName);
                list2.set(0, newName.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] components, int i, StringBuilder newName) {
        if (components[i] != null && !components[i].isEmpty()) {
            if (newName.length() > 0) {
                newName.append(' ');
            }
            newName.append(components[i]);
        }
    }
}
