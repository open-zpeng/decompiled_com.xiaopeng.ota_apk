package com.xiaopeng.lib.apirouter.server;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.ota.speech.SpeechEventObserver;
/* loaded from: classes2.dex */
public class SpeechEventObserver_Stub extends Binder implements IInterface {
    public SpeechEventObserver provider = new SpeechEventObserver();
    public SpeechEventObserver_Manifest manifest = new SpeechEventObserver_Manifest();

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 0) {
            parcel.enforceInterface(SpeechEventObserver_Manifest.DESCRIPTOR);
            Uri uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
            try {
                this.provider.onEvent((String) TransactTranslator.read(uri.getQueryParameter("event"), "java.lang.String"), (String) TransactTranslator.read(uri.getQueryParameter("data"), "java.lang.String"));
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, null);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                parcel2.writeException(new IllegalStateException(e.getMessage()));
                return true;
            }
        } else if (i != 1) {
            if (i == 1598968902) {
                parcel2.writeString(SpeechEventObserver_Manifest.DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel.enforceInterface(SpeechEventObserver_Manifest.DESCRIPTOR);
            Uri uri2 = (Uri) Uri.CREATOR.createFromParcel(parcel);
            try {
                this.provider.onQuery((String) TransactTranslator.read(uri2.getQueryParameter("event"), "java.lang.String"), (String) TransactTranslator.read(uri2.getQueryParameter("data"), "java.lang.String"), (String) TransactTranslator.read(uri2.getQueryParameter("callback"), "java.lang.String"));
                parcel2.writeNoException();
                TransactTranslator.reply(parcel2, null);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                parcel2.writeException(new IllegalStateException(e2.getMessage()));
                return true;
            }
        }
    }
}
