package com.google.android.material.card;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1124R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;

class MaterialCardViewHelper {
    private static final float CARD_VIEW_SHADOW_MULTIPLIER = 1.5f;
    private static final int CHECKED_ICON_LAYER_INDEX = 2;
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private static final int DEFAULT_STROKE_VALUE = -1;
    private final MaterialShapeDrawable bgDrawable;
    private boolean checkable;
    private Drawable checkedIcon;
    private final int checkedIconMargin;
    private final int checkedIconSize;
    private ColorStateList checkedIconTint;
    private LayerDrawable clickableForegroundDrawable;
    private MaterialShapeDrawable compatRippleDrawable;
    private Drawable fgDrawable;
    private final MaterialShapeDrawable foregroundContentDrawable;
    private MaterialShapeDrawable foregroundShapeDrawable;
    private boolean isBackgroundOverwritten = false;
    private final MaterialCardView materialCardView;
    private ColorStateList rippleColor;
    private Drawable rippleDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private final Rect userContentPadding = new Rect();

    public MaterialCardViewHelper(MaterialCardView card, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.materialCardView = card;
        this.bgDrawable = new MaterialShapeDrawable(card.getContext(), attrs, defStyleAttr, defStyleRes);
        this.bgDrawable.initializeElevationOverlay(card.getContext());
        this.bgDrawable.setShadowColor(-12303292);
        Builder shapeAppearanceModelBuilder = this.bgDrawable.getShapeAppearanceModel().toBuilder();
        TypedArray cardViewAttributes = card.getContext().obtainStyledAttributes(attrs, C1124R.styleable.CardView, defStyleAttr, C1124R.style.CardView);
        if (cardViewAttributes.hasValue(C1124R.styleable.CardView_cardCornerRadius)) {
            shapeAppearanceModelBuilder.setAllCornerSizes(cardViewAttributes.getDimension(C1124R.styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearanceModel(shapeAppearanceModelBuilder.build());
        Resources resources = card.getResources();
        this.checkedIconMargin = resources.getDimensionPixelSize(C1124R.dimen.mtrl_card_checked_icon_margin);
        this.checkedIconSize = resources.getDimensionPixelSize(C1124R.dimen.mtrl_card_checked_icon_size);
        cardViewAttributes.recycle();
    }

    /* access modifiers changed from: 0000 */
    public void loadFromAttributes(TypedArray attributes) {
        this.strokeColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, C1124R.styleable.MaterialCardView_strokeColor);
        if (this.strokeColor == null) {
            this.strokeColor = ColorStateList.valueOf(-1);
        }
        this.strokeWidth = attributes.getDimensionPixelSize(C1124R.styleable.MaterialCardView_strokeWidth, 0);
        this.checkable = attributes.getBoolean(C1124R.styleable.MaterialCardView_android_checkable, false);
        this.materialCardView.setLongClickable(this.checkable);
        this.checkedIconTint = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, C1124R.styleable.MaterialCardView_checkedIconTint);
        setCheckedIcon(MaterialResources.getDrawable(this.materialCardView.getContext(), attributes, C1124R.styleable.MaterialCardView_checkedIcon));
        this.rippleColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, C1124R.styleable.MaterialCardView_rippleColor);
        if (this.rippleColor == null) {
            this.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(this.materialCardView, C1124R.attr.colorControlHighlight));
        }
        ColorStateList foregroundColor = MaterialResources.getColorStateList(this.materialCardView.getContext(), attributes, C1124R.styleable.MaterialCardView_cardForegroundColor);
        this.foregroundContentDrawable.setFillColor(foregroundColor == null ? ColorStateList.valueOf(0) : foregroundColor);
        updateRippleColor();
        updateElevation();
        updateStroke();
        this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        this.fgDrawable = this.materialCardView.isClickable() ? getClickableForeground() : this.foregroundContentDrawable;
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    /* access modifiers changed from: 0000 */
    public boolean isBackgroundOverwritten() {
        return this.isBackgroundOverwritten;
    }

    /* access modifiers changed from: 0000 */
    public void setBackgroundOverwritten(boolean isBackgroundOverwritten2) {
        this.isBackgroundOverwritten = isBackgroundOverwritten2;
    }

    /* access modifiers changed from: 0000 */
    public void setStrokeColor(ColorStateList strokeColor2) {
        if (this.strokeColor != strokeColor2) {
            this.strokeColor = strokeColor2;
            updateStroke();
        }
    }

