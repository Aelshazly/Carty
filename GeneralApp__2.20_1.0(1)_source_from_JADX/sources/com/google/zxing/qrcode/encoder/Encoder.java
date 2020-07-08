package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import kotlin.UByte;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    /* renamed from: com.google.zxing.qrcode.encoder.Encoder$1 */
    static /* synthetic */ class C09401 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode = new int[Mode.values().length];

        static {
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private Encoder() {
    }

    private static int calculateMaskPenalty(ByteMatrix matrix) {
        return MaskUtil.applyMaskPenaltyRule1(matrix) + MaskUtil.applyMaskPenaltyRule2(matrix) + MaskUtil.applyMaskPenaltyRule3(matrix) + MaskUtil.applyMaskPenaltyRule4(matrix);
    }

    public static QRCode encode(String content, ErrorCorrectionLevel ecLevel) throws WriterException {
        return encode(content, ecLevel, null);
    }

    public static QRCode encode(String content, ErrorCorrectionLevel ecLevel, Map<EncodeHintType, ?> hints) throws WriterException {
        Version version;
        String encoding = "ISO-8859-1";
        if (hints != null && hints.containsKey(EncodeHintType.CHARACTER_SET)) {
            encoding = hints.get(EncodeHintType.CHARACTER_SET).toString();
        }
        Mode mode = chooseMode(content, encoding);
        BitArray headerBits = new BitArray();
        if (mode == Mode.BYTE && !"ISO-8859-1".equals(encoding)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(encoding);
            CharacterSetECI eci = characterSetECIByName;
            if (characterSetECIByName != null) {
                appendECI(eci, headerBits);
            }
        }
        appendModeInfo(mode, headerBits);
        BitArray dataBits = new BitArray();
        appendBytes(content, mode, dataBits, encoding);
        if (hints == null || !hints.containsKey(EncodeHintType.QR_VERSION)) {
            version = recommendVersion(ecLevel, mode, headerBits, dataBits);
        } else {
            version = Version.getVersionForNumber(Integer.parseInt(hints.get(EncodeHintType.QR_VERSION).toString()));
            if (!willFit(calculateBitsNeeded(mode, headerBits, dataBits, version), version, ecLevel)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        BitArray bitArray = new BitArray();
        BitArray headerAndDataBits = bitArray;
        bitArray.appendBitArray(headerBits);
        appendLengthInfo(mode == Mode.BYTE ? dataBits.getSizeInBytes() : content.length(), version, mode, headerAndDataBits);
        headerAndDataBits.appendBitArray(dataBits);
        ECBlocks ecBlocks = version.getECBlocksForLevel(ecLevel);
        int totalCodewords = version.getTotalCodewords() - ecBlocks.getTotalECCodewords();
        int numDataBytes = totalCodewords;
        terminateBits(totalCodewords, headerAndDataBits);
        BitArray finalBits = interleaveWithECBytes(headerAndDataBits, version.getTotalCodewords(), numDataBytes, ecBlocks.getNumBlocks());
        QRCode qRCode = new QRCode();
        QRCode qrCode = qRCode;
        qRCode.setECLevel(ecLevel);
        qrCode.setMode(mode);
        qrCode.setVersion(version);
        int dimension = version.getDimensionForVersion();
        ByteMatrix matrix = new ByteMatrix(dimension, dimension);
        int maskPattern = chooseMaskPattern(finalBits, ecLevel, version, matrix);
        qrCode.setMaskPattern(maskPattern);
        MatrixUtil.buildMatrix(finalBits, ecLevel, version, maskPattern, matrix);
        qrCode.setMatrix(matrix);
        return qrCode;
    }

    private static Version recommendVersion(ErrorCorrectionLevel ecLevel, Mode mode, BitArray headerBits, BitArray dataBits) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, headerBits, dataBits, chooseVersion(calculateBitsNeeded(mode, headerBits, dataBits, Version.getVersionForNumber(1)), ecLevel)), ecLevel);
    }

    private static int calculateBitsNeeded(Mode mode, BitArray headerBits, BitArray dataBits, Version version) {
        return headerBits.getSize() + mode.getCharacterCountBits(version) + dataBits.getSize();
    }

    static int getAlphanumericCode(int code) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (code < iArr.length) {
            return iArr[code];
        }
        return -1;
    }

    public static Mode chooseMode(String content) {
        return chooseMode(content, null);
    }

    private static Mode chooseMode(String content, String encoding) {
        if ("Shift_JIS".equals(encoding) && isOnlyDoubleByteKanji(content)) {
            return Mode.KANJI;
        }
        boolean hasNumeric = false;
        boolean hasAlphanumeric = false;
        for (int i = 0; i < content.length(); i++) {
            char charAt = content.charAt(i);
            char c = charAt;
            if (charAt >= '0' && c <= '9') {
                hasNumeric = true;
            } else if (getAlphanumericCode(c) == -1) {
                return Mode.BYTE;
            } else {
                hasAlphanumeric = true;
            }
        }
        if (hasAlphanumeric) {
            return Mode.ALPHANUMERIC;
        }
        if (hasNumeric) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean isOnlyDoubleByteKanji(String content) {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            int length2 = length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length2; i += 2) {
                int i2 = bytes[i] & 255;
                int byte1 = i2;
                if ((i2 < 129 || byte1 > 159) && (byte1 < 224 || byte1 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int chooseMaskPattern(BitArray bits, ErrorCorrectionLevel ecLevel, Version version, ByteMatrix matrix) throws WriterException {
        int minPenalty = Integer.MAX_VALUE;
        int bestMaskPattern = -1;
        for (int maskPattern = 0; maskPattern < 8; maskPattern++) {
            MatrixUtil.buildMatrix(bits, ecLevel, version, maskPattern, matrix);
            int calculateMaskPenalty = calculateMaskPenalty(matrix);
            int penalty = calculateMaskPenalty;
            if (calculateMaskPenalty < minPenalty) {
                minPenalty = penalty;
                bestMaskPattern = maskPattern;
            }
        }
        return bestMaskPattern;
    }

    private static Version chooseVersion(int numInputBits, ErrorCorrectionLevel ecLevel) throws WriterException {
        for (int versionNum = 1; versionNum <= 40; versionNum++) {
            Version version = Version.getVersionForNumber(versionNum);
            if (willFit(numInputBits, version, ecLevel)) {
                return version;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean willFit(int numInputBits, Version version, ErrorCorrectionLevel ecLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(ecLevel).getTotalECCodewords() >= (numInputBits + 7) / 8;
    }

    static void terminateBits(int numDataBytes, BitArray bits) throws WriterException {
        int capacity = numDataBytes << 3;
        if (bits.getSize() <= capacity) {
            for (int i = 0; i < 4 && bits.getSize() < capacity; i++) {
                bits.appendBit(false);
            }
            int size = bits.getSize() & 7;
            int numBitsInLastByte = size;
            if (size > 0) {
                for (int i2 = numBitsInLastByte; i2 < 8; i2++) {
                    bits.appendBit(false);
                }
            }
            int numPaddingBytes = numDataBytes - bits.getSizeInBytes();
            for (int i3 = 0; i3 < numPaddingBytes; i3++) {
                bits.appendBits((i3 & 1) == 0 ? 236 : 17, 8);
            }
            if (bits.getSize() != capacity) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        StringBuilder sb = new StringBuilder("data bits cannot fit in the QR Code");
        sb.append(bits.getSize());
        sb.append(" > ");
        sb.append(capacity);
        throw new WriterException(sb.toString());
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int numTotalBytes, int numDataBytes, int numRSBlocks, int blockID, int[] numDataBytesInBlock, int[] numECBytesInBlock) throws WriterException {
        int i = numTotalBytes;
        int i2 = numRSBlocks;
        int i3 = blockID;
        if (i3 < i2) {
            int numRsBlocksInGroup2 = i % i2;
            int numRsBlocksInGroup1 = i2 - numRsBlocksInGroup2;
            int i4 = i / i2;
            int i5 = numDataBytes / i2;
            int numDataBytesInGroup1 = i5;
            int numDataBytesInGroup2 = i5 + 1;
            int numEcBytesInGroup1 = i4 - numDataBytesInGroup1;
            int numEcBytesInGroup2 = (i4 + 1) - numDataBytesInGroup2;
            if (numEcBytesInGroup1 != numEcBytesInGroup2) {
                throw new WriterException("EC bytes mismatch");
            } else if (i2 != numRsBlocksInGroup1 + numRsBlocksInGroup2) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((numDataBytesInGroup1 + numEcBytesInGroup1) * numRsBlocksInGroup1) + ((numDataBytesInGroup2 + numEcBytesInGroup2) * numRsBlocksInGroup2)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i3 < numRsBlocksInGroup1) {
                numDataBytesInBlock[0] = numDataBytesInGroup1;
                numECBytesInBlock[0] = numEcBytesInGroup1;
            } else {
                numDataBytesInBlock[0] = numDataBytesInGroup2;
                numECBytesInBlock[0] = numEcBytesInGroup2;
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    static BitArray interleaveWithECBytes(BitArray bits, int numTotalBytes, int numDataBytes, int numRSBlocks) throws WriterException {
        int i = numTotalBytes;
        int i2 = numDataBytes;
        int i3 = numRSBlocks;
        if (bits.getSizeInBytes() == i2) {
            Collection<BlockPair> blocks = new ArrayList<>(i3);
            int dataBytesOffset = 0;
            int maxNumDataBytes = 0;
            int maxNumEcBytes = 0;
            int size = 0;
            for (int i4 = 0; i4 < i3; i4++) {
                int[] numDataBytesInBlock = new int[1];
                int[] numEcBytesInBlock = new int[1];
                int[] numDataBytesInBlock2 = numDataBytesInBlock;
                getNumDataBytesAndNumECBytesForBlockID(numTotalBytes, numDataBytes, numRSBlocks, i4, numDataBytesInBlock, numEcBytesInBlock);
                int i5 = numDataBytesInBlock2[0];
                int i6 = size;
                size = i5;
                byte[] dataBytes = new byte[i5];
                bits.toBytes(dataBytesOffset << 3, dataBytes, 0, size);
                byte[] ecBytes = generateECBytes(dataBytes, numEcBytesInBlock[0]);
                blocks.add(new BlockPair(dataBytes, ecBytes));
                maxNumDataBytes = Math.max(maxNumDataBytes, size);
                maxNumEcBytes = Math.max(maxNumEcBytes, ecBytes.length);
                dataBytesOffset += numDataBytesInBlock2[0];
            }
            BitArray bitArray = bits;
            if (i2 == dataBytesOffset) {
                BitArray result = new BitArray();
                for (int i7 = 0; i7 < maxNumDataBytes; i7++) {
                    for (BlockPair dataBytes2 : blocks) {
                        byte[] dataBytes3 = dataBytes2.getDataBytes();
                        if (i7 < dataBytes3.length) {
                            result.appendBits(dataBytes3[i7], 8);
                        }
                    }
                }
                for (int i8 = 0; i8 < maxNumEcBytes; i8++) {
                    for (BlockPair errorCorrectionBytes : blocks) {
                        byte[] ecBytes2 = errorCorrectionBytes.getErrorCorrectionBytes();
                        if (i8 < ecBytes2.length) {
                            result.appendBits(ecBytes2[i8], 8);
                        }
                    }
                }
                if (i == result.getSizeInBytes()) {
                    return result;
                }
                StringBuilder sb = new StringBuilder("Interleaving error: ");
                sb.append(i);
                sb.append(" and ");
                sb.append(result.getSizeInBytes());
                sb.append(" differ.");
                throw new WriterException(sb.toString());
            }
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = bits;
        throw new WriterException("Number of bits and data bytes does not match");
    }

    static byte[] generateECBytes(byte[] dataBytes, int numEcBytesInBlock) {
        int length = dataBytes.length;
        int numDataBytes = length;
        int[] toEncode = new int[(length + numEcBytesInBlock)];
        for (int i = 0; i < numDataBytes; i++) {
            toEncode[i] = dataBytes[i] & UByte.MAX_VALUE;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(toEncode, numEcBytesInBlock);
        byte[] ecBytes = new byte[numEcBytesInBlock];
        for (int i2 = 0; i2 < numEcBytesInBlock; i2++) {
            ecBytes[i2] = (byte) toEncode[numDataBytes + i2];
        }
        return ecBytes;
    }

    static void appendModeInfo(Mode mode, BitArray bits) {
        bits.appendBits(mode.getBits(), 4);
    }

    static void appendLengthInfo(int numLetters, Version version, Mode mode, BitArray bits) throws WriterException {
        int numBits = mode.getCharacterCountBits(version);
        if (numLetters < (1 << numBits)) {
            bits.appendBits(numLetters, numBits);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(numLetters);
        sb.append(" is bigger than ");
        sb.append((1 << numBits) - 1);
        throw new WriterException(sb.toString());
    }

    static void appendBytes(String content, Mode mode, BitArray bits, String encoding) throws WriterException {
        int i = C09401.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()];
        if (i == 1) {
            appendNumericBytes(content, bits);
        } else if (i == 2) {
            appendAlphanumericBytes(content, bits);
        } else if (i == 3) {
            append8BitBytes(content, bits, encoding);
        } else if (i == 4) {
            appendKanjiBytes(content, bits);
        } else {
            StringBuilder sb = new StringBuilder("Invalid mode: ");
            sb.append(mode);
            throw new WriterException(sb.toString());
        }
    }

    static void appendNumericBytes(CharSequence content, BitArray bits) {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int num1 = content.charAt(i) - '0';
            if (i + 2 < length) {
                bits.appendBits((num1 * 100) + ((content.charAt(i + 1) - '0') * 10) + (content.charAt(i + 2) - '0'), 10);
                i += 3;
            } else if (i + 1 < length) {
                bits.appendBits((num1 * 10) + (content.charAt(i + 1) - '0'), 7);
                i += 2;
            } else {
                bits.appendBits(num1, 4);
                i++;
            }
        }
    }

    static void appendAlphanumericBytes(CharSequence content, BitArray bits) throws WriterException {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int alphanumericCode = getAlphanumericCode(content.charAt(i));
            int code1 = alphanumericCode;
            if (alphanumericCode == -1) {
                throw new WriterException();
            } else if (i + 1 < length) {
                int alphanumericCode2 = getAlphanumericCode(content.charAt(i + 1));
                int code2 = alphanumericCode2;
                if (alphanumericCode2 != -1) {
                    bits.appendBits((code1 * 45) + code2, 11);
                    i += 2;
                } else {
                    throw new WriterException();
                }
            } else {
                bits.appendBits(code1, 6);
                i++;
            }
        }
    }

    static void append8BitBytes(String content, BitArray bits, String encoding) throws WriterException {
        try {
            for (byte b : content.getBytes(encoding)) {
                bits.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new WriterException((Throwable) uee);
        }
    }

    static void appendKanjiBytes(String content, BitArray bits) throws WriterException {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            int i = 0;
            while (i < length) {
                int code = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                int subtracted = -1;
                if (code >= 33088 && code <= 40956) {
                    subtracted = code - 33088;
                } else if (code >= 57408 && code <= 60351) {
                    subtracted = code - 49472;
                }
                if (subtracted != -1) {
                    bits.appendBits(((subtracted >> 8) * 192) + (subtracted & 255), 13);
                    i += 2;
                } else {
                    throw new WriterException("Invalid byte sequence");
                }
            }
        } catch (UnsupportedEncodingException uee) {
            throw new WriterException((Throwable) uee);
        }
    }

    private static void appendECI(CharacterSetECI eci, BitArray bits) {
        bits.appendBits(Mode.ECI.getBits(), 4);
        bits.appendBits(eci.getValue(), 8);
    }
}
