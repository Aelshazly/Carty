package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo24952d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", "", "()V", "cache", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "notOnJava9", "buildCache", "continuation", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getModuleName", "", "Cache", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: DebugMetadata.kt */
final class ModuleNameRetriever {
    public static final ModuleNameRetriever INSTANCE = new ModuleNameRetriever();
    public static Cache cache;
    private static final Cache notOnJava9 = new Cache(null, null, null);

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo24952d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "getModuleMethod", "Ljava/lang/reflect/Method;", "getDescriptorMethod", "nameMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: DebugMetadata.kt */
    private static final class Cache {
        public final Method getDescriptorMethod;
        public final Method getModuleMethod;
        public final Method nameMethod;

        public Cache(Method getModuleMethod2, Method getDescriptorMethod2, Method nameMethod2) {
            this.getModuleMethod = getModuleMethod2;
            this.getDescriptorMethod = getDescriptorMethod2;
            this.nameMethod = nameMethod2;
        }
    }

    private ModuleNameRetriever() {
    }

    public final String getModuleName(BaseContinuationImpl continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        Cache cache2 = cache;
        if (cache2 == null) {
            cache2 = buildCache(continuation);
        }
        Object obj = null;
        if (cache2 == notOnJava9) {
            return null;
        }
        Method method = cache2.getModuleMethod;
        if (method != null) {
            Object module = method.invoke(continuation.getClass(), new Object[0]);
            if (module != null) {
                Method method2 = cache2.getDescriptorMethod;
                if (method2 != null) {
                    Object descriptor = method2.invoke(module, new Object[0]);
                    if (descriptor != null) {
                        Method method3 = cache2.nameMethod;
                        Object invoke = method3 != null ? method3.invoke(descriptor, new Object[0]) : null;
                        if (invoke instanceof String) {
                            obj = invoke;
                        }
                        return (String) obj;
                    }
                }
                return null;
            }
        }
        return null;
    }

    private final Cache buildCache(BaseContinuationImpl continuation) {
        try {
            Cache cache2 = new Cache(Class.class.getDeclaredMethod("getModule", new Class[0]), continuation.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), continuation.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
            cache = cache2;
            return cache2;
        } catch (Exception e) {
            Cache cache3 = notOnJava9;
            cache = cache3;
            return cache3;
        }
    }
}