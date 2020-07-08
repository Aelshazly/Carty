package com.visioglobe.libVisioMove;

import android.view.MotionEvent;
import java.util.LinkedList;
import java.util.List;

public class EventQueue {
    protected List<MotionEvent> mEventList = new LinkedList();

    public synchronized void pushEvent(MotionEvent pEvent) {
        if (pEvent.getAction() != 2) {
            this.mEventList.add(pEvent);
        } else if (this.mEventList.size() <= 0 || ((MotionEvent) this.mEventList.get(this.mEventList.size() - 1)).getAction() != 2) {
            this.mEventList.add(pEvent);
        } else {
            this.mEventList.set(this.mEventList.size() - 1, pEvent);
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean isEmpty() {
        return this.mEventList.size() == 0;
    }

    public synchronized MotionEvent popEvent() {
        MotionEvent lEvent;
        lEvent = (MotionEvent) this.mEventList.get(0);
        this.mEventList.remove(0);
        return lEvent;
    }
}
