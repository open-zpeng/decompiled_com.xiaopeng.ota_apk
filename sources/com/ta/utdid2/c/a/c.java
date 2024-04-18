package com.ta.utdid2.c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ta.utdid2.b.a.i;
import com.ta.utdid2.c.a.b;
import java.io.File;
import java.util.Map;
/* compiled from: PersistentConfiguration.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f146a;

    /* renamed from: a  reason: collision with other field name */
    private b f148a;

    /* renamed from: a  reason: collision with other field name */
    private d f149a;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Context mContext;
    private SharedPreferences.Editor a = null;

    /* renamed from: a  reason: collision with other field name */
    private b.a f147a = null;

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017b A[Catch: Exception -> 0x0187, TRY_LEAVE, TryCatch #2 {Exception -> 0x0187, blocks: (B:78:0x0177, B:80:0x017b), top: B:91:0x0177 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c(android.content.Context r10, java.lang.String r11, java.lang.String r12, boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.c.a.c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    private d a(String str) {
        File m76a = m76a(str);
        if (m76a != null) {
            this.f149a = new d(m76a.getAbsolutePath());
            return this.f149a;
        }
        return null;
    }

    /* renamed from: a  reason: collision with other method in class */
    private File m76a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        b.a a;
        if (sharedPreferences == null || bVar == null || (a = bVar.a()) == null) {
            return;
        }
        a.b();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                a.a(key, (String) value);
            } else if (value instanceof Integer) {
                a.a(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                a.a(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                a.a(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                a.a(key, ((Boolean) value).booleanValue());
            }
        }
        a.commit();
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (bVar == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.clear();
        for (Map.Entry<String, ?> entry : bVar.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                edit.putString(key, (String) value);
            } else if (value instanceof Integer) {
                edit.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                edit.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                edit.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                edit.putBoolean(key, ((Boolean) value).booleanValue());
            }
        }
        edit.commit();
    }

    private boolean b() {
        b bVar = this.f148a;
        if (bVar != null) {
            boolean mo75a = bVar.mo75a();
            if (!mo75a) {
                commit();
            }
            return mo75a;
        }
        return false;
    }

    private void c() {
        b bVar;
        SharedPreferences sharedPreferences;
        if (this.a == null && (sharedPreferences = this.f146a) != null) {
            this.a = sharedPreferences.edit();
        }
        if (this.i && this.f147a == null && (bVar = this.f148a) != null) {
            this.f147a = bVar.a();
        }
        b();
    }

    public void putString(String str, String str2) {
        if (i.m74a(str) || str.equals("t")) {
            return;
        }
        c();
        SharedPreferences.Editor editor = this.a;
        if (editor != null) {
            editor.putString(str, str2);
        }
        b.a aVar = this.f147a;
        if (aVar != null) {
            aVar.a(str, str2);
        }
    }

    public void remove(String str) {
        if (i.m74a(str) || str.equals("t")) {
            return;
        }
        c();
        SharedPreferences.Editor editor = this.a;
        if (editor != null) {
            editor.remove(str);
        }
        b.a aVar = this.f147a;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(4:3|(1:7)|8|(9:10|11|(1:15)|16|17|18|19|(4:21|(2:23|(2:25|(3:27|(1:29)(1:31)|30))(2:32|(1:36)))|37|(3:43|44|(1:46)))|49))|54|11|(2:13|15)|16|17|18|19|(0)|49) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean commit() {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r6.a
            r3 = 0
            if (r2 == 0) goto L21
            boolean r4 = r6.j
            if (r4 != 0) goto L17
            android.content.SharedPreferences r4 = r6.f146a
            if (r4 == 0) goto L17
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L17:
            android.content.SharedPreferences$Editor r0 = r6.a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L21
            r0 = r3
            goto L22
        L21:
            r0 = 1
        L22:
            android.content.SharedPreferences r1 = r6.f146a
            if (r1 == 0) goto L32
            android.content.Context r1 = r6.mContext
            if (r1 == 0) goto L32
            java.lang.String r2 = r6.e
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r6.f146a = r1
        L32:
            r1 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L38
            goto L3c
        L38:
            r2 = move-exception
            r2.printStackTrace()
        L3c:
            boolean r2 = com.ta.utdid2.b.a.i.m74a(r1)
            if (r2 != 0) goto La5
            java.lang.String r2 = "mounted"
            boolean r4 = r1.equals(r2)
            if (r4 == 0) goto L85
            com.ta.utdid2.c.a.b r4 = r6.f148a
            if (r4 != 0) goto L7a
            java.lang.String r4 = r6.f
            com.ta.utdid2.c.a.d r4 = r6.a(r4)
            if (r4 == 0) goto L85
            java.lang.String r5 = r6.e
            com.ta.utdid2.c.a.b r4 = r4.a(r5, r3)
            r6.f148a = r4
            boolean r4 = r6.j
            if (r4 != 0) goto L6a
            android.content.SharedPreferences r4 = r6.f146a
            com.ta.utdid2.c.a.b r5 = r6.f148a
            r6.a(r4, r5)
            goto L71
        L6a:
            com.ta.utdid2.c.a.b r4 = r6.f148a
            android.content.SharedPreferences r5 = r6.f146a
            r6.a(r4, r5)
        L71:
            com.ta.utdid2.c.a.b r4 = r6.f148a
            com.ta.utdid2.c.a.b$a r4 = r4.a()
            r6.f147a = r4
            goto L85
        L7a:
            com.ta.utdid2.c.a.b$a r4 = r6.f147a
            if (r4 == 0) goto L85
            boolean r4 = r4.commit()
            if (r4 != 0) goto L85
            r0 = r3
        L85:
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L97
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto La5
            com.ta.utdid2.c.a.b r1 = r6.f148a
            if (r1 == 0) goto La5
        L97:
            com.ta.utdid2.c.a.d r1 = r6.f149a     // Catch: java.lang.Exception -> La5
            if (r1 == 0) goto La5
            com.ta.utdid2.c.a.d r1 = r6.f149a     // Catch: java.lang.Exception -> La5
            java.lang.String r2 = r6.e     // Catch: java.lang.Exception -> La5
            com.ta.utdid2.c.a.b r1 = r1.a(r2, r3)     // Catch: java.lang.Exception -> La5
            r6.f148a = r1     // Catch: java.lang.Exception -> La5
        La5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.c.a.c.commit():boolean");
    }

    public String getString(String str) {
        b();
        SharedPreferences sharedPreferences = this.f146a;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!i.m74a(string)) {
                return string;
            }
        }
        b bVar = this.f148a;
        return bVar != null ? bVar.getString(str, "") : "";
    }
}
