package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference.BaseSavedState;
import androidx.preference.Preference.SummaryProvider;

public class ListPreference extends DialogPreference {
    private static final String TAG = "ListPreference";
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mSummary;
    private String mValue;
    private boolean mValueSet;

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        String mValue;

        SavedState(Parcel source) {
            super(source);
            this.mValue = source.readString();
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(this.mValue);
        }
    }

    public static final class SimpleSummaryProvider implements SummaryProvider<ListPreference> {
        private static SimpleSummaryProvider sSimpleSummaryProvider;

        private SimpleSummaryProvider() {
        }

        public static SimpleSummaryProvider getInstance() {
            if (sSimpleSummaryProvider == null) {
                sSimpleSummaryProvider = new SimpleSummaryProvider();
            }
            return sSimpleSummaryProvider;
        }

        public CharSequence provideSummary(ListPreference preference) {
            if (TextUtils.isEmpty(preference.getEntry())) {
                return preference.getContext().getString(C1048R.string.not_set);
            }
            return preference.getEntry();
        }
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, C1048R.styleable.ListPreference, defStyleAttr, defStyleRes);
        this.mEntries = TypedArrayUtils.getTextArray(a, C1048R.styleable.ListPreference_entries, C1048R.styleable.ListPreference_android_entries);
        this.mEntryValues = TypedArrayUtils.getTextArray(a, C1048R.styleable.ListPreference_entryValues, C1048R.styleable.ListPreference_android_entryValues);
        if (TypedArrayUtils.getBoolean(a, C1048R.styleable.ListPreference_useSimpleSummaryProvider, C1048R.styleable.ListPreference_useSimpleSummaryProvider, false)) {
            setSummaryProvider(SimpleSummaryProvider.getInstance());
        }
        a.recycle();
        TypedArray a2 = context.obtainStyledAttributes(attrs, C1048R.styleable.Preference, defStyleAttr, defStyleRes);
        this.mSummary = TypedArrayUtils.getString(a2, C1048R.styleable.Preference_summary, C1048R.styleable.Preference_android_summary);
        a2.recycle();
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ListPreference(Context context, AttributeSet attrs) {
        this(context, attrs, TypedArrayUtils.getAttr(context, C1048R.attr.dialogPreferenceStyle, 16842897));
    }

    public ListPreference(Context context) {
        this(context, null);
    }

    public void setEntries(CharSequence[] entries) {
        this.mEntries = entries;
    }

    public void setEntries(int entriesResId) {
        setEntries(getContext().getResources().getTextArray(entriesResId));
    }

    public CharSequence[] getEntries() {
        return this.mEntries;
    }

    public void setEntryValues(CharSequence[] entryValues) {
        this.mEntryValues = entryValues;
    }

    public void setEntryValues(int entryValuesResId) {
        setEntryValues(getContext().getResources().getTextArray(entryValuesResId));
    }

    public CharSequence[] getEntryValues() {
        return this.mEntryValues;
    }

    public void setSummary(CharSequence summary) {
        super.setSummary(summary);
        if (summary == null && this.mSummary != null) {
            this.mSummary = null;
        } else if (summary != null && !summary.equals(this.mSummary)) {
            this.mSummary = summary.toString();
        }
    }

    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().provideSummary(this);
        }
        CharSequence entry = getEntry();
        CharSequence summary = super.getSummary();
        String str = this.mSummary;
        if (str == null) {
            return summary;
        }
        Object[] objArr = new Object[1];
        objArr[0] = entry == null ? "" : entry;
        String formattedString = String.format(str, objArr);
        if (TextUtils.equals(formattedString, summary)) {
            return summary;
        }
        Log.w(TAG, "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return formattedString;
    }

    public void setValue(String value) {
        boolean changed = !TextUtils.equals(this.mValue, value);
        if (changed || !this.mValueSet) {
            this.mValue = value;
            this.mValueSet = true;
            persistString(value);
            if (changed) {
                notifyChanged();
            }
        }
    }

    public String getValue() {
        return this.mValue;
    }

    public CharSequence getEntry() {
        int index = getValueIndex();
        if (index >= 0) {
            CharSequence[] charSequenceArr = this.mEntries;
            if (charSequenceArr != null) {
                return charSequenceArr[index];
            }
        }
        return null;
    }

    public int findIndexOfValue(String value) {
        if (value != null) {
            CharSequence[] charSequenceArr = this.mEntryValues;
            if (charSequenceArr != null) {
                for (int i = charSequenceArr.length - 1; i >= 0; i--) {
                    if (this.mEntryValues[i].equals(value)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public void setValueIndex(int index) {
        CharSequence[] charSequenceArr = this.mEntryValues;
        if (charSequenceArr != null) {
            setValue(charSequenceArr[index].toString());
        }
    }

    private int getValueIndex() {
        return findIndexOfValue(this.mValue);
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(Object defaultValue) {
        setValue(getPersistedString((String) defaultValue));
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }
        SavedState myState = new SavedState(superState);
        myState.mValue = getValue();
        return myState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        setValue(myState.mValue);
    }
}
