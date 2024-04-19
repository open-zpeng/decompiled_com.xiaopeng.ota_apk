package com.xiaopeng.xuimanager.iot;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.iot.devices.ScanDevice;
import com.xiaopeng.xuimanager.iot.internal.IoTCommunication;
import com.xiaopeng.xuimanager.iot.utils.DeviceBuilder;
import com.xiaopeng.xuimanager.pipebus.IPipeBus;
import com.xiaopeng.xuimanager.pipebus.IPipeBusListener;
import com.xiaopeng.xuimanager.utils.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class IoTManager implements XUIManagerBase {
    private static final int MAX_RETRY_CNT = 150;
    private static final int MSG_CONNECT_IOTSVC = 1;
    private static final String SERVICE_NAME = "IoTManagerService";
    private static final String TAG = IoTManager.class.getSimpleName() + "##";
    private static volatile IoTManager mIoTManager;
    private int connectRetryCount;
    private List<IDeviceListener> mAppListenerList;
    private IPipeBusListener mBusLisener;
    private WeakReference<Context> mContextRef;
    private IBinder.DeathRecipient mDeathRecipient;
    private IPipeBus mPipeBusClient;
    private List<Map<String, String>> mSubscriberList;
    private Handler mUiHandler;

    static /* synthetic */ int access$308(IoTManager ioTManager) {
        int i = ioTManager.connectRetryCount;
        ioTManager.connectRetryCount = i + 1;
        return i;
    }

    private IoTManager(IBinder iBinder, Context context, Handler handler) {
        LogUtil.setModuleLogLevel(TAG, 0);
        LogUtil.log(1, TAG, "IoTManager created");
    }

    private IoTManager() {
        LogUtil.setModuleLogLevel(TAG, 0);
        LogUtil.log(1, TAG, "IoTManager created");
        this.connectRetryCount = 0;
        this.mUiHandler = new Handler(Looper.getMainLooper()) { // from class: com.xiaopeng.xuimanager.iot.IoTManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str = IoTManager.TAG;
                LogUtil.log(1, str, "get msg=" + message.what);
                if (message.what != 1) {
                    return;
                }
                IoTManager.this.selfGetService();
                if (IoTManager.this.mPipeBusClient == null) {
                    if (IoTManager.this.connectRetryCount < 150) {
                        IoTManager.access$308(IoTManager.this);
                        IoTManager.this.mUiHandler.sendEmptyMessageDelayed(1, 200L);
                        return;
                    }
                    return;
                }
                IoTManager.this.connectRetryCount = 0;
                IoTManager.this.selfSubscribeNotifications();
            }
        };
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.xiaopeng.xuimanager.iot.IoTManager.2
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                LogUtil.log(3, IoTManager.TAG, "IoTManager Service died");
                IoTManager.this.mPipeBusClient = null;
                IoTManager.this.connectRetryCount = 0;
                IoTManager.this.mUiHandler.sendEmptyMessageDelayed(1, 200L);
            }
        };
        selfGetService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IPipeBus selfGetService() {
        IBinder service;
        IPipeBusListener iPipeBusListener;
        if (this.mPipeBusClient == null && (service = ServiceManager.getService(SERVICE_NAME)) != null) {
            try {
                service.linkToDeath(this.mDeathRecipient, 0);
            } catch (RemoteException e) {
                String str = TAG;
                LogUtil.log(4, str, "linkToDeath exception=" + e.getMessage());
            }
            this.mPipeBusClient = IPipeBus.Stub.asInterface(service);
            List<IDeviceListener> list = this.mAppListenerList;
            if (list != null && !list.isEmpty() && (iPipeBusListener = this.mBusLisener) != null) {
                try {
                    this.mPipeBusClient.registerListener(iPipeBusListener);
                } catch (RemoteException e2) {
                    String str2 = TAG;
                    LogUtil.log(4, str2, "auto registerListener exception=" + e2.getMessage());
                }
            }
        }
        return this.mPipeBusClient;
    }

    /* loaded from: classes2.dex */
    private static class IoTManagerHolder {
        private static final IoTManager sInstance = new IoTManager();

        private IoTManagerHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processEvent(String str, String[] strArr) {
        List<IDeviceListener> list = this.mAppListenerList;
        if (list == null) {
            LogUtil.log(3, TAG, "no AppListenerList");
        } else if (list != null && list.isEmpty()) {
            LogUtil.log(3, TAG, "no AppListener register");
        } else {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -333836685) {
                if (hashCode != 781495096) {
                    if (hashCode == 1532124661 && str.equals(IoTCommunication.EVENT_OPERATION_RESULT)) {
                        c = 2;
                    }
                } else if (str.equals(IoTCommunication.EVENT_DEVICE_ADD)) {
                    c = 0;
                }
            } else if (str.equals(IoTCommunication.EVENT_PROPERTY_UPDATE)) {
                c = 1;
            }
            if (c == 0) {
                List<BaseDevice> fromJsonArray = DeviceBuilder.fromJsonArray(strArr[0]);
                if (fromJsonArray == null) {
                    LogUtil.log(3, TAG, "EVENT_DEVICE_ADD but get null");
                    return;
                }
                for (IDeviceListener iDeviceListener : this.mAppListenerList) {
                    iDeviceListener.onDeviceAdd(fromJsonArray);
                }
            } else if (c == 1) {
                if (strArr == null || strArr.length <= 2) {
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(strArr[1], strArr[2]);
                for (IDeviceListener iDeviceListener2 : this.mAppListenerList) {
                    iDeviceListener2.onPropertiesUpdated(strArr[0], hashMap);
                }
            } else if (c == 2 && strArr != null && strArr.length > 2) {
                for (IDeviceListener iDeviceListener3 : this.mAppListenerList) {
                    iDeviceListener3.onOperationResult(strArr[0], strArr[1], strArr[2]);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private class BusListener extends IPipeBusListener.Stub {
        private final String TAG;

        private BusListener() {
            this.TAG = IoTManager.TAG + BusListener.class.getSimpleName();
        }

        @Override // com.xiaopeng.xuimanager.pipebus.IPipeBusListener
        public void onPipeBusEvent(String str, String str2, String[] strArr) {
            String str3 = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPipeBusEvent,module=");
            sb.append(str);
            sb.append(",types=");
            sb.append(str2);
            sb.append(",events[0]=");
            sb.append(strArr != null ? Integer.valueOf(strArr[0].hashCode()) : "null");
            LogUtil.log(1, str3, sb.toString());
            IoTManager.this.processEvent(str2, strArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selfSubscribeNotifications() {
        List<Map<String, String>> list = this.mSubscriberList;
        if (list == null || list.isEmpty()) {
            return;
        }
        String str = TAG;
        LogUtil.log(1, str, "selfSubscribeNotifications,suber size=" + this.mSubscriberList.size());
        if (this.mPipeBusClient != null) {
            String[] strArr = new String[2];
            for (Map<String, String> map : this.mSubscriberList) {
                strArr[0] = map.get(DeviceBuilder.FIELD_DEVICE_TYPE);
                strArr[1] = map.get(DeviceBuilder.FIELD_DEVICE_ID);
                try {
                    this.mPipeBusClient.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_ADD_MONITOR_DEVICE, strArr);
                } catch (Exception e) {
                    String str2 = TAG;
                    LogUtil.log(4, str2, "subscribeNotifications fail,e=" + e.getMessage());
                }
            }
        }
    }

    public static IoTManager getInstance() {
        return IoTManagerHolder.sInstance;
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        LogUtil.log(1, TAG, "onXUIDisconnected");
    }

    public void init(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    public synchronized void reset() {
        LogUtil.log(1, TAG, "reset");
        this.mUiHandler.removeMessages(1);
        if (this.mContextRef != null) {
            this.mContextRef.clear();
        }
        if (this.mAppListenerList != null && !this.mAppListenerList.isEmpty()) {
            this.mAppListenerList.clear();
            selfGetService();
            if (this.mPipeBusClient != null) {
                try {
                    this.mPipeBusClient.unRegisterListener(this.mBusLisener);
                    this.mBusLisener = null;
                } catch (RemoteException e) {
                    String str = TAG;
                    LogUtil.log(4, str, "reset exception=" + e.getMessage());
                }
            }
            if (this.mAppListenerList != null) {
                this.mAppListenerList.clear();
                this.mAppListenerList = null;
            }
        }
        if (this.mSubscriberList != null && !this.mSubscriberList.isEmpty()) {
            this.mSubscriberList.clear();
            this.mSubscriberList = null;
        }
    }

    public List<BaseDevice> getDevice(String str, String str2) {
        String str3 = TAG;
        LogUtil.log(1, str3, "getDevice,type=" + str + ",params=" + str2);
        selfGetService();
        IPipeBus iPipeBus = this.mPipeBusClient;
        if (iPipeBus != null) {
            try {
                String[] strArr = {str, str2};
                String[] strArr2 = new String[1];
                iPipeBus.ioControlWithPocket(IoTCommunication.MODULE_IOT, IoTCommunication.IO_GET_DEVICE, strArr, strArr2);
                if (strArr2.length > 0) {
                    String str4 = TAG;
                    LogUtil.log(1, str4, "getDevice0=" + strArr2[0] + ",size=" + strArr2.length);
                    if (strArr2[0] != null) {
                        return DeviceBuilder.fromJsonArray(strArr2[0]);
                    }
                    return null;
                }
                return null;
            } catch (Exception e) {
                String str5 = TAG;
                LogUtil.log(4, str5, "getDevice fail,e=" + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public void requestDeviceList(String str) {
        String str2 = TAG;
        LogUtil.log(1, str2, "requestDeviceList,filter=" + str);
        selfGetService();
        IPipeBus iPipeBus = this.mPipeBusClient;
        if (iPipeBus != null) {
            try {
                iPipeBus.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_REQUEST_DEVICE, new String[]{str});
            } catch (Exception e) {
                String str3 = TAG;
                LogUtil.log(4, str3, "ioControl fail,e=" + e.getMessage());
            }
        }
    }

    public synchronized void registerListener(IDeviceListener iDeviceListener) {
        if (this.mAppListenerList == null) {
            this.mAppListenerList = new ArrayList();
        }
        if (this.mAppListenerList.isEmpty()) {
            if (this.mBusLisener == null) {
                this.mBusLisener = new BusListener();
            }
            selfGetService();
            if (this.mPipeBusClient != null) {
                try {
                    this.mPipeBusClient.registerListener(this.mBusLisener);
                } catch (RemoteException e) {
                    String str = TAG;
                    LogUtil.log(4, str, "registerListener exception=" + e.getMessage());
                }
            } else {
                this.connectRetryCount = 0;
                this.mUiHandler.sendEmptyMessageDelayed(1, 200L);
            }
        }
        if (!this.mAppListenerList.contains(iDeviceListener)) {
            this.mAppListenerList.add(iDeviceListener);
        }
    }

    public synchronized void unRegisterListener(IDeviceListener iDeviceListener) {
        if (this.mAppListenerList != null) {
            this.mAppListenerList.remove(iDeviceListener);
            if (this.mAppListenerList.isEmpty()) {
                selfGetService();
                if (this.mPipeBusClient != null) {
                    try {
                        this.mPipeBusClient.unRegisterListener(this.mBusLisener);
                        this.mBusLisener = null;
                    } catch (RemoteException e) {
                        String str = TAG;
                        LogUtil.log(4, str, "unRegisterListener exception=" + e.getMessage());
                    }
                }
            }
        }
    }

    public Map<String, String> getDeviceProperties(BaseDevice baseDevice) {
        String str = TAG;
        LogUtil.log(1, str, "getDeviceProperties,type=" + baseDevice.getDeviceType() + ",device id=" + baseDevice.getDeviceId());
        selfGetService();
        if (this.mPipeBusClient != null) {
            try {
                String[] strArr = {BaseDevice.GET_BY_DEVICE_TYPE, baseDevice.getDeviceType(), baseDevice.getDeviceId()};
                String[] strArr2 = new String[1];
                this.mPipeBusClient.ioControlWithPocket(IoTCommunication.MODULE_IOT, IoTCommunication.IO_GET_PROPERTIES, strArr, strArr2);
                if (strArr2.length > 0) {
                    String str2 = TAG;
                    LogUtil.log(1, str2, "getDeviceProperties0=" + strArr2[0] + ",size=" + strArr2.length);
                    if (strArr2[0] != null) {
                        return DeviceBuilder.jsonStrToPropMap(strArr2[0]);
                    }
                    return null;
                }
                return null;
            } catch (Exception e) {
                String str3 = TAG;
                LogUtil.log(4, str3, "getDeviceProperties fail,e=" + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public void setDeviceProperties(BaseDevice baseDevice, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.w(TAG, "setDeviceProperties,invalid propMap");
            return;
        }
        String str = TAG;
        LogUtil.log(1, str, "setDeviceProperties,type=" + map + "type=" + baseDevice.getDeviceType() + ",device id=" + baseDevice.getDeviceId());
        selfGetService();
        if (this.mPipeBusClient != null) {
            try {
                this.mPipeBusClient.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_SET_PROPERTIES, new String[]{baseDevice.getDeviceType(), baseDevice.getDeviceId(), DeviceBuilder.propToJson(map).toString()});
            } catch (RemoteException e) {
                String str2 = TAG;
                LogUtil.log(4, str2, "setDeviceProperties fail,e=" + e.getMessage());
            }
        }
    }

    public synchronized void subscribeNotifications(BaseDevice baseDevice) {
        String str = TAG;
        LogUtil.log(1, str, "subscribeNotifications,type=" + baseDevice.getDeviceType() + ",name=" + baseDevice.getDeviceName());
        String[] strArr = {baseDevice.getDeviceType(), baseDevice.getDeviceId()};
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceBuilder.FIELD_DEVICE_TYPE, strArr[0]);
        hashMap.put(DeviceBuilder.FIELD_DEVICE_ID, strArr[1]);
        if (this.mSubscriberList == null) {
            this.mSubscriberList = new ArrayList();
        }
        if (!this.mSubscriberList.isEmpty()) {
            for (Map<String, String> map : this.mSubscriberList) {
                if (baseDevice.getDeviceId().equals(map.get(DeviceBuilder.FIELD_DEVICE_ID))) {
                    String str2 = TAG;
                    LogUtil.log(3, str2, "ignore repeat subscribe device,class=" + baseDevice.getDeviceClass() + ",id=" + baseDevice.getDeviceId());
                    return;
                }
            }
        }
        this.mSubscriberList.add(hashMap);
        selfGetService();
        if (this.mPipeBusClient != null) {
            try {
                this.mPipeBusClient.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_ADD_MONITOR_DEVICE, strArr);
            } catch (RemoteException e) {
                String str3 = TAG;
                LogUtil.log(4, str3, "subscribeNotifications fail,e=" + e.getMessage());
            }
        } else {
            this.connectRetryCount = 0;
            this.mUiHandler.sendEmptyMessageDelayed(1, 200L);
        }
    }

    public synchronized void unSubscribeNotifications(BaseDevice baseDevice) {
        String str = TAG;
        LogUtil.log(1, str, "unSubscribeNotifications,type=" + baseDevice.getDeviceType() + ",name=" + baseDevice.getDeviceName());
        String[] strArr = {baseDevice.getDeviceType(), baseDevice.getDeviceId()};
        if (this.mSubscriberList != null && !this.mSubscriberList.isEmpty()) {
            Iterator<Map<String, String>> it = this.mSubscriberList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map<String, String> next = it.next();
                if (strArr[1].equals(next.get(DeviceBuilder.FIELD_DEVICE_ID))) {
                    this.mSubscriberList.remove(next);
                    break;
                }
            }
            selfGetService();
            if (this.mPipeBusClient != null) {
                try {
                    this.mPipeBusClient.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_REMOVE_MONITOR_DEVICE, strArr);
                } catch (RemoteException e) {
                    String str2 = TAG;
                    LogUtil.log(4, str2, "unSubscribeNotifications fail,e=" + e.getMessage());
                }
            }
            return;
        }
        String str3 = TAG;
        LogUtil.log(3, str3, "unSubscribeNotifications invalid,device class=" + baseDevice.getDeviceClass() + ",id=" + baseDevice.getDeviceId());
    }

    public void sendCommand(BaseDevice baseDevice, String str, String str2) {
        if (baseDevice != null) {
            String str3 = TAG;
            LogUtil.log(1, str3, "sendCommand,type=" + baseDevice.getDeviceType() + ",name=" + baseDevice.getDeviceName() + ",cmd=" + str + ",params=" + str2);
        } else {
            String str4 = TAG;
            LogUtil.log(1, str4, "sendCommand,cmd=" + str + ",params=" + str2);
        }
        selfGetService();
        if (this.mPipeBusClient != null) {
            try {
                String[] strArr = new String[4];
                if (baseDevice != null) {
                    strArr[0] = baseDevice.getDeviceType();
                    if (ScanDevice.DEVICE_TYPE.equals(strArr[0])) {
                        strArr[1] = DeviceBuilder.toJson(baseDevice).toString();
                    } else {
                        strArr[1] = baseDevice.getDeviceId();
                    }
                } else {
                    strArr[0] = null;
                    strArr[1] = null;
                }
                strArr[2] = str;
                strArr[3] = str2;
                this.mPipeBusClient.ioControl(IoTCommunication.MODULE_IOT, IoTCommunication.IO_SEND_DEVICE_CMD, strArr);
            } catch (RemoteException e) {
                String str5 = TAG;
                LogUtil.log(4, str5, "sendCommand fail,e=" + e.getMessage());
            }
        }
    }
}
