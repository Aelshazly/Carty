package p008cz.msebera.android.httpclient.protocol;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpClientConnection;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.HttpRequestExecutor */
public class HttpRequestExecutor {
    public static final int DEFAULT_WAIT_FOR_CONTINUE = 3000;
    private final int waitForContinue;

    public HttpRequestExecutor(int waitForContinue2) {
        this.waitForContinue = Args.positive(waitForContinue2, "Wait for continue time");
    }

    public HttpRequestExecutor() {
        this(3000);
    }

    /* access modifiers changed from: protected */
    public boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
        boolean z = false;
        if ("HEAD".equalsIgnoreCase(request.getRequestLine().getMethod())) {
            return false;
        }
        int status = response.getStatusLine().getStatusCode();
        if (!(status < 200 || status == 204 || status == 304 || status == 205)) {
            z = true;
        }
        return z;
    }

    public HttpResponse execute(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        Args.notNull(request, "HTTP request");
        Args.notNull(conn, "Client connection");
        Args.notNull(context, "HTTP context");
        try {
            HttpResponse response = doSendRequest(request, conn, context);
            if (response == null) {
                return doReceiveResponse(request, conn, context);
            }
            return response;
        } catch (IOException ex) {
            closeConnection(conn);
            throw ex;
        } catch (HttpException ex2) {
            closeConnection(conn);
            throw ex2;
        } catch (RuntimeException ex3) {
            closeConnection(conn);
            throw ex3;
        }
    }

    private static void closeConnection(HttpClientConnection conn) {
        try {
            conn.close();
        } catch (IOException e) {
        }
    }

    public void preProcess(HttpRequest request, HttpProcessor processor, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        Args.notNull(processor, "HTTP processor");
        Args.notNull(context, "HTTP context");
        context.setAttribute("http.request", request);
        processor.process(request, context);
    }

    /* access modifiers changed from: protected */
    public HttpResponse doSendRequest(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        Args.notNull(request, "HTTP request");
        Args.notNull(conn, "Client connection");
        Args.notNull(context, "HTTP context");
        HttpResponse response = null;
        context.setAttribute("http.connection", conn);
        String str = "http.request_sent";
        context.setAttribute(str, Boolean.FALSE);
        conn.sendRequestHeader(request);
        if (request instanceof HttpEntityEnclosingRequest) {
            boolean sendentity = true;
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if (((HttpEntityEnclosingRequest) request).expectContinue() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                conn.flush();
                if (conn.isResponseAvailable(this.waitForContinue)) {
                    response = conn.receiveResponseHeader();
                    if (canResponseHaveBody(request, response)) {
                        conn.receiveResponseEntity(response);
                    }
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200) {
                        sendentity = false;
                    } else if (status == 100) {
                        response = null;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unexpected response: ");
                        sb.append(response.getStatusLine());
                        throw new ProtocolException(sb.toString());
                    }
                }
            }
            if (sendentity) {
                conn.sendRequestEntity((HttpEntityEnclosingRequest) request);
            }
        }
        conn.flush();
        context.setAttribute(str, Boolean.TRUE);
        return response;
    }

    /* access modifiers changed from: protected */
    public HttpResponse doReceiveResponse(HttpRequest request, HttpClientConnection conn, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        Args.notNull(conn, "Client connection");
        Args.notNull(context, "HTTP context");
        HttpResponse response = null;
        int statusCode = 0;
        while (true) {
            if (response != null && statusCode >= 200) {
                return response;
            }
            response = conn.receiveResponseHeader();
            if (canResponseHaveBody(request, response)) {
                conn.receiveResponseEntity(response);
            }
            statusCode = response.getStatusLine().getStatusCode();
        }
    }

    public void postProcess(HttpResponse response, HttpProcessor processor, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        Args.notNull(processor, "HTTP processor");
        Args.notNull(context, "HTTP context");
        context.setAttribute("http.response", response);
        processor.process(response, context);
    }
}
