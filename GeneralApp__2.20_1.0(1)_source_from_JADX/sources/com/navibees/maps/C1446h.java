package com.navibees.maps;

import android.app.Application;
import androidx.collection.SimpleArrayMap;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.VisioFloor;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.visioglobe.p020g.C1520i;
import com.visioglobe.libVisioMove.C1732libVisioMove;
import com.visioglobe.libVisioMove.VgIMapModule;
import com.visioglobe.libVisioMove.VgIModule;
import com.visioglobe.libVisioMove.VgIModuleManager;
import com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr;
import com.visioglobe.libVisioMove.VgINavigationModule;
import com.visioglobe.libVisioMove.VgINavigationRequestParameters;
import com.visioglobe.libVisioMove.VgIRouteRefPtr;
import com.visioglobe.libVisioMove.VgIRoutingNodeRefPtr;
import com.visioglobe.libVisioMove.VgIRoutingNodeRefPtrVector;
import com.visioglobe.libVisioMove.VgSurfaceView;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.navibees.maps.h */
/* compiled from: VgMyNavigationHelperN */
public class C1446h implements C1520i {

    /* renamed from: b */
    protected static final String[] f587b = {"en", "fr", "ar", "de", "ur"};

    /* renamed from: c */
    protected static final int f588c = f587b.length;

    /* renamed from: d */
    protected static final String[][] f589d = {new String[]{"<unknown>", "Go straight", "Turn gentle right", "Turn gentle left", "Turn right", "Turn left", "Turn sharp right", "Turn sharp left", "Make right U-turn", "Make left U-turn", "Start", "You have arrived", "Go up to floor %L", "Go down to floor %L", "Use transportation mode %M", "Change Buildings", "Stop"}, new String[]{"<inconnu>", "Continuez tout droit", "Tournez légèrement à droite", "Tournez légèrement à gauche", "Tournez à droite", "Tournez à gauche", "Tournez fortement à droite", "Tournez fortement à gauche", "Effectuez un demi-tour à droite", "Effectuez un demi-tour à gauche", "Départ", "Arrivée", "Montez à l'étage %L", "Descendez à l'étage %L", "Changez de moyen de transport: %M", "Changez de bâtiment", "Arrêtez vous"}, new String[]{"<غير معروف>", "اِتَّجِه نَحوَ الأَمَامِ", "انْعَطِفْ قَلِيلاً نَحوَ اليَمِين", "انْعَطِفْ قَلِيلاً نَحوَ اليَسَار", "انْعَطِفْ نَحوَ اليَمِين", "انْعَطِفْ نَحوَ اليَسَار", "انْعَطِفْ نَحوَ اليَمِين", "انْعَطِفْ نَحوَ اليَسَار", "اِستَدِرّ للْخَلْفِ مِن ناحيَةِ اليَمِين", "اِستَدِرّ للْخَلْفِ مِن ناحيَةِ اليَسَار", "البِدَايَة", "لَقَدْ وَصَلْتْ", "اِصْعَدْ إِلَى %L", "انْزِلْ إِلَى %L", "اِسْتَخْدِم وَسِيلَةَ التَنَقُلْ : %M", "غَيِّر المَبنَى", "تَوَقَف عِندَ النُقطَةِ رَقم"}, new String[]{"<unbekannt>", "geradeaus fahren", "Leicht rechts abbiegen", "Leicht links abbiegen", "Rechts abbiegen", "Links abbiegen", "Stark rechts abbiegen", "Stark links abbiegen", "Rechts umdrehen", "Links umdrehen", "Start", "Ziel", "Hinauf zum Stock %L", "Hinunter zum Stock %L", "Benutzen Transport: %M", "Gebäude wechseln", "Anhalten"}, new String[]{"<نامعلوم>", "سیدھا جائیے", "تھوڑا دائیں مڑیں", "تھوڑا بائیں مڑیں", "دائیں جانب مڑیں", "بائیں جانب مڑیں", "زیادہ دائیں جانب مڑیں", "زیادہ بائیں جانب مڑیں", "دائیں جانب یوٹرن لیں", "بائیں جانب یوٹرن لیں", "آغاز", "آپ پہنچ چکے ہیں", "اوپری منزل %L پر جائیں", "نچلی منزل %L پر جائیں", "نقل و حمل کا موڈ ٪M استعمال کریں", "عمارتوں کی تبدیلی", "روکنے"}};

