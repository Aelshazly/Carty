package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: cz.msebera.android.httpclient.impl.conn.LoggingOutputStream */
class LoggingOutputStream extends OutputStream {
    private final OutputStream out;
    private final Wire wire;

    public LoggingOutputStream(OutputStream out2, Wire wire2) {
        this.out = out2;
        this.wire = wire2;
    }

    public void write(int b) throws IOException {
        try {
            this.wire.output(b);
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[write] I/O error: ");
            sb.append(ex.getMessage());
            wire2.output(sb.toString());
            throw ex;
        }
    }

    public void write(byte[] b) throws IOException {
        try {
            this.wire.output(b);
            this.out.write(b);
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[write] I/O error: ");
            sb.append(ex.getMessage());
            wire2.output(sb.toString());
            throw ex;
        }
    }

    public void write(byte[] b, int off, int len) throws IOException {
        try {
            this.wire.output(b, off, len);
            this.out.write(b, off, len);
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[write] I/O error: ");
            sb.append(ex.getMessage());
            wire2.output(sb.toString());
            throw ex;
        }
    }

    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[flush] I/O error: ");
            sb.append(ex.getMessage());
            wire2.output(sb.toString());
            throw ex;
        }
    }

    public void close() throws IOException {
        try {
            this.out.close();
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[close] I/O error: ");
            sb.append(ex.getMessage());
            wire2.output(sb.toString());
            throw ex;
        }
    }
}
