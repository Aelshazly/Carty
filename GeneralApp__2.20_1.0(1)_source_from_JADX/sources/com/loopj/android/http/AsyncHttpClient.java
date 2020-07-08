package com.loopj.android.http;

import android.content.Context;
import android.os.Looper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.auth.AuthScope;
import p008cz.msebera.android.httpclient.auth.AuthState;
import p008cz.msebera.android.httpclient.auth.Credentials;
import p008cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.CredentialsProvider;
import p008cz.msebera.android.httpclient.client.HttpClient;
import p008cz.msebera.android.httpclient.client.RedirectHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;
import p008cz.msebera.android.httpclient.client.methods.HttpHead;
import p008cz.msebera.android.httpclient.client.methods.HttpPatch;
import p008cz.msebera.android.httpclient.client.methods.HttpPost;
import p008cz.msebera.android.httpclient.client.methods.HttpPut;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.client.params.ClientPNames;
import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import p008cz.msebera.android.httpclient.conn.params.ConnPerRouteBean;
import p008cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import p008cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import p008cz.msebera.android.httpclient.conn.scheme.Scheme;
import p008cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import p008cz.msebera.android.httpclient.conn.scheme.SocketFactory;
import p008cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import p008cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import p008cz.msebera.android.httpclient.impl.auth.BasicScheme;
import p008cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import p008cz.msebera.android.httpclient.impl.conn.tsccm.ThreadSafeClientConnManager;
import p008cz.msebera.android.httpclient.params.BasicHttpParams;
import p008cz.msebera.android.httpclient.params.HttpConnectionParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.params.HttpProtocolParams;
import p008cz.msebera.android.httpclient.protocol.BasicHttpContext;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.protocol.SyncBasicHttpContext;

public class AsyncHttpClient {
    public static final int DEFAULT_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_MAX_RETRIES = 5;
    public static final int DEFAULT_RETRY_SLEEP_TIME_MILLIS = 1500;
    public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    public static final int DEFAULT_SOCKET_TIMEOUT = 10000;
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String LOG_TAG = "AsyncHttpClient";
    public static LogInterface log = new LogHandler();
    /* access modifiers changed from: private */
    public final Map<String, String> clientHeaderMap;
    private int connectTimeout;
    private final DefaultHttpClient httpClient;
    private final HttpContext httpContext;
    private boolean isUrlEncodingEnabled;
    private int maxConnections;
    private final Map<Context, List<RequestHandle>> requestMap;
    private int responseTimeout;
    private ExecutorService threadPool;

    private static class InflatingEntity extends HttpEntityWrapper {
        GZIPInputStream gzippedStream;
        PushbackInputStream pushbackStream;
        InputStream wrappedStream;

        public InflatingEntity(HttpEntity wrapped) {
            super(wrapped);
        }

        public InputStream getContent() throws IOException {
            this.wrappedStream = this.wrappedEntity.getContent();
            this.pushbackStream = new PushbackInputStream(this.wrappedStream, 2);
            if (!AsyncHttpClient.isInputStreamGZIPCompressed(this.pushbackStream)) {
                return this.pushbackStream;
            }
            this.gzippedStream = new GZIPInputStream(this.pushbackStream);
            return this.gzippedStream;
        }

        public long getContentLength() {
            if (this.wrappedEntity == null) {
                return 0;
            }
            return this.wrappedEntity.getContentLength();
        }

        public void consumeContent() throws IOException {
            AsyncHttpClient.silentCloseInputStream(this.wrappedStream);
            AsyncHttpClient.silentCloseInputStream(this.pushbackStream);
            AsyncHttpClient.silentCloseInputStream(this.gzippedStream);
            super.consumeContent();
        }
    }

    public AsyncHttpClient() {
        this(false, 80, 443);
    }

    public AsyncHttpClient(int httpPort) {
        this(false, httpPort, 443);
    }

    public AsyncHttpClient(int httpPort, int httpsPort) {
        this(false, httpPort, httpsPort);
    }

    public AsyncHttpClient(boolean fixNoHttpResponseException, int httpPort, int httpsPort) {
        this(getDefaultSchemeRegistry(fixNoHttpResponseException, httpPort, httpsPort));
    }

