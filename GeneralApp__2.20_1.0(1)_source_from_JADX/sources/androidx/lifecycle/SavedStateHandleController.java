package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistry.AutoRecreated;
import androidx.savedstate.SavedStateRegistryOwner;

final class SavedStateHandleController implements LifecycleEventObserver {
    static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";
    private final SavedStateHandle mHandle;
    private boolean mIsAttached = false;
    private final String mKey;

    static final class OnRecreation implements AutoRecreated {
        OnRecreation() {
        }

        public void onRecreated(SavedStateRegistryOwner owner) {
            if (owner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) owner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = owner.getSavedStateRegistry();
                for (String key : viewModelStore.keys()) {
                    SavedStateHandleController.attachHandleIfNeeded(viewModelStore.get(key), savedStateRegistry, owner.getLifecycle());
                }
                if (!viewModelStore.keys().isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
        }
    }

    SavedStateHandleController(String key, SavedStateHandle handle) {
        this.mKey = key;
        this.mHandle = handle;
    }

    /* access modifiers changed from: 0000 */
    public boolean isAttached() {
        return this.mIsAttached;
    }

    /* access modifiers changed from: 0000 */
    public void attachToLifecycle(SavedStateRegistry registry, Lifecycle lifecycle) {
        if (!this.mIsAttached) {
            this.mIsAttached = true;
            lifecycle.addObserver(this);
            registry.registerSavedStateProvider(this.mKey, this.mHandle.savedStateProvider());
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public void onStateChanged(LifecycleOwner source, Event event) {
        if (event == Event.ON_DESTROY) {
            this.mIsAttached = false;
            source.getLifecycle().removeObserver(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public SavedStateHandle getHandle() {
        return this.mHandle;
    }

    static SavedStateHandleController create(SavedStateRegistry registry, Lifecycle lifecycle, String key, Bundle defaultArgs) {
        SavedStateHandleController controller = new SavedStateHandleController(key, SavedStateHandle.createHandle(registry.consumeRestoredStateForKey(key), defaultArgs));
        controller.attachToLifecycle(registry, lifecycle);
        tryToAddRecreator(registry, lifecycle);
        return controller;
    }

    static void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry registry, Lifecycle lifecycle) {
        SavedStateHandleController controller = (SavedStateHandleController) viewModel.getTag(TAG_SAVED_STATE_HANDLE_CONTROLLER);
        if (controller != null && !controller.isAttached()) {
            controller.attachToLifecycle(registry, lifecycle);
            tryToAddRecreator(registry, lifecycle);
        }
    }

    private static void tryToAddRecreator(final SavedStateRegistry registry, final Lifecycle lifecycle) {
        State currentState = lifecycle.getCurrentState();
        if (currentState == State.INITIALIZED || currentState.isAtLeast(State.STARTED)) {
            registry.runOnNextRecreation(OnRecreation.class);
        } else {
            lifecycle.addObserver(new LifecycleEventObserver() {
                public void onStateChanged(LifecycleOwner source, Event event) {
                    if (event == Event.ON_START) {
                        lifecycle.removeObserver(this);
                        registry.runOnNextRecreation(OnRecreation.class);
                    }
                }
            });
        }
    }
}
