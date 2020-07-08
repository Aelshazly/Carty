package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
    private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public static class AndroidViewModelFactory extends NewInstanceFactory {
        private static AndroidViewModelFactory sInstance;
        private Application mApplication;

        public static AndroidViewModelFactory getInstance(Application application) {
            if (sInstance == null) {
                sInstance = new AndroidViewModelFactory(application);
            }
            return sInstance;
        }

        public AndroidViewModelFactory(Application application) {
            this.mApplication = application;
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            String str = "Cannot create an instance of ";
            if (!AndroidViewModel.class.isAssignableFrom(modelClass)) {
                return super.create(modelClass);
            }
            try {
                return (ViewModel) modelClass.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this.mApplication});
            } catch (NoSuchMethodException e) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(modelClass);
                throw new RuntimeException(sb.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(modelClass);
                throw new RuntimeException(sb2.toString(), e2);
            } catch (InstantiationException e3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(modelClass);
                throw new RuntimeException(sb3.toString(), e3);
            } catch (InvocationTargetException e4) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(modelClass);
                throw new RuntimeException(sb4.toString(), e4);
            }
        }
    }

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    static abstract class KeyedFactory extends OnRequeryFactory implements Factory {
        public abstract <T extends ViewModel> T create(String str, Class<T> cls);

        KeyedFactory() {
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }
    }

    public static class NewInstanceFactory implements Factory {
        private static NewInstanceFactory sInstance;

        static NewInstanceFactory getInstance() {
            if (sInstance == null) {
                sInstance = new NewInstanceFactory();
            }
            return sInstance;
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            String str = "Cannot create an instance of ";
            try {
                return (ViewModel) modelClass.newInstance();
            } catch (InstantiationException e) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(modelClass);
                throw new RuntimeException(sb.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(modelClass);
                throw new RuntimeException(sb2.toString(), e2);
            }
        }
    }

    static class OnRequeryFactory {
        OnRequeryFactory() {
        }

        /* access modifiers changed from: 0000 */
        public void onRequery(ViewModel viewModel) {
        }
    }

    public ViewModelProvider(ViewModelStoreOwner owner) {
        Factory factory;
        ViewModelStore viewModelStore = owner.getViewModelStore();
        if (owner instanceof HasDefaultViewModelProviderFactory) {
            factory = ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelProviderFactory();
        } else {
            factory = NewInstanceFactory.getInstance();
        }
        this(viewModelStore, factory);
    }

    public ViewModelProvider(ViewModelStoreOwner owner, Factory factory) {
        this(owner.getViewModelStore(), factory);
    }

    public ViewModelProvider(ViewModelStore store, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = store;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("androidx.lifecycle.ViewModelProvider.DefaultKey:");
            sb.append(canonicalName);
            return get(sb.toString(), modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends ViewModel> T get(String key, Class<T> modelClass) {
        ViewModel viewModel;
        ViewModel viewModel2 = this.mViewModelStore.get(key);
        if (modelClass.isInstance(viewModel2)) {
            Factory factory = this.mFactory;
            if (factory instanceof OnRequeryFactory) {
                ((OnRequeryFactory) factory).onRequery(viewModel2);
            }
            return viewModel2;
        }
        Factory factory2 = this.mFactory;
        if (factory2 instanceof KeyedFactory) {
            viewModel = ((KeyedFactory) factory2).create(key, modelClass);
        } else {
            viewModel = factory2.create(modelClass);
        }
        this.mViewModelStore.put(key, viewModel);
        return viewModel;
    }
}
