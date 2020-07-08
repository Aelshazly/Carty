package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo24952d2 = {"<anonymous>", "Lkotlin/coroutines/experimental/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: CoroutineContext.kt */
final class CoroutineContext$plus$1 extends Lambda implements Function2<CoroutineContext, Element, CoroutineContext> {
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    public final CoroutineContext invoke(CoroutineContext acc, Element element) {
        CombinedContext combinedContext;
        Intrinsics.checkParameterIsNotNull(acc, "acc");
        Intrinsics.checkParameterIsNotNull(element, "element");
        CoroutineContext removed = acc.minusKey(element.getKey());
        if (removed == EmptyCoroutineContext.INSTANCE) {
            return element;
        }
        ContinuationInterceptor interceptor = (ContinuationInterceptor) removed.get(ContinuationInterceptor.Key);
        if (interceptor == null) {
            combinedContext = new CombinedContext(removed, element);
        } else {
            CoroutineContext left = removed.minusKey(ContinuationInterceptor.Key);
            if (left == EmptyCoroutineContext.INSTANCE) {
                combinedContext = new CombinedContext(element, interceptor);
            } else {
                combinedContext = new CombinedContext(new CombinedContext(left, element), interceptor);
            }
        }
        return combinedContext;
    }
}
