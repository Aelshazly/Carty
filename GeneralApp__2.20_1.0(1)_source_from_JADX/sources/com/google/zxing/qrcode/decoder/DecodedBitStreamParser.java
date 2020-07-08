package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bytes, Version version, ErrorCorrectionLevel ecLevel, Map<DecodeHintType, ?> hints) throws FormatException {
        Mode mode;
        int parityData;
        int symbolSequence;
        Mode mode2;
        String str;
        Mode mode3;
        Version version2 = version;
        BitSource bits = new BitSource(bytes);
        StringBuilder result = new StringBuilder(50);
        int i = 1;
        ArrayList arrayList = new ArrayList(1);
        int symbolSequence2 = -1;
        int parityData2 = -1;
        CharacterSetECI currentCharacterSetECI = null;
        boolean fc1InEffect = false;
        while (true) {
            try {
                if (bits.available() < 4) {
                    try {
                        mode = Mode.TERMINATOR;
                    } catch (IllegalArgumentException e) {
                        StringBuilder sb = result;
                        BitSource bitSource = bits;
                        CharacterSetECI characterSetECI = currentCharacterSetECI;
                        throw FormatException.getFormatInstance();
                    }
                } else {
                    mode = Mode.forBits(bits.readBits(4));
                }
                if (mode != Mode.TERMINATOR) {
                    if (mode == Mode.FNC1_FIRST_POSITION) {
                        mode3 = mode;
                    } else if (mode == Mode.FNC1_SECOND_POSITION) {
                        mode3 = mode;
                    } else if (mode == Mode.STRUCTURED_APPEND) {
                        if (bits.available() >= 16) {
                            symbolSequence = bits.readBits(8);
                            parityData = bits.readBits(8);
                            mode2 = mode;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (mode == Mode.ECI) {
                        CharacterSetECI characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bits));
                        currentCharacterSetECI = characterSetECIByValue;
                        if (characterSetECIByValue != null) {
                            symbolSequence = symbolSequence2;
                            parityData = parityData2;
                            mode2 = mode;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (mode == Mode.HANZI) {
                        int subset = bits.readBits(4);
                        int countHanzi = bits.readBits(mode.getCharacterCountBits(version2));
                        if (subset == i) {
                            decodeHanziSegment(bits, result, countHanzi);
                        }
                        symbolSequence = symbolSequence2;
                        parityData = parityData2;
                        mode2 = mode;
                    } else {
                        int count = bits.readBits(mode.getCharacterCountBits(version2));
                        if (mode == Mode.NUMERIC) {
                            decodeNumericSegment(bits, result, count);
                            mode2 = mode;
                        } else if (mode == Mode.ALPHANUMERIC) {
                            decodeAlphanumericSegment(bits, result, count, fc1InEffect);
                            mode2 = mode;
                        } else if (mode == Mode.BYTE) {
                            mode2 = mode;
                            decodeByteSegment(bits, result, count, currentCharacterSetECI, arrayList, hints);
                        } else {
                            mode2 = mode;
                            if (mode2 == Mode.KANJI) {
                                decodeKanjiSegment(bits, result, count);
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        }
                        symbolSequence = symbolSequence2;
                        parityData = parityData2;
                    }
                    fc1InEffect = true;
                    symbolSequence = symbolSequence2;
                    parityData = parityData2;
                } else {
                    mode2 = mode;
                    symbolSequence = symbolSequence2;
                    parityData = parityData2;
                }
                try {
                    if (mode2 == Mode.TERMINATOR) {
                        String sb2 = result.toString();
                        ArrayList arrayList2 = arrayList.isEmpty() ? null : arrayList;
                        if (ecLevel == null) {
                            str = null;
                        } else {
                            str = ecLevel.toString();
                        }
                        StringBuilder sb3 = result;
                        BitSource bitSource2 = bits;
                        DecoderResult decoderResult = new DecoderResult(bytes, sb2, arrayList2, str, symbolSequence, parityData);
                        return decoderResult;
                    }
                    BitSource bitSource3 = bits;
                    symbolSequence2 = symbolSequence;
                    parityData2 = parityData;
                    i = 1;
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb4 = result;
                    BitSource bitSource4 = bits;
                    int i2 = symbolSequence;
                    int i3 = parityData;
                    CharacterSetECI characterSetECI2 = currentCharacterSetECI;
                    throw FormatException.getFormatInstance();
                }
            } catch (IllegalArgumentException e3) {
                StringBuilder sb5 = result;
                BitSource bitSource5 = bits;
                CharacterSetECI characterSetECI22 = currentCharacterSetECI;
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeHanziSegment(BitSource bits, StringBuilder result, int count) throws FormatException {
        int i;
        if (count * 13 <= bits.available()) {
            byte[] buffer = new byte[(count * 2)];
            int offset = 0;
            while (count > 0) {
                int readBits = bits.readBits(13);
                int i2 = ((readBits / 96) << 8) | (readBits % 96);
                int assembledTwoBytes = i2;
                if (i2 < 959) {
                    i = 41377;
                } else {
                    i = 42657;
                }
                int assembledTwoBytes2 = assembledTwoBytes + i;
                buffer[offset] = (byte) (assembledTwoBytes2 >> 8);
                buffer[offset + 1] = (byte) assembledTwoBytes2;
                offset += 2;
                count--;
            }
            try {
                result.append(new String(buffer, StringUtils.GB2312));
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bits, StringBuilder result, int count) throws FormatException {
        int i;
        if (count * 13 <= bits.available()) {
            byte[] buffer = new byte[(count * 2)];
            int offset = 0;
            while (count > 0) {
                int readBits = bits.readBits(13);
                int i2 = ((readBits / 192) << 8) | (readBits % 192);
                int assembledTwoBytes = i2;
                if (i2 < 7936) {
                    i = 33088;
                } else {
                    i = 49472;
                }
                int assembledTwoBytes2 = assembledTwoBytes + i;
                buffer[offset] = (byte) (assembledTwoBytes2 >> 8);
                buffer[offset + 1] = (byte) assembledTwoBytes2;
                offset += 2;
                count--;
            }
            try {
                result.append(new String(buffer, StringUtils.SHIFT_JIS));
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeByteSegment(BitSource bits, StringBuilder result, int count, CharacterSetECI currentCharacterSetECI, Collection<byte[]> byteSegments, Map<DecodeHintType, ?> hints) throws FormatException {
        String encoding;
        if ((count << 3) <= bits.available()) {
            byte[] readBytes = new byte[count];
            for (int i = 0; i < count; i++) {
                readBytes[i] = (byte) bits.readBits(8);
            }
            if (currentCharacterSetECI == null) {
                encoding = StringUtils.guessEncoding(readBytes, hints);
            } else {
                encoding = currentCharacterSetECI.name();
            }
            try {
                result.append(new String(readBytes, encoding));
                byteSegments.add(readBytes);
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static char toAlphaNumericChar(int value) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (value < cArr.length) {
            return cArr[value];
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeAlphanumericSegment(BitSource bits, StringBuilder result, int count, boolean fc1InEffect) throws FormatException {
        int start = result.length();
        while (count > 1) {
            if (bits.available() >= 11) {
                int nextTwoCharsBits = bits.readBits(11);
                result.append(toAlphaNumericChar(nextTwoCharsBits / 45));
                result.append(toAlphaNumericChar(nextTwoCharsBits % 45));
                count -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (count == 1) {
            if (bits.available() >= 6) {
                result.append(toAlphaNumericChar(bits.readBits(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (fc1InEffect) {
            for (int i = start; i < result.length(); i++) {
                if (result.charAt(i) == '%') {
                    if (i >= result.length() - 1 || result.charAt(i + 1) != '%') {
                        result.setCharAt(i, 29);
                    } else {
                        result.deleteCharAt(i + 1);
                    }
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bits, StringBuilder result, int count) throws FormatException {
        while (count >= 3) {
            if (bits.available() >= 10) {
                int readBits = bits.readBits(10);
                int threeDigitsBits = readBits;
                if (readBits < 1000) {
                    result.append(toAlphaNumericChar(threeDigitsBits / 100));
                    result.append(toAlphaNumericChar((threeDigitsBits / 10) % 10));
                    result.append(toAlphaNumericChar(threeDigitsBits % 10));
                    count -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (count != 2) {
            if (count == 1) {
                if (bits.available() >= 4) {
                    int readBits2 = bits.readBits(4);
                    int digitBits = readBits2;
                    if (readBits2 < 10) {
                        result.append(toAlphaNumericChar(digitBits));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
        } else if (bits.available() >= 7) {
            int readBits3 = bits.readBits(7);
            int twoDigitsBits = readBits3;
            if (readBits3 < 100) {
                result.append(toAlphaNumericChar(twoDigitsBits / 10));
                result.append(toAlphaNumericChar(twoDigitsBits % 10));
                return;
            }
            throw FormatException.getFormatInstance();
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int parseECIValue(BitSource bits) throws FormatException {
        int readBits = bits.readBits(8);
        int firstByte = readBits;
        if ((readBits & 128) == 0) {
            return firstByte & 127;
        }
        if ((firstByte & 192) == 128) {
            return ((firstByte & 63) << 8) | bits.readBits(8);
        } else if ((firstByte & 224) == 192) {
            return ((firstByte & 31) << 16) | bits.readBits(16);
        } else {
            throw FormatException.getFormatInstance();
        }
    }
}
