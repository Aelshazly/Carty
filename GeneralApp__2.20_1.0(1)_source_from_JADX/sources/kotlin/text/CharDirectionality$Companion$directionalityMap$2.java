package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, mo24952d2 = {"<anonymous>", "", "", "Lkotlin/text/CharDirectionality;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: CharDirectionality.kt */
final class CharDirectionality$Companion$directionalityMap$2 extends Lambda implements Function0<Map<Integer, ? extends CharDirectionality>> {
    public static final CharDirectionality$Companion$directionalityMap$2 INSTANCE = new CharDirectionality$Companion$directionalityMap$2();

    CharDirectionality$Companion$directionalityMap$2() {
        super(0);
    }

    public final Map<Integer, CharDirectionality> invoke() {
        CharDirectionality[] charDirectionalityArr;
        CharDirectionality[] values = CharDirectionality.values();
        Map destination$iv$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
        for (CharDirectionality charDirectionality : values) {
            destination$iv$iv.put(Integer.valueOf(charDirectionality.getValue()), charDirectionality);
        }
        return destination$iv$iv;
    }
}
