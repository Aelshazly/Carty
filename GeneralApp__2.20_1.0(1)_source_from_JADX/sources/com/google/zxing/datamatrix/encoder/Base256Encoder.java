package com.google.zxing.datamatrix.encoder;

import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;

final class Base256Encoder implements Encoder {
    Base256Encoder() {
    }

    public int getEncodingMode() {
        return 5;
    }

    public void encode(EncoderContext context) {
        StringBuilder sb = new StringBuilder();
        StringBuilder buffer = sb;
        sb.append(0);
        while (true) {
            if (!context.hasMoreCharacters()) {
                break;
            }
            buffer.append(context.getCurrentChar());
            context.pos++;
            int lookAheadTest = HighLevelEncoder.lookAheadTest(context.getMessage(), context.pos, getEncodingMode());
            int newMode = lookAheadTest;
            if (lookAheadTest != getEncodingMode()) {
                context.signalEncoderChange(newMode);
                break;
            }
        }
        int dataCount = buffer.length() - 1;
        int currentSize = context.getCodewordCount() + dataCount + 1;
        context.updateSymbolInfo(currentSize);
        boolean mustPad = context.getSymbolInfo().getDataCapacity() - currentSize > 0;
        if (context.hasMoreCharacters() || mustPad) {
            if (dataCount <= 249) {
                buffer.setCharAt(0, (char) dataCount);
            } else if (dataCount <= 1555) {
                buffer.setCharAt(0, (char) ((dataCount / Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249));
                buffer.insert(1, (char) (dataCount % Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            } else {
                StringBuilder sb2 = new StringBuilder("Message length not in valid ranges: ");
                sb2.append(dataCount);
                throw new IllegalStateException(sb2.toString());
            }
        }
        int c = buffer.length();
        for (int i = 0; i < c; i++) {
            context.writeCodeword(randomize255State(buffer.charAt(i), context.getCodewordCount() + 1));
        }
    }

    private static char randomize255State(char ch, int codewordPosition) {
        int i = ch + ((codewordPosition * 149) % 255) + 1;
        int tempVariable = i;
        if (i <= 255) {
            return (char) tempVariable;
        }
        return (char) (tempVariable + InputDeviceCompat.SOURCE_ANY);
    }
}