    /* renamed from: e */
    protected static final String[][] f590e = {new String[]{"<unknown>", "go straight", "turn gentle right", "turn gentle left", "turn right", "turn left", "turn sharp right", "turn sharp left", "make right U-turn", "make left U-turn", "start", "you have arrived", "go up %L", "go down %L", "change transportation mode: %M", "change buildings", "stop"}, new String[]{"<inconnu>", "continuez tout droit", "tournez légèrement à droite", "tournez légèrement à gauche", "tournez à droite", "tournez à gauche", "tournez fortement à droite", "tournez fortement à gauche", "effectuez un demi-tour à droite", "effectuez un demi-tour à gauche", "départ", "vous serez arrivés", "montez", "descendez", "changez de mode de transport: %M", "changez de bâtiment", "arrêtez vous"}, new String[]{"<غير معروف>", "اِتَّجِه نَحوَ الأَمَامِ", "انْعَطِفْ قَلِيلاً نَحوَ اليَمِين", "انْعَطِفْ قَلِيلاً نَحوَ اليَسَار", "انْعَطِفْ نَحوَ اليَمِين", "انْعَطِفْ نَحوَ اليَسَار", "انْعَطِفْ نَحوَ اليَمِين", "انْعَطِفْ نَحوَ اليَسَار", "اِستَدِرّ للْخَلْفِ مِن ناحيَةِ اليَمِين", "اِستَدِرّ للْخَلْفِ مِن ناحيَةِ اليَسَار", "البِدَايَة", "تَصِلْ إِلَى وِجْهَتِكْْ", "اِصْعَدْ إِلَى %L", "انْزِلْ إِلَى %L", "اِسْتَخْدِم وَسِيلَةَ التَنَقُلْ : %M", "غَيِّر المَبنَى", "تَوَقَف عِندَ النُقطَةِ رَقم"}, new String[]{"<unbekannt>", "geradeaus fahren", "Leicht rechts abbiegen", "Leicht links abbiegen", "Rechts abbiegen", "Links abbiegen", "Stark rechts abbiegen", "Stark links abbiegen", "Rechts umdrehen", "Links umdrehen", "Start", "Sie sind angekommen", "Hinaufgehen", "Hinuntergehen", "Wechseln Transport: %M", "Gebäude wechseln", "Anhalten"}, new String[]{"<نامعلوم>", "سیدھا جائیے", "تھوڑا دائیں مڑیں", "تھوڑا بائیں مڑیں", "دائیں جانب مڑیں", "بائیں جانب مڑیں", "زیادہ دائیں جانب مڑیں", "زیادہ بائیں جانب مڑیں", "دائیں جانب یوٹرن لیں", "بائیں جانب یوٹرن لیں", "آغاز", "آپ پہنچ چکے ہیں", "اوپری منزل %L پر جائیں", "نچلی منزل %L پر جائیں", "نقل و حمل کا موڈ ٪M استعمال کریں", "عمارتوں کی تبدیلی", "روکنے"}};

    /* renamed from: f */
    protected static final String[][] f591f = {new String[]{"a few seconds", "about a minute", "about %d minutes"}, new String[]{"quelques secondes", "environ une minute", "environ %d minutes"}, new String[]{"بِضْعِ ثَوَانٍٍ", "لِمُدَّةِ دَقِيقَة", "لِمُدَّةِ %d دَقَائِق"}, new String[]{"einige Sekunden", "ungefähr eine Minute", "ungefähr %d Minuten"}, new String[]{"چند سیکنڈ", "تقريباً ایک منٹ", "تقريباً %d منٹ"}};

