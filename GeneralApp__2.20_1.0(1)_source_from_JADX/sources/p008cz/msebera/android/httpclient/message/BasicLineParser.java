package p008cz.msebera.android.httpclient.message;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.RequestLine;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.message.BasicLineParser */
public class BasicLineParser implements LineParser {
    @Deprecated
    public static final BasicLineParser DEFAULT = new BasicLineParser();
    public static final BasicLineParser INSTANCE = new BasicLineParser();
    protected final ProtocolVersion protocol;

    public BasicLineParser(ProtocolVersion proto) {
        this.protocol = proto != null ? proto : HttpVersion.HTTP_1_1;
    }

    public BasicLineParser() {
        this(null);
    }

    public static ProtocolVersion parseProtocolVersion(String value, LineParser parser) throws ParseException {
        Args.notNull(value, "Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        return (parser != null ? parser : INSTANCE).parseProtocolVersion(buffer, new ParserCursor(0, value.length()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.ProtocolVersion parseProtocolVersion(p008cz.msebera.android.httpclient.util.CharArrayBuffer r14, p008cz.msebera.android.httpclient.message.ParserCursor r15) throws p008cz.msebera.android.httpclient.ParseException {
        /*
            r13 = this;
            java.lang.String r0 = "Char array buffer"
            p008cz.msebera.android.httpclient.util.Args.notNull(r14, r0)
            java.lang.String r0 = "Parser cursor"
            p008cz.msebera.android.httpclient.util.Args.notNull(r15, r0)
            cz.msebera.android.httpclient.ProtocolVersion r0 = r13.protocol
            java.lang.String r0 = r0.getProtocol()
            int r1 = r0.length()
            int r2 = r15.getPos()
            int r3 = r15.getUpperBound()
            r13.skipWhitespace(r14, r15)
            int r4 = r15.getPos()
            int r5 = r4 + r1
            int r5 = r5 + 4
            java.lang.String r6 = "Not a valid protocol version: "
            if (r5 > r3) goto L_0x00f1
            r5 = 1
            r7 = 0
        L_0x002d:
            r8 = 0
            r9 = 1
            if (r5 == 0) goto L_0x0044
            if (r7 >= r1) goto L_0x0044
            int r10 = r4 + r7
            char r10 = r14.charAt(r10)
            char r11 = r0.charAt(r7)
            if (r10 != r11) goto L_0x0040
            r8 = 1
        L_0x0040:
            r5 = r8
            int r7 = r7 + 1
            goto L_0x002d
        L_0x0044:
            if (r5 == 0) goto L_0x0052
            int r7 = r4 + r1
            char r7 = r14.charAt(r7)
            r10 = 47
            if (r7 != r10) goto L_0x0051
            r8 = 1
        L_0x0051:
            r5 = r8
        L_0x0052:
            if (r5 == 0) goto L_0x00d8
            int r6 = r1 + 1
            int r4 = r4 + r6
            r6 = 46
            int r6 = r14.indexOf(r6, r4, r3)
            r7 = -1
            if (r6 == r7) goto L_0x00bd
            java.lang.String r8 = r14.substringTrimmed(r4, r6)     // Catch:{ NumberFormatException -> 0x00a1 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x00a1 }
            int r4 = r6 + 1
            r9 = 32
            int r9 = r14.indexOf(r9, r4, r3)
            if (r9 != r7) goto L_0x0074
            r9 = r3
        L_0x0074:
            java.lang.String r7 = r14.substringTrimmed(r4, r9)     // Catch:{ NumberFormatException -> 0x0085 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x0085 }
            r15.updatePos(r9)
            cz.msebera.android.httpclient.ProtocolVersion r10 = r13.createProtocolVersion(r8, r7)
            return r10
        L_0x0085:
            r7 = move-exception
            cz.msebera.android.httpclient.ParseException r10 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Invalid protocol minor version number: "
            r11.append(r12)
            java.lang.String r12 = r14.substring(r2, r3)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x00a1:
            r7 = move-exception
            cz.msebera.android.httpclient.ParseException r8 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Invalid protocol major version number: "
            r9.append(r10)
            java.lang.String r10 = r14.substring(r2, r3)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x00bd:
            cz.msebera.android.httpclient.ParseException r7 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Invalid protocol version number: "
            r8.append(r9)
            java.lang.String r9 = r14.substring(r2, r3)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00d8:
            cz.msebera.android.httpclient.ParseException r7 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            java.lang.String r6 = r14.substring(r2, r3)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L_0x00f1:
            cz.msebera.android.httpclient.ParseException r5 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            java.lang.String r6 = r14.substring(r2, r3)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.message.BasicLineParser.parseProtocolVersion(cz.msebera.android.httpclient.util.CharArrayBuffer, cz.msebera.android.httpclient.message.ParserCursor):cz.msebera.android.httpclient.ProtocolVersion");
    }

    /* access modifiers changed from: protected */
    public ProtocolVersion createProtocolVersion(int major, int minor) {
        return this.protocol.forVersion(major, minor);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasProtocolVersion(p008cz.msebera.android.httpclient.util.CharArrayBuffer r10, p008cz.msebera.android.httpclient.message.ParserCursor r11) {
        /*
            r9 = this;
            java.lang.String r0 = "Char array buffer"
            p008cz.msebera.android.httpclient.util.Args.notNull(r10, r0)
            java.lang.String r0 = "Parser cursor"
            p008cz.msebera.android.httpclient.util.Args.notNull(r11, r0)
            int r0 = r11.getPos()
            cz.msebera.android.httpclient.ProtocolVersion r1 = r9.protocol
            java.lang.String r1 = r1.getProtocol()
            int r2 = r1.length()
            int r3 = r10.length()
            int r4 = r2 + 4
            r5 = 0
            if (r3 >= r4) goto L_0x0022
            return r5
        L_0x0022:
            if (r0 >= 0) goto L_0x002d
            int r3 = r10.length()
            int r3 = r3 + -4
            int r0 = r3 - r2
            goto L_0x0042
        L_0x002d:
            if (r0 != 0) goto L_0x0042
        L_0x002f:
            int r3 = r10.length()
            if (r0 >= r3) goto L_0x0042
            char r3 = r10.charAt(r0)
            boolean r3 = p008cz.msebera.android.httpclient.protocol.HTTP.isWhitespace(r3)
            if (r3 == 0) goto L_0x0042
            int r0 = r0 + 1
            goto L_0x002f
        L_0x0042:
            int r3 = r0 + r2
            int r3 = r3 + 4
            int r4 = r10.length()
            if (r3 <= r4) goto L_0x004d
            return r5
        L_0x004d:
            r3 = 1
            r4 = 0
        L_0x004f:
            r6 = 1
            if (r3 == 0) goto L_0x0066
            if (r4 >= r2) goto L_0x0066
            int r7 = r0 + r4
            char r7 = r10.charAt(r7)
            char r8 = r1.charAt(r4)
            if (r7 != r8) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r6 = 0
        L_0x0062:
            r3 = r6
            int r4 = r4 + 1
            goto L_0x004f
        L_0x0066:
            if (r3 == 0) goto L_0x0074
            int r4 = r0 + r2
            char r4 = r10.charAt(r4)
            r7 = 47
            if (r4 != r7) goto L_0x0073
            r5 = 1
        L_0x0073:
            r3 = r5
        L_0x0074:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.message.BasicLineParser.hasProtocolVersion(cz.msebera.android.httpclient.util.CharArrayBuffer, cz.msebera.android.httpclient.message.ParserCursor):boolean");
    }

    public static RequestLine parseRequestLine(String value, LineParser parser) throws ParseException {
        Args.notNull(value, "Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        return (parser != null ? parser : INSTANCE).parseRequestLine(buffer, new ParserCursor(0, value.length()));
    }

    public RequestLine parseRequestLine(CharArrayBuffer buffer, ParserCursor cursor) throws ParseException {
        String str = "Invalid request line: ";
        Args.notNull(buffer, "Char array buffer");
        Args.notNull(cursor, "Parser cursor");
        int indexFrom = cursor.getPos();
        int indexTo = cursor.getUpperBound();
        try {
            skipWhitespace(buffer, cursor);
            int i = cursor.getPos();
            int blank = buffer.indexOf(32, i, indexTo);
            if (blank >= 0) {
                String method = buffer.substringTrimmed(i, blank);
                cursor.updatePos(blank);
                skipWhitespace(buffer, cursor);
                int i2 = cursor.getPos();
                int blank2 = buffer.indexOf(32, i2, indexTo);
                if (blank2 >= 0) {
                    String uri = buffer.substringTrimmed(i2, blank2);
                    cursor.updatePos(blank2);
                    ProtocolVersion ver = parseProtocolVersion(buffer, cursor);
                    skipWhitespace(buffer, cursor);
                    if (cursor.atEnd()) {
                        return createRequestLine(method, uri, ver);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(buffer.substring(indexFrom, indexTo));
                    throw new ParseException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(buffer.substring(indexFrom, indexTo));
                throw new ParseException(sb2.toString());
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(buffer.substring(indexFrom, indexTo));
            throw new ParseException(sb3.toString());
        } catch (IndexOutOfBoundsException e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(buffer.substring(indexFrom, indexTo));
            throw new ParseException(sb4.toString());
        }
    }

    /* access modifiers changed from: protected */
    public RequestLine createRequestLine(String method, String uri, ProtocolVersion ver) {
        return new BasicRequestLine(method, uri, ver);
    }

    public static StatusLine parseStatusLine(String value, LineParser parser) throws ParseException {
        Args.notNull(value, "Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        return (parser != null ? parser : INSTANCE).parseStatusLine(buffer, new ParserCursor(0, value.length()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r6 = java.lang.Integer.parseInt(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005d, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        if (r3 >= r1) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r7 = r11.substringTrimmed(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r7 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        return createStatusLine(r2, r6, r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p008cz.msebera.android.httpclient.StatusLine parseStatusLine(p008cz.msebera.android.httpclient.util.CharArrayBuffer r11, p008cz.msebera.android.httpclient.message.ParserCursor r12) throws p008cz.msebera.android.httpclient.ParseException {
        /*
            r10 = this;
            java.lang.String r0 = "Char array buffer"
            p008cz.msebera.android.httpclient.util.Args.notNull(r11, r0)
            java.lang.String r0 = "Parser cursor"
            p008cz.msebera.android.httpclient.util.Args.notNull(r12, r0)
            int r0 = r12.getPos()
            int r1 = r12.getUpperBound()
            cz.msebera.android.httpclient.ProtocolVersion r2 = r10.parseProtocolVersion(r11, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r10.skipWhitespace(r11, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            int r3 = r12.getPos()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r4 = 32
            int r4 = r11.indexOf(r4, r3, r1)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            if (r4 >= 0) goto L_0x0026
            r4 = r1
        L_0x0026:
            java.lang.String r5 = r11.substringTrimmed(r3, r4)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r6 = 0
        L_0x002b:
            int r7 = r5.length()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.String r8 = "Status line contains invalid status code: "
            if (r6 >= r7) goto L_0x0059
            char r7 = r5.charAt(r6)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            boolean r7 = java.lang.Character.isDigit(r7)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            if (r7 == 0) goto L_0x0040
            int r6 = r6 + 1
            goto L_0x002b
        L_0x0040:
            cz.msebera.android.httpclient.ParseException r7 = new cz.msebera.android.httpclient.ParseException     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.String r8 = r11.substring(r0, r1)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.String r8 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r7.<init>(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            throw r7     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
        L_0x0059:
            int r6 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x006d }
            r3 = r4
            if (r3 >= r1) goto L_0x0066
            java.lang.String r7 = r11.substringTrimmed(r3, r1)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            goto L_0x0068
        L_0x0066:
            java.lang.String r7 = ""
        L_0x0068:
            cz.msebera.android.httpclient.StatusLine r8 = r10.createStatusLine(r2, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            return r8
        L_0x006d:
            r6 = move-exception
            cz.msebera.android.httpclient.ParseException r7 = new cz.msebera.android.httpclient.ParseException     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.String r8 = r11.substring(r0, r1)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r9.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            java.lang.String r8 = r9.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            r7.<init>(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
            throw r7     // Catch:{ IndexOutOfBoundsException -> 0x0087 }
        L_0x0087:
            r2 = move-exception
            cz.msebera.android.httpclient.ParseException r3 = new cz.msebera.android.httpclient.ParseException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Invalid status line: "
            r4.append(r5)
            java.lang.String r5 = r11.substring(r0, r1)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p008cz.msebera.android.httpclient.message.BasicLineParser.parseStatusLine(cz.msebera.android.httpclient.util.CharArrayBuffer, cz.msebera.android.httpclient.message.ParserCursor):cz.msebera.android.httpclient.StatusLine");
    }

    /* access modifiers changed from: protected */
    public StatusLine createStatusLine(ProtocolVersion ver, int status, String reason) {
        return new BasicStatusLine(ver, status, reason);
    }

    public static Header parseHeader(String value, LineParser parser) throws ParseException {
        Args.notNull(value, "Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        return (parser != null ? parser : INSTANCE).parseHeader(buffer);
    }

    public Header parseHeader(CharArrayBuffer buffer) throws ParseException {
        return new BufferedHeader(buffer);
    }

    /* access modifiers changed from: protected */
    public void skipWhitespace(CharArrayBuffer buffer, ParserCursor cursor) {
        int pos = cursor.getPos();
        int indexTo = cursor.getUpperBound();
        while (pos < indexTo && HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        cursor.updatePos(pos);
    }
}
