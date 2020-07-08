package com.google.zxing.oned;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};

    public boolean[] encode(String contents) {
        if (contents.length() < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(DEFAULT_GUARD);
            sb.append(contents);
            sb.append(DEFAULT_GUARD);
            contents = sb.toString();
        } else {
            char firstChar = Character.toUpperCase(contents.charAt(0));
            char lastChar = Character.toUpperCase(contents.charAt(contents.length() - 1));
            boolean startsNormal = CodaBarReader.arrayContains(START_END_CHARS, firstChar);
            boolean endsNormal = CodaBarReader.arrayContains(START_END_CHARS, lastChar);
            boolean startsAlt = CodaBarReader.arrayContains(ALT_START_END_CHARS, firstChar);
            boolean endsAlt = CodaBarReader.arrayContains(ALT_START_END_CHARS, lastChar);
            String str = "Invalid start/end guards: ";
            if (startsNormal) {
                if (!endsNormal) {
                    StringBuilder sb2 = new StringBuilder(str);
                    sb2.append(contents);
                    throw new IllegalArgumentException(sb2.toString());
                }
            } else if (startsAlt) {
                if (!endsAlt) {
                    StringBuilder sb3 = new StringBuilder(str);
                    sb3.append(contents);
                    throw new IllegalArgumentException(sb3.toString());
                }
            } else if (endsNormal || endsAlt) {
                StringBuilder sb4 = new StringBuilder(str);
                sb4.append(contents);
                throw new IllegalArgumentException(sb4.toString());
            } else {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(DEFAULT_GUARD);
                sb5.append(contents);
                sb5.append(DEFAULT_GUARD);
                contents = sb5.toString();
            }
        }
        int resultLength = 20;
        for (int i = 1; i < contents.length() - 1; i++) {
            if (Character.isDigit(contents.charAt(i)) || contents.charAt(i) == '-' || contents.charAt(i) == '$') {
                resultLength += 9;
            } else if (CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, contents.charAt(i))) {
                resultLength += 10;
            } else {
                StringBuilder sb6 = new StringBuilder("Cannot encode : '");
                sb6.append(contents.charAt(i));
                sb6.append('\'');
                throw new IllegalArgumentException(sb6.toString());
            }
        }
        boolean[] result = new boolean[((contents.length() - 1) + resultLength)];
        int position = 0;
        for (int index = 0; index < contents.length(); index++) {
            char c = Character.toUpperCase(contents.charAt(index));
            if (index == 0 || index == contents.length() - 1) {
                if (c == '*') {
                    c = 'C';
                } else if (c == 'E') {
                    c = 'D';
                } else if (c == 'N') {
                    c = 'B';
                } else if (c == 'T') {
                    c = 'A';
                }
            }
            int code = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= CodaBarReader.ALPHABET.length) {
                    break;
                } else if (c == CodaBarReader.ALPHABET[i2]) {
                    code = CodaBarReader.CHARACTER_ENCODINGS[i2];
                    break;
                } else {
                    i2++;
                }
            }
            boolean color = true;
            int counter = 0;
            int bit = 0;
            while (bit < 7) {
                result[position] = color;
                position++;
                if (((code >> (6 - bit)) & 1) == 0 || counter == 1) {
                    color = !color;
                    bit++;
                    counter = 0;
                } else {
                    counter++;
                }
            }
            if (index < contents.length() - 1) {
                result[position] = false;
                position++;
            }
        }
        return result;
    }
}