    /* renamed from: g */
    protected static String[][] f592g = {new String[]{" for ", " then ", " and ", " near ", " using ", "the stairway", "the escalator", "the lift"}, new String[]{" pendant ", " puis ", " et ", " à proximité de ", " en empruntant ", "les escalier", "l'escalator", "l'ascenseur"}, new String[]{" لِـ", " ثُمَّ ", " وَ ", " بالقُرْبِ مِن ", " بِسْتِخدام ", "الْسُلَّم", "الْسُلَّم المُتَحَرِّك", "المِصْعَد"}, new String[]{" während ", " dann ", " und ", " neben ", " mit ", "die treppe", "die rolltreppe", "der lift"}, new String[]{" ے ", " پھر ", " اور ", " قریب ", " استعمال کرتے ہوئے ", "سیڑھی", "چلتی سیڑھی", "لفٹ"}};

    /* renamed from: a */
    private VgSurfaceView f593a;

    /* renamed from: com.navibees.maps.h$a */
    /* compiled from: VgMyNavigationHelperN */
    enum C1447a {
        eStringFor,
        eStringThen,
        eStringAnd,
        eStringNear,
        eStringUsing,
        eStringStairway,
        eStringEscalator,
        eStringLift,
        eStringCount
    }

    /* renamed from: com.navibees.maps.h$b */
    /* compiled from: VgMyNavigationHelperN */
    static class C1448b {

        /* renamed from: a */
        String f604a;

        /* renamed from: b */
        String f605b;

        /* renamed from: c */
        String f606c;

        /* renamed from: d */
        float f607d;

        C1448b() {
        }
    }

