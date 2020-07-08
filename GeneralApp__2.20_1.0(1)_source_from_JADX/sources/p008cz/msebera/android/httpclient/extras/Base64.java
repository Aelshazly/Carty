package p008cz.msebera.android.httpclient.extras;

import java.io.UnsupportedEncodingException;
import kotlin.UByte;

/* renamed from: cz.msebera.android.httpclient.extras.Base64 */
public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    /* renamed from: cz.msebera.android.httpclient.extras.Base64$Coder */
    static abstract class Coder {

        /* renamed from: op */
        public int f129op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    /* renamed from: cz.msebera.android.httpclient.extras.Base64$Decoder */
    static class Decoder extends Coder {
        private static final int[] DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        public Decoder(int flags, byte[] output) {
            this.output = output;
            this.alphabet = (flags & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int len) {
            return ((len * 3) / 4) + 10;
        }

        public boolean process(byte[] input, int offset, int len, boolean finish) {
            if (this.state == 6) {
                return false;
            }
            int p = offset;
            int len2 = len + offset;
            int state2 = this.state;
            int value2 = this.value;
            int op = 0;
            byte[] output = this.output;
            int[] alphabet2 = this.alphabet;
            while (p < len2) {
                if (state2 == 0) {
                    while (p + 4 <= len2) {
                        int i = (alphabet2[input[p] & UByte.MAX_VALUE] << 18) | (alphabet2[input[p + 1] & UByte.MAX_VALUE] << 12) | (alphabet2[input[p + 2] & UByte.MAX_VALUE] << 6) | alphabet2[input[p + 3] & UByte.MAX_VALUE];
                        value2 = i;
                        if (i < 0) {
                            break;
                        }
                        output[op + 2] = (byte) value2;
                        output[op + 1] = (byte) (value2 >> 8);
                        output[op] = (byte) (value2 >> 16);
                        op += 3;
                        p += 4;
                    }
                    if (p >= len2) {
                        break;
                    }
                }
                int p2 = p + 1;
                int d = alphabet2[input[p] & UByte.MAX_VALUE];
                if (state2 != 0) {
                    if (state2 != 1) {
                        if (state2 != 2) {
                            if (state2 != 3) {
                                if (state2 != 4) {
                                    if (state2 == 5 && d != -1) {
                                        this.state = 6;
                                        return false;
                                    }
                                } else if (d == -2) {
                                    state2++;
                                } else if (d != -1) {
                                    this.state = 6;
                                    return false;
                                }
                            } else if (d >= 0) {
                                value2 = (value2 << 6) | d;
                                output[op + 2] = (byte) value2;
                                output[op + 1] = (byte) (value2 >> 8);
                                output[op] = (byte) (value2 >> 16);
                                op += 3;
                                state2 = 0;
                            } else if (d == -2) {
                                output[op + 1] = (byte) (value2 >> 2);
                                output[op] = (byte) (value2 >> 10);
                                op += 2;
                                state2 = 5;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        } else if (d >= 0) {
                            value2 = (value2 << 6) | d;
                            state2++;
                        } else if (d == -2) {
                            int op2 = op + 1;
                            output[op] = (byte) (value2 >> 4);
                            state2 = 4;
                            op = op2;
                        } else if (d != -1) {
                            this.state = 6;
                            return false;
                        }
                    } else if (d >= 0) {
                        value2 = (value2 << 6) | d;
                        state2++;
                    } else if (d != -1) {
                        this.state = 6;
                        return false;
                    }
                } else if (d >= 0) {
                    value2 = d;
                    state2++;
                } else if (d != -1) {
                    this.state = 6;
                    return false;
                }
                p = p2;
            }
            if (!finish) {
                this.state = state2;
                this.value = value2;
                this.f129op = op;
                return true;
            }
            if (state2 != 0) {
                if (state2 == 1) {
                    this.state = 6;
                    return false;
                } else if (state2 == 2) {
                    int op3 = op + 1;
                    output[op] = (byte) (value2 >> 4);
                    op = op3;
                } else if (state2 == 3) {
                    int op4 = op + 1;
                    output[op] = (byte) (value2 >> 10);
                    op = op4 + 1;
                    output[op4] = (byte) (value2 >> 2);
                } else if (state2 == 4) {
                    this.state = 6;
                    return false;
                }
            }
            this.state = state2;
            this.f129op = op;
            return true;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.extras.Base64$Encoder */
    static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            Class<Base64> cls = Base64.class;
        }

        public Encoder(int flags, byte[] output) {
            this.output = output;
            boolean z = true;
            this.do_padding = (flags & 1) == 0;
            this.do_newline = (flags & 2) == 0;
            if ((flags & 4) == 0) {
                z = false;
            }
            this.do_cr = z;
            this.alphabet = (flags & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        public int maxOutputSize(int len) {
            return ((len * 8) / 5) + 10;
        }

        /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r12v15, types: [byte] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r12v6, types: [byte] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r13v6, types: [byte] */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00a0  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00f6  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x01df  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r19, int r20, int r21, boolean r22) {
            /*
                r18 = this;
                r0 = r18
                byte[] r1 = r0.alphabet
                byte[] r2 = r0.output
                r3 = 0
                int r4 = r0.count
                r5 = r20
                int r6 = r21 + r20
                r7 = -1
                int r8 = r0.tailLen
                r9 = 2
                r10 = 1
                if (r8 == 0) goto L_0x0059
                r11 = 0
                if (r8 == r10) goto L_0x0038
                if (r8 == r9) goto L_0x001a
                goto L_0x005a
            L_0x001a:
                int r8 = r5 + 1
                if (r8 > r6) goto L_0x005a
                byte[] r8 = r0.tail
                byte r12 = r8[r11]
                r12 = r12 & 255(0xff, float:3.57E-43)
                int r12 = r12 << 16
                byte r8 = r8[r10]
                r8 = r8 & 255(0xff, float:3.57E-43)
                int r8 = r8 << 8
                r8 = r8 | r12
                int r12 = r5 + 1
                byte r5 = r19[r5]
                r5 = r5 & 255(0xff, float:3.57E-43)
                r7 = r8 | r5
                r0.tailLen = r11
                goto L_0x005b
            L_0x0038:
                int r8 = r5 + 2
                if (r8 > r6) goto L_0x005a
                byte[] r8 = r0.tail
                byte r8 = r8[r11]
                r8 = r8 & 255(0xff, float:3.57E-43)
                int r8 = r8 << 16
                int r12 = r5 + 1
                byte r5 = r19[r5]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 8
                r5 = r5 | r8
                int r8 = r12 + 1
                byte r12 = r19[r12]
                r12 = r12 & 255(0xff, float:3.57E-43)
                r7 = r5 | r12
                r0.tailLen = r11
                r12 = r8
                goto L_0x005b
            L_0x0059:
            L_0x005a:
                r12 = r5
            L_0x005b:
                r5 = -1
                r8 = 13
                r11 = 10
                if (r7 == r5) goto L_0x009c
                int r5 = r3 + 1
                int r13 = r7 >> 18
                r13 = r13 & 63
                byte r13 = r1[r13]
                r2[r3] = r13
                int r3 = r5 + 1
                int r13 = r7 >> 12
                r13 = r13 & 63
                byte r13 = r1[r13]
                r2[r5] = r13
                int r5 = r3 + 1
                int r13 = r7 >> 6
                r13 = r13 & 63
                byte r13 = r1[r13]
                r2[r3] = r13
                int r3 = r5 + 1
                r13 = r7 & 63
                byte r13 = r1[r13]
                r2[r5] = r13
                int r4 = r4 + -1
                if (r4 != 0) goto L_0x009c
                boolean r5 = r0.do_cr
                if (r5 == 0) goto L_0x0095
                int r5 = r3 + 1
                r2[r3] = r8
                r3 = r5
            L_0x0095:
                int r5 = r3 + 1
                r2[r3] = r11
                r4 = 19
                r3 = r5
            L_0x009c:
                int r5 = r12 + 3
                if (r5 > r6) goto L_0x00f4
                byte r5 = r19[r12]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 16
                int r13 = r12 + 1
                byte r13 = r19[r13]
                r13 = r13 & 255(0xff, float:3.57E-43)
                int r13 = r13 << 8
                r5 = r5 | r13
                int r13 = r12 + 2
                byte r13 = r19[r13]
                r13 = r13 & 255(0xff, float:3.57E-43)
                r7 = r5 | r13
                int r5 = r7 >> 18
                r5 = r5 & 63
                byte r5 = r1[r5]
                r2[r3] = r5
                int r5 = r3 + 1
                int r13 = r7 >> 12
                r13 = r13 & 63
                byte r13 = r1[r13]
                r2[r5] = r13
                int r5 = r3 + 2
                int r13 = r7 >> 6
                r13 = r13 & 63
                byte r13 = r1[r13]
                r2[r5] = r13
                int r5 = r3 + 3
                r13 = r7 & 63
                byte r13 = r1[r13]
                r2[r5] = r13
                int r12 = r12 + 3
                int r3 = r3 + 4
                int r4 = r4 + -1
                if (r4 != 0) goto L_0x009c
                boolean r5 = r0.do_cr
                if (r5 == 0) goto L_0x00ec
                int r5 = r3 + 1
                r2[r3] = r8
                r3 = r5
            L_0x00ec:
                int r5 = r3 + 1
                r2[r3] = r11
                r4 = 19
                r3 = r5
                goto L_0x009c
            L_0x00f4:
                if (r22 == 0) goto L_0x01df
                int r5 = r0.tailLen
                int r13 = r12 - r5
                int r14 = r6 + -1
                r15 = 61
                if (r13 != r14) goto L_0x0150
                r9 = 0
                if (r5 <= 0) goto L_0x010f
                byte[] r5 = r0.tail
                int r13 = r9 + 1
                byte r5 = r5[r9]
                r17 = r12
                r12 = r5
                r5 = r17
                goto L_0x0114
            L_0x010f:
                int r5 = r12 + 1
                byte r12 = r19[r12]
                r13 = r9
            L_0x0114:
                r9 = r12 & 255(0xff, float:3.57E-43)
                int r7 = r9 << 4
                int r9 = r0.tailLen
                int r9 = r9 - r13
                r0.tailLen = r9
                int r9 = r3 + 1
                int r12 = r7 >> 6
                r12 = r12 & 63
                byte r12 = r1[r12]
                r2[r3] = r12
                int r3 = r9 + 1
                r12 = r7 & 63
                byte r12 = r1[r12]
                r2[r9] = r12
                boolean r9 = r0.do_padding
                if (r9 == 0) goto L_0x013b
                int r9 = r3 + 1
                r2[r3] = r15
                int r3 = r9 + 1
                r2[r9] = r15
            L_0x013b:
                boolean r9 = r0.do_newline
                if (r9 == 0) goto L_0x014d
                boolean r9 = r0.do_cr
                if (r9 == 0) goto L_0x0148
                int r9 = r3 + 1
                r2[r3] = r8
                r3 = r9
            L_0x0148:
                int r8 = r3 + 1
                r2[r3] = r11
                r3 = r8
            L_0x014d:
                r12 = r5
                goto L_0x01dd
            L_0x0150:
                int r13 = r12 - r5
                int r14 = r6 + -2
                if (r13 != r14) goto L_0x01c5
                r13 = 0
                if (r5 <= r10) goto L_0x0166
                byte[] r5 = r0.tail
                int r14 = r13 + 1
                byte r5 = r5[r13]
                r13 = r14
                r17 = r12
                r12 = r5
                r5 = r17
                goto L_0x016a
            L_0x0166:
                int r5 = r12 + 1
                byte r12 = r19[r12]
            L_0x016a:
                r12 = r12 & 255(0xff, float:3.57E-43)
                int r12 = r12 << r11
                int r14 = r0.tailLen
                if (r14 <= 0) goto L_0x0179
                byte[] r14 = r0.tail
                int r16 = r13 + 1
                byte r13 = r14[r13]
                r14 = r5
                goto L_0x0180
            L_0x0179:
                int r14 = r5 + 1
                byte r5 = r19[r5]
                r16 = r13
                r13 = r5
            L_0x0180:
                r5 = r13 & 255(0xff, float:3.57E-43)
                int r5 = r5 << r9
                r5 = r5 | r12
                int r7 = r0.tailLen
                int r7 = r7 - r16
                r0.tailLen = r7
                int r7 = r3 + 1
                int r9 = r5 >> 12
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r3] = r9
                int r3 = r7 + 1
                int r9 = r5 >> 6
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r7] = r9
                int r7 = r3 + 1
                r9 = r5 & 63
                byte r9 = r1[r9]
                r2[r3] = r9
                boolean r3 = r0.do_padding
                if (r3 == 0) goto L_0x01af
                int r3 = r7 + 1
                r2[r7] = r15
                goto L_0x01b0
            L_0x01af:
                r3 = r7
            L_0x01b0:
                boolean r7 = r0.do_newline
                if (r7 == 0) goto L_0x01c2
                boolean r7 = r0.do_cr
                if (r7 == 0) goto L_0x01bd
                int r7 = r3 + 1
                r2[r3] = r8
                r3 = r7
            L_0x01bd:
                int r7 = r3 + 1
                r2[r3] = r11
                r3 = r7
            L_0x01c2:
                r7 = r5
                r12 = r14
                goto L_0x01dd
            L_0x01c5:
                boolean r5 = r0.do_newline
                if (r5 == 0) goto L_0x01dd
                if (r3 <= 0) goto L_0x01dd
                r5 = 19
                if (r4 == r5) goto L_0x01dd
                boolean r5 = r0.do_cr
                if (r5 == 0) goto L_0x01d8
                int r5 = r3 + 1
                r2[r3] = r8
                r3 = r5
            L_0x01d8:
                int r5 = r3 + 1
                r2[r3] = r11
                r3 = r5
            L_0x01dd:
                goto L_0x020c
            L_0x01df:
                int r5 = r6 + -1
                if (r12 != r5) goto L_0x01f0
                byte[] r5 = r0.tail
                int r8 = r0.tailLen
                int r9 = r8 + 1
                r0.tailLen = r9
                byte r9 = r19[r12]
                r5[r8] = r9
                goto L_0x020c
            L_0x01f0:
                int r5 = r6 + -2
                if (r12 != r5) goto L_0x020c
                byte[] r5 = r0.tail
                int r8 = r0.tailLen
                int r9 = r8 + 1
                r0.tailLen = r9
                byte r9 = r19[r12]
                r5[r8] = r9
                int r8 = r0.tailLen
                int r9 = r8 + 1
                r0.tailLen = r9
                int r9 = r12 + 1
                byte r9 = r19[r9]
                r5[r8] = r9
            L_0x020c:
                r0.f129op = r3
                r0.count = r4
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.extras.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] decode(String str, int flags) {
        return decode(str.getBytes(), flags);
    }

    public static byte[] decode(byte[] input, int flags) {
        return decode(input, 0, input.length, flags);
    }

    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        Decoder decoder = new Decoder(flags, new byte[((len * 3) / 4)]);
        if (!decoder.process(input, offset, len, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.f129op == decoder.output.length) {
            return decoder.output;
        } else {
            byte[] temp = new byte[decoder.f129op];
            System.arraycopy(decoder.output, 0, temp, 0, decoder.f129op);
            return temp;
        }
    }

    public static String encodeToString(byte[] input, int flags) {
        try {
            return new String(encode(input, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] input, int offset, int len, int flags) {
        try {
            return new String(encode(input, offset, len, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] input, int flags) {
        return encode(input, 0, input.length, flags);
    }

    public static byte[] encode(byte[] input, int offset, int len, int flags) {
        Encoder encoder = new Encoder(flags, null);
        int output_len = (len / 3) * 4;
        int i = 2;
        if (!encoder.do_padding) {
            int i2 = len % 3;
            if (i2 != 0) {
                if (i2 == 1) {
                    output_len += 2;
                } else if (i2 == 2) {
                    output_len += 3;
                }
            }
        } else if (len % 3 > 0) {
            output_len += 4;
        }
        if (encoder.do_newline && len > 0) {
            int i3 = ((len - 1) / 57) + 1;
            if (!encoder.do_cr) {
                i = 1;
            }
            output_len += i3 * i;
        }
        encoder.output = new byte[output_len];
        encoder.process(input, offset, len, true);
        return encoder.output;
    }

    private Base64() {
    }
}
