package androidx.transition;

import android.os.Build.VERSION;
import android.view.View;

class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    ViewUtilsApi23() {
    }

    public void setTransitionVisibility(View view, int visibility) {
        if (VERSION.SDK_INT == 28) {
            super.setTransitionVisibility(view, visibility);
        } else if (sTryHiddenSetTransitionVisibility) {
            try {
                view.setTransitionVisibility(visibility);
            } catch (NoSuchMethodError e) {
                sTryHiddenSetTransitionVisibility = false;
            }
        }
    }
}
