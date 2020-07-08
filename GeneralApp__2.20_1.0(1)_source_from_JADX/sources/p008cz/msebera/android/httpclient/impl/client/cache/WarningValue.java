package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.WarningValue */
class WarningValue {
    private static final String ASCTIME_DATE = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4}";
    private static final String DATE1 = "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}";
    private static final String DATE2 = "\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}";
    private static final String DATE3 = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d";
    private static final String DOMAINLABEL = "\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?";
    private static final String HOST = "((\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?)|(\\d+\\.\\d+\\.\\d+\\.\\d+)";
    private static final String HOSTNAME = "(\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?";
    private static final String HOSTPORT = "(((\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?)|(\\d+\\.\\d+\\.\\d+\\.\\d+))(\\:\\d*)?";
    private static final Pattern HOSTPORT_PATTERN = Pattern.compile(HOSTPORT);
    private static final String HTTP_DATE = "((Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4})";
    private static final String IPV4ADDRESS = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    private static final String MONTH = "Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec";
    private static final String PORT = "\\d*";
    private static final String RFC1123_DATE = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT";
    private static final String RFC850_DATE = "(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT";
    private static final String TIME = "\\d{2}:\\d{2}:\\d{2}";
    private static final String TOPLABEL = "\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?";
    private static final String WARN_DATE = "\"(((Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4}))\"";
    private static final Pattern WARN_DATE_PATTERN = Pattern.compile(WARN_DATE);
    private static final String WEEKDAY = "Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday";
    private static final String WKDAY = "Mon|Tue|Wed|Thu|Fri|Sat|Sun";
    private int init_offs;
    private int offs;
    private final String src;
    private String warnAgent;
    private int warnCode;
    private Date warnDate;
    private String warnText;

    WarningValue(String s) {
        this(s, 0);
    }

    WarningValue(String s, int offs2) {
        this.init_offs = offs2;
        this.offs = offs2;
        this.src = s;
        consumeWarnValue();
    }

    public static WarningValue[] getWarningValues(Header h) {
        List<WarningValue> out = new ArrayList<>();
        String src2 = h.getValue();
        int offs2 = 0;
        while (offs2 < src2.length()) {
            try {
                WarningValue wv = new WarningValue(src2, offs2);
                out.add(wv);
                offs2 = wv.offs;
            } catch (IllegalArgumentException e) {
                int nextComma = src2.indexOf(44, offs2);
                if (nextComma == -1) {
                    break;
                }
                offs2 = nextComma + 1;
            }
        }
        return (WarningValue[]) out.toArray(new WarningValue[0]);
    }

    /* access modifiers changed from: protected */
    public void consumeLinearWhitespace() {
        while (this.offs < this.src.length()) {
            char charAt = this.src.charAt(this.offs);
            if (charAt != 9) {
                if (charAt != 13) {
                    if (charAt != ' ') {
                        return;
                    }
                } else if (this.offs + 2 < this.src.length() && this.src.charAt(this.offs + 1) == 10 && (this.src.charAt(this.offs + 2) == ' ' || this.src.charAt(this.offs + 2) == 9)) {
                    this.offs += 2;
                } else {
                    return;
                }
            }
            this.offs++;
        }
    }

    private boolean isChar(char c) {
        int i = c;
        return i >= 0 && i <= 127;
    }

    private boolean isControl(char c) {
        int i = c;
        return i == 127 || (i >= 0 && i <= 31);
    }

    private boolean isSeparator(char c) {
        return c == '(' || c == ')' || c == '<' || c == '>' || c == '@' || c == ',' || c == ';' || c == ':' || c == '\\' || c == '\"' || c == '/' || c == '[' || c == ']' || c == '?' || c == '=' || c == '{' || c == '}' || c == ' ' || c == 9;
    }

    /* access modifiers changed from: protected */
    public void consumeToken() {
        if (!isTokenChar(this.src.charAt(this.offs))) {
            parseError();
        }
        while (this.offs < this.src.length() && isTokenChar(this.src.charAt(this.offs))) {
            this.offs++;
        }
    }

    private boolean isTokenChar(char c) {
        return isChar(c) && !isControl(c) && !isSeparator(c);
    }

    /* access modifiers changed from: protected */
    public void consumeHostPort() {
        Matcher m = HOSTPORT_PATTERN.matcher(this.src.substring(this.offs));
        if (!m.find()) {
            parseError();
        }
        if (m.start() != 0) {
            parseError();
        }
        this.offs += m.end();
    }

    /* access modifiers changed from: protected */
    public void consumeWarnAgent() {
        int curr_offs = this.offs;
        try {
            consumeHostPort();
            this.warnAgent = this.src.substring(curr_offs, this.offs);
            consumeCharacter(' ');
        } catch (IllegalArgumentException e) {
            this.offs = curr_offs;
            consumeToken();
            this.warnAgent = this.src.substring(curr_offs, this.offs);
            consumeCharacter(' ');
        }
    }

    /* access modifiers changed from: protected */
    public void consumeQuotedString() {
        if (this.src.charAt(this.offs) != '\"') {
            parseError();
        }
        this.offs++;
        boolean foundEnd = false;
        while (this.offs < this.src.length() && !foundEnd) {
            char c = this.src.charAt(this.offs);
            if (this.offs + 1 < this.src.length() && c == '\\' && isChar(this.src.charAt(this.offs + 1))) {
                this.offs += 2;
            } else if (c == '\"') {
                foundEnd = true;
                this.offs++;
            } else if (c == '\"' || isControl(c)) {
                parseError();
            } else {
                this.offs++;
            }
        }
        if (!foundEnd) {
            parseError();
        }
    }

    /* access modifiers changed from: protected */
    public void consumeWarnText() {
        int curr = this.offs;
        consumeQuotedString();
        this.warnText = this.src.substring(curr, this.offs);
    }

    /* access modifiers changed from: protected */
    public void consumeWarnDate() {
        int curr = this.offs;
        Matcher m = WARN_DATE_PATTERN.matcher(this.src.substring(this.offs));
        if (!m.lookingAt()) {
            parseError();
        }
        this.offs += m.end();
        this.warnDate = DateUtils.parseDate(this.src.substring(curr + 1, this.offs - 1));
    }

    /* access modifiers changed from: protected */
    public void consumeWarnValue() {
        consumeLinearWhitespace();
        consumeWarnCode();
        consumeWarnAgent();
        consumeWarnText();
        if (this.offs + 1 < this.src.length() && this.src.charAt(this.offs) == ' ' && this.src.charAt(this.offs + 1) == '\"') {
            consumeCharacter(' ');
            consumeWarnDate();
        }
        consumeLinearWhitespace();
        if (this.offs != this.src.length()) {
            consumeCharacter(',');
        }
    }

    /* access modifiers changed from: protected */
    public void consumeCharacter(char c) {
        if (this.offs + 1 > this.src.length() || c != this.src.charAt(this.offs)) {
            parseError();
        }
        this.offs++;
    }

    /* access modifiers changed from: protected */
    public void consumeWarnCode() {
        if (this.offs + 4 > this.src.length() || !Character.isDigit(this.src.charAt(this.offs)) || !Character.isDigit(this.src.charAt(this.offs + 1)) || !Character.isDigit(this.src.charAt(this.offs + 2)) || this.src.charAt(this.offs + 3) != ' ') {
            parseError();
        }
        String str = this.src;
        int i = this.offs;
        this.warnCode = Integer.parseInt(str.substring(i, i + 3));
        this.offs += 4;
    }

    private void parseError() {
        String s = this.src.substring(this.init_offs);
        StringBuilder sb = new StringBuilder();
        sb.append("Bad warn code \"");
        sb.append(s);
        sb.append("\"");
        throw new IllegalArgumentException(sb.toString());
    }

    public int getWarnCode() {
        return this.warnCode;
    }

    public String getWarnAgent() {
        return this.warnAgent;
    }

    public String getWarnText() {
        return this.warnText;
    }

    public Date getWarnDate() {
        return this.warnDate;
    }

    public String toString() {
        if (this.warnDate != null) {
            return String.format("%d %s %s \"%s\"", new Object[]{Integer.valueOf(this.warnCode), this.warnAgent, this.warnText, DateUtils.formatDate(this.warnDate)});
        }
        return String.format("%d %s %s", new Object[]{Integer.valueOf(this.warnCode), this.warnAgent, this.warnText});
    }
}
