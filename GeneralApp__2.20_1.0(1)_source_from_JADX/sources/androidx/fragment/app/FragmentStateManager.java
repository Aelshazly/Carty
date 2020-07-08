package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.core.p003os.EnvironmentCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.C1016R;
import androidx.fragment.app.Fragment.SavedState;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.ViewModelStoreOwner;

class FragmentStateManager {
    private static final String TAG = "FragmentManager";
    private static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    private static final String TARGET_STATE_TAG = "android:target_state";
    private static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    private static final String VIEW_STATE_TAG = "android:view_state";
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    private final Fragment mFragment;
    private int mFragmentManagerState = -1;

    /* renamed from: androidx.fragment.app.FragmentStateManager$1 */
    static /* synthetic */ class C02131 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher dispatcher, Fragment fragment) {
        this.mDispatcher = dispatcher;
        this.mFragment = fragment;
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher dispatcher, ClassLoader classLoader, FragmentFactory fragmentFactory, FragmentState fs) {
        this.mDispatcher = dispatcher;
        this.mFragment = fragmentFactory.instantiate(classLoader, fs.mClassName);
        if (fs.mArguments != null) {
            fs.mArguments.setClassLoader(classLoader);
        }
        this.mFragment.setArguments(fs.mArguments);
        this.mFragment.mWho = fs.mWho;
        this.mFragment.mFromLayout = fs.mFromLayout;
        Fragment fragment = this.mFragment;
        fragment.mRestored = true;
        fragment.mFragmentId = fs.mFragmentId;
        this.mFragment.mContainerId = fs.mContainerId;
        this.mFragment.mTag = fs.mTag;
        this.mFragment.mRetainInstance = fs.mRetainInstance;
        this.mFragment.mRemoving = fs.mRemoving;
        this.mFragment.mDetached = fs.mDetached;
        this.mFragment.mHidden = fs.mHidden;
        this.mFragment.mMaxState = State.values()[fs.mMaxLifecycleState];
        if (fs.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState = fs.mSavedFragmentState;
        } else {
            this.mFragment.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instantiated fragment ");
            sb.append(this.mFragment);
            Log.v(TAG, sb.toString());
        }
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher dispatcher, Fragment retainedFragment, FragmentState fs) {
        this.mDispatcher = dispatcher;
        this.mFragment = retainedFragment;
        Fragment fragment = this.mFragment;
        fragment.mSavedViewState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        fragment.mTargetWho = fragment.mTarget != null ? this.mFragment.mTarget.mWho : null;
        this.mFragment.mTarget = null;
        if (fs.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState = fs.mSavedFragmentState;
        } else {
            this.mFragment.mSavedFragmentState = new Bundle();
        }
    }

    /* access modifiers changed from: 0000 */
    public Fragment getFragment() {
        return this.mFragment;
    }

    /* access modifiers changed from: 0000 */
    public void setFragmentManagerState(int state) {
        this.mFragmentManagerState = state;
    }

    /* access modifiers changed from: 0000 */
    public int computeMaxState() {
        int maxState = this.mFragmentManagerState;
        if (this.mFragment.mFromLayout) {
            if (this.mFragment.mInLayout) {
                maxState = Math.max(this.mFragmentManagerState, 1);
            } else if (this.mFragmentManagerState < 2) {
                maxState = Math.min(maxState, this.mFragment.mState);
            } else {
                maxState = Math.min(maxState, 1);
            }
        }
        if (!this.mFragment.mAdded) {
            maxState = Math.min(maxState, 1);
        }
        if (this.mFragment.mRemoving) {
            if (this.mFragment.isInBackStack()) {
                maxState = Math.min(maxState, 1);
            } else {
                maxState = Math.min(maxState, -1);
            }
        }
        if (this.mFragment.mDeferStart && this.mFragment.mState < 3) {
            maxState = Math.min(maxState, 2);
        }
        int i = C02131.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.mFragment.mMaxState.ordinal()];
        if (i == 1) {
            return maxState;
        }
        if (i == 2) {
            return Math.min(maxState, 3);
        }
        if (i != 3) {
            return Math.min(maxState, -1);
        }
        return Math.min(maxState, 1);
    }

    /* access modifiers changed from: 0000 */
    public void ensureInflatedView() {
        if (this.mFragment.mFromLayout && this.mFragment.mInLayout && !this.mFragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("moveto CREATE_VIEW: ");
                sb.append(this.mFragment);
                Log.d(TAG, sb.toString());
            }
            Fragment fragment = this.mFragment;
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, this.mFragment.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(C1016R.C1019id.fragment_container_view_tag, this.mFragment);
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                Fragment fragment2 = this.mFragment;
                fragment2.onViewCreated(fragment2.mView, this.mFragment.mSavedFragmentState);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment3, fragment3.mView, this.mFragment.mSavedFragmentState, false);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void restoreState(ClassLoader classLoader) {
        if (this.mFragment.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState.setClassLoader(classLoader);
            Fragment fragment = this.mFragment;
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
            Fragment fragment2 = this.mFragment;
            fragment2.mTargetWho = fragment2.mSavedFragmentState.getString(TARGET_STATE_TAG);
            if (this.mFragment.mTargetWho != null) {
                Fragment fragment3 = this.mFragment;
                fragment3.mTargetRequestCode = fragment3.mSavedFragmentState.getInt(TARGET_REQUEST_CODE_STATE_TAG, 0);
            }
            if (this.mFragment.mSavedUserVisibleHint != null) {
                Fragment fragment4 = this.mFragment;
                fragment4.mUserVisibleHint = fragment4.mSavedUserVisibleHint.booleanValue();
                this.mFragment.mSavedUserVisibleHint = null;
            } else {
                Fragment fragment5 = this.mFragment;
                fragment5.mUserVisibleHint = fragment5.mSavedFragmentState.getBoolean(USER_VISIBLE_HINT_TAG, true);
            }
            if (!this.mFragment.mUserVisibleHint) {
                this.mFragment.mDeferStart = true;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void attach(FragmentHostCallback<?> host, FragmentManager fragmentManager, Fragment parentFragment) {
        Fragment fragment = this.mFragment;
        fragment.mHost = host;
        fragment.mParentFragment = parentFragment;
        fragment.mFragmentManager = fragmentManager;
        this.mDispatcher.dispatchOnFragmentPreAttached(fragment, host.getContext(), false);
        this.mFragment.performAttach();
        if (this.mFragment.mParentFragment == null) {
            host.onAttachFragment(this.mFragment);
        } else {
            this.mFragment.mParentFragment.onAttachFragment(this.mFragment);
        }
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, host.getContext(), false);
    }

    /* access modifiers changed from: 0000 */
    public void create() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto CREATED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        if (!this.mFragment.mIsCreated) {
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
            Fragment fragment2 = this.mFragment;
            fragment2.performCreate(fragment2.mSavedFragmentState);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher2 = this.mDispatcher;
            Fragment fragment3 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher2.dispatchOnFragmentCreated(fragment3, fragment3.mSavedFragmentState, false);
            return;
        }
        Fragment fragment4 = this.mFragment;
        fragment4.restoreChildFragmentState(fragment4.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    /* access modifiers changed from: 0000 */
    public void createView(FragmentContainer fragmentContainer) {
        String resName;
        if (!this.mFragment.mFromLayout) {
            if (FragmentManager.isLoggingEnabled(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("moveto CREATE_VIEW: ");
                sb.append(this.mFragment);
                Log.d(TAG, sb.toString());
            }
            ViewGroup container = null;
            if (this.mFragment.mContainer != null) {
                container = this.mFragment.mContainer;
            } else if (this.mFragment.mContainerId != 0) {
                if (this.mFragment.mContainerId != -1) {
                    container = (ViewGroup) fragmentContainer.onFindViewById(this.mFragment.mContainerId);
                    if (container == null && !this.mFragment.mRestored) {
                        try {
                            resName = this.mFragment.getResources().getResourceName(this.mFragment.mContainerId);
                        } catch (NotFoundException e) {
                            resName = EnvironmentCompat.MEDIA_UNKNOWN;
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("No view found for id 0x");
                        sb2.append(Integer.toHexString(this.mFragment.mContainerId));
                        sb2.append(" (");
                        sb2.append(resName);
                        sb2.append(") for fragment ");
                        sb2.append(this.mFragment);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Cannot create fragment ");
                    sb3.append(this.mFragment);
                    sb3.append(" for a container view with no id");
                    throw new IllegalArgumentException(sb3.toString());
                }
            }
            Fragment fragment = this.mFragment;
            fragment.mContainer = container;
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), container, this.mFragment.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                boolean z = false;
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(C1016R.C1019id.fragment_container_view_tag, this.mFragment);
                if (container != null) {
                    container.addView(this.mFragment.mView);
                }
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                ViewCompat.requestApplyInsets(this.mFragment.mView);
                Fragment fragment2 = this.mFragment;
                fragment2.onViewCreated(fragment2.mView, this.mFragment.mSavedFragmentState);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment3, fragment3.mView, this.mFragment.mSavedFragmentState, false);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mView.getVisibility() == 0 && this.mFragment.mContainer != null) {
                    z = true;
                }
                fragment4.mIsNewlyAdded = z;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto ACTIVITY_CREATED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        Fragment fragment = this.mFragment;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
        Fragment fragment2 = this.mFragment;
        fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentActivityCreated(fragment2, fragment2.mSavedFragmentState, false);
    }

    /* access modifiers changed from: 0000 */
    public void restoreViewState() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto RESTORE_VIEW_STATE: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        if (this.mFragment.mView != null) {
            Fragment fragment = this.mFragment;
            fragment.restoreViewState(fragment.mSavedFragmentState);
        }
        this.mFragment.mSavedFragmentState = null;
    }

    /* access modifiers changed from: 0000 */
    public void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto STARTED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    /* access modifiers changed from: 0000 */
    public void resume() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("moveto RESUMED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
    }

    /* access modifiers changed from: 0000 */
    public void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom RESUMED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    /* access modifiers changed from: 0000 */
    public void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom STARTED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }

    /* access modifiers changed from: 0000 */
    public FragmentState saveState() {
        FragmentState fs = new FragmentState(this.mFragment);
        if (this.mFragment.mState <= -1 || fs.mSavedFragmentState != null) {
            fs.mSavedFragmentState = this.mFragment.mSavedFragmentState;
        } else {
            fs.mSavedFragmentState = saveBasicState();
            if (this.mFragment.mTargetWho != null) {
                if (fs.mSavedFragmentState == null) {
                    fs.mSavedFragmentState = new Bundle();
                }
                fs.mSavedFragmentState.putString(TARGET_STATE_TAG, this.mFragment.mTargetWho);
                if (this.mFragment.mTargetRequestCode != 0) {
                    fs.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, this.mFragment.mTargetRequestCode);
                }
            }
        }
        return fs;
    }

    /* access modifiers changed from: 0000 */
    public SavedState saveInstanceState() {
        SavedState savedState = null;
        if (this.mFragment.mState <= -1) {
            return null;
        }
        Bundle result = saveBasicState();
        if (result != null) {
            savedState = new SavedState(result);
        }
        return savedState;
    }

    private Bundle saveBasicState() {
        Bundle result = new Bundle();
        this.mFragment.performSaveInstanceState(result);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, result, false);
        if (result.isEmpty()) {
            result = null;
        }
        if (this.mFragment.mView != null) {
            saveViewState();
        }
        if (this.mFragment.mSavedViewState != null) {
            if (result == null) {
                result = new Bundle();
            }
            result.putSparseParcelableArray(VIEW_STATE_TAG, this.mFragment.mSavedViewState);
        }
        if (!this.mFragment.mUserVisibleHint) {
            if (result == null) {
                result = new Bundle();
            }
            result.putBoolean(USER_VISIBLE_HINT_TAG, this.mFragment.mUserVisibleHint);
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    public void saveViewState() {
        if (this.mFragment.mView != null) {
            SparseArray<Parcelable> mStateArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(mStateArray);
            if (mStateArray.size() > 0) {
                this.mFragment.mSavedViewState = mStateArray;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void destroy(FragmentHostCallback<?> host, FragmentManagerViewModel nonConfig) {
        boolean shouldClear;
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom CREATED: ");
            sb.append(this.mFragment);
            Log.d(TAG, sb.toString());
        }
        boolean beingRemoved = this.mFragment.mRemoving && !this.mFragment.isInBackStack();
        if (beingRemoved || nonConfig.shouldDestroy(this.mFragment)) {
            if (host instanceof ViewModelStoreOwner) {
                shouldClear = nonConfig.isCleared();
            } else if (host.getContext() instanceof Activity) {
                shouldClear = true ^ ((Activity) host.getContext()).isChangingConfigurations();
            } else {
                shouldClear = true;
            }
            if (beingRemoved || shouldClear) {
                nonConfig.clearNonConfigState(this.mFragment);
            }
            this.mFragment.performDestroy();
            this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
            return;
        }
        this.mFragment.mState = 0;
    }

    /* access modifiers changed from: 0000 */
    public void detach(FragmentManagerViewModel nonConfig) {
        boolean isLoggingEnabled = FragmentManager.isLoggingEnabled(3);
        String str = TAG;
        if (isLoggingEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append("movefrom ATTACHED: ");
            sb.append(this.mFragment);
            Log.d(str, sb.toString());
        }
        this.mFragment.performDetach();
        boolean z = false;
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !this.mFragment.isInBackStack()) {
            z = true;
        }
        if (z || nonConfig.shouldDestroy(this.mFragment)) {
            if (FragmentManager.isLoggingEnabled(3)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("initState called for fragment: ");
                sb2.append(this.mFragment);
                Log.d(str, sb2.toString());
            }
            this.mFragment.initState();
        }
    }
}