    /* access modifiers changed from: 0000 */
    public int getStrokeColor() {
        ColorStateList colorStateList = this.strokeColor;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList getStrokeColorStateList() {
        return this.strokeColor;
    }

    /* access modifiers changed from: 0000 */
    public void setStrokeWidth(int strokeWidth2) {
        if (strokeWidth2 != this.strokeWidth) {
            this.strokeWidth = strokeWidth2;
            updateStroke();
        }
    }

    /* access modifiers changed from: 0000 */
    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    /* access modifiers changed from: 0000 */
    public MaterialShapeDrawable getBackground() {
        return this.bgDrawable;
    }

    /* access modifiers changed from: 0000 */
    public void setCardBackgroundColor(ColorStateList color) {
        this.bgDrawable.setFillColor(color);
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList getCardBackgroundColor() {
        return this.bgDrawable.getFillColor();
    }

    /* access modifiers changed from: 0000 */
    public void setUserContentPadding(int left, int top, int right, int bottom) {
        this.userContentPadding.set(left, top, right, bottom);
        updateContentPadding();
    }

    /* access modifiers changed from: 0000 */
    public Rect getUserContentPadding() {
        return this.userContentPadding;
    }

    /* access modifiers changed from: 0000 */
    public void updateClickable() {
        Drawable previousFgDrawable = this.fgDrawable;
        this.fgDrawable = this.materialCardView.isClickable() ? getClickableForeground() : this.foregroundContentDrawable;
        Drawable drawable = this.fgDrawable;
        if (previousFgDrawable != drawable) {
            updateInsetForeground(drawable);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setCornerRadius(float cornerRadius) {
        setShapeAppearanceModel(this.shapeAppearanceModel.withCornerSize(cornerRadius));
        this.fgDrawable.invalidateSelf();
        if (shouldAddCornerPaddingOutsideCardBackground() || shouldAddCornerPaddingInsideCardBackground()) {
            updateContentPadding();
        }
        if (shouldAddCornerPaddingOutsideCardBackground()) {
            updateInsets();
        }
    }

    /* access modifiers changed from: 0000 */
    public float getCornerRadius() {
        return this.bgDrawable.getTopLeftCornerResolvedSize();
    }

    /* access modifiers changed from: 0000 */
    public void setProgress(float progress) {
        this.bgDrawable.setInterpolation(progress);
        MaterialShapeDrawable materialShapeDrawable = this.foregroundContentDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setInterpolation(progress);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.foregroundShapeDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setInterpolation(progress);
        }
    }

    /* access modifiers changed from: 0000 */
    public float getProgress() {
        return this.bgDrawable.getInterpolation();
    }

    /* access modifiers changed from: 0000 */
    public void updateElevation() {
        this.bgDrawable.setElevation(this.materialCardView.getCardElevation());
    }

    /* access modifiers changed from: 0000 */
    public void updateInsets() {
        if (!isBackgroundOverwritten()) {
            this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        }
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    /* access modifiers changed from: 0000 */
    public void updateStroke() {
        this.foregroundContentDrawable.setStroke((float) this.strokeWidth, this.strokeColor);
    }

    /* access modifiers changed from: 0000 */
    public void updateContentPadding() {
        int contentPaddingOffset = (int) ((shouldAddCornerPaddingInsideCardBackground() || shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f) - getParentCardViewCalculatedCornerPadding());
        this.materialCardView.setAncestorContentPadding(this.userContentPadding.left + contentPaddingOffset, this.userContentPadding.top + contentPaddingOffset, this.userContentPadding.right + contentPaddingOffset, this.userContentPadding.bottom + contentPaddingOffset);
    }

    /* access modifiers changed from: 0000 */
    public void setCheckable(boolean checkable2) {
        this.checkable = checkable2;
    }

    /* access modifiers changed from: 0000 */
    public boolean isCheckable() {
        return this.checkable;
    }

    /* access modifiers changed from: 0000 */
    public void setRippleColor(ColorStateList rippleColor2) {
        this.rippleColor = rippleColor2;
        updateRippleColor();
    }

    /* access modifiers changed from: 0000 */
    public void setCheckedIconTint(ColorStateList checkedIconTint2) {
        this.checkedIconTint = checkedIconTint2;
        Drawable drawable = this.checkedIcon;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, checkedIconTint2);
        }
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    /* access modifiers changed from: 0000 */
    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    /* access modifiers changed from: 0000 */
    public void setCheckedIcon(Drawable checkedIcon2) {
        this.checkedIcon = checkedIcon2;
        if (checkedIcon2 != null) {
            this.checkedIcon = DrawableCompat.wrap(checkedIcon2.mutate());
            DrawableCompat.setTintList(this.checkedIcon, this.checkedIconTint);
        }
        if (this.clickableForegroundDrawable != null) {
            this.clickableForegroundDrawable.setDrawableByLayerId(C1124R.C1127id.mtrl_card_checked_layer_id, createCheckedIconLayer());
        }
    }

    /* access modifiers changed from: 0000 */
    public void onMeasure(int measuredWidth, int measuredHeight) {
        if (this.clickableForegroundDrawable != null) {
            int i = this.checkedIconMargin;
            int i2 = measuredWidth - i;
            int i3 = this.checkedIconSize;
            int left = i2 - i3;
            int bottom = (measuredHeight - i) - i3;
            int right = this.checkedIconMargin;
            if (ViewCompat.getLayoutDirection(this.materialCardView) == 1) {
                int tmp = right;
                right = left;
                left = tmp;
            }
            this.clickableForegroundDrawable.setLayerInset(2, left, this.checkedIconMargin, right, bottom);
        }
    }

    /* access modifiers changed from: 0000 */
    public void forceRippleRedraw() {
        Drawable drawable = this.rippleDrawable;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int bottom = bounds.bottom;
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, bottom - 1);
            this.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, bottom);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        this.bgDrawable.setShapeAppearanceModel(shapeAppearanceModel2);
        MaterialShapeDrawable materialShapeDrawable = this.foregroundContentDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.foregroundShapeDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        MaterialShapeDrawable materialShapeDrawable3 = this.compatRippleDrawable;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.setShapeAppearanceModel(shapeAppearanceModel2);
        }
    }

