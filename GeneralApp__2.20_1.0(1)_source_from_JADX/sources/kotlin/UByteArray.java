package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.UByteIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006."}, mo24952d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "storage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([BI)B", "hashCode", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "Lkotlin/collections/UByteIterator;", "iterator-impl", "([B)Lkotlin/collections/UByteIterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "Iterator", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: UByteArray.kt */
public final class UByteArray implements Collection<UByte>, KMappedMarker {
    private final byte[] storage;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo24952d2 = {"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextUByte", "Lkotlin/UByte;", "()B", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: UByteArray.kt */
    private static final class Iterator extends UByteIterator {
        private final byte[] array;
        private int index;

        public Iterator(byte[] array2) {
            Intrinsics.checkParameterIsNotNull(array2, "array");
            this.array = array2;
        }

        public boolean hasNext() {
            return this.index < this.array.length;
        }

        public byte nextUByte() {
            int i = this.index;
            byte[] bArr = this.array;
            if (i < bArr.length) {
                this.index = i + 1;
                return UByte.m1284constructorimpl(bArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m1331equalsimpl(byte[] bArr, Object obj) {
        return (obj instanceof UByteArray) && Intrinsics.areEqual((Object) bArr, (Object) ((UByteArray) obj).m1342unboximpl());
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m1332equalsimpl0(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkParameterIsNotNull(bArr, "p1");
        Intrinsics.checkParameterIsNotNull(bArr2, "p2");
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m1335hashCodeimpl(byte[] bArr) {
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    public static /* synthetic */ void storage$annotations() {
    }

    /* renamed from: toString-impl reason: not valid java name */
    public static String m1339toStringimpl(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("UByteArray(storage=");
        sb.append(Arrays.toString(bArr));
        sb.append(")");
        return sb.toString();
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-7apg3OU reason: not valid java name */
    public boolean m1340add7apg3OU(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: contains-7apg3OU reason: not valid java name */
    public boolean m1341contains7apg3OU(byte b) {
        return m1329contains7apg3OU(this.storage, b);
    }

    public boolean containsAll(Collection<? extends Object> collection) {
        return m1330containsAllimpl(this.storage, collection);
    }

    public boolean equals(Object obj) {
        return m1331equalsimpl(this.storage, obj);
    }

    public int getSize() {
        return m1334getSizeimpl(this.storage);
    }

    public int hashCode() {
        return m1335hashCodeimpl(this.storage);
    }

    public boolean isEmpty() {
        return m1336isEmptyimpl(this.storage);
    }

    public UByteIterator iterator() {
        return m1337iteratorimpl(this.storage);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return m1339toStringimpl(this.storage);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ byte[] m1342unboximpl() {
        return this.storage;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m1341contains7apg3OU(((UByte) obj).m1325unboximpl());
        }
        return false;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    private /* synthetic */ UByteArray(byte[] storage2) {
        Intrinsics.checkParameterIsNotNull(storage2, "storage");
        this.storage = storage2;
    }

    /* renamed from: constructor-impl reason: not valid java name */
    public static byte[] m1328constructorimpl(byte[] storage2) {
        Intrinsics.checkParameterIsNotNull(storage2, "storage");
        return storage2;
    }

    /* renamed from: constructor-impl reason: not valid java name */
    public static byte[] m1327constructorimpl(int size) {
        return m1328constructorimpl(new byte[size]);
    }

    /* renamed from: get-impl reason: not valid java name */
    public static final byte m1333getimpl(byte[] $this, int index) {
        return UByte.m1284constructorimpl($this[index]);
    }

    /* renamed from: set-VurrAj0 reason: not valid java name */
    public static final void m1338setVurrAj0(byte[] $this, int index, byte value) {
        $this[index] = value;
    }

    /* renamed from: getSize-impl reason: not valid java name */
    public static int m1334getSizeimpl(byte[] $this) {
        return $this.length;
    }

    /* renamed from: iterator-impl reason: not valid java name */
    public static UByteIterator m1337iteratorimpl(byte[] $this) {
        return new Iterator($this);
    }

    /* renamed from: contains-7apg3OU reason: not valid java name */
    public static boolean m1329contains7apg3OU(byte[] $this, byte element) {
        return ArraysKt.contains($this, element);
    }

    /* renamed from: containsAll-impl reason: not valid java name */
    public static boolean m1330containsAllimpl(byte[] $this, Collection<UByte> elements) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Iterable $this$all$iv = elements;
        if (((Collection) $this$all$iv).isEmpty()) {
            return true;
        }
        for (Object it : $this$all$iv) {
            if (!(it instanceof UByte) || !ArraysKt.contains($this, ((UByte) it).m1325unboximpl())) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: isEmpty-impl reason: not valid java name */
    public static boolean m1336isEmptyimpl(byte[] $this) {
        return $this.length == 0;
    }
}
