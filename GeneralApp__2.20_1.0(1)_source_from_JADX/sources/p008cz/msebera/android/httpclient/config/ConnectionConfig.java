package p008cz.msebera.android.httpclient.config;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.config.ConnectionConfig */
public class ConnectionConfig implements Cloneable {
    public static final ConnectionConfig DEFAULT = new Builder().build();
    private final int bufferSize;
    private final Charset charset;
    private final int fragmentSizeHint;
    private final CodingErrorAction malformedInputAction;
    private final MessageConstraints messageConstraints;
    private final CodingErrorAction unmappableInputAction;

    /* renamed from: cz.msebera.android.httpclient.config.ConnectionConfig$Builder */
    public static class Builder {
        private int bufferSize;
        private Charset charset;
        private int fragmentSizeHint = -1;
        private CodingErrorAction malformedInputAction;
        private MessageConstraints messageConstraints;
        private CodingErrorAction unmappableInputAction;

        Builder() {
        }

        public Builder setBufferSize(int bufferSize2) {
            this.bufferSize = bufferSize2;
            return this;
        }

        public Builder setFragmentSizeHint(int fragmentSizeHint2) {
            this.fragmentSizeHint = fragmentSizeHint2;
            return this;
        }

        public Builder setCharset(Charset charset2) {
            this.charset = charset2;
            return this;
        }

        public Builder setMalformedInputAction(CodingErrorAction malformedInputAction2) {
            this.malformedInputAction = malformedInputAction2;
            if (malformedInputAction2 != null && this.charset == null) {
                this.charset = Consts.ASCII;
            }
            return this;
        }

        public Builder setUnmappableInputAction(CodingErrorAction unmappableInputAction2) {
            this.unmappableInputAction = unmappableInputAction2;
            if (unmappableInputAction2 != null && this.charset == null) {
                this.charset = Consts.ASCII;
            }
            return this;
        }

        public Builder setMessageConstraints(MessageConstraints messageConstraints2) {
            this.messageConstraints = messageConstraints2;
            return this;
        }

        public ConnectionConfig build() {
            Charset cs = this.charset;
            if (cs == null && !(this.malformedInputAction == null && this.unmappableInputAction == null)) {
                cs = Consts.ASCII;
            }
            int i = this.bufferSize;
            int bufSize = i > 0 ? i : 8192;
            int i2 = this.fragmentSizeHint;
            ConnectionConfig connectionConfig = new ConnectionConfig(bufSize, i2 >= 0 ? i2 : bufSize, cs, this.malformedInputAction, this.unmappableInputAction, this.messageConstraints);
            return connectionConfig;
        }
    }

    ConnectionConfig(int bufferSize2, int fragmentSizeHint2, Charset charset2, CodingErrorAction malformedInputAction2, CodingErrorAction unmappableInputAction2, MessageConstraints messageConstraints2) {
        this.bufferSize = bufferSize2;
        this.fragmentSizeHint = fragmentSizeHint2;
        this.charset = charset2;
        this.malformedInputAction = malformedInputAction2;
        this.unmappableInputAction = unmappableInputAction2;
        this.messageConstraints = messageConstraints2;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public int getFragmentSizeHint() {
        return this.fragmentSizeHint;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public CodingErrorAction getMalformedInputAction() {
        return this.malformedInputAction;
    }

    public CodingErrorAction getUnmappableInputAction() {
        return this.unmappableInputAction;
    }

    public MessageConstraints getMessageConstraints() {
        return this.messageConstraints;
    }

    /* access modifiers changed from: protected */
    public ConnectionConfig clone() throws CloneNotSupportedException {
        return (ConnectionConfig) super.clone();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[bufferSize=");
        builder.append(this.bufferSize);
        builder.append(", fragmentSizeHint=");
        builder.append(this.fragmentSizeHint);
        builder.append(", charset=");
        builder.append(this.charset);
        builder.append(", malformedInputAction=");
        builder.append(this.malformedInputAction);
        builder.append(", unmappableInputAction=");
        builder.append(this.unmappableInputAction);
        builder.append(", messageConstraints=");
        builder.append(this.messageConstraints);
        builder.append("]");
        return builder.toString();
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(ConnectionConfig config) {
        Args.notNull(config, "Connection config");
        return new Builder().setCharset(config.getCharset()).setMalformedInputAction(config.getMalformedInputAction()).setUnmappableInputAction(config.getUnmappableInputAction()).setMessageConstraints(config.getMessageConstraints());
    }
}
