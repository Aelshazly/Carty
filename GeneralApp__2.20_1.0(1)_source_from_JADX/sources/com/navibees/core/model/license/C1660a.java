package com.navibees.core.model.license;

import android.app.Application;
import android.util.Base64;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.ApplicationConfiguration;
import com.navibees.core.model.metadata.json.Venue;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.UByte;

/* renamed from: com.navibees.core.model.license.a */
/* compiled from: LicenseManager */
public final class C1660a {

    /* renamed from: a */
    private boolean f1307a = false;

    /* renamed from: b */
    private boolean f1308b = true;

    /* renamed from: c */
    private Application f1309c;

    /* renamed from: com.navibees.core.model.license.a$a */
    /* compiled from: LicenseManager */
    static /* synthetic */ class C1661a {

        /* renamed from: a */
        static final /* synthetic */ int[] f1310a = new int[NaviBeesFeature.values().length];

        static {
            try {
                f1310a[NaviBeesFeature.Multi_Floor_Navigation.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1310a[NaviBeesFeature.TurnByTurn_Navigation.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1310a[NaviBeesFeature.Navigation_TTS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public C1660a(Application application) {
        this.f1309c = application;
    }

    /* renamed from: b */
    private void m956b(NaviBeesFeature naviBeesFeature) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        ApplicationConfiguration configuration = NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().getConfiguration();
        if (configuration != null) {
            int i = C1661a.f1310a[naviBeesFeature.ordinal()];
            if (i == 1) {
                mo29047a();
                m956b(NaviBeesFeature.Positioning);
            } else if (i == 2) {
                m956b(NaviBeesFeature.Multi_Floor_Navigation);
            } else if (i == 3) {
                m956b(NaviBeesFeature.TurnByTurn_Navigation);
            }
            if (((configuration.licenseFeatureStr >> naviBeesFeature.ordinal()) & 1) != 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("NaviBeesFeature:");
                sb.append(naviBeesFeature);
                sb.append(" not included in License");
                throw new NaviBeesLicenseNotAuthorizedException(sb.toString());
            }
            return;
        }
        throw new NaviBeesLicenseNotAuthorizedException("In Valid Plan");
    }

    /* renamed from: c */
    private String m957c() {
        try {
            return new String(Base64.decode(new byte[]{73, 48, 53, 104, 86, 109, 107, 106}, 0), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* renamed from: d */
    private boolean m958d() {
        String str = NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().getConfiguration().accessToken;
        if (str == null) {
            return false;
        }
        String b = m955b();
        if (b == null || !str.equals(b)) {
            this.f1307a = false;
            return false;
        }
        this.f1307a = true;
        return true;
    }

    /* renamed from: e */
    private boolean m959e() {
        ApplicationConfiguration configuration = NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().getConfiguration();
        try {
            this.f1308b = m953a(new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(configuration.licenseStartDate).getTime() + (((long) configuration.licenseDuration) * 24 * 60 * 60 * 1000)), new Date());
            return this.f1308b;
        } catch (ParseException e) {
            e.printStackTrace();
            this.f1308b = true;
            return this.f1308b;
        }
    }

    /* renamed from: a */
    public void mo29048a(NaviBeesFeature naviBeesFeature) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        String str = "License expired";
        if (!this.f1307a) {
            if (!m958d()) {
                throw new NaviBeesLicenseNotAuthorizedException("In Valid Access Token");
            } else if (m959e()) {
                throw new NaviBeesLicenseExpireException(str);
            } else if (naviBeesFeature != null) {
                m956b(naviBeesFeature);
            }
        } else if (this.f1308b) {
            throw new NaviBeesLicenseExpireException(str);
        } else if (naviBeesFeature != null) {
            m956b(naviBeesFeature);
        }
    }

    /* renamed from: a */
    public void mo29047a() throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        Venue currentVenue = NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().getCurrentVenue();
        if (currentVenue != null) {
            int i = currentVenue.mapProvider.type;
            if (i == 0) {
                mo29048a(NaviBeesFeature._2D_Maps);
            } else if (i == 1) {
                mo29048a(NaviBeesFeature._3D_Maps);
            }
        }
    }

    /* renamed from: a */
    public boolean mo29049a(String str, int i) {
        try {
            this.f1308b = m953a(new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str).getTime() + (((long) i) * 24 * 60 * 60 * 1000)), new Date());
            return this.f1308b;
        } catch (ParseException e) {
            e.printStackTrace();
            this.f1308b = true;
            return this.f1308b;
        }
    }

    /* renamed from: a */
    private byte[] m954a(String str, byte[] bArr) {
        if (str != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                instance.reset();
                if (bArr != null) {
                    instance.update(bArr);
                }
                return instance.digest(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    /* renamed from: b */
    private String m955b() {
        ApplicationConfiguration configuration = NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().getConfiguration();
        String c = m957c();
        if (c == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(NaviBeesManager.getInstance(this.f1309c).getMetaDataManager().navibeesApp.identifier);
        stringBuffer.append(c);
        stringBuffer.append(configuration.licenseFeatureStr);
        stringBuffer.append(c);
        stringBuffer.append(configuration.licenseStartDate);
        stringBuffer.append(c);
        stringBuffer.append(configuration.licenseDuration);
        return m952a(m954a(stringBuffer.toString(), (byte[]) null));
    }

    /* renamed from: a */
    private String m952a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private boolean m953a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(date);
        instance2.setTime(date2);
        return instance2.after(instance);
    }
}
