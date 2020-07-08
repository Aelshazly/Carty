package com.google.android.material.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import com.google.android.material.C1124R;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialDialogs {
    private MaterialDialogs() {
    }

    public static InsetDrawable insetDrawable(Drawable drawable, Rect backgroundInsets) {
        InsetDrawable insetDrawable = new InsetDrawable(drawable, backgroundInsets.left, backgroundInsets.top, backgroundInsets.right, backgroundInsets.bottom);
        return insetDrawable;
    }

    public static Rect getDialogBackgroundInsets(Context context, int defaultStyleAttribute, int defaultStyleResource) {
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context, null, C1124R.styleable.MaterialAlertDialog, defaultStyleAttribute, defaultStyleResource, new int[0]);
        int backgroundInsetStart = attributes.getDimensionPixelSize(C1124R.styleable.MaterialAlertDialog_backgroundInsetStart, context.getResources().getDimensionPixelSize(C1124R.dimen.mtrl_alert_dialog_background_inset_start));
        int backgroundInsetTop = attributes.getDimensionPixelSize(C1124R.styleable.MaterialAlertDialog_backgroundInsetTop, context.getResources().getDimensionPixelSize(C1124R.dimen.mtrl_alert_dialog_background_inset_top));
        int backgroundInsetEnd = attributes.getDimensionPixelSize(C1124R.styleable.MaterialAlertDialog_backgroundInsetEnd, context.getResources().getDimensionPixelSize(C1124R.dimen.mtrl_alert_dialog_background_inset_end));
        int backgroundInsetBottom = attributes.getDimensionPixelSize(C1124R.styleable.MaterialAlertDialog_backgroundInsetBottom, context.getResources().getDimensionPixelSize(C1124R.dimen.mtrl_alert_dialog_background_inset_bottom));
        attributes.recycle();
        int backgroundInsetLeft = backgroundInsetStart;
        int backgroundInsetRight = backgroundInsetEnd;
        if (VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1) {
            backgroundInsetLeft = backgroundInsetEnd;
            backgroundInsetRight = backgroundInsetStart;
        }
        return new Rect(backgroundInsetLeft, backgroundInsetTop, backgroundInsetRight, backgroundInsetBottom);
    }
}
