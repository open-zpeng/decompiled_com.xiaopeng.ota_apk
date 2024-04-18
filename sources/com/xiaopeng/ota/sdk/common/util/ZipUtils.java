package com.xiaopeng.ota.sdk.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/* loaded from: classes2.dex */
public class ZipUtils {
    private static final String TAG = "ZipUtils";

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r2 <= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
        r5.write(r1, 0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
        r0.closeEntry();
        r4 = r5.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        r5 = new java.io.ByteArrayOutputStream(2097152);
        r1 = new byte[4096];
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
        r2 = r0.read(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] getEntryFromZip(java.io.File r4, java.lang.String r5) throws java.io.IOException {
        /*
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r4)
            r0.<init>(r1)
        La:
            r4 = 0
            java.util.zip.ZipEntry r1 = r0.getNextEntry()     // Catch: java.lang.Throwable -> L5c
            if (r1 == 0) goto L40
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L5c
            boolean r1 = r1.equalsIgnoreCase(r5)     // Catch: java.lang.Throwable -> L5c
            if (r1 == 0) goto L3c
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L5c
            r1 = 2097152(0x200000, float:2.938736E-39)
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5c
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L5c
        L26:
            int r2 = r0.read(r1)     // Catch: java.lang.Throwable -> L5c
            if (r2 <= 0) goto L31
            r3 = 0
            r5.write(r1, r3, r2)     // Catch: java.lang.Throwable -> L5c
            goto L26
        L31:
            r0.closeEntry()     // Catch: java.lang.Throwable -> L5c
            byte[] r4 = r5.toByteArray()     // Catch: java.lang.Throwable -> L5c
            r0.close()
            return r4
        L3c:
            r0.closeEntry()     // Catch: java.lang.Throwable -> L5c
            goto La
        L40:
            r0.close()
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = " Not Found"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            throw r4
        L5a:
            r5 = move-exception
            goto L5e
        L5c:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L5a
        L5e:
            if (r4 == 0) goto L69
            r0.close()     // Catch: java.lang.Throwable -> L64
            goto L6c
        L64:
            r0 = move-exception
            r4.addSuppressed(r0)
            goto L6c
        L69:
            r0.close()
        L6c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.common.util.ZipUtils.getEntryFromZip(java.io.File, java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File getFileFromZip(java.io.File r6, java.lang.String r7) {
        /*
            r0 = 0
            byte[] r1 = getEntryFromZip(r6, r7)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.String r2 = com.xiaopeng.ota.sdk.common.util.ZipUtils.TAG     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r3.<init>()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.String r4 = "getFileFromZip:"
            r3.append(r4)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r4 = 0
            if (r1 != 0) goto L16
            r5 = r4
            goto L17
        L16:
            int r5 = r1.length     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
        L17:
            r3.append(r5)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            android.util.Log.d(r2, r3)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            if (r1 == 0) goto L66
            int r2 = r1.length     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            if (r2 != 0) goto L27
            goto L66
        L27:
            java.lang.String r6 = r6.getAbsolutePath()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r2.<init>()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            int r3 = r6.lastIndexOf(r3)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            int r3 = r3 + 1
            java.lang.String r6 = r6.substring(r4, r3)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r2.append(r6)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r2.append(r7)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            r2.<init>(r7)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            r2.write(r1)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            r2.close()     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            java.io.File r1 = new java.io.File     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            r1.<init>(r6)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L79
            r7.close()     // Catch: java.lang.Exception -> L5f
            goto L63
        L5f:
            r6 = move-exception
            r6.printStackTrace()
        L63:
            return r1
        L64:
            r6 = move-exception
            goto L6b
        L66:
            return r0
        L67:
            r6 = move-exception
            goto L7b
        L69:
            r6 = move-exception
            r7 = r0
        L6b:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L78
            r7.close()     // Catch: java.lang.Exception -> L74
            goto L78
        L74:
            r6 = move-exception
            r6.printStackTrace()
        L78:
            return r0
        L79:
            r6 = move-exception
            r0 = r7
        L7b:
            if (r0 == 0) goto L85
            r0.close()     // Catch: java.lang.Exception -> L81
            goto L85
        L81:
            r7 = move-exception
            r7.printStackTrace()
        L85:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.ota.sdk.common.util.ZipUtils.getFileFromZip(java.io.File, java.lang.String):java.io.File");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.Closeable, java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.io.BufferedInputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.io.Closeable, java.io.BufferedOutputStream] */
    public static void zipFile(String str, String str2) throws Exception {
        ZipOutputStream zipOutputStream;
        ?? r1;
        ZipOutputStream zipOutputStream2;
        ZipOutputStream zipOutputStream3;
        ZipOutputStream zipOutputStream4;
        ZipOutputStream zipOutputStream5;
        ZipOutputStream zipOutputStream6;
        ZipOutputStream zipOutputStream7;
        ZipOutputStream zipOutputStream8 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(str2)));
            try {
                r1 = new FileInputStream(str);
            } catch (Exception e) {
                e = e;
                r1 = null;
                zipOutputStream5 = null;
            } catch (Throwable th) {
                th = th;
                r1 = null;
                zipOutputStream4 = r1;
                IoUtils.close(zipOutputStream8);
                IoUtils.close(zipOutputStream4);
                IoUtils.close(zipOutputStream);
                IoUtils.close((Closeable) r1);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            zipOutputStream2 = null;
            r1 = null;
            zipOutputStream3 = null;
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
            r1 = null;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(new File(str).getName()));
            ?? bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
            try {
                ?? bufferedInputStream = new BufferedInputStream(r1);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 1024);
                        if (read != -1) {
                            bufferedOutputStream.write(bArr, 0, read);
                        } else {
                            bufferedOutputStream.flush();
                            zipOutputStream.flush();
                            IoUtils.close((Closeable) bufferedOutputStream);
                            IoUtils.close((Closeable) bufferedInputStream);
                            IoUtils.close(zipOutputStream);
                            IoUtils.close((Closeable) r1);
                            return;
                        }
                    }
                } catch (Exception e3) {
                    zipOutputStream6 = zipOutputStream;
                    zipOutputStream2 = bufferedOutputStream;
                    e = e3;
                    zipOutputStream7 = bufferedInputStream;
                    zipOutputStream8 = zipOutputStream6;
                    zipOutputStream3 = zipOutputStream7;
                    try {
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        ZipOutputStream zipOutputStream9 = zipOutputStream8;
                        zipOutputStream8 = zipOutputStream2;
                        zipOutputStream = zipOutputStream9;
                        zipOutputStream4 = zipOutputStream3;
                        IoUtils.close(zipOutputStream8);
                        IoUtils.close(zipOutputStream4);
                        IoUtils.close(zipOutputStream);
                        IoUtils.close((Closeable) r1);
                        throw th;
                    }
                } catch (Throwable th4) {
                    zipOutputStream8 = bufferedOutputStream;
                    th = th4;
                    zipOutputStream4 = bufferedInputStream;
                    IoUtils.close(zipOutputStream8);
                    IoUtils.close(zipOutputStream4);
                    IoUtils.close(zipOutputStream);
                    IoUtils.close((Closeable) r1);
                    throw th;
                }
            } catch (Exception e4) {
                zipOutputStream6 = zipOutputStream;
                zipOutputStream2 = bufferedOutputStream;
                e = e4;
                zipOutputStream7 = null;
            } catch (Throwable th5) {
                zipOutputStream8 = bufferedOutputStream;
                th = th5;
                zipOutputStream4 = null;
            }
        } catch (Exception e5) {
            e = e5;
            zipOutputStream5 = null;
            zipOutputStream8 = zipOutputStream;
            zipOutputStream2 = zipOutputStream5;
            zipOutputStream3 = zipOutputStream5;
            throw e;
        } catch (Throwable th6) {
            th = th6;
            zipOutputStream4 = null;
        }
    }

    public static void unzip(File file, String str) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
        try {
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                File file2 = new File(str, nextEntry.getName());
                if (nextEntry.isDirectory()) {
                    file2.mkdirs();
                } else {
                    File parentFile = file2.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                    byte[] bArr = new byte[Math.toIntExact(nextEntry.getSize())];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.close();
                }
            }
            zipInputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    zipInputStream.close();
                }
                throw th2;
            }
        }
    }
}
