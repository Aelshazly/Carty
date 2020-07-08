package p008cz.msebera.android.httpclient.config;

import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.config.MessageConstraints */
public class MessageConstraints implements Cloneable {
    public static final MessageConstraints DEFAULT = new Builder().build();
    private final int maxHeaderCount;
    private final int maxLineLength;

    /* renamed from: cz.msebera.android.httpclient.config.MessageConstraints$Builder */
    public static class Builder {
        private int maxHeaderCount = -1;
        private int maxLineLength = -1;

        Builder() {
        }

        public Builder setMaxLineLength(int maxLineLength2) {
            this.maxLineLength = maxLineLength2;
            return this;
        }

        public Builder setMaxHeaderCount(int maxHeaderCount2) {
            this.maxHeaderCount = maxHeaderCount2;
            return this;
        }

        public MessageConstraints build() {
            return new MessageConstraints(this.maxLineLength, this.maxHeaderCount);
        }
    }

    MessageConstraints(int maxLineLength2, int maxHeaderCount2) {
        this.maxLineLength = maxLineLength2;
        this.maxHeaderCount = maxHeaderCount2;
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    /* access modifiers changed from: protected */
    public MessageConstraints clone() throws CloneNotSupportedException {
        return (MessageConstraints) super.clone();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[maxLineLength=");
        builder.append(this.maxLineLength);
        builder.append(", maxHeaderCount=");
        builder.append(this.maxHeaderCount);
        builder.append("]");
        return builder.toString();
    }

    public static MessageConstraints lineLen(int max) {
        return new MessageConstraints(Args.notNegative(max, "Max line length"), -1);
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(MessageConstraints config) {
        Args.notNull(config, "Message constraints");
        return new Builder().setMaxHeaderCount(config.getMaxHeaderCount()).setMaxLineLength(config.getMaxLineLength());
    }
}
