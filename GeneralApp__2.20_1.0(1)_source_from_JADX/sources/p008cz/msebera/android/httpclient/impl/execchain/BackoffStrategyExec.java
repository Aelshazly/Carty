package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.BackoffManager;
import p008cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.BackoffStrategyExec */
public class BackoffStrategyExec implements ClientExecChain {
    private final BackoffManager backoffManager;
    private final ConnectionBackoffStrategy connectionBackoffStrategy;
    private final ClientExecChain requestExecutor;

    public BackoffStrategyExec(ClientExecChain requestExecutor2, ConnectionBackoffStrategy connectionBackoffStrategy2, BackoffManager backoffManager2) {
        Args.notNull(requestExecutor2, "HTTP client request executor");
        Args.notNull(connectionBackoffStrategy2, "Connection backoff strategy");
        Args.notNull(backoffManager2, "Backoff manager");
        this.requestExecutor = requestExecutor2;
        this.connectionBackoffStrategy = connectionBackoffStrategy2;
        this.backoffManager = backoffManager2;
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Args.notNull(route, "HTTP route");
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        CloseableHttpResponse out = null;
        try {
            CloseableHttpResponse out2 = this.requestExecutor.execute(route, request, context, execAware);
            if (this.connectionBackoffStrategy.shouldBackoff((HttpResponse) out2)) {
                this.backoffManager.backOff(route);
            } else {
                this.backoffManager.probe(route);
            }
            return out2;
        } catch (Exception ex) {
            if (out != null) {
                out.close();
            }
            if (this.connectionBackoffStrategy.shouldBackoff((Throwable) ex)) {
                this.backoffManager.backOff(route);
            }
            if (ex instanceof RuntimeException) {
                throw ((RuntimeException) ex);
            } else if (ex instanceof HttpException) {
                throw ((HttpException) ex);
            } else if (ex instanceof IOException) {
                throw ((IOException) ex);
            } else {
                throw new UndeclaredThrowableException(ex);
            }
        }
    }
}
