package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.cache.InputLimit;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.SizeLimitedResponseReader */
class SizeLimitedResponseReader {
    private boolean consumed;
    private InputStream instream;
    private InputLimit limit;
    private final long maxResponseSizeBytes;
    private final HttpRequest request;
    private Resource resource;
    private final ResourceFactory resourceFactory;
    /* access modifiers changed from: private */
    public final CloseableHttpResponse response;

    public SizeLimitedResponseReader(ResourceFactory resourceFactory2, long maxResponseSizeBytes2, HttpRequest request2, CloseableHttpResponse response2) {
        this.resourceFactory = resourceFactory2;
        this.maxResponseSizeBytes = maxResponseSizeBytes2;
        this.request = request2;
        this.response = response2;
    }

    /* access modifiers changed from: protected */
    public void readResponse() throws IOException {
        if (!this.consumed) {
            doConsume();
        }
    }

    private void ensureNotConsumed() {
        if (this.consumed) {
            throw new IllegalStateException("Response has already been consumed");
        }
    }

    private void ensureConsumed() {
        if (!this.consumed) {
            throw new IllegalStateException("Response has not been consumed");
        }
    }

    private void doConsume() throws IOException {
        ensureNotConsumed();
        this.consumed = true;
        this.limit = new InputLimit(this.maxResponseSizeBytes);
        HttpEntity entity = this.response.getEntity();
        if (entity != null) {
            String uri = this.request.getRequestLine().getUri();
            this.instream = entity.getContent();
            try {
                this.resource = this.resourceFactory.generate(uri, this.instream, this.limit);
            } finally {
                if (!this.limit.isReached()) {
                    this.instream.close();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isLimitReached() {
        ensureConsumed();
        return this.limit.isReached();
    }

    /* access modifiers changed from: 0000 */
    public Resource getResource() {
        ensureConsumed();
        return this.resource;
    }

    /* access modifiers changed from: 0000 */
    public CloseableHttpResponse getReconstructedResponse() throws IOException {
        ensureConsumed();
        HttpResponse reconstructed = new BasicHttpResponse(this.response.getStatusLine());
        reconstructed.setHeaders(this.response.getAllHeaders());
        CombinedEntity combinedEntity = new CombinedEntity(this.resource, this.instream);
        HttpEntity entity = this.response.getEntity();
        if (entity != null) {
            combinedEntity.setContentType(entity.getContentType());
            combinedEntity.setContentEncoding(entity.getContentEncoding());
            combinedEntity.setChunked(entity.isChunked());
        }
        reconstructed.setEntity(combinedEntity);
        return (CloseableHttpResponse) Proxy.newProxyInstance(ResponseProxyHandler.class.getClassLoader(), new Class[]{CloseableHttpResponse.class}, new ResponseProxyHandler(reconstructed) {
            public void close() throws IOException {
                SizeLimitedResponseReader.this.response.close();
            }
        });
    }
}
