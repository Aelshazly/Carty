package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1124R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BadgeDrawable extends Drawable implements TextDrawableDelegate {
    private static final int BADGE_NUMBER_NONE = -1;
    public static final int BOTTOM_END = 8388693;
    public static final int BOTTOM_START = 8388691;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    private static final int DEFAULT_MAX_BADGE_CHARACTER_COUNT = 4;
    private static final int DEFAULT_STYLE = C1124R.style.Widget_MaterialComponents_Badge;
    private static final int DEFAULT_THEME_ATTR = C1124R.attr.badgeStyle;
    private static final int MAX_CIRCULAR_BADGE_NUMBER_COUNT = 9;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;
    private WeakReference<View> anchorViewRef;
    private final Rect badgeBounds = new Rect();
    private float badgeCenterX;
    private float badgeCenterY;
    private final float badgeRadius;
    private final float badgeWidePadding;
    private final float badgeWithTextRadius;
    private final WeakReference<Context> contextRef;
    private float cornerRadius;
    private WeakReference<ViewGroup> customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final SavedState savedState;
    private final MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
    private final TextDrawableHelper textDrawableHelper;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeGravity {
    }

    public static final class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        /* access modifiers changed from: private */
        public int alpha = 255;
        /* access modifiers changed from: private */
        public int backgroundColor;
        /* access modifiers changed from: private */
        public int badgeGravity;
        /* access modifiers changed from: private */
        public int badgeTextColor;
        /* access modifiers changed from: private */
        public CharSequence contentDescriptionNumberless;
        /* access modifiers changed from: private */
        public int contentDescriptionQuantityStrings;
        /* access modifiers changed from: private */
        public int maxCharacterCount;
        /* access modifiers changed from: private */
        public int number = -1;

        public SavedState(Context context) {
            this.badgeTextColor = new TextAppearance(context, C1124R.style.TextAppearance_MaterialComponents_Badge).textColor.getDefaultColor();
            this.contentDescriptionNumberless = context.getString(C1124R.string.mtrl_badge_numberless_content_description);
            this.contentDescriptionQuantityStrings = C1124R.plurals.mtrl_badge_content_description;
        }

        protected SavedState(Parcel in) {
            this.backgroundColor = in.readInt();
            this.badgeTextColor = in.readInt();
            this.alpha = in.readInt();
            this.number = in.readInt();
            this.maxCharacterCount = in.readInt();
            this.contentDescriptionNumberless = in.readString();
            this.contentDescriptionQuantityStrings = in.readInt();
            this.badgeGravity = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.backgroundColor);
            dest.writeInt(this.badgeTextColor);
            dest.writeInt(this.alpha);
            dest.writeInt(this.number);
            dest.writeInt(this.maxCharacterCount);
            dest.writeString(this.contentDescriptionNumberless.toString());
            dest.writeInt(this.contentDescriptionQuantityStrings);
            dest.writeInt(this.badgeGravity);
        }
    }

    public SavedState getSavedState() {
        return this.savedState;
    }

    static BadgeDrawable createFromSavedState(Context context, SavedState savedState2) {
        BadgeDrawable badge = new BadgeDrawable(context);
        badge.restoreFromSavedState(savedState2);
        return badge;
    }

    public static BadgeDrawable create(Context context) {
        return createFromAttributes(context, null, DEFAULT_THEME_ATTR, DEFAULT_STYLE);
    }

    public static BadgeDrawable createFromResource(Context context, int id) {
        AttributeSet attrs = DrawableUtils.parseDrawableXml(context, id, "badge");
        int style = attrs.getStyleAttribute();
        if (style == 0) {
            style = DEFAULT_STYLE;
        }
        return createFromAttributes(context, attrs, DEFAULT_THEME_ATTR, style);
    }

    private static BadgeDrawable createFromAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        BadgeDrawable badge = new BadgeDrawable(context);
        badge.loadDefaultStateFromAttributes(context, attrs, defStyleAttr, defStyleRes);
        return badge;
    }

    public void setVisible(boolean visible) {
        setVisible(visible, false);
    }

    private void restoreFromSavedState(SavedState savedState2) {
        setMaxCharacterCount(savedState2.maxCharacterCount);
        if (savedState2.number != -1) {
            setNumber(savedState2.number);
        }
        setBackgroundColor(savedState2.backgroundColor);
        setBadgeTextColor(savedState2.badgeTextColor);
        setBadgeGravity(savedState2.badgeGravity);
    }

    private void loadDefaultStateFromAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context, attrs, C1124R.styleable.Badge, defStyleAttr, defStyleRes, new int[0]);
        setMaxCharacterCount(a.getInt(C1124R.styleable.Badge_maxCharacterCount, 4));
        if (a.hasValue(C1124R.styleable.Badge_number)) {
            setNumber(a.getInt(C1124R.styleable.Badge_number, 0));
        }
        setBackgroundColor(readColorFromAttributes(context, a, C1124R.styleable.Badge_backgroundColor));
        if (a.hasValue(C1124R.styleable.Badge_badgeTextColor)) {
            setBadgeTextColor(readColorFromAttributes(context, a, C1124R.styleable.Badge_badgeTextColor));
        }
        setBadgeGravity(a.getInt(C1124R.styleable.Badge_badgeGravity, TOP_END));
        a.recycle();
    }

    private static int readColorFromAttributes(Context context, TypedArray a, int index) {
        return MaterialResources.getColorStateList(context, a, index).getDefaultColor();
    }

    private BadgeDrawable(Context context) {
        this.contextRef = new WeakReference<>(context);
        ThemeEnforcement.checkMaterialTheme(context);
        Resources res = context.getResources();
        this.badgeRadius = (float) res.getDimensionPixelSize(C1124R.dimen.mtrl_badge_radius);
        this.badgeWidePadding = (float) res.getDimensionPixelSize(C1124R.dimen.mtrl_badge_long_text_horizontal_padding);
        this.badgeWithTextRadius = (float) res.getDimensionPixelSize(C1124R.dimen.mtrl_badge_with_text_radius);
        this.textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper.getTextPaint().setTextAlign(Align.CENTER);
        this.savedState = new SavedState(context);
        setTextAppearanceResource(C1124R.style.TextAppearance_MaterialComponents_Badge);
    }

    public void updateBadgeCoordinates(View anchorView, ViewGroup customBadgeParent) {
        this.anchorViewRef = new WeakReference<>(anchorView);
        this.customBadgeParentRef = new WeakReference<>(customBadgeParent);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    public void setBackgroundColor(int backgroundColor) {
        this.savedState.backgroundColor = backgroundColor;
        ColorStateList backgroundColorStateList = ColorStateList.valueOf(backgroundColor);
        if (this.shapeDrawable.getFillColor() != backgroundColorStateList) {
            this.shapeDrawable.setFillColor(backgroundColorStateList);
            invalidateSelf();
        }
    }

    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    public void setBadgeTextColor(int badgeTextColor) {
        this.savedState.badgeTextColor = badgeTextColor;
        if (this.textDrawableHelper.getTextPaint().getColor() != badgeTextColor) {
            this.textDrawableHelper.getTextPaint().setColor(badgeTextColor);
            invalidateSelf();
        }
    }

    public boolean hasNumber() {
        return this.savedState.number != -1;
    }

    public int getNumber() {
        if (!hasNumber()) {
            return 0;
        }
        return this.savedState.number;
    }

    public void setNumber(int number) {
        int number2 = Math.max(0, number);
        if (this.savedState.number != number2) {
            this.savedState.number = number2;
            this.textDrawableHelper.setTextWidthDirty(true);
            updateCenterAndBounds();
            invalidateSelf();
        }
    }

    public void clearNumber() {
        this.savedState.number = -1;
        invalidateSelf();
    }

    public int getMaxCharacterCount() {
        return this.savedState.maxCharacterCount;
    }

    public void setMaxCharacterCount(int maxCharacterCount) {
        if (this.savedState.maxCharacterCount != maxCharacterCount) {
            this.savedState.maxCharacterCount = maxCharacterCount;
            updateMaxBadgeNumber();
            this.textDrawableHelper.setTextWidthDirty(true);
            updateCenterAndBounds();
            invalidateSelf();
        }
    }

    public int getBadgeGravity() {
        return this.savedState.badgeGravity;
    }

    public void setBadgeGravity(int gravity) {
        if (this.savedState.badgeGravity != gravity) {
            this.savedState.badgeGravity = gravity;
            WeakReference<View> weakReference = this.anchorViewRef;
            if (weakReference != null && weakReference.get() != null) {
                View view = (View) this.anchorViewRef.get();
                WeakReference<ViewGroup> weakReference2 = this.customBadgeParentRef;
                updateBadgeCoordinates(view, weakReference2 != null ? (ViewGroup) weakReference2.get() : null);
            }
        }
    }

    public boolean isStateful() {
        return false;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getAlpha() {
        return this.savedState.alpha;
    }

    public void setAlpha(int alpha) {
        this.savedState.alpha = alpha;
        this.textDrawableHelper.getTextPaint().setAlpha(alpha);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasNumber()) {
                drawText(canvas);
            }
        }
    }

    public void onTextSizeChange() {
        invalidateSelf();
    }

    public boolean onStateChange(int[] state) {
        return super.onStateChange(state);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.savedState.contentDescriptionNumberless = charSequence;
    }

    public void setContentDescriptionQuantityStringsResource(int stringsResource) {
        this.savedState.contentDescriptionQuantityStrings = stringsResource;
    }

    public CharSequence getContentDescription() {
        if (!isVisible()) {
            return null;
        }
        if (!hasNumber()) {
            return this.savedState.contentDescriptionNumberless;
        }
        if (this.savedState.contentDescriptionQuantityStrings <= 0) {
            return null;
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return null;
        }
        return context.getResources().getQuantityString(this.savedState.contentDescriptionQuantityStrings, getNumber(), new Object[]{Integer.valueOf(getNumber())});
    }

    private void setTextAppearanceResource(int id) {
        Context context = (Context) this.contextRef.get();
        if (context != null) {
            setTextAppearance(new TextAppearance(context, id));
        }
    }

    private void setTextAppearance(TextAppearance textAppearance) {
        if (this.textDrawableHelper.getTextAppearance() != textAppearance) {
            Context context = (Context) this.contextRef.get();
            if (context != null) {
                this.textDrawableHelper.setTextAppearance(textAppearance, context);
                updateCenterAndBounds();
            }
        }
    }

    private void updateCenterAndBounds() {
        Context context = (Context) this.contextRef.get();
        WeakReference<View> weakReference = this.anchorViewRef;
        ViewGroup customBadgeParent = null;
        View anchorView = weakReference != null ? (View) weakReference.get() : null;
        if (context != null && anchorView != null) {
            Rect tmpRect = new Rect();
            tmpRect.set(this.badgeBounds);
            Rect anchorRect = new Rect();
            anchorView.getDrawingRect(anchorRect);
            WeakReference<ViewGroup> weakReference2 = this.customBadgeParentRef;
            if (weakReference2 != null) {
                customBadgeParent = (ViewGroup) weakReference2.get();
            }
            if (customBadgeParent != null || BadgeUtils.USE_COMPAT_PARENT) {
                (customBadgeParent == null ? (ViewGroup) anchorView.getParent() : customBadgeParent).offsetDescendantRectToMyCoords(anchorView, anchorRect);
            }
            calculateCenterAndBounds(context, anchorRect, anchorView);
            BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
            this.shapeDrawable.setCornerSize(this.cornerRadius);
            if (!tmpRect.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    private void calculateCenterAndBounds(Context context, Rect anchorRect, View anchorView) {
        int access$400 = this.savedState.badgeGravity;
        if (access$400 == 8388691 || access$400 == 8388693) {
            this.badgeCenterY = (float) anchorRect.bottom;
        } else {
            this.badgeCenterY = (float) anchorRect.top;
        }
        if (getNumber() <= 9) {
            this.cornerRadius = !hasNumber() ? this.badgeRadius : this.badgeWithTextRadius;
            float f = this.cornerRadius;
            this.halfBadgeHeight = f;
            this.halfBadgeWidth = f;
        } else {
            this.cornerRadius = this.badgeWithTextRadius;
            this.halfBadgeHeight = this.cornerRadius;
            this.halfBadgeWidth = (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.badgeWidePadding;
        }
        int inset = context.getResources().getDimensionPixelSize(hasNumber() ? C1124R.dimen.mtrl_badge_text_horizontal_edge_offset : C1124R.dimen.mtrl_badge_horizontal_edge_offset);
        int access$4002 = this.savedState.badgeGravity;
        if (access$4002 == 8388659 || access$4002 == 8388691) {
            this.badgeCenterX = ViewCompat.getLayoutDirection(anchorView) == 0 ? (((float) anchorRect.left) - this.halfBadgeWidth) + ((float) inset) : (((float) anchorRect.right) + this.halfBadgeWidth) - ((float) inset);
        } else {
            this.badgeCenterX = ViewCompat.getLayoutDirection(anchorView) == 0 ? (((float) anchorRect.right) + this.halfBadgeWidth) - ((float) inset) : (((float) anchorRect.left) - this.halfBadgeWidth) + ((float) inset);
        }
    }

    private void drawText(Canvas canvas) {
        Rect textBounds = new Rect();
        String badgeText = getBadgeText();
        this.textDrawableHelper.getTextPaint().getTextBounds(badgeText, 0, badgeText.length(), textBounds);
        canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + ((float) (textBounds.height() / 2)), this.textDrawableHelper.getTextPaint());
    }

    private String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return Integer.toString(getNumber());
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return "";
        }
        return context.getString(C1124R.string.mtrl_exceed_max_badge_number_suffix, new Object[]{Integer.valueOf(this.maxBadgeNumber), DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX});
    }

    private void updateMaxBadgeNumber() {
        this.maxBadgeNumber = ((int) Math.pow(10.0d, ((double) getMaxCharacterCount()) - 1.0d)) - 1;
    }
}
