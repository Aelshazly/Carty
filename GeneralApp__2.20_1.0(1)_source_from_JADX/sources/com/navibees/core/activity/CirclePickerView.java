package com.navibees.core.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import androidx.core.view.ViewCompat;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.sdk.C1266R;
import java.util.ArrayList;
import java.util.HashMap;
import p008cz.msebera.android.httpclient.HttpStatus;

public class CirclePickerView implements OnClickListener {

    /* renamed from: a */
    private Context f962a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public HorizontalScrollView f963b;

    /* renamed from: c */
    private LinearLayout f964c;

    /* renamed from: d */
    private int f965d;

    /* renamed from: e */
    private CirclePickerListener f966e;

    /* renamed from: f */
    public boolean f967f = true;

    /* renamed from: g */
    private final int f968g = 16;

    /* renamed from: h */
    private HashMap<String, Integer> f969h;

    public interface CirclePickerListener {
        void didSelectCell(String str, CirclePickerView circlePickerView);
    }

    public interface OnHideFinishedListener {
        /* renamed from: a */
        void mo28779a();
    }

    public CirclePickerView(Context context, HorizontalScrollView horizontalScrollView, CirclePickerListener circlePickerListener) {
        this.f963b = horizontalScrollView;
        this.f964c = (LinearLayout) this.f963b.findViewById(C1266R.C1269id.container);
        this.f962a = context;
        this.f966e = circlePickerListener;
    }

    /* renamed from: b */
    private int m650b(float f) {
        return Math.round(f / this.f962a.getResources().getDisplayMetrics().density);
    }

    public void onClick(View view) {
        String str = (String) view.getTag();
        mo28767a(str);
        this.f966e.didSelectCell(str, this);
    }

    /* renamed from: a */
    public void mo28768a(String[] strArr, String[] strArr2) {
        if (this.f964c.getChildCount() > 0) {
            this.f964c.removeAllViews();
        }
        HashMap<String, Integer> hashMap = this.f969h;
        if (hashMap == null) {
            this.f969h = new HashMap<>();
        } else {
            hashMap.clear();
        }
        int a = m646a(56.0f);
        int a2 = m646a(16.0f);
        for (int i = 0; i < strArr.length; i++) {
            Button button = (Button) LayoutInflater.from(this.f962a).inflate(C1266R.layout.com_navibees_sdk_circle_cell, null);
            LayoutParams layoutParams = new LayoutParams(a, a);
            if (i == 0) {
                layoutParams.setMarginStart(a2);
            }
            layoutParams.setMarginEnd(a2);
            button.setLayoutParams(layoutParams);
            button.setText(strArr[i]);
            int textSize = (int) NaviBeesUtils.setTextSize(button, layoutParams.width);
            if (textSize > 15) {
                textSize -= 12;
            }
            button.setTextSize(0, (float) textSize);
            button.setTag(strArr2[i]);
            if (i == this.f965d) {
                m649a(button, true);
            } else {
                m649a(button, false);
            }
            this.f969h.put(strArr2[i], Integer.valueOf(i));
            button.setAlpha(0.0f);
            button.setOnClickListener(this);
            this.f964c.addView(button);
        }
    }

    /* renamed from: a */
    public void mo28765a() {
        this.f967f = false;
        this.f963b.setVisibility(0);
        int i = this.f965d;
        if (i > -1 && i < this.f964c.getChildCount()) {
            final View childAt = this.f964c.getChildAt(this.f965d);
            int left = childAt.getLeft();
            int scrollX = this.f963b.getScrollX();
            int a = m646a(56.0f);
            int a2 = m646a(16.0f);
            int width = this.f963b.getLayoutDirection() != 1 ? scrollX + ((this.f963b.getWidth() - a) - a2) : scrollX + a2;
            long b = (long) ((m650b((float) Math.abs(left - width)) * 1000) / HttpStatus.SC_INTERNAL_SERVER_ERROR);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, "x", new float[]{(float) width, (float) left});
            ofFloat.setDuration(b);
            ofFloat.addListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    childAt.setAlpha(1.0f);
                }
            });
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.f964c.getChildCount(); i2++) {
                if (i2 != this.f965d) {
                    arrayList.add(ObjectAnimator.ofFloat(this.f964c.getChildAt(i2), "alpha", new float[]{0.0f, 1.0f}));
                }
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.setDuration(400);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{ofFloat, animatorSet});
            animatorSet2.start();
        }
    }

    /* renamed from: a */
    public void mo28766a(final OnHideFinishedListener onHideFinishedListener) {
        this.f967f = true;
        if (this.f965d < this.f964c.getChildCount()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.f964c.getChildCount(); i++) {
                if (i != this.f965d) {
                    arrayList.add(ObjectAnimator.ofFloat(this.f964c.getChildAt(i), "alpha", new float[]{1.0f, 0.0f}));
                }
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.setDuration(400);
            final View childAt = this.f964c.getChildAt(this.f965d);
            final int left = childAt.getLeft();
            int scrollX = this.f963b.getScrollX();
            int a = m646a(56.0f);
            int a2 = m646a(16.0f);
            int width = this.f963b.getLayoutDirection() != 1 ? scrollX + ((this.f963b.getWidth() - a) - a2) : scrollX + a2;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, "x", new float[]{(float) left, (float) width});
            ofFloat.setDuration((long) ((m650b((float) Math.abs(width - left)) * 1000) / HttpStatus.SC_INTERNAL_SERVER_ERROR));
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{animatorSet, ofFloat});
            animatorSet2.start();
            animatorSet2.addListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    childAt.setAlpha(0.0f);
                    CirclePickerView.this.f963b.setVisibility(4);
                    childAt.setX((float) left);
                    onHideFinishedListener.mo28779a();
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            });
        }
    }

    /* renamed from: a */
    private void m648a(int i) {
        if (this.f965d < this.f964c.getChildCount() && i < this.f964c.getChildCount()) {
            m649a((Button) this.f964c.getChildAt(this.f965d), false);
            m649a((Button) this.f964c.getChildAt(i), true);
        }
        this.f965d = i;
    }

    /* renamed from: a */
    public void mo28767a(String str) {
        if (this.f969h.containsKey(str)) {
            m648a(((Integer) this.f969h.get(str)).intValue());
        }
    }

    /* renamed from: a */
    private int m646a(float f) {
        return Math.round(TypedValue.applyDimension(1, f, this.f962a.getResources().getDisplayMetrics()));
    }

    /* renamed from: a */
    private void m649a(Button button, boolean z) {
        if (z) {
            button.setBackgroundResource(C1266R.C1268drawable.com_navibees_sdk_floor_button_highlighted);
            button.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        button.setBackgroundResource(C1266R.C1268drawable.com_navibees_sdk_floor_button_not_highlighted);
        button.setTextColor(-9145485);
    }
}
