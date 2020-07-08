package com.google.firebase.crashlytics.internal.log;

import android.content.Context;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.File;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class LogFileManager {
    private static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
    private static final String LOGFILE_EXT = ".temp";
    private static final String LOGFILE_PREFIX = "crashlytics-userlog-";
    static final int MAX_LOG_SIZE = 65536;
    private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore();
    private final Context context;
    private FileLogStore currentLog;
    private final DirectoryProvider directoryProvider;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public interface DirectoryProvider {
        File getLogFileDir();
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        public void writeToLog(long timestamp, String msg) {
        }

        public byte[] getLogAsBytes() {
            return null;
        }

        public String getLogAsString() {
            return null;
        }

        public void closeLogFile() {
        }

        public void deleteLogFile() {
        }
    }

    public LogFileManager(Context context2, DirectoryProvider directoryProvider2) {
        this(context2, directoryProvider2, null);
    }

    public LogFileManager(Context context2, DirectoryProvider directoryProvider2, String currentSessionId) {
        this.context = context2;
        this.directoryProvider = directoryProvider2;
        this.currentLog = NOOP_LOG_STORE;
        setCurrentSession(currentSessionId);
    }

    public final void setCurrentSession(String sessionId) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (sessionId != null) {
            if (!CommonUtils.getBooleanResourceValue(this.context, COLLECT_CUSTOM_LOGS, true)) {
                Logger.getLogger().mo19679d("Preferences requested no custom logs. Aborting log file creation.");
            } else {
                setLogFile(getWorkingFileForSession(sessionId), 65536);
            }
        }
    }

    public void writeToLog(long timestamp, String msg) {
        this.currentLog.writeToLog(timestamp, msg);
    }

    public byte[] getBytesForLog() {
        return this.currentLog.getLogAsBytes();
    }

    public String getLogString() {
        return this.currentLog.getLogAsString();
    }

    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    public void discardOldLogFiles(Set<String> sessionIdsToKeep) {
        File[] logFiles = this.directoryProvider.getLogFileDir().listFiles();
        if (logFiles != null) {
            for (File file : logFiles) {
                if (!sessionIdsToKeep.contains(getSessionIdForFile(file))) {
                    file.delete();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLogFile(File workingFile, int maxLogSize) {
        this.currentLog = new QueueFileLogStore(workingFile, maxLogSize);
    }

    private File getWorkingFileForSession(String sessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOGFILE_PREFIX);
        sb.append(sessionId);
        sb.append(LOGFILE_EXT);
        return new File(this.directoryProvider.getLogFileDir(), sb.toString());
    }

    private String getSessionIdForFile(File workingFile) {
        String filename = workingFile.getName();
        int indexOfExtension = filename.lastIndexOf(LOGFILE_EXT);
        if (indexOfExtension == -1) {
            return filename;
        }
        return filename.substring(LOGFILE_PREFIX.length(), indexOfExtension);
    }
}
