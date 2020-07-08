package p008cz.msebera.android.httpclient.impl.p012io;

import java.io.IOException;
import java.net.Socket;
import p008cz.msebera.android.httpclient.p013io.EofSensor;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.io.SocketInputBuffer */
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private boolean eof = false;
    private final Socket socket;

    public SocketInputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        Args.notNull(socket2, "Socket");
        this.socket = socket2;
        int n = buffersize;
        if (n < 0) {
            n = socket2.getReceiveBufferSize();
        }
        if (n < 1024) {
            n = 1024;
        }
        init(socket2.getInputStream(), n, params);
    }

    /* access modifiers changed from: protected */
    public int fillBuffer() throws IOException {
        int i = super.fillBuffer();
        this.eof = i == -1;
        return i;
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        boolean result = hasBufferedData();
        if (result) {
            return result;
        }
        int oldtimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(timeout);
            fillBuffer();
            return hasBufferedData();
        } finally {
            this.socket.setSoTimeout(oldtimeout);
        }
    }

    public boolean isEof() {
        return this.eof;
    }
}
