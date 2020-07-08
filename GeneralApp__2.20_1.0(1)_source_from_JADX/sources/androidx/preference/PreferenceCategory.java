package androidx.preference;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;

public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attrs) {
        this(context, attrs, TypedArrayUtils.getAttr(context, C1048R.attr.preferenceCategoryStyle, 16842892));
    }

    public PreferenceCategory(Context context) {
        this(context, null);
    }

    public boolean isEnabled() {
        return false;
    }

    public boolean shouldDisableDependents() {
        return !super.isEnabled();
    }

    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        if (VERSION.SDK_INT >= 28) {
            holder.itemView.setAccessibilityHeading(true);
        } else if (VERSION.SDK_INT < 21) {
            TypedValue value = new TypedValue();
            if (getContext().getTheme().resolveAttribute(C1048R.attr.colorAccent, value, true)) {
                TextView titleView = (TextView) holder.findViewById(16908310);
                if (titleView != null && titleView.getCurrentTextColor() == ContextCompat.getColor(getContext(), C1048R.C1049color.preference_fallback_accent_color)) {
                    titleView.setTextColor(value.data);
                }
            }
        }
    }

    @Deprecated
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat info) {
        super.onInitializeAccessibilityNodeInfo(info);
        if (VERSION.SDK_INT < 28) {
            CollectionItemInfoCompat existingItemInfo = info.getCollectionItemInfo();
            if (existingItemInfo != null) {
                info.setCollectionItemInfo(CollectionItemInfoCompat.obtain(existingItemInfo.getRowIndex(), existingItemInfo.getRowSpan(), existingItemInfo.getColumnIndex(), existingItemInfo.getColumnSpan(), true, existingItemInfo.isSelected()));
            }
        }
    }
}
