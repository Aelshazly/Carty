package p008cz.msebera.android.httpclient.conn.routing;

import java.net.InetAddress;
import p008cz.msebera.android.httpclient.HttpHost;

/* renamed from: cz.msebera.android.httpclient.conn.routing.RouteInfo */
public interface RouteInfo {

    /* renamed from: cz.msebera.android.httpclient.conn.routing.RouteInfo$LayerType */
    public enum LayerType {
        PLAIN,
        LAYERED
    }

    /* renamed from: cz.msebera.android.httpclient.conn.routing.RouteInfo$TunnelType */
    public enum TunnelType {
        PLAIN,
        TUNNELLED
    }

    int getHopCount();

    HttpHost getHopTarget(int i);

    LayerType getLayerType();

    InetAddress getLocalAddress();

    HttpHost getProxyHost();

    HttpHost getTargetHost();

    TunnelType getTunnelType();

    boolean isLayered();

    boolean isSecure();

    boolean isTunnelled();
}
