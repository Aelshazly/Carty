package p008cz.msebera.android.httpclient.impl.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.conn.DnsResolver;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.conn.InMemoryDnsResolver */
public class InMemoryDnsResolver implements DnsResolver {
    private final Map<String, InetAddress[]> dnsMap = new ConcurrentHashMap();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(InMemoryDnsResolver.class);

    public void add(String host, InetAddress... ips) {
        Args.notNull(host, "Host name");
        Args.notNull(ips, "Array of IP addresses");
        this.dnsMap.put(host, ips);
    }

    public InetAddress[] resolve(String host) throws UnknownHostException {
        InetAddress[] resolvedAddresses = (InetAddress[]) this.dnsMap.get(host);
        if (this.log.isInfoEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Resolving ");
            sb.append(host);
            sb.append(" to ");
            sb.append(Arrays.deepToString(resolvedAddresses));
            httpClientAndroidLog.info(sb.toString());
        }
        if (resolvedAddresses != null) {
            return resolvedAddresses;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(host);
        sb2.append(" cannot be resolved");
        throw new UnknownHostException(sb2.toString());
    }
}
