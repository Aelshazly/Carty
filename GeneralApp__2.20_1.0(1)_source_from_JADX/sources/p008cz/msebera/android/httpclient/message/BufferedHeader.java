package p008cz.msebera.android.httpclient.message;

import java.io.Serializable;
import p008cz.msebera.android.httpclient.FormattedHeader;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.message.BufferedHeader */
public class BufferedHeader implements FormattedHeader, Cloneable, Serializable {
    private static final long serialVersionUID = -2768352615787625448L;
    private final CharArrayBuffer buffer;
    private final String name;
    private final int valuePos;

    public BufferedHeader(CharArrayBuffer buffer2) throws ParseException {
        Args.notNull(buffer2, "Char array buffer");
        int colon = buffer2.indexOf(58);
        String str = "Invalid header: ";
        if (colon != -1) {
            String s = buffer2.substringTrimmed(0, colon);
            if (s.length() != 0) {
                this.buffer = buffer2;
                this.name = s;
                this.valuePos = colon + 1;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(buffer2.toString());
            throw new ParseException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(buffer2.toString());
        throw new ParseException(sb2.toString());
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        CharArrayBuffer charArrayBuffer = this.buffer;
        return charArrayBuffer.substringTrimmed(this.valuePos, charArrayBuffer.length());
    }

    public HeaderElement[] getElements() throws ParseException {
        ParserCursor cursor = new ParserCursor(0, this.buffer.length());
        cursor.updatePos(this.valuePos);
        return BasicHeaderValueParser.INSTANCE.parseElements(this.buffer, cursor);
    }

    public int getValuePos() {
        return this.valuePos;
    }

    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    public String toString() {
        return this.buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
