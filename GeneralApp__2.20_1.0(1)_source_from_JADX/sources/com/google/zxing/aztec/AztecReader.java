package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;

public final class AztecReader implements Reader {
    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r19, java.util.Map<com.google.zxing.DecodeHintType, ?> r20) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r18 = this;
            r1 = r20
            r2 = 0
            r3 = 0
            com.google.zxing.aztec.detector.Detector r0 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r4 = r19.getBlackMatrix()
            r0.<init>(r4)
            r4 = r0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.google.zxing.aztec.AztecDetectorResult r0 = r4.detect(r7)     // Catch:{ NotFoundException -> 0x0030, FormatException -> 0x002c }
            r9 = r8
            r9 = r0
            com.google.zxing.ResultPoint[] r0 = r0.getPoints()     // Catch:{ NotFoundException -> 0x002a, FormatException -> 0x0028 }
            r5 = r0
            com.google.zxing.aztec.decoder.Decoder r0 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x002a, FormatException -> 0x0028 }
            r0.<init>()     // Catch:{ NotFoundException -> 0x002a, FormatException -> 0x0028 }
            com.google.zxing.common.DecoderResult r0 = r0.decode(r9)     // Catch:{ NotFoundException -> 0x002a, FormatException -> 0x0028 }
            r6 = r0
            goto L_0x0033
        L_0x0028:
            r0 = move-exception
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            goto L_0x0032
        L_0x002c:
            r0 = move-exception
            r9 = r8
        L_0x002e:
            r3 = r0
            goto L_0x0034
        L_0x0030:
            r0 = move-exception
            r9 = r8
        L_0x0032:
            r2 = r0
        L_0x0033:
        L_0x0034:
            if (r6 != 0) goto L_0x0056
            r0 = 1
            com.google.zxing.aztec.AztecDetectorResult r0 = r4.detect(r0)     // Catch:{ NotFoundException -> 0x004e, FormatException -> 0x004c }
            r9 = r0
            com.google.zxing.ResultPoint[] r0 = r0.getPoints()     // Catch:{ NotFoundException -> 0x004e, FormatException -> 0x004c }
            r5 = r0
            com.google.zxing.aztec.decoder.Decoder r0 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x004e, FormatException -> 0x004c }
            r0.<init>()     // Catch:{ NotFoundException -> 0x004e, FormatException -> 0x004c }
            com.google.zxing.common.DecoderResult r0 = r0.decode(r9)     // Catch:{ NotFoundException -> 0x004e, FormatException -> 0x004c }
            r6 = r0
            goto L_0x0056
        L_0x004c:
            r0 = move-exception
            goto L_0x004f
        L_0x004e:
            r0 = move-exception
        L_0x004f:
            if (r2 != 0) goto L_0x0055
            if (r3 == 0) goto L_0x0054
            throw r3
        L_0x0054:
            throw r0
        L_0x0055:
            throw r2
        L_0x0056:
            if (r1 == 0) goto L_0x0070
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            java.lang.Object r0 = r1.get(r0)
            com.google.zxing.ResultPointCallback r0 = (com.google.zxing.ResultPointCallback) r0
            r9 = r0
            if (r0 == 0) goto L_0x0070
            int r0 = r5.length
        L_0x0064:
            if (r7 >= r0) goto L_0x006e
            r10 = r5[r7]
            r9.foundPossibleResultPoint(r10)
            int r7 = r7 + 1
            goto L_0x0064
        L_0x006e:
            r0 = r5
            goto L_0x0071
        L_0x0070:
            r0 = r8
        L_0x0071:
            com.google.zxing.Result r7 = new com.google.zxing.Result
            java.lang.String r11 = r6.getText()
            byte[] r12 = r6.getRawBytes()
            int r13 = r6.getNumBits()
            com.google.zxing.BarcodeFormat r15 = com.google.zxing.BarcodeFormat.AZTEC
            long r16 = java.lang.System.currentTimeMillis()
            r10 = r7
            r14 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16)
            java.util.List r9 = r6.getByteSegments()
            r0 = r9
            if (r9 == 0) goto L_0x0096
            com.google.zxing.ResultMetadataType r9 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r7.putMetadata(r9, r0)
        L_0x0096:
            java.lang.String r9 = r6.getECLevel()
            r8 = r9
            if (r9 == 0) goto L_0x00a2
            com.google.zxing.ResultMetadataType r9 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r7.putMetadata(r9, r8)
        L_0x00a2:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
    }
}
