package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\b\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo24952d2 = {"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, mo24953k = 5, mo24954mv = {1, 1, 15}, mo24956xi = 1, mo24957xs = "kotlin/StandardKt")
/* compiled from: Synchronized.kt */
class StandardKt__SynchronizedKt extends StandardKt__StandardKt {
    /* renamed from: synchronized reason: not valid java name */
    private static final <R> R m1276synchronized(Object lock, Function0<? extends R> block) {
        R invoke;
        synchronized (lock) {
            try {
                invoke = block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        InlineMarker.finallyEnd(1);
        return invoke;
    }
}
