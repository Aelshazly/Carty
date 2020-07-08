package p008cz.msebera.android.httpclient.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: cz.msebera.android.httpclient.conn.DnsResolver */
public interface DnsResolver {
    InetAddress[] resolve(String str) throws UnknownHostException;
}
