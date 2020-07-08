package com.google.zxing.client.result;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x020a, code lost:
        if (r3.equals("10") != false) goto L_0x0222;
     */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0251  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0267  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0273  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r34) {
        /*
            r33 = this;
            com.google.zxing.BarcodeFormat r0 = r34.getBarcodeFormat()
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.RSS_EXPANDED
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r0 = getMassagedText(r34)
            r1 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            java.util.HashMap r15 = new java.util.HashMap
            r15.<init>()
            r16 = 0
            r19 = r1
            r20 = r3
            r21 = r4
            r22 = r5
            r23 = r6
            r24 = r7
            r25 = r8
            r26 = r9
            r27 = r10
            r28 = r11
            r29 = r12
            r30 = r13
            r31 = r14
            r1 = r16
            r3 = r2
        L_0x003f:
            int r4 = r0.length()
            if (r1 >= r4) goto L_0x0277
            java.lang.String r4 = findAIvalue(r1, r0)
            r3 = r4
            if (r4 != 0) goto L_0x004d
            return r2
        L_0x004d:
            int r4 = r3.length()
            r5 = 2
            int r4 = r4 + r5
            int r4 = r4 + r1
            r1 = r4
            java.lang.String r4 = findValue(r4, r0)
            int r6 = r4.length()
            int r1 = r1 + r6
            r6 = -1
            int r7 = r3.hashCode()
            r8 = 1536(0x600, float:2.152E-42)
            r9 = 0
            r10 = 4
            r11 = 3
            if (r7 == r8) goto L_0x0217
            r8 = 1537(0x601, float:2.154E-42)
            if (r7 == r8) goto L_0x020d
            r8 = 1567(0x61f, float:2.196E-42)
            if (r7 == r8) goto L_0x0204
            r5 = 1568(0x620, float:2.197E-42)
            if (r7 == r5) goto L_0x01fa
            r5 = 1570(0x622, float:2.2E-42)
            if (r7 == r5) goto L_0x01f0
            r5 = 1572(0x624, float:2.203E-42)
            if (r7 == r5) goto L_0x01e6
            r5 = 1574(0x626, float:2.206E-42)
            if (r7 == r5) goto L_0x01dc
            switch(r7) {
                case 1567966: goto L_0x01d2;
                case 1567967: goto L_0x01c7;
                case 1567968: goto L_0x01bc;
                case 1567969: goto L_0x01b0;
                case 1567970: goto L_0x01a4;
                case 1567971: goto L_0x0198;
                case 1567972: goto L_0x018c;
                case 1567973: goto L_0x0180;
                case 1567974: goto L_0x0174;
                case 1567975: goto L_0x0168;
                default: goto L_0x0085;
            }
        L_0x0085:
            switch(r7) {
                case 1568927: goto L_0x015c;
                case 1568928: goto L_0x0150;
                case 1568929: goto L_0x0144;
                case 1568930: goto L_0x0138;
                case 1568931: goto L_0x012c;
                case 1568932: goto L_0x0120;
                case 1568933: goto L_0x0114;
                case 1568934: goto L_0x0108;
                case 1568935: goto L_0x00fc;
                case 1568936: goto L_0x00f0;
                default: goto L_0x0088;
            }
        L_0x0088:
            switch(r7) {
                case 1575716: goto L_0x00e4;
                case 1575717: goto L_0x00d8;
                case 1575718: goto L_0x00cc;
                case 1575719: goto L_0x00c0;
                default: goto L_0x008b;
            }
        L_0x008b:
            switch(r7) {
                case 1575747: goto L_0x00b4;
                case 1575748: goto L_0x00a8;
                case 1575749: goto L_0x009c;
                case 1575750: goto L_0x0090;
                default: goto L_0x008e;
            }
        L_0x008e:
            goto L_0x0221
        L_0x0090:
            java.lang.String r5 = "3933"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 34
            goto L_0x0222
        L_0x009c:
            java.lang.String r5 = "3932"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 33
            goto L_0x0222
        L_0x00a8:
            java.lang.String r5 = "3931"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 32
            goto L_0x0222
        L_0x00b4:
            java.lang.String r5 = "3930"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 31
            goto L_0x0222
        L_0x00c0:
            java.lang.String r5 = "3923"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 30
            goto L_0x0222
        L_0x00cc:
            java.lang.String r5 = "3922"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 29
            goto L_0x0222
        L_0x00d8:
            java.lang.String r5 = "3921"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 28
            goto L_0x0222
        L_0x00e4:
            java.lang.String r5 = "3920"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 27
            goto L_0x0222
        L_0x00f0:
            java.lang.String r5 = "3209"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 26
            goto L_0x0222
        L_0x00fc:
            java.lang.String r5 = "3208"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 25
            goto L_0x0222
        L_0x0108:
            java.lang.String r5 = "3207"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 24
            goto L_0x0222
        L_0x0114:
            java.lang.String r5 = "3206"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 23
            goto L_0x0222
        L_0x0120:
            java.lang.String r5 = "3205"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 22
            goto L_0x0222
        L_0x012c:
            java.lang.String r5 = "3204"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 21
            goto L_0x0222
        L_0x0138:
            java.lang.String r5 = "3203"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 20
            goto L_0x0222
        L_0x0144:
            java.lang.String r5 = "3202"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 19
            goto L_0x0222
        L_0x0150:
            java.lang.String r5 = "3201"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 18
            goto L_0x0222
        L_0x015c:
            java.lang.String r5 = "3200"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 17
            goto L_0x0222
        L_0x0168:
            java.lang.String r5 = "3109"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 16
            goto L_0x0222
        L_0x0174:
            java.lang.String r5 = "3108"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 15
            goto L_0x0222
        L_0x0180:
            java.lang.String r5 = "3107"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 14
            goto L_0x0222
        L_0x018c:
            java.lang.String r5 = "3106"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 13
            goto L_0x0222
        L_0x0198:
            java.lang.String r5 = "3105"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 12
            goto L_0x0222
        L_0x01a4:
            java.lang.String r5 = "3104"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 11
            goto L_0x0222
        L_0x01b0:
            java.lang.String r5 = "3103"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 10
            goto L_0x0222
        L_0x01bc:
            java.lang.String r5 = "3102"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 9
            goto L_0x0222
        L_0x01c7:
            java.lang.String r5 = "3101"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 8
            goto L_0x0222
        L_0x01d2:
            java.lang.String r5 = "3100"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 7
            goto L_0x0222
        L_0x01dc:
            java.lang.String r5 = "17"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 6
            goto L_0x0222
        L_0x01e6:
            java.lang.String r5 = "15"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 5
            goto L_0x0222
        L_0x01f0:
            java.lang.String r5 = "13"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 4
            goto L_0x0222
        L_0x01fa:
            java.lang.String r5 = "11"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 3
            goto L_0x0222
        L_0x0204:
            java.lang.String r7 = "10"
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L_0x008e
            goto L_0x0222
        L_0x020d:
            java.lang.String r5 = "01"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 1
            goto L_0x0222
        L_0x0217:
            java.lang.String r5 = "00"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x008e
            r5 = 0
            goto L_0x0222
        L_0x0221:
            r5 = -1
        L_0x0222:
            switch(r5) {
                case 0: goto L_0x0273;
                case 1: goto L_0x026f;
                case 2: goto L_0x026b;
                case 3: goto L_0x0267;
                case 4: goto L_0x0263;
                case 5: goto L_0x025f;
                case 6: goto L_0x025b;
                case 7: goto L_0x0251;
                case 8: goto L_0x0251;
                case 9: goto L_0x0251;
                case 10: goto L_0x0251;
                case 11: goto L_0x0251;
                case 12: goto L_0x0251;
                case 13: goto L_0x0251;
                case 14: goto L_0x0251;
                case 15: goto L_0x0251;
                case 16: goto L_0x0251;
                case 17: goto L_0x0247;
                case 18: goto L_0x0247;
                case 19: goto L_0x0247;
                case 20: goto L_0x0247;
                case 21: goto L_0x0247;
                case 22: goto L_0x0247;
                case 23: goto L_0x0247;
                case 24: goto L_0x0247;
                case 25: goto L_0x0247;
                case 26: goto L_0x0247;
                case 27: goto L_0x023f;
                case 28: goto L_0x023f;
                case 29: goto L_0x023f;
                case 30: goto L_0x023f;
                case 31: goto L_0x022a;
                case 32: goto L_0x022a;
                case 33: goto L_0x022a;
                case 34: goto L_0x022a;
                default: goto L_0x0225;
            }
        L_0x0225:
            r15.put(r3, r4)
            goto L_0x003f
        L_0x022a:
            int r5 = r4.length()
            if (r5 >= r10) goto L_0x0231
            return r2
        L_0x0231:
            java.lang.String r29 = r4.substring(r11)
            java.lang.String r31 = r4.substring(r9, r11)
            java.lang.String r30 = r3.substring(r11)
            goto L_0x003f
        L_0x023f:
            r29 = r4
            java.lang.String r30 = r3.substring(r11)
            goto L_0x003f
        L_0x0247:
            r26 = r4
            java.lang.String r27 = "LB"
            java.lang.String r28 = r3.substring(r11)
            goto L_0x003f
        L_0x0251:
            r26 = r4
            java.lang.String r27 = "KG"
            java.lang.String r28 = r3.substring(r11)
            goto L_0x003f
        L_0x025b:
            r25 = r4
            goto L_0x003f
        L_0x025f:
            r24 = r4
            goto L_0x003f
        L_0x0263:
            r23 = r4
            goto L_0x003f
        L_0x0267:
            r22 = r4
            goto L_0x003f
        L_0x026b:
            r21 = r4
            goto L_0x003f
        L_0x026f:
            r19 = r4
            goto L_0x003f
        L_0x0273:
            r20 = r4
            goto L_0x003f
        L_0x0277:
            com.google.zxing.client.result.ExpandedProductParsedResult r2 = new com.google.zxing.client.result.ExpandedProductParsedResult
            r3 = r2
            r4 = r0
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            r13 = r27
            r14 = r28
            r32 = r15
            r15 = r29
            r16 = r30
            r17 = r31
            r18 = r32
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int i, String rawText) {
        if (rawText.charAt(i) != '(') {
            return null;
        }
        CharSequence rawTextAux = rawText.substring(i + 1);
        StringBuilder buf = new StringBuilder();
        for (int index = 0; index < rawTextAux.length(); index++) {
            char charAt = rawTextAux.charAt(index);
            char currentChar = charAt;
            if (charAt == ')') {
                return buf.toString();
            }
            if (currentChar < '0' || currentChar > '9') {
                return null;
            }
            buf.append(currentChar);
        }
        return buf.toString();
    }

    private static String findValue(int i, String rawText) {
        StringBuilder buf = new StringBuilder();
        String rawTextAux = rawText.substring(i);
        for (int index = 0; index < rawTextAux.length(); index++) {
            char charAt = rawTextAux.charAt(index);
            char c = charAt;
            if (charAt == '(') {
                if (findAIvalue(index, rawTextAux) != null) {
                    break;
                }
                buf.append('(');
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }
}