    public AsyncHttpClient(SchemeRegistry schemeRegistry) {
        this.maxConnections = 10;
        this.connectTimeout = DEFAULT_SOCKET_TIMEOUT;
        this.responseTimeout = DEFAULT_SOCKET_TIMEOUT;
        boolean z = true;
        this.isUrlEncodingEnabled = true;
        BasicHttpParams httpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(httpParams, (long) this.connectTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(this.maxConnections));
        ConnManagerParams.setMaxTotalConnections(httpParams, 10);
        HttpConnectionParams.setSoTimeout(httpParams, this.responseTimeout);
        HttpConnectionParams.setConnectionTimeout(httpParams, this.connectTimeout);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        ClientConnectionManager cm = createConnectionManager(schemeRegistry, httpParams);
        if (cm == null) {
            z = false;
        }
        Utils.asserts(z, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.threadPool = getDefaultThreadPool();
        this.requestMap = Collections.synchronizedMap(new WeakHashMap());
        this.clientHeaderMap = new HashMap();
        this.httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        this.httpClient = new DefaultHttpClient(cm, httpParams);
        this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest request, HttpContext context) {
                String str = "Accept-Encoding";
                if (!request.containsHeader(str)) {
                    request.addHeader(str, AsyncHttpClient.ENCODING_GZIP);
                }
                for (String header : AsyncHttpClient.this.clientHeaderMap.keySet()) {
                    if (request.containsHeader(header)) {
                        Header overwritten = request.getFirstHeader(header);
                        AsyncHttpClient.log.mo22447d(AsyncHttpClient.LOG_TAG, String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{header, AsyncHttpClient.this.clientHeaderMap.get(header), overwritten.getName(), overwritten.getValue()}));
                        request.removeHeader(overwritten);
                    }
                    request.addHeader(header, (String) AsyncHttpClient.this.clientHeaderMap.get(header));
                }
            }
        });
        this.httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(HttpResponse response, HttpContext context) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header encoding = entity.getContentEncoding();
                    if (encoding != null) {
                        HeaderElement[] elements = encoding.getElements();
                        int length = elements.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            } else if (elements[i].getName().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                                response.setEntity(new InflatingEntity(entity));
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        });
        this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                AuthState authState = (AuthState) context.getAttribute("http.auth.target-scope");
                CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute("http.auth.credentials-provider");
                HttpHost targetHost = (HttpHost) context.getAttribute("http.target_host");
                if (authState.getAuthScheme() == null) {
                    Credentials creds = credsProvider.getCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()));
                    if (creds != null) {
                        authState.setAuthScheme(new BasicScheme());
                        authState.setCredentials(creds);
                    }
                }
            }
        }, 0);
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(5, 1500));
    }

    private static SchemeRegistry getDefaultSchemeRegistry(boolean fixNoHttpResponseException, int httpPort, int httpsPort) {
        SSLSocketFactory sslSocketFactory;
        String str = LOG_TAG;
        if (fixNoHttpResponseException) {
            log.mo22447d(str, "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (httpPort < 1) {
            httpPort = 80;
            log.mo22447d(str, "Invalid HTTP port number specified, defaulting to 80");
        }
        if (httpsPort < 1) {
            httpsPort = 443;
            log.mo22447d(str, "Invalid HTTPS port number specified, defaulting to 443");
        }
        if (fixNoHttpResponseException) {
            sslSocketFactory = MySSLSocketFactory.getFixedSocketFactory();
        } else {
            sslSocketFactory = SSLSocketFactory.getSocketFactory();
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, (SocketFactory) PlainSocketFactory.getSocketFactory(), httpPort));
        schemeRegistry.register(new Scheme("https", (SocketFactory) sslSocketFactory, httpsPort));
        return schemeRegistry;
    }

    public static void allowRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToWhitelist(cls);
        }
    }

    public static void blockRetryExceptionClass(Class<?> cls) {
        if (cls != null) {
            RetryHandler.addClassToBlacklist(cls);
        }
    }

    public static String getUrlWithQueryString(boolean shouldEncodeUrl, String url, RequestParams params) {
        if (url == null) {
            return null;
        }
        if (shouldEncodeUrl) {
            try {
                URL _url = new URL(URLDecoder.decode(url, "UTF-8"));
                URI uri = new URI(_url.getProtocol(), _url.getUserInfo(), _url.getHost(), _url.getPort(), _url.getPath(), _url.getQuery(), _url.getRef());
                url = uri.toASCIIString();
            } catch (Exception ex) {
                log.mo22450e(LOG_TAG, "getUrlWithQueryString encoding URL", ex);
            }
        }
        if (params != null) {
            String paramString = params.getParamString().trim();
            if (!paramString.equals("")) {
                String str = "?";
                if (!paramString.equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(url);
                    if (url.contains(str)) {
                        str = "&";
                    }
                    sb.append(str);
                    String url2 = sb.toString();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(url2);
                    sb2.append(paramString);
                    url = sb2.toString();
                }
            }
        }
        return url;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r0v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [boolean, int]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [int, ?[int, short, byte, char], boolean]
      mth insns count: 27
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isInputStreamGZIPCompressed(java.io.PushbackInputStream r7) throws java.io.IOException {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 2
            byte[] r2 = new byte[r1]
            r3 = 0
        L_0x0008:
            if (r3 >= r1) goto L_0x001d
            int r4 = 2 - r3
            int r4 = r7.read(r2, r3, r4)     // Catch:{ all -> 0x0018 }
            if (r4 >= 0) goto L_0x0016
            r7.unread(r2, r0, r3)
            return r0
        L_0x0016:
            int r3 = r3 + r4
            goto L_0x0008
        L_0x0018:
            r1 = move-exception
            r7.unread(r2, r0, r3)
            throw r1
        L_0x001d:
            r7.unread(r2, r0, r3)
            byte r1 = r2[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r4 = 1
            byte r5 = r2[r4]
            int r5 = r5 << 8
            r6 = 65280(0xff00, float:9.1477E-41)
            r5 = r5 & r6
            r1 = r1 | r5
            r5 = 35615(0x8b1f, float:4.9907E-41)
            if (r5 != r1) goto L_0x0035
            r0 = 1
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.AsyncHttpClient.isInputStreamGZIPCompressed(java.io.PushbackInputStream):boolean");
    }

    public static void silentCloseInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                log.mo22463w(LOG_TAG, "Cannot close input stream", e);
            }
        }
    }

    public static void silentCloseOutputStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                log.mo22463w(LOG_TAG, "Cannot close output stream", e);
            }
        }
    }

    public static void endEntityViaReflection(HttpEntity entity) {
        if (entity instanceof HttpEntityWrapper) {
            Field f = null;
            try {
                Field[] fields = HttpEntityWrapper.class.getDeclaredFields();
                int length = fields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field ff = fields[i];
                    if (ff.getName().equals("wrappedEntity")) {
                        f = ff;
                        break;
                    }
                    i++;
                }
                if (f != null) {
                    f.setAccessible(true);
                    HttpEntity wrapped = (HttpEntity) f.get(entity);
                    if (wrapped != null) {
                        wrapped.consumeContent();
                    }
                }
            } catch (Throwable t) {
                log.mo22450e(LOG_TAG, "wrappedEntity consume", t);
            }
        }
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public HttpContext getHttpContext() {
        return this.httpContext;
    }

    public boolean isLoggingEnabled() {
        return log.isLoggingEnabled();
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        log.setLoggingEnabled(loggingEnabled);
    }

    public int getLoggingLevel() {
        return log.getLoggingLevel();
    }

    public void setLoggingLevel(int logLevel) {
        log.setLoggingLevel(logLevel);
    }

    public LogInterface getLogInterface() {
        return log;
    }

    public void setLogInterface(LogInterface logInterfaceInstance) {
        if (logInterfaceInstance != null) {
            log = logInterfaceInstance;
        }
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.httpContext.setAttribute("http.cookie-store", cookieStore);
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    public void setThreadPool(ExecutorService threadPool2) {
        this.threadPool = threadPool2;
    }

    /* access modifiers changed from: protected */
    public ExecutorService getDefaultThreadPool() {
        return Executors.newCachedThreadPool();
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager createConnectionManager(SchemeRegistry schemeRegistry, BasicHttpParams httpParams) {
        return new ThreadSafeClientConnManager(httpParams, schemeRegistry);
    }

    public void setEnableRedirects(boolean enableRedirects, boolean enableRelativeRedirects, boolean enableCircularRedirects) {
        this.httpClient.getParams().setBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, !enableRelativeRedirects);
        this.httpClient.getParams().setBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, enableCircularRedirects);
        this.httpClient.setRedirectHandler(new MyRedirectHandler(enableRedirects));
    }

    public void setEnableRedirects(boolean enableRedirects, boolean enableRelativeRedirects) {
        setEnableRedirects(enableRedirects, enableRelativeRedirects, true);
    }

    public void setEnableRedirects(boolean enableRedirects) {
        setEnableRedirects(enableRedirects, enableRedirects, enableRedirects);
    }

    public void setRedirectHandler(RedirectHandler customRedirectHandler) {
        this.httpClient.setRedirectHandler(customRedirectHandler);
    }

    public void setUserAgent(String userAgent) {
        HttpProtocolParams.setUserAgent(this.httpClient.getParams(), userAgent);
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int maxConnections2) {
        if (maxConnections2 < 1) {
            maxConnections2 = 10;
        }
        this.maxConnections = maxConnections2;
        ConnManagerParams.setMaxConnectionsPerRoute(this.httpClient.getParams(), new ConnPerRouteBean(this.maxConnections));
    }

    public void setTimeout(int value) {
        int value2 = value < 1000 ? DEFAULT_SOCKET_TIMEOUT : value;
        setConnectTimeout(value2);
        setResponseTimeout(value2);
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int value) {
        this.connectTimeout = value < 1000 ? DEFAULT_SOCKET_TIMEOUT : value;
        HttpParams httpParams = this.httpClient.getParams();
        ConnManagerParams.setTimeout(httpParams, (long) this.connectTimeout);
        HttpConnectionParams.setConnectionTimeout(httpParams, this.connectTimeout);
    }

    public int getResponseTimeout() {
        return this.responseTimeout;
    }

    public void setResponseTimeout(int value) {
        this.responseTimeout = value < 1000 ? DEFAULT_SOCKET_TIMEOUT : value;
        HttpConnectionParams.setSoTimeout(this.httpClient.getParams(), this.responseTimeout);
    }

    public void setProxy(String hostname, int port) {
        this.httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(hostname, port));
    }

    public void setProxy(String hostname, int port, String username, String password) {
        this.httpClient.getCredentialsProvider().setCredentials(new AuthScope(hostname, port), new UsernamePasswordCredentials(username, password));
        this.httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(hostname, port));
    }

    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", (SocketFactory) sslSocketFactory, 443));
    }

    public void setMaxRetriesAndTimeout(int retries, int timeout) {
        this.httpClient.setHttpRequestRetryHandler(new RetryHandler(retries, timeout));
    }

    public void removeAllHeaders() {
        this.clientHeaderMap.clear();
    }

    public void addHeader(String header, String value) {
        this.clientHeaderMap.put(header, value);
    }

    public void removeHeader(String header) {
        this.clientHeaderMap.remove(header);
    }

    public void setBasicAuth(String username, String password) {
        setBasicAuth(username, password, false);
    }

    public void setBasicAuth(String username, String password, boolean preemptive) {
        setBasicAuth(username, password, null, preemptive);
    }

    public void setBasicAuth(String username, String password, AuthScope scope) {
        setBasicAuth(username, password, scope, false);
    }

    public void setBasicAuth(String username, String password, AuthScope scope, boolean preemptive) {
        setCredentials(scope, new UsernamePasswordCredentials(username, password));
        setAuthenticationPreemptive(preemptive);
    }

    public void setCredentials(AuthScope authScope, Credentials credentials) {
        if (credentials == null) {
            log.mo22447d(LOG_TAG, "Provided credentials are null, not setting");
        } else {
            this.httpClient.getCredentialsProvider().setCredentials(authScope == null ? AuthScope.ANY : authScope, credentials);
        }
    }

    public void setAuthenticationPreemptive(boolean isPreemptive) {
        if (isPreemptive) {
            this.httpClient.addRequestInterceptor(new PreemptiveAuthorizationHttpRequestInterceptor(), 0);
        } else {
            this.httpClient.removeRequestInterceptorByClass(PreemptiveAuthorizationHttpRequestInterceptor.class);
        }
    }

    public void clearCredentialsProvider() {
        this.httpClient.getCredentialsProvider().clear();
    }

    public void cancelRequests(Context context, final boolean mayInterruptIfRunning) {
        if (context == null) {
            log.mo22449e(LOG_TAG, "Passed null Context to cancelRequests");
            return;
        }
        final List<RequestHandle> requestList = (List) this.requestMap.get(context);
        this.requestMap.remove(context);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.threadPool.submit(new Runnable() {
                public void run() {
                    AsyncHttpClient.this.cancelRequests(requestList, mayInterruptIfRunning);
                }
            });
        } else {
            cancelRequests(requestList, mayInterruptIfRunning);
        }
    }

    /* access modifiers changed from: private */
    public void cancelRequests(List<RequestHandle> requestList, boolean mayInterruptIfRunning) {
        if (requestList != null) {
            for (RequestHandle requestHandle : requestList) {
                requestHandle.cancel(mayInterruptIfRunning);
            }
        }
    }

    public void cancelAllRequests(boolean mayInterruptIfRunning) {
        for (List<RequestHandle> requestList : this.requestMap.values()) {
            if (requestList != null) {
                for (RequestHandle requestHandle : requestList) {
                    requestHandle.cancel(mayInterruptIfRunning);
                }
            }
        }
        this.requestMap.clear();
    }

    public void cancelRequestsByTAG(Object TAG, boolean mayInterruptIfRunning) {
        if (TAG == null) {
            log.mo22447d(LOG_TAG, "cancelRequestsByTAG, passed TAG is null, cannot proceed");
            return;
        }
        for (List<RequestHandle> requestList : this.requestMap.values()) {
            if (requestList != null) {
                for (RequestHandle requestHandle : requestList) {
                    if (TAG.equals(requestHandle.getTag())) {
                        requestHandle.cancel(mayInterruptIfRunning);
                    }
                }
            }
        }
    }

    public RequestHandle head(String url, ResponseHandlerInterface responseHandler) {
        return head(null, url, null, responseHandler);
    }

    public RequestHandle head(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return head(null, url, params, responseHandler);
    }

    public RequestHandle head(Context context, String url, ResponseHandlerInterface responseHandler) {
        return head(context, url, null, responseHandler);
    }

    public RequestHandle head(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params)), null, responseHandler, context);
    }

    public RequestHandle head(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        HttpHead httpHead = new HttpHead(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params));
        if (headers != null) {
            httpHead.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, httpHead, null, responseHandler, context);
    }

    public RequestHandle get(String url, ResponseHandlerInterface responseHandler) {
        return get(null, url, null, responseHandler);
    }

    public RequestHandle get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return get(null, url, params, responseHandler);
    }

    public RequestHandle get(Context context, String url, ResponseHandlerInterface responseHandler) {
        return get(context, url, null, responseHandler);
    }

    public RequestHandle get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params)), null, responseHandler, context);
    }

    public RequestHandle get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        HttpGet httpGet = new HttpGet(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params));
        if (headers != null) {
            httpGet.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, httpGet, null, responseHandler, context);
    }

    public RequestHandle get(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpGet(URI.create(url).normalize()), entity), contentType, responseHandler, context);
    }

    public RequestHandle post(String url, ResponseHandlerInterface responseHandler) {
        return post(null, url, null, responseHandler);
    }

    public RequestHandle post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return post(null, url, params, responseHandler);
    }

    public RequestHandle post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return post(context, url, paramsToEntity(params, responseHandler), null, responseHandler);
    }

    public RequestHandle post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPost(getURI(url)), entity), contentType, responseHandler, context);
    }

    public RequestHandle post(Context context, String url, Header[] headers, RequestParams params, String contentType, ResponseHandlerInterface responseHandler) {
        HttpPost httpPost = new HttpPost(getURI(url));
        if (params != null) {
            httpPost.setEntity(paramsToEntity(params, responseHandler));
        }
        if (headers != null) {
            httpPost.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, httpPost, contentType, responseHandler, context);
    }

    public RequestHandle post(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        HttpEntityEnclosingRequestBase request = addEntityToRequestBase(new HttpPost(getURI(url)), entity);
        if (headers != null) {
            request.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, request, contentType, responseHandler, context);
    }

    public RequestHandle put(String url, ResponseHandlerInterface responseHandler) {
        return put(null, url, null, responseHandler);
    }

    public RequestHandle put(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return put(null, url, params, responseHandler);
    }

    public RequestHandle put(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return put(context, url, paramsToEntity(params, responseHandler), null, responseHandler);
    }

    public RequestHandle put(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPut(getURI(url)), entity), contentType, responseHandler, context);
    }

    public RequestHandle put(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        HttpEntityEnclosingRequestBase request = addEntityToRequestBase(new HttpPut(getURI(url)), entity);
        if (headers != null) {
            request.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, request, contentType, responseHandler, context);
    }

    public RequestHandle patch(String url, ResponseHandlerInterface responseHandler) {
        return patch(null, url, null, responseHandler);
    }

    public RequestHandle patch(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return patch(null, url, params, responseHandler);
    }

    public RequestHandle patch(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return patch(context, url, paramsToEntity(params, responseHandler), null, responseHandler);
    }

    public RequestHandle patch(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpPatch(getURI(url)), entity), contentType, responseHandler, context);
    }

    public RequestHandle patch(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        HttpEntityEnclosingRequestBase request = addEntityToRequestBase(new HttpPatch(getURI(url)), entity);
        if (headers != null) {
            request.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, request, contentType, responseHandler, context);
    }

    public RequestHandle delete(String url, ResponseHandlerInterface responseHandler) {
        return delete((Context) null, url, responseHandler);
    }

    public RequestHandle delete(Context context, String url, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, new HttpDelete(getURI(url)), null, responseHandler, context);
    }

    public RequestHandle delete(Context context, String url, Header[] headers, ResponseHandlerInterface responseHandler) {
        HttpDelete delete = new HttpDelete(getURI(url));
        if (headers != null) {
            delete.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, delete, null, responseHandler, context);
    }

    public void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        sendRequest(this.httpClient, this.httpContext, new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params)), null, responseHandler, null);
    }

    public RequestHandle delete(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        HttpDelete httpDelete = new HttpDelete(getUrlWithQueryString(this.isUrlEncodingEnabled, url, params));
        if (headers != null) {
            httpDelete.setHeaders(headers);
        }
        return sendRequest(this.httpClient, this.httpContext, httpDelete, null, responseHandler, context);
    }

    public RequestHandle delete(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(new HttpDelete(URI.create(url).normalize()), entity), contentType, responseHandler, context);
    }

    /* access modifiers changed from: protected */
    public AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient client, HttpContext httpContext2, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
        return new AsyncHttpRequest(client, httpContext2, uriRequest, responseHandler);
    }

    /* access modifiers changed from: protected */
    public RequestHandle sendRequest(DefaultHttpClient client, HttpContext httpContext2, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
        List list;
        if (uriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (responseHandler == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!responseHandler.getUseSynchronousMode() || responseHandler.getUsePoolThread()) {
            if (contentType != null) {
                if (!(uriRequest instanceof HttpEntityEnclosingRequestBase) || ((HttpEntityEnclosingRequestBase) uriRequest).getEntity() == null || !uriRequest.containsHeader("Content-Type")) {
                    uriRequest.setHeader("Content-Type", contentType);
                } else {
                    log.mo22462w(LOG_TAG, "Passed contentType will be ignored because HttpEntity sets content type");
                }
            }
            responseHandler.setRequestHeaders(uriRequest.getAllHeaders());
            responseHandler.setRequestURI(uriRequest.getURI());
            AsyncHttpRequest request = newAsyncHttpRequest(client, httpContext2, uriRequest, contentType, responseHandler, context);
            this.threadPool.submit(request);
            RequestHandle requestHandle = new RequestHandle(request);
            if (context != null) {
                synchronized (this.requestMap) {
                    list = (List) this.requestMap.get(context);
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.requestMap.put(context, list);
                    }
                }
                list.add(requestHandle);
                Iterator<RequestHandle> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (((RequestHandle) iterator.next()).shouldBeGarbageCollected()) {
                        iterator.remove();
                    }
                }
            }
            return requestHandle;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    /* access modifiers changed from: protected */
    public URI getURI(String url) {
        return URI.create(url).normalize();
    }

    public void setURLEncodingEnabled(boolean enabled) {
        this.isUrlEncodingEnabled = enabled;
    }

    private HttpEntity paramsToEntity(RequestParams params, ResponseHandlerInterface responseHandler) {
        if (params == null) {
            return null;
        }
        try {
            return params.getEntity(responseHandler);
        } catch (IOException e) {
            if (responseHandler != null) {
                responseHandler.sendFailureMessage(0, null, null, e);
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUrlEncodingEnabled() {
        return this.isUrlEncodingEnabled;
    }

    private HttpEntityEnclosingRequestBase addEntityToRequestBase(HttpEntityEnclosingRequestBase requestBase, HttpEntity entity) {
        if (entity != null) {
            requestBase.setEntity(entity);
        }
        return requestBase;
    }
}