    public C1446h(VgSurfaceView vgSurfaceView) {
        this.f593a = vgSurfaceView;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:115:0x04ae, code lost:
        if (r13 == com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown.swigValue()) goto L_0x04cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x04c7, code lost:
        if (r29.getHeight() != r12.getHeight()) goto L_0x04cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x05d9, code lost:
        if (r2 != com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown.swigValue()) goto L_0x05f1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0593  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x05ca  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m349a(com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r29, com.visioglobe.libVisioMove.VgINavigationConstRefPtr r30, com.navibees.maps.C1446h.C1448b r31, com.visioglobe.libVisioMove.VgIMapModule r32, int r33, android.app.Application r34) {
        /*
            r0 = r29
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            com.visioglobe.libVisioMove.VgManeuverType r5 = r29.getManeuverType()
            int r5 = r5.swigValue()
            com.visioglobe.libVisioMove.VgINavigationRequestParameters r6 = r30.getRequestParameters()
            com.visioglobe.libVisioMove.VgIRouteRefPtr r7 = r6.getMRoute()
            boolean r6 = r6.getMMergeFloorChangeInstructions()
            long r8 = r29.getIndex()
            r10 = 1
            long r12 = r8 + r10
            r14 = r30
            com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr r12 = r14.getInstruction(r12)
            com.visioglobe.libVisioMove.VgManeuverType r13 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeUnknown
            int r13 = r13.swigValue()
            boolean r15 = r12.isValid()
            if (r15 == 0) goto L_0x0040
            com.visioglobe.libVisioMove.VgManeuverType r13 = r12.getManeuverType()
            int r13 = r13.swigValue()
        L_0x0040:
            long r15 = r30.getNumInstructions()
            long r10 = r29.getDestinationIndex()
            int r11 = (int) r10
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeLayer
            int r10 = r10.swigValue()
            java.lang.String r14 = ""
            r19 = 1
            if (r5 != r10) goto L_0x0064
            java.lang.String[][] r7 = f589d
            r7 = r7[r3]
            r7 = r7[r5]
            r22 = r6
            r24 = r8
            r0 = r14
            r1 = r0
            r6 = 0
            goto L_0x0466
        L_0x0064:
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeEnd
            int r10 = r10.swigValue()
            java.lang.String r0 = ": "
            if (r5 != r10) goto L_0x009f
            java.lang.String[][] r10 = f589d
            r10 = r10[r3]
            r10 = r10[r5]
            java.lang.String r7 = m345a(r2, r7, r11, r4)
            if (r7 == 0) goto L_0x0095
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            r11.append(r0)
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r22 = r6
            r24 = r8
            r0 = r14
            r1 = r0
            r6 = 1
            goto L_0x0466
        L_0x0095:
            r22 = r6
            r24 = r8
            r7 = r10
            r0 = r14
            r1 = r0
            r6 = 0
            goto L_0x0466
        L_0x009f:
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeModality
            int r10 = r10.swigValue()
            if (r5 == r10) goto L_0x0459
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r10 = r10.swigValue()
            if (r5 == r10) goto L_0x0454
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r10 = r10.swigValue()
            if (r5 == r10) goto L_0x044f
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeStart
            int r10 = r10.swigValue()
            if (r5 != r10) goto L_0x00c5
            r22 = r6
            r24 = r8
            goto L_0x045d
        L_0x00c5:
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoStraight
            int r10 = r10.swigValue()
            r20 = 1114636288(0x42700000, float:60.0)
            if (r5 != r10) goto L_0x03c1
            java.lang.String[][] r10 = f589d
            r10 = r10[r3]
            com.visioglobe.libVisioMove.VgManeuverType r21 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoStraight
            int r21 = r21.swigValue()
            r10 = r10[r21]
            r21 = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String[][] r22 = f592g
            r22 = r22[r3]
            com.navibees.maps.h$a r23 = com.navibees.maps.C1446h.C1447a.eStringFor
            int r23 = r23.ordinal()
            r1 = r22[r23]
            r10.append(r1)
            float r1 = r29.getDuration()
            float r1 = r1 / r20
            java.lang.String r1 = m343a(r1, r3)
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            com.visioglobe.libVisioMove.VgINavigationRequestParameters r10 = r30.getRequestParameters()
            boolean r10 = r10.getMMergeFloorChangeInstructions()
            if (r10 == 0) goto L_0x02dc
            boolean r10 = r12.isValid()
            if (r10 != 0) goto L_0x015d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String[][] r20 = f592g
            r20 = r20[r3]
            com.navibees.maps.h$a r22 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r22 = r22.ordinal()
            r23 = r1
            r1 = r20[r22]
            r10.append(r1)
            java.lang.String[][] r1 = f590e
            r1 = r1[r3]
            com.visioglobe.libVisioMove.VgManeuverType r20 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeEnd
            int r20 = r20.swigValue()
            r1 = r1[r20]
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            java.lang.String r10 = m345a(r2, r7, r11, r4)
            if (r10 == 0) goto L_0x0158
            r22 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r1)
            r6.append(r0)
            r6.append(r10)
            java.lang.String r1 = r6.toString()
            r6 = 1
            goto L_0x026e
        L_0x0158:
            r22 = r6
            r6 = 0
            goto L_0x026e
        L_0x015d:
            r23 = r1
            r22 = r6
            java.lang.String r1 = r12.getLayer()
            java.lang.String r6 = r29.getLayer()
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0232
            float r1 = r29.getHeight()
            float r6 = r12.getHeight()
            int r10 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r10 <= 0) goto L_0x01a3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r10 = r10.ordinal()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r10 = r10.swigValue()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            goto L_0x01f6
        L_0x01a3:
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x01cf
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r10 = r10.ordinal()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r10 = r10.swigValue()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            goto L_0x01f6
        L_0x01cf:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r10 = r10.ordinal()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeLayer
            int r10 = r10.swigValue()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
        L_0x01f6:
            java.lang.String r6 = r12.getModality()
            java.lang.String r10 = r29.getModality()
            boolean r6 = r6.contentEquals(r10)
            if (r6 != 0) goto L_0x0230
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r1)
            java.lang.String[][] r1 = f592g
            r1 = r1[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringAnd
            int r10 = r10.ordinal()
            r1 = r1[r10]
            r6.append(r1)
            java.lang.String[][] r1 = f590e
            r1 = r1[r3]
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeModality
            int r10 = r10.swigValue()
            r1 = r1[r10]
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            r6 = 0
            goto L_0x026e
        L_0x0230:
            r6 = 0
            goto L_0x026e
        L_0x0232:
            java.lang.String r1 = r12.getModality()
            java.lang.String r6 = r29.getModality()
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x026c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r14)
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r10 = r10.ordinal()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            com.visioglobe.libVisioMove.VgManeuverType r10 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeModality
            int r10 = r10.swigValue()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r6 = 0
            goto L_0x026e
        L_0x026c:
            r1 = r14
            r6 = 0
        L_0x026e:
            boolean r10 = r12.isValid()
            if (r10 == 0) goto L_0x02d4
            long r24 = r12.getDestinationIndex()
            long r26 = r29.getDestinationIndex()
            int r10 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r10 == 0) goto L_0x02d4
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String[][] r1 = f592g
            r1 = r1[r3]
            com.navibees.maps.h$a r20 = com.navibees.maps.C1446h.C1447a.eStringAnd
            int r20 = r20.ordinal()
            r1 = r1[r20]
            r10.append(r1)
            java.lang.String[][] r1 = f590e
            r1 = r1[r3]
            com.visioglobe.libVisioMove.VgManeuverType r20 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeWaypoint
            int r20 = r20.swigValue()
            r1 = r1[r20]
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            java.lang.String r7 = m345a(r2, r7, r11, r4)
            if (r7 == 0) goto L_0x02cc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r1)
            r6.append(r0)
            r6.append(r7)
            java.lang.String r0 = r6.toString()
            r1 = r0
            r24 = r8
            r7 = r21
            r0 = r23
            r6 = 1
            goto L_0x0466
        L_0x02cc:
            r24 = r8
            r7 = r21
            r0 = r23
            goto L_0x0466
        L_0x02d4:
            r24 = r8
            r7 = r21
            r0 = r23
            goto L_0x0466
        L_0x02dc:
            r23 = r1
            r22 = r6
            r17 = 1
            long r24 = r15 - r17
            int r1 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r1 >= 0) goto L_0x03b7
            boolean r1 = r12.isValid()
            if (r1 == 0) goto L_0x03b4
            com.visioglobe.libVisioMove.VgManeuverType r1 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeEnd
            int r1 = r1.swigValue()
            if (r13 != r1) goto L_0x033c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r10 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r10 = r10.ordinal()
            r6 = r6[r10]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            r6 = r6[r13]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r24 = r8
            long r8 = r12.getDestinationIndex()
            int r6 = (int) r8
            java.lang.String r6 = m345a(r2, r7, r6, r4)
            if (r6 == 0) goto L_0x0339
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            r8.append(r0)
            r8.append(r6)
            java.lang.String r1 = r8.toString()
            r6 = r1
            r1 = 1
            goto L_0x0340
        L_0x0339:
            r6 = r1
            r1 = 0
            goto L_0x0340
        L_0x033c:
            r24 = r8
            r6 = r14
            r1 = 0
        L_0x0340:
            com.visioglobe.libVisioMove.VgManeuverType r8 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeChangeLayer
            int r8 = r8.swigValue()
            if (r13 == r8) goto L_0x0360
            com.visioglobe.libVisioMove.VgManeuverType r8 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r8 = r8.swigValue()
            if (r13 == r8) goto L_0x0360
            com.visioglobe.libVisioMove.VgManeuverType r8 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r8 = r8.swigValue()
            if (r13 == r8) goto L_0x0360
            com.visioglobe.libVisioMove.VgManeuverType r8 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeWaypoint
            int r8 = r8.swigValue()
            if (r13 != r8) goto L_0x0381
        L_0x0360:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String[][] r8 = f592g
            r8 = r8[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r9 = r9.ordinal()
            r8 = r8[r9]
            r6.append(r8)
            java.lang.String[][] r8 = f590e
            r8 = r8[r3]
            r8 = r8[r13]
            r6.append(r8)
            java.lang.String r6 = r6.toString()
        L_0x0381:
            com.visioglobe.libVisioMove.VgManeuverType r8 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeWaypoint
            int r8 = r8.swigValue()
            if (r13 != r8) goto L_0x03a9
            java.lang.String r7 = m345a(r2, r7, r11, r4)
            if (r7 == 0) goto L_0x03a9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = r1.toString()
            r1 = r0
            r7 = r21
            r0 = r23
            r6 = 1
            goto L_0x0466
        L_0x03a9:
            r7 = r21
            r0 = r23
            r28 = r6
            r6 = r1
            r1 = r28
            goto L_0x0466
        L_0x03b4:
            r24 = r8
            goto L_0x03b9
        L_0x03b7:
            r24 = r8
        L_0x03b9:
            r1 = r14
            r7 = r21
            r0 = r23
            r6 = 0
            goto L_0x0466
        L_0x03c1:
            r22 = r6
            r24 = r8
            com.visioglobe.libVisioMove.VgManeuverType r1 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeWaypoint
            int r1 = r1.swigValue()
            if (r5 != r1) goto L_0x03fb
            java.lang.String[][] r1 = f589d
            r1 = r1[r3]
            com.visioglobe.libVisioMove.VgManeuverType r6 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeWaypoint
            int r6 = r6.swigValue()
            r1 = r1[r6]
            java.lang.String r6 = m345a(r2, r7, r11, r4)
            if (r6 == 0) goto L_0x03f6
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r1)
            r7.append(r0)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            r0 = r14
            r1 = r0
            r6 = 1
            goto L_0x0466
        L_0x03f6:
            r7 = r1
            r0 = r14
            r1 = r0
            r6 = 0
            goto L_0x0466
        L_0x03fb:
            java.lang.String[][] r0 = f589d
            r0 = r0[r3]
            com.visioglobe.libVisioMove.VgManeuverType r1 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoStraight
            int r1 = r1.swigValue()
            r7 = r0[r1]
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String[][] r1 = f592g
            r1 = r1[r3]
            com.navibees.maps.h$a r6 = com.navibees.maps.C1446h.C1447a.eStringFor
            int r6 = r6.ordinal()
            r1 = r1[r6]
            r0.append(r1)
            float r1 = r29.getDuration()
            float r1 = r1 / r20
            java.lang.String r1 = m343a(r1, r3)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r8 = com.navibees.maps.C1446h.C1447a.eStringThen
            int r8 = r8.ordinal()
            r6 = r6[r8]
            r1.append(r6)
            java.lang.String[][] r6 = f590e
            r6 = r6[r3]
            r6 = r6[r5]
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r6 = 0
            goto L_0x0466
        L_0x044f:
            r22 = r6
            r24 = r8
            goto L_0x045d
        L_0x0454:
            r22 = r6
            r24 = r8
            goto L_0x045d
        L_0x0459:
            r22 = r6
            r24 = r8
        L_0x045d:
            java.lang.String[][] r0 = f589d
            r0 = r0[r3]
            r7 = r0[r5]
            r0 = r14
            r1 = r0
            r6 = 0
        L_0x0466:
            com.visioglobe.libVisioMove.VgNearPlaceVector r8 = r29.getNearPlaces()
            r9 = 2
            if (r22 == 0) goto L_0x0476
            r17 = 1
            long r20 = r15 - r17
            int r11 = (r24 > r20 ? 1 : (r24 == r20 ? 0 : -1))
            if (r11 >= 0) goto L_0x0481
        L_0x0476:
            if (r22 != 0) goto L_0x047f
            long r20 = r15 - r9
            int r11 = (r24 > r20 ? 1 : (r24 == r20 ? 0 : -1))
            if (r11 < 0) goto L_0x047f
            goto L_0x0481
        L_0x047f:
            r19 = r6
        L_0x0481:
            com.visioglobe.libVisioMove.VgManeuverType r6 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r6 = r6.swigValue()
            if (r5 == r6) goto L_0x04cc
            com.visioglobe.libVisioMove.VgManeuverType r6 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r6 = r6.swigValue()
            if (r5 == r6) goto L_0x04ca
            if (r22 != 0) goto L_0x04b1
            r9 = r24
            int r6 = (int) r9
            r11 = r5
            long r5 = (long) r6
            r20 = 2
            long r20 = r15 - r20
            int r23 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r23 >= 0) goto L_0x04b4
            com.visioglobe.libVisioMove.VgManeuverType r5 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r5 = r5.swigValue()
            if (r13 == r5) goto L_0x04cd
            com.visioglobe.libVisioMove.VgManeuverType r5 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r5 = r5.swigValue()
            if (r13 == r5) goto L_0x04cd
            goto L_0x04b4
        L_0x04b1:
            r11 = r5
            r9 = r24
        L_0x04b4:
            if (r22 == 0) goto L_0x0575
            r5 = 1
            long r15 = r15 - r5
            int r5 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r5 >= 0) goto L_0x0575
            float r5 = r29.getHeight()
            float r6 = r12.getHeight()
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0575
            goto L_0x04cd
        L_0x04ca:
            r11 = r5
            goto L_0x04cd
        L_0x04cc:
            r11 = r5
        L_0x04cd:
            com.visioglobe.libVisioMove.VgStringSet r5 = r29.getAttributes()
            r6 = 0
        L_0x04d2:
            long r9 = (long) r6
            long r15 = r5.size()
            int r13 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r13 >= 0) goto L_0x0575
            java.lang.String r9 = r5.get(r6)
            java.lang.String r10 = "stairway"
            boolean r10 = r9.equals(r10)
            if (r10 == 0) goto L_0x050f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringUsing
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringStairway
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            goto L_0x0576
        L_0x050f:
            java.lang.String r10 = "escalator"
            boolean r10 = r9.equals(r10)
            if (r10 == 0) goto L_0x0540
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringUsing
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringEscalator
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            goto L_0x0576
        L_0x0540:
            java.lang.String r10 = "lift"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0571
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringUsing
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String[][] r6 = f592g
            r6 = r6[r3]
            com.navibees.maps.h$a r9 = com.navibees.maps.C1446h.C1447a.eStringLift
            int r9 = r9.ordinal()
            r6 = r6[r9]
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            goto L_0x0576
        L_0x0571:
            int r6 = r6 + 1
            goto L_0x04d2
        L_0x0575:
            r5 = r14
        L_0x0576:
            if (r19 != 0) goto L_0x05ae
            if (r2 == 0) goto L_0x05ae
            long r9 = r8.size()
            r15 = 0
            int r6 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r6 <= 0) goto L_0x05ae
            r6 = 0
            com.visioglobe.libVisioMove.VgNearPlace r6 = r8.get(r6)
            java.lang.String r6 = r6.getMID()
            java.lang.String r2 = m346a(r2, r6, r4)
            if (r2 == 0) goto L_0x05ae
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String[][] r8 = f592g
            r3 = r8[r3]
            com.navibees.maps.h$a r8 = com.navibees.maps.C1446h.C1447a.eStringNear
            int r8 = r8.ordinal()
            r3 = r3[r8]
            r6.append(r3)
            r6.append(r2)
            java.lang.String r14 = r6.toString()
        L_0x05ae:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            r2.append(r0)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            r3 = r31
            r3.f605b = r2
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x05db
            com.visioglobe.libVisioMove.VgManeuverType r1 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoUp
            int r1 = r1.swigValue()
            r2 = r11
            if (r2 == r1) goto L_0x05db
            com.visioglobe.libVisioMove.VgManeuverType r1 = com.visioglobe.libVisioMove.VgManeuverType.eVgManeuverTypeGoDown
            int r1 = r1.swigValue()
            if (r2 != r1) goto L_0x05f1
        L_0x05db:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r3.f605b
            r1.append(r2)
            r1.append(r5)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            r3.f605b = r1
        L_0x05f1:
            java.lang.String r1 = r3.f605b
            r2 = r29
            java.lang.String r1 = m344a(r4, r1, r2, r12)
            r3.f605b = r1
            java.lang.String r1 = m344a(r4, r7, r2, r12)
            r3.f604a = r1
            java.lang.String r0 = m344a(r4, r0, r2, r12)
            r3.f606c = r0
            float r0 = r29.getDuration()
            r3.f607d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.maps.C1446h.m349a(com.visioglobe.libVisioMove.VgINavigationInstructionConstRefPtr, com.visioglobe.libVisioMove.VgINavigationConstRefPtr, com.navibees.maps.h$b, com.visioglobe.libVisioMove.VgIMapModule, int, android.app.Application):void");
    }

    public void release() {
        this.f593a = null;
    }

    /* renamed from: a */
    public static String m348a(String str, Application application) {
        if (!str.isEmpty()) {
            SimpleArrayMap buildings = NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings();
            for (int i = 0; i < buildings.size(); i++) {
                Iterator it = ((Building) buildings.valueAt(i)).floors.iterator();
                while (it.hasNext()) {
                    VisioFloor visioFloor = (VisioFloor) it.next();
                    if (visioFloor.vgfloorId.equals(str)) {
                        return visioFloor.name.getText();
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m346a(VgIMapModule vgIMapModule, String str, Application application) {
        String[] strArr = new String[1];
        if (vgIMapModule.getPlaceName(str, strArr)) {
            String str2 = strArr[0];
            if (!str2.isEmpty()) {
                VisioPOI visioPOI = NaviBeesUtils.getVisioPOI(application, str2);
                if (visioPOI != null && visioPOI.name != null) {
                    return visioPOI.name.getText();
                }
                SimpleArrayMap buildings = NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings();
                for (int i = 0; i < buildings.size(); i++) {
                    List<Floor> list = ((Building) buildings.valueAt(i)).floors;
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            VisioFloor visioFloor = (VisioFloor) it.next();
                            if (visioFloor.vgfloorId.equals(str2)) {
                                return visioFloor.name.getText();
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m347a(VgIRouteRefPtr vgIRouteRefPtr, int i) {
        VgIRoutingNodeRefPtrVector mDestinations = vgIRouteRefPtr.getRequestParameters().getMDestinations();
        if (i >= 0 && ((long) i) < mDestinations.size()) {
            VgIRoutingNodeRefPtr vgIRoutingNodeRefPtr = mDestinations.get(i);
            if (vgIRoutingNodeRefPtr.isValid() && vgIRoutingNodeRefPtr.hasPoiID()) {
                return vgIRoutingNodeRefPtr.getPoiID();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m345a(VgIMapModule vgIMapModule, VgIRouteRefPtr vgIRouteRefPtr, int i, Application application) {
        String a = m347a(vgIRouteRefPtr, i);
        if (a != null) {
            return m346a(vgIMapModule, a, application);
        }
        return null;
    }

    /* renamed from: a */
    protected static String m344a(Application application, String str, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr, VgINavigationInstructionConstRefPtr vgINavigationInstructionConstRefPtr2) {
        String str2 = "\"";
        if (vgINavigationInstructionConstRefPtr.isValid()) {
            String str3 = "%m";
            String replace = str.replace("%d", String.valueOf((int) (vgINavigationInstructionConstRefPtr.getDuration() / 60.0f))).replace(str3, vgINavigationInstructionConstRefPtr.getModality());
            String a = m348a(vgINavigationInstructionConstRefPtr.getLayer(), application);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(a);
            sb.append(str2);
            str = replace.replace("%l", sb.toString());
        }
        if (!vgINavigationInstructionConstRefPtr2.isValid()) {
            return str;
        }
        String replace2 = str.replace("%M", vgINavigationInstructionConstRefPtr2.getModality());
        String a2 = m348a(vgINavigationInstructionConstRefPtr2.getLayer(), application);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(a2);
        sb2.append(str2);
        return replace2.replace("%L", sb2.toString());
    }

    /* renamed from: a */
    static int m342a(String str) {
        if (str.length() < 2) {
            return 0;
        }
        String substring = str.substring(0, 2);
        for (int i = 0; i < f588c; i++) {
            if (f587b[i].equalsIgnoreCase(substring)) {
                return i;
            }
        }
        return 0;
    }

    /* renamed from: a */
    static String m343a(float f, int i) {
        if (i >= f588c) {
            i = 0;
        }
        double d = (double) f;
        if (d < 1.0d) {
            return f591f[i][0];
        }
        if (d < 2.0d) {
            return f591f[i][1];
        }
        return f591f[i][2];
    }

    /* renamed from: a */
    public void mo28465a(VgINavigationRequestParameters vgINavigationRequestParameters) {
        VgIModuleManager editModuleManager = this.f593a.getApplication().editModuleManager();
        if (editModuleManager != null) {
            VgIModule queryModule = editModuleManager.queryModule("Navigation");
            if (queryModule != null) {
                VgINavigationModule castToINavigationModule = C1732libVisioMove.castToINavigationModule(queryModule);
                vgINavigationRequestParameters.setMMergeFloorChangeInstructions(true);
                castToINavigationModule.computeNavigation(vgINavigationRequestParameters);
            }
        }
    }
}
