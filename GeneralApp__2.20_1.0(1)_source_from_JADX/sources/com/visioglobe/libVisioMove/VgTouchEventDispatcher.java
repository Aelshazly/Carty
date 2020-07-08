package com.visioglobe.libVisioMove;

import android.view.MotionEvent;

public class VgTouchEventDispatcher {
    static void run(VgSurfaceView pView, long pAppCPtr, MotionEvent pEvent) {
        int action = pEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    int pointerCount = pEvent.getPointerCount();
                    if (pointerCount == 1) {
                        pView.dispatchTouchMove(pAppCPtr, (int) pEvent.getX(), (int) pEvent.getY(), 1);
                        return;
                    } else if (pointerCount == 2) {
                        pView.dispatchDoubleTouchMove(pAppCPtr, (int) pEvent.getX(0), (int) pEvent.getY(0), (int) pEvent.getX(1), (int) pEvent.getY(1), 2);
                        return;
                    } else {
                        return;
                    }
                } else if (action != 3) {
                    if (action != 5) {
                        if (action != 6) {
                            if (action != 261) {
                                if (action != 262) {
                                    if (action != 517) {
                                        if (action != 518) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pView.dispatchTouchUp(pAppCPtr, (int) pEvent.getX(), (int) pEvent.getY(), 1);
            return;
        }
        pView.dispatchTouchDown(pAppCPtr, (int) pEvent.getX(), (int) pEvent.getY(), 1);
    }
}
