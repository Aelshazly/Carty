package okhttp3.internal.http;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import p008cz.msebera.android.httpclient.client.methods.HttpPatch;
import p008cz.msebera.android.httpclient.client.methods.HttpPost;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, mo24952d2 = {"Lokhttp3/internal/http/HttpMethod;", "", "()V", "invalidatesCache", "", "method", "", "permitsRequestBody", "redirectsToGet", "redirectsWithBody", "requiresRequestBody", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: HttpMethod.kt */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public final boolean invalidatesCache(String method) {
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        return Intrinsics.areEqual((Object) method, (Object) HttpPost.METHOD_NAME) || Intrinsics.areEqual((Object) method, (Object) HttpPatch.METHOD_NAME) || Intrinsics.areEqual((Object) method, (Object) "PUT") || Intrinsics.areEqual((Object) method, (Object) "DELETE") || Intrinsics.areEqual((Object) method, (Object) "MOVE");
    }

    @JvmStatic
    public static final boolean requiresRequestBody(String method) {
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        return Intrinsics.areEqual((Object) method, (Object) HttpPost.METHOD_NAME) || Intrinsics.areEqual((Object) method, (Object) "PUT") || Intrinsics.areEqual((Object) method, (Object) HttpPatch.METHOD_NAME) || Intrinsics.areEqual((Object) method, (Object) "PROPPATCH") || Intrinsics.areEqual((Object) method, (Object) "REPORT");
    }

    @JvmStatic
    public static final boolean permitsRequestBody(String method) {
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        return !Intrinsics.areEqual((Object) method, (Object) "GET") && !Intrinsics.areEqual((Object) method, (Object) "HEAD");
    }

    public final boolean redirectsWithBody(String method) {
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        return Intrinsics.areEqual((Object) method, (Object) "PROPFIND");
    }

    public final boolean redirectsToGet(String method) {
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        return !Intrinsics.areEqual((Object) method, (Object) "PROPFIND");
    }
}
