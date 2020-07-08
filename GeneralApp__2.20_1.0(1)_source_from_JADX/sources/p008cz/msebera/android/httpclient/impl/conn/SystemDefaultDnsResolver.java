package p008cz.msebera.android.httpclient.impl.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import p008cz.msebera.android.httpclient.conn.DnsResolver;

/* renamed from: cz.msebera.android.httpclient.impl.conn.SystemDefaultDnsResolver */
public class SystemDefaultDnsResolver implements DnsResolver {
    public static final SystemDefaultDnsResolver INSTANCE = new SystemDefaultDnsResolver();

    public InetAddress[] resolve(String host) throws UnknownHostException {
        return InetAddress.getAllByName(host);
    }
}
