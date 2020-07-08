package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser {
    private static final Object[][] FOUR_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
    private static final Object[][] TWO_DIGIT_DATA_LENGTH;
    private static final Object VARIABLE_LENGTH = new Object();

    static {
        Integer valueOf = Integer.valueOf(18);
        Object[] objArr = {"00", valueOf};
        Object[] objArr2 = {"01", Integer.valueOf(14)};
        Object[] objArr3 = {"02", Integer.valueOf(14)};
        Integer valueOf2 = Integer.valueOf(3);
        Integer valueOf3 = Integer.valueOf(20);
        Object[] objArr4 = {"10", VARIABLE_LENGTH, valueOf3};
        Integer valueOf4 = Integer.valueOf(6);
        Object[] objArr5 = {"11", valueOf4};
        Object[] objArr6 = {"12", valueOf4};
        Object[] objArr7 = {"13", valueOf4};
        Object[] objArr8 = {"15", valueOf4};
        Object[] objArr9 = {"17", valueOf4};
        Object[] objArr10 = {"20", Integer.valueOf(2)};
        Object[] objArr11 = {"21", VARIABLE_LENGTH, valueOf3};
        Object[] objArr12 = {"22", VARIABLE_LENGTH, Integer.valueOf(29)};
        Object[] objArr13 = {"30", VARIABLE_LENGTH, Integer.valueOf(8)};
        Object[] objArr14 = {"37", VARIABLE_LENGTH, Integer.valueOf(8)};
        Integer valueOf5 = Integer.valueOf(13);
        Integer valueOf6 = Integer.valueOf(30);
        Object[] objArr15 = {"90", VARIABLE_LENGTH, valueOf6};
        Object[] objArr16 = {"91", VARIABLE_LENGTH, valueOf6};
        Integer valueOf7 = Integer.valueOf(15);
        TWO_DIGIT_DATA_LENGTH = new Object[][]{objArr, objArr2, objArr3, objArr4, objArr5, objArr6, objArr7, objArr8, objArr9, objArr10, objArr11, objArr12, objArr13, objArr14, objArr15, objArr16, new Object[]{"92", VARIABLE_LENGTH, valueOf6}, new Object[]{"93", VARIABLE_LENGTH, valueOf6}, new Object[]{"94", VARIABLE_LENGTH, valueOf6}, new Object[]{"95", VARIABLE_LENGTH, valueOf6}, new Object[]{"96", VARIABLE_LENGTH, valueOf6}, new Object[]{"97", VARIABLE_LENGTH, valueOf6}, new Object[]{"98", VARIABLE_LENGTH, valueOf6}, new Object[]{"99", VARIABLE_LENGTH, valueOf6}};
        THREE_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"240", VARIABLE_LENGTH, valueOf6}, new Object[]{"241", VARIABLE_LENGTH, valueOf6}, new Object[]{"242", VARIABLE_LENGTH, valueOf4}, new Object[]{"250", VARIABLE_LENGTH, valueOf6}, new Object[]{"251", VARIABLE_LENGTH, valueOf6}, new Object[]{"253", VARIABLE_LENGTH, Integer.valueOf(17)}, new Object[]{"254", VARIABLE_LENGTH, valueOf3}, new Object[]{"400", VARIABLE_LENGTH, valueOf6}, new Object[]{"401", VARIABLE_LENGTH, valueOf6}, new Object[]{"402", Integer.valueOf(17)}, new Object[]{"403", VARIABLE_LENGTH, valueOf6}, new Object[]{"410", valueOf5}, new Object[]{"411", valueOf5}, new Object[]{"412", valueOf5}, new Object[]{"413", valueOf5}, new Object[]{"414", valueOf5}, new Object[]{"420", VARIABLE_LENGTH, valueOf3}, new Object[]{"421", VARIABLE_LENGTH, valueOf7}, new Object[]{"422", valueOf2}, new Object[]{"423", VARIABLE_LENGTH, valueOf7}, new Object[]{"424", valueOf2}, new Object[]{"425", valueOf2}, new Object[]{"426", valueOf2}};
        THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"310", valueOf4}, new Object[]{"311", valueOf4}, new Object[]{"312", valueOf4}, new Object[]{"313", valueOf4}, new Object[]{"314", valueOf4}, new Object[]{"315", valueOf4}, new Object[]{"316", valueOf4}, new Object[]{"320", valueOf4}, new Object[]{"321", valueOf4}, new Object[]{"322", valueOf4}, new Object[]{"323", valueOf4}, new Object[]{"324", valueOf4}, new Object[]{"325", valueOf4}, new Object[]{"326", valueOf4}, new Object[]{"327", valueOf4}, new Object[]{"328", valueOf4}, new Object[]{"329", valueOf4}, new Object[]{"330", valueOf4}, new Object[]{"331", valueOf4}, new Object[]{"332", valueOf4}, new Object[]{"333", valueOf4}, new Object[]{"334", valueOf4}, new Object[]{"335", valueOf4}, new Object[]{"336", valueOf4}, new Object[]{"340", valueOf4}, new Object[]{"341", valueOf4}, new Object[]{"342", valueOf4}, new Object[]{"343", valueOf4}, new Object[]{"344", valueOf4}, new Object[]{"345", valueOf4}, new Object[]{"346", valueOf4}, new Object[]{"347", valueOf4}, new Object[]{"348", valueOf4}, new Object[]{"349", valueOf4}, new Object[]{"350", valueOf4}, new Object[]{"351", valueOf4}, new Object[]{"352", valueOf4}, new Object[]{"353", valueOf4}, new Object[]{"354", valueOf4}, new Object[]{"355", valueOf4}, new Object[]{"356", valueOf4}, new Object[]{"357", valueOf4}, new Object[]{"360", valueOf4}, new Object[]{"361", valueOf4}, new Object[]{"362", valueOf4}, new Object[]{"363", valueOf4}, new Object[]{"364", valueOf4}, new Object[]{"365", valueOf4}, new Object[]{"366", valueOf4}, new Object[]{"367", valueOf4}, new Object[]{"368", valueOf4}, new Object[]{"369", valueOf4}, new Object[]{"390", VARIABLE_LENGTH, valueOf7}, new Object[]{"391", VARIABLE_LENGTH, valueOf}, new Object[]{"392", VARIABLE_LENGTH, valueOf7}, new Object[]{"393", VARIABLE_LENGTH, valueOf}, new Object[]{"703", VARIABLE_LENGTH, valueOf6}};
        FOUR_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"7001", valueOf5}, new Object[]{"7002", VARIABLE_LENGTH, valueOf6}, new Object[]{"7003", Integer.valueOf(10)}, new Object[]{"8001", Integer.valueOf(14)}, new Object[]{"8002", VARIABLE_LENGTH, valueOf3}, new Object[]{"8003", VARIABLE_LENGTH, valueOf6}, new Object[]{"8004", VARIABLE_LENGTH, valueOf6}, new Object[]{"8005", valueOf4}, new Object[]{"8006", valueOf}, new Object[]{"8007", VARIABLE_LENGTH, valueOf6}, new Object[]{"8008", VARIABLE_LENGTH, Integer.valueOf(12)}, new Object[]{"8018", valueOf}, new Object[]{"8020", VARIABLE_LENGTH, Integer.valueOf(25)}, new Object[]{"8100", valueOf4}, new Object[]{"8101", Integer.valueOf(10)}, new Object[]{"8102", Integer.valueOf(2)}, new Object[]{"8110", VARIABLE_LENGTH, Integer.valueOf(70)}, new Object[]{"8200", VARIABLE_LENGTH, Integer.valueOf(70)}};
    }

    private FieldParser() {
    }

    static String parseFieldsInGeneralPurpose(String rawInformation) throws NotFoundException {
        if (rawInformation.isEmpty()) {
            return null;
        }
        if (rawInformation.length() >= 2) {
            String firstTwoDigits = rawInformation.substring(0, 2);
            Object[][] objArr = TWO_DIGIT_DATA_LENGTH;
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object[] objArr2 = objArr[i];
                Object[] dataLength = objArr2;
                if (!objArr2[0].equals(firstTwoDigits)) {
                    i++;
                } else if (dataLength[1] == VARIABLE_LENGTH) {
                    return processVariableAI(2, ((Integer) dataLength[2]).intValue(), rawInformation);
                } else {
                    return processFixedAI(2, ((Integer) dataLength[1]).intValue(), rawInformation);
                }
            }
            if (rawInformation.length() >= 3) {
                String firstThreeDigits = rawInformation.substring(0, 3);
                Object[][] objArr3 = THREE_DIGIT_DATA_LENGTH;
                int length2 = objArr3.length;
                int i2 = 0;
                while (i2 < length2) {
                    Object[] objArr4 = objArr3[i2];
                    Object[] dataLength2 = objArr4;
                    if (!objArr4[0].equals(firstThreeDigits)) {
                        i2++;
                    } else if (dataLength2[1] == VARIABLE_LENGTH) {
                        return processVariableAI(3, ((Integer) dataLength2[2]).intValue(), rawInformation);
                    } else {
                        return processFixedAI(3, ((Integer) dataLength2[1]).intValue(), rawInformation);
                    }
                }
                Object[][] objArr5 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
                int length3 = objArr5.length;
                int i3 = 0;
                while (i3 < length3) {
                    Object[] objArr6 = objArr5[i3];
                    Object[] dataLength3 = objArr6;
                    if (!objArr6[0].equals(firstThreeDigits)) {
                        i3++;
                    } else if (dataLength3[1] == VARIABLE_LENGTH) {
                        return processVariableAI(4, ((Integer) dataLength3[2]).intValue(), rawInformation);
                    } else {
                        return processFixedAI(4, ((Integer) dataLength3[1]).intValue(), rawInformation);
                    }
                }
                if (rawInformation.length() >= 4) {
                    String firstFourDigits = rawInformation.substring(0, 4);
                    Object[][] objArr7 = FOUR_DIGIT_DATA_LENGTH;
                    int length4 = objArr7.length;
                    int i4 = 0;
                    while (i4 < length4) {
                        Object[] objArr8 = objArr7[i4];
                        Object[] dataLength4 = objArr8;
                        if (!objArr8[0].equals(firstFourDigits)) {
                            i4++;
                        } else if (dataLength4[1] == VARIABLE_LENGTH) {
                            return processVariableAI(4, ((Integer) dataLength4[2]).intValue(), rawInformation);
                        } else {
                            return processFixedAI(4, ((Integer) dataLength4[1]).intValue(), rawInformation);
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processFixedAI(int aiSize, int fieldSize, String rawInformation) throws NotFoundException {
        if (rawInformation.length() >= aiSize) {
            String ai = rawInformation.substring(0, aiSize);
            if (rawInformation.length() >= aiSize + fieldSize) {
                String field = rawInformation.substring(aiSize, aiSize + fieldSize);
                String remaining = rawInformation.substring(aiSize + fieldSize);
                StringBuilder sb = new StringBuilder("(");
                sb.append(ai);
                sb.append(')');
                sb.append(field);
                String result = sb.toString();
                String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(remaining);
                String parsedAI = parseFieldsInGeneralPurpose;
                if (parseFieldsInGeneralPurpose == null) {
                    return result;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(result);
                sb2.append(parsedAI);
                return sb2.toString();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processVariableAI(int aiSize, int variableFieldSize, String rawInformation) throws NotFoundException {
        int maxSize;
        String ai = rawInformation.substring(0, aiSize);
        if (rawInformation.length() < aiSize + variableFieldSize) {
            maxSize = rawInformation.length();
        } else {
            maxSize = aiSize + variableFieldSize;
        }
        String field = rawInformation.substring(aiSize, maxSize);
        String remaining = rawInformation.substring(maxSize);
        StringBuilder sb = new StringBuilder("(");
        sb.append(ai);
        sb.append(')');
        sb.append(field);
        String result = sb.toString();
        String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(remaining);
        String parsedAI = parseFieldsInGeneralPurpose;
        if (parseFieldsInGeneralPurpose == null) {
            return result;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(result);
        sb2.append(parsedAI);
        return sb2.toString();
    }
}
