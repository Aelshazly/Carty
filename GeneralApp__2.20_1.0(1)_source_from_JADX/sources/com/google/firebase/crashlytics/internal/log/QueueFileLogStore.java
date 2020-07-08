package com.google.firebase.crashlytics.internal.log;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.log.QueueFile.ElementReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class QueueFileLogStore implements FileLogStore {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private class LogBytes {
        public final byte[] bytes;
        public final int offset;

        LogBytes(byte[] bytes2, int offset2) {
            this.bytes = bytes2;
            this.offset = offset2;
        }
    }

    QueueFileLogStore(File workingFile2, int maxLogSize2) {
        this.workingFile = workingFile2;
        this.maxLogSize = maxLogSize2;
    }

    public void writeToLog(long timestamp, String msg) {
        openLogFile();
        doWriteToLog(timestamp, msg);
    }

    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        byte[] rawBytes = new byte[logBytes.offset];
        System.arraycopy(logBytes.bytes, 0, rawBytes, 0, logBytes.offset);
        return rawBytes;
    }

    public String getLogAsString() {
        byte[] logBytes = getLogAsBytes();
        if (logBytes != null) {
            return new String(logBytes, UTF_8);
        }
        return null;
    }

    private LogBytes getLogBytes() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        QueueFile queueFile = this.logFile;
        if (queueFile == null) {
            return null;
        }
        final int[] offsetHolder = {0};
        final byte[] logBytes = new byte[queueFile.usedBytes()];
        try {
            this.logFile.forEach(new ElementReader() {
                public void read(InputStream in, int length) throws IOException {
                    try {
                        in.read(logBytes, offsetHolder[0], length);
                        int[] iArr = offsetHolder;
                        iArr[0] = iArr[0] + length;
                    } finally {
                        in.close();
                    }
                }
            });
        } catch (IOException e) {
            Logger.getLogger().mo19682e("A problem occurred while reading the Crashlytics log file.", e);
        }
        return new LogBytes(logBytes, offsetHolder[0]);
    }

    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Could not open log file: ");
                sb.append(this.workingFile);
                logger.mo19682e(sb.toString(), e);
            }
        }
    }

    private void doWriteToLog(long timestamp, String msg) {
        String str = " ";
        if (this.logFile != null) {
            if (msg == null) {
                msg = "null";
            }
            try {
                int quarterMaxLogSize = this.maxLogSize / 4;
                if (msg.length() > quarterMaxLogSize) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("...");
                    sb.append(msg.substring(msg.length() - quarterMaxLogSize));
                    msg = sb.toString();
                }
                this.logFile.add(String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(timestamp), msg.replaceAll("\r", str).replaceAll("\n", str)}).getBytes(UTF_8));
                while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                    this.logFile.remove();
                }
            } catch (IOException e) {
                Logger.getLogger().mo19682e("There was a problem writing to the Crashlytics log.", e);
            }
        }
    }
}
