package p008cz.msebera.android.httpclient.util;

import java.io.Serializable;
import kotlin.UByte;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.util.CharArrayBuffer */
public final class CharArrayBuffer implements Serializable {
    private static final long serialVersionUID = -6208952725094867135L;
    private char[] buffer;
    private int len;

    public CharArrayBuffer(int capacity) {
        Args.notNegative(capacity, "Buffer capacity");
        this.buffer = new char[capacity];
    }

    private void expand(int newlen) {
        char[] newbuffer = new char[Math.max(this.buffer.length << 1, newlen)];
        System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
        this.buffer = newbuffer;
    }

    public void append(char[] b, int off, int len2) {
        if (b != null) {
            if (off < 0 || off > b.length || len2 < 0 || off + len2 < 0 || off + len2 > b.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("off: ");
                sb.append(off);
                sb.append(" len: ");
                sb.append(len2);
                sb.append(" b.length: ");
                sb.append(b.length);
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (len2 != 0) {
                int newlen = this.len + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                System.arraycopy(b, off, this.buffer, this.len, len2);
                this.len = newlen;
            }
        }
    }

    public void append(String str) {
        String s = str != null ? str : "null";
        int strlen = s.length();
        int newlen = this.len + strlen;
        if (newlen > this.buffer.length) {
            expand(newlen);
        }
        s.getChars(0, strlen, this.buffer, this.len);
        this.len = newlen;
    }

    public void append(CharArrayBuffer b, int off, int len2) {
        if (b != null) {
            append(b.buffer, off, len2);
        }
    }

    public void append(CharArrayBuffer b) {
        if (b != null) {
            append(b.buffer, 0, b.len);
        }
    }

    public void append(char ch) {
        int newlen = this.len + 1;
        if (newlen > this.buffer.length) {
            expand(newlen);
        }
        this.buffer[this.len] = ch;
        this.len = newlen;
    }

    public void append(byte[] b, int off, int len2) {
        if (b != null) {
            if (off < 0 || off > b.length || len2 < 0 || off + len2 < 0 || off + len2 > b.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("off: ");
                sb.append(off);
                sb.append(" len: ");
                sb.append(len2);
                sb.append(" b.length: ");
                sb.append(b.length);
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (len2 != 0) {
                int oldlen = this.len;
                int newlen = oldlen + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                int i1 = off;
                for (int i2 = oldlen; i2 < newlen; i2++) {
                    this.buffer[i2] = (char) (b[i1] & UByte.MAX_VALUE);
                    i1++;
                }
                this.len = newlen;
            }
        }
    }

    public void append(ByteArrayBuffer b, int off, int len2) {
        if (b != null) {
            append(b.buffer(), off, len2);
        }
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void clear() {
        this.len = 0;
    }

    public char[] toCharArray() {
        int i = this.len;
        char[] b = new char[i];
        if (i > 0) {
            System.arraycopy(this.buffer, 0, b, 0, i);
        }
        return b;
    }

    public char charAt(int i) {
        return this.buffer[i];
    }

    public char[] buffer() {
        return this.buffer;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public void ensureCapacity(int required) {
        if (required > 0) {
            int length = this.buffer.length;
            int i = this.len;
            if (required > length - i) {
                expand(i + required);
            }
        }
    }

    public void setLength(int len2) {
        if (len2 < 0 || len2 > this.buffer.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("len: ");
            sb.append(len2);
            sb.append(" < 0 or > buffer len: ");
            sb.append(this.buffer.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        this.len = len2;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }

    public int indexOf(int ch, int from, int to) {
        int beginIndex = from;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        int endIndex = to;
        if (endIndex > this.len) {
            endIndex = this.len;
        }
        if (beginIndex > endIndex) {
            return -1;
        }
        for (int i = beginIndex; i < endIndex; i++) {
            if (this.buffer[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(int ch) {
        return indexOf(ch, 0, this.len);
    }

    public String substring(int beginIndex, int endIndex) {
        return new String(this.buffer, beginIndex, endIndex - beginIndex);
    }

    public String substringTrimmed(int from, int to) {
        int beginIndex = from;
        int endIndex = to;
        if (beginIndex < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Negative beginIndex: ");
            sb.append(beginIndex);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (endIndex > this.len) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("endIndex: ");
            sb2.append(endIndex);
            sb2.append(" > length: ");
            sb2.append(this.len);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else if (beginIndex <= endIndex) {
            while (beginIndex < endIndex && HTTP.isWhitespace(this.buffer[beginIndex])) {
                beginIndex++;
            }
            while (endIndex > beginIndex && HTTP.isWhitespace(this.buffer[endIndex - 1])) {
                endIndex--;
            }
            return new String(this.buffer, beginIndex, endIndex - beginIndex);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("beginIndex: ");
            sb3.append(beginIndex);
            sb3.append(" > endIndex: ");
            sb3.append(endIndex);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public String toString() {
        return new String(this.buffer, 0, this.len);
    }
}
