package com.navibees.visioglobe.p020g;

import com.navibees.visioglobe.p019f.C1501d;
import com.navibees.visioglobe.p019f.C1501d.C1502a;

/* renamed from: com.navibees.visioglobe.g.l */
/* compiled from: VgMyRemoteMapManager */
public interface C1523l {

    /* renamed from: com.navibees.visioglobe.g.l$a */
    /* compiled from: VgMyRemoteMapManager */
    public interface C1524a {

        /* renamed from: com.navibees.visioglobe.g.l$a$a */
        /* compiled from: VgMyRemoteMapManager */
        public enum C1525a {
            eSuccess,
            eFailed
        }

        /* renamed from: a */
        void mo28291a(C1523l lVar, C1525a aVar, C1501d dVar);

        /* renamed from: a */
        void mo28292a(C1523l lVar, C1525a aVar, String str);
    }

    /* renamed from: com.navibees.visioglobe.g.l$b */
    /* compiled from: VgMyRemoteMapManager */
    public static class C1526b {

        /* renamed from: a */
        public String f869a;

        /* renamed from: b */
        public String f870b;

        /* renamed from: c */
        public String f871c;

        /* renamed from: d */
        public String f872d;

        /* renamed from: e */
        public String f873e = "descriptor.json";

        /* renamed from: f */
        public C1524a f874f;
    }

    /* renamed from: a */
    String mo28609a(String str);

    /* renamed from: a */
    boolean mo28612a(C1502a aVar);
}
