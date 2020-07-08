package okio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0014B\u001f\b\u0002\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\rH\u0002R\u001e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005X\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, mo24952d2 = {"Lokio/Options;", "Ljava/util/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$jvm", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$jvm", "()[I", "get", "index", "Companion", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
/* compiled from: Options.kt */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    @Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, mo24952d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "jvm"}, mo24953k = 1, mo24954mv = {1, 1, 11})
    /* compiled from: Options.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        /* renamed from: of */
        public final Options mo27949of(ByteString... byteStrings) {
            ByteString[] byteStringArr;
            ByteString[] byteStringArr2 = byteStrings;
            Intrinsics.checkParameterIsNotNull(byteStringArr2, "byteStrings");
            if (byteStringArr2.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, null);
            }
            List list = ArraysKt.toMutableList((T[]) byteStrings);
            CollectionsKt.sort(list);
            ByteString[] byteStringArr3 = byteStrings;
            Collection destination$iv$iv = new ArrayList(byteStringArr3.length);
            boolean z = false;
            for (ByteString byteString : byteStringArr3) {
                destination$iv$iv.add(Integer.valueOf(-1));
            }
            Object[] array = ((List) destination$iv$iv).toArray(new Integer[0]);
            if (array != null) {
                Integer[] numArr = (Integer[]) array;
                List indexes = CollectionsKt.mutableListOf((Integer[]) Arrays.copyOf(numArr, numArr.length));
                ByteString[] byteStringArr4 = byteStrings;
                int index$iv = 0;
                int length = byteStringArr4.length;
                int i = 0;
                while (i < length) {
                    int index$iv2 = index$iv + 1;
                    boolean z2 = z;
                    indexes.set(CollectionsKt.binarySearch$default(list, (Comparable) byteStringArr4[i], 0, 0, 6, (Object) null), Integer.valueOf(index$iv));
                    i++;
                    index$iv = index$iv2;
                    z = z2;
                }
                if (((ByteString) list.get(0)).size() > 0) {
                    int a = 0;
                    while (a < list.size()) {
                        ByteString prefix = (ByteString) list.get(a);
                        int b = a + 1;
                        while (b < list.size()) {
                            ByteString byteString2 = (ByteString) list.get(b);
                            if (!byteString2.startsWith(prefix)) {
                                continue;
                                break;
                            }
                            if (!(byteString2.size() != prefix.size())) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("duplicate option: ");
                                sb.append(byteString2);
                                throw new IllegalArgumentException(sb.toString().toString());
                            } else if (((Number) indexes.get(b)).intValue() > ((Number) indexes.get(a)).intValue()) {
                                list.remove(b);
                                indexes.remove(b);
                            } else {
                                b++;
                            }
                        }
                        a++;
                    }
                    Buffer trieBytes = new Buffer();
                    int i2 = a;
                    List list2 = indexes;
                    buildTrieRecursive$default(this, 0, trieBytes, 0, list, 0, 0, indexes, 53, null);
                    int[] trie = new int[((int) getIntCount(trieBytes))];
                    int i3 = 0;
                    while (!trieBytes.exhausted()) {
                        int i4 = i3 + 1;
                        trie[i3] = trieBytes.readInt();
                        i3 = i4;
                    }
                    return new Options((ByteString[]) byteStrings.clone(), trie, null);
                }
                int i5 = index$iv;
                throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        static /* bridge */ /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) {
            int i5;
            int i6;
            int i7;
            long j2 = (i4 & 1) != 0 ? 0 : j;
            if ((i4 & 4) != 0) {
                i5 = 0;
            } else {
                i5 = i;
            }
            if ((i4 & 16) != 0) {
                i6 = 0;
            } else {
                i6 = i2;
            }
            if ((i4 & 32) != 0) {
                i7 = list.size();
            } else {
                i7 = i3;
            }
            companion.buildTrieRecursive(j2, buffer, i5, list, i6, i7, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
            ByteString from;
            int fromIndex2;
            int prefixIndex;
            ByteString from2;
            ByteString from3;
            Buffer childNodes;
            int rangeEnd;
            int selectChoiceCount;
            int prefixIndex2;
            int fromIndex3;
            Buffer buffer = node;
            int i = byteStringOffset;
            List<? extends ByteString> list = byteStrings;
            int i2 = toIndex;
            List<Integer> list2 = indexes;
            boolean z = false;
            int i3 = fromIndex;
            String str = "Failed requirement.";
            if (i3 < i2) {
                int i4 = i3;
                while (i4 < i2) {
                    if (((ByteString) list.get(i4)).size() >= i) {
                        i4++;
                    } else {
                        throw new IllegalArgumentException(str.toString());
                    }
                }
                int fromIndex4 = fromIndex;
                ByteString from4 = (ByteString) list.get(fromIndex4);
                ByteString to = (ByteString) list.get(i2 - 1);
                if (i == from4.size()) {
                    int prefixIndex3 = ((Number) list2.get(fromIndex4)).intValue();
                    int fromIndex5 = fromIndex4 + 1;
                    fromIndex2 = fromIndex5;
                    from = (ByteString) list.get(fromIndex5);
                    prefixIndex = prefixIndex3;
                } else {
                    fromIndex2 = fromIndex4;
                    from = from4;
                    prefixIndex = -1;
                }
                if (from.getByte(i) != to.getByte(i)) {
                    int i5 = fromIndex2 + 1;
                    int selectChoiceCount2 = 1;
                    while (i5 < i2) {
                        int i6 = i5;
                        if (((ByteString) list.get(i6 - 1)).getByte(i) != ((ByteString) list.get(i6)).getByte(i)) {
                            selectChoiceCount2++;
                        }
                        i5 = i6 + 1;
                    }
                    long childNodesOffset = nodeOffset + getIntCount(buffer) + ((long) 2) + ((long) (selectChoiceCount2 * 2));
                    buffer.writeInt(selectChoiceCount2);
                    buffer.writeInt(prefixIndex);
                    for (int i7 = fromIndex2; i7 < i2; i7++) {
                        int rangeByte = ((ByteString) list.get(i7)).getByte(i);
                        if (i7 == fromIndex2 || rangeByte != ((ByteString) list.get(i7 - 1)).getByte(i)) {
                            buffer.writeInt(255 & rangeByte);
                        }
                    }
                    Buffer childNodes2 = new Buffer();
                    int rangeStart = fromIndex2;
                    while (rangeStart < i2) {
                        byte rangeByte2 = ((ByteString) list.get(rangeStart)).getByte(i);
                        int rangeEnd2 = toIndex;
                        int i8 = rangeStart + 1;
                        while (true) {
                            if (i8 >= i2) {
                                i8 = rangeEnd2;
                                break;
                            }
                            int rangeEnd3 = rangeEnd2;
                            if (rangeByte2 != ((ByteString) list.get(i8)).getByte(i)) {
                                int i9 = i8;
                                break;
                            } else {
                                i8++;
                                rangeEnd2 = rangeEnd3;
                            }
                        }
                        if (rangeStart + 1 == i8) {
                            byte b = rangeByte2;
                            if (i + 1 == ((ByteString) list.get(rangeStart)).size()) {
                                buffer.writeInt(((Number) list2.get(rangeStart)).intValue());
                                rangeEnd = i8;
                                int i10 = rangeStart;
                                childNodes = childNodes2;
                                selectChoiceCount = selectChoiceCount2;
                                prefixIndex2 = prefixIndex;
                                fromIndex3 = fromIndex2;
                                from3 = from;
                                rangeStart = rangeEnd;
                                fromIndex2 = fromIndex3;
                                prefixIndex = prefixIndex2;
                                selectChoiceCount2 = selectChoiceCount;
                                childNodes2 = childNodes;
                                from = from3;
                                int prefixIndex4 = fromIndex;
                                list2 = indexes;
                            }
                        }
                        buffer.writeInt(((int) (childNodesOffset + getIntCount(childNodes2))) * -1);
                        rangeEnd = i8;
                        childNodes = childNodes2;
                        selectChoiceCount = selectChoiceCount2;
                        prefixIndex2 = prefixIndex;
                        fromIndex3 = fromIndex2;
                        from3 = from;
                        buildTrieRecursive(childNodesOffset, childNodes2, i + 1, byteStrings, rangeStart, rangeEnd, indexes);
                        rangeStart = rangeEnd;
                        fromIndex2 = fromIndex3;
                        prefixIndex = prefixIndex2;
                        selectChoiceCount2 = selectChoiceCount;
                        childNodes2 = childNodes;
                        from = from3;
                        int prefixIndex42 = fromIndex;
                        list2 = indexes;
                    }
                    int i11 = rangeStart;
                    int i12 = selectChoiceCount2;
                    int i13 = prefixIndex;
                    int fromIndex6 = fromIndex2;
                    ByteString from5 = from;
                    buffer.writeAll(childNodes2);
                    int i14 = fromIndex6;
                    ByteString byteString = from5;
                    List<Integer> list3 = indexes;
                    return;
                }
                int prefixIndex5 = prefixIndex;
                int fromIndex7 = fromIndex2;
                ByteString from6 = from;
                int min = Math.min(from6.size(), to.size());
                int scanByteCount = 0;
                int i15 = i;
                while (true) {
                    if (i15 >= min) {
                        from2 = from6;
                        break;
                    }
                    from2 = from6;
                    if (from2.getByte(i15) != to.getByte(i15)) {
                        break;
                    }
                    scanByteCount++;
                    i15++;
                    from6 = from2;
                }
                long childNodesOffset2 = nodeOffset + getIntCount(buffer) + ((long) 2) + ((long) scanByteCount) + 1;
                buffer.writeInt(-scanByteCount);
                buffer.writeInt(prefixIndex5);
                int i16 = i + scanByteCount;
                for (int i17 = i; i17 < i16; i17++) {
                    buffer.writeInt((int) from2.getByte(i17) & UByte.MAX_VALUE);
                }
                if (fromIndex7 + 1 == i2) {
                    if (i + scanByteCount == ((ByteString) list.get(fromIndex7)).size()) {
                        z = true;
                    }
                    if (z) {
                        int fromIndex8 = fromIndex7;
                        buffer.writeInt(((Number) indexes.get(fromIndex8)).intValue());
                        int i18 = fromIndex8;
                        ByteString byteString2 = from2;
                        return;
                    }
                    throw new IllegalStateException("Check failed.".toString());
                }
                int fromIndex9 = fromIndex7;
                List<Integer> list4 = indexes;
                Buffer childNodes3 = new Buffer();
                buffer.writeInt(((int) (childNodesOffset2 + getIntCount(childNodes3))) * -1);
                Buffer childNodes4 = childNodes3;
                int i19 = fromIndex9;
                ByteString byteString3 = from2;
                int i20 = scanByteCount;
                buildTrieRecursive(childNodesOffset2, childNodes3, i + scanByteCount, byteStrings, fromIndex9, toIndex, indexes);
                buffer.writeAll(childNodes4);
                return;
            }
            throw new IllegalArgumentException(str.toString());
        }

        private final long getIntCount(Buffer $receiver) {
            return $receiver.size() / ((long) 4);
        }
    }

    @JvmStatic
    /* renamed from: of */
    public static final Options m136of(ByteString... byteStringArr) {
        return Companion.mo27949of(byteStringArr);
    }

    private Options(ByteString[] byteStrings2, int[] trie2) {
        this.byteStrings = byteStrings2;
        this.trie = trie2;
    }

    public /* synthetic */ Options(ByteString[] byteStrings2, int[] trie2, DefaultConstructorMarker $constructor_marker) {
        this(byteStrings2, trie2);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj != null ? obj instanceof ByteString : true) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains(byteString);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj != null ? obj instanceof ByteString : true) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf(byteString);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj != null ? obj instanceof ByteString : true) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    public final /* bridge */ ByteString remove(int i) {
        return removeAt(i);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (obj != null ? obj instanceof ByteString : true) {
            return remove((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean remove(ByteString byteString) {
        return super.remove(byteString);
    }

    public /* bridge */ ByteString removeAt(int i) {
        return (ByteString) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final ByteString[] getByteStrings$jvm() {
        return this.byteStrings;
    }

    public final int[] getTrie$jvm() {
        return this.trie;
    }

    public int getSize() {
        return this.byteStrings.length;
    }

    public ByteString get(int index) {
        return this.byteStrings[index];
    }
}
