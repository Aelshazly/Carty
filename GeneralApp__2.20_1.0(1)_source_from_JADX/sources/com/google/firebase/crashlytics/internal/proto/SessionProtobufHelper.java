package com.google.firebase.crashlytics.internal.proto;

import android.app.ActivityManager.RunningAppProcessInfo;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class SessionProtobufHelper {
    private static final String SIGNAL_DEFAULT = "0";
    private static final ByteString SIGNAL_DEFAULT_BYTE_STRING = ByteString.copyFromUtf8(SIGNAL_DEFAULT);
    private static final ByteString UNITY_PLATFORM_BYTE_STRING = ByteString.copyFromUtf8("Unity");

    private SessionProtobufHelper() {
    }

    public static void writeBeginSession(CodedOutputStream cos, String sessionId, String generator, long startedAtSeconds) throws Exception {
        cos.writeBytes(1, ByteString.copyFromUtf8(generator));
        cos.writeBytes(2, ByteString.copyFromUtf8(sessionId));
        cos.writeUInt64(3, startedAtSeconds);
    }

    public static void writeSessionApp(CodedOutputStream cos, String packageName, String versionCode, String versionName, String installUuid, int deliveryMechanism, String unityVersion) throws Exception {
        CodedOutputStream codedOutputStream = cos;
        ByteString packageNameBytes = ByteString.copyFromUtf8(packageName);
        ByteString versionCodeBytes = ByteString.copyFromUtf8(versionCode);
        ByteString versionNameBytes = ByteString.copyFromUtf8(versionName);
        ByteString installIdBytes = ByteString.copyFromUtf8(installUuid);
        ByteString unityVersionBytes = unityVersion != null ? ByteString.copyFromUtf8(unityVersion) : null;
        cos.writeTag(7, 2);
        cos.writeRawVarint32(getSessionAppSize(packageNameBytes, versionCodeBytes, versionNameBytes, installIdBytes, deliveryMechanism, unityVersionBytes));
        cos.writeBytes(1, packageNameBytes);
        cos.writeBytes(2, versionCodeBytes);
        cos.writeBytes(3, versionNameBytes);
        cos.writeBytes(6, installIdBytes);
        if (unityVersionBytes != null) {
            cos.writeBytes(8, UNITY_PLATFORM_BYTE_STRING);
            cos.writeBytes(9, unityVersionBytes);
        }
        cos.writeEnum(10, deliveryMechanism);
    }

    public static void writeSessionOS(CodedOutputStream cos, String osRelease, String osCodeName, boolean isRooted) throws Exception {
        ByteString releaseBytes = ByteString.copyFromUtf8(osRelease);
        ByteString codeNameBytes = ByteString.copyFromUtf8(osCodeName);
        cos.writeTag(8, 2);
        cos.writeRawVarint32(getSessionOSSize(releaseBytes, codeNameBytes, isRooted));
        cos.writeEnum(1, 3);
        cos.writeBytes(2, releaseBytes);
        cos.writeBytes(3, codeNameBytes);
        cos.writeBool(4, isRooted);
    }

    public static void writeSessionDevice(CodedOutputStream cos, int arch, String model, int availableProcessors, long totalRam, long diskSpace, boolean isEmulator, int state, String manufacturer, String modelClass) throws Exception {
        CodedOutputStream codedOutputStream = cos;
        ByteString modelBytes = stringToByteString(model);
        ByteString modelClassBytes = stringToByteString(modelClass);
        ByteString manufacturerBytes = stringToByteString(manufacturer);
        cos.writeTag(9, 2);
        int i = availableProcessors;
        long j = totalRam;
        long j2 = diskSpace;
        boolean z = isEmulator;
        int i2 = state;
        cos.writeRawVarint32(getSessionDeviceSize(arch, modelBytes, i, j, j2, z, i2, manufacturerBytes, modelClassBytes));
        cos.writeEnum(3, arch);
        cos.writeBytes(4, modelBytes);
        cos.writeUInt32(5, i);
        cos.writeUInt64(6, j);
        cos.writeUInt64(7, j2);
        cos.writeBool(10, z);
        cos.writeUInt32(12, i2);
        if (manufacturerBytes != null) {
            cos.writeBytes(13, manufacturerBytes);
        }
        if (modelClassBytes != null) {
            cos.writeBytes(14, modelClassBytes);
        }
    }

    public static void writeSessionUser(CodedOutputStream cos, String id, String name, String email) throws Exception {
        ByteString idBytes = ByteString.copyFromUtf8(id == null ? "" : id);
        ByteString nameBytes = stringToByteString(name);
        ByteString emailBytes = stringToByteString(email);
        int size = 0 + CodedOutputStream.computeBytesSize(1, idBytes);
        if (name != null) {
            size += CodedOutputStream.computeBytesSize(2, nameBytes);
        }
        if (email != null) {
            size += CodedOutputStream.computeBytesSize(3, emailBytes);
        }
        cos.writeTag(6, 2);
        cos.writeRawVarint32(size);
        cos.writeBytes(1, idBytes);
        if (name != null) {
            cos.writeBytes(2, nameBytes);
        }
        if (email != null) {
            cos.writeBytes(3, emailBytes);
        }
    }

    public static void writeSessionEvent(CodedOutputStream cos, long eventTime, String eventType, TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionsDepth, Map<String, String> customAttributes, byte[] logBytes, RunningAppProcessInfo runningAppProcessInfo, int orientation, String packageName, String buildId, Float batteryLevel, int batteryVelocity, boolean proximityEnabled, long usedRamInBytes, long diskUsedInBytes) throws Exception {
        ByteString logByteString;
        CodedOutputStream codedOutputStream = cos;
        String str = buildId;
        ByteString packageNameBytes = ByteString.copyFromUtf8(packageName);
        ByteString optionalBuildIdBytes = str == null ? null : ByteString.copyFromUtf8(str.replace("-", ""));
        if (logBytes != null) {
            logByteString = ByteString.copyFrom(logBytes);
        } else {
            Logger.getLogger().mo19679d("No log data to include with this event.");
            logByteString = null;
        }
        codedOutputStream.writeTag(10, 2);
        long j = eventTime;
        codedOutputStream.writeRawVarint32(getSessionEventSize(j, eventType, exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, customAttributes, runningAppProcessInfo, orientation, packageNameBytes, optionalBuildIdBytes, batteryLevel, batteryVelocity, proximityEnabled, usedRamInBytes, diskUsedInBytes, logByteString));
        codedOutputStream.writeUInt64(1, j);
        codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(eventType));
        CodedOutputStream codedOutputStream2 = cos;
        ByteString logByteString2 = logByteString;
        writeSessionEventApp(codedOutputStream2, exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes, customAttributes, runningAppProcessInfo, orientation);
        writeSessionEventDevice(codedOutputStream2, batteryLevel, batteryVelocity, proximityEnabled, orientation, usedRamInBytes, diskUsedInBytes);
        writeSessionEventLog(codedOutputStream, logByteString2);
    }

    public static void writeSessionAppClsId(CodedOutputStream cos, String clsId) throws Exception {
        ByteString orgIdBytes = ByteString.copyFromUtf8(clsId);
        cos.writeTag(7, 2);
        int orgIdSize = CodedOutputStream.computeBytesSize(2, orgIdBytes);
        cos.writeRawVarint32(CodedOutputStream.computeTagSize(5) + CodedOutputStream.computeRawVarint32Size(orgIdSize) + orgIdSize);
        cos.writeTag(5, 2);
        cos.writeRawVarint32(orgIdSize);
        cos.writeBytes(2, orgIdBytes);
    }

    private static void writeSessionEventApp(CodedOutputStream cos, TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionsDepth, ByteString packageNameBytes, ByteString optionalBuildIdBytes, Map<String, String> customAttributes, RunningAppProcessInfo runningAppProcessInfo, int orientation) throws Exception {
        cos.writeTag(3, 2);
        cos.writeRawVarint32(getEventAppSize(exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes, customAttributes, runningAppProcessInfo, orientation));
        writeSessionEventAppExecution(cos, exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes);
        if (customAttributes != null && !customAttributes.isEmpty()) {
            writeSessionEventAppCustomAttributes(cos, customAttributes);
        }
        if (runningAppProcessInfo != null) {
            cos.writeBool(3, runningAppProcessInfo.importance != 100);
        }
        cos.writeUInt32(4, orientation);
    }

    private static void writeSessionEventAppExecution(CodedOutputStream cos, TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionsDepth, ByteString packageNameBytes, ByteString optionalBuildIdBytes) throws Exception {
        CodedOutputStream codedOutputStream = cos;
        Thread[] threadArr = otherThreads;
        ByteString byteString = optionalBuildIdBytes;
        codedOutputStream.writeTag(1, 2);
        codedOutputStream.writeRawVarint32(getEventAppExecutionSize(exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes));
        writeThread(codedOutputStream, exceptionThread, exceptionStack, 4, true);
        int len = threadArr.length;
        for (int i = 0; i < len; i++) {
            writeThread(codedOutputStream, threadArr[i], (StackTraceElement[]) otherStacks.get(i), 0, false);
        }
        List<StackTraceElement[]> list = otherStacks;
        writeSessionEventAppExecutionException(codedOutputStream, exception, 1, maxChainedExceptionsDepth, 2);
        codedOutputStream.writeTag(3, 2);
        codedOutputStream.writeRawVarint32(getEventAppExecutionSignalSize());
        codedOutputStream.writeBytes(1, SIGNAL_DEFAULT_BYTE_STRING);
        codedOutputStream.writeBytes(2, SIGNAL_DEFAULT_BYTE_STRING);
        codedOutputStream.writeUInt64(3, 0);
        codedOutputStream.writeTag(4, 2);
        codedOutputStream.writeRawVarint32(getBinaryImageSize(packageNameBytes, optionalBuildIdBytes));
        codedOutputStream.writeUInt64(1, 0);
        codedOutputStream.writeUInt64(2, 0);
        codedOutputStream.writeBytes(3, packageNameBytes);
        if (byteString != null) {
            codedOutputStream.writeBytes(4, byteString);
        }
    }

    private static void writeSessionEventAppCustomAttributes(CodedOutputStream cos, Map<String, String> customAttributes) throws Exception {
        for (Entry<String, String> entry : customAttributes.entrySet()) {
            cos.writeTag(2, 2);
            cos.writeRawVarint32(getEventAppCustomAttributeSize((String) entry.getKey(), (String) entry.getValue()));
            cos.writeBytes(1, ByteString.copyFromUtf8((String) entry.getKey()));
            String value = (String) entry.getValue();
            cos.writeBytes(2, ByteString.copyFromUtf8(value == null ? "" : value));
        }
    }

    private static void writeSessionEventAppExecutionException(CodedOutputStream cos, TrimmedThrowableData exception, int chainDepth, int maxChainedExceptionsDepth, int field) throws Exception {
        cos.writeTag(field, 2);
        cos.writeRawVarint32(getEventAppExecutionExceptionSize(exception, 1, maxChainedExceptionsDepth));
        cos.writeBytes(1, ByteString.copyFromUtf8(exception.className));
        String message = exception.localizedMessage;
        if (message != null) {
            cos.writeBytes(3, ByteString.copyFromUtf8(message));
        }
        for (StackTraceElement element : exception.stacktrace) {
            writeFrame(cos, 4, element, true);
        }
        TrimmedThrowableData cause = exception.cause;
        if (cause == null) {
            return;
        }
        if (chainDepth < maxChainedExceptionsDepth) {
            writeSessionEventAppExecutionException(cos, cause, chainDepth + 1, maxChainedExceptionsDepth, 6);
            return;
        }
        int overflowCount = 0;
        while (cause != null) {
            cause = cause.cause;
            overflowCount++;
        }
        cos.writeUInt32(7, overflowCount);
    }

    private static void writeThread(CodedOutputStream cos, Thread thread, StackTraceElement[] stackTraceElements, int importance, boolean isCrashedThread) throws Exception {
        cos.writeTag(1, 2);
        cos.writeRawVarint32(getThreadSize(thread, stackTraceElements, importance, isCrashedThread));
        cos.writeBytes(1, ByteString.copyFromUtf8(thread.getName()));
        cos.writeUInt32(2, importance);
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            writeFrame(cos, 3, stackTraceElement, isCrashedThread);
        }
    }

    private static void writeFrame(CodedOutputStream cos, int fieldIndex, StackTraceElement element, boolean isCrashedThread) throws Exception {
        cos.writeTag(fieldIndex, 2);
        cos.writeRawVarint32(getFrameSize(element, isCrashedThread));
        if (element.isNativeMethod()) {
            cos.writeUInt64(1, (long) Math.max(element.getLineNumber(), 0));
        } else {
            cos.writeUInt64(1, 0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(element.getClassName());
        sb.append(".");
        sb.append(element.getMethodName());
        cos.writeBytes(2, ByteString.copyFromUtf8(sb.toString()));
        if (element.getFileName() != null) {
            cos.writeBytes(3, ByteString.copyFromUtf8(element.getFileName()));
        }
        int i = 4;
        if (!element.isNativeMethod() && element.getLineNumber() > 0) {
            cos.writeUInt64(4, (long) element.getLineNumber());
        }
        if (!isCrashedThread) {
            i = 0;
        }
        cos.writeUInt32(5, i);
    }

    private static void writeSessionEventDevice(CodedOutputStream cos, Float batteryLevel, int batteryVelocity, boolean proximityEnabled, int orientation, long heapAllocatedSize, long diskUsed) throws Exception {
        cos.writeTag(5, 2);
        cos.writeRawVarint32(getEventDeviceSize(batteryLevel, batteryVelocity, proximityEnabled, orientation, heapAllocatedSize, diskUsed));
        if (batteryLevel != null) {
            cos.writeFloat(1, batteryLevel.floatValue());
        }
        cos.writeSInt32(2, batteryVelocity);
        cos.writeBool(3, proximityEnabled);
        cos.writeUInt32(4, orientation);
        cos.writeUInt64(5, heapAllocatedSize);
        cos.writeUInt64(6, diskUsed);
    }

    private static void writeSessionEventLog(CodedOutputStream cos, ByteString log) throws Exception {
        if (log != null) {
            cos.writeTag(6, 2);
            cos.writeRawVarint32(getEventLogSize(log));
            cos.writeBytes(1, log);
        }
    }

    private static int getSessionAppSize(ByteString packageName, ByteString versionCode, ByteString versionName, ByteString installUuid, int deliveryMechanism, ByteString unityVersion) {
        int size = 0 + CodedOutputStream.computeBytesSize(1, packageName) + CodedOutputStream.computeBytesSize(2, versionCode) + CodedOutputStream.computeBytesSize(3, versionName) + CodedOutputStream.computeBytesSize(6, installUuid);
        if (unityVersion != null) {
            size = size + CodedOutputStream.computeBytesSize(8, UNITY_PLATFORM_BYTE_STRING) + CodedOutputStream.computeBytesSize(9, unityVersion);
        }
        return size + CodedOutputStream.computeEnumSize(10, deliveryMechanism);
    }

    private static int getSessionOSSize(ByteString release, ByteString codeName, boolean isRooted) {
        return 0 + CodedOutputStream.computeEnumSize(1, 3) + CodedOutputStream.computeBytesSize(2, release) + CodedOutputStream.computeBytesSize(3, codeName) + CodedOutputStream.computeBoolSize(4, isRooted);
    }

    private static int getSessionDeviceSize(int arch, ByteString model, int availableProcessors, long totalRam, long diskSpace, boolean isEmulator, int state, ByteString manufacturer, ByteString modelClass) {
        int i = 0;
        int size = 0 + CodedOutputStream.computeEnumSize(3, arch) + (model == null ? 0 : CodedOutputStream.computeBytesSize(4, model)) + CodedOutputStream.computeUInt32Size(5, availableProcessors) + CodedOutputStream.computeUInt64Size(6, totalRam) + CodedOutputStream.computeUInt64Size(7, diskSpace) + CodedOutputStream.computeBoolSize(10, isEmulator) + CodedOutputStream.computeUInt32Size(12, state) + (manufacturer == null ? 0 : CodedOutputStream.computeBytesSize(13, manufacturer));
        if (modelClass != null) {
            i = CodedOutputStream.computeBytesSize(14, modelClass);
        }
        return size + i;
    }

    private static int getBinaryImageSize(ByteString packageNameBytes, ByteString optionalBuildIdBytes) {
        int size = 0 + CodedOutputStream.computeUInt64Size(1, 0) + CodedOutputStream.computeUInt64Size(2, 0) + CodedOutputStream.computeBytesSize(3, packageNameBytes);
        if (optionalBuildIdBytes != null) {
            return size + CodedOutputStream.computeBytesSize(4, optionalBuildIdBytes);
        }
        return size;
    }

    private static int getSessionEventSize(long eventTime, String eventType, TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionsDepth, Map<String, String> customAttributes, RunningAppProcessInfo runningAppProcessInfo, int orientation, ByteString packageNameBytes, ByteString optionalBuildIdBytes, Float batteryLevel, int batteryVelocity, boolean proximityEnabled, long heapAllocatedSize, long diskUsed, ByteString log) {
        int size = 0 + CodedOutputStream.computeUInt64Size(1, eventTime) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(eventType));
        int eventAppSize = getEventAppSize(exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes, customAttributes, runningAppProcessInfo, orientation);
        int size2 = size + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(eventAppSize) + eventAppSize;
        int eventDeviceSize = getEventDeviceSize(batteryLevel, batteryVelocity, proximityEnabled, orientation, heapAllocatedSize, diskUsed);
        int size3 = size2 + CodedOutputStream.computeTagSize(5) + CodedOutputStream.computeRawVarint32Size(eventDeviceSize) + eventDeviceSize;
        if (log == null) {
            return size3;
        }
        int logSize = getEventLogSize(log);
        return size3 + CodedOutputStream.computeTagSize(6) + CodedOutputStream.computeRawVarint32Size(logSize) + logSize;
    }

    private static int getEventAppSize(TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionsDepth, ByteString packageNameBytes, ByteString optionalBuildIdBytes, Map<String, String> customAttributes, RunningAppProcessInfo runningAppProcessInfo, int orientation) {
        RunningAppProcessInfo runningAppProcessInfo2 = runningAppProcessInfo;
        int executionSize = getEventAppExecutionSize(exception, exceptionThread, exceptionStack, otherThreads, otherStacks, maxChainedExceptionsDepth, packageNameBytes, optionalBuildIdBytes);
        boolean z = true;
        int size = 0 + CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(executionSize) + executionSize;
        if (customAttributes != null) {
            for (Entry<String, String> entry : customAttributes.entrySet()) {
                int entrySize = getEventAppCustomAttributeSize((String) entry.getKey(), (String) entry.getValue());
                size += CodedOutputStream.computeTagSize(2) + CodedOutputStream.computeRawVarint32Size(entrySize) + entrySize;
            }
        }
        if (runningAppProcessInfo2 != null) {
            if (runningAppProcessInfo2.importance == 100) {
                z = false;
            }
            size += CodedOutputStream.computeBoolSize(3, z);
        }
        return size + CodedOutputStream.computeUInt32Size(4, orientation);
    }

    private static int getEventAppExecutionSize(TrimmedThrowableData exception, Thread exceptionThread, StackTraceElement[] exceptionStack, Thread[] otherThreads, List<StackTraceElement[]> otherStacks, int maxChainedExceptionDepth, ByteString packageNameBytes, ByteString optionalBuildIdBytes) {
        int threadSize = getThreadSize(exceptionThread, exceptionStack, 4, true);
        int size = 0 + CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(threadSize) + threadSize;
        int len = otherThreads.length;
        for (int i = 0; i < len; i++) {
            int threadSize2 = getThreadSize(otherThreads[i], (StackTraceElement[]) otherStacks.get(i), 0, false);
            size += CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(threadSize2) + threadSize2;
        }
        int exceptionSize = getEventAppExecutionExceptionSize(exception, 1, maxChainedExceptionDepth);
        int size2 = size + CodedOutputStream.computeTagSize(2) + CodedOutputStream.computeRawVarint32Size(exceptionSize) + exceptionSize;
        int signalSize = getEventAppExecutionSignalSize();
        int size3 = size2 + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(signalSize) + signalSize;
        int binaryImageSize = getBinaryImageSize(packageNameBytes, optionalBuildIdBytes);
        return size3 + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(binaryImageSize) + binaryImageSize;
    }

    private static int getEventAppCustomAttributeSize(String key, String value) {
        return CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(key)) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(value == null ? "" : value));
    }

    private static int getEventDeviceSize(Float batteryLevel, int batteryVelocity, boolean proximityEnabled, int orientation, long heapAllocatedSize, long diskUsed) {
        int size = 0;
        if (batteryLevel != null) {
            size = 0 + CodedOutputStream.computeFloatSize(1, batteryLevel.floatValue());
        }
        return size + CodedOutputStream.computeSInt32Size(2, batteryVelocity) + CodedOutputStream.computeBoolSize(3, proximityEnabled) + CodedOutputStream.computeUInt32Size(4, orientation) + CodedOutputStream.computeUInt64Size(5, heapAllocatedSize) + CodedOutputStream.computeUInt64Size(6, diskUsed);
    }

    private static int getEventLogSize(ByteString log) {
        return CodedOutputStream.computeBytesSize(1, log);
    }

    private static int getEventAppExecutionExceptionSize(TrimmedThrowableData ex, int chainDepth, int maxChainedExceptionsDepth) {
        int size = 0 + CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(ex.className));
        String message = ex.localizedMessage;
        if (message != null) {
            size += CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(message));
        }
        for (StackTraceElement element : ex.stacktrace) {
            int frameSize = getFrameSize(element, true);
            size += CodedOutputStream.computeTagSize(4) + CodedOutputStream.computeRawVarint32Size(frameSize) + frameSize;
        }
        TrimmedThrowableData cause = ex.cause;
        if (cause == null) {
            return size;
        }
        if (chainDepth < maxChainedExceptionsDepth) {
            int exceptionSize = getEventAppExecutionExceptionSize(cause, chainDepth + 1, maxChainedExceptionsDepth);
            return size + CodedOutputStream.computeTagSize(6) + CodedOutputStream.computeRawVarint32Size(exceptionSize) + exceptionSize;
        }
        int overflowCount = 0;
        while (cause != null) {
            cause = cause.cause;
            overflowCount++;
        }
        return size + CodedOutputStream.computeUInt32Size(7, overflowCount);
    }

    private static int getEventAppExecutionSignalSize() {
        return 0 + CodedOutputStream.computeBytesSize(1, SIGNAL_DEFAULT_BYTE_STRING) + CodedOutputStream.computeBytesSize(2, SIGNAL_DEFAULT_BYTE_STRING) + CodedOutputStream.computeUInt64Size(3, 0);
    }

    private static int getFrameSize(StackTraceElement element, boolean isCrashedThread) {
        int size;
        int i = 0;
        if (element.isNativeMethod()) {
            size = 0 + CodedOutputStream.computeUInt64Size(1, (long) Math.max(element.getLineNumber(), 0));
        } else {
            size = 0 + CodedOutputStream.computeUInt64Size(1, 0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(element.getClassName());
        sb.append(".");
        sb.append(element.getMethodName());
        int size2 = size + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(sb.toString()));
        if (element.getFileName() != null) {
            size2 += CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(element.getFileName()));
        }
        if (!element.isNativeMethod() && element.getLineNumber() > 0) {
            size2 += CodedOutputStream.computeUInt64Size(4, (long) element.getLineNumber());
        }
        if (isCrashedThread) {
            i = 2;
        }
        return size2 + CodedOutputStream.computeUInt32Size(5, i);
    }

    private static int getThreadSize(Thread thread, StackTraceElement[] stackTraceElements, int importance, boolean isCrashedThread) {
        int size = CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(thread.getName())) + CodedOutputStream.computeUInt32Size(2, importance);
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            int frameSize = getFrameSize(stackTraceElement, isCrashedThread);
            size += CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(frameSize) + frameSize;
        }
        return size;
    }

    private static ByteString stringToByteString(String s) {
        if (s == null) {
            return null;
        }
        return ByteString.copyFromUtf8(s);
    }
}
