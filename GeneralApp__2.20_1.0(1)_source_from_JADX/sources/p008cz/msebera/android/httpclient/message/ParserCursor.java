package p008cz.msebera.android.httpclient.message;

import kotlin.text.Typography;

/* renamed from: cz.msebera.android.httpclient.message.ParserCursor */
public class ParserCursor {
    private final int lowerBound;
    private int pos;
    private final int upperBound;

    public ParserCursor(int lowerBound2, int upperBound2) {
        if (lowerBound2 < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (lowerBound2 <= upperBound2) {
            this.lowerBound = lowerBound2;
            this.upperBound = upperBound2;
            this.pos = lowerBound2;
        } else {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public int getPos() {
        return this.pos;
    }

    public void updatePos(int pos2) {
        String str = "pos: ";
        if (pos2 < this.lowerBound) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(pos2);
            sb.append(" < lowerBound: ");
            sb.append(this.lowerBound);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (pos2 <= this.upperBound) {
            this.pos = pos2;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(pos2);
            sb2.append(" > upperBound: ");
            sb2.append(this.upperBound);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        buffer.append(Integer.toString(this.lowerBound));
        buffer.append(Typography.greater);
        buffer.append(Integer.toString(this.pos));
        buffer.append(Typography.greater);
        buffer.append(Integer.toString(this.upperBound));
        buffer.append(']');
        return buffer.toString();
    }
}
