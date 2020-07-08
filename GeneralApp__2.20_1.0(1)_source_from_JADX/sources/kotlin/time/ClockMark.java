package kotlin.time;

import kotlin.Metadata;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u001b\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, mo24952d2 = {"Lkotlin/time/ClockMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(D)Lkotlin/time/ClockMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Clock.kt */
public abstract class ClockMark {
    public abstract double elapsedNow();

    /* renamed from: plus-LRDsOJo reason: not valid java name */
    public ClockMark m2188plusLRDsOJo(double duration) {
        return new AdjustedClockMark(this, duration, null);
    }

    /* renamed from: minus-LRDsOJo reason: not valid java name */
    public ClockMark m2187minusLRDsOJo(double duration) {
        return m2188plusLRDsOJo(Duration.m2232unaryMinusimpl(duration));
    }

    public final boolean hasPassedNow() {
        return !Duration.m2212isNegativeimpl(elapsedNow());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m2212isNegativeimpl(elapsedNow());
    }
}
