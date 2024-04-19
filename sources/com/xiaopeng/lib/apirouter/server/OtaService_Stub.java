package com.xiaopeng.lib.apirouter.server;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.RemoteException;
import com.xiaopeng.ota.system.OtaService;
/* loaded from: classes2.dex */
public class OtaService_Stub extends Binder implements IInterface {
    public OtaService provider = new OtaService();
    public OtaService_Manifest manifest = new OtaService_Manifest();

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 0) {
            parcel.enforceInterface(OtaService_Manifest.DESCRIPTOR);
            Uri uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
            try {
                Integer valueOf = Integer.valueOf(this.provider.getOTAState());
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, valueOf);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                parcel2.writeException(new IllegalStateException(e.getMessage()));
                return true;
            }
        } else if (i == 1) {
            parcel.enforceInterface(OtaService_Manifest.DESCRIPTOR);
            Uri uri2 = (Uri) Uri.CREATOR.createFromParcel(parcel);
            try {
                Boolean valueOf2 = Boolean.valueOf(this.provider.existNewVersion());
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, valueOf2);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                parcel2.writeException(new IllegalStateException(e2.getMessage()));
                return true;
            }
        } else if (i == 2) {
            parcel.enforceInterface(OtaService_Manifest.DESCRIPTOR);
            Uri uri3 = (Uri) Uri.CREATOR.createFromParcel(parcel);
            try {
                String vcuMode = this.provider.getVcuMode();
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, vcuMode);
                return true;
            } catch (Exception e3) {
                e3.printStackTrace();
                parcel2.writeException(new IllegalStateException(e3.getMessage()));
                return true;
            }
        } else if (i == 3) {
            parcel.enforceInterface(OtaService_Manifest.DESCRIPTOR);
            try {
                String vcuMode2 = this.provider.setVcuMode(((Integer) TransactTranslator.read(((Uri) Uri.CREATOR.createFromParcel(parcel)).getQueryParameter(PowerManager.EXTRA_POWER_SAVE_MODE), "int")).intValue());
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, vcuMode2);
                return true;
            } catch (Exception e4) {
                e4.printStackTrace();
                parcel2.writeException(new IllegalStateException(e4.getMessage()));
                return true;
            }
        } else if (i != 4) {
            if (i == 1598968902) {
                parcel2.writeString(OtaService_Manifest.DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel.enforceInterface(OtaService_Manifest.DESCRIPTOR);
            try {
                String readDid = this.provider.readDid((String) TransactTranslator.read(((Uri) Uri.CREATOR.createFromParcel(parcel)).getQueryParameter("requestJsonContent"), "java.lang.String"));
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, readDid);
                return true;
            } catch (Exception e5) {
                e5.printStackTrace();
                parcel2.writeException(new IllegalStateException(e5.getMessage()));
                return true;
            }
        }
    }
}
