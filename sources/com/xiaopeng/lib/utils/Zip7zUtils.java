package com.xiaopeng.lib.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
/* loaded from: classes2.dex */
public class Zip7zUtils {
    public static void zip7z(String str, String str2) {
        File file;
        SevenZOutputFile sevenZOutputFile;
        SevenZOutputFile sevenZOutputFile2 = null;
        try {
            try {
                File file2 = new File(str2);
                file = new File(str);
                sevenZOutputFile = new SevenZOutputFile(file2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            if (file.isDirectory()) {
                zip7zDirectory(file, sevenZOutputFile, "");
            } else {
                zip7zFile(file, sevenZOutputFile, "");
            }
            FileUtils.closeQuietly(sevenZOutputFile);
        } catch (IOException e2) {
            e = e2;
            sevenZOutputFile2 = sevenZOutputFile;
            System.out.println(e.toString());
            FileUtils.closeQuietly(sevenZOutputFile2);
        } catch (Throwable th2) {
            th = th2;
            sevenZOutputFile2 = sevenZOutputFile;
            FileUtils.closeQuietly(sevenZOutputFile2);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void zip7zFile(File file, SevenZOutputFile sevenZOutputFile, String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = bufferedInputStream2;
            }
        } catch (FileNotFoundException e) {
            e = e;
        } catch (IOException e2) {
            e = e2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(file.getName());
            sevenZOutputFile.putArchiveEntry(sevenZOutputFile.createArchiveEntry(file, sb.toString()));
            byte[] bArr = new byte[4096];
            BufferedInputStream bufferedInputStream3 = sb;
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                bufferedInputStream3 = null;
                sevenZOutputFile.write(bArr, 0, read);
            }
            FileUtils.closeQuietly(bufferedInputStream);
            bufferedInputStream2 = bufferedInputStream3;
            if (sevenZOutputFile == null) {
                return;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            bufferedInputStream2 = bufferedInputStream;
            e.printStackTrace();
            FileUtils.closeQuietly(bufferedInputStream2);
            bufferedInputStream2 = bufferedInputStream2;
            if (sevenZOutputFile == null) {
                return;
            }
            sevenZOutputFile.closeArchiveEntry();
        } catch (IOException e4) {
            e = e4;
            bufferedInputStream2 = bufferedInputStream;
            e.printStackTrace();
            FileUtils.closeQuietly(bufferedInputStream2);
            bufferedInputStream2 = bufferedInputStream2;
            if (sevenZOutputFile == null) {
                return;
            }
            sevenZOutputFile.closeArchiveEntry();
        } catch (Throwable th2) {
            th = th2;
            FileUtils.closeQuietly(bufferedInputStream);
            if (sevenZOutputFile != null) {
                sevenZOutputFile.closeArchiveEntry();
            }
            throw th;
        }
        sevenZOutputFile.closeArchiveEntry();
    }

    private static void zip7zDirectory(File file, SevenZOutputFile sevenZOutputFile, String str) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                zip7zDirectory(file2, sevenZOutputFile, str + file2.getName() + "/");
            } else {
                zip7zFile(file2, sevenZOutputFile, str);
            }
        }
    }
}
