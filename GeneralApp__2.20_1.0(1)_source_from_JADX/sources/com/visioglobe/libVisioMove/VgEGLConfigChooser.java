package com.visioglobe.libVisioMove;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.util.Log;
import java.util.LinkedList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class VgEGLConfigChooser implements EGLConfigChooser {
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final String TAG = "VgEGLConfigChooser";
    protected boolean mNeedsGLES2 = false;
    protected boolean mNeedsTransparency;
    private boolean mUsesCoverageAa;
    private int[] mValue;

    public void setNeedsTransparency(boolean pNeedsTransparency) {
        this.mNeedsTransparency = pNeedsTransparency;
    }

    public void setNeedsGLES2(boolean pNeedsGLES2) {
        this.mNeedsGLES2 = pNeedsGLES2;
    }

    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
        EGLConfig lBestConfig;
        this.mValue = new int[1];
        int[] configSpecGLES1 = {12344};
        int[] configSpecGLES2 = {12352, 4, 12344};
        int[] configSpec = this.mNeedsGLES2 ? configSpecGLES2 : configSpecGLES1;
        if (egl.eglChooseConfig(display, configSpec, null, 0, this.mValue)) {
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            int[] iArr = this.mValue;
            int lNumConfigs = iArr[0];
            EGLConfig[] lConfigs = new EGLConfig[lNumConfigs];
            if (egl.eglChooseConfig(display, configSpec, lConfigs, lNumConfigs, iArr)) {
                int i = 0;
                while (i < lNumConfigs) {
                    EGL10 egl10 = egl;
                    EGLDisplay eGLDisplay = display;
                    EGLConfig[] lConfigs2 = lConfigs;
                    int lNumConfigs2 = lNumConfigs;
                    int lAlpha = findConfigAttrib(egl10, eGLDisplay, lConfigs[i], 12321, 0);
                    int[] configSpecGLES12 = configSpecGLES1;
                    int lDepth = findConfigAttrib(egl10, eGLDisplay, lConfigs2[i], 12325, 0);
                    int lSamples = findConfigAttrib(egl10, eGLDisplay, lConfigs2[i], 12337, 0);
                    if (((this.mNeedsTransparency && lAlpha >= 4) || (!this.mNeedsTransparency && lAlpha == 0)) && lDepth >= 16) {
                        if (lSamples > 0) {
                            linkedList2.add(Integer.valueOf(i));
                        } else {
                            linkedList.add(Integer.valueOf(i));
                        }
                    }
                    i++;
                    lNumConfigs = lNumConfigs2;
                    lConfigs = lConfigs2;
                    configSpecGLES1 = configSpecGLES12;
                }
                EGLConfig[] lConfigs3 = lConfigs;
                int lNumConfigs3 = lNumConfigs;
                int[] iArr2 = configSpecGLES1;
                if (linkedList2.size() > 0) {
                    lBestConfig = lConfigs3[((Integer) linkedList2.getLast()).intValue()];
                } else if (linkedList.size() > 0) {
                    lBestConfig = lConfigs3[((Integer) linkedList.getLast()).intValue()];
                } else {
                    lBestConfig = lConfigs3[lNumConfigs3 - 1];
                }
                if (1 != 0) {
                    EGL10 egl102 = egl;
                    EGLDisplay eGLDisplay2 = display;
                    EGLConfig eGLConfig = lBestConfig;
                    int lRed = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12324, 0);
                    int lGreen = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12323, 0);
                    int[] iArr3 = configSpecGLES2;
                    int lBlue = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12322, 0);
                    LinkedList linkedList3 = linkedList;
                    int lAlpha2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12321, 0);
                    LinkedList linkedList4 = linkedList2;
                    int lDepth2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12325, 0);
                    int[] iArr4 = configSpec;
                    int lSampleBuffers = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12338, 0);
                    int lSamples2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, 12337, 0);
                    String str = TAG;
                    Log.i(str, "************ EGL CONFIG *************");
                    StringBuilder sb = new StringBuilder();
                    sb.append("Red channel bits: ");
                    sb.append(lRed);
                    Log.i(str, sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Green channel bits: ");
                    sb2.append(lGreen);
                    Log.i(str, sb2.toString());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Blue channel bits: ");
                    sb3.append(lBlue);
                    Log.i(str, sb3.toString());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Alpha channel bits: ");
                    sb4.append(lAlpha2);
                    Log.i(str, sb4.toString());
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Depth channel bits: ");
                    sb5.append(lDepth2);
                    Log.i(str, sb5.toString());
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Sample buffers: ");
                    sb6.append(lSampleBuffers);
                    Log.i(str, sb6.toString());
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("Samples: ");
                    sb7.append(lSamples2);
                    Log.i(str, sb7.toString());
                    Log.i(str, "*************************************");
                    Log.i(str, "");
                } else {
                    LinkedList linkedList5 = linkedList;
                    LinkedList linkedList6 = linkedList2;
                    int[] iArr5 = configSpec;
                }
                return lBestConfig;
            }
            int i2 = lNumConfigs;
            int[] iArr6 = configSpecGLES1;
            int[] iArr7 = configSpecGLES2;
            LinkedList linkedList7 = linkedList;
            LinkedList linkedList8 = linkedList2;
            int[] iArr8 = configSpec;
        } else {
            int[] iArr9 = configSpecGLES2;
            int[] iArr10 = configSpec;
        }
        return null;
    }

    private int findConfigAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int attribute, int defaultValue) {
        if (egl.eglGetConfigAttrib(display, config, attribute, this.mValue)) {
            return this.mValue[0];
        }
        return defaultValue;
    }

    public boolean usesCoverageAa() {
        return this.mUsesCoverageAa;
    }
}
