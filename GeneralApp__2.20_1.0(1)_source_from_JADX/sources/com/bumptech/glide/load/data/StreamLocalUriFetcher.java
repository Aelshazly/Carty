package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
    private static final int ID_CONTACTS_CONTACT = 3;
    private static final int ID_CONTACTS_LOOKUP = 1;
    private static final int ID_CONTACTS_PHOTO = 4;
    private static final int ID_CONTACTS_THUMBNAIL = 2;
    private static final int ID_LOOKUP_BY_PHONE = 5;
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);

    static {
        String str = "com.android.contacts";
        URI_MATCHER.addURI(str, "contacts/lookup/*/#", 1);
        URI_MATCHER.addURI(str, "contacts/lookup/*", 1);
        URI_MATCHER.addURI(str, "contacts/#/photo", 2);
        URI_MATCHER.addURI(str, "contacts/#", 3);
        URI_MATCHER.addURI(str, "contacts/#/display_photo", 4);
        URI_MATCHER.addURI(str, "phone_lookup/*", 5);
    }

    public StreamLocalUriFetcher(ContentResolver resolver, Uri uri) {
        super(resolver, uri);
    }

    /* access modifiers changed from: protected */
    public InputStream loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        InputStream inputStream = loadResourceFromUri(uri, contentResolver);
        if (inputStream != null) {
            return inputStream;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("InputStream is null for ");
        sb.append(uri);
        throw new FileNotFoundException(sb.toString());
    }

    private InputStream loadResourceFromUri(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        int match = URI_MATCHER.match(uri);
        if (match != 1) {
            if (match == 3) {
                return openContactPhotoInputStream(contentResolver, uri);
            }
            if (match != 5) {
                return contentResolver.openInputStream(uri);
            }
        }
        Uri uri2 = Contacts.lookupContact(contentResolver, uri);
        if (uri2 != null) {
            return openContactPhotoInputStream(contentResolver, uri2);
        }
        throw new FileNotFoundException("Contact cannot be found");
    }

    private InputStream openContactPhotoInputStream(ContentResolver contentResolver, Uri contactUri) {
        return Contacts.openContactPhotoInputStream(contentResolver, contactUri, true);
    }

    /* access modifiers changed from: protected */
    public void close(InputStream data) throws IOException {
        data.close();
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }
}
