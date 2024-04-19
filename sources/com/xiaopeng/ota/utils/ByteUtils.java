package com.xiaopeng.ota.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes2.dex */
public class ByteUtils {
    public static int byte2Int(byte b) {
        return b & 255;
    }

    public static int short2Int(short s) {
        return s & 65535;
    }

    public static short bytes2ShortLE(byte[] bArr) {
        return bytes2Short(bArr, ByteOrder.LITTLE_ENDIAN);
    }

    public static short bytes2ShortBE(byte[] bArr) {
        return bytes2Short(bArr, ByteOrder.BIG_ENDIAN);
    }

    public static short bytes2Short(byte[] bArr, ByteOrder byteOrder) {
        if (bArr != null && bArr.length == 2) {
            return ByteBuffer.wrap(bArr).order(byteOrder).getShort();
        }
        throw new IllegalArgumentException("bytes length != 2");
    }

    public static byte[] short2BytesLE(short s) {
        return short2Bytes(s, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] short2BytesBE(short s) {
        return short2Bytes(s, ByteOrder.BIG_ENDIAN);
    }

    public static byte[] short2Bytes(short s, ByteOrder byteOrder) {
        ByteBuffer order = ByteBuffer.allocate(2).order(byteOrder);
        order.putShort(s);
        return order.array();
    }

    public static int bytes2IntLE(byte[] bArr) {
        return bytes2Int(bArr, ByteOrder.LITTLE_ENDIAN);
    }

    public static int bytes2IntBE(byte[] bArr) {
        return bytes2Int(bArr, ByteOrder.BIG_ENDIAN);
    }

    public static int bytes2Int(byte[] bArr, ByteOrder byteOrder) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (bArr.length == 4) {
            return ByteBuffer.wrap(bArr).order(byteOrder).getInt();
        }
        if (bArr.length == 2) {
            return short2Int(bytes2Short(bArr, byteOrder));
        }
        if (bArr.length == 1) {
            return byte2Int(bArr[0]);
        }
        throw new IllegalArgumentException("bytes length invalid:" + bArr.length);
    }

    public static byte[] int2BytesLE(int i) {
        return int2Bytes(i, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] int2BytesBE(int i) {
        return int2Bytes(i, ByteOrder.BIG_ENDIAN);
    }

    public static byte[] int2Bytes(int i, ByteOrder byteOrder) {
        ByteBuffer order = ByteBuffer.allocate(4).order(byteOrder);
        order.putInt(i);
        return order.array();
    }

    public static byte[] long2BytesBE(long j) {
        return long2Bytes(j, ByteOrder.BIG_ENDIAN);
    }

    public static byte[] long2BytesLE(long j) {
        return long2Bytes(j, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] long2Bytes(long j, ByteOrder byteOrder) {
        ByteBuffer order = ByteBuffer.allocate(8).order(byteOrder);
        order.putLong(j);
        return order.array();
    }

    public static String readString(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        int i2 = 0;
        while (i2 < i && bArr[i2] != 0) {
            i2++;
        }
        return new String(bArr, 0, i2);
    }
}
