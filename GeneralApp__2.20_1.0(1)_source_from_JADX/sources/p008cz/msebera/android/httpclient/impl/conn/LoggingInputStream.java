package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: cz.msebera.android.httpclient.impl.conn.LoggingInputStream */
class LoggingInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f237in;
    private final Wire wire;

    public LoggingInputStream(InputStream in, Wire wire2) {
        this.f237in = in;
        this.wire = wire2;
    }

    public int read() throws IOException {
        try {
            int b = this.f237in.read();
            if (b == -1) {
                this.wire.input("end of stream");
            } else {
                this.wire.input(b);
            }
            return b;
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[read] I/O error: ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }

    public int read(byte[] b) throws IOException {
        try {
            int bytesRead = this.f237in.read(b);
            if (bytesRead == -1) {
                this.wire.input("end of stream");
            } else if (bytesRead > 0) {
                this.wire.input(b, 0, bytesRead);
            }
            return bytesRead;
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[read] I/O error: ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        try {
            int bytesRead = this.f237in.read(b, off, len);
            if (bytesRead == -1) {
                this.wire.input("end of stream");
            } else if (bytesRead > 0) {
                this.wire.input(b, off, bytesRead);
            }
            return bytesRead;
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[read] I/O error: ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }

    public long skip(long n) throws IOException {
        try {
            return super.skip(n);
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[skip] I/O error: ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }

    public int available() throws IOException {
        try {
            return this.f237in.available();
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[available] I/O error : ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }

    public void mark(int readlimit) {
        super.mark(readlimit);
    }

    public void reset() throws IOException {
        super.reset();
    }

    public boolean markSupported() {
        return false;
    }

    public void close() throws IOException {
        try {
            this.f237in.close();
        } catch (IOException ex) {
            Wire wire2 = this.wire;
            StringBuilder sb = new StringBuilder();
            sb.append("[close] I/O error: ");
            sb.append(ex.getMessage());
            wire2.input(sb.toString());
            throw ex;
        }
    }
}
