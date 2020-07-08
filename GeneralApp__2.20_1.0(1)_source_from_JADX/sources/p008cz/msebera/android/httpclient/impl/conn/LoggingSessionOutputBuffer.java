package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.p013io.HttpTransportMetrics;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.LoggingSessionOutputBuffer */
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final String charset;
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer out2, Wire wire2, String charset2) {
        this.out = out2;
        this.wire = wire2;
        this.charset = charset2 != null ? charset2 : Consts.ASCII.name();
    }

    public LoggingSessionOutputBuffer(SessionOutputBuffer out2, Wire wire2) {
        this(out2, wire2, null);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        if (this.wire.enabled()) {
            this.wire.output(b, off, len);
        }
    }

    public void write(int b) throws IOException {
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    public void write(byte[] b) throws IOException {
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void writeLine(CharArrayBuffer buffer) throws IOException {
        this.out.writeLine(buffer);
        if (this.wire.enabled()) {
            String s = new String(buffer.buffer(), 0, buffer.length());
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("\r\n");
            this.wire.output(sb.toString().getBytes(this.charset));
        }
    }

    public void writeLine(String s) throws IOException {
        this.out.writeLine(s);
        if (this.wire.enabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("\r\n");
            this.wire.output(sb.toString().getBytes(this.charset));
        }
    }

    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }
}
