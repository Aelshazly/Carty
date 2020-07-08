package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo24952d2 = {"<anonymous>", "", "it", "Lkotlin/ranges/IntRange;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: Strings.kt */
final class StringsKt__StringsKt$splitToSequence$2 extends Lambda implements Function1<IntRange, String> {
    final /* synthetic */ CharSequence $this_splitToSequence;

    StringsKt__StringsKt$splitToSequence$2(CharSequence charSequence) {
        this.$this_splitToSequence = charSequence;
        super(1);
    }

    public final String invoke(IntRange it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        return StringsKt.substring(this.$this_splitToSequence, it);
    }
}
