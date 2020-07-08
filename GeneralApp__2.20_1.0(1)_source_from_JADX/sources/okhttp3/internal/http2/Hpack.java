package okhttp3.internal.http2;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import p008cz.msebera.android.httpclient.HttpHost;
import p008cz.msebera.android.httpclient.client.methods.HttpPost;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005J\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, mo24952d2 = {"Lokhttp3/internal/http2/Hpack;", "", "()V", "NAME_TO_FIRST_INDEX", "", "Lokio/ByteString;", "", "getNAME_TO_FIRST_INDEX", "()Ljava/util/Map;", "PREFIX_4_BITS", "PREFIX_5_BITS", "PREFIX_6_BITS", "PREFIX_7_BITS", "SETTINGS_HEADER_TABLE_SIZE", "SETTINGS_HEADER_TABLE_SIZE_LIMIT", "STATIC_HEADER_TABLE", "", "Lokhttp3/internal/http2/Header;", "getSTATIC_HEADER_TABLE", "()[Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "checkLowercase", "name", "nameToFirstIndex", "Reader", "Writer", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: Hpack.kt */
public final class Hpack {
    public static final Hpack INSTANCE;
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
    private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
    private static final Header[] STATIC_HEADER_TABLE;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u001aJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0018\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\nH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0006\u0010\u0006\u001a\u00020\u0005J\b\u0010!\u001a\u00020\u0005H\u0002J\u0006\u0010\"\u001a\u00020\u001cJ\u0006\u0010#\u001a\u00020\u0013J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u0005J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\u0010\u0010+\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\b\u0010,\u001a\u00020\u0013H\u0002R\u001c\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t8\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, mo24952d2 = {"Lokhttp3/internal/http2/Hpack$Reader;", "", "source", "Lokio/Source;", "headerTableSizeSetting", "", "maxDynamicTableByteCount", "(Lokio/Source;II)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "headerCount", "headerList", "", "nextHeaderIndex", "Lokio/BufferedSource;", "adjustDynamicTableByteCount", "", "clearDynamicTable", "dynamicTableIndex", "index", "evictToRecoverBytes", "bytesToRecover", "getAndResetHeaderList", "", "getName", "Lokio/ByteString;", "insertIntoDynamicTable", "entry", "isStaticHeader", "", "readByte", "readByteString", "readHeaders", "readIndexedHeader", "readInt", "firstByte", "prefixMask", "readLiteralHeaderWithIncrementalIndexingIndexedName", "nameIndex", "readLiteralHeaderWithIncrementalIndexingNewName", "readLiteralHeaderWithoutIndexingIndexedName", "readLiteralHeaderWithoutIndexingNewName", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Hpack.kt */
    public static final class Reader {
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public int headerCount;
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        private int nextHeaderIndex;
        private final BufferedSource source;

        public Reader(Source source2, int i) {
            this(source2, i, 0, 4, null);
        }

        public Reader(Source source2, int headerTableSizeSetting2, int maxDynamicTableByteCount2) {
            Intrinsics.checkParameterIsNotNull(source2, Param.SOURCE);
            this.headerTableSizeSetting = headerTableSizeSetting2;
            this.maxDynamicTableByteCount = maxDynamicTableByteCount2;
            this.headerList = new ArrayList();
            this.source = Okio.buffer(source2);
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
        }

        public /* synthetic */ Reader(Source source2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i3 & 4) != 0) {
                i2 = i;
            }
            this(source2, i, i2);
        }

        public final List<Header> getAndResetHeaderList() {
            List result = CollectionsKt.toList(this.headerList);
            this.headerList.clear();
            return result;
        }

        public final int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        private final void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i >= i2) {
                return;
            }
            if (i == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(i2 - i);
            }
        }

        private final void clearDynamicTable() {
            ArraysKt.fill$default((Object[]) this.dynamicTable, (Object) null, 0, 0, 6, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int evictToRecoverBytes(int bytesToRecover) {
            int bytesToRecover2 = bytesToRecover;
            int entriesToEvict = 0;
            if (bytesToRecover2 > 0) {
                int j = this.dynamicTable.length;
                while (true) {
                    j--;
                    if (j < this.nextHeaderIndex || bytesToRecover2 <= 0) {
                        Header[] headerArr = this.dynamicTable;
                        int i = this.nextHeaderIndex;
                        System.arraycopy(headerArr, i + 1, headerArr, i + 1 + entriesToEvict, this.headerCount);
                        this.nextHeaderIndex += entriesToEvict;
                    } else {
                        Header toEvict = this.dynamicTable[j];
                        if (toEvict == null) {
                            Intrinsics.throwNpe();
                        }
                        bytesToRecover2 -= toEvict.hpackSize;
                        this.dynamicTableByteCount -= toEvict.hpackSize;
                        this.headerCount--;
                        entriesToEvict++;
                    }
                }
                Header[] headerArr2 = this.dynamicTable;
                int i2 = this.nextHeaderIndex;
                System.arraycopy(headerArr2, i2 + 1, headerArr2, i2 + 1 + entriesToEvict, this.headerCount);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        public final void readHeaders() throws IOException {
            while (!this.source.exhausted()) {
                int b = Util.and(this.source.readByte(), 255);
                if (b == 128) {
                    throw new IOException("index == 0");
                } else if ((b & 128) == 128) {
                    readIndexedHeader(readInt(b, 127) - 1);
                } else if (b == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((b & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(b, 63) - 1);
                } else if ((b & 32) == 32) {
                    this.maxDynamicTableByteCount = readInt(b, 31);
                    int i = this.maxDynamicTableByteCount;
                    if (i < 0 || i > this.headerTableSizeSetting) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid dynamic table size update ");
                        sb.append(this.maxDynamicTableByteCount);
                        throw new IOException(sb.toString());
                    }
                    adjustDynamicTableByteCount();
                } else if (b == 16 || b == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(b, 15) - 1);
                }
            }
        }

        private final void readIndexedHeader(int index) throws IOException {
            if (isStaticHeader(index)) {
                this.headerList.add(Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index]);
                return;
            }
            int dynamicTableIndex = dynamicTableIndex(index - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (dynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (dynamicTableIndex < headerArr.length) {
                    Collection collection = this.headerList;
                    Header header = headerArr[dynamicTableIndex];
                    if (header == null) {
                        Intrinsics.throwNpe();
                    }
                    collection.add(header);
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Header index too large ");
            sb.append(index + 1);
            throw new IOException(sb.toString());
        }

        private final int dynamicTableIndex(int index) {
            return this.nextHeaderIndex + 1 + index;
        }

        private final void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
            this.headerList.add(new Header(getName(index), readByteString()));
        }

        private final void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            this.headerList.add(new Header(Hpack.INSTANCE.checkLowercase(readByteString()), readByteString()));
        }

        private final void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
            insertIntoDynamicTable(-1, new Header(getName(nameIndex), readByteString()));
        }

        private final void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            insertIntoDynamicTable(-1, new Header(Hpack.INSTANCE.checkLowercase(readByteString()), readByteString()));
        }

        private final ByteString getName(int index) throws IOException {
            if (isStaticHeader(index)) {
                return Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index].name;
            }
            int dynamicTableIndex = dynamicTableIndex(index - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (dynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (dynamicTableIndex < headerArr.length) {
                    Header header = headerArr[dynamicTableIndex];
                    if (header == null) {
                        Intrinsics.throwNpe();
                    }
                    return header.name;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Header index too large ");
            sb.append(index + 1);
            throw new IOException(sb.toString());
        }

        private final boolean isStaticHeader(int index) {
            return index >= 0 && index <= Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length - 1;
        }

        private final void insertIntoDynamicTable(int index, Header entry) {
            int index2 = index;
            this.headerList.add(entry);
            int delta = entry.hpackSize;
            if (index2 != -1) {
                Header header = this.dynamicTable[dynamicTableIndex(index2)];
                if (header == null) {
                    Intrinsics.throwNpe();
                }
                delta -= header.hpackSize;
            }
            int i = this.maxDynamicTableByteCount;
            if (delta > i) {
                clearDynamicTable();
                return;
            }
            int entriesEvicted = evictToRecoverBytes((this.dynamicTableByteCount + delta) - i);
            if (index2 == -1) {
                int i2 = this.headerCount + 1;
                Header[] headerArr = this.dynamicTable;
                if (i2 > headerArr.length) {
                    Header[] doubled = new Header[(headerArr.length * 2)];
                    System.arraycopy(headerArr, 0, doubled, headerArr.length, headerArr.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = doubled;
                }
                int i3 = this.nextHeaderIndex;
                this.nextHeaderIndex = i3 - 1;
                this.dynamicTable[i3] = entry;
                this.headerCount++;
            } else {
                this.dynamicTable[index2 + dynamicTableIndex(index2) + entriesEvicted] = entry;
            }
            this.dynamicTableByteCount += delta;
        }

        private final int readByte() throws IOException {
            return Util.and(this.source.readByte(), 255);
        }

        public final int readInt(int firstByte, int prefixMask) throws IOException {
            int prefix = firstByte & prefixMask;
            if (prefix < prefixMask) {
                return prefix;
            }
            int result = prefixMask;
            int shift = 0;
            while (true) {
                int b = readByte();
                if ((b & 128) == 0) {
                    return result + (b << shift);
                }
                result += (b & 127) << shift;
                shift += 7;
            }
        }

        public final ByteString readByteString() throws IOException {
            int firstByte = readByte();
            long length = (long) readInt(firstByte, 127);
            if (!((firstByte & 128) == 128)) {
                return this.source.readByteString(length);
            }
            Buffer decodeBuffer = new Buffer();
            Huffman.INSTANCE.decode(this.source, length, decodeBuffer);
            return decodeBuffer.readByteString();
        }
    }

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dJ\u0014\u0010\u001e\u001a\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 J\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003R\u001c\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n8\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, mo24952d2 = {"Lokhttp3/internal/http2/Hpack$Writer;", "", "headerTableSizeSetting", "", "useCompression", "", "out", "Lokio/Buffer;", "(IZLokio/Buffer;)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "emitDynamicTableSizeUpdate", "headerCount", "maxDynamicTableByteCount", "nextHeaderIndex", "smallestHeaderTableSizeSetting", "adjustDynamicTableByteCount", "", "clearDynamicTable", "evictToRecoverBytes", "bytesToRecover", "insertIntoDynamicTable", "entry", "resizeHeaderTable", "writeByteString", "data", "Lokio/ByteString;", "writeHeaders", "headerBlock", "", "writeInt", "value", "prefixMask", "bits", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: Hpack.kt */
    public static final class Writer {
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;
        public int headerCount;
        public int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        private int nextHeaderIndex;
        private final Buffer out;
        private int smallestHeaderTableSizeSetting;
        private final boolean useCompression;

        public Writer(int i, Buffer buffer) {
            this(i, false, buffer, 2, null);
        }

        public Writer(Buffer buffer) {
            this(0, false, buffer, 3, null);
        }

        public Writer(int headerTableSizeSetting2, boolean useCompression2, Buffer out2) {
            Intrinsics.checkParameterIsNotNull(out2, "out");
            this.headerTableSizeSetting = headerTableSizeSetting2;
            this.useCompression = useCompression2;
            this.out = out2;
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.maxDynamicTableByteCount = this.headerTableSizeSetting;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
        }

        public /* synthetic */ Writer(int i, boolean z, Buffer buffer, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i2 & 1) != 0) {
                i = 4096;
            }
            if ((i2 & 2) != 0) {
                z = true;
            }
            this(i, z, buffer);
        }

        private final void clearDynamicTable() {
            ArraysKt.fill$default((Object[]) this.dynamicTable, (Object) null, 0, 0, 6, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int evictToRecoverBytes(int bytesToRecover) {
            int bytesToRecover2 = bytesToRecover;
            int entriesToEvict = 0;
            if (bytesToRecover2 > 0) {
                int j = this.dynamicTable.length;
                while (true) {
                    j--;
                    if (j < this.nextHeaderIndex || bytesToRecover2 <= 0) {
                        Header[] headerArr = this.dynamicTable;
                        int i = this.nextHeaderIndex;
                        System.arraycopy(headerArr, i + 1, headerArr, i + 1 + entriesToEvict, this.headerCount);
                        Header[] headerArr2 = this.dynamicTable;
                        int i2 = this.nextHeaderIndex;
                        Arrays.fill(headerArr2, i2 + 1, i2 + 1 + entriesToEvict, null);
                        this.nextHeaderIndex += entriesToEvict;
                    } else {
                        Header header = this.dynamicTable[j];
                        if (header == null) {
                            Intrinsics.throwNpe();
                        }
                        bytesToRecover2 -= header.hpackSize;
                        int i3 = this.dynamicTableByteCount;
                        Header header2 = this.dynamicTable[j];
                        if (header2 == null) {
                            Intrinsics.throwNpe();
                        }
                        this.dynamicTableByteCount = i3 - header2.hpackSize;
                        this.headerCount--;
                        entriesToEvict++;
                    }
                }
                Header[] headerArr3 = this.dynamicTable;
                int i4 = this.nextHeaderIndex;
                System.arraycopy(headerArr3, i4 + 1, headerArr3, i4 + 1 + entriesToEvict, this.headerCount);
                Header[] headerArr22 = this.dynamicTable;
                int i22 = this.nextHeaderIndex;
                Arrays.fill(headerArr22, i22 + 1, i22 + 1 + entriesToEvict, null);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        private final void insertIntoDynamicTable(Header entry) {
            int delta = entry.hpackSize;
            int i = this.maxDynamicTableByteCount;
            if (delta > i) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes((this.dynamicTableByteCount + delta) - i);
            int i2 = this.headerCount + 1;
            Header[] headerArr = this.dynamicTable;
            if (i2 > headerArr.length) {
                Header[] doubled = new Header[(headerArr.length * 2)];
                System.arraycopy(headerArr, 0, doubled, headerArr.length, headerArr.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = doubled;
            }
            int index = this.nextHeaderIndex;
            this.nextHeaderIndex = index - 1;
            this.dynamicTable[index] = entry;
            this.headerCount++;
            this.dynamicTableByteCount += delta;
        }

        public final void writeHeaders(List<Header> headerBlock) throws IOException {
            Intrinsics.checkParameterIsNotNull(headerBlock, "headerBlock");
            if (this.emitDynamicTableSizeUpdate) {
                int i = this.smallestHeaderTableSizeSetting;
                if (i < this.maxDynamicTableByteCount) {
                    writeInt(i, 31, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int size = headerBlock.size();
            for (int i2 = 0; i2 < size; i2++) {
                Header header = (Header) headerBlock.get(i2);
                ByteString name = header.name.toAsciiLowercase();
                ByteString value = header.value;
                int headerIndex = -1;
                int headerNameIndex = -1;
                Integer staticIndex = (Integer) Hpack.INSTANCE.getNAME_TO_FIRST_INDEX().get(name);
                if (staticIndex != null) {
                    headerNameIndex = staticIndex.intValue() + 1;
                    if (2 <= headerNameIndex && 7 >= headerNameIndex) {
                        if (Intrinsics.areEqual((Object) Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex - 1].value, (Object) value)) {
                            headerIndex = headerNameIndex;
                        } else if (Intrinsics.areEqual((Object) Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex].value, (Object) value)) {
                            headerIndex = headerNameIndex + 1;
                        }
                    }
                }
                if (headerIndex == -1) {
                    int j = this.nextHeaderIndex + 1;
                    int length = this.dynamicTable.length;
                    while (true) {
                        if (j >= length) {
                            break;
                        }
                        Header header2 = this.dynamicTable[j];
                        if (header2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual((Object) header2.name, (Object) name)) {
                            Header header3 = this.dynamicTable[j];
                            if (header3 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (Intrinsics.areEqual((Object) header3.value, (Object) value)) {
                                headerIndex = (j - this.nextHeaderIndex) + Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length;
                                break;
                            } else if (headerNameIndex == -1) {
                                headerNameIndex = (j - this.nextHeaderIndex) + Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length;
                            }
                        }
                        j++;
                    }
                }
                if (headerIndex != -1) {
                    writeInt(headerIndex, 127, 128);
                } else if (headerNameIndex == -1) {
                    this.out.writeByte(64);
                    writeByteString(name);
                    writeByteString(value);
                    insertIntoDynamicTable(header);
                } else if (!name.startsWith(Header.PSEUDO_PREFIX) || !(!Intrinsics.areEqual((Object) Header.TARGET_AUTHORITY, (Object) name))) {
                    writeInt(headerNameIndex, 63, 64);
                    writeByteString(value);
                    insertIntoDynamicTable(header);
                } else {
                    writeInt(headerNameIndex, 15, 0);
                    writeByteString(value);
                }
            }
        }

        public final void writeInt(int value, int prefixMask, int bits) {
            int value2 = value;
            if (value2 < prefixMask) {
                this.out.writeByte(bits | value2);
                return;
            }
            this.out.writeByte(bits | prefixMask);
            int value3 = value2 - prefixMask;
            while (value3 >= 128) {
                this.out.writeByte((value3 & 127) | 128);
                value3 >>>= 7;
            }
            this.out.writeByte(value3);
        }

        public final void writeByteString(ByteString data) throws IOException {
            Intrinsics.checkParameterIsNotNull(data, "data");
            if (!this.useCompression || Huffman.INSTANCE.encodedLength(data) >= data.size()) {
                writeInt(data.size(), 127, 0);
                this.out.write(data);
                return;
            }
            Buffer huffmanBuffer = new Buffer();
            Huffman.INSTANCE.encode(data, huffmanBuffer);
            ByteString huffmanBytes = huffmanBuffer.readByteString();
            writeInt(huffmanBytes.size(), 127, 128);
            this.out.write(huffmanBytes);
        }

        public final void resizeHeaderTable(int headerTableSizeSetting2) {
            this.headerTableSizeSetting = headerTableSizeSetting2;
            int effectiveHeaderTableSize = Math.min(headerTableSizeSetting2, 16384);
            int i = this.maxDynamicTableByteCount;
            if (i != effectiveHeaderTableSize) {
                if (effectiveHeaderTableSize < i) {
                    this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, effectiveHeaderTableSize);
                }
                this.emitDynamicTableSizeUpdate = true;
                this.maxDynamicTableByteCount = effectiveHeaderTableSize;
                adjustDynamicTableByteCount();
            }
        }

        private final void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i >= i2) {
                return;
            }
            if (i == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(i2 - i);
            }
        }
    }

    static {
        Hpack hpack = new Hpack();
        INSTANCE = hpack;
        String str = "";
        STATIC_HEADER_TABLE = new Header[]{new Header(Header.TARGET_AUTHORITY, str), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, HttpPost.METHOD_NAME), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, HttpHost.DEFAULT_SCHEME_NAME), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", str), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", str), new Header("accept-ranges", str), new Header("accept", str), new Header("access-control-allow-origin", str), new Header("age", str), new Header("allow", str), new Header("authorization", str), new Header("cache-control", str), new Header("content-disposition", str), new Header("content-encoding", str), new Header("content-language", str), new Header("content-length", str), new Header("content-location", str), new Header("content-range", str), new Header("content-type", str), new Header("cookie", str), new Header("date", str), new Header("etag", str), new Header("expect", str), new Header(ClientCookie.EXPIRES_ATTR, str), new Header("from", str), new Header("host", str), new Header("if-match", str), new Header("if-modified-since", str), new Header("if-none-match", str), new Header("if-range", str), new Header("if-unmodified-since", str), new Header("last-modified", str), new Header("link", str), new Header(Param.LOCATION, str), new Header("max-forwards", str), new Header("proxy-authenticate", str), new Header("proxy-authorization", str), new Header("range", str), new Header("referer", str), new Header("refresh", str), new Header("retry-after", str), new Header("server", str), new Header("set-cookie", str), new Header("strict-transport-security", str), new Header("transfer-encoding", str), new Header("user-agent", str), new Header("vary", str), new Header("via", str), new Header("www-authenticate", str)};
        NAME_TO_FIRST_INDEX = hpack.nameToFirstIndex();
    }

    private Hpack() {
    }

    public final Header[] getSTATIC_HEADER_TABLE() {
        return STATIC_HEADER_TABLE;
    }

    public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
        return NAME_TO_FIRST_INDEX;
    }

    private final Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap result = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int length = STATIC_HEADER_TABLE.length;
        for (int i = 0; i < length; i++) {
            if (!result.containsKey(STATIC_HEADER_TABLE[i].name)) {
                result.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
            }
        }
        Map<ByteString, Integer> unmodifiableMap = Collections.unmodifiableMap(result);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableMap, "Collections.unmodifiableMap(result)");
        return unmodifiableMap;
    }

    public final ByteString checkLowercase(ByteString name) throws IOException {
        Intrinsics.checkParameterIsNotNull(name, "name");
        int size = name.size();
        for (int i = 0; i < size; i++) {
            byte b = (byte) 65;
            byte b2 = (byte) 90;
            byte b3 = name.getByte(i);
            if (b <= b3 && b2 >= b3) {
                StringBuilder sb = new StringBuilder();
                sb.append("PROTOCOL_ERROR response malformed: mixed case name: ");
                sb.append(name.utf8());
                throw new IOException(sb.toString());
            }
        }
        return name;
    }
}
