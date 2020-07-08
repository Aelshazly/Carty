package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Date;
import java.util.TimeZone;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.cookie.DateUtils */
public final class DateUtils {
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static Date parseDate(String dateValue) throws DateParseException {
        return parseDate(dateValue, null, null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats) throws DateParseException {
        return parseDate(dateValue, dateFormats, null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats, Date startDate) throws DateParseException {
        Date d = p008cz.msebera.android.httpclient.client.utils.DateUtils.parseDate(dateValue, dateFormats, startDate);
        if (d != null) {
            return d;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to parse the date ");
        sb.append(dateValue);
        throw new DateParseException(sb.toString());
    }

    public static String formatDate(Date date) {
        return p008cz.msebera.android.httpclient.client.utils.DateUtils.formatDate(date);
    }

    public static String formatDate(Date date, String pattern) {
        return p008cz.msebera.android.httpclient.client.utils.DateUtils.formatDate(date, pattern);
    }

    private DateUtils() {
    }
}
