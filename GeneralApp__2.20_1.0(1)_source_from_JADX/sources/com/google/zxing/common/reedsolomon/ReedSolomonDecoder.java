package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF field2) {
        this.field = field2;
    }

    public void decode(int[] received, int twoS) throws ReedSolomonException {
        ReedSolomonDecoder reedSolomonDecoder = this;
        int[] iArr = received;
        int i = twoS;
        GenericGFPoly poly = new GenericGFPoly(reedSolomonDecoder.field, iArr);
        int[] syndromeCoefficients = new int[i];
        boolean noError = true;
        for (int i2 = 0; i2 < i; i2++) {
            GenericGF genericGF = reedSolomonDecoder.field;
            int eval = poly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + i2));
            syndromeCoefficients[(i - 1) - i2] = eval;
            if (eval != 0) {
                noError = false;
            }
        }
        if (!noError) {
            int i3 = 1;
            GenericGFPoly[] runEuclideanAlgorithm = reedSolomonDecoder.runEuclideanAlgorithm(reedSolomonDecoder.field.buildMonomial(i, 1), new GenericGFPoly(reedSolomonDecoder.field, syndromeCoefficients), i);
            GenericGFPoly[] sigmaOmega = runEuclideanAlgorithm;
            int position = 0;
            GenericGFPoly sigma = runEuclideanAlgorithm[0];
            GenericGFPoly omega = sigmaOmega[1];
            int[] errorLocations = reedSolomonDecoder.findErrorLocations(sigma);
            int[] errorMagnitudes = reedSolomonDecoder.findErrorMagnitudes(omega, errorLocations);
            int i4 = 0;
            while (i4 < errorLocations.length) {
                int length = (iArr.length - i3) - reedSolomonDecoder.field.log(errorLocations[i4]);
                int i5 = position;
                position = length;
                if (length >= 0) {
                    iArr[position] = GenericGF.addOrSubtract(iArr[position], errorMagnitudes[i4]);
                    i4++;
                    i3 = 1;
                    reedSolomonDecoder = this;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly a, GenericGFPoly b, int R) throws ReedSolomonException {
        if (a.getDegree() < b.getDegree()) {
            GenericGFPoly temp = a;
            a = b;
            b = temp;
        }
        GenericGFPoly rLast = a;
        GenericGFPoly r = b;
        GenericGFPoly tLast = this.field.getZero();
        GenericGFPoly t = this.field.getOne();
        while (r.getDegree() >= R / 2) {
            GenericGFPoly rLastLast = rLast;
            GenericGFPoly tLastLast = tLast;
            rLast = r;
            tLast = t;
            if (!rLast.isZero()) {
                r = rLastLast;
                GenericGFPoly q = this.field.getZero();
                int dltInverse = this.field.inverse(rLast.getCoefficient(rLast.getDegree()));
                while (r.getDegree() >= rLast.getDegree() && !r.isZero()) {
                    int degreeDiff = r.getDegree() - rLast.getDegree();
                    int scale = this.field.multiply(r.getCoefficient(r.getDegree()), dltInverse);
                    q = q.addOrSubtract(this.field.buildMonomial(degreeDiff, scale));
                    r = r.addOrSubtract(rLast.multiplyByMonomial(degreeDiff, scale));
                }
                t = q.multiply(tLast).addOrSubtract(tLastLast);
                if (r.getDegree() >= rLast.getDegree()) {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
        int coefficient = t.getCoefficient(0);
        int sigmaTildeAtZero = coefficient;
        if (coefficient != 0) {
            int inverse = this.field.inverse(sigmaTildeAtZero);
            return new GenericGFPoly[]{t.multiply(inverse), r.multiply(inverse)};
        }
        throw new ReedSolomonException("sigmaTilde(0) was zero");
    }

    private int[] findErrorLocations(GenericGFPoly errorLocator) throws ReedSolomonException {
        int degree = errorLocator.getDegree();
        int numErrors = degree;
        if (degree == 1) {
            return new int[]{errorLocator.getCoefficient(1)};
        }
        int[] result = new int[numErrors];
        int e = 0;
        for (int i = 1; i < this.field.getSize() && e < numErrors; i++) {
            if (errorLocator.evaluateAt(i) == 0) {
                result[e] = this.field.inverse(i);
                e++;
            }
        }
        if (e == numErrors) {
            return result;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly errorEvaluator, int[] errorLocations) {
        int length = errorLocations.length;
        int s = length;
        int[] result = new int[length];
        for (int i = 0; i < s; i++) {
            int xiInverse = this.field.inverse(errorLocations[i]);
            int denominator = 1;
            for (int j = 0; j < s; j++) {
                if (i != j) {
                    int multiply = this.field.multiply(errorLocations[j], xiInverse);
                    int term = multiply;
                    denominator = this.field.multiply(denominator, (multiply & 1) == 0 ? term | 1 : term & -2);
                }
            }
            result[i] = this.field.multiply(errorEvaluator.evaluateAt(xiInverse), this.field.inverse(denominator));
            if (this.field.getGeneratorBase() != 0) {
                result[i] = this.field.multiply(result[i], xiInverse);
            }
        }
        return result;
    }
}
