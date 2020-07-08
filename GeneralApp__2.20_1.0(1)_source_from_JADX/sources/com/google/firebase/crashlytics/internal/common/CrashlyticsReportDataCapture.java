package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME = new HashMap();
    private static final String GENERATOR = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{BuildConfig.VERSION_NAME});
    private static final int GENERATOR_TYPE = 3;
    private static final int REPORT_ANDROID_PLATFORM = 4;
    private static final int SESSION_ANDROID_PLATFORM = 3;
    private static final String SIGNAL_DEFAULT = "0";
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    static {
        ARCHITECTURES_BY_NAME.put("armeabi", Integer.valueOf(5));
        ARCHITECTURES_BY_NAME.put("armeabi-v7a", Integer.valueOf(6));
        ARCHITECTURES_BY_NAME.put("arm64-v8a", Integer.valueOf(9));
        ARCHITECTURES_BY_NAME.put("x86", Integer.valueOf(0));
        ARCHITECTURES_BY_NAME.put("x86_64", Integer.valueOf(1));
    }

    public CrashlyticsReportDataCapture(Context context2, IdManager idManager2, AppData appData2, StackTraceTrimmingStrategy stackTraceTrimmingStrategy2) {
        this.context = context2;
        this.idManager = idManager2;
        this.appData = appData2;
        this.stackTraceTrimmingStrategy = stackTraceTrimmingStrategy2;
    }

    public CrashlyticsReport captureReportData(String identifier, long timestamp) {
        return buildReportData().setSession(populateSessionData(identifier, timestamp)).build();
    }

    public CrashlyticsReport captureReportData() {
        return buildReportData().build();
    }

    public Event captureEventData(Throwable event, Thread eventThread, String type, long timestamp, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        int orientation = this.context.getResources().getConfiguration().orientation;
        Throwable th = event;
        return Event.builder().setType(type).setTimestamp(timestamp).setApp(populateEventApplicationData(orientation, new TrimmedThrowableData(event, this.stackTraceTrimmingStrategy), eventThread, eventThreadImportance, maxChainedExceptions, includeAllThreads)).setDevice(populateEventDeviceData(orientation)).build();
    }

    private Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion(BuildConfig.VERSION_NAME).setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private Session populateSessionData(String identifier, long timestamp) {
        return Session.builder().setStartedAt(timestamp).setIdentifier(identifier).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private Application populateSessionApplicationData() {
        return Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).build();
    }

    private OperatingSystem populateSessionOperatingSystemData() {
        return OperatingSystem.builder().setPlatform(3).setVersion(VERSION.RELEASE).setBuildVersion(VERSION.CODENAME).setJailbroken(CommonUtils.isRooted(this.context)).build();
    }

    private Device populateSessionDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int arch = getDeviceArchitecture();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRam = CommonUtils.getTotalRamInBytes();
        long diskSpace = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        boolean isEmulator = CommonUtils.isEmulator(this.context);
        int state = CommonUtils.getDeviceState(this.context);
        String manufacturer = Build.MANUFACTURER;
        return Device.builder().setArch(arch).setModel(Build.MODEL).setCores(availableProcessors).setRam(totalRam).setDiskSpace(diskSpace).setSimulator(isEmulator).setState(state).setManufacturer(manufacturer).setModelClass(Build.PRODUCT).build();
    }

    private Event.Application populateEventApplicationData(int orientation, TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        Boolean isBackground = null;
        RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(this.appData.packageName, this.context);
        if (runningAppProcessInfo != null) {
            isBackground = Boolean.valueOf(runningAppProcessInfo.importance != 100);
        }
        return Event.Application.builder().setBackground(isBackground).setUiOrientation(orientation).setExecution(populateExecutionData(trimmedEvent, eventThread, eventThreadImportance, maxChainedExceptions, includeAllThreads)).build();
    }

    private Event.Device populateEventDeviceData(int orientation) {
        BatteryState battery = BatteryState.get(this.context);
        Float batteryLevel = battery.getBatteryLevel();
        Double batteryLevelDouble = batteryLevel != null ? Double.valueOf(batteryLevel.doubleValue()) : null;
        int batteryVelocity = battery.getBatteryVelocity();
        return Event.Device.builder().setBatteryLevel(batteryLevelDouble).setBatteryVelocity(batteryVelocity).setProximityOn(CommonUtils.getProximitySensorEnabled(this.context)).setOrientation(orientation).setRamUsed(CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(this.context)).setDiskUsed(CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath())).build();
    }

    private Execution populateExecutionData(TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        return Execution.builder().setThreads(populateThreadsList(trimmedEvent, eventThread, eventThreadImportance, includeAllThreads)).setException(populateExceptionData(trimmedEvent, eventThreadImportance, maxChainedExceptions)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private ImmutableList<Thread> populateThreadsList(TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, boolean includeAllThreads) {
        List<Thread> threadsList = new ArrayList<>();
        threadsList.add(populateThreadData(eventThread, trimmedEvent.stacktrace, eventThreadImportance));
        if (includeAllThreads) {
            for (Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread thread = (Thread) entry.getKey();
                if (!thread.equals(eventThread)) {
                    threadsList.add(populateThreadData(thread, this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) entry.getValue())));
                }
            }
        }
        return ImmutableList.from(threadsList);
    }

    private Thread populateThreadData(Thread thread, StackTraceElement[] stacktrace) {
        return populateThreadData(thread, stacktrace, 0);
    }

    private Thread populateThreadData(Thread thread, StackTraceElement[] stacktrace, int importance) {
        return Thread.builder().setName(thread.getName()).setImportance(importance).setFrames(ImmutableList.from((List<E>) populateFramesList(stacktrace, importance))).build();
    }

    private ImmutableList<Frame> populateFramesList(StackTraceElement[] stacktrace, int importance) {
        List<Frame> framesList = new ArrayList<>();
        for (StackTraceElement element : stacktrace) {
            framesList.add(populateFrameData(element, Frame.builder().setImportance(importance)));
        }
        return ImmutableList.from(framesList);
    }

    private Exception populateExceptionData(TrimmedThrowableData trimmedEvent, int eventThreadImportance, int maxChainedExceptions) {
        return populateExceptionData(trimmedEvent, eventThreadImportance, maxChainedExceptions, 0);
    }

    private Exception populateExceptionData(TrimmedThrowableData trimmedEvent, int eventThreadImportance, int maxChainedExceptions, int chainDepth) {
        String type = trimmedEvent.className;
        String reason = trimmedEvent.localizedMessage;
        StackTraceElement[] stacktrace = trimmedEvent.stacktrace != null ? trimmedEvent.stacktrace : new StackTraceElement[0];
        TrimmedThrowableData cause = trimmedEvent.cause;
        int overflowCount = 0;
        if (chainDepth >= maxChainedExceptions) {
            TrimmedThrowableData skipped = cause;
            while (skipped != null) {
                skipped = skipped.cause;
                overflowCount++;
            }
        }
        Exception.Builder builder = Exception.builder().setType(type).setReason(reason).setFrames(ImmutableList.from((List<E>) populateFramesList(stacktrace, eventThreadImportance))).setOverflowCount(overflowCount);
        if (cause != null && overflowCount == 0) {
            builder.setCausedBy(populateExceptionData(cause, eventThreadImportance, maxChainedExceptions, chainDepth + 1));
        }
        return builder.build();
    }

    private Frame populateFrameData(StackTraceElement element, Frame.Builder frameBuilder) {
        long pc = 0;
        if (element.isNativeMethod()) {
            pc = Math.max((long) element.getLineNumber(), 0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(element.getClassName());
        sb.append(".");
        sb.append(element.getMethodName());
        String symbol = sb.toString();
        String file = element.getFileName();
        long offset = 0;
        if (!element.isNativeMethod() && element.getLineNumber() > 0) {
            offset = (long) element.getLineNumber();
        }
        return frameBuilder.setPc(pc).setSymbol(symbol).setFile(file).setOffset(offset).build();
    }

    private ImmutableList<BinaryImage> populateBinaryImagesList() {
        return ImmutableList.from((E[]) new BinaryImage[]{populateBinaryImageData()});
    }

    private BinaryImage populateBinaryImageData() {
        return BinaryImage.builder().setBaseAddress(0).setSize(0).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private Signal populateSignalData() {
        Signal.Builder builder = Signal.builder();
        String str = SIGNAL_DEFAULT;
        return builder.setName(str).setCode(str).setAddress(0).build();
    }

    private static int getDeviceArchitecture() {
        String primaryAbi = Build.CPU_ABI;
        if (TextUtils.isEmpty(primaryAbi)) {
            return 7;
        }
        Integer arch = (Integer) ARCHITECTURES_BY_NAME.get(primaryAbi.toLowerCase(Locale.US));
        if (arch == null) {
            return 7;
        }
        return arch.intValue();
    }
}
