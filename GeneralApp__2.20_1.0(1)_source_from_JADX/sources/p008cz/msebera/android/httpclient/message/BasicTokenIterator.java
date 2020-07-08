package p008cz.msebera.android.httpclient.message;

import java.util.NoSuchElementException;
import p008cz.msebera.android.httpclient.HeaderIterator;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.TokenIterator;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.message.BasicTokenIterator */
public class BasicTokenIterator implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected String currentHeader;
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos = findNext(-1);

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.headerIt = (HeaderIterator) Args.notNull(headerIterator, "Header iterator");
    }

    public boolean hasNext() {
        return this.currentToken != null;
    }

    public String nextToken() throws NoSuchElementException, ParseException {
        if (this.currentToken != null) {
            String result = this.currentToken;
            this.searchPos = findNext(this.searchPos);
            return result;
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public final Object next() throws NoSuchElementException, ParseException {
        return nextToken();
    }

    public final void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    /* access modifiers changed from: protected */
    public int findNext(int pos) throws ParseException {
        int from;
        int from2 = pos;
        if (from2 >= 0) {
            from = findTokenSeparator(from2);
        } else if (!this.headerIt.hasNext()) {
            return -1;
        } else {
            this.currentHeader = this.headerIt.nextHeader().getValue();
            from = 0;
        }
        int start = findTokenStart(from);
        if (start < 0) {
            this.currentToken = null;
            return -1;
        }
        int end = findTokenEnd(start);
        this.currentToken = createToken(this.currentHeader, start, end);
        return end;
    }

    /* access modifiers changed from: protected */
    public String createToken(String value, int start, int end) {
        return value.substring(start, end);
    }

    /* access modifiers changed from: protected */
    public int findTokenStart(int pos) {
        int from = Args.notNegative(pos, "Search position");
        boolean found = false;
        while (!found) {
            String str = this.currentHeader;
            if (str == null) {
                break;
            }
            int to = str.length();
            while (!found && from < to) {
                char ch = this.currentHeader.charAt(from);
                if (isTokenSeparator(ch) || isWhitespace(ch)) {
                    from++;
                } else if (isTokenChar(this.currentHeader.charAt(from))) {
                    found = true;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid character before token (pos ");
                    sb.append(from);
                    sb.append("): ");
                    sb.append(this.currentHeader);
                    throw new ParseException(sb.toString());
                }
            }
            if (!found) {
                if (this.headerIt.hasNext()) {
                    this.currentHeader = this.headerIt.nextHeader().getValue();
                    from = 0;
                } else {
                    this.currentHeader = null;
                }
            }
        }
        if (found) {
            return from;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int findTokenSeparator(int pos) {
        int from = Args.notNegative(pos, "Search position");
        boolean found = false;
        int to = this.currentHeader.length();
        while (!found && from < to) {
            char ch = this.currentHeader.charAt(from);
            if (isTokenSeparator(ch)) {
                found = true;
            } else if (isWhitespace(ch)) {
                from++;
            } else {
                String str = "): ";
                if (isTokenChar(ch)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Tokens without separator (pos ");
                    sb.append(from);
                    sb.append(str);
                    sb.append(this.currentHeader);
                    throw new ParseException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid character after token (pos ");
                sb2.append(from);
                sb2.append(str);
                sb2.append(this.currentHeader);
                throw new ParseException(sb2.toString());
            }
        }
        return from;
    }

    /* access modifiers changed from: protected */
    public int findTokenEnd(int from) {
        Args.notNegative(from, "Search position");
        int to = this.currentHeader.length();
        int end = from + 1;
        while (end < to && isTokenChar(this.currentHeader.charAt(end))) {
            end++;
        }
        return end;
    }

    /* access modifiers changed from: protected */
    public boolean isTokenSeparator(char ch) {
        return ch == ',';
    }

    /* access modifiers changed from: protected */
    public boolean isWhitespace(char ch) {
        return ch == 9 || Character.isSpaceChar(ch);
    }

    /* access modifiers changed from: protected */
    public boolean isTokenChar(char ch) {
        if (Character.isLetterOrDigit(ch)) {
            return true;
        }
        if (!Character.isISOControl(ch) && !isHttpSeparator(ch)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isHttpSeparator(char ch) {
        return HTTP_SEPARATORS.indexOf(ch) >= 0;
    }
}
