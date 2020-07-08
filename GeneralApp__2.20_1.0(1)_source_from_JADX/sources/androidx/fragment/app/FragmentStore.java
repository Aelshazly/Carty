package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class FragmentStore {
    private static final String TAG = "FragmentManager";
    private final HashMap<String, FragmentStateManager> mActive = new HashMap<>();
    private final ArrayList<Fragment> mAdded = new ArrayList<>();

    FragmentStore() {
    }

    /* access modifiers changed from: 0000 */
    public void resetActiveFragments() {
        this.mActive.clear();
    }

    /* access modifiers changed from: 0000 */
    public void restoreAddedFragments(List<String> added) {
        this.mAdded.clear();
        if (added != null) {
            for (String who : added) {
                Fragment f = findActiveFragment(who);
                if (f != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("restoreSaveState: added (");
                        sb.append(who);
                        sb.append("): ");
                        sb.append(f);
                        Log.v(TAG, sb.toString());
                    }
                    addFragment(f);
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("No instantiated fragment for (");
                    sb2.append(who);
                    sb2.append(")");
                    throw new IllegalStateException(sb2.toString());
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void makeActive(FragmentStateManager newlyActive) {
        this.mActive.put(newlyActive.getFragment().mWho, newlyActive);
    }

    /* access modifiers changed from: 0000 */
    public void addFragment(Fragment fragment) {
        if (!this.mAdded.contains(fragment)) {
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Fragment already added: ");
        sb.append(fragment);
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public void dispatchStateChange(int state) {
        Iterator it = this.mAdded.iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = (FragmentStateManager) this.mActive.get(((Fragment) it.next()).mWho);
            if (fragmentStateManager != null) {
                fragmentStateManager.setFragmentManagerState(state);
            }
        }
        for (FragmentStateManager fragmentStateManager2 : this.mActive.values()) {
            if (fragmentStateManager2 != null) {
                fragmentStateManager2.setFragmentManagerState(state);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeFragment(Fragment fragment) {
        synchronized (this.mAdded) {
            this.mAdded.remove(fragment);
        }
        fragment.mAdded = false;
    }

    /* access modifiers changed from: 0000 */
    public void makeInactive(FragmentStateManager newlyInactive) {
        Fragment f = newlyInactive.getFragment();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.getFragment();
                if (f.mWho.equals(fragment.mTargetWho)) {
                    fragment.mTarget = f;
                    fragment.mTargetWho = null;
                }
            }
        }
        this.mActive.put(f.mWho, null);
        if (f.mTargetWho != null) {
            f.mTarget = findActiveFragment(f.mTargetWho);
        }
    }

    /* access modifiers changed from: 0000 */
    public void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<FragmentState> saveActiveFragments() {
        ArrayList<FragmentState> active = new ArrayList<>(this.mActive.size());
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment f = fragmentStateManager.getFragment();
                FragmentState fs = fragmentStateManager.saveState();
                active.add(fs);
                if (FragmentManager.isLoggingEnabled(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved state of ");
                    sb.append(f);
                    sb.append(": ");
                    sb.append(fs.mSavedFragmentState);
                    Log.v(TAG, sb.toString());
                }
            }
        }
        return active;
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<String> saveAddedFragments() {
        synchronized (this.mAdded) {
            if (this.mAdded.isEmpty()) {
                return null;
            }
            ArrayList<String> added = new ArrayList<>(this.mAdded.size());
            Iterator it = this.mAdded.iterator();
            while (it.hasNext()) {
                Fragment f = (Fragment) it.next();
                added.add(f.mWho);
                if (FragmentManager.isLoggingEnabled(2)) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveAllState: adding fragment (");
                    sb.append(f.mWho);
                    sb.append("): ");
                    sb.append(f);
                    Log.v(str, sb.toString());
                }
            }
            return added;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Fragment> getFragments() {
        ArrayList arrayList;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            arrayList = new ArrayList(this.mAdded);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public List<Fragment> getActiveFragments() {
        ArrayList<Fragment> activeFragments = new ArrayList<>();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                activeFragments.add(fragmentStateManager.getFragment());
            } else {
                activeFragments.add(null);
            }
        }
        return activeFragments;
    }

    /* access modifiers changed from: 0000 */
    public int getActiveFragmentCount() {
        return this.mActive.size();
    }

    /* access modifiers changed from: 0000 */
    public Fragment findFragmentById(int id) {
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = (Fragment) this.mAdded.get(i);
            if (f != null && f.mFragmentId == id) {
                return f;
            }
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment f2 = fragmentStateManager.getFragment();
                if (f2.mFragmentId == id) {
                    return f2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Fragment findFragmentByTag(String tag) {
        if (tag != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (tag != null) {
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                if (fragmentStateManager != null) {
                    Fragment f2 = fragmentStateManager.getFragment();
                    if (tag.equals(f2.mTag)) {
                        return f2;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean containsActiveFragment(String who) {
        return this.mActive.containsKey(who);
    }

    /* access modifiers changed from: 0000 */
    public FragmentStateManager getFragmentStateManager(String who) {
        return (FragmentStateManager) this.mActive.get(who);
    }

    /* access modifiers changed from: 0000 */
    public Fragment findFragmentByWho(String who) {
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment findFragmentByWho = fragmentStateManager.getFragment().findFragmentByWho(who);
                Fragment f = findFragmentByWho;
                if (findFragmentByWho != null) {
                    return f;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Fragment findActiveFragment(String who) {
        FragmentStateManager fragmentStateManager = (FragmentStateManager) this.mActive.get(who);
        if (fragmentStateManager != null) {
            return fragmentStateManager.getFragment();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Fragment findFragmentUnder(Fragment f) {
        ViewGroup container = f.mContainer;
        View view = f.mView;
        if (container == null || view == null) {
            return null;
        }
        for (int i = this.mAdded.indexOf(f) - 1; i >= 0; i--) {
            Fragment underFragment = (Fragment) this.mAdded.get(i);
            if (underFragment.mContainer == container && underFragment.mView != null) {
                return underFragment;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append("    ");
        String innerPrefix = sb.toString();
        if (!this.mActive.isEmpty()) {
            writer.print(prefix);
            writer.print("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                writer.print(prefix);
                if (fragmentStateManager != null) {
                    Fragment f = fragmentStateManager.getFragment();
                    writer.println(f);
                    f.dump(innerPrefix, fd, writer, args);
                } else {
                    writer.println("null");
                }
            }
        }
        int count = this.mAdded.size();
        if (count > 0) {
            writer.print(prefix);
            writer.println("Added Fragments:");
            for (int i = 0; i < count; i++) {
                Fragment f2 = (Fragment) this.mAdded.get(i);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i);
                writer.print(": ");
                writer.println(f2.toString());
            }
        }
    }
}
