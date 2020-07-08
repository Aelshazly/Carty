package okhttp3;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, mo24952d2 = {"okhttp3/Interceptor$Companion$invoke$1", "Lokhttp3/Interceptor;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Interceptor.kt */
public final class OkHttpClient$Builder$addInterceptor$$inlined$invoke$1 implements Interceptor {
    final /* synthetic */ Function1 $block$inlined;

    public OkHttpClient$Builder$addInterceptor$$inlined$invoke$1(Function1 function1) {
        this.$block$inlined = function1;
    }

    public Response intercept(Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        return (Response) this.$block$inlined.invoke(chain);
    }
}