package p008cz.msebera.android.httpclient.impl.conn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.text.Typography;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.conn.Wire */
public class Wire {

    /* renamed from: id */
    private final String f239id;
    public HttpClientAndroidLog log;

    public Wire(HttpClientAndroidLog log2, String id) {
        this.log = log2;
        this.f239id = id;
    }

    public Wire(HttpClientAndroidLog log2) {
        this(log2, "");
    }

    private void wire(String header, InputStream instream) throws IOException {
        String str;
        StringBuilder buffer = new StringBuilder();
        while (true) {
            int read = instream.read();
            int ch = read;
            str = " ";
            if (read == -1) {
                break;
            } else if (ch == 13) {
                buffer.append("[\\r]");
            } else if (ch == 10) {
                buffer.append("[\\n]\"");
                buffer.insert(0, "\"");
                buffer.insert(0, header);
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append(this.f239id);
                sb.append(str);
                sb.append(buffer.toString());
                httpClientAndroidLog.debug(sb.toString());
                buffer.setLength(0);
            } else if (ch < 32 || ch > 127) {
                buffer.append("[0x");
                buffer.append(Integer.toHexString(ch));
                buffer.append("]");
            } else {
                buffer.append((char) ch);
            }
        }
        if (buffer.length() > 0) {
            buffer.append(Typography.quote);
            buffer.insert(0, Typography.quote);
            buffer.insert(0, header);
            HttpClientAndroidLog httpClientAndroidLog2 = this.log;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f239id);
            sb2.append(str);
            sb2.append(buffer.toString());
            httpClientAndroidLog2.debug(sb2.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void output(InputStream outstream) throws IOException {
        Args.notNull(outstream, "Output");
        wire(">> ", outstream);
    }

    public void input(InputStream instream) throws IOException {
        Args.notNull(instream, "Input");
        wire("<< ", instream);
    }

    public void output(byte[] b, int off, int len) throws IOException {
        Args.notNull(b, "Output");
        wire(">> ", new ByteArrayInputStream(b, off, len));
    }

    public void input(byte[] b, int off, int len) throws IOException {
        Args.notNull(b, "Input");
        wire("<< ", new ByteArrayInputStream(b, off, len));
    }

    public void output(byte[] b) throws IOException {
        Args.notNull(b, "Output");
        wire(">> ", new ByteArrayInputStream(b));
    }

    public void input(byte[] b) throws IOException {
        Args.notNull(b, "Input");
        wire("<< ", new ByteArrayInputStream(b));
    }

    public void output(int b) throws IOException {
        output(new byte[]{(byte) b});
    }

    public void input(int b) throws IOException {
        input(new byte[]{(byte) b});
    }

    public void output(String s) throws IOException {
        Args.notNull(s, "Output");
        output(s.getBytes());
    }

    public void input(String s) throws IOException {
        Args.notNull(s, "Input");
        input(s.getBytes());
    }
}
