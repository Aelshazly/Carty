package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
abstract class SendRequest {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public static abstract class Builder {
        public abstract SendRequest build();

        /* access modifiers changed from: 0000 */
        public abstract Builder setEncoding(Encoding encoding);

        /* access modifiers changed from: 0000 */
        public abstract Builder setEvent(Event<?> event);

        /* access modifiers changed from: 0000 */
        public abstract Builder setTransformer(Transformer<?, byte[]> transformer);

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            setEvent(event);
            setEncoding(encoding);
            setTransformer(transformer);
            return this;
        }
    }

    public abstract Encoding getEncoding();

    /* access modifiers changed from: 0000 */
    public abstract Event<?> getEvent();

    /* access modifiers changed from: 0000 */
    public abstract Transformer<?, byte[]> getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();

    SendRequest() {
    }

    public byte[] getPayload() {
        return (byte[]) getTransformer().apply(getEvent().getPayload());
    }

    public static Builder builder() {
        return new Builder();
    }
}
