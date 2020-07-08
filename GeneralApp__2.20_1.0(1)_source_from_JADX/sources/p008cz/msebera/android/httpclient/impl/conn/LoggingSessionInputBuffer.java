package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.p013io.EofSensor;
import p008cz.msebera.android.httpclient.p013io.HttpTransportMetrics;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.LoggingSessionInputBuffer */
public class LoggingSessionInputBuffer implements SessionInputBuffer, EofSensor {
    private final String charset;
    private final EofSensor eofSensor;

    /* renamed from: in */
    private final SessionInputBuffer f238in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire2, String charset2) {
        this.f238in = in;
        this.eofSensor = in instanceof EofSensor ? (EofSensor) in : null;
        this.wire = wire2;
        this.charset = charset2 != null ? charset2 : Consts.ASCII.name();
    }

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire2) {
        this(in, wire2, null);
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        return this.f238in.isDataAvailable(timeout);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int l = this.f238in.read(b, off, len);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, off, l);
        }
        return l;
    }

    public int read() throws IOException {
        int l = this.f238in.read();
        if (this.wire.enabled() && l != -1) {
            this.wire.input(l);
        }
        return l;
    }

    public int read(byte[] b) throws IOException {
        int l = this.f238in.read(b);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, 0, l);
        }
        return l;
    }

    public String readLine() throws IOException {
        String s = this.f238in.readLine();
        if (this.wire.enabled() && s != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("\r\n");
            this.wire.input(sb.toString().getBytes(this.charset));
        }
        return s;
    }

    public int readLine(CharArrayBuffer buffer) throws IOException {
        int l = this.f238in.readLine(buffer);
        if (this.wire.enabled() && l >= 0) {
            String s = new String(buffer.buffer(), buffer.length() - l, l);
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("\r\n");
            this.wire.input(sb.toString().getBytes(this.charset));
        }
        return l;
    }

    public HttpTransportMetrics getMetrics() {
        return this.f238in.getMetrics();
    }

    public boolean isEof() {
        EofSensor eofSensor2 = this.eofSensor;
        if (eofSensor2 != null) {
            return eofSensor2.isEof();
        }
        return false;
    }
}
