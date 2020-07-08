package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, mo24952d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Duration.kt */
public final class Duration implements Comparable<Duration> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final double INFINITE = m2191constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());
    /* access modifiers changed from: private */
    public static final double ZERO = m2191constructorimpl(0.0d);
    private final double value;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo24952d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Duration.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double convert(double value, TimeUnit sourceUnit, TimeUnit targetUnit) {
            Intrinsics.checkParameterIsNotNull(sourceUnit, "sourceUnit");
            Intrinsics.checkParameterIsNotNull(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }
    }

    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ Duration m2189boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m2195equalsimpl(double d, Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).m2234unboximpl()) == 0;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m2196equalsimpl0(double d, double d2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m2209hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public static /* synthetic */ void hoursComponent$annotations() {
    }

    public static /* synthetic */ void minutesComponent$annotations() {
    }

    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: compareTo-LRDsOJo reason: not valid java name */
    public int m2233compareToLRDsOJo(double d) {
        return m2190compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object obj) {
        return m2195equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2209hashCodeimpl(this.value);
    }

    public String toString() {
        return m2229toStringimpl(this.value);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ double m2234unboximpl() {
        return this.value;
    }

    private /* synthetic */ Duration(double value2) {
        this.value = value2;
    }

    /* renamed from: constructor-impl reason: not valid java name */
    public static double m2191constructorimpl(double value2) {
        return value2;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m2233compareToLRDsOJo(((Duration) obj).m2234unboximpl());
    }

    /* renamed from: unaryMinus-impl reason: not valid java name */
    public static final double m2232unaryMinusimpl(double $this) {
        return m2191constructorimpl(-$this);
    }

    /* renamed from: plus-LRDsOJo reason: not valid java name */
    public static final double m2215plusLRDsOJo(double $this, double other) {
        return m2191constructorimpl($this + other);
    }

    /* renamed from: minus-LRDsOJo reason: not valid java name */
    public static final double m2214minusLRDsOJo(double $this, double other) {
        return m2191constructorimpl($this - other);
    }

    /* renamed from: times-impl reason: not valid java name */
    public static final double m2218timesimpl(double $this, int scale) {
        return m2191constructorimpl(((double) scale) * $this);
    }

    /* renamed from: times-impl reason: not valid java name */
    public static final double m2217timesimpl(double $this, double scale) {
        return m2191constructorimpl($this * scale);
    }

    /* renamed from: div-impl reason: not valid java name */
    public static final double m2194divimpl(double $this, int scale) {
        return m2191constructorimpl($this / ((double) scale));
    }

    /* renamed from: div-impl reason: not valid java name */
    public static final double m2193divimpl(double $this, double scale) {
        return m2191constructorimpl($this / scale);
    }

    /* renamed from: div-LRDsOJo reason: not valid java name */
    public static final double m2192divLRDsOJo(double $this, double other) {
        return $this / other;
    }

    /* renamed from: isNegative-impl reason: not valid java name */
    public static final boolean m2212isNegativeimpl(double $this) {
        return $this < ((double) 0);
    }

    /* renamed from: isPositive-impl reason: not valid java name */
    public static final boolean m2213isPositiveimpl(double $this) {
        return $this > ((double) 0);
    }

    /* renamed from: isInfinite-impl reason: not valid java name */
    public static final boolean m2211isInfiniteimpl(double $this) {
        return Double.isInfinite($this);
    }

    /* renamed from: isFinite-impl reason: not valid java name */
    public static final boolean m2210isFiniteimpl(double $this) {
        return !Double.isInfinite($this) && !Double.isNaN($this);
    }

    /* renamed from: getAbsoluteValue-impl reason: not valid java name */
    public static final double m2197getAbsoluteValueimpl(double $this) {
        return m2212isNegativeimpl($this) ? m2232unaryMinusimpl($this) : $this;
    }

    /* renamed from: compareTo-LRDsOJo reason: not valid java name */
    public static int m2190compareToLRDsOJo(double $this, double other) {
        return Double.compare($this, other);
    }

    /* renamed from: toComponents-impl reason: not valid java name */
    public static final <T> T m2222toComponentsimpl(double $this, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m2199getInDaysimpl($this)), Integer.valueOf(m2198getHoursComponentimpl($this)), Integer.valueOf(m2206getMinutesComponentimpl($this)), Integer.valueOf(m2208getSecondsComponentimpl($this)), Integer.valueOf(m2207getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl reason: not valid java name */
    public static final <T> T m2221toComponentsimpl(double $this, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m2200getInHoursimpl($this)), Integer.valueOf(m2206getMinutesComponentimpl($this)), Integer.valueOf(m2208getSecondsComponentimpl($this)), Integer.valueOf(m2207getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl reason: not valid java name */
    public static final <T> T m2220toComponentsimpl(double $this, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m2203getInMinutesimpl($this)), Integer.valueOf(m2208getSecondsComponentimpl($this)), Integer.valueOf(m2207getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl reason: not valid java name */
    public static final <T> T m2219toComponentsimpl(double $this, Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Long.valueOf((long) m2205getInSecondsimpl($this)), Integer.valueOf(m2207getNanosecondsComponentimpl($this)));
    }

    /* renamed from: getHoursComponent-impl reason: not valid java name */
    public static final int m2198getHoursComponentimpl(double $this) {
        return (int) (m2200getInHoursimpl($this) % ((double) 24));
    }

    /* renamed from: getMinutesComponent-impl reason: not valid java name */
    public static final int m2206getMinutesComponentimpl(double $this) {
        return (int) (m2203getInMinutesimpl($this) % ((double) 60));
    }

    /* renamed from: getSecondsComponent-impl reason: not valid java name */
    public static final int m2208getSecondsComponentimpl(double $this) {
        return (int) (m2205getInSecondsimpl($this) % ((double) 60));
    }

    /* renamed from: getNanosecondsComponent-impl reason: not valid java name */
    public static final int m2207getNanosecondsComponentimpl(double $this) {
        return (int) (m2204getInNanosecondsimpl($this) % 1.0E9d);
    }

    /* renamed from: toDouble-impl reason: not valid java name */
    public static final double m2223toDoubleimpl(double $this, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return DurationUnitKt.convertDurationUnit($this, DurationKt.getStorageUnit(), unit);
    }

    /* renamed from: toLong-impl reason: not valid java name */
    public static final long m2226toLongimpl(double $this, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (long) m2223toDoubleimpl($this, unit);
    }

    /* renamed from: toInt-impl reason: not valid java name */
    public static final int m2224toIntimpl(double $this, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (int) m2223toDoubleimpl($this, unit);
    }

    /* renamed from: getInDays-impl reason: not valid java name */
    public static final double m2199getInDaysimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl reason: not valid java name */
    public static final double m2200getInHoursimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl reason: not valid java name */
    public static final double m2203getInMinutesimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl reason: not valid java name */
    public static final double m2205getInSecondsimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl reason: not valid java name */
    public static final double m2202getInMillisecondsimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl reason: not valid java name */
    public static final double m2201getInMicrosecondsimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl reason: not valid java name */
    public static final double m2204getInNanosecondsimpl(double $this) {
        return m2223toDoubleimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongNanoseconds-impl reason: not valid java name */
    public static final long m2228toLongNanosecondsimpl(double $this) {
        return m2226toLongimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongMilliseconds-impl reason: not valid java name */
    public static final long m2227toLongMillisecondsimpl(double $this) {
        return m2226toLongimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: toString-impl reason: not valid java name */
    public static String m2229toStringimpl(double $this) {
        TimeUnit unit;
        String str;
        if (m2211isInfiniteimpl($this)) {
            return String.valueOf($this);
        }
        if ($this == 0.0d) {
            return "0s";
        }
        double absNs = m2204getInNanosecondsimpl(m2197getAbsoluteValueimpl($this));
        boolean scientific = false;
        int maxDecimals = 0;
        if (absNs < 1.0E-6d) {
            unit = TimeUnit.SECONDS;
            TimeUnit timeUnit = unit;
            scientific = true;
        } else if (absNs < ((double) 1)) {
            unit = TimeUnit.NANOSECONDS;
            TimeUnit timeUnit2 = unit;
            maxDecimals = 7;
        } else if (absNs < 1000.0d) {
            unit = TimeUnit.NANOSECONDS;
        } else if (absNs < 1000000.0d) {
            unit = TimeUnit.MICROSECONDS;
        } else if (absNs < 1.0E9d) {
            unit = TimeUnit.MILLISECONDS;
        } else if (absNs < 1.0E12d) {
            unit = TimeUnit.SECONDS;
        } else if (absNs < 6.0E13d) {
            unit = TimeUnit.MINUTES;
        } else if (absNs < 3.6E15d) {
            unit = TimeUnit.HOURS;
        } else if (absNs < 8.64E20d) {
            unit = TimeUnit.DAYS;
        } else {
            unit = TimeUnit.DAYS;
            TimeUnit timeUnit3 = unit;
            scientific = true;
        }
        double value2 = m2223toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        if (scientific) {
            str = FormatToDecimalsKt.formatScientific(value2);
        } else if (maxDecimals > 0) {
            str = FormatToDecimalsKt.formatUpToDecimals(value2, maxDecimals);
        } else {
            str = FormatToDecimalsKt.formatToExactDecimals(value2, m2216precisionimpl($this, Math.abs(value2)));
        }
        sb.append(str);
        sb.append(DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: precision-impl reason: not valid java name */
    private static final int m2216precisionimpl(double $this, double value2) {
        if (value2 < ((double) 1)) {
            return 3;
        }
        if (value2 < ((double) 10)) {
            return 2;
        }
        if (value2 < ((double) 100)) {
            return 1;
        }
        return 0;
    }

    /* renamed from: toString-impl$default reason: not valid java name */
    public static /* synthetic */ String m2231toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m2230toStringimpl(d, timeUnit, i);
    }

    /* renamed from: toString-impl reason: not valid java name */
    public static final String m2230toStringimpl(double $this, TimeUnit unit, int decimals) {
        String str;
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (!(decimals >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("decimals must be not negative, but was ");
            sb.append(decimals);
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (m2211isInfiniteimpl($this)) {
            return String.valueOf($this);
        } else {
            double number = m2223toDoubleimpl($this, unit);
            StringBuilder sb2 = new StringBuilder();
            if (Math.abs(number) < 1.0E14d) {
                str = FormatToDecimalsKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12));
            } else {
                str = FormatToDecimalsKt.formatScientific(number);
            }
            sb2.append(str);
            sb2.append(DurationUnitKt.shortName(unit));
            return sb2.toString();
        }
    }

    /* renamed from: toIsoString-impl reason: not valid java name */
    public static final String m2225toIsoStringimpl(double $this) {
        StringBuilder sb = new StringBuilder();
        StringBuilder $this$buildString = sb;
        if (m2212isNegativeimpl($this)) {
            $this$buildString.append('-');
        }
        $this$buildString.append("PT");
        double $this$iv = m2197getAbsoluteValueimpl($this);
        int hours = (int) m2200getInHoursimpl($this$iv);
        int minutes = m2206getMinutesComponentimpl($this$iv);
        int seconds = m2208getSecondsComponentimpl($this$iv);
        int nanoseconds = m2207getNanosecondsComponentimpl($this$iv);
        boolean hasMinutes = true;
        boolean hasHours = hours != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        if (minutes == 0 && (!hasSeconds || !hasHours)) {
            hasMinutes = false;
        }
        if (hasHours) {
            $this$buildString.append(hours);
            $this$buildString.append('H');
        }
        if (hasMinutes) {
            $this$buildString.append(minutes);
            $this$buildString.append('M');
        }
        if (hasSeconds || (!hasHours && !hasMinutes)) {
            $this$buildString.append(seconds);
            if (nanoseconds != 0) {
                $this$buildString.append('.');
                String nss = StringsKt.padStart(String.valueOf(nanoseconds), 9, '0');
                if (nanoseconds % 1000000 == 0) {
                    double d = $this$iv;
                    $this$buildString.append(nss, 0, 3);
                } else {
                    if (nanoseconds % 1000 == 0) {
                        $this$buildString.append(nss, 0, 6);
                    } else {
                        $this$buildString.append(nss);
                    }
                }
            } else {
                double d2 = $this$iv;
            }
            $this$buildString.append('S');
        } else {
            double d3 = $this$iv;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
