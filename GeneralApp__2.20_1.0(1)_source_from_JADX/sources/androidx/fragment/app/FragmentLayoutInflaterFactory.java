package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import androidx.fragment.C1016R;

class FragmentLayoutInflaterFactory implements Factory2 {
    private static final String TAG = "FragmentManager";
    private final FragmentManager mFragmentManager;

    FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (FragmentContainerView.class.getName().equals(name)) {
            return new FragmentContainerView(context, attrs, this.mFragmentManager);
        }
        Fragment fragment = null;
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue(null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, C1016R.styleable.Fragment);
        if (fname == null) {
            fname = a.getString(C1016R.styleable.Fragment_android_name);
        }
        int id = a.getResourceId(C1016R.styleable.Fragment_android_id, -1);
        String tag = a.getString(C1016R.styleable.Fragment_android_tag);
        a.recycle();
        if (fname == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), fname)) {
            return null;
        }
        int containerId = parent != null ? parent.getId() : 0;
        if (containerId == -1 && id == -1 && tag == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(attrs.getPositionDescription());
            sb.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
            sb.append(fname);
            throw new IllegalArgumentException(sb.toString());
        }
        if (id != -1) {
            fragment = this.mFragmentManager.findFragmentById(id);
        }
        if (fragment == null && tag != null) {
            fragment = this.mFragmentManager.findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = this.mFragmentManager.findFragmentById(containerId);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("onCreateView: id=0x");
            sb2.append(Integer.toHexString(id));
            sb2.append(" fname=");
            sb2.append(fname);
            sb2.append(" existing=");
            sb2.append(fragment);
            Log.v(TAG, sb2.toString());
        }
        if (fragment == null) {
            fragment = this.mFragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), fname);
            fragment.mFromLayout = true;
            fragment.mFragmentId = id != 0 ? id : containerId;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            FragmentManager fragmentManager = this.mFragmentManager;
            fragment.mFragmentManager = fragmentManager;
            fragment.mHost = fragmentManager.mHost;
            fragment.onInflate(this.mFragmentManager.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            this.mFragmentManager.addFragment(fragment);
            this.mFragmentManager.moveToState(fragment);
        } else if (!fragment.mInLayout) {
            fragment.mInLayout = true;
            fragment.mHost = this.mFragmentManager.mHost;
            fragment.onInflate(this.mFragmentManager.mHost.getContext(), attrs, fragment.mSavedFragmentState);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(attrs.getPositionDescription());
            sb3.append(": Duplicate id 0x");
            sb3.append(Integer.toHexString(id));
            sb3.append(", tag ");
            sb3.append(tag);
            sb3.append(", or parent id 0x");
            sb3.append(Integer.toHexString(containerId));
            sb3.append(" with another fragment for ");
            sb3.append(fname);
            throw new IllegalArgumentException(sb3.toString());
        }
        if (this.mFragmentManager.mCurState >= 1 || !fragment.mFromLayout) {
            this.mFragmentManager.moveToState(fragment);
        } else {
            this.mFragmentManager.moveToState(fragment, 1);
        }
        if (fragment.mView != null) {
            if (id != 0) {
                fragment.mView.setId(id);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(tag);
            }
            return fragment.mView;
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Fragment ");
        sb4.append(fname);
        sb4.append(" did not create a view.");
        throw new IllegalStateException(sb4.toString());
    }
}
