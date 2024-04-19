package com.xiaopeng.ota.sdk.common.util;

import android.os.StatFs;
import android.text.TextUtils;
import com.xiaopeng.ota.sdk.common.OTAConstants;
import com.xiaopeng.ota.sdk.exception.NoSpaceLeftException;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public class FileUtils {
    private static DecimalFormat DF = new DecimalFormat("#.00");
    public static final String DOT = ".";
    private static final String GB = "GB";
    private static final long GB_VALUE = 1073741824;
    private static final String KB = "KB";
    private static final long KB_VALUE = 1024;
    private static final String MB = "MB";
    private static final long MB_VALUE = 1048576;
    private static final String MODE_READ_FILE = "r";

    public static byte[] readFile(File file) throws IOException {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] byteArray = toByteArray(fileInputStream);
                IoUtils.close(fileInputStream);
                return byteArray;
            } catch (IOException e) {
                throw e;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                IoUtils.close(fileInputStream2);
                throw th;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } else {
                            byteArrayOutputStream2.flush();
                            IoUtils.close(byteArrayOutputStream2);
                            return byteArrayOutputStream2.toByteArray();
                        }
                    } catch (IOException e) {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        IoUtils.close(byteArrayOutputStream);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                throw e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean copyFile(String str, String str2) throws IOException {
        return copyFile(new File(str), new File(str2));
    }

    public static boolean copyFile(File file, File file2) throws IOException {
        return copyFile(file, file2, false);
    }

    public static boolean copyFileAndSync(File file, File file2) throws IOException {
        return copyFile(file, file2, true);
    }

    private static boolean copyFile(File file, File file2, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            boolean z2 = false;
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        z2 = true;
                        if (z) {
                            fileOutputStream.getFD().sync();
                        }
                        fileInputStream2 = fileInputStream;
                    } catch (IOException e) {
                        e = e;
                        fileInputStream2 = fileInputStream;
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            IoUtils.close(fileOutputStream);
                            IoUtils.close(fileInputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.close(fileOutputStream);
                        IoUtils.close(fileInputStream);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } else {
                fileOutputStream = null;
            }
            IoUtils.close(fileOutputStream);
            IoUtils.close(fileInputStream2);
            return z2;
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.io.ByteArrayOutputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v8 */
    public static byte[] readFileSkipHeader(int i, File file) throws IOException {
        RandomAccessFile randomAccessFile;
        ?? r4;
        byte[] bArr = new byte[4096];
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, MODE_READ_FILE);
            try {
                randomAccessFile.seek(i);
                r4 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = randomAccessFile.read(bArr);
                        if (read != -1) {
                            r4.write(bArr, 0, read);
                        } else {
                            r4.flush();
                            byte[] byteArray = r4.toByteArray();
                            IoUtils.close(randomAccessFile);
                            IoUtils.close((Closeable) r4);
                            return byteArray;
                        }
                    } catch (IOException e) {
                        e = e;
                        randomAccessFile2 = randomAccessFile;
                        r4 = r4;
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            randomAccessFile2 = r4;
                            IoUtils.close(randomAccessFile);
                            IoUtils.close(randomAccessFile2);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = r4;
                        IoUtils.close(randomAccessFile);
                        IoUtils.close(randomAccessFile2);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                r4 = 0;
            } catch (Throwable th3) {
                th = th3;
                IoUtils.close(randomAccessFile);
                IoUtils.close(randomAccessFile2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            r4 = 0;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = null;
        }
    }

    public static boolean delete(String str) {
        return delete(new File(str));
    }

    public static boolean delete(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!delete(new File(file, str))) {
                    return false;
                }
            }
            return file.delete();
        }
        return file.delete();
    }

    public static boolean deleteFile(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean mkdirsIfNotExist(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    public static String getFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str).getName();
    }

    public static void save(String str, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists() || !file.isFile()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                IoUtils.close(fileOutputStream);
            } catch (IOException e) {
            } catch (Throwable th2) {
                fileOutputStream2 = fileOutputStream;
                th = th2;
                IoUtils.close(fileOutputStream2);
                throw th;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    public static long getAvailableSize(String str) {
        StatFs statFs = new StatFs(OTAConstants.INSTALL_DIRECTORY);
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static String getSpaceDescription(long j) {
        if (j > 1073741824) {
            return DF.format((j * 1.0d) / 1.073741824E9d) + GB;
        } else if (j > 1048576) {
            return DF.format((j * 1.0d) / 1048576.0d) + MB;
        } else if (j > 1024) {
            return DF.format((j * 1.0d) / 1024.0d) + KB;
        } else {
            return j + "";
        }
    }

    public static void checkSpaceAvailable(long j, String str) throws NoSpaceLeftException {
        String substring = str.substring(0, str.lastIndexOf(File.separator) + 1);
        long availableSize = getAvailableSize(substring);
        if (availableSize > j) {
            return;
        }
        throw new NoSpaceLeftException("No space left on device, path:" + substring + "\nfree space: " + getSpaceDescription(availableSize) + ", package size: " + getSpaceDescription(j));
    }

    public static String getFileSuffix(String str) {
        int lastIndexOf;
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(".")) > -1) {
            return str.substring(lastIndexOf + 1);
        }
        return null;
    }

    public static String getName(String str) {
        int lastIndexOf;
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(".")) > -1) {
            return str.substring(0, lastIndexOf);
        }
        return null;
    }

    public static long getLastModified(File file) {
        if (file.exists()) {
            return file.lastModified();
        }
        return -1L;
    }

    public static boolean lastModifyMoreThenTime(File file, long j) {
        return System.currentTimeMillis() - j > getLastModified(file);
    }

    public static void deleteOldFile(String str, long j) {
        File[] listFiles;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.isFile() || !file.exists()) {
            return;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile() && lastModifyMoreThenTime(file2, j)) {
                file2.delete();
            }
        }
    }
}
