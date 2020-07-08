package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzjf {
    static String zzd(zzfh zzfh) {
        zzji zzji = new zzji(zzfh);
        StringBuilder sb = new StringBuilder(zzji.size());
        for (int i = 0; i < zzji.size(); i++) {
            byte zzao = zzji.zzao(i);
            if (zzao == 34) {
                sb.append("\\\"");
            } else if (zzao == 39) {
                sb.append("\\'");
            } else if (zzao != 92) {
                switch (zzao) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (zzao >= 32 && zzao <= 126) {
                            sb.append((char) zzao);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zzao >>> 6) & 3) + 48));
                            sb.append((char) (((zzao >>> 3) & 7) + 48));
                            sb.append((char) ((zzao & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
