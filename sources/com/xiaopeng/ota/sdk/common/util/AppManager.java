package com.xiaopeng.ota.sdk.common.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import java.io.File;
import java.io.IOException;
import org.apache.commons.compress.utils.IOUtils;
/* loaded from: classes2.dex */
public class AppManager {
    public static final String ACTION_PACKAGE_INSTALL = "com.xiaopeng.ota.broadcast.ACTION_PACKAGE_INSTALL";
    private static final int BUFFER_LENGTH = 65536;
    public static final String EXTRA_PACKAGE_NAME = "packageName";

    public void install(Context context, String str, String str2, Class<?> cls) throws Exception {
        File file = new File(str2);
        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        sessionParams.setSize(file.length());
        int createSession = packageInstaller.createSession(sessionParams);
        if (createSession != -1) {
            addApkToInstallSession(packageInstaller, createSession, file);
            commitInstallation(context, str, packageInstaller, createSession, cls);
            return;
        }
        throw new RuntimeException("Create package installer session failed");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void addApkToInstallSession(android.content.pm.PackageInstaller r10, int r11, java.io.File r12) throws java.io.IOException {
        /*
            r9 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5a
            r1.<init>(r12)     // Catch: java.lang.Throwable -> L5a
            android.content.pm.PackageInstaller$Session r10 = r10.openSession(r11)     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = "package"
            r4 = 0
            long r6 = r12.length()     // Catch: java.lang.Throwable -> L3a
            r2 = r10
            java.io.OutputStream r11 = r2.openWrite(r3, r4, r6)     // Catch: java.lang.Throwable -> L3a
            r12 = 65536(0x10000, float:9.18355E-41)
            byte[] r12 = new byte[r12]     // Catch: java.lang.Throwable -> L34
        L1b:
            int r2 = r1.read(r12)     // Catch: java.lang.Throwable -> L34
            r3 = -1
            if (r2 == r3) goto L27
            r3 = 0
            r11.write(r12, r3, r2)     // Catch: java.lang.Throwable -> L34
            goto L1b
        L27:
            r10.fsync(r11)     // Catch: java.lang.Throwable -> L34
            r1.close()     // Catch: java.lang.Throwable -> L58
            org.apache.commons.compress.utils.IOUtils.closeQuietly(r11)
            org.apache.commons.compress.utils.IOUtils.closeQuietly(r10)
            return
        L34:
            r12 = move-exception
            r0 = r12
            goto L47
        L37:
            r12 = move-exception
            r11 = r0
            goto L49
        L3a:
            r11 = move-exception
            r8 = r0
            r0 = r11
            r11 = r8
            goto L47
        L3f:
            r12 = move-exception
            r10 = r0
            r11 = r10
            goto L49
        L43:
            r10 = move-exception
            r11 = r0
            r0 = r10
            r10 = r11
        L47:
            throw r0     // Catch: java.lang.Throwable -> L48
        L48:
            r12 = move-exception
        L49:
            if (r0 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L57
        L4f:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch: java.lang.Throwable -> L58
            goto L57
        L54:
            r1.close()     // Catch: java.lang.Throwable -> L58
        L57:
            throw r12     // Catch: java.lang.Throwable -> L58
        L58:
            r12 = move-exception
            goto L5d
        L5a:
            r12 = move-exception
            r10 = r0
            r11 = r10
        L5d:
            org.apache.commons.compress.utils.IOUtils.closeQuietly(r11)
            org.apache.commons.compress.utils.IOUtils.closeQuietly(r10)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.common.util.AppManager.addApkToInstallSession(android.content.pm.PackageInstaller, int, java.io.File):void");
    }

    private void commitInstallation(Context context, String str, PackageInstaller packageInstaller, int i, Class<?> cls) throws IOException {
        PackageInstaller.Session session;
        try {
            session = packageInstaller.openSession(i);
            try {
                Intent intent = new Intent(context, cls);
                intent.setAction(ACTION_PACKAGE_INSTALL);
                intent.putExtra("packageName", str);
                session.commit(PendingIntent.getBroadcast(context, 1, intent, 134217728).getIntentSender());
                IOUtils.closeQuietly(session);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(session);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            session = null;
        }
    }
}
