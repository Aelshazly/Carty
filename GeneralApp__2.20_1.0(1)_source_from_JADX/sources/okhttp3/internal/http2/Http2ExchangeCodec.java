package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Sink;
import okio.Source;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0001&B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\fH\u0016J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "realConnection", "Lokhttp3/internal/connection/RealConnection;", "chain", "Lokhttp3/Interceptor$Chain;", "connection", "Lokhttp3/internal/http2/Http2Connection;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokhttp3/Interceptor$Chain;Lokhttp3/internal/http2/Http2Connection;)V", "canceled", "", "protocol", "Lokhttp3/Protocol;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Http2ExchangeCodec.kt */
public final class Http2ExchangeCodec implements ExchangeCodec {
    private static final String CONNECTION = "connection";
    public static final Companion Companion = new Companion(null);
    private static final String ENCODING = "encoding";
    private static final String HOST = "host";
    /* access modifiers changed from: private */
    public static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f261TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    /* access modifiers changed from: private */
    public static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f261TE, TRANSFER_ENCODING, ENCODING, UPGRADE);
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";

    /* renamed from: TE */
    private static final String f261TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String UPGRADE = "upgrade";
    private volatile boolean canceled;
    private final Chain chain;
    private final Http2Connection connection;
    private final Protocol protocol;
    private final RealConnection realConnection;
    private volatile Http2Stream stream;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo24952d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", "", "()V", "CONNECTION", "", "ENCODING", "HOST", "HTTP_2_SKIPPED_REQUEST_HEADERS", "", "HTTP_2_SKIPPED_RESPONSE_HEADERS", "KEEP_ALIVE", "PROXY_CONNECTION", "TE", "TRANSFER_ENCODING", "UPGRADE", "http2HeadersList", "Lokhttp3/internal/http2/Header;", "request", "Lokhttp3/Request;", "readHttp2HeadersList", "Lokhttp3/Response$Builder;", "headerBlock", "Lokhttp3/Headers;", "protocol", "Lokhttp3/Protocol;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Http2ExchangeCodec.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final List<Header> http2HeadersList(Request request) {
            Intrinsics.checkParameterIsNotNull(request, "request");
            Headers headers = request.headers();
            ArrayList result = new ArrayList(headers.size() + 4);
            result.add(new Header(Header.TARGET_METHOD, request.method()));
            result.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
            String host = request.header("Host");
            if (host != null) {
                result.add(new Header(Header.TARGET_AUTHORITY, host));
            }
            result.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
            int i = 0;
            int size = headers.size();
            while (i < size) {
                String name = headers.name(i);
                Locale locale = Locale.US;
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                if (name != null) {
                    String name2 = name.toLowerCase(locale);
                    Intrinsics.checkExpressionValueIsNotNull(name2, "(this as java.lang.String).toLowerCase(locale)");
                    if (!Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name2) || (Intrinsics.areEqual((Object) name2, (Object) Http2ExchangeCodec.f261TE) && Intrinsics.areEqual((Object) headers.value(i), (Object) "trailers"))) {
                        result.add(new Header(name2, headers.value(i)));
                    }
                    i++;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            return result;
        }

        public final Builder readHttp2HeadersList(Headers headerBlock, Protocol protocol) {
            Intrinsics.checkParameterIsNotNull(headerBlock, "headerBlock");
            Intrinsics.checkParameterIsNotNull(protocol, "protocol");
            StatusLine statusLine = null;
            Headers.Builder headersBuilder = new Headers.Builder();
            int size = headerBlock.size();
            for (int i = 0; i < size; i++) {
                String name = headerBlock.name(i);
                String value = headerBlock.value(i);
                if (Intrinsics.areEqual((Object) name, (Object) Header.RESPONSE_STATUS_UTF8)) {
                    okhttp3.internal.http.StatusLine.Companion companion = StatusLine.Companion;
                    StringBuilder sb = new StringBuilder();
                    sb.append("HTTP/1.1 ");
                    sb.append(value);
                    statusLine = companion.parse(sb.toString());
                } else if (!Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    headersBuilder.addLenient$okhttp(name, value);
                }
            }
            if (statusLine != null) {
                return new Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(headersBuilder.build());
            }
            throw new ProtocolException("Expected ':status' header not present");
        }
    }

    public Http2ExchangeCodec(OkHttpClient client, RealConnection realConnection2, Chain chain2, Http2Connection connection2) {
        Protocol protocol2;
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(realConnection2, "realConnection");
        Intrinsics.checkParameterIsNotNull(chain2, "chain");
        Intrinsics.checkParameterIsNotNull(connection2, CONNECTION);
        this.realConnection = realConnection2;
        this.chain = chain2;
        this.connection = connection2;
        if (client.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            protocol2 = Protocol.H2_PRIOR_KNOWLEDGE;
        } else {
            protocol2 = Protocol.HTTP_2;
        }
        this.protocol = protocol2;
    }

    public RealConnection connection() {
        return this.realConnection;
    }

    public Sink createRequestBody(Request request, long contentLength) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Http2Stream http2Stream = this.stream;
        if (http2Stream == null) {
            Intrinsics.throwNpe();
        }
        return http2Stream.getSink();
    }

    public void writeRequestHeaders(Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        if (this.stream == null) {
            this.stream = this.connection.newStream(Companion.http2HeadersList(request), request.body() != null);
            if (this.canceled) {
                Http2Stream http2Stream = this.stream;
                if (http2Stream == null) {
                    Intrinsics.throwNpe();
                }
                http2Stream.closeLater(ErrorCode.CANCEL);
                throw new IOException("Canceled");
            }
            Http2Stream http2Stream2 = this.stream;
            if (http2Stream2 == null) {
                Intrinsics.throwNpe();
            }
            http2Stream2.readTimeout().timeout((long) this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            Http2Stream http2Stream3 = this.stream;
            if (http2Stream3 == null) {
                Intrinsics.throwNpe();
            }
            http2Stream3.writeTimeout().timeout((long) this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public void flushRequest() {
        this.connection.flush();
    }

    public void finishRequest() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream == null) {
            Intrinsics.throwNpe();
        }
        http2Stream.getSink().close();
    }

    public Builder readResponseHeaders(boolean expectContinue) {
        Http2Stream http2Stream = this.stream;
        if (http2Stream == null) {
            Intrinsics.throwNpe();
        }
        Builder responseBuilder = Companion.readHttp2HeadersList(http2Stream.takeHeaders(), this.protocol);
        if (!expectContinue || responseBuilder.getCode$okhttp() != 100) {
            return responseBuilder;
        }
        return null;
    }

    public long reportedContentLength(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        return Util.headersContentLength(response);
    }

    public Source openResponseBodySource(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        Http2Stream http2Stream = this.stream;
        if (http2Stream == null) {
            Intrinsics.throwNpe();
        }
        return http2Stream.getSource$okhttp();
    }

    public Headers trailers() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream == null) {
            Intrinsics.throwNpe();
        }
        return http2Stream.trailers();
    }

    public void cancel() {
        this.canceled = true;
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }
}
