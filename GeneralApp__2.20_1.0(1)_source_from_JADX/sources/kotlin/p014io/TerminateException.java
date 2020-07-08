package kotlin.p014io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo24952d2 = {"Lkotlin/io/TerminateException;", "Lkotlin/io/FileSystemException;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* renamed from: kotlin.io.TerminateException */
/* compiled from: Utils.kt */
final class TerminateException extends FileSystemException {
    public TerminateException(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        super(file, null, null, 6, null);
    }
}
