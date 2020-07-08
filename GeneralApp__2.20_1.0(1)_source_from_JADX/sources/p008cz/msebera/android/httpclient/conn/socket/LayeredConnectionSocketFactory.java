package p008cz.msebera.android.httpclient.conn.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

/* renamed from: cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory */
public interface LayeredConnectionSocketFactory extends ConnectionSocketFactory {
    Socket createLayeredSocket(Socket socket, String str, int i, HttpContext httpContext) throws IOException, UnknownHostException;
}
