package p008cz.msebera.android.httpclient.protocol;

import java.util.LinkedList;
import java.util.List;
import p008cz.msebera.android.httpclient.HttpRequestInterceptor;
import p008cz.msebera.android.httpclient.HttpResponseInterceptor;

/* renamed from: cz.msebera.android.httpclient.protocol.HttpProcessorBuilder */
public class HttpProcessorBuilder {
    private ChainBuilder<HttpRequestInterceptor> requestChainBuilder;
    private ChainBuilder<HttpResponseInterceptor> responseChainBuilder;

    public static HttpProcessorBuilder create() {
        return new HttpProcessorBuilder();
    }

    HttpProcessorBuilder() {
    }

    private ChainBuilder<HttpRequestInterceptor> getRequestChainBuilder() {
        if (this.requestChainBuilder == null) {
            this.requestChainBuilder = new ChainBuilder<>();
        }
        return this.requestChainBuilder;
    }

    private ChainBuilder<HttpResponseInterceptor> getResponseChainBuilder() {
        if (this.responseChainBuilder == null) {
            this.responseChainBuilder = new ChainBuilder<>();
        }
        return this.responseChainBuilder;
    }

    public HttpProcessorBuilder addFirst(HttpRequestInterceptor e) {
        if (e == null) {
            return this;
        }
        getRequestChainBuilder().addFirst(e);
        return this;
    }

    public HttpProcessorBuilder addLast(HttpRequestInterceptor e) {
        if (e == null) {
            return this;
        }
        getRequestChainBuilder().addLast(e);
        return this;
    }

    public HttpProcessorBuilder add(HttpRequestInterceptor e) {
        return addLast(e);
    }

    public HttpProcessorBuilder addAllFirst(HttpRequestInterceptor... e) {
        if (e == null) {
            return this;
        }
        getRequestChainBuilder().addAllFirst((E[]) e);
        return this;
    }

    public HttpProcessorBuilder addAllLast(HttpRequestInterceptor... e) {
        if (e == null) {
            return this;
        }
        getRequestChainBuilder().addAllLast((E[]) e);
        return this;
    }

    public HttpProcessorBuilder addAll(HttpRequestInterceptor... e) {
        return addAllLast(e);
    }

    public HttpProcessorBuilder addFirst(HttpResponseInterceptor e) {
        if (e == null) {
            return this;
        }
        getResponseChainBuilder().addFirst(e);
        return this;
    }

    public HttpProcessorBuilder addLast(HttpResponseInterceptor e) {
        if (e == null) {
            return this;
        }
        getResponseChainBuilder().addLast(e);
        return this;
    }

    public HttpProcessorBuilder add(HttpResponseInterceptor e) {
        return addLast(e);
    }

    public HttpProcessorBuilder addAllFirst(HttpResponseInterceptor... e) {
        if (e == null) {
            return this;
        }
        getResponseChainBuilder().addAllFirst((E[]) e);
        return this;
    }

    public HttpProcessorBuilder addAllLast(HttpResponseInterceptor... e) {
        if (e == null) {
            return this;
        }
        getResponseChainBuilder().addAllLast((E[]) e);
        return this;
    }

    public HttpProcessorBuilder addAll(HttpResponseInterceptor... e) {
        return addAllLast(e);
    }

    public HttpProcessor build() {
        ChainBuilder<HttpRequestInterceptor> chainBuilder = this.requestChainBuilder;
        LinkedList linkedList = null;
        List build = chainBuilder != null ? chainBuilder.build() : null;
        ChainBuilder<HttpResponseInterceptor> chainBuilder2 = this.responseChainBuilder;
        if (chainBuilder2 != null) {
            linkedList = chainBuilder2.build();
        }
        return new ImmutableHttpProcessor(build, (List<HttpResponseInterceptor>) linkedList);
    }
}
