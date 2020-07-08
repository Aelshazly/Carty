package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, null);
    }

    public Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException, FormatException {
        try {
            return doDecode(image, hints);
        } catch (NotFoundException nfe) {
            if (!(hints != null && hints.containsKey(DecodeHintType.TRY_HARDER)) || !image.isRotateSupported()) {
                throw nfe;
            }
            BinaryBitmap rotatedImage = image.rotateCounterClockwise();
            Result doDecode = doDecode(rotatedImage, hints);
            Result result = doDecode;
            Map<ResultMetadataType, ?> metadata = doDecode.getResultMetadata();
            int orientation = 270;
            if (metadata != null && metadata.containsKey(ResultMetadataType.ORIENTATION)) {
                orientation = (((Integer) metadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            result.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(orientation));
            ResultPoint[] resultPoints = result.getResultPoints();
            ResultPoint[] points = resultPoints;
            if (resultPoints != null) {
                int height = rotatedImage.getHeight();
                for (int i = 0; i < points.length; i++) {
                    points[i] = new ResultPoint((((float) height) - points[i].getY()) - 1.0f, points[i].getX());
                }
            }
            return result;
        }
    }

    public void reset() {
    }

    /* JADX WARNING: type inference failed for: r27v0, types: [java.util.Map<com.google.zxing.DecodeHintType, ?>] */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r13v0 */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r13v1 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r13v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r24v0 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r13v3 */
    /* JADX WARNING: type inference failed for: r0v9, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r13v4 */
    /* JADX WARNING: type inference failed for: r2v6, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r21v0 */
    /* JADX WARNING: type inference failed for: r13v5 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r21v1 */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.google.zxing.Result] */
    /* JADX WARNING: type inference failed for: r13v6, types: [com.google.zxing.Result] */
    /* JADX WARNING: type inference failed for: r21v2 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v16, types: [java.util.EnumMap, java.util.Map] */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: type inference failed for: r13v7, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r0v21 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r13v8 */
    /* JADX WARNING: type inference failed for: r13v9 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r13v10 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r13v11 */
    /* JADX WARNING: type inference failed for: r13v12 */
    /* JADX WARNING: type inference failed for: r13v13 */
    /* JADX WARNING: type inference failed for: r13v14 */
    /* JADX WARNING: type inference failed for: r13v15 */
    /* JADX WARNING: type inference failed for: r13v16 */
    /* JADX WARNING: type inference failed for: r21v3 */
    /* JADX WARNING: type inference failed for: r21v4 */
    /* JADX WARNING: type inference failed for: r13v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00fd, code lost:
        return r13;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Map<com.google.zxing.DecodeHintType, ?>, code=null, for r27v0, types: [java.util.Map<com.google.zxing.DecodeHintType, ?>] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r13v1
      assigns: []
      uses: []
      mth insns count: 149
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a3 A[SYNTHETIC, Splitter:B:47:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00fd A[EDGE_INSN: B:81:0x00fd->B:67:0x00fd ?: BREAK  
    EDGE_INSN: B:81:0x00fd->B:67:0x00fd ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 20 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) throws com.google.zxing.NotFoundException {
        /*
            r25 = this;
            r0 = r27
            int r1 = r26.getWidth()
            int r2 = r26.getHeight()
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>(r1)
            int r4 = r2 >> 1
            r6 = 1
            if (r0 == 0) goto L_0x001e
            com.google.zxing.DecodeHintType r7 = com.google.zxing.DecodeHintType.TRY_HARDER
            boolean r7 = r0.containsKey(r7)
            if (r7 == 0) goto L_0x001e
            r7 = 1
            goto L_0x001f
        L_0x001e:
            r7 = 0
        L_0x001f:
            if (r7 == 0) goto L_0x0024
            r8 = 8
            goto L_0x0025
        L_0x0024:
            r8 = 5
        L_0x0025:
            int r8 = r2 >> r8
            int r8 = java.lang.Math.max(r6, r8)
            if (r7 == 0) goto L_0x002f
            r9 = r2
            goto L_0x0031
        L_0x002f:
            r9 = 15
        L_0x0031:
            r10 = 0
            r11 = 0
            r12 = r11
            r13 = r12
            r14 = r13
            r11 = r3
            r3 = r0
            r0 = 0
        L_0x0039:
            if (r10 >= r9) goto L_0x013c
            int r15 = r10 + 1
            r5 = 2
            int r15 = r15 / r5
            r17 = r10 & 1
            if (r17 != 0) goto L_0x0046
            r17 = 1
            goto L_0x0048
        L_0x0046:
            r17 = 0
        L_0x0048:
            if (r17 == 0) goto L_0x004c
            r6 = r15
            goto L_0x004d
        L_0x004c:
            int r6 = -r15
        L_0x004d:
            int r6 = r6 * r8
            int r6 = r6 + r4
            r27 = r6
            if (r6 < 0) goto L_0x0133
            r6 = r27
            if (r6 >= r2) goto L_0x012c
            r5 = r26
            com.google.zxing.common.BitArray r0 = r5.getBlackRow(r6, r11)     // Catch:{ NotFoundException -> 0x0117 }
            r11 = r0
            r0 = 0
            r24 = r3
            r3 = r0
            r0 = r24
        L_0x0066:
            r19 = r2
            r2 = 2
            if (r3 >= r2) goto L_0x010f
            r2 = 1
            if (r3 != r2) goto L_0x0094
            r11.reverse()
            if (r0 == 0) goto L_0x0091
            com.google.zxing.DecodeHintType r2 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r2 = r0.containsKey(r2)
            if (r2 == 0) goto L_0x0091
            java.util.EnumMap r2 = new java.util.EnumMap
            r20 = r4
            java.lang.Class<com.google.zxing.DecodeHintType> r4 = com.google.zxing.DecodeHintType.class
            r2.<init>(r4)
            r4 = r13
            r13 = r2
            r2.putAll(r0)
            com.google.zxing.DecodeHintType r2 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r13.remove(r2)
            r0 = r13
            r2 = r0
            goto L_0x0097
        L_0x0091:
            r20 = r4
            goto L_0x0096
        L_0x0094:
            r20 = r4
        L_0x0096:
            r2 = r0
        L_0x0097:
            r4 = r25
            com.google.zxing.Result r0 = r4.decodeRow(r6, r11, r2)     // Catch:{ ReaderException -> 0x00fe }
            r13 = r0
            r21 = r2
            r2 = 1
            if (r3 != r2) goto L_0x00fd
            com.google.zxing.ResultMetadataType r0 = com.google.zxing.ResultMetadataType.ORIENTATION     // Catch:{ ReaderException -> 0x00f7 }
            r2 = 180(0xb4, float:2.52E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ ReaderException -> 0x00f7 }
            r13.putMetadata(r0, r2)     // Catch:{ ReaderException -> 0x00f7 }
            com.google.zxing.ResultPoint[] r0 = r13.getResultPoints()     // Catch:{ ReaderException -> 0x00f7 }
            r2 = r14
            r2 = r0
            if (r0 == 0) goto L_0x00fd
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00f0 }
            float r14 = (float) r1
            r16 = 0
            r22 = r2[r16]     // Catch:{ ReaderException -> 0x00ee }
            float r22 = r22.getX()     // Catch:{ ReaderException -> 0x00ee }
            float r14 = r14 - r22
            r22 = 1065353216(0x3f800000, float:1.0)
            float r14 = r14 - r22
            r23 = r2[r16]     // Catch:{ ReaderException -> 0x00ee }
            float r4 = r23.getY()     // Catch:{ ReaderException -> 0x00ee }
            r0.<init>(r14, r4)     // Catch:{ ReaderException -> 0x00ee }
            r2[r16] = r0     // Catch:{ ReaderException -> 0x00ee }
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00ee }
            float r4 = (float) r1
            r18 = 1
            r14 = r2[r18]     // Catch:{ ReaderException -> 0x00ec }
            float r14 = r14.getX()     // Catch:{ ReaderException -> 0x00ec }
            float r4 = r4 - r14
            float r4 = r4 - r22
            r14 = r2[r18]     // Catch:{ ReaderException -> 0x00ec }
            float r14 = r14.getY()     // Catch:{ ReaderException -> 0x00ec }
            r0.<init>(r4, r14)     // Catch:{ ReaderException -> 0x00ec }
            r2[r18] = r0     // Catch:{ ReaderException -> 0x00ec }
            goto L_0x00fd
        L_0x00ec:
            r0 = move-exception
            goto L_0x00f5
        L_0x00ee:
            r0 = move-exception
            goto L_0x00f3
        L_0x00f0:
            r0 = move-exception
            r16 = 0
        L_0x00f3:
            r18 = 1
        L_0x00f5:
            r14 = r2
            goto L_0x0105
        L_0x00f7:
            r0 = move-exception
            r16 = 0
            r18 = 1
            goto L_0x0105
        L_0x00fd:
            return r13
        L_0x00fe:
            r0 = move-exception
            r21 = r2
            r16 = 0
            r18 = 1
        L_0x0105:
            int r3 = r3 + 1
            r2 = r19
            r4 = r20
            r0 = r21
            goto L_0x0066
        L_0x010f:
            r20 = r4
            r16 = 0
            r18 = 1
            r3 = r0
            goto L_0x0122
        L_0x0117:
            r0 = move-exception
            r19 = r2
            r20 = r4
            r16 = 0
            r18 = 1
            r0 = r12
        L_0x0122:
            int r10 = r10 + 1
            r0 = r6
            r2 = r19
            r4 = r20
            r6 = 1
            goto L_0x0039
        L_0x012c:
            r5 = r26
            r19 = r2
            r20 = r4
            goto L_0x0142
        L_0x0133:
            r5 = r26
            r6 = r27
            r19 = r2
            r20 = r4
            goto L_0x0142
        L_0x013c:
            r5 = r26
            r19 = r2
            r20 = r4
        L_0x0142:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    protected static void recordPattern(BitArray row, int start, int[] counters) throws NotFoundException {
        int numCounters = counters.length;
        Arrays.fill(counters, 0, numCounters, 0);
        int end = row.getSize();
        if (start < end) {
            boolean isWhite = !row.get(start);
            int counterPosition = 0;
            int i = start;
            while (i < end) {
                if (!(row.get(i) ^ isWhite)) {
                    counterPosition++;
                    if (counterPosition == numCounters) {
                        break;
                    }
                    counters[counterPosition] = 1;
                    isWhite = !isWhite;
                } else {
                    counters[counterPosition] = counters[counterPosition] + 1;
                }
                i++;
            }
            if (counterPosition == numCounters) {
                return;
            }
            if (counterPosition != numCounters - 1 || i != end) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPatternInReverse(BitArray row, int start, int[] counters) throws NotFoundException {
        int numTransitionsLeft = counters.length;
        boolean last = row.get(start);
        while (start > 0 && numTransitionsLeft >= 0) {
            start--;
            if (row.get(start) != last) {
                numTransitionsLeft--;
                last = !last;
            }
        }
        if (numTransitionsLeft < 0) {
            recordPattern(row, start + 1, counters);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
        int numCounters = counters.length;
        int total = 0;
        int patternLength = 0;
        for (int i = 0; i < numCounters; i++) {
            total += counters[i];
            patternLength += pattern[i];
        }
        if (total < patternLength) {
            return Float.POSITIVE_INFINITY;
        }
        float unitBarWidth = ((float) total) / ((float) patternLength);
        float maxIndividualVariance2 = maxIndividualVariance * unitBarWidth;
        float totalVariance = 0.0f;
        for (int x = 0; x < numCounters; x++) {
            int counter = counters[x];
            float scaledPattern = ((float) pattern[x]) * unitBarWidth;
            float f = ((float) counter) > scaledPattern ? ((float) counter) - scaledPattern : scaledPattern - ((float) counter);
            float variance = f;
            if (f > maxIndividualVariance2) {
                return Float.POSITIVE_INFINITY;
            }
            totalVariance += variance;
        }
        return totalVariance / ((float) total);
    }
}
