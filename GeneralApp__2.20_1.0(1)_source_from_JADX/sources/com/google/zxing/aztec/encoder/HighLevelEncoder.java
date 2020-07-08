package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.UByte;
import okhttp3.internal.p017ws.WebSocketProtocol;

public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP;
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int MODE_DIGIT = 2;
    static final int MODE_LOWER = 1;
    static final int MODE_MIXED = 3;
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int MODE_PUNCT = 4;
    static final int MODE_UPPER = 0;
    static final int[][] SHIFT_TABLE;
    private final byte[] text;

    static {
        Class<int> cls = int.class;
        int[][] iArr = (int[][]) Array.newInstance(cls, new int[]{5, 256});
        CHAR_MAP = iArr;
        iArr[0][32] = 1;
        for (int c = 65; c <= 90; c++) {
            CHAR_MAP[0][c] = (c - 65) + 2;
        }
        CHAR_MAP[1][32] = 1;
        for (int c2 = 97; c2 <= 122; c2++) {
            CHAR_MAP[1][c2] = (c2 - 97) + 2;
        }
        CHAR_MAP[2][32] = 1;
        for (int c3 = 48; c3 <= 57; c3++) {
            CHAR_MAP[2][c3] = (c3 - 48) + 2;
        }
        int[][] iArr2 = CHAR_MAP;
        iArr2[2][44] = 12;
        iArr2[2][46] = 13;
        int[] mixedTable = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, WebSocketProtocol.PAYLOAD_SHORT, 127};
        for (int i = 0; i < 28; i++) {
            CHAR_MAP[3][mixedTable[i]] = i;
        }
        int[] punctTable = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i2 = 0; i2 < 31; i2++) {
            if (punctTable[i2] > 0) {
                CHAR_MAP[4][punctTable[i2]] = i2;
            }
        }
        int[][] iArr3 = (int[][]) Array.newInstance(cls, new int[]{6, 6});
        SHIFT_TABLE = iArr3;
        for (int[] fill : iArr3) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr4 = SHIFT_TABLE;
        iArr4[0][4] = 0;
        iArr4[1][4] = 0;
        iArr4[1][0] = 28;
        iArr4[3][4] = 0;
        iArr4[2][4] = 0;
        iArr4[2][0] = 15;
    }

    public HighLevelEncoder(byte[] text2) {
        this.text = text2;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v4, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.common.BitArray encode() {
        /*
            r7 = this;
            com.google.zxing.aztec.encoder.State r0 = com.google.zxing.aztec.encoder.State.INITIAL_STATE
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
        L_0x0007:
            byte[] r2 = r7.text
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0056
            int r3 = r1 + 1
            int r4 = r2.length
            r5 = 0
            if (r3 >= r4) goto L_0x0017
            int r3 = r1 + 1
            byte r2 = r2[r3]
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            byte[] r3 = r7.text
            byte r3 = r3[r1]
            r4 = 13
            if (r3 == r4) goto L_0x003f
            r4 = 44
            r6 = 32
            if (r3 == r4) goto L_0x003a
            r4 = 46
            if (r3 == r4) goto L_0x0035
            r4 = 58
            if (r3 == r4) goto L_0x0030
            r3 = 0
            goto L_0x0046
        L_0x0030:
            if (r2 != r6) goto L_0x0033
            r5 = 5
        L_0x0033:
            r3 = r5
            goto L_0x0046
        L_0x0035:
            if (r2 != r6) goto L_0x0038
            r5 = 3
        L_0x0038:
            r3 = r5
            goto L_0x0046
        L_0x003a:
            if (r2 != r6) goto L_0x003d
            r5 = 4
        L_0x003d:
            r3 = r5
            goto L_0x0046
        L_0x003f:
            r3 = 10
            if (r2 != r3) goto L_0x0044
            r5 = 2
        L_0x0044:
            r3 = r5
        L_0x0046:
            if (r3 <= 0) goto L_0x004f
            java.util.Collection r0 = updateStateListForPair(r0, r1, r3)
            int r1 = r1 + 1
            goto L_0x0053
        L_0x004f:
            java.util.Collection r0 = r7.updateStateListForChar(r0, r1)
        L_0x0053:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0056:
            com.google.zxing.aztec.encoder.HighLevelEncoder$1 r1 = new com.google.zxing.aztec.encoder.HighLevelEncoder$1
            r1.<init>()
            java.lang.Object r1 = java.util.Collections.min(r0, r1)
            com.google.zxing.aztec.encoder.State r1 = (com.google.zxing.aztec.encoder.State) r1
            r2 = 0
            byte[] r3 = r7.text
            com.google.zxing.common.BitArray r1 = r1.toBitArray(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }

    private Collection<State> updateStateListForChar(Iterable<State> states, int index) {
        Collection<State> result = new LinkedList<>();
        for (State state : states) {
            updateStateForChar(state, index, result);
        }
        return simplifyStates(result);
    }

    private void updateStateForChar(State state, int index, Collection<State> result) {
        char ch = (char) (this.text[index] & UByte.MAX_VALUE);
        boolean charInCurrentTable = CHAR_MAP[state.getMode()][ch] > 0;
        State stateNoBinary = null;
        for (int mode = 0; mode <= 4; mode++) {
            int i = CHAR_MAP[mode][ch];
            int charInMode = i;
            if (i > 0) {
                if (stateNoBinary == null) {
                    stateNoBinary = state.endBinaryShift(index);
                }
                if (!charInCurrentTable || mode == state.getMode() || mode == 2) {
                    result.add(stateNoBinary.latchAndAppend(mode, charInMode));
                }
                if (!charInCurrentTable && SHIFT_TABLE[state.getMode()][mode] >= 0) {
                    result.add(stateNoBinary.shiftAndAppend(mode, charInMode));
                }
            }
        }
        if (state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][ch] == 0) {
            result.add(state.addBinaryShiftChar(index));
        }
    }

    private static Collection<State> updateStateListForPair(Iterable<State> states, int index, int pairCode) {
        Collection<State> result = new LinkedList<>();
        for (State updateStateForPair : states) {
            updateStateForPair(updateStateForPair, index, pairCode, result);
        }
        return simplifyStates(result);
    }

    private static void updateStateForPair(State state, int index, int pairCode, Collection<State> result) {
        State stateNoBinary = state.endBinaryShift(index);
        result.add(stateNoBinary.latchAndAppend(4, pairCode));
        if (state.getMode() != 4) {
            result.add(stateNoBinary.shiftAndAppend(4, pairCode));
        }
        if (pairCode == 3 || pairCode == 4) {
            result.add(stateNoBinary.latchAndAppend(2, 16 - pairCode).latchAndAppend(2, 1));
        }
        if (state.getBinaryShiftByteCount() > 0) {
            result.add(state.addBinaryShiftChar(index).addBinaryShiftChar(index + 1));
        }
    }

    private static Collection<State> simplifyStates(Iterable<State> states) {
        List<State> result = new LinkedList<>();
        for (State newState : states) {
            boolean add = true;
            Iterator<State> iterator = result.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    break;
                }
                State state = (State) iterator.next();
                State oldState = state;
                if (state.isBetterThanOrEqualTo(newState)) {
                    add = false;
                    break;
                } else if (newState.isBetterThanOrEqualTo(oldState)) {
                    iterator.remove();
                }
            }
            if (add) {
                result.add(newState);
            }
        }
        return result;
    }
}
