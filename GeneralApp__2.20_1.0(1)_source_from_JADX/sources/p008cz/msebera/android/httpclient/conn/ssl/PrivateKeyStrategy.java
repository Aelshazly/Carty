package p008cz.msebera.android.httpclient.conn.ssl;

import java.net.Socket;
import java.util.Map;

/* renamed from: cz.msebera.android.httpclient.conn.ssl.PrivateKeyStrategy */
public interface PrivateKeyStrategy {
    String chooseAlias(Map<String, PrivateKeyDetails> map, Socket socket);
}
