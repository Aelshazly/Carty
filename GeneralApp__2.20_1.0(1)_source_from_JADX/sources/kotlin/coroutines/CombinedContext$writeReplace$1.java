package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo24952d2 = {"<anonymous>", "", "<anonymous parameter 0>", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke", "(Lkotlin/Unit;Lkotlin/coroutines/CoroutineContext$Element;)V"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: CoroutineContextImpl.kt */
final class CombinedContext$writeReplace$1 extends Lambda implements Function2<Unit, Element, Unit> {
    final /* synthetic */ CoroutineContext[] $elements;
    final /* synthetic */ IntRef $index;

    CombinedContext$writeReplace$1(CoroutineContext[] coroutineContextArr, IntRef intRef) {
        this.$elements = coroutineContextArr;
        this.$index = intRef;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Unit) obj, (Element) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Unit $noName_0, Element element) {
        Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(element, "element");
        CoroutineContext[] coroutineContextArr = this.$elements;
        IntRef intRef = this.$index;
        int i = intRef.element;
        intRef.element = i + 1;
        coroutineContextArr[i] = element;
    }
}
