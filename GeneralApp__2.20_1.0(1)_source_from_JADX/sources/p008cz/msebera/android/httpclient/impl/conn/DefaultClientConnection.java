package p008cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseFactory;
import p008cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import p008cz.msebera.android.httpclient.conn.OperatedClientConnection;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.impl.SocketHttpClientConnection;
import p008cz.msebera.android.httpclient.message.LineParser;
import p008cz.msebera.android.httpclient.p013io.HttpMessageParser;
import p008cz.msebera.android.httpclient.p013io.SessionInputBuffer;
import p008cz.msebera.android.httpclient.p013io.SessionOutputBuffer;
import p008cz.msebera.android.httpclient.params.BasicHttpParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.params.HttpProtocolParams;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.DefaultClientConnection */
public class DefaultClientConnection extends SocketHttpClientConnection implements OperatedClientConnection, ManagedHttpClientConnection, HttpContext {
    private final Map<String, Object> attributes = new HashMap();
    private boolean connSecure;
    public HttpClientAndroidLog headerLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private volatile boolean shutdown;
    private volatile Socket socket;
    private HttpHost targetHost;
    public HttpClientAndroidLog wireLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");

    public String getId() {
        return null;
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final boolean isSecure() {
        return this.connSecure;
    }

    public final Socket getSocket() {
        return this.socket;
    }

    public SSLSession getSSLSession() {
        if (this.socket instanceof SSLSocket) {
            return ((SSLSocket) this.socket).getSession();
        }
        return null;
    }

    public void opening(Socket sock, HttpHost target) throws IOException {
        assertNotOpen();
        this.socket = sock;
        this.targetHost = target;
        if (this.shutdown) {
            sock.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    public void openCompleted(boolean secure, HttpParams params) throws IOException {
        Args.notNull(params, "Parameters");
        assertNotOpen();
        this.connSecure = secure;
        bind(this.socket, params);
    }

    public void shutdown() throws IOException {
        this.shutdown = true;
        try {
            super.shutdown();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Connection ");
                sb.append(this);
                sb.append(" shut down");
                httpClientAndroidLog.debug(sb.toString());
            }
            Socket sock = this.socket;
            if (sock != null) {
                sock.close();
            }
        } catch (IOException ex) {
            this.log.debug("I/O error shutting down connection", ex);
        }
    }

    public void close() throws IOException {
        try {
            super.close();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Connection ");
                sb.append(this);
                sb.append(" closed");
                httpClientAndroidLog.debug(sb.toString());
            }
        } catch (IOException ex) {
            this.log.debug("I/O error closing connection", ex);
        }
    }

    /* access modifiers changed from: protected */
    public SessionInputBuffer createSessionInputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        SessionInputBuffer inbuffer = super.createSessionInputBuffer(socket2, buffersize > 0 ? buffersize : 8192, params);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionInputBuffer(inbuffer, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(params));
        }
        return inbuffer;
    }

    /* access modifiers changed from: protected */
    public SessionOutputBuffer createSessionOutputBuffer(Socket socket2, int buffersize, HttpParams params) throws IOException {
        SessionOutputBuffer outbuffer = super.createSessionOutputBuffer(socket2, buffersize > 0 ? buffersize : 8192, params);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionOutputBuffer(outbuffer, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(params));
        }
        return outbuffer;
    }

    /* access modifiers changed from: protected */
    public HttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer buffer, HttpResponseFactory responseFactory, HttpParams params) {
        return new DefaultHttpResponseParser(buffer, (LineParser) null, responseFactory, params);
    }

    public void bind(Socket socket2) throws IOException {
        bind(socket2, new BasicHttpParams());
    }

    public void update(Socket sock, HttpHost target, boolean secure, HttpParams params) throws IOException {
        assertOpen();
        Args.notNull(target, "Target host");
        Args.notNull(params, "Parameters");
        if (sock != null) {
            this.socket = sock;
            bind(sock, params);
        }
        this.targetHost = target;
        this.connSecure = secure;
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        Header[] headers;
        HttpResponse response = super.receiveResponseHeader();
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Receiving response: ");
            sb.append(response.getStatusLine());
            httpClientAndroidLog.debug(sb.toString());
        }
        if (this.headerLog.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog2 = this.headerLog;
            StringBuilder sb2 = new StringBuilder();
            String str = "<< ";
            sb2.append(str);
            sb2.append(response.getStatusLine().toString());
            httpClientAndroidLog2.debug(sb2.toString());
            for (Header header : response.getAllHeaders()) {
                HttpClientAndroidLog httpClientAndroidLog3 = this.headerLog;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(header.toString());
                httpClientAndroidLog3.debug(sb3.toString());
            }
        }
        return response;
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        Header[] headers;
        if (this.log.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Sending request: ");
            sb.append(request.getRequestLine());
            httpClientAndroidLog.debug(sb.toString());
        }
        super.sendRequestHeader(request);
        if (this.headerLog.isDebugEnabled()) {
            HttpClientAndroidLog httpClientAndroidLog2 = this.headerLog;
            StringBuilder sb2 = new StringBuilder();
            String str = ">> ";
            sb2.append(str);
            sb2.append(request.getRequestLine().toString());
            httpClientAndroidLog2.debug(sb2.toString());
            for (Header header : request.getAllHeaders()) {
                HttpClientAndroidLog httpClientAndroidLog3 = this.headerLog;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(header.toString());
                httpClientAndroidLog3.debug(sb3.toString());
            }
        }
    }

    public Object getAttribute(String id) {
        return this.attributes.get(id);
    }

    public Object removeAttribute(String id) {
        return this.attributes.remove(id);
    }

    public void setAttribute(String id, Object obj) {
        this.attributes.put(id, obj);
    }
}
