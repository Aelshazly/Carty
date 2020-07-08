package kotlin.concurrent;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u001a0\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\fH\b¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, mo24952d2 = {"thread", "Ljava/lang/Thread;", "start", "", "isDaemon", "contextClassLoader", "Ljava/lang/ClassLoader;", "name", "", "priority", "", "block", "Lkotlin/Function0;", "", "getOrSet", "T", "", "Ljava/lang/ThreadLocal;", "default", "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, mo24953k = 2, mo24954mv = {1, 1, 15})
/* compiled from: Thread.kt */
public final class ThreadsKt {
    public static /* synthetic */ Thread thread$default(boolean z, boolean z2, ClassLoader classLoader, String str, int i, Function0 function0, int i2, Object obj) {
        boolean z3;
        ClassLoader classLoader2;
        String str2;
        int i3;
        boolean z4 = (i2 & 1) != 0 ? true : z;
        if ((i2 & 2) != 0) {
            z3 = false;
        } else {
            z3 = z2;
        }
        if ((i2 & 4) != 0) {
            classLoader2 = null;
        } else {
            classLoader2 = classLoader;
        }
        if ((i2 & 8) != 0) {
            str2 = null;
        } else {
            str2 = str;
        }
        if ((i2 & 16) != 0) {
            i3 = -1;
        } else {
            i3 = i;
        }
        return thread(z4, z3, classLoader2, str2, i3, function0);
    }

    public static final Thread thread(boolean start, boolean isDaemon, ClassLoader contextClassLoader, String name, int priority, Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        ThreadsKt$thread$thread$1 thread = new ThreadsKt$thread$thread$1(block);
        if (isDaemon) {
            thread.setDaemon(true);
        }
        if (priority > 0) {
            thread.setPriority(priority);
        }
        if (name != null) {
            thread.setName(name);
        }
        if (contextClassLoader != null) {
            thread.setContextClassLoader(contextClassLoader);
        }
        if (start) {
            thread.start();
        }
        return thread;
    }

    private static final <T> T getOrSet(ThreadLocal<T> $this$getOrSet, Function0<? extends T> function0) {
        T t = $this$getOrSet.get();
        if (t != null) {
            return t;
        }
        Object invoke = function0.invoke();
        $this$getOrSet.set(invoke);
        return invoke;
    }
}
