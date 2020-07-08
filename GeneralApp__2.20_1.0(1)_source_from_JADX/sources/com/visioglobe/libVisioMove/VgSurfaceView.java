package com.visioglobe.libVisioMove;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class VgSurfaceView extends GLSurfaceView implements Renderer, OnTouchListener, OnKeyListener, Callback {
    private static final String TAG = "VgSurfaceView";
    /* access modifiers changed from: private */
    public VgIApplication mApp;
    /* access modifiers changed from: private */
    public long mAppCPtr;
    /* access modifiers changed from: private */
    public Bitmap mBitmapFromGLSurface;
    private FutureTask<Bitmap> mCaptureTask;
    protected boolean mDoRender;
    protected VgEGLConfigChooser mEGLConfigChooser;
    /* access modifiers changed from: private */
    public VgIEngine mEngine;
    /* access modifiers changed from: private */
    public EventQueue mEventQueue;
    protected boolean mIsDestroyed;
    private boolean mNeedsTransparency;
    private LinkedList<Runnable> mOnCreateRunnables;
    private boolean mPendingDraw;
    private long mRedrawListener;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    protected boolean mUseGLES2;

    private class RedrawRunnable implements Runnable {
        private RedrawRunnable() {
        }

        public void run() {
            VgSurfaceView vgSurfaceView = VgSurfaceView.this;
            vgSurfaceView.requestEngineRedraw(vgSurfaceView.mAppCPtr, true);
        }
    }

    private native long registerAsApplicationListener(long j);

    /* access modifiers changed from: private */
    public native void requestEngineRedraw(long j, boolean z);

    /* access modifiers changed from: private */
    public native void setAndroidIdFromJavaString(String str, long j);

    private native void setAndroidIdFromTelephonyManager(TelephonyManager telephonyManager, long j);

    private native void triggerPostDrawCallbacks(long j);

    private native void unregisterAsApplicationListener(long j);

    public native void dispatchDoubleTouchMove(long j, int i, int i2, int i3, int i4, int i5);

    public native void dispatchTouchDown(long j, int i, int i2, int i3);

    public native void dispatchTouchMove(long j, int i, int i2, int i3);

    public native void dispatchTouchUp(long j, int i, int i2, int i3);

    public native void drawFrame(long j);

    public native void setSurfaceSize(long j, int i, int i2);

    public Future<Bitmap> captureToBitmap() {
        if (this.mIsDestroyed || !this.mDoRender || getVisibility() != 0 || this.mSurfaceHeight == -1 || this.mSurfaceWidth == -1) {
            return null;
        }
        FutureTask<Bitmap> futureTask = this.mCaptureTask;
        if (futureTask != null) {
            futureTask.cancel(false);
        }
        this.mCaptureTask = new FutureTask<>(new Callable<Bitmap>() {
            public Bitmap call() throws Exception {
                return VgSurfaceView.this.mBitmapFromGLSurface;
            }
        });
        requestRedraw();
        return this.mCaptureTask;
    }

    public VgSurfaceView(Context pContext, AttributeSet pAttributeSet, boolean pNeedsTransparency) {
        super(pContext.getApplicationContext(), pAttributeSet);
        this.mAppCPtr = 0;
        this.mDoRender = false;
        this.mIsDestroyed = true;
        this.mOnCreateRunnables = new LinkedList<>();
        this.mPendingDraw = false;
        this.mSurfaceWidth = -1;
        this.mSurfaceHeight = -1;
        this.mUseGLES2 = true;
        this.mNeedsTransparency = pNeedsTransparency;
        readAttributes(pContext, pAttributeSet);
        init();
    }

    public VgSurfaceView(Context pContext, AttributeSet pAttributeSet) {
        super(pContext, pAttributeSet);
        this.mAppCPtr = 0;
        this.mDoRender = false;
        this.mIsDestroyed = true;
        this.mOnCreateRunnables = new LinkedList<>();
        this.mPendingDraw = false;
        this.mSurfaceWidth = -1;
        this.mSurfaceHeight = -1;
        this.mUseGLES2 = true;
        this.mNeedsTransparency = false;
        readAttributes(pContext, pAttributeSet);
        init();
    }

    public VgSurfaceView(Context pContext) {
        super(pContext);
        this.mAppCPtr = 0;
        this.mDoRender = false;
        this.mIsDestroyed = true;
        this.mOnCreateRunnables = new LinkedList<>();
        this.mPendingDraw = false;
        this.mSurfaceWidth = -1;
        this.mSurfaceHeight = -1;
        this.mUseGLES2 = true;
        this.mNeedsTransparency = false;
        init();
    }

    public VgSurfaceView(Context pContext, boolean pNeedsTransparency) {
        super(pContext.getApplicationContext());
        this.mAppCPtr = 0;
        this.mDoRender = false;
        this.mIsDestroyed = true;
        this.mOnCreateRunnables = new LinkedList<>();
        this.mPendingDraw = false;
        this.mSurfaceWidth = -1;
        this.mSurfaceHeight = -1;
        this.mUseGLES2 = true;
        this.mNeedsTransparency = pNeedsTransparency;
        init();
    }

    private void readAttributes(Context pContext, AttributeSet pAttributeSet) {
        TypedArray a = pContext.getTheme().obtainStyledAttributes(pAttributeSet, C1278R.styleable.VgSurfaceView, 0, 0);
        try {
            this.mNeedsTransparency = a.getBoolean(C1278R.styleable.VgSurfaceView_needsTransparency, false);
        } finally {
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        String str = TAG;
        Log.i(str, ">>>> VgSurfaceView.init()");
        if (this.mUseGLES2) {
            setEGLContextClientVersion(2);
        }
        this.mEventQueue = new EventQueue();
        this.mEGLConfigChooser = new VgEGLConfigChooser();
        this.mEGLConfigChooser.setNeedsTransparency(this.mNeedsTransparency);
        this.mEGLConfigChooser.setNeedsGLES2(this.mUseGLES2);
        setEGLConfigChooser(this.mEGLConfigChooser);
        if (this.mNeedsTransparency) {
            getHolder().setFormat(-3);
        }
        setRenderer(this);
        setRenderMode(0);
        queueEvent(new Runnable() {
            public void run() {
                String str = VgSurfaceView.TAG;
                Log.i(str, ">>>> creating application");
                VgSurfaceView vgSurfaceView = VgSurfaceView.this;
                vgSurfaceView.mApp = vgSurfaceView.mUseGLES2 ? VgIApplication.createApplicationOGLES2() : VgIApplication.createApplicationOGLES1();
                VgSurfaceView vgSurfaceView2 = VgSurfaceView.this;
                vgSurfaceView2.mEngine = vgSurfaceView2.mApp.editEngine();
                VgSurfaceView vgSurfaceView3 = VgSurfaceView.this;
                vgSurfaceView3.mAppCPtr = VgIApplication.getCPtr(vgSurfaceView3.mApp);
                String lAndroidId = Secure.getString(VgSurfaceView.this.getContext().getContentResolver(), "android_id");
                VgSurfaceView vgSurfaceView4 = VgSurfaceView.this;
                vgSurfaceView4.setAndroidIdFromJavaString(lAndroidId, vgSurfaceView4.mAppCPtr);
                Log.i(str, "<<<< Application created");
            }
        });
        Log.i(str, "<<<< VgSurfaceView.init()");
    }

    /* access modifiers changed from: protected */
    public long getAppCPtr() {
        return this.mAppCPtr;
    }

    private void registerRedrawListener() {
        if (this.mRedrawListener == 0) {
            long j = this.mAppCPtr;
            if (j != 0) {
                this.mRedrawListener = registerAsApplicationListener(j);
            }
        }
    }

    private void unregisterRedrawListener() {
        long j = this.mRedrawListener;
        if (j != 0) {
            unregisterAsApplicationListener(j);
            this.mRedrawListener = 0;
        }
    }

    public void pauseRendering() {
        this.mDoRender = false;
        FutureTask<Bitmap> futureTask = this.mCaptureTask;
        if (futureTask != null) {
            futureTask.cancel(false);
            this.mCaptureTask = null;
        }
    }

    public void resumeRendering() {
        this.mDoRender = true;
        requestRender();
    }

    public boolean isRendering() {
        return this.mDoRender;
    }

    public void requestRedraw() {
        if (!this.mPendingDraw) {
            this.mPendingDraw = true;
            requestRender();
        }
    }

    public void postLoadDataset() {
        if (!this.mIsDestroyed && this.mDoRender) {
            queueEvent(new RedrawRunnable());
        }
    }

    public VgIApplication getApplication() {
        return this.mApp;
    }

    public void addOnCreateRunnable(Runnable pRunnable) {
        this.mOnCreateRunnables.add(pRunnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDrawFrame(javax.microedition.khronos.opengles.GL10 r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.mIsDestroyed     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)
            return
        L_0x0007:
            boolean r0 = r7.mDoRender     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0050
        L_0x000b:
            com.visioglobe.libVisioMove.EventQueue r0 = r7.mEventQueue     // Catch:{ all -> 0x0052 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x001f
            long r0 = r7.mAppCPtr     // Catch:{ all -> 0x0052 }
            com.visioglobe.libVisioMove.EventQueue r2 = r7.mEventQueue     // Catch:{ all -> 0x0052 }
            android.view.MotionEvent r2 = r2.popEvent()     // Catch:{ all -> 0x0052 }
            com.visioglobe.libVisioMove.VgTouchEventDispatcher.run(r7, r0, r2)     // Catch:{ all -> 0x0052 }
            goto L_0x000b
        L_0x001f:
            r0 = 0
            r7.mPendingDraw = r0     // Catch:{ all -> 0x0052 }
            long r0 = r7.mAppCPtr     // Catch:{ all -> 0x0052 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0034
            long r0 = r7.mAppCPtr     // Catch:{ all -> 0x0052 }
            r7.drawFrame(r0)     // Catch:{ all -> 0x0052 }
            long r0 = r7.mAppCPtr     // Catch:{ all -> 0x0052 }
            r7.triggerPostDrawCallbacks(r0)     // Catch:{ all -> 0x0052 }
        L_0x0034:
            java.util.concurrent.FutureTask<android.graphics.Bitmap> r0 = r7.mCaptureTask     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0050
            r2 = 0
            r3 = 0
            int r4 = r7.mSurfaceWidth     // Catch:{ all -> 0x0052 }
            int r5 = r7.mSurfaceHeight     // Catch:{ all -> 0x0052 }
            r1 = r7
            r6 = r8
            android.graphics.Bitmap r0 = r1.createBitmapFromGLSurface(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0052 }
            r7.mBitmapFromGLSurface = r0     // Catch:{ all -> 0x0052 }
            java.util.concurrent.FutureTask<android.graphics.Bitmap> r0 = r7.mCaptureTask     // Catch:{ all -> 0x0052 }
            r0.run()     // Catch:{ all -> 0x0052 }
            r0 = 0
            r7.mBitmapFromGLSurface = r0     // Catch:{ all -> 0x0052 }
            r7.mCaptureTask = r0     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r7)
            return
        L_0x0052:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visioglobe.libVisioMove.VgSurfaceView.onDrawFrame(javax.microedition.khronos.opengles.GL10):void");
    }

    private Bitmap createBitmapFromGLSurface(int x, int y, int w, int h, GL10 gl) throws OutOfMemoryError {
        int i = w;
        int i2 = h;
        int[] bitmapBuffer = new int[(i * i2)];
        int[] bitmapSource = new int[(i * i2)];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);
        try {
            gl.glReadPixels(x, y, w, h, 6408, 5121, intBuffer);
            for (int i3 = 0; i3 < i2; i3++) {
                int offset1 = i3 * i;
                int offset2 = ((i2 - i3) - 1) * i;
                for (int j = 0; j < i; j++) {
                    int texturePixel = bitmapBuffer[offset1 + j];
                    int i4 = offset2 + j;
                    bitmapSource[i4] = (-16711936 & texturePixel) | ((texturePixel << 16) & 16711680) | ((texturePixel >> 16) & 255);
                }
            }
            return Bitmap.createBitmap(bitmapSource, i, i2, Config.ARGB_8888);
        } catch (GLException e) {
            return null;
        }
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        setOnTouchListener(this);
        setOnKeyListener(this);
        while (!this.mOnCreateRunnables.isEmpty()) {
            ((Runnable) this.mOnCreateRunnables.removeFirst()).run();
        }
        this.mIsDestroyed = false;
        registerRedrawListener();
        if (getApplication() != null) {
            this.mEngine.resetGraphicResources(true);
            if (this.mUseGLES2) {
                this.mEngine.reloadShaders();
            }
        }
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (this.mUseGLES2) {
            GLES20.glViewport(0, 0, width, height);
        } else {
            gl.glViewport(0, 0, width, height);
        }
        long j = this.mAppCPtr;
        if (j != 0) {
            setSurfaceSize(j, width, height);
        }
        this.mSurfaceWidth = width;
        this.mSurfaceHeight = height;
    }

    public void surfaceDestroyed(SurfaceHolder pHolder) {
        unregisterRedrawListener();
        this.mIsDestroyed = true;
        this.mDoRender = false;
        setOnTouchListener(null);
        setOnKeyListener(null);
        super.surfaceDestroyed(pHolder);
    }

    public boolean onKey(View pView, int pInt, KeyEvent pEvent) {
        return false;
    }

    public boolean onTouch(View pView, MotionEvent pEvent) {
        int action = pEvent.getAction();
        if (action != 0 && action != 1 && action != 2 && action != 3 && action != 5 && action != 6 && action != 261 && action != 262 && action != 517 && action != 518) {
            return false;
        }
        final MotionEvent lEvent = MotionEvent.obtainNoHistory(pEvent);
        queueEvent(new Runnable() {
            public void run() {
                VgSurfaceView.this.mEventQueue.pushEvent(lEvent);
                VgSurfaceView vgSurfaceView = VgSurfaceView.this;
                vgSurfaceView.requestEngineRedraw(vgSurfaceView.mAppCPtr, true);
            }
        });
        return true;
    }

    public void setRenderOnDemand(boolean pIsEnabled) {
        setRenderMode(pIsEnabled ^ true ? 1 : 0);
    }

    public boolean getRenderOnDemand() {
        return getRenderMode() == 0;
    }
}
