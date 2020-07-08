package p008cz.msebera.android.httpclient.p013io;

import java.io.IOException;
import p008cz.msebera.android.httpclient.util.CharArrayBuffer;

/* renamed from: cz.msebera.android.httpclient.io.SessionOutputBuffer */
public interface SessionOutputBuffer {
    void flush() throws IOException;

    HttpTransportMetrics getMetrics();

    void write(int i) throws IOException;

    void write(byte[] bArr) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;

    void writeLine(CharArrayBuffer charArrayBuffer) throws IOException;

    void writeLine(String str) throws IOException;
}
