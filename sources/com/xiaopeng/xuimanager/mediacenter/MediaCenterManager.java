package com.xiaopeng.xuimanager.mediacenter;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.xiaopeng.xuimanager.XUIManager;
import com.xiaopeng.xuimanager.XUIManagerBase;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener;
import com.xiaopeng.xuimanager.mediacenter.IBTStatusListener;
import com.xiaopeng.xuimanager.mediacenter.ILyricUpdateListener;
import com.xiaopeng.xuimanager.mediacenter.IMediaCenter;
import com.xiaopeng.xuimanager.mediacenter.IMediaCenterEventListener;
import com.xiaopeng.xuimanager.mediacenter.IModeChangedListener;
import com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener;
import com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener;
import com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
@SystemApi
/* loaded from: classes2.dex */
public class MediaCenterManager implements XUIManagerBase {
    public static final String ACTION_MEDIA_PLAYBACK_CONTROL = "com.xiaopeng.xuiservice.playbackControl";
    public static final boolean DBG = false;
    public static final String EXTRA_PLAYBACK_CMD = "playbackCmd";
    public static final String EXTRA_PLAYBACK_PARAM = "playbackParam";
    public static final int MODE_CAR_SHOW = 1;
    public static final int MODE_NORMAL = 0;
    private static final int MSG_MEDIACENTER_ERROR_EVENT = 0;
    private static final int MSG_PLAYBACK_CONTROL_EVENT = 1;
    private static final int MSG_SET_FAVORITE_EVENT = 3;
    private static final int MSG_SWITCH_SOURCE_EVENT = 2;
    public static final int PLAYBACK_CMD_ENTER = 11;
    public static final int PLAYBACK_CMD_EXIT = 12;
    public static final int PLAYBACK_CMD_FAVORITE = 8;
    public static final int PLAYBACK_CMD_NEXT = 6;
    public static final int PLAYBACK_CMD_PAUSE = 2;
    public static final int PLAYBACK_CMD_PREV = 7;
    public static final int PLAYBACK_CMD_RESUME = 3;
    public static final int PLAYBACK_CMD_REWIND = 13;
    public static final int PLAYBACK_CMD_SEEKTO = 4;
    public static final int PLAYBACK_CMD_SET_LYRIC = 10;
    public static final int PLAYBACK_CMD_SET_MODE = 9;
    public static final int PLAYBACK_CMD_SPEED = 5;
    public static final int PLAYBACK_CMD_START = 0;
    public static final int PLAYBACK_CMD_STOP = 1;
    public static final int PLAYBACK_LYRIC_OFF = 1;
    public static final int PLAYBACK_LYRIC_ON = 0;
    public static final int PLAYBACK_MODE_CYCLE = 1;
    public static final int PLAYBACK_MODE_RANDOM = 3;
    public static final int PLAYBACK_MODE_SEQUENCE = 0;
    public static final int PLAYBACK_MODE_SINGLE_CYCLE = 2;
    public static final int PLAYBACK_STATE_NEW_MEDIASTREAM = 10;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_STARTED = 0;
    public static final int PLAYBACK_STATE_STOPED = 1;
    public static final int SOURCE_TYPE_FM = 1;
    public static final int SOURCE_TYPE_MUSIC = 0;
    public static final int STATE_BT_AVAILABLE = 2;
    public static final int STATE_BT_MEDIA_CONNECTED = 5;
    public static final int STATE_BT_MEDIA_CONNECTING = 4;
    public static final int STATE_BT_MEDIA_DISCONNECTING = 3;
    public static final int STATE_BT_SOURCE_SELECTED = 6;
    public static final int STATE_BT_UNAVAILABLE = 1;
    public static final String TAG = "MediaCenterManager";
    private static String mServiceName;
    private final Handler mHandler;
    private IMediaCenter mService;
    private final ArraySet<MediaCenterEventListener> mListeners = new ArraySet<>();
    private MediaCenterEventListenerToService mListenerToService = null;
    private final Map<String, VendorControlListener> mVendorControlListeners = new HashMap();
    private PlaybackControlListener mControlListenerToService = null;
    private final ArraySet<VisualCaptureListener> mVisualCaptureListeners = new ArraySet<>();
    private AudioCaptureListener mAudioCaptureListenerToService = null;
    private final ArraySet<PlaybackListener> mPlaybackListeners = new ArraySet<>();
    private PlaybackInfoListener mPlaybackInfoListenerToService = null;
    private final List<VisualizerViewEnableListener> mVisualizerViewEnableListeners = new CopyOnWriteArrayList();
    private VisualizerViewEnableServiceListener mVisualizerViewEnableListenerToService = null;
    private List<LyricUpdateListener> mLyricUpdateListeners = new CopyOnWriteArrayList();
    private LyricUpdateServiceListener mLyricUpdateServiceListener = null;
    private final List<ModeChangedListener> mModeChangedListeners = new CopyOnWriteArrayList();
    private ModeChangedServiceListener mModeChangedListenerToService = null;
    private List<BTStatusListener> mBTStatusListeners = new ArrayList();
    private BTStatusListenerToService mBTStatusListenerToService = new BTStatusListenerToService(this);

