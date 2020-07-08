package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import androidx.core.util.Preconditions;
import com.google.android.material.C1124R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.ShapeAppearanceModel;

final class CalendarItemStyle {
    private final ColorStateList backgroundColor;
    private final Rect insets;
    private final ShapeAppearanceModel itemShape;
    private final ColorStateList strokeColor;
    private final int strokeWidth;
    private final ColorStateList textColor;

    private CalendarItemStyle(ColorStateList backgroundColor2, ColorStateList textColor2, ColorStateList strokeColor2, int strokeWidth2, ShapeAppearanceModel itemShape2, Rect insets2) {
        Preconditions.checkArgumentNonnegative(insets2.left);
        Preconditions.checkArgumentNonnegative(insets2.top);
        Preconditions.checkArgumentNonnegative(insets2.right);
        Preconditions.checkArgumentNonnegative(insets2.bottom);
        this.insets = insets2;
        this.textColor = textColor2;
        this.backgroundColor = backgroundColor2;
        this.strokeColor = strokeColor2;
        this.strokeWidth = strokeWidth2;
        this.itemShape = itemShape2;
    }

    static CalendarItemStyle create(Context context, int materialCalendarItemStyle) {
        Context context2 = context;
        int i = materialCalendarItemStyle;
        Preconditions.checkArgument(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray styleableArray = context2.obtainStyledAttributes(i, C1124R.styleable.MaterialCalendarItem);
        Rect insets2 = new Rect(styleableArray.getDimensionPixelOffset(C1124R.styleable.MaterialCalendarItem_android_insetLeft, 0), styleableArray.getDimensionPixelOffset(C1124R.styleable.MaterialCalendarItem_android_insetTop, 0), styleableArray.getDimensionPixelOffset(C1124R.styleable.MaterialCalendarItem_android_insetRight, 0), styleableArray.getDimensionPixelOffset(C1124R.styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList backgroundColor2 = MaterialResources.getColorStateList(context2, styleableArray, C1124R.styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList textColor2 = MaterialResources.getColorStateList(context2, styleableArray, C1124R.styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList strokeColor2 = MaterialResources.getColorStateList(context2, styleableArray, C1124R.styleable.MaterialCalendarItem_itemStrokeColor);
        int strokeWidth2 = styleableArray.getDimensionPixelSize(C1124R.styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        int shapeAppearanceResId = styleableArray.getResourceId(C1124R.styleable.MaterialCalendarItem_itemShapeAppearance, 0);
        ShapeAppearanceModel itemShape2 = ShapeAppearanceModel.builder(context2, shapeAppearanceResId, styleableArray.getResourceId(C1124R.styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).build();
        styleableArray.recycle();
        int i2 = shapeAppearanceResId;
        CalendarItemStyle calendarItemStyle = new CalendarItemStyle(backgroundColor2, textColor2, strokeColor2, strokeWidth2, itemShape2, insets2);
        return calendarItemStyle;
    }

    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r4v0, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.graphics.drawable.RippleDrawable] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void styleItem(android.widget.TextView r11) {
        /*
            r10 = this;
            com.google.android.material.shape.MaterialShapeDrawable r0 = new com.google.android.material.shape.MaterialShapeDrawable
            r0.<init>()
            com.google.android.material.shape.MaterialShapeDrawable r1 = new com.google.android.material.shape.MaterialShapeDrawable
            r1.<init>()
            com.google.android.material.shape.ShapeAppearanceModel r2 = r10.itemShape
            r0.setShapeAppearanceModel(r2)
            com.google.android.material.shape.ShapeAppearanceModel r2 = r10.itemShape
            r1.setShapeAppearanceModel(r2)
            android.content.res.ColorStateList r2 = r10.backgroundColor
            r0.setFillColor(r2)
            int r2 = r10.strokeWidth
            float r2 = (float) r2
            android.content.res.ColorStateList r3 = r10.strokeColor
            r0.setStroke(r2, r3)
            android.content.res.ColorStateList r2 = r10.textColor
            r11.setTextColor(r2)
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r2 < r3) goto L_0x003a
            android.graphics.drawable.RippleDrawable r2 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r3 = r10.textColor
            r4 = 30
            android.content.res.ColorStateList r3 = r3.withAlpha(r4)
            r2.<init>(r3, r0, r1)
            goto L_0x003b
        L_0x003a:
            r2 = r0
        L_0x003b:
            android.graphics.drawable.InsetDrawable r9 = new android.graphics.drawable.InsetDrawable
            android.graphics.Rect r3 = r10.insets
            int r5 = r3.left
            android.graphics.Rect r3 = r10.insets
            int r6 = r3.top
            android.graphics.Rect r3 = r10.insets
            int r7 = r3.right
            android.graphics.Rect r3 = r10.insets
            int r8 = r3.bottom
            r3 = r9
            r4 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            androidx.core.view.ViewCompat.setBackground(r11, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.CalendarItemStyle.styleItem(android.widget.TextView):void");
    }

    /* access modifiers changed from: 0000 */
    public int getLeftInset() {
        return this.insets.left;
    }

    /* access modifiers changed from: 0000 */
    public int getRightInset() {
        return this.insets.right;
    }

    /* access modifiers changed from: 0000 */
    public int getTopInset() {
        return this.insets.top;
    }

    /* access modifiers changed from: 0000 */
    public int getBottomInset() {
        return this.insets.bottom;
    }
}