    /* access modifiers changed from: 0000 */
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    private void updateInsetForeground(Drawable insetForeground) {
        if (VERSION.SDK_INT < 23 || !(this.materialCardView.getForeground() instanceof InsetDrawable)) {
            this.materialCardView.setForeground(insetDrawable(insetForeground));
        } else {
            ((InsetDrawable) this.materialCardView.getForeground()).setDrawable(insetForeground);
        }
    }

    private Drawable insetDrawable(Drawable originalDrawable) {
        int insetVertical = 0;
        int insetHorizontal = 0;
        if ((VERSION.SDK_INT < 21) || this.materialCardView.getUseCompatPadding()) {
            insetVertical = (int) Math.ceil((double) calculateVerticalBackgroundPadding());
            insetHorizontal = (int) Math.ceil((double) calculateHorizontalBackgroundPadding());
        }
        C05861 r3 = new InsetDrawable(originalDrawable, insetHorizontal, insetVertical, insetHorizontal, insetVertical) {
            public boolean getPadding(Rect padding) {
                return false;
            }
        };
        return r3;
    }

    private float calculateVerticalBackgroundPadding() {
        return (this.materialCardView.getMaxCardElevation() * CARD_VIEW_SHADOW_MULTIPLIER) + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private float calculateHorizontalBackgroundPadding() {
        return this.materialCardView.getMaxCardElevation() + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    private boolean canClipToOutline() {
        return VERSION.SDK_INT >= 21 && this.bgDrawable.isRoundRect();
    }

    private float getParentCardViewCalculatedCornerPadding() {
        if (!this.materialCardView.getPreventCornerOverlap() || (VERSION.SDK_INT >= 21 && !this.materialCardView.getUseCompatPadding())) {
            return 0.0f;
        }
        return (float) ((1.0d - COS_45) * ((double) this.materialCardView.getCardViewRadius()));
    }

    private boolean shouldAddCornerPaddingInsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && !canClipToOutline();
    }

    private boolean shouldAddCornerPaddingOutsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && canClipToOutline() && this.materialCardView.getUseCompatPadding();
    }

    private float calculateActualCornerPadding() {
        return Math.max(Math.max(calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopLeftCorner(), this.bgDrawable.getTopLeftCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopRightCorner(), this.bgDrawable.getTopRightCornerResolvedSize())), Math.max(calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomRightCorner(), this.bgDrawable.getBottomRightCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomLeftCorner(), this.bgDrawable.getBottomLeftCornerResolvedSize())));
    }

    private float calculateCornerPaddingForCornerTreatment(CornerTreatment treatment, float size) {
        if (treatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * ((double) size));
        }
        if (treatment instanceof CutCornerTreatment) {
            return size / 2.0f;
        }
        return 0.0f;
    }

    private Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            this.rippleDrawable = createForegroundRippleDrawable();
        }
        if (this.clickableForegroundDrawable == null) {
            this.clickableForegroundDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, createCheckedIconLayer()});
            this.clickableForegroundDrawable.setId(2, C1124R.C1127id.mtrl_card_checked_layer_id);
        }
        return this.clickableForegroundDrawable;
    }

    private Drawable createForegroundRippleDrawable() {
        if (!RippleUtils.USE_FRAMEWORK_RIPPLE) {
            return createCompatRippleDrawable();
        }
        this.foregroundShapeDrawable = createForegroundShapeDrawable();
        return new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
    }

    private Drawable createCompatRippleDrawable() {
        StateListDrawable rippleDrawable2 = new StateListDrawable();
        this.compatRippleDrawable = createForegroundShapeDrawable();
        this.compatRippleDrawable.setFillColor(this.rippleColor);
        rippleDrawable2.addState(new int[]{16842919}, this.compatRippleDrawable);
        return rippleDrawable2;
    }

    private void updateRippleColor() {
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            Drawable drawable = this.rippleDrawable;
            if (drawable != null) {
                ((RippleDrawable) drawable).setColor(this.rippleColor);
                return;
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.compatRippleDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setFillColor(this.rippleColor);
        }
    }

    private Drawable createCheckedIconLayer() {
        StateListDrawable checkedLayer = new StateListDrawable();
        Drawable drawable = this.checkedIcon;
        if (drawable != null) {
            checkedLayer.addState(CHECKED_STATE_SET, drawable);
        }
        return checkedLayer;
    }

    private MaterialShapeDrawable createForegroundShapeDrawable() {
        return new MaterialShapeDrawable(this.shapeAppearanceModel);
    }
}
