package p008cz.msebera.android.httpclient.config;

import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.config.SocketConfig */
public class SocketConfig implements Cloneable {
    public static final SocketConfig DEFAULT = new Builder().build();
    private final boolean soKeepAlive;
    private final int soLinger;
    private final boolean soReuseAddress;
    private final int soTimeout;
    private final boolean tcpNoDelay;

    /* renamed from: cz.msebera.android.httpclient.config.SocketConfig$Builder */
    public static class Builder {
        private boolean soKeepAlive;
        private int soLinger = -1;
        private boolean soReuseAddress;
        private int soTimeout;
        private boolean tcpNoDelay = true;

        Builder() {
        }

        public Builder setSoTimeout(int soTimeout2) {
            this.soTimeout = soTimeout2;
            return this;
        }

        public Builder setSoReuseAddress(boolean soReuseAddress2) {
            this.soReuseAddress = soReuseAddress2;
            return this;
        }

        public Builder setSoLinger(int soLinger2) {
            this.soLinger = soLinger2;
            return this;
        }

        public Builder setSoKeepAlive(boolean soKeepAlive2) {
            this.soKeepAlive = soKeepAlive2;
            return this;
        }

        public Builder setTcpNoDelay(boolean tcpNoDelay2) {
            this.tcpNoDelay = tcpNoDelay2;
            return this;
        }

        public SocketConfig build() {
            SocketConfig socketConfig = new SocketConfig(this.soTimeout, this.soReuseAddress, this.soLinger, this.soKeepAlive, this.tcpNoDelay);
            return socketConfig;
        }
    }

    SocketConfig(int soTimeout2, boolean soReuseAddress2, int soLinger2, boolean soKeepAlive2, boolean tcpNoDelay2) {
        this.soTimeout = soTimeout2;
        this.soReuseAddress = soReuseAddress2;
        this.soLinger = soLinger2;
        this.soKeepAlive = soKeepAlive2;
        this.tcpNoDelay = tcpNoDelay2;
    }

    public int getSoTimeout() {
        return this.soTimeout;
    }

    public boolean isSoReuseAddress() {
        return this.soReuseAddress;
    }

    public int getSoLinger() {
        return this.soLinger;
    }

    public boolean isSoKeepAlive() {
        return this.soKeepAlive;
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    /* access modifiers changed from: protected */
    public SocketConfig clone() throws CloneNotSupportedException {
        return (SocketConfig) super.clone();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[soTimeout=");
        builder.append(this.soTimeout);
        builder.append(", soReuseAddress=");
        builder.append(this.soReuseAddress);
        builder.append(", soLinger=");
        builder.append(this.soLinger);
        builder.append(", soKeepAlive=");
        builder.append(this.soKeepAlive);
        builder.append(", tcpNoDelay=");
        builder.append(this.tcpNoDelay);
        builder.append("]");
        return builder.toString();
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(SocketConfig config) {
        Args.notNull(config, "Socket config");
        return new Builder().setSoTimeout(config.getSoTimeout()).setSoReuseAddress(config.isSoReuseAddress()).setSoLinger(config.getSoLinger()).setSoKeepAlive(config.isSoKeepAlive()).setTcpNoDelay(config.isTcpNoDelay());
    }
}
