package p008cz.msebera.android.httpclient.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.client.CookieStore;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.config.Lookup;
import p008cz.msebera.android.httpclient.conn.routing.RouteInfo;
import p008cz.msebera.android.httpclient.cookie.Cookie;
import p008cz.msebera.android.httpclient.cookie.CookieOrigin;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import p008cz.msebera.android.httpclient.cookie.SetCookie2;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.protocol.HttpContext;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.TextUtils;

/* renamed from: cz.msebera.android.httpclient.client.protocol.RequestAddCookies */
public class RequestAddCookies implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        String policy;
        int i;
        Lookup lookup;
        CookieStore cookieStore;
        HttpRequest httpRequest = request;
        HttpContext httpContext = context;
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        String method = request.getRequestLine().getMethod();
        if (!method.equalsIgnoreCase("CONNECT")) {
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            CookieStore cookieStore2 = clientContext.getCookieStore();
            if (cookieStore2 == null) {
                this.log.debug("Cookie store not specified in HTTP context");
                return;
            }
            Lookup cookieSpecRegistry = clientContext.getCookieSpecRegistry();
            if (cookieSpecRegistry == null) {
                this.log.debug("CookieSpec registry not specified in HTTP context");
                return;
            }
            HttpHost targetHost = clientContext.getTargetHost();
            if (targetHost == null) {
                this.log.debug("Target host not set in the context");
                return;
            }
            RouteInfo route = clientContext.getHttpRoute();
            if (route == null) {
                this.log.debug("Connection route not set in the context");
                return;
            }
            String policy2 = clientContext.getRequestConfig().getCookieSpec();
            if (policy2 == null) {
                policy = "best-match";
            } else {
                policy = policy2;
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog httpClientAndroidLog = this.log;
                StringBuilder sb = new StringBuilder();
                sb.append("CookieSpec selected: ");
                sb.append(policy);
                httpClientAndroidLog.debug(sb.toString());
            }
            URI requestURI = null;
            if (httpRequest instanceof HttpUriRequest) {
                requestURI = ((HttpUriRequest) httpRequest).getURI();
            } else {
                try {
                    requestURI = new URI(request.getRequestLine().getUri());
                } catch (URISyntaxException e) {
                }
            }
            String path = requestURI != null ? requestURI.getPath() : null;
            String hostName = targetHost.getHostName();
            int port = targetHost.getPort();
            if (port < 0) {
                port = route.getTargetHost().getPort();
            }
            if (port >= 0) {
                String str = method;
                i = port;
            } else {
                String str2 = method;
                i = 0;
            }
            if (!TextUtils.isEmpty(path)) {
                String str3 = path;
            } else {
                String str4 = path;
                path = "/";
            }
            HttpHost httpHost = targetHost;
            CookieOrigin cookieOrigin = new CookieOrigin(hostName, i, path, route.isSecure());
            CookieSpecProvider provider = (CookieSpecProvider) cookieSpecRegistry.lookup(policy);
            if (provider != null) {
                CookieSpec cookieSpec = provider.create(clientContext);
                CookieSpecProvider cookieSpecProvider = provider;
                List<Cookie> arrayList = new ArrayList<>(cookieStore2.getCookies());
                List<Cookie> matchedCookies = new ArrayList<>();
                Date now = new Date();
                for (Cookie cookie : arrayList) {
                    ArrayList arrayList2 = arrayList;
                    HttpClientContext clientContext2 = clientContext;
                    Date now2 = now;
                    boolean isExpired = cookie.isExpired(now2);
                    Date now3 = now2;
                    String str5 = "Cookie ";
                    if (isExpired) {
                        cookieStore = cookieStore2;
                        lookup = cookieSpecRegistry;
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog2 = this.log;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str5);
                            sb2.append(cookie);
                            sb2.append(" expired");
                            httpClientAndroidLog2.debug(sb2.toString());
                        }
                    } else if (cookieSpec.match(cookie, cookieOrigin)) {
                        cookieStore = cookieStore2;
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog httpClientAndroidLog3 = this.log;
                            lookup = cookieSpecRegistry;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str5);
                            sb3.append(cookie);
                            sb3.append(" match ");
                            sb3.append(cookieOrigin);
                            httpClientAndroidLog3.debug(sb3.toString());
                        } else {
                            lookup = cookieSpecRegistry;
                        }
                        matchedCookies.add(cookie);
                    } else {
                        cookieStore = cookieStore2;
                        lookup = cookieSpecRegistry;
                    }
                    cookieStore2 = cookieStore;
                    clientContext = clientContext2;
                    arrayList = arrayList2;
                    now = now3;
                    cookieSpecRegistry = lookup;
                }
                List<Cookie> cookies = arrayList;
                HttpClientContext httpClientContext = clientContext;
                Lookup lookup2 = cookieSpecRegistry;
                Date date = now;
                CookieStore cookieStore3 = cookieStore2;
                if (!matchedCookies.isEmpty()) {
                    for (Header header : cookieSpec.formatCookies(matchedCookies)) {
                        httpRequest.addHeader(header);
                    }
                }
                int ver = cookieSpec.getVersion();
                if (ver > 0) {
                    boolean needVersionHeader = false;
                    for (Cookie cookie2 : matchedCookies) {
                        if (ver != cookie2.getVersion() || !(cookie2 instanceof SetCookie2)) {
                            needVersionHeader = true;
                        }
                    }
                    if (needVersionHeader) {
                        Header header2 = cookieSpec.getVersionHeader();
                        if (header2 != null) {
                            httpRequest.addHeader(header2);
                        }
                    }
                }
                httpContext.setAttribute("http.cookie-spec", cookieSpec);
                httpContext.setAttribute("http.cookie-origin", cookieOrigin);
                return;
            }
            HttpClientContext httpClientContext2 = clientContext;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Unsupported cookie policy: ");
            sb4.append(policy);
            throw new HttpException(sb4.toString());
        }
    }
}
