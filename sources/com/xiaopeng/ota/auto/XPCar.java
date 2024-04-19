package com.xiaopeng.ota.auto;

import android.car.Car;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaopeng.ota.auto.service.ICarConnect;
import com.xiaopeng.ota.sdk.common.log.Logger;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes2.dex */
public class XPCar {
    public static final String CAR_TYPE_D55 = "D55";
    private static final String TAG = "XPCar";
    private Car mCar;
    private CopyOnWriteArraySet<ICarConnect> mCarConnectSet = new CopyOnWriteArraySet<>();
    private boolean mConnected;
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XPCar(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void register(ICarConnect iCarConnect) {
        if (iCarConnect == null) {
            throw new NullPointerException();
        }
        synchronized (this.mCarConnectSet) {
            if (!this.mCarConnectSet.contains(iCarConnect)) {
                this.mCarConnectSet.add(iCarConnect);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregister(ICarConnect iCarConnect) {
        if (iCarConnect == null) {
            throw new NullPointerException();
        }
        synchronized (this.mCarConnectSet) {
            if (this.mCarConnectSet.contains(iCarConnect)) {
                this.mCarConnectSet.remove(iCarConnect);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void connect() {
        char c;
        final String carVersion = CarApi.getCarVersion();
        switch (carVersion.hashCode()) {
            case 66946:
                if (carVersion.equals("D20")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 66947:
                if (carVersion.equals("D21")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 66948:
                if (carVersion.equals("D22")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 67044:
                if (carVersion.equals("D55")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 67915:
                if (carVersion.equals("E28")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 67946:
                if (carVersion.equals(Car.CAR_TYPE_E38)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            Logger.d(TAG, "Not support", new Object[0]);
        } else if (c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
            this.mCar = Car.createCar(this.mContext, new ServiceConnection() { // from class: com.xiaopeng.ota.auto.XPCar.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Logger.i(XPCar.TAG, "%s Car connected!", carVersion);
                    XPCar.this.onCarConnected();
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    Logger.e(XPCar.TAG, "%s Car disConnected!", carVersion);
                    XPCar.this.onCarDisConnected();
                }
            });
            this.mCar.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnect() {
        Car car = this.mCar;
        if (car != null) {
            car.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCarConnected() {
        this.mConnected = true;
        CopyOnWriteArraySet<ICarConnect> copyOnWriteArraySet = this.mCarConnectSet;
        if (copyOnWriteArraySet != null) {
            Iterator<ICarConnect> it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                it.next().onConnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCarDisConnected() {
        this.mConnected = false;
        CopyOnWriteArraySet<ICarConnect> copyOnWriteArraySet = this.mCarConnectSet;
        if (copyOnWriteArraySet != null) {
            Iterator<ICarConnect> it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                it.next().onDisconnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Car getCar() {
        return this.mCar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnected() {
        return this.mConnected;
    }
}
