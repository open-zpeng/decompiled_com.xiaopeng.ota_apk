package com.xiaopeng.xuimanager.userscenario;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.userscenario.IUserScenario;
import com.xiaopeng.xuimanager.userscenario.IUserScenarioListener;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class UserScenarioManager implements XUIManagerBase {
    public static final String ACTION_KEY_EXIT_REASON = "exitReason";
    public static final String ACTION_KEY_NAME = "scenario_name";
    public static final String ACTION_KEY_SOURCE = "source";
    public static final String ACTION_KEY_STATUS = "status";
    public static final String ACTION_USERSCENARIO = "com.xiaopeng.xui.userscenario";
    public static final String ACTION_VALUE_ENTER = "enter";
    public static final String ACTION_VALUE_EXIT = "exit";
    private static final boolean DBG = true;
    private static final int MSG_EVENT_NOTIFY = 1;
    public static final String REASON_APP_REQUEST = "rAppRequest";
    public static final String REASON_DOOR_OPEN = "rDoorOpen";
    public static final String REASON_GEAR_NOT_P = "rGearNotP";
    public static final String REASON_IG_OFF = "rIgOff";
    public static final String REASON_SCREEN_ON = "rScreenOn";
    public static final String REASON_VOICE_ACTIVE = "rVoiceActive";
    public static final String RET_FAIL_DOOR_OPEN = "doorOpen";
    public static final String RET_FAIL_GEAR_NOT_P = "gearNotP";
    public static final String RET_REMOTE_EXCEPTION = "remoteException";
    public static final String RET_SCENARIO_CONFLICT = "conflict#";
    public static final String RET_SCENARIO_CONFLICT_CLEANING = "conflict#cleaning_mode";
    public static final String RET_SCENARIO_CONFLICT_MEDITATION = "conflict#meditation_mode";
    public static final String RET_SCENARIO_CONFLICT_SPACECAPSULE = "conflict#spacecapsule_mode";
    public static final String RET_SCENARIO_CONFLICT_VIPSEAT = "conflict#vipseat_mode";
    public static final String RET_SCENARIO_CONFLICT_WAITING = "conflict#waiting_mode";
    public static final String RET_SCENARIO_INVALID = "scenarioInvalid";
    public static final String RET_SCENARIO_UNAVAILABLE = "scenarioUnavailable";
    public static final String RET_SUCCESS = "success";
    public static final String SCENARIO_CLEANING_MODE = "cleaning_mode";
    public static final String SCENARIO_MEDITATION_MODE = "meditation_mode";
    public static final String SCENARIO_NORMAL = "normal_mode";
    public static final String SCENARIO_SPACECAPSULE_MODE = "spacecapsule_mode";
    public static final String SCENARIO_SPACECAPSULE_MOVIE_MODE = "spacecapsule_mode_movie";
    public static final String SCENARIO_SPACECAPSULE_SLEEP_MODE = "spacecapsule_mode_sleep";
    public static final String SCENARIO_VIPSEAT_MODE = "vipseat_mode";
    public static final String SCENARIO_WAITING_MODE = "waiting_mode";
    public static final String SOURCE_ACTIVITY = "activity";
    public static final String SOURCE_SERVICE = "service";
    public static final String SOURCE_SIGNAL = "signal";
    public static final String SOURCE_VOICE = "voice";
    public static final int STATE_EXITING = 3;
    public static final int STATE_IDLE = 0;
    public static final int STATE_INVALID = -1;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_STARTING = 1;
    public static final String TAG = "UserScenarioManager";
    private static String mServiceName;
    private Handler mHandler;
    private IUserScenario mService;
    private boolean serverDisconnected = false;
    private final ArraySet<UserScenarioListener> mListeners = new ArraySet<>();
    private UserScenarioListenerImpl mUserScenarioListenerImpl = null;

    /* loaded from: classes2.dex */
    public interface UserScenarioListener {
        void onUserScenarioStateChanged(String str, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEvent(String str, int i) {
        Iterator<UserScenarioListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onUserScenarioStateChanged(str, i);
        }
    }

    /* loaded from: classes2.dex */
    private static class UserScenarioListenerImpl extends IUserScenarioListener.Stub {
        private Handler mHandler;

        public UserScenarioListenerImpl(Handler handler) {
            this.mHandler = handler;
        }

        @Override // com.xiaopeng.xuimanager.userscenario.IUserScenarioListener
        public void onUserScenarioStateChanged(String str, int i) throws RemoteException {
            this.mHandler.obtainMessage(1, i, 0, str).sendToTarget();
        }
    }

    public UserScenarioManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = null;
        String str = TAG;
        Log.d(str, "create UserScenarioManager,context=" + context + ",handler=" + handler);
        this.mService = IUserScenario.Stub.asInterface(iBinder);
        this.mHandler = new Handler(handler.getLooper()) { // from class: com.xiaopeng.xuimanager.userscenario.UserScenarioManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str2 = UserScenarioManager.TAG;
                Log.d(str2, "handle msg:" + message.what);
                if (message.what != 1) {
                    return;
                }
                UserScenarioManager.this.notifyEvent((String) message.obj, message.arg1);
            }
        };
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public synchronized void onXUIDisconnected() {
        Log.d(TAG, "onXUIDisconnected");
        this.mListeners.clear();
        if (this.mUserScenarioListenerImpl != null) {
            try {
                this.mService.unregisterListener(this.mUserScenarioListenerImpl);
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "onXUIDisconnected,unregisterListener e=" + e);
            }
            this.mUserScenarioListenerImpl = null;
        }
        this.mHandler = null;
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public synchronized void onXUIConnected(IBinder iBinder) {
        if (this.serverDisconnected) {
            this.serverDisconnected = false;
            this.mService = IUserScenario.Stub.asInterface(iBinder);
            if (this.mUserScenarioListenerImpl != null) {
                try {
                    this.mService.registerListener(this.mUserScenarioListenerImpl);
                } catch (RemoteException e) {
                    String str = TAG;
                    Log.e(str, "registerListener e=" + e);
                }
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onServerDisconnected() {
        this.serverDisconnected = true;
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void setServiceName(String str) {
        mServiceName = str;
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public String getServiceName() {
        if (mServiceName == null) {
            mServiceName = getClass().getSimpleName();
        }
        return mServiceName;
    }

    public String enterUserScenario(String str, String str2) {
        String str3;
        try {
            str3 = this.mService.enterUserScenario(str, str2);
        } catch (RemoteException e) {
            String str4 = TAG;
            Log.e(str4, "enterUserScenario e=" + e);
            str3 = RET_REMOTE_EXCEPTION;
        }
        String str5 = TAG;
        Log.i(str5, "enterUserScenario-" + str + ",source-" + str2 + ",ret-" + str3);
        return str3;
    }

    public String exitUserScenario(String str) {
        String str2;
        try {
            str2 = this.mService.exitUserScenario(str);
        } catch (RemoteException e) {
            String str3 = TAG;
            Log.e(str3, "exitUserScenario e=" + e);
            str2 = RET_REMOTE_EXCEPTION;
        }
        String str4 = TAG;
        Log.i(str4, "exitUserScenario-" + str + ",ret-" + str2);
        return str2;
    }

    public String getCurrentUserScenario() {
        try {
            return this.mService.getCurrentUserScenario();
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "getCurrentUserScenario e=" + e);
            return null;
        }
    }

    public int getUserScenarioStatus(String str) {
        try {
            return this.mService.getUserScenarioStatus(str);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "getUserScenarioStatus e=" + e);
            return -1;
        }
    }

    public synchronized void registerListener(UserScenarioListener userScenarioListener) {
        String str = TAG;
        Log.i(str, "registerListener-" + userScenarioListener);
        if (userScenarioListener == null) {
            Log.w(TAG, "registerListener null!");
            return;
        }
        if (this.mUserScenarioListenerImpl == null) {
            this.mUserScenarioListenerImpl = new UserScenarioListenerImpl(this.mHandler);
            try {
                this.mService.registerListener(this.mUserScenarioListenerImpl);
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "registerListener e=" + e);
            }
        }
        this.mListeners.add(userScenarioListener);
    }

    public synchronized void unregisterListener(UserScenarioListener userScenarioListener) {
        String str = TAG;
        Log.i(str, "unregisterListener-" + userScenarioListener);
        this.mListeners.remove(userScenarioListener);
        if (this.mListeners.isEmpty()) {
            try {
                this.mService.unregisterListener(this.mUserScenarioListenerImpl);
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "unregisterListener e=" + e);
            }
            this.mUserScenarioListenerImpl = null;
        }
    }

    public void reportStatus(String str, int i) {
        IUserScenario iUserScenario = this.mService;
        if (iUserScenario == null) {
            Log.w(TAG, "reportStatus,invalid call");
            return;
        }
        try {
            iUserScenario.reportStatus(str, i);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "reportStatus e=" + e);
        }
    }

    public String registerBinderObserver(IBinder iBinder) {
        IUserScenario iUserScenario = this.mService;
        if (iUserScenario == null) {
            Log.w(TAG, "registerBinderObserver,invalid call");
            return RET_REMOTE_EXCEPTION;
        }
        try {
            iUserScenario.registerBinderObserver(iBinder);
            return RET_SUCCESS;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "registerBinderObserver e=" + e);
            return RET_REMOTE_EXCEPTION;
        }
    }

    public String checkEnterUserScenario(String str, String str2) {
        IUserScenario iUserScenario = this.mService;
        if (iUserScenario == null) {
            Log.w(TAG, "checkEnterUserScenario,invalid call");
            return RET_REMOTE_EXCEPTION;
        }
        try {
            return iUserScenario.checkEnterUserScenario(str, str2);
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "checkEnterUserScenario e=" + e);
            return RET_REMOTE_EXCEPTION;
        }
    }
}
