package p008cz.msebera.android.httpclient.impl.execchain;

import java.io.IOException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.NoHttpResponseException;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.NonRepeatableRequestException;
import p008cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import p008cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.execchain.RetryExec */
public class RetryExec implements ClientExecChain {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final ClientExecChain requestExecutor;
    private final HttpRequestRetryHandler retryHandler;

    public RetryExec(ClientExecChain requestExecutor2, HttpRequestRetryHandler retryHandler2) {
        Args.notNull(requestExecutor2, "HTTP request executor");
        Args.notNull(retryHandler2, "HTTP request retry handler");
        this.requestExecutor = requestExecutor2;
        this.retryHandler = retryHandler2;
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Args.notNull(route, "HTTP route");
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        Header[] origheaders = request.getAllHeaders();
        int execCount = 1;
        while (true) {
            try {
                return this.requestExecutor.execute(route, request, context, execAware);
            } catch (IOException ex) {
                if (execAware != null && execAware.isAborted()) {
                    this.log.debug("Request has been aborted");
                    throw ex;
                } else if (this.retryHandler.retryRequest(ex, execCount, context)) {
                    if (this.log.isInfoEnabled()) {
                        HttpClientAndroidLog httpClientAndroidLog = this.log;
                        StringBuilder sb = new StringBuilder();
                        sb.append("I/O exception (");
                        sb.append(ex.getClass().getName());
                        sb.append(") caught when processing request to ");
                        sb.append(route);
                        sb.append(": ");
                        sb.append(ex.getMessage());
                        httpClientAndroidLog.info(sb.toString());
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(ex.getMessage(), ex);
                    }
                    if (RequestEntityProxy.isRepeatable(request)) {
                        request.setHeaders(origheaders);
                        if (this.log.isInfoEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Retrying request to ");
                            sb2.append(route);
                            httpClientAndroidLog2.info(sb2.toString());
                        }
                        execCount++;
                    } else {
                        this.log.debug("Cannot retry non-repeatable request");
                        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity", ex);
                    }
                } else if (ex instanceof NoHttpResponseException) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(route.getTargetHost().toHostString());
                    sb3.append(" failed to respond");
                    NoHttpResponseException updatedex = new NoHttpResponseException(sb3.toString());
                    updatedex.setStackTrace(ex.getStackTrace());
                    throw updatedex;
                } else {
                    throw ex;
                }
            }
        }
    }
}
