package p008cz.msebera.android.httpclient.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.scheme.HostNameResolver */
public interface HostNameResolver {
    InetAddress resolve(String str) throws IOException;
}
