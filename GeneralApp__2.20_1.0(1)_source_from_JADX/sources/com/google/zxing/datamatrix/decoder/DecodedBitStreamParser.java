package com.google.zxing.datamatrix.decoder;

import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.text.Typography;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', Typography.quote, '#', Typography.dollar, '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    /* renamed from: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$1 */
    static /* synthetic */ class C09251 {

        /* renamed from: $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f95xb73eb560 = new int[Mode.values().length];

        static {
            try {
                f95xb73eb560[Mode.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f95xb73eb560[Mode.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f95xb73eb560[Mode.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f95xb73eb560[Mode.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f95xb73eb560[Mode.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bytes) throws FormatException {
        BitSource bits = new BitSource(bytes);
        StringBuilder result = new StringBuilder(100);
        StringBuilder resultTrailer = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bits, result, resultTrailer);
            } else {
                int i = C09251.f95xb73eb560[mode.ordinal()];
                if (i == 1) {
                    decodeC40Segment(bits, result);
                } else if (i == 2) {
                    decodeTextSegment(bits, result);
                } else if (i == 3) {
                    decodeAnsiX12Segment(bits, result);
                } else if (i == 4) {
                    decodeEdifactSegment(bits, result);
                } else if (i == 5) {
                    decodeBase256Segment(bits, result, arrayList);
                } else {
                    throw FormatException.getFormatInstance();
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode == Mode.PAD_ENCODE) {
                break;
            }
        } while (bits.available() > 0);
        if (resultTrailer.length() > 0) {
            result.append(resultTrailer);
        }
        return new DecoderResult(bytes, result.toString(), arrayList.isEmpty() ? null : arrayList, null);
    }

    private static Mode decodeAsciiSegment(BitSource bits, StringBuilder result, StringBuilder resultTrailer) throws FormatException {
        boolean upperShift = false;
        do {
            int readBits = bits.readBits(8);
            int oneByte = readBits;
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (oneByte <= 128) {
                if (upperShift) {
                    oneByte += 128;
                }
                result.append((char) (oneByte - 1));
                return Mode.ASCII_ENCODE;
            } else if (oneByte == 129) {
                return Mode.PAD_ENCODE;
            } else {
                if (oneByte <= 229) {
                    int i = oneByte - 130;
                    int value = i;
                    if (i < 10) {
                        result.append('0');
                    }
                    result.append(value);
                } else if (oneByte == 230) {
                    return Mode.C40_ENCODE;
                } else {
                    if (oneByte == 231) {
                        return Mode.BASE256_ENCODE;
                    }
                    if (oneByte == 232) {
                        result.append(29);
                    } else if (!(oneByte == 233 || oneByte == 234)) {
                        if (oneByte == 235) {
                            upperShift = true;
                        } else {
                            String str = "\u001e\u0004";
                            if (oneByte == 236) {
                                result.append("[)>\u001e05\u001d");
                                resultTrailer.insert(0, str);
                            } else if (oneByte == 237) {
                                result.append("[)>\u001e06\u001d");
                                resultTrailer.insert(0, str);
                            } else if (oneByte == 238) {
                                return Mode.ANSIX12_ENCODE;
                            } else {
                                if (oneByte == 239) {
                                    return Mode.TEXT_ENCODE;
                                }
                                if (oneByte == 240) {
                                    return Mode.EDIFACT_ENCODE;
                                }
                                if (!(oneByte == 241 || oneByte < 242 || (oneByte == 254 && bits.available() == 0))) {
                                    throw FormatException.getFormatInstance();
                                }
                            }
                        }
                    }
                }
            }
        } while (bits.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeC40Segment(BitSource bits, StringBuilder result) throws FormatException {
        boolean upperShift = false;
        int[] cValues = new int[3];
        int shift = 0;
        while (bits.available() != 8) {
            int readBits = bits.readBits(8);
            int firstByte = readBits;
            if (readBits != 254) {
                parseTwoBytes(firstByte, bits.readBits(8), cValues);
                for (int i = 0; i < 3; i++) {
                    int cValue = cValues[i];
                    if (shift != 0) {
                        if (shift == 1) {
                            if (upperShift) {
                                result.append((char) (cValue + 128));
                                upperShift = false;
                            } else {
                                result.append((char) cValue);
                            }
                            shift = 0;
                        } else if (shift == 2) {
                            char[] cArr = C40_SHIFT2_SET_CHARS;
                            if (cValue < cArr.length) {
                                char c40char = cArr[cValue];
                                if (upperShift) {
                                    result.append((char) (c40char + 128));
                                    upperShift = false;
                                } else {
                                    result.append(c40char);
                                }
                            } else if (cValue == 27) {
                                result.append(29);
                            } else if (cValue == 30) {
                                upperShift = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            shift = 0;
                        } else if (shift == 3) {
                            if (upperShift) {
                                result.append((char) (cValue + 224));
                                upperShift = false;
                            } else {
                                result.append((char) (cValue + 96));
                            }
                            shift = 0;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (cValue < 3) {
                        shift = cValue + 1;
                    } else {
                        char[] cArr2 = C40_BASIC_SET_CHARS;
                        if (cValue < cArr2.length) {
                            char c40char2 = cArr2[cValue];
                            if (upperShift) {
                                result.append((char) (c40char2 + 128));
                                upperShift = false;
                            } else {
                                result.append(c40char2);
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
                if (bits.available() <= 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bits, StringBuilder result) throws FormatException {
        boolean upperShift = false;
        int[] cValues = new int[3];
        int shift = 0;
        while (bits.available() != 8) {
            int readBits = bits.readBits(8);
            int firstByte = readBits;
            if (readBits != 254) {
                parseTwoBytes(firstByte, bits.readBits(8), cValues);
                for (int i = 0; i < 3; i++) {
                    int cValue = cValues[i];
                    if (shift != 0) {
                        if (shift == 1) {
                            if (upperShift) {
                                result.append((char) (cValue + 128));
                                upperShift = false;
                            } else {
                                result.append((char) cValue);
                            }
                            shift = 0;
                        } else if (shift == 2) {
                            char[] cArr = TEXT_SHIFT2_SET_CHARS;
                            if (cValue < cArr.length) {
                                char textChar = cArr[cValue];
                                if (upperShift) {
                                    result.append((char) (textChar + 128));
                                    upperShift = false;
                                } else {
                                    result.append(textChar);
                                }
                            } else if (cValue == 27) {
                                result.append(29);
                            } else if (cValue == 30) {
                                upperShift = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            shift = 0;
                        } else if (shift == 3) {
                            char[] cArr2 = TEXT_SHIFT3_SET_CHARS;
                            if (cValue < cArr2.length) {
                                char textChar2 = cArr2[cValue];
                                if (upperShift) {
                                    result.append((char) (textChar2 + 128));
                                    upperShift = false;
                                } else {
                                    result.append(textChar2);
                                }
                                shift = 0;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (cValue < 3) {
                        shift = cValue + 1;
                    } else {
                        char[] cArr3 = TEXT_BASIC_SET_CHARS;
                        if (cValue < cArr3.length) {
                            char textChar3 = cArr3[cValue];
                            if (upperShift) {
                                result.append((char) (textChar3 + 128));
                                upperShift = false;
                            } else {
                                result.append(textChar3);
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
                if (bits.available() <= 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void decodeAnsiX12Segment(BitSource bits, StringBuilder result) throws FormatException {
        int[] cValues = new int[3];
        while (bits.available() != 8) {
            int readBits = bits.readBits(8);
            int firstByte = readBits;
            if (readBits != 254) {
                parseTwoBytes(firstByte, bits.readBits(8), cValues);
                for (int i = 0; i < 3; i++) {
                    int i2 = cValues[i];
                    int cValue = i2;
                    if (i2 == 0) {
                        result.append(13);
                    } else if (cValue == 1) {
                        result.append('*');
                    } else if (cValue == 2) {
                        result.append(Typography.greater);
                    } else if (cValue == 3) {
                        result.append(' ');
                    } else if (cValue < 14) {
                        result.append((char) (cValue + 44));
                    } else if (cValue < 40) {
                        result.append((char) (cValue + 51));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (bits.available() <= 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void parseTwoBytes(int firstByte, int secondByte, int[] result) {
        int i = ((firstByte << 8) + secondByte) - 1;
        int fullBitValue = i;
        int temp = i / 1600;
        result[0] = temp;
        int i2 = fullBitValue - (temp * 1600);
        int fullBitValue2 = i2;
        int temp2 = i2 / 40;
        result[1] = temp2;
        result[2] = fullBitValue2 - (temp2 * 40);
    }

    private static void decodeEdifactSegment(BitSource bits, StringBuilder result) {
        while (bits.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bits.readBits(6);
                int edifactValue = readBits;
                if (readBits == 31) {
                    int bitOffset = 8 - bits.getBitOffset();
                    int bitsLeft = bitOffset;
                    if (bitOffset != 8) {
                        bits.readBits(bitsLeft);
                    }
                    return;
                }
                if ((edifactValue & 32) == 0) {
                    edifactValue |= 64;
                }
                result.append((char) edifactValue);
            }
            if (bits.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(BitSource bits, StringBuilder result, Collection<byte[]> byteSegments) throws FormatException {
        int count;
        int codewordPosition = bits.getByteOffset() + 1;
        int codewordPosition2 = codewordPosition + 1;
        int codewordPosition3 = unrandomize255State(bits.readBits(8), codewordPosition);
        int d1 = codewordPosition3;
        if (codewordPosition3 == 0) {
            count = bits.available() / 8;
        } else if (d1 < 250) {
            count = d1;
        } else {
            count = unrandomize255State(bits.readBits(8), codewordPosition2) + ((d1 - 249) * Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            codewordPosition2++;
        }
        if (count >= 0) {
            byte[] bytes = new byte[count];
            int i = 0;
            while (i < count) {
                if (bits.available() >= 8) {
                    int codewordPosition4 = codewordPosition2 + 1;
                    bytes[i] = (byte) unrandomize255State(bits.readBits(8), codewordPosition2);
                    i++;
                    codewordPosition2 = codewordPosition4;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            byteSegments.add(bytes);
            try {
                result.append(new String(bytes, "ISO8859_1"));
            } catch (UnsupportedEncodingException uee) {
                StringBuilder sb = new StringBuilder("Platform does not support required encoding: ");
                sb.append(uee);
                throw new IllegalStateException(sb.toString());
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int unrandomize255State(int randomizedBase256Codeword, int base256CodewordPosition) {
        int i = randomizedBase256Codeword - (((base256CodewordPosition * 149) % 255) + 1);
        int tempVariable = i;
        return i >= 0 ? tempVariable : tempVariable + 256;
    }
}
