package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class SavedStateViewModelFactory extends KeyedFactory {
    private static final Class<?>[] ANDROID_VIEWMODEL_SIGNATURE = {Application.class, SavedStateHandle.class};
    private static final Class<?>[] VIEWMODEL_SIGNATURE = {SavedStateHandle.class};
    private final Application mApplication;
    private final Bundle mDefaultArgs;
    private final AndroidViewModelFactory mFactory;
    private final Lifecycle mLifecycle;
    private final SavedStateRegistry mSavedStateRegistry;

    public SavedStateViewModelFactory(Application application, SavedStateRegistryOwner owner) {
        this(application, owner, null);
    }

    public SavedStateViewModelFactory(Application application, SavedStateRegistryOwner owner, Bundle defaultArgs) {
        this.mSavedStateRegistry = owner.getSavedStateRegistry();
        this.mLifecycle = owner.getLifecycle();
        this.mDefaultArgs = defaultArgs;
        this.mApplication = application;
        this.mFactory = AndroidViewModelFactory.getInstance(application);
    }

    public <T extends ViewModel> T create(String key, Class<T> modelClass) {
        Constructor<T> constructor;
        T viewmodel;
        boolean isAndroidViewModel = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (isAndroidViewModel) {
            constructor = findMatchingConstructor(modelClass, ANDROID_VIEWMODEL_SIGNATURE);
        } else {
            constructor = findMatchingConstructor(modelClass, VIEWMODEL_SIGNATURE);
        }
        if (constructor == null) {
            return this.mFactory.create(modelClass);
        }
        SavedStateHandleController controller = SavedStateHandleController.create(this.mSavedStateRegistry, this.mLifecycle, key, this.mDefaultArgs);
        if (isAndroidViewModel) {
            try {
                viewmodel = (ViewModel) constructor.newInstance(new Object[]{this.mApplication, controller.getHandle()});
            } catch (IllegalAccessException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to access ");
                sb.append(modelClass);
                throw new RuntimeException(sb.toString(), e);
            } catch (InstantiationException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("A ");
                sb2.append(modelClass);
                sb2.append(" cannot be instantiated.");
                throw new RuntimeException(sb2.toString(), e2);
            } catch (InvocationTargetException e3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("An exception happened in constructor of ");
                sb3.append(modelClass);
                throw new RuntimeException(sb3.toString(), e3.getCause());
            }
        } else {
            viewmodel = (ViewModel) constructor.newInstance(new Object[]{controller.getHandle()});
        }
        viewmodel.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", controller);
        return viewmodel;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName, modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    private static <T> Constructor<T> findMatchingConstructor(Class<T> modelClass, Class<?>[] signature) {
        Constructor<?>[] constructors;
        for (Constructor<?> constructor : modelClass.getConstructors()) {
            if (Arrays.equals(signature, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void onRequery(ViewModel viewModel) {
        SavedStateHandleController.attachHandleIfNeeded(viewModel, this.mSavedStateRegistry, this.mLifecycle);
    }
}
