package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new Creator<FragmentState>() {
        public FragmentState createFromParcel(Parcel in) {
            return new FragmentState(in);
        }

        public FragmentState[] newArray(int size) {
            return new FragmentState[size];
        }
    };
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    FragmentState(Fragment frag) {
        this.mClassName = frag.getClass().getName();
        this.mWho = frag.mWho;
        this.mFromLayout = frag.mFromLayout;
        this.mFragmentId = frag.mFragmentId;
        this.mContainerId = frag.mContainerId;
        this.mTag = frag.mTag;
        this.mRetainInstance = frag.mRetainInstance;
        this.mRemoving = frag.mRemoving;
        this.mDetached = frag.mDetached;
        this.mArguments = frag.mArguments;
        this.mHidden = frag.mHidden;
        this.mMaxLifecycleState = frag.mMaxState.ordinal();
    }

    FragmentState(Parcel in) {
        this.mClassName = in.readString();
        this.mWho = in.readString();
        boolean z = true;
        this.mFromLayout = in.readInt() != 0;
        this.mFragmentId = in.readInt();
        this.mContainerId = in.readInt();
        this.mTag = in.readString();
        this.mRetainInstance = in.readInt() != 0;
        this.mRemoving = in.readInt() != 0;
        this.mDetached = in.readInt() != 0;
        this.mArguments = in.readBundle();
        if (in.readInt() == 0) {
            z = false;
        }
        this.mHidden = z;
        this.mSavedFragmentState = in.readBundle();
        this.mMaxLifecycleState = in.readInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.mClassName);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")}:");
        if (this.mFromLayout) {
            sb.append(" fromLayout");
        }
        if (this.mContainerId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mContainerId));
        }
        String str = this.mTag;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        if (this.mRetainInstance) {
            sb.append(" retainInstance");
        }
        if (this.mRemoving) {
            sb.append(" removing");
        }
        if (this.mDetached) {
            sb.append(" detached");
        }
        if (this.mHidden) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mClassName);
        dest.writeString(this.mWho);
        dest.writeInt(this.mFromLayout ? 1 : 0);
        dest.writeInt(this.mFragmentId);
        dest.writeInt(this.mContainerId);
        dest.writeString(this.mTag);
        dest.writeInt(this.mRetainInstance ? 1 : 0);
        dest.writeInt(this.mRemoving ? 1 : 0);
        dest.writeInt(this.mDetached ? 1 : 0);
        dest.writeBundle(this.mArguments);
        dest.writeInt(this.mHidden ? 1 : 0);
        dest.writeBundle(this.mSavedFragmentState);
        dest.writeInt(this.mMaxLifecycleState);
    }
}