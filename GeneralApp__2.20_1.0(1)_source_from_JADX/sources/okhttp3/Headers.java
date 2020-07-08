package okhttp3;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 '2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0002&'B\u0015\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u0003J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0012\u001a\u00020\u0003H\u0007J\b\u0010\u0017\u001a\u00020\tH\u0016J\u001b\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0019H\u0002J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u001fJ\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\"0!J\b\u0010#\u001a\u00020\u0003H\u0016J\u000e\u0010$\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\u0006\u0010\u0012\u001a\u00020\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006("}, mo24952d2 = {"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "namesAndValues", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "size", "", "()I", "byteCount", "", "equals", "", "other", "", "get", "name", "getDate", "Ljava/util/Date;", "getInstant", "Ljava/time/Instant;", "hashCode", "iterator", "", "index", "names", "", "newBuilder", "Lokhttp3/Headers$Builder;", "-deprecated_size", "toMultimap", "", "", "toString", "value", "values", "Builder", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Headers.kt */
public final class Headers implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    private final String[] namesAndValues;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005J\u0018\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rJ\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\u001d\u0010\u0011\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\u0016\u0010\u0013\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0010J\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0005J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rH\u0002J\u0019\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, mo24952d2 = {"Lokhttp3/Headers$Builder;", "", "()V", "namesAndValues", "", "", "getNamesAndValues$okhttp", "()Ljava/util/List;", "add", "line", "name", "value", "Ljava/time/Instant;", "Ljava/util/Date;", "addAll", "headers", "Lokhttp3/Headers;", "addLenient", "addLenient$okhttp", "addUnsafeNonAscii", "build", "get", "removeAll", "set", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Headers.kt */
    public static final class Builder {
        private final List<String> namesAndValues = new ArrayList(20);

        public final List<String> getNamesAndValues$okhttp() {
            return this.namesAndValues;
        }

        public final Builder addLenient$okhttp(String line) {
            Intrinsics.checkParameterIsNotNull(line, "line");
            Builder $this$apply = this;
            int index = StringsKt.indexOf$default((CharSequence) line, ':', 1, false, 4, (Object) null);
            String str = "(this as java.lang.String).substring(startIndex)";
            if (index != -1) {
                String substring = line.substring(0, index);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String substring2 = line.substring(index + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring2, str);
                $this$apply.addLenient$okhttp(substring, substring2);
            } else {
                String str2 = "";
                if (line.charAt(0) == ':') {
                    String substring3 = line.substring(1);
                    Intrinsics.checkExpressionValueIsNotNull(substring3, str);
                    $this$apply.addLenient$okhttp(str2, substring3);
                } else {
                    $this$apply.addLenient$okhttp(str2, line);
                }
            }
            return this;
        }

        public final Builder add(String line) {
            Intrinsics.checkParameterIsNotNull(line, "line");
            Builder $this$apply = this;
            int index = StringsKt.indexOf$default((CharSequence) line, ':', 0, false, 6, (Object) null);
            if (index != -1) {
                String substring = line.substring(0, index);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                if (substring != null) {
                    String obj = StringsKt.trim((CharSequence) substring).toString();
                    String substring2 = line.substring(index + 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                    $this$apply.add(obj, substring2);
                    return this;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected header: ");
            sb.append(line);
            throw new IllegalArgumentException(sb.toString().toString());
        }

        public final Builder add(String name, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            Headers.Companion.checkValue(value, name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        public final Builder addUnsafeNonAscii(String name, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        public final Builder addAll(Headers headers) {
            Intrinsics.checkParameterIsNotNull(headers, "headers");
            Builder $this$apply = this;
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                $this$apply.addLenient$okhttp(headers.name(i), headers.value(i));
            }
            return this;
        }

        public final Builder add(String name, Date value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            add(name, DatesKt.toHttpDateString(value));
            return this;
        }

        public final Builder add(String name, Instant value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            add(name, new Date(value.toEpochMilli()));
            return this;
        }

        public final Builder set(String name, Date value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            set(name, DatesKt.toHttpDateString(value));
            return this;
        }

        public final Builder set(String name, Instant value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            return set(name, new Date(value.toEpochMilli()));
        }

        public final Builder addLenient$okhttp(String name, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Builder $this$apply = this;
            $this$apply.namesAndValues.add(name);
            $this$apply.namesAndValues.add(StringsKt.trim((CharSequence) value).toString());
            return this;
        }

        public final Builder removeAll(String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Builder $this$apply = this;
            int i = 0;
            while (i < $this$apply.namesAndValues.size()) {
                if (StringsKt.equals(name, (String) $this$apply.namesAndValues.get(i), true)) {
                    $this$apply.namesAndValues.remove(i);
                    $this$apply.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public final Builder set(String name, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            Headers.Companion.checkValue(value, name);
            $this$apply.removeAll(name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        public final String get(String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            IntProgression step = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2);
            int i = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? i >= last : i <= last) {
                while (!StringsKt.equals(name, (String) this.namesAndValues.get(i), true)) {
                    if (i != last) {
                        i += step2;
                    }
                }
                return (String) this.namesAndValues.get(i + 1);
            }
            return null;
        }

        public final Headers build() {
            Object[] array = this.namesAndValues.toArray(new String[0]);
            if (array != null) {
                return new Headers((String[]) array, null);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J%\u0010\t\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007¢\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007¢\u0006\u0004\b\u0011\u0010\u0010J!\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007¢\u0006\u0002\b\u0011J\u001d\u0010\u0014\u001a\u00020\u000e*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007¢\u0006\u0002\b\u000f¨\u0006\u0015"}, mo24952d2 = {"Lokhttp3/Headers$Companion;", "", "()V", "checkName", "", "name", "", "checkValue", "value", "get", "namesAndValues", "", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "headersOf", "Lokhttp3/Headers;", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "headers", "", "toHeaders", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Headers.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String get(String[] namesAndValues, String name) {
            IntProgression step = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2);
            int i = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? i >= last : i <= last) {
                while (!StringsKt.equals(name, namesAndValues[i], true)) {
                    if (i != last) {
                        i += step2;
                    }
                }
                return namesAndValues[i + 1];
            }
            return null;
        }

        @JvmStatic
        /* renamed from: of */
        public final Headers mo26571of(String... namesAndValues) {
            Intrinsics.checkParameterIsNotNull(namesAndValues, "namesAndValues");
            if (namesAndValues.length % 2 == 0) {
                Object clone = namesAndValues.clone();
                if (clone != null) {
                    String[] namesAndValues2 = (String[]) clone;
                    int length = namesAndValues2.length;
                    int i = 0;
                    while (i < length) {
                        if (namesAndValues2[i] != null) {
                            String str = namesAndValues2[i];
                            if (str != null) {
                                namesAndValues2[i] = StringsKt.trim((CharSequence) str).toString();
                                i++;
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                        } else {
                            throw new IllegalArgumentException("Headers cannot be null".toString());
                        }
                    }
                    IntProgression step = RangesKt.step((IntProgression) RangesKt.until(0, namesAndValues2.length), 2);
                    int i2 = step.getFirst();
                    int last = step.getLast();
                    int step2 = step.getStep();
                    if (step2 < 0 ? i2 >= last : i2 <= last) {
                        while (true) {
                            String name = namesAndValues2[i2];
                            String value = namesAndValues2[i2 + 1];
                            checkName(name);
                            checkValue(value, name);
                            if (i2 == last) {
                                break;
                            }
                            i2 += step2;
                        }
                    }
                    return new Headers(namesAndValues2, null);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            throw new IllegalArgumentException("Expected alternating header names and values".toString());
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "function name changed", replaceWith = @ReplaceWith(expression = "headersOf(*namesAndValues)", imports = {}))
        /* renamed from: -deprecated_of reason: not valid java name */
        public final Headers m2291deprecated_of(String... namesAndValues) {
            Intrinsics.checkParameterIsNotNull(namesAndValues, "namesAndValues");
            return mo26571of((String[]) Arrays.copyOf(namesAndValues, namesAndValues.length));
        }

        @JvmStatic
        /* renamed from: of */
        public final Headers mo26570of(Map<String, String> $this$toHeaders) {
            Intrinsics.checkParameterIsNotNull($this$toHeaders, "$this$toHeaders");
            String[] namesAndValues = new String[($this$toHeaders.size() * 2)];
            int i = 0;
            Map<String, String> map = $this$toHeaders;
            for (Entry entry : $this$toHeaders.entrySet()) {
                String k = (String) entry.getKey();
                String v = (String) entry.getValue();
                String str = "null cannot be cast to non-null type kotlin.CharSequence";
                if (k != null) {
                    String name = StringsKt.trim((CharSequence) k).toString();
                    if (v != null) {
                        String value = StringsKt.trim((CharSequence) v).toString();
                        checkName(name);
                        checkValue(value, name);
                        namesAndValues[i] = name;
                        namesAndValues[i + 1] = value;
                        i += 2;
                    } else {
                        throw new TypeCastException(str);
                    }
                } else {
                    throw new TypeCastException(str);
                }
            }
            return new Headers(namesAndValues, null);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "function moved to extension", replaceWith = @ReplaceWith(expression = "headers.toHeaders()", imports = {}))
        /* renamed from: -deprecated_of reason: not valid java name */
        public final Headers m2290deprecated_of(Map<String, String> headers) {
            Intrinsics.checkParameterIsNotNull(headers, "headers");
            return mo26570of(headers);
        }

        /* access modifiers changed from: private */
        public final void checkName(String name) {
            if (name.length() > 0) {
                int length = name.length();
                int i = 0;
                while (i < length) {
                    char c = name.charAt(i);
                    if ('!' <= c && '~' >= c) {
                        i++;
                    } else {
                        throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(c), Integer.valueOf(i), name).toString());
                    }
                }
                return;
            }
            throw new IllegalArgumentException("name is empty".toString());
        }

        /* access modifiers changed from: private */
        public final void checkValue(String value, String name) {
            int length = value.length();
            int i = 0;
            while (i < length) {
                char c = value.charAt(i);
                if (c == 9 || (' ' <= c && '~' >= c)) {
                    i++;
                } else {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(c), Integer.valueOf(i), name, value).toString());
                }
            }
        }
    }

    @JvmStatic
    /* renamed from: of */
    public static final Headers m126of(Map<String, String> map) {
        return Companion.mo26570of(map);
    }

    @JvmStatic
    /* renamed from: of */
    public static final Headers m127of(String... strArr) {
        return Companion.mo26571of(strArr);
    }

    private Headers(String[] namesAndValues2) {
        this.namesAndValues = namesAndValues2;
    }

    public /* synthetic */ Headers(String[] namesAndValues2, DefaultConstructorMarker $constructor_marker) {
        this(namesAndValues2);
    }

    public final String get(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return Companion.get(this.namesAndValues, name);
    }

    public final Date getDate(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String str = get(name);
        if (str != null) {
            return DatesKt.toHttpDateOrNull(str);
        }
        return null;
    }

    public final Instant getInstant(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Date value = getDate(name);
        if (value != null) {
            return value.toInstant();
        }
        return null;
    }

    public final int size() {
        return this.namesAndValues.length / 2;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size reason: not valid java name */
    public final int m2289deprecated_size() {
        return size();
    }

    public final String name(int index) {
        return this.namesAndValues[index * 2];
    }

    public final String value(int index) {
        return this.namesAndValues[(index * 2) + 1];
    }

    public final Set<String> names() {
        TreeSet result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int size = size();
        for (int i = 0; i < size; i++) {
            result.add(name(i));
        }
        Set<String> unmodifiableSet = Collections.unmodifiableSet(result);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiableSet(result)");
        return unmodifiableSet;
    }

    public final List<String> values(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List result = null;
        int size = size();
        for (int i = 0; i < size; i++) {
            if (StringsKt.equals(name, name(i), true)) {
                if (result == null) {
                    result = new ArrayList(2);
                }
                result.add(value(i));
            }
        }
        if (result == null) {
            return CollectionsKt.emptyList();
        }
        List<String> unmodifiableList = Collections.unmodifiableList(result);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiableList(result)");
        return unmodifiableList;
    }

    public final long byteCount() {
        String[] strArr = this.namesAndValues;
        long result = (long) (strArr.length * 2);
        for (int i = 0; i < strArr.length; i++) {
            result += (long) this.namesAndValues[i].length();
        }
        return result;
    }

    public Iterator<Pair<String, String>> iterator() {
        int size = size();
        Pair[] pairArr = new Pair[size];
        for (int i = 0; i < size; i++) {
            int it = i;
            pairArr[i] = TuplesKt.m113to(name(it), value(it));
        }
        return ArrayIteratorKt.iterator(pairArr);
    }

    public final Builder newBuilder() {
        Builder result = new Builder();
        CollectionsKt.addAll((Collection<? super T>) result.getNamesAndValues$okhttp(), (T[]) this.namesAndValues);
        return result;
    }

    public boolean equals(Object other) {
        return (other instanceof Headers) && Arrays.equals(this.namesAndValues, ((Headers) other).namesAndValues);
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder $this$buildString = sb;
        int size = size();
        for (int i = 0; i < size; i++) {
            $this$buildString.append(name(i));
            $this$buildString.append(": ");
            $this$buildString.append(value(i));
            $this$buildString.append("\n");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public final Map<String, List<String>> toMultimap() {
        TreeMap result = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int size = size();
        int i = 0;
        while (i < size) {
            String name = name(i);
            Locale locale = Locale.US;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
            if (name != null) {
                String name2 = name.toLowerCase(locale);
                Intrinsics.checkExpressionValueIsNotNull(name2, "(this as java.lang.String).toLowerCase(locale)");
                List values = (List) result.get(name2);
                if (values == null) {
                    values = new ArrayList(2);
                    result.put(name2, values);
                }
                values.add(value(i));
                i++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return result;
    }
}
