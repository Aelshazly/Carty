package p008cz.msebera.android.httpclient.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* renamed from: cz.msebera.android.httpclient.util.NetUtils */
public final class NetUtils {
    public static void formatAddress(StringBuilder buffer, SocketAddress socketAddress) {
        Args.notNull(buffer, "Buffer");
        Args.notNull(socketAddress, "Socket address");
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress socketaddr = (InetSocketAddress) socketAddress;
            InetAddress inetaddr = socketaddr.getAddress();
            buffer.append(inetaddr != 0 ? inetaddr.getHostAddress() : inetaddr);
            buffer.append(':');
            buffer.append(socketaddr.getPort());
            return;
        }
        buffer.append(socketAddress);
    }
}
