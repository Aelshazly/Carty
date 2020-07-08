package com.google.firebase.crashlytics.internal.ndk;

import com.google.firebase.crashlytics.internal.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class ProcMapEntryParser {
    private static final Pattern MAP_REGEX = Pattern.compile("\\s*(\\p{XDigit}+)-\\s*(\\p{XDigit}+)\\s+(.{4})\\s+\\p{XDigit}+\\s+.+\\s+\\d+\\s+(.*)");

    private ProcMapEntryParser() {
    }

    static ProcMapEntry parse(String mapEntry) {
        Matcher m = MAP_REGEX.matcher(mapEntry);
        if (!m.matches()) {
            return null;
        }
        try {
            long address = Long.valueOf(m.group(1), 16).longValue();
            ProcMapEntry procMapEntry = new ProcMapEntry(address, Long.valueOf(m.group(2), 16).longValue() - address, m.group(3), m.group(4));
            return procMapEntry;
        } catch (Exception e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not parse map entry: ");
            sb.append(mapEntry);
            logger.mo19679d(sb.toString());
            return null;
        }
    }
}
