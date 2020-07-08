package android.support.p000v4.media;

import android.media.MediaDescription;
import android.net.Uri;

/* renamed from: android.support.v4.media.MediaDescriptionCompatApi23 */
class MediaDescriptionCompatApi23 {

    /* renamed from: android.support.v4.media.MediaDescriptionCompatApi23$Builder */
    static class Builder {
        public static void setMediaUri(Object builderObj, Uri mediaUri) {
            ((android.media.MediaDescription.Builder) builderObj).setMediaUri(mediaUri);
        }

        private Builder() {
        }
    }

    public static Uri getMediaUri(Object descriptionObj) {
        return ((MediaDescription) descriptionObj).getMediaUri();
    }

    private MediaDescriptionCompatApi23() {
    }
}