    /* loaded from: classes2.dex */
    public interface BTStatusListener {
        void onBtStatusChanged(int i);
    }

    /* loaded from: classes2.dex */
    public interface LyricUpdateListener {
        void onLyricUpdated(String str);
    }

    /* loaded from: classes2.dex */
    public interface MediaCenterEventListener {
        void onErrorEvent(int i, int i2);
    }

    /* loaded from: classes2.dex */
    public interface ModeChangedListener {
        void OnModeChanged(int i);
    }

    /* loaded from: classes2.dex */
    public interface PlaybackListener {
        void OnMediaInfoNotify(MediaInfo mediaInfo);

        void OnPlaybackChanged(int i);

        void OnUpdatePosition(long j, long j2);
    }

    /* loaded from: classes2.dex */
    public interface VendorControlListener {
        int OnPlaybackControl(int i, int i2);

        int OnSetFavorite(boolean z, String str);

        int OnSwitchSource(int i);
    }

    /* loaded from: classes2.dex */
    public interface VisualCaptureListener {
        void OnFftDataCapture(byte[] bArr, int i);

        default void OnRatioData(float f, float f2) {
        }
    }

    /* loaded from: classes2.dex */
    public interface VisualizerViewEnableListener {
        void OnVisualizerViewEnable(boolean z);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<MediaCenterManager> mMgr;

