package com.google.android.material.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;

public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    private boolean waitingForDismissAllowingStateLoss;

    private class BottomSheetDismissCallback extends BottomSheetCallback {
        private BottomSheetDismissCallback() {
        }

        public void onStateChanged(View bottomSheet, int newState) {
            if (newState == 5) {
                BottomSheetDialogFragment.this.dismissAfterAnimation();
            }
        }

        public void onSlide(View bottomSheet, float slideOffset) {
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }

    public void dismiss() {
        if (!tryDismissWithAnimation(false)) {
            super.dismiss();
        }
    }

    public void dismissAllowingStateLoss() {
        if (!tryDismissWithAnimation(true)) {
            super.dismissAllowingStateLoss();
        }
    }

    private boolean tryDismissWithAnimation(boolean allowingStateLoss) {
        Dialog baseDialog = getDialog();
        if (baseDialog instanceof BottomSheetDialog) {
            BottomSheetDialog dialog = (BottomSheetDialog) baseDialog;
            BottomSheetBehavior<?> behavior = dialog.getBehavior();
            if (behavior.isHideable() && dialog.getDismissWithAnimation()) {
                dismissWithAnimation(behavior, allowingStateLoss);
                return true;
            }
        }
        return false;
    }

    private void dismissWithAnimation(BottomSheetBehavior<?> behavior, boolean allowingStateLoss) {
        this.waitingForDismissAllowingStateLoss = allowingStateLoss;
        if (behavior.getState() == 5) {
            dismissAfterAnimation();
            return;
        }
        if (getDialog() instanceof BottomSheetDialog) {
            ((BottomSheetDialog) getDialog()).removeDefaultCallback();
        }
        behavior.addBottomSheetCallback(new BottomSheetDismissCallback());
        behavior.setState(5);
    }

    /* access modifiers changed from: private */
    public void dismissAfterAnimation() {
        if (this.waitingForDismissAllowingStateLoss) {
            super.dismissAllowingStateLoss();
        } else {
            super.dismiss();
        }
    }
}
