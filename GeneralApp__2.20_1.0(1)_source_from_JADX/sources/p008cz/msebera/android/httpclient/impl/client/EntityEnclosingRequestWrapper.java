package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import p008cz.msebera.android.httpclient.protocol.HTTP;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.EntityEnclosingRequestWrapper */
public class EntityEnclosingRequestWrapper extends RequestWrapper implements HttpEntityEnclosingRequest {
    /* access modifiers changed from: private */
    public boolean consumed;
    private HttpEntity entity;

    /* renamed from: cz.msebera.android.httpclient.impl.client.EntityEnclosingRequestWrapper$EntityWrapper */
    class EntityWrapper extends HttpEntityWrapper {
        EntityWrapper(HttpEntity entity) {
            super(entity);
        }

        public void consumeContent() throws IOException {
            EntityEnclosingRequestWrapper.this.consumed = true;
            super.consumeContent();
        }

        public InputStream getContent() throws IOException {
            EntityEnclosingRequestWrapper.this.consumed = true;
            return super.getContent();
        }

        public void writeTo(OutputStream outstream) throws IOException {
            EntityEnclosingRequestWrapper.this.consumed = true;
            super.writeTo(outstream);
        }
    }

    public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest request) throws ProtocolException {
        super(request);
        setEntity(request.getEntity());
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setEntity(HttpEntity entity2) {
        this.entity = entity2 != null ? new EntityWrapper(entity2) : null;
        this.consumed = false;
    }

    public boolean expectContinue() {
        Header expect = getFirstHeader("Expect");
        if (expect != null) {
            if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean isRepeatable() {
        HttpEntity httpEntity = this.entity;
        return httpEntity == null || httpEntity.isRepeatable() || !this.consumed;
    }
}
