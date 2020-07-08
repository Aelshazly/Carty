package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, mo24952d2 = {"<anonymous>", "", "T", "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: Comparisons.kt */
public final class ComparisonsKt__ComparisonsKt$thenBy$2<T> implements Comparator<T> {
    final /* synthetic */ Comparator $comparator;
    final /* synthetic */ Function1 $selector;
    final /* synthetic */ Comparator $this_thenBy;

    public ComparisonsKt__ComparisonsKt$thenBy$2(Comparator comparator, Comparator comparator2, Function1 function1) {
        this.$this_thenBy = comparator;
        this.$comparator = comparator2;
        this.$selector = function1;
    }

    public final int compare(T a, T b) {
        int previousCompare = this.$this_thenBy.compare(a, b);
        return previousCompare != 0 ? previousCompare : this.$comparator.compare(this.$selector.invoke(a), this.$selector.invoke(b));
    }
}