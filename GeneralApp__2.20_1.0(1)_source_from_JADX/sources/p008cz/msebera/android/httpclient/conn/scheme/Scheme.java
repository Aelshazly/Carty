package p008cz.msebera.android.httpclient.conn.scheme;

import java.util.Locale;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.LangUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.conn.scheme.Scheme */
public final class Scheme {
    private final int defaultPort;
    private final boolean layered;
    private final String name;
    private final SchemeSocketFactory socketFactory;
    private String stringRep;

    public Scheme(String name2, int port, SchemeSocketFactory factory) {
        Args.notNull(name2, "Scheme name");
        Args.check(port > 0 && port <= 65535, "Port is invalid");
        Args.notNull(factory, "Socket factory");
        this.name = name2.toLowerCase(Locale.ENGLISH);
        this.defaultPort = port;
        if (factory instanceof SchemeLayeredSocketFactory) {
            this.layered = true;
            this.socketFactory = factory;
        } else if (factory instanceof LayeredSchemeSocketFactory) {
            this.layered = true;
            this.socketFactory = new SchemeLayeredSocketFactoryAdaptor2((LayeredSchemeSocketFactory) factory);
        } else {
            this.layered = false;
            this.socketFactory = factory;
        }
    }

    @Deprecated
    public Scheme(String name2, SocketFactory factory, int port) {
        Args.notNull(name2, "Scheme name");
        Args.notNull(factory, "Socket factory");
        Args.check(port > 0 && port <= 65535, "Port is invalid");
        this.name = name2.toLowerCase(Locale.ENGLISH);
        if (factory instanceof LayeredSocketFactory) {
            this.socketFactory = new SchemeLayeredSocketFactoryAdaptor((LayeredSocketFactory) factory);
            this.layered = true;
        } else {
            this.socketFactory = new SchemeSocketFactoryAdaptor(factory);
            this.layered = false;
        }
        this.defaultPort = port;
    }

    public final int getDefaultPort() {
        return this.defaultPort;
    }

    @Deprecated
    public final SocketFactory getSocketFactory() {
        SchemeSocketFactory schemeSocketFactory = this.socketFactory;
        if (schemeSocketFactory instanceof SchemeSocketFactoryAdaptor) {
            return ((SchemeSocketFactoryAdaptor) schemeSocketFactory).getFactory();
        }
        if (this.layered) {
            return new LayeredSocketFactoryAdaptor((LayeredSchemeSocketFactory) schemeSocketFactory);
        }
        return new SocketFactoryAdaptor(schemeSocketFactory);
    }

    public final SchemeSocketFactory getSchemeSocketFactory() {
        return this.socketFactory;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isLayered() {
        return this.layered;
    }

    public final int resolvePort(int port) {
        return port <= 0 ? this.defaultPort : port;
    }

    public final String toString() {
        if (this.stringRep == null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(this.name);
            buffer.append(':');
            buffer.append(Integer.toString(this.defaultPort));
            this.stringRep = buffer.toString();
        }
        return this.stringRep;
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scheme)) {
            return false;
        }
        Scheme that = (Scheme) obj;
        if (!(this.name.equals(that.name) && this.defaultPort == that.defaultPort && this.layered == that.layered)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), (Object) this.name), this.layered);
    }
}
