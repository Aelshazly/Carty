package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {
    private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height) {
        return encode(contents, format, width, height, null);
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        Charset charset = DEFAULT_CHARSET;
        int eccPercent = 33;
        int layers = 0;
        if (hints != null) {
            if (hints.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset = Charset.forName(hints.get(EncodeHintType.CHARACTER_SET).toString());
            }
            if (hints.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                eccPercent = Integer.parseInt(hints.get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (hints.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                layers = Integer.parseInt(hints.get(EncodeHintType.AZTEC_LAYERS).toString());
            }
        }
        return encode(contents, format, width, height, charset, eccPercent, layers);
    }

    private static BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Charset charset, int eccPercent, int layers) {
        if (format == BarcodeFormat.AZTEC) {
            return renderResult(Encoder.encode(contents.getBytes(charset), eccPercent, layers), width, height);
        }
        StringBuilder sb = new StringBuilder("Can only encode AZTEC, but got ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    private static BitMatrix renderResult(AztecCode code, int width, int height) {
        BitMatrix matrix = code.getMatrix();
        BitMatrix input = matrix;
        if (matrix != null) {
            int inputWidth = input.getWidth();
            int inputHeight = input.getHeight();
            int outputWidth = Math.max(width, inputWidth);
            int outputHeight = Math.max(height, inputHeight);
            int multiple = Math.min(outputWidth / inputWidth, outputHeight / inputHeight);
            int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
            int topPadding = (outputHeight - (inputHeight * multiple)) / 2;
            BitMatrix output = new BitMatrix(outputWidth, outputHeight);
            int inputY = 0;
            int outputY = topPadding;
            while (inputY < inputHeight) {
                int inputX = 0;
                int outputX = leftPadding;
                while (inputX < inputWidth) {
                    if (input.get(inputX, inputY)) {
                        output.setRegion(outputX, outputY, multiple, multiple);
                    }
                    inputX++;
                    outputX += multiple;
                }
                inputY++;
                outputY += multiple;
            }
            return output;
        }
        int i = width;
        int i2 = height;
        throw new IllegalStateException();
    }
}
