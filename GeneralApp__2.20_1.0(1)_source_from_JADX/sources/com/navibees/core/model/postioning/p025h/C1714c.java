package com.navibees.core.model.postioning.p025h;

import java.lang.reflect.Array;

/* renamed from: com.navibees.core.model.postioning.h.c */
/* compiled from: Matrix */
public class C1714c {

    /* renamed from: d */
    static final /* synthetic */ boolean f1552d = (!C1714c.class.desiredAssertionStatus());

    /* renamed from: a */
    int f1553a;

    /* renamed from: b */
    int f1554b;

    /* renamed from: c */
    public double[][] f1555c;

    public C1714c(int i, int i2) {
        this.f1553a = i;
        this.f1554b = i2;
        this.f1555c = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
    }

    /* renamed from: c */
    public static void m1126c(C1714c cVar, C1714c cVar2, C1714c cVar3) {
        if (!f1552d && cVar.f1554b != cVar2.f1553a) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1553a != cVar3.f1553a) {
            throw new AssertionError();
        } else if (f1552d || cVar2.f1554b == cVar3.f1554b) {
            for (int i = 0; i < cVar3.f1553a; i++) {
                for (int i2 = 0; i2 < cVar3.f1554b; i2++) {
                    cVar3.f1555c[i][i2] = 0.0d;
                    for (int i3 = 0; i3 < cVar.f1554b; i3++) {
                        double[] dArr = cVar3.f1555c[i];
                        dArr[i2] = dArr[i2] + (cVar.f1555c[i][i3] * cVar2.f1555c[i3][i2]);
                    }
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: d */
    static void m1127d(C1714c cVar, C1714c cVar2, C1714c cVar3) {
        if (!f1552d && cVar.f1553a != cVar2.f1553a) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1553a != cVar3.f1553a) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1554b != cVar2.f1554b) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1554b == cVar3.f1554b) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    cVar3.f1555c[i][i2] = cVar.f1555c[i][i2] - cVar2.f1555c[i][i2];
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public void mo29389a(double... dArr) {
        if (f1552d || dArr.length == this.f1553a * this.f1554b) {
            for (int i = 0; i < this.f1553a; i++) {
                int i2 = 0;
                while (true) {
                    int i3 = this.f1554b;
                    if (i2 >= i3) {
                        break;
                    }
                    this.f1555c[i][i2] = dArr[(i3 * i) + i2];
                    i2++;
                }
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo29390b() {
        if (f1552d || this.f1553a == this.f1554b) {
            for (int i = 0; i < this.f1553a; i++) {
                for (int i2 = 0; i2 < this.f1554b; i2++) {
                    if (i == i2) {
                        this.f1555c[i][i2] = 1.0d;
                    } else {
                        this.f1555c[i][i2] = 0.0d;
                    }
                }
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static void m1120a(C1714c cVar, C1714c cVar2) {
        if (!f1552d && cVar.f1553a != cVar2.f1553a) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1554b == cVar2.f1554b) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    cVar2.f1555c[i][i2] = cVar.f1555c[i][i2];
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    static void m1123b(C1714c cVar, C1714c cVar2, C1714c cVar3) {
        if (!f1552d && cVar.f1554b != cVar2.f1554b) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1553a != cVar3.f1553a) {
            throw new AssertionError();
        } else if (f1552d || cVar2.f1553a == cVar3.f1554b) {
            for (int i = 0; i < cVar3.f1553a; i++) {
                for (int i2 = 0; i2 < cVar3.f1554b; i2++) {
                    cVar3.f1555c[i][i2] = 0.0d;
                    for (int i3 = 0; i3 < cVar.f1554b; i3++) {
                        double[] dArr = cVar3.f1555c[i];
                        dArr[i2] = dArr[i2] + (cVar.f1555c[i][i3] * cVar2.f1555c[i2][i3]);
                    }
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29384a() {
        for (int i = 0; i < this.f1553a; i++) {
            for (int i2 = 0; i2 < this.f1554b; i2++) {
                if (i2 > 0) {
                    System.out.print(" ");
                }
                System.out.format("%6.2f", new Object[]{Double.valueOf(this.f1555c[i][i2])});
            }
            System.out.print("\n");
        }
    }

    /* renamed from: c */
    static void m1125c(C1714c cVar, C1714c cVar2) {
        if (!f1552d && cVar.f1553a != cVar2.f1554b) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1554b == cVar2.f1553a) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    cVar2.f1555c[i2][i] = cVar.f1555c[i][i2];
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    static void m1121a(C1714c cVar, C1714c cVar2, C1714c cVar3) {
        if (!f1552d && cVar.f1553a != cVar2.f1553a) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1553a != cVar3.f1553a) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1554b != cVar2.f1554b) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1554b == cVar3.f1554b) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    cVar3.f1555c[i][i2] = cVar.f1555c[i][i2] + cVar2.f1555c[i][i2];
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    static boolean m1124b(C1714c cVar, C1714c cVar2) {
        if (!f1552d && cVar.f1553a != cVar.f1554b) {
            throw new AssertionError();
        } else if (!f1552d && cVar.f1553a != cVar2.f1553a) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1553a == cVar2.f1554b) {
            cVar2.mo29390b();
            int i = 0;
            while (i < cVar.f1553a) {
                if (cVar.f1555c[i][i] == 0.0d) {
                    int i2 = i + 1;
                    while (i2 < cVar.f1553a && cVar.f1555c[i2][i] == 0.0d) {
                        i2++;
                    }
                    if (i2 == cVar.f1553a) {
                        return false;
                    }
                    cVar.mo29387a(i, i2);
                    cVar2.mo29387a(i, i2);
                }
                double d = 1.0d / cVar.f1555c[i][i];
                cVar.mo29386a(i, d);
                cVar2.mo29386a(i, d);
                for (int i3 = 0; i3 < cVar.f1553a; i3++) {
                    if (i != i3) {
                        double d2 = -cVar.f1555c[i3][i];
                        cVar.mo29388a(i3, i, d2);
                        cVar2.mo29388a(i3, i, d2);
                    }
                }
                i++;
            }
            return true;
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    static void m1119a(C1714c cVar) {
        if (f1552d || cVar.f1553a == cVar.f1554b) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    if (i == i2) {
                        double[][] dArr = cVar.f1555c;
                        dArr[i][i2] = 1.0d - dArr[i][i2];
                    } else {
                        double[][] dArr2 = cVar.f1555c;
                        dArr2[i][i2] = 0.0d - dArr2[i][i2];
                    }
                }
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    static boolean m1122a(C1714c cVar, C1714c cVar2, double d) {
        if (!f1552d && cVar.f1553a != cVar2.f1553a) {
            throw new AssertionError();
        } else if (f1552d || cVar.f1554b == cVar2.f1554b) {
            for (int i = 0; i < cVar.f1553a; i++) {
                for (int i2 = 0; i2 < cVar.f1554b; i2++) {
                    if (Math.abs(cVar.f1555c[i][i2] - cVar2.f1555c[i][i2]) > d) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29385a(double d) {
        if (f1552d || d != 0.0d) {
            for (int i = 0; i < this.f1553a; i++) {
                for (int i2 = 0; i2 < this.f1554b; i2++) {
                    double[] dArr = this.f1555c[i];
                    dArr[i2] = dArr[i2] * d;
                }
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29387a(int i, int i2) {
        if (f1552d || i != i2) {
            double[][] dArr = this.f1555c;
            double[] dArr2 = dArr[i];
            dArr[i] = dArr[i2];
            dArr[i2] = dArr2;
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29386a(int i, double d) {
        if (f1552d || d != 0.0d) {
            for (int i2 = 0; i2 < this.f1554b; i2++) {
                double[] dArr = this.f1555c[i];
                dArr[i2] = dArr[i2] * d;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29388a(int i, int i2, double d) {
        if (f1552d || i != i2) {
            for (int i3 = 0; i3 < this.f1554b; i3++) {
                double[][] dArr = this.f1555c;
                double[] dArr2 = dArr[i];
                dArr2[i3] = dArr2[i3] + (dArr[i2][i3] * d);
            }
            return;
        }
        throw new AssertionError();
    }
}
