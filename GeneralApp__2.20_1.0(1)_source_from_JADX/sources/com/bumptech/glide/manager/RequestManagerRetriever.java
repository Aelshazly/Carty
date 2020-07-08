package com.bumptech.glide.manager;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Callback {
    private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory() {
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    private static final String FRAGMENT_INDEX_KEY = "key";
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private static final String TAG = "RMRetriever";
    private volatile RequestManager applicationManager;
    private final RequestManagerFactory factory;
    private final Handler handler;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
    private final Bundle tempBundle = new Bundle();
    private final ArrayMap<View, Fragment> tempViewToFragment = new ArrayMap<>();
    private final ArrayMap<View, androidx.fragment.app.Fragment> tempViewToSupportFragment = new ArrayMap<>();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context);
    }

    public RequestManagerRetriever(RequestManagerFactory factory2) {
        this.factory = factory2 != null ? factory2 : DEFAULT_FACTORY;
        this.handler = new Handler(Looper.getMainLooper(), this);
    }

    private RequestManager getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = this.factory.build(Glide.get(context.getApplicationContext()), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode(), context.getApplicationContext());
                }
            }
        }
        return this.applicationManager;
    }

    public RequestManager get(Context context) {
        if (context != null) {
            if (Util.isOnMainThread() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return get((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return get((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    return get(((ContextWrapper) context).getBaseContext());
                }
            }
            return getApplicationManager(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    public RequestManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return supportFragmentGet(activity, activity.getSupportFragmentManager(), null, isActivityVisible(activity));
    }

    public RequestManager get(androidx.fragment.app.Fragment fragment) {
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        }
        return supportFragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return fragmentGet(activity, activity.getFragmentManager(), null, isActivityVisible(activity));
    }

    public RequestManager get(View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activity = findActivity(view.getContext());
        if (activity == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            androidx.fragment.app.Fragment fragment = findSupportFragment(view, (FragmentActivity) activity);
            return fragment != null ? get(fragment) : get(activity);
        }
        Fragment fragment2 = findFragment(view, activity);
        if (fragment2 == null) {
            return get(activity);
        }
        return get(fragment2);
    }

    private static void findAllSupportFragmentsWithViews(Collection<androidx.fragment.app.Fragment> topLevelFragments, Map<View, androidx.fragment.app.Fragment> result) {
        if (topLevelFragments != null) {
            for (androidx.fragment.app.Fragment fragment : topLevelFragments) {
                if (!(fragment == null || fragment.getView() == null)) {
                    result.put(fragment.getView(), fragment);
                    findAllSupportFragmentsWithViews(fragment.getChildFragmentManager().getFragments(), result);
                }
            }
        }
    }

    private androidx.fragment.app.Fragment findSupportFragment(View target, FragmentActivity activity) {
        this.tempViewToSupportFragment.clear();
        findAllSupportFragmentsWithViews(activity.getSupportFragmentManager().getFragments(), this.tempViewToSupportFragment);
        androidx.fragment.app.Fragment result = null;
        View activityRoot = activity.findViewById(16908290);
        for (View current = target; !current.equals(activityRoot); current = (View) current.getParent()) {
            result = (androidx.fragment.app.Fragment) this.tempViewToSupportFragment.get(current);
            if (result != null || !(current.getParent() instanceof View)) {
                break;
            }
        }
        this.tempViewToSupportFragment.clear();
        return result;
    }

    @Deprecated
    private Fragment findFragment(View target, Activity activity) {
        this.tempViewToFragment.clear();
        findAllFragmentsWithViews(activity.getFragmentManager(), this.tempViewToFragment);
        Fragment result = null;
        View activityRoot = activity.findViewById(16908290);
        for (View current = target; !current.equals(activityRoot); current = (View) current.getParent()) {
            result = (Fragment) this.tempViewToFragment.get(current);
            if (result != null || !(current.getParent() instanceof View)) {
                break;
            }
        }
        this.tempViewToFragment.clear();
        return result;
    }

    @Deprecated
    private void findAllFragmentsWithViews(FragmentManager fragmentManager, ArrayMap<View, Fragment> result) {
        if (VERSION.SDK_INT >= 26) {
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                }
            }
            return;
        }
        findAllFragmentsWithViewsPreO(fragmentManager, result);
    }

    @Deprecated
    private void findAllFragmentsWithViewsPreO(FragmentManager fragmentManager, ArrayMap<View, Fragment> result) {
        int index = 0;
        while (true) {
            Bundle bundle = this.tempBundle;
            int index2 = index + 1;
            String str = FRAGMENT_INDEX_KEY;
            bundle.putInt(str, index);
            Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.tempBundle, str);
            } catch (Exception e) {
            }
            if (fragment != null) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    if (VERSION.SDK_INT >= 17) {
                        findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                    }
                }
                index = index2;
            } else {
                return;
            }
        }
    }

    private Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static void assertNotDestroyed(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @Deprecated
    public RequestManager get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (Util.isOnBackgroundThread() || VERSION.SDK_INT < 17) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public RequestManagerFragment getRequestManagerFragment(Activity activity) {
        return getRequestManagerFragment(activity.getFragmentManager(), null, isActivityVisible(activity));
    }

    private RequestManagerFragment getRequestManagerFragment(FragmentManager fm, Fragment parentHint, boolean isParentVisible) {
        String str = FRAGMENT_TAG;
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag(str);
        if (current == null) {
            current = (RequestManagerFragment) this.pendingRequestManagerFragments.get(fm);
            if (current == null) {
                current = new RequestManagerFragment();
                current.setParentFragmentHint(parentHint);
                if (isParentVisible) {
                    current.getGlideLifecycle().onStart();
                }
                this.pendingRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, str).commitAllowingStateLoss();
                this.handler.obtainMessage(1, fm).sendToTarget();
            }
        }
        return current;
    }

    @Deprecated
    private RequestManager fragmentGet(Context context, FragmentManager fm, Fragment parentHint, boolean isParentVisible) {
        RequestManagerFragment current = getRequestManagerFragment(fm, parentHint, isParentVisible);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager requestManager2 = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
        current.setRequestManager(requestManager2);
        return requestManager2;
    }

    /* access modifiers changed from: 0000 */
    public SupportRequestManagerFragment getSupportRequestManagerFragment(FragmentActivity activity) {
        return getSupportRequestManagerFragment(activity.getSupportFragmentManager(), null, isActivityVisible(activity));
    }

    private static boolean isActivityVisible(Activity activity) {
        return !activity.isFinishing();
    }

    private SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fm, androidx.fragment.app.Fragment parentHint, boolean isParentVisible) {
        String str = FRAGMENT_TAG;
        SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag(str);
        if (current == null) {
            current = (SupportRequestManagerFragment) this.pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                current = new SupportRequestManagerFragment();
                current.setParentFragmentHint(parentHint);
                if (isParentVisible) {
                    current.getGlideLifecycle().onStart();
                }
                this.pendingSupportRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add((androidx.fragment.app.Fragment) current, str).commitAllowingStateLoss();
                this.handler.obtainMessage(2, fm).sendToTarget();
            }
        }
        return current;
    }

    private RequestManager supportFragmentGet(Context context, androidx.fragment.app.FragmentManager fm, androidx.fragment.app.Fragment parentHint, boolean isParentVisible) {
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm, parentHint, isParentVisible);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager requestManager2 = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
        current.setRequestManager(requestManager2);
        return requestManager2;
    }

    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        int i = message.what;
        if (i == 1) {
            FragmentManager fm = (FragmentManager) message.obj;
            key = fm;
            removed = this.pendingRequestManagerFragments.remove(fm);
        } else if (i != 2) {
            handled = false;
        } else {
            androidx.fragment.app.FragmentManager supportFm = (androidx.fragment.app.FragmentManager) message.obj;
            key = supportFm;
            removed = this.pendingSupportRequestManagerFragments.remove(supportFm);
        }
        if (handled && removed == null) {
            String str = TAG;
            if (Log.isLoggable(str, 5)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to remove expected request manager fragment, manager: ");
                sb.append(key);
                Log.w(str, sb.toString());
            }
        }
        return handled;
    }
}
