package com.google.zxing.datamatrix.encoder;

final class ASCIIEncoder implements Encoder {
    ASCIIEncoder() {
    }

    public int getEncodingMode() {
        return 0;
    }

    public void encode(EncoderContext context) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(context.getMessage(), context.pos) >= 2) {
            context.writeCodeword(encodeASCIIDigits(context.getMessage().charAt(context.pos), context.getMessage().charAt(context.pos + 1)));
            context.pos += 2;
            return;
        }
        char c = context.getCurrentChar();
        int lookAheadTest = HighLevelEncoder.lookAheadTest(context.getMessage(), context.pos, getEncodingMode());
        int newMode = lookAheadTest;
        if (lookAheadTest != getEncodingMode()) {
            if (newMode == 1) {
                context.writeCodeword(230);
                context.signalEncoderChange(1);
            } else if (newMode == 2) {
                context.writeCodeword(239);
                context.signalEncoderChange(2);
            } else if (newMode == 3) {
                context.writeCodeword(238);
                context.signalEncoderChange(3);
            } else if (newMode == 4) {
                context.writeCodeword(240);
                context.signalEncoderChange(4);
            } else if (newMode == 5) {
                context.writeCodeword(231);
                context.signalEncoderChange(5);
            } else {
                StringBuilder sb = new StringBuilder("Illegal mode: ");
                sb.append(newMode);
                throw new IllegalStateException(sb.toString());
            }
        } else if (HighLevelEncoder.isExtendedASCII(c)) {
            context.writeCodeword(235);
            context.writeCodeword((char) ((c - 128) + 1));
            context.pos++;
        } else {
            context.writeCodeword((char) (c + 1));
            context.pos++;
        }
    }

    private static char encodeASCIIDigits(char digit1, char digit2) {
        if (HighLevelEncoder.isDigit(digit1) && HighLevelEncoder.isDigit(digit2)) {
            return (char) (((digit1 - '0') * 10) + (digit2 - '0') + 130);
        }
        StringBuilder sb = new StringBuilder("not digits: ");
        sb.append(digit1);
        sb.append(digit2);
        throw new IllegalArgumentException(sb.toString());
    }
}
