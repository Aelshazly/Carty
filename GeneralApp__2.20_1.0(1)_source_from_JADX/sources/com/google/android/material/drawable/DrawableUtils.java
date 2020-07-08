package com.google.android.material.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableUtils {
    private DrawableUtils() {
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, ColorStateList tint, Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return new PorterDuffColorFilter(tint.getColorForState(drawable.getState(), 0), tintMode);
    }

    public static AttributeSet parseDrawableXml(Context context, int id, CharSequence startTag) {
        int type;
        try {
            XmlPullParser parser = context.getResources().getXml(id);
            do {
                type = parser.next();
                if (type == 2) {
                    break;
                }
            } while (type != 1);
            if (type != 2) {
                throw new XmlPullParserException("No start tag found");
            } else if (TextUtils.equals(parser.getName(), startTag)) {
                return Xml.asAttributeSet(parser);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Must have a <");
                sb.append(startTag);
                sb.append("> start tag");
                throw new XmlPullParserException(sb.toString());
            }
        } catch (IOException | XmlPullParserException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Can't load badge resource ID #0x");
            sb2.append(Integer.toHexString(id));
            NotFoundException exception = new NotFoundException(sb2.toString());
            exception.initCause(e);
            throw exception;
        }
    }
}
