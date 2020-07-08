package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import java.io.InterruptedIOException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.ServiceUnavailableRetryExec */
public class ServiceUnavailableRetryExec implements ClientExecChain {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final ClientExecChain requestExecutor;
    private final ServiceUnavailableRetryStrategy retryStrategy;

    public ServiceUnavailableRetryExec(ClientExecChain requestExecutor2, ServiceUnavailableRetryStrategy retryStrategy2) {
        Args.notNull(requestExecutor2, "HTTP request executor");
        Args.notNull(retryStrategy2, "Retry strategy");
        this.requestExecutor = requestExecutor2;
        this.retryStrategy = retryStrategy2;
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Header[] origheaders = request.getAllHeaders();
        int c = 1;
        while (true) {
            CloseableHttpResponse response = this.requestExecutor.execute(route, request, context, execAware);
            try {
                if (!this.retryStrategy.retryRequest(response, c, context)) {
                    return response;
                }
                response.close();
                long nextInterval = this.retryStrategy.getRetryInterval();
                if (nextInterval > 0) {
                    HttpClientAndroidLog httpClientAndroidLog = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Wait for ");
                    sb.append(nextInterval);
                    httpClientAndroidLog.trace(sb.toString());
                    Thread.sleep(nextInterval);
                }
                request.setHeaders(origheaders);
                c++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (RuntimeException ex) {
                response.close();
                throw ex;
            }
        }
    }
}
