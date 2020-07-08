package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_SendRequest extends SendRequest {
    private final Encoding encoding;
    private final Event<?> event;
    private final Transformer<?, byte[]> transformer;
    private final TransportContext transportContext;
    private final String transportName;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends com.google.android.datatransport.runtime.SendRequest.Builder {
        private Encoding encoding;
        private Event<?> event;
        private Transformer<?, byte[]> transformer;
        private TransportContext transportContext;
        private String transportName;

        Builder() {
        }

        public com.google.android.datatransport.runtime.SendRequest.Builder setTransportContext(TransportContext transportContext2) {
            if (transportContext2 != null) {
                this.transportContext = transportContext2;
                return this;
            }
            throw new NullPointerException("Null transportContext");
        }

        public com.google.android.datatransport.runtime.SendRequest.Builder setTransportName(String transportName2) {
            if (transportName2 != null) {
                this.transportName = transportName2;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.runtime.SendRequest.Builder setEvent(Event<?> event2) {
            if (event2 != null) {
                this.event = event2;
                return this;
            }
            throw new NullPointerException("Null event");
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.runtime.SendRequest.Builder setTransformer(Transformer<?, byte[]> transformer2) {
            if (transformer2 != null) {
                this.transformer = transformer2;
                return this;
            }
            throw new NullPointerException("Null transformer");
        }

        /* access modifiers changed from: 0000 */
        public com.google.android.datatransport.runtime.SendRequest.Builder setEncoding(Encoding encoding2) {
            if (encoding2 != null) {
                this.encoding = encoding2;
                return this;
            }
            throw new NullPointerException("Null encoding");
        }

        public SendRequest build() {
            String missing = "";
            if (this.transportContext == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" transportContext");
                missing = sb.toString();
            }
            if (this.transportName == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" transportName");
                missing = sb2.toString();
            }
            if (this.event == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" event");
                missing = sb3.toString();
            }
            if (this.transformer == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" transformer");
                missing = sb4.toString();
            }
            if (this.encoding == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" encoding");
                missing = sb5.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_SendRequest autoValue_SendRequest = new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding);
                return autoValue_SendRequest;
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Missing required properties:");
            sb6.append(missing);
            throw new IllegalStateException(sb6.toString());
        }
    }

    private AutoValue_SendRequest(TransportContext transportContext2, String transportName2, Event<?> event2, Transformer<?, byte[]> transformer2, Encoding encoding2) {
        this.transportContext = transportContext2;
        this.transportName = transportName2;
        this.event = event2;
        this.transformer = transformer2;
        this.encoding = encoding2;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public String getTransportName() {
        return this.transportName;
    }

    /* access modifiers changed from: 0000 */
    public Event<?> getEvent() {
        return this.event;
    }

    /* access modifiers changed from: 0000 */
    public Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendRequest{transportContext=");
        sb.append(this.transportContext);
        sb.append(", transportName=");
        sb.append(this.transportName);
        sb.append(", event=");
        sb.append(this.event);
        sb.append(", transformer=");
        sb.append(this.transformer);
        sb.append(", encoding=");
        sb.append(this.encoding);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof SendRequest)) {
            return false;
        }
        SendRequest that = (SendRequest) o;
        if (!this.transportContext.equals(that.getTransportContext()) || !this.transportName.equals(that.getTransportName()) || !this.event.equals(that.getEvent()) || !this.transformer.equals(that.getTransformer()) || !this.encoding.equals(that.getEncoding())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((((1 * 1000003) ^ this.transportContext.hashCode()) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003) ^ this.encoding.hashCode();
    }
}
