package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import retrofit2.CallAdapter.Factory;

class Platform {
    private static final Platform PLATFORM = findPlatform();

    static class Android extends Platform {

        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            public void execute(Runnable r) {
                this.handler.post(r);
            }
        }

        Android() {
        }

        /* access modifiers changed from: 0000 */
        public boolean isDefaultMethod(Method method) {
            if (VERSION.SDK_INT < 24) {
                return false;
            }
            return method.isDefault();
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        /* access modifiers changed from: 0000 */
        public List<? extends Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
            if (callbackExecutor != null) {
                DefaultCallAdapterFactory executorFactory = new DefaultCallAdapterFactory(callbackExecutor);
                if (VERSION.SDK_INT < 24) {
                    return Collections.singletonList(executorFactory);
                }
                return Arrays.asList(new Factory[]{CompletableFutureCallAdapterFactory.INSTANCE, executorFactory});
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        public int defaultCallAdapterFactoriesSize() {
            return VERSION.SDK_INT >= 24 ? 2 : 1;
        }

        /* access modifiers changed from: 0000 */
        public List<? extends Converter.Factory> defaultConverterFactories() {
            if (VERSION.SDK_INT >= 24) {
                return Collections.singletonList(OptionalConverterFactory.INSTANCE);
            }
            return Collections.emptyList();
        }

        /* access modifiers changed from: 0000 */
        public int defaultConverterFactoriesSize() {
            return VERSION.SDK_INT >= 24 ? 1 : 0;
        }
    }

    static class Java8 extends Platform {
        Java8() {
        }

        /* access modifiers changed from: 0000 */
        public boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        /* access modifiers changed from: 0000 */
        public Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args) throws Throwable {
            Constructor<Lookup> constructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            constructor.setAccessible(true);
            return ((Lookup) constructor.newInstance(new Object[]{declaringClass, Integer.valueOf(-1)})).unreflectSpecial(method, declaringClass).bindTo(object).invokeWithArguments(args);
        }

        /* access modifiers changed from: 0000 */
        public List<? extends Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
            List<Factory> factories = new ArrayList<>(2);
            factories.add(CompletableFutureCallAdapterFactory.INSTANCE);
            factories.add(new DefaultCallAdapterFactory(callbackExecutor));
            return Collections.unmodifiableList(factories);
        }

        /* access modifiers changed from: 0000 */
        public int defaultCallAdapterFactoriesSize() {
            return 2;
        }

        /* access modifiers changed from: 0000 */
        public List<? extends Converter.Factory> defaultConverterFactories() {
            return Collections.singletonList(OptionalConverterFactory.INSTANCE);
        }

        /* access modifiers changed from: 0000 */
        public int defaultConverterFactoriesSize() {
            return 1;
        }
    }

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e2) {
            return new Platform();
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public List<? extends Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
        return Collections.singletonList(new DefaultCallAdapterFactory(callbackExecutor));
    }

    /* access modifiers changed from: 0000 */
    public int defaultCallAdapterFactoriesSize() {
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public List<? extends Converter.Factory> defaultConverterFactories() {
        return Collections.emptyList();
    }

    /* access modifiers changed from: 0000 */
    public int defaultConverterFactoriesSize() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDefaultMethod(Method method) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object object, @Nullable Object... args) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