        EventCallbackHandler(MediaCenterManager mediaCenterManager, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(mediaCenterManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MediaCenterManager mediaCenterManager = this.mMgr.get();
            int i = message.what;
            if (i == 0) {
                if (mediaCenterManager != null) {
                    mediaCenterManager.dispatchErrorEventToClient(((Integer) message.obj).intValue(), message.arg1);
                }
            } else if (i == 1) {
                if (mediaCenterManager != null) {
                    mediaCenterManager.dispatchControlEventToVendor(message.arg1, message.arg2, (String) message.obj);
                }
            } else if (i == 2) {
                if (mediaCenterManager != null) {
                    mediaCenterManager.dispatchSwitchSourceEventToVendor(message.arg1, (String) message.obj);
                }
            } else if (i == 3) {
                if (mediaCenterManager != null) {
                    mediaCenterManager.dispatchSetFavoriteEventToVendor((FavoriteEvent) message.obj);
                }
            } else {
                Log.d(MediaCenterManager.TAG, "Event type not handled?" + message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MediaCenterEventListenerToService extends IMediaCenterEventListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public MediaCenterEventListenerToService(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IMediaCenterEventListener
        public void onError(int i, int i2) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.handleErrorEvent(i, i2);
            }
        }
    }

    /* loaded from: classes2.dex */
    private class PlaybackControlListener extends IPlaybackControlListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;
        private final String mPkgName;

        public PlaybackControlListener(String str, MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
            this.mPkgName = str;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnPlaybackControl(int i, int i2) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.handlePlaybackControlEvent(i, i2, this.mPkgName);
                return 0;
            }
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnSwitchSource(int i) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.handleSwitchSourceEvent(i, this.mPkgName);
                return 0;
            }
            return 0;
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackControlListener
        public int OnSetFavorite(boolean z, String str) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.handleSetFavoriteEvent(z, str, this.mPkgName);
                return 0;
            }
            return 0;
        }
    }

    /* loaded from: classes2.dex */
    private static class AudioCaptureListener extends IAudioCaptureListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public AudioCaptureListener(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
        public void OnFftDataCapture(byte[] bArr, int i) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnFftDataCapture(bArr, i);
            }
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IAudioCaptureListener
        public void OnRatioData(float f, float f2) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnRatioData(f, f2);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class PlaybackInfoListener extends IPlaybackInfoListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public PlaybackInfoListener(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnPlaybackChanged(int i) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnPlaybackChanged(i);
            }
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnUpdatePosition(long j, long j2) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnUpdatePosition(j, j2);
            }
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IPlaybackInfoListener
        public void OnMediaInfoNotify(MediaInfo mediaInfo) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnMediaInfoNotify(mediaInfo);
            }
        }
    }

    public MediaCenterManager(IBinder iBinder, Context context, Handler handler) {
        this.mService = IMediaCenter.Stub.asInterface(iBinder);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public synchronized void registerListener(MediaCenterEventListener mediaCenterEventListener) throws XUIServiceNotConnectedException {
        if (this.mListeners.isEmpty()) {
            try {
                this.mListenerToService = new MediaCenterEventListenerToService(this);
                this.mService.registerListener(this.mListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mListeners.add(mediaCenterEventListener);
    }

    public synchronized void unregisterListener(MediaCenterEventListener mediaCenterEventListener) throws XUIServiceNotConnectedException {
        this.mListeners.remove(mediaCenterEventListener);
        if (this.mListeners.isEmpty()) {
            try {
                this.mService.unregisterListener(this.mListenerToService);
                this.mListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public synchronized void vendorRegister(Context context) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorRegister();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public synchronized void vendorUnRegister(Context context) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorUnRegister();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void vendorUpdatePlaybackStatus(int i) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorUpdatePlaybackStatus(i);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void vendorUpdatePosition(long j, long j2) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorUpdatePosition(j, j2);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void vendorMediaInfoNotify(MediaInfo mediaInfo) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorMediaInfoNotify(mediaInfo);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public synchronized void vendorSetControlListener(Context context, VendorControlListener vendorControlListener) throws XUIServiceNotConnectedException {
        String packageName = context.getPackageName();
        if (this.mVendorControlListeners.containsKey(packageName)) {
            this.mVendorControlListeners.remove(packageName);
        }
        try {
            this.mService.vendorSetControlListener(packageName, new PlaybackControlListener(packageName, this));
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
        }
        this.mVendorControlListeners.put(packageName, vendorControlListener);
    }

    public void vendorStartAudioSession(int i, int i2, String str) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorStartAudioSession(i, i2, str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
        }
    }

    public void vendorStopAudioSession(int i, String str) throws XUIServiceNotConnectedException {
        try {
            this.mService.vendorStopAudioSession(i, str);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
        }
    }

    public synchronized void vendorUnSetControlListener(Context context, VendorControlListener vendorControlListener) throws XUIServiceNotConnectedException {
        String packageName = context.getPackageName();
        try {
            this.mService.vendorUnSetControlListener(packageName, null);
            this.mVendorControlListeners.remove(packageName);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public synchronized void venderInvoke(Parcel parcel, Parcel parcel2) {
    }

    public int getCurrentPlayStatus() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getCurrentPlayStatus();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            return 1;
        }
    }

    public MediaInfo getCurrentMediaInfo() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getCurrentMediaInfo();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            return null;
        }
    }

    public long[] getCurrentPosition() throws XUIServiceNotConnectedException {
        long[] jArr = {0, 0};
        try {
            return this.mService.getCurrentPosition();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            return jArr;
        }
    }

    public synchronized void registerVisualizerListener(VisualCaptureListener visualCaptureListener) throws XUIServiceNotConnectedException {
        if (this.mVisualCaptureListeners.isEmpty()) {
            try {
                this.mAudioCaptureListenerToService = new AudioCaptureListener(this);
                this.mService.registerVisualizerListener(this.mAudioCaptureListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mVisualCaptureListeners.add(visualCaptureListener);
    }

    public synchronized void unRegisterVisualizerListener(VisualCaptureListener visualCaptureListener) throws XUIServiceNotConnectedException {
        this.mVisualCaptureListeners.remove(visualCaptureListener);
        if (this.mVisualCaptureListeners.isEmpty() && this.mAudioCaptureListenerToService != null) {
            try {
                this.mService.unRegisterVisualizerListener(this.mAudioCaptureListenerToService);
                this.mAudioCaptureListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public synchronized void registerPlaybackListener(PlaybackListener playbackListener) throws XUIServiceNotConnectedException {
        if (this.mPlaybackListeners.isEmpty()) {
            try {
                this.mPlaybackInfoListenerToService = new PlaybackInfoListener(this);
                this.mService.registerPlaybackInfoListener(this.mPlaybackInfoListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        this.mPlaybackListeners.add(playbackListener);
    }

    public synchronized void unRegisterPlaybackListener(PlaybackListener playbackListener) throws XUIServiceNotConnectedException {
        this.mPlaybackListeners.remove(playbackListener);
        if (this.mPlaybackListeners.isEmpty()) {
            try {
                this.mService.unRegisterPlaybackInfoListener(this.mPlaybackInfoListenerToService);
                this.mPlaybackInfoListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public int playbackControl(int i, int i2) throws XUIServiceNotConnectedException {
        try {
            return this.mService.playbackControl(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public int switchSource(int i) throws XUIServiceNotConnectedException {
        try {
            return this.mService.switchSource(i);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchErrorEventToClient(int i, int i2) {
        ArraySet<MediaCenterEventListener> arraySet;
        synchronized (this) {
            arraySet = this.mListeners;
        }
        if (!arraySet.isEmpty()) {
            for (MediaCenterEventListener mediaCenterEventListener : arraySet) {
                mediaCenterEventListener.onErrorEvent(i, i2);
            }
            return;
        }
        Log.d(TAG, "listener is empty, not dispatching error event to client");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleErrorEvent(int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = Integer.valueOf(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchControlEventToVendor(int i, int i2, String str) {
        Map<String, VendorControlListener> map;
        synchronized (this) {
            map = this.mVendorControlListeners;
        }
        if (!map.isEmpty()) {
            VendorControlListener vendorControlListener = map.get(str);
            if (vendorControlListener != null) {
                vendorControlListener.OnPlaybackControl(i, i2);
                return;
            }
            return;
        }
        Log.d(TAG, "listeners is empty, not dispatching control event to vendor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlaybackControlEvent(int i, int i2, String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        obtainMessage.obj = str;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchSwitchSourceEventToVendor(int i, String str) {
        Map<String, VendorControlListener> map;
        synchronized (this) {
            map = this.mVendorControlListeners;
        }
        if (!map.isEmpty()) {
            VendorControlListener vendorControlListener = map.get(str);
            if (vendorControlListener != null) {
                vendorControlListener.OnSwitchSource(i);
                return;
            }
            return;
        }
        Log.e(TAG, "Listener is empty, not dispatching source change event to vendor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchSetFavoriteEventToVendor(FavoriteEvent favoriteEvent) {
        Map<String, VendorControlListener> map;
        synchronized (this) {
            map = this.mVendorControlListeners;
        }
        if (!map.isEmpty()) {
            VendorControlListener vendorControlListener = map.get(favoriteEvent.mPkgName);
            if (vendorControlListener != null) {
                vendorControlListener.OnSetFavorite(favoriteEvent.mFavorite, favoriteEvent.mId);
                return;
            }
            return;
        }
        Log.e(TAG, "Listener is empty, not dispatching source change event to vendor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSwitchSourceEvent(int i, String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.arg1 = i;
        obtainMessage.obj = str;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetFavoriteEvent(boolean z, String str, String str2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.obj = new FavoriteEvent(z, str, str2);
        this.mHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OnFftDataCapture(byte[] bArr, int i) {
        ArraySet<VisualCaptureListener> arraySet;
        synchronized (this) {
            arraySet = this.mVisualCaptureListeners;
        }
        if (!arraySet.isEmpty()) {
            for (VisualCaptureListener visualCaptureListener : arraySet) {
                visualCaptureListener.OnFftDataCapture(bArr, i);
            }
            return;
        }
        Log.d(TAG, "Listener is empty, not dispatching fft data");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OnRatioData(float f, float f2) {
        ArraySet<VisualCaptureListener> arraySet;
        synchronized (this) {
            arraySet = this.mVisualCaptureListeners;
        }
        if (!arraySet.isEmpty()) {
            for (VisualCaptureListener visualCaptureListener : arraySet) {
                visualCaptureListener.OnRatioData(f, f2);
            }
            return;
        }
        Log.d(TAG, "Listener died, not dispatching fft data.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OnPlaybackChanged(int i) {
        ArraySet<PlaybackListener> arraySet;
        synchronized (this) {
            arraySet = this.mPlaybackListeners;
        }
        if (!arraySet.isEmpty()) {
            for (PlaybackListener playbackListener : arraySet) {
                playbackListener.OnPlaybackChanged(i);
            }
            return;
        }
        Log.d(TAG, "listener is empty, not dispatching packback changed event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OnUpdatePosition(long j, long j2) {
        ArraySet<PlaybackListener> arraySet;
        synchronized (this) {
            arraySet = this.mPlaybackListeners;
        }
        if (!arraySet.isEmpty()) {
            for (PlaybackListener playbackListener : arraySet) {
                playbackListener.OnUpdatePosition(j, j2);
            }
            return;
        }
        Log.d(TAG, "listener is empty, not dispatching update position event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OnMediaInfoNotify(MediaInfo mediaInfo) {
        ArraySet<PlaybackListener> arraySet;
        synchronized (this) {
            arraySet = this.mPlaybackListeners;
        }
        if (!arraySet.isEmpty()) {
            for (PlaybackListener playbackListener : arraySet) {
                playbackListener.OnMediaInfoNotify(mediaInfo);
            }
            return;
        }
        Log.d(TAG, "listener is empty, not dispatching mediainfo notify event");
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIDisconnected() {
        Iterator<MediaCenterEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            try {
                unregisterListener(it.next());
            } catch (XUIServiceNotConnectedException unused) {
            }
        }
    }

    @Override // com.xiaopeng.xuimanager.XUIManagerBase
    public void onXUIConnected(IBinder iBinder) {
        this.mService = IMediaCenter.Stub.asInterface(iBinder);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void OnVisualizerViewEnable(boolean z) {
        if (!this.mVisualizerViewEnableListeners.isEmpty()) {
            for (VisualizerViewEnableListener visualizerViewEnableListener : this.mVisualizerViewEnableListeners) {
                visualizerViewEnableListener.OnVisualizerViewEnable(z);
            }
            return;
        }
        Log.d(TAG, "mVisualizerViewEnableListeners is empty");
    }

    public void setVisualizerViewEnable(boolean z) throws XUIServiceNotConnectedException {
        try {
            this.mService.setVisualizerViewEnable(z);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void registerVisualizerViewEnableListener(VisualizerViewEnableListener visualizerViewEnableListener) throws XUIServiceNotConnectedException {
        if (this.mVisualizerViewEnableListeners.isEmpty()) {
            try {
                this.mVisualizerViewEnableListenerToService = new VisualizerViewEnableServiceListener(this);
                this.mService.registerVisualizerViewEnableListener(this.mVisualizerViewEnableListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        if (this.mVisualizerViewEnableListeners.contains(visualizerViewEnableListener)) {
            return;
        }
        this.mVisualizerViewEnableListeners.add(visualizerViewEnableListener);
    }

    public void unRegisterVisualizerViewEnableListener(VisualizerViewEnableListener visualizerViewEnableListener) throws XUIServiceNotConnectedException {
        if (this.mVisualizerViewEnableListeners.contains(visualizerViewEnableListener)) {
            this.mVisualizerViewEnableListeners.remove(visualizerViewEnableListener);
        }
        if (this.mVisualizerViewEnableListeners.isEmpty()) {
            try {
                this.mService.unRegisterVisualizerViewEnableListener(this.mVisualizerViewEnableListenerToService);
                this.mVisualizerViewEnableListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class VisualizerViewEnableServiceListener extends IVisualizerViewEnableListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public VisualizerViewEnableServiceListener(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IVisualizerViewEnableListener
        public void onVisualizerViewEnable(boolean z) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.OnVisualizerViewEnable(z);
            }
        }
    }

    public void notifyLyricUpdate(String str) throws XUIServiceNotConnectedException {
        try {
            this.mService.notifyLyricUpdate(str);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            throw new XUIServiceNotConnectedException(e);
        }
    }

    public void registerLyricUpdateListener(LyricUpdateListener lyricUpdateListener) throws XUIServiceNotConnectedException {
        if (this.mLyricUpdateListeners.isEmpty()) {
            try {
                this.mLyricUpdateServiceListener = new LyricUpdateServiceListener(this);
                this.mService.registerLyricUpdateListener(this.mLyricUpdateServiceListener);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        if (this.mLyricUpdateListeners.contains(lyricUpdateListener)) {
            return;
        }
        this.mLyricUpdateListeners.add(lyricUpdateListener);
    }

    public void unRegisterLyricUpdateListener(LyricUpdateListener lyricUpdateListener) throws XUIServiceNotConnectedException {
        if (this.mLyricUpdateListeners.contains(lyricUpdateListener)) {
            this.mLyricUpdateListeners.remove(lyricUpdateListener);
        }
        if (this.mLyricUpdateListeners.isEmpty()) {
            try {
                this.mService.unRegisterLyricUpdateListener(this.mLyricUpdateServiceListener);
                this.mLyricUpdateServiceListener = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceLyricUpdated(String str) {
        if (!this.mLyricUpdateListeners.isEmpty()) {
            for (LyricUpdateListener lyricUpdateListener : this.mLyricUpdateListeners) {
                lyricUpdateListener.onLyricUpdated(str);
            }
            return;
        }
        Log.d(TAG, "mLyricUpdateListeners is empty");
    }

    /* loaded from: classes2.dex */
    private static class LyricUpdateServiceListener extends ILyricUpdateListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public LyricUpdateServiceListener(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.ILyricUpdateListener
        public void onLyricUpdated(String str) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.onServiceLyricUpdated(str);
            }
        }
    }

    public void registerModeChangedListener(ModeChangedListener modeChangedListener) throws XUIServiceNotConnectedException {
        if (this.mModeChangedListeners.isEmpty()) {
            try {
                this.mModeChangedListenerToService = new ModeChangedServiceListener(this);
                this.mService.registerModeChangedListener(this.mModeChangedListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        if (this.mModeChangedListeners.contains(modeChangedListener)) {
            return;
        }
        this.mModeChangedListeners.add(modeChangedListener);
    }

    public void unRegisterModeChangedListener(ModeChangedListener modeChangedListener) throws XUIServiceNotConnectedException {
        if (this.mModeChangedListeners.contains(modeChangedListener)) {
            this.mModeChangedListeners.remove(modeChangedListener);
        }
        if (this.mModeChangedListeners.isEmpty()) {
            try {
                this.mService.unRegisterModeChangedListener(this.mModeChangedListenerToService);
                this.mModeChangedListenerToService = null;
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public int getCurrentMode() throws XUIServiceNotConnectedException {
        try {
            return this.mService.getCurrentMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: " + e.toString());
            throw new XUIServiceNotConnectedException(e);
        } catch (IllegalStateException e2) {
            XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            return 0;
        }
    }

    /* loaded from: classes2.dex */
    private static class ModeChangedServiceListener extends IModeChangedListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public ModeChangedServiceListener(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IModeChangedListener
        public void onModeChanged(int i) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.notifyClientModeChanged(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyClientModeChanged(int i) {
        if (!this.mModeChangedListeners.isEmpty()) {
            for (ModeChangedListener modeChangedListener : this.mModeChangedListeners) {
                modeChangedListener.OnModeChanged(i);
            }
            return;
        }
        Log.d(TAG, "mModeChangedListeners is empty");
    }

    /* loaded from: classes2.dex */
    private static class BTStatusListenerToService extends IBTStatusListener.Stub {
        private final WeakReference<MediaCenterManager> mManager;

        public BTStatusListenerToService(MediaCenterManager mediaCenterManager) {
            this.mManager = new WeakReference<>(mediaCenterManager);
        }

        @Override // com.xiaopeng.xuimanager.mediacenter.IBTStatusListener
        public void onBtStatusChanged(int i) {
            MediaCenterManager mediaCenterManager = this.mManager.get();
            if (mediaCenterManager != null) {
                mediaCenterManager.notifyBtStatusChanged(i);
            }
        }
    }

    public synchronized void registerBtStatusListener(BTStatusListener bTStatusListener) throws XUIServiceNotConnectedException {
        if (this.mBTStatusListeners.isEmpty()) {
            try {
                Log.d(TAG, "registerBtStatusListener");
                this.mService.registerBtStatusListener(this.mBTStatusListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            } catch (IllegalStateException e2) {
                XUIManager.checkXUIServiceNotConnectedExceptionFromXUIService(e2);
            }
        }
        if (!this.mBTStatusListeners.contains(bTStatusListener)) {
            this.mBTStatusListeners.add(bTStatusListener);
        }
    }

    public synchronized void unRegisterBtStatusListener(BTStatusListener bTStatusListener) throws XUIServiceNotConnectedException {
        if (this.mBTStatusListeners.contains(bTStatusListener)) {
            this.mBTStatusListeners.remove(bTStatusListener);
        }
        if (this.mBTStatusListeners.isEmpty()) {
            try {
                Log.d(TAG, "unRegisterBtStatusListener");
                this.mService.unRegisterBtStatusListener(this.mBTStatusListenerToService);
            } catch (RemoteException e) {
                Log.e(TAG, "Could not unregister: " + e.toString());
                throw new XUIServiceNotConnectedException(e);
            }
        }
    }

    public synchronized void playBtMedia() throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                this.mService.playBtMedia();
            } catch (RemoteException unused) {
                Log.e(TAG, "playBtMedia error");
            }
        }
    }

    public synchronized void pauseBtMedia() throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                this.mService.pauseBtMedia();
            } catch (RemoteException unused) {
                Log.e(TAG, "pauseBtMedia error");
            }
        }
    }

    public synchronized boolean isBtDeviceAvailable() throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                return this.mService.getBtStatus() >= 2;
            } catch (RemoteException unused) {
                Log.e(TAG, "isBtDeviceAvailable error");
            }
        }
        return false;
    }

    public synchronized boolean isBtSourceSelected() throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                return this.mService.getBtStatus() == 6;
            } catch (RemoteException unused) {
                Log.e(TAG, "isBtSourceSelected error");
            }
        }
        return false;
    }

    public synchronized int getBtStatus() throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                return this.mService.getBtStatus();
            } catch (RemoteException unused) {
                Log.e(TAG, "getBtStatus error");
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBtStatusChanged(int i) {
        if (this.mBTStatusListeners.size() > 0) {
            for (BTStatusListener bTStatusListener : this.mBTStatusListeners) {
                bTStatusListener.onBtStatusChanged(i);
            }
        }
    }

    public synchronized void setFavorite(boolean z, String str) throws XUIServiceNotConnectedException {
        if (this.mService != null) {
            try {
                this.mService.setFavorite(z, str);
            } catch (RemoteException unused) {
                Log.e(TAG, "setFavorite error");
            }
        }
    }

    /* loaded from: classes2.dex */
    public class FavoriteEvent {
        public boolean mFavorite;
        public String mId;
        public String mPkgName;

        public FavoriteEvent(boolean z, String str, String str2) {
            this.mFavorite = z;
            this.mId = str;
            this.mPkgName = str2;
        }
    }
}
