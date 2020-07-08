package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;
import okio.Timeout;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, mo24952d2 = {"okhttp3/internal/cache/CacheInterceptor$cacheWritingResponse$cacheWritingSource$1", "Lokio/Source;", "cacheRequestClosed", "", "getCacheRequestClosed", "()Z", "setCacheRequestClosed", "(Z)V", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: CacheInterceptor.kt */
public final class CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 implements Source {
    final /* synthetic */ BufferedSink $cacheBody;
    final /* synthetic */ CacheRequest $cacheRequest;
    final /* synthetic */ BufferedSource $source;
    private boolean cacheRequestClosed;

    CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(BufferedSource $captured_local_variable$0, CacheRequest $captured_local_variable$1, BufferedSink $captured_local_variable$2) {
        this.$source = $captured_local_variable$0;
        this.$cacheRequest = $captured_local_variable$1;
        this.$cacheBody = $captured_local_variable$2;
    }

    public final boolean getCacheRequestClosed() {
        return this.cacheRequestClosed;
    }

    public final void setCacheRequestClosed(boolean z) {
        this.cacheRequestClosed = z;
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        try {
            long bytesRead = this.$source.read(sink, byteCount);
            if (bytesRead == -1) {
                if (!this.cacheRequestClosed) {
                    this.cacheRequestClosed = true;
                    this.$cacheBody.close();
                }
                return -1;
            }
            sink.copyTo(this.$cacheBody.getBuffer(), sink.size() - bytesRead, bytesRead);
            this.$cacheBody.emitCompleteSegments();
            return bytesRead;
        } catch (IOException e) {
            if (!this.cacheRequestClosed) {
                this.cacheRequestClosed = true;
                this.$cacheRequest.abort();
            }
            throw e;
        }
    }

    public Timeout timeout() {
        return this.$source.timeout();
    }

    public void close() throws IOException {
        if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
            this.cacheRequestClosed = true;
            this.$cacheRequest.abort();
        }
        this.$source.close();
    }
}
