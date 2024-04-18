package com.xiaopeng.speech.protocol.node.navi;

import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.actorapi.ResultActor;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.FeedUIEvent;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.bean.FeedListUIValue;
import com.xiaopeng.speech.protocol.bean.XpListTypeEnum;
import com.xiaopeng.speech.protocol.event.ContextEvent;
import com.xiaopeng.speech.protocol.event.NaviEvent;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
import com.xiaopeng.speech.protocol.node.navi.bean.AddressBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NaviPreferenceBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NearbySearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PathBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiSearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.RouteSelectBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectParkingBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectRouteBean;
import com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean;
import com.xiaopeng.speech.protocol.node.navi.bean.WaypointSearchBean;
import com.xiaopeng.speech.speechwidget.ContentWidget;
import com.xiaopeng.speech.speechwidget.ListWidget;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import com.xiaopeng.speech.speechwidget.TextWidget;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NaviNode extends SpeechNode<NaviListener> {
    private static final String ACTIVE_VOICE_TASK = "主动语音";
    public static final String ALL_ROUTE_WIDGET_ID = "-Route-2";
    public static final String BASE_ROUTE_WIDGET_ID = "-Route-1";
    private static final String COMMAND_SPLIT = "#";
    private static final String KEY_MODE = "mode";
    private static final String KEY_PULLUP_NAVI = "pullUpNavi";
    private static final String OFFLINE_SKILL = "离线命令词";
    private static final String SELECT_PARKING_INTENT = "停车场主动语音";
    private static final String SELECT_ROUTE_INTENT = "路线主动语音";
    private static final int STOP_DIALOG_OPT_FORCE = 0;
    private static final int STOP_DIALOG_OPT_OPTIONAL = 1;
    private boolean mAddressPendingRoute = false;
    private IBinder binder = new Binder();

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE)
    public void onControlClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMIN)
    public void onMapZoomIn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomIn();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMOUT)
    public void onMapZoomOut(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomOut();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROAD_INFO_OPEN)
    public void onOpenTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onOpenTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROAD_INFO_CLOSE)
    public void onCloseTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onCloseTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OVERVIEW_OPEN)
    public void onControlOverviewOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOverviewOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OVERVIEW_CLOSE)
    public void onControlOverviewClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOverviewClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_OVERVIEW)
    public void onMapOverview(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapOverview();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_FAVORITE_OPEN)
    public void onControlFavoriteOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlFavoriteOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SETTINGS_OPEN)
    public void onControlSettingsOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSettingsOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_OPEN)
    public void onControlChargeOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlChargeOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_CLOSE)
    public void onControlChargeClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlChargeClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONGESTION)
    public void onDriveAvoidCongestion(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCongestion();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONGESTION_OFF)
    public void onDriveAvoidCongestionOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCongestionOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CHARGE)
    public void onDriveAvoidCharge(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCharge();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CHARGE_OFF)
    public void onDriveAvoidChargeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidChargeOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_FIRST_OFF)
    public void onDriveHighwayFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONTROLS)
    public void onDriveAvoidControls(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidControls();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONTROLS_OFF)
    public void onDriveAvoidControlsOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidControlsOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_RADAR_ROUTE)
    public void onDriveRadarRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveRadarRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_RADAR_ROUTE_OFF)
    public void onDriveRadarRouteOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveRadarRouteOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_MUTE)
    public void onControlSpeechMute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechMute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_SUPER_SIMPLE)
    public void onControlSpeechSuperSimple(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechSuperSimple();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_GENERAL)
    public void onControlSpeechGeneral(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechGeneral();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_EYE)
    public void onControlSpeechEye(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechEye();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_EYE_OFF)
    public void onControlSpeechEyeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechEyeOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_SCALE)
    public void onControlSmartScale(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartScale();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_SCALE_OFF)
    public void onControlSmartScaleOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartScaleOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SECURITY_REMIND)
    public void onControlSecurityRemind(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSecurityRemind();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_ROAD_AHEAD)
    public void onControlRoadAhead(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRoadAhead();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_NO)
    public void onDriveHighwayNo(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayNo();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_NO_OFF)
    public void onDriveHighwayNoOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayNoOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_FIRST)
    public void onDriveHighwayFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.NAVIGATING_GET)
    public void onNavigatingGet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNavigatingGet();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.POI_SEARCH)
    public void onPoiSearch(String str, String str2) {
        PoiSearchBean fromJson = PoiSearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiSearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SECURITY_REMIND_OFF)
    public void onControlSecurityRemindOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSecurityRemindOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ENTER_FIND_PATH)
    public void onMapEnterFindPath(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapEnterFindPath();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_EXIT_FIND_PATH)
    public void onMapExitFindPath(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapExitFindPath();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SEARCH_CLOSE)
    public void onSearchClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSearchClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAIN_ROAD)
    public void onMainRoad(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMainRoad();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SIDE_ROAD)
    public void onSideRoad(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSideRoad();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_FAVORITE_CLOSE)
    public void onControlFavoriteClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlFavoriteClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_ROAD_AHEAD_OFF)
    public void onControlRoadAheadOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRoadAheadOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMIN_MAX)
    public void onMapZoominMax(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoominMax();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMOUT_MIN)
    public void onMapZoomoutMin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomoutMin();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.NEARBY_SEARCH)
    public void onNearbySearch(String str, String str2) {
        NearbySearchBean fromJson = NearbySearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNearbySearch(fromJson);
            }
        }
    }

    public void postPoiResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.POI_SEARCH, str, list);
    }

    public void postNearbyResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.NEARBY_SEARCH, str, list);
    }

    public void postSearchPoiResult(String str, String str2, List<PoiBean> list) {
        postPoiResult(str, str2, list);
    }

    public void postAddressPosResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.POI_SEARCH, str, list);
    }

    public void postWaypointsFull(String str) {
        postWaypointSearchResult(str, null, true, true);
    }

    public void postWaypointsNotExitRoute(String str) {
        postWaypointSearchResult(str, null, false, false);
    }

    public void postWaypointSearchResult(String str, List<PoiBean> list) {
        postWaypointSearchResult(str, list, true, false);
    }

    private void postWaypointSearchResult(String str, List<PoiBean> list, boolean z, boolean z2) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(str);
        listWidget.setExist(z);
        listWidget.setExtraType("navi");
        listWidget.addContent("isWaypointListFull", String.valueOf(z2));
        if (list != null) {
            for (PoiBean poiBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(poiBean.getName());
                contentWidget.setSubTitle(poiBean.getAddress());
                try {
                    contentWidget.addExtra("navi", poiBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.WAYPOINT_SEARCH).setResult(listWidget));
    }

    private void postPoiResult(String str, String str2, List<PoiBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(str2);
        listWidget.setExtraType("navi");
        if (list != null) {
            for (PoiBean poiBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(poiBean.getName());
                contentWidget.setSubTitle(poiBean.getAddress());
                try {
                    contentWidget.addExtra("navi", poiBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(str).setResult(listWidget));
    }

    @SpeechAnnotation(event = NaviEvent.ADDRESS_GET)
    public void onAddressGet(String str, String str2) {
        AddressBean fromJson = AddressBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAddressGet(fromJson);
            }
        }
        this.mAddressPendingRoute = false;
    }

    public void postAddressGetResult(boolean z, boolean z2, PoiBean poiBean) {
        TextWidget textWidget = new TextWidget();
        textWidget.setText(z ? "success" : "fail");
        textWidget.addContent("hasBigData", z2 ? OOBEEvent.STRING_TRUE : OOBEEvent.STRING_FALSE);
        if (poiBean != null) {
            try {
                textWidget.addContent("address", poiBean.getAddress());
                textWidget.addExtra("navi", poiBean.toJson().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.ADDRESS_GET).setResult(textWidget));
    }

    @SpeechAnnotation(event = NaviEvent.ADDRESS_PENDING_ROUTE)
    public void onAddressPendingRoute(String str, String str2) {
        LogUtils.i(this, "pending route");
        this.mAddressPendingRoute = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.ADDRESS_SET)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAddressSet(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r10 = "soundArea"
            com.xiaopeng.speech.protocol.node.navi.bean.AddressBean r0 = new com.xiaopeng.speech.protocol.node.navi.bean.AddressBean
            r0.<init>()
            r1 = 0
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L65
            r3.<init>(r11)     // Catch: org.json.JSONException -> L65
            java.lang.String r11 = "addressType"
            java.lang.String r11 = r3.optString(r11)     // Catch: org.json.JSONException -> L65
            r0.setAddressType(r11)     // Catch: org.json.JSONException -> L65
            java.lang.String r11 = "poi"
            java.lang.String r11 = r3.optString(r11)     // Catch: org.json.JSONException -> L65
            com.xiaopeng.speech.protocol.node.navi.bean.PoiBean r11 = com.xiaopeng.speech.protocol.node.navi.bean.PoiBean.fromJson(r11)     // Catch: org.json.JSONException -> L65
            java.lang.String r4 = "pref"
            java.lang.String r4 = r3.optString(r4)     // Catch: org.json.JSONException -> L60
            java.lang.String r5 = "type"
            java.lang.String r5 = r3.optString(r5)     // Catch: org.json.JSONException -> L5b
            java.lang.String r6 = "naviType"
            int r6 = r3.optInt(r6)     // Catch: org.json.JSONException -> L57
            java.lang.String r7 = "routeSelectRef"
            int r7 = r3.optInt(r7)     // Catch: org.json.JSONException -> L54
            boolean r8 = r3.has(r10)     // Catch: org.json.JSONException -> L52
            if (r8 == 0) goto L6e
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: org.json.JSONException -> L52
            r8.<init>()     // Catch: org.json.JSONException -> L52
            int r2 = r3.getInt(r10)     // Catch: org.json.JSONException -> L4f
            r8.put(r10, r2)     // Catch: org.json.JSONException -> L4f
            r2 = r8
            goto L6e
        L4f:
            r10 = move-exception
            r2 = r8
            goto L6b
        L52:
            r10 = move-exception
            goto L6b
        L54:
            r10 = move-exception
            r7 = r1
            goto L6b
        L57:
            r10 = move-exception
            r6 = r1
            r7 = r6
            goto L6b
        L5b:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r5 = r2
            goto L6b
        L60:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r4 = r2
            goto L6a
        L65:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r11 = r2
            r4 = r11
        L6a:
            r5 = r4
        L6b:
            r10.printStackTrace()
        L6e:
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r10 = r9.mListenerList
            java.lang.Object[] r10 = r10.collectCallbacks()
            if (r10 == 0) goto L84
            r3 = r1
        L77:
            int r8 = r10.length
            if (r3 >= r8) goto L84
            r8 = r10[r3]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r8 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r8
            r8.onAddressSet(r0, r11)
            int r3 = r3 + 1
            goto L77
        L84:
            boolean r10 = r9.mAddressPendingRoute
            if (r10 == 0) goto L99
            com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean r10 = new com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean
            r10.<init>(r11, r4, r5)
            r10.setNaviType(r6)
            r10.setRouteSelectRef(r7)
            r10.setSpeechInfo(r2)
            r9.doControlStart(r10)
        L99:
            r9.mAddressPendingRoute = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onAddressSet(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_START)
    public void onControlStart(String str, String str2) {
        boolean z;
        int i;
        int i2;
        PoiBean poiBean;
        String str3;
        String str4;
        boolean z2;
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str2);
            poiBean = PoiBean.fromJson(jSONObject2.optString("poi"));
            try {
                str3 = jSONObject2.optString("pref");
                try {
                    str4 = jSONObject2.optString("type");
                    try {
                        i = jSONObject2.optInt("naviType");
                        try {
                            i2 = jSONObject2.optInt("routeSelectRef");
                            try {
                                z2 = jSONObject2.has("fullSceneSwitch") ? jSONObject2.getBoolean("fullSceneSwitch") : true;
                                try {
                                    if (jSONObject2.has("soundArea")) {
                                        JSONObject jSONObject3 = new JSONObject();
                                        try {
                                            jSONObject3.put("soundArea", jSONObject2.getInt("soundArea"));
                                            jSONObject = jSONObject3;
                                        } catch (JSONException e) {
                                            e = e;
                                            jSONObject = jSONObject3;
                                            JSONException jSONException = e;
                                            z = z2;
                                            e = jSONException;
                                            e.printStackTrace();
                                            z2 = z;
                                            StartNaviBean startNaviBean = new StartNaviBean(poiBean, str3, str4);
                                            startNaviBean.setNaviType(i);
                                            startNaviBean.setRouteSelectRef(i2);
                                            startNaviBean.setFullSceneSwitch(z2);
                                            startNaviBean.setSpeechInfo(jSONObject);
                                            LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean.toString());
                                            doControlStart(startNaviBean);
                                        }
                                    }
                                } catch (JSONException e2) {
                                    e = e2;
                                }
                            } catch (JSONException e3) {
                                e = e3;
                                z = true;
                            }
                        } catch (JSONException e4) {
                            e = e4;
                            z = true;
                            i2 = 0;
                        }
                    } catch (JSONException e5) {
                        e = e5;
                        z = true;
                        i = 0;
                        i2 = 0;
                    }
                } catch (JSONException e6) {
                    e = e6;
                    z = true;
                    i = 0;
                    i2 = 0;
                    str4 = null;
                }
            } catch (JSONException e7) {
                e = e7;
                z = true;
                i = 0;
                i2 = 0;
                str3 = null;
                str4 = str3;
                e.printStackTrace();
                z2 = z;
                StartNaviBean startNaviBean2 = new StartNaviBean(poiBean, str3, str4);
                startNaviBean2.setNaviType(i);
                startNaviBean2.setRouteSelectRef(i2);
                startNaviBean2.setFullSceneSwitch(z2);
                startNaviBean2.setSpeechInfo(jSONObject);
                LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean2.toString());
                doControlStart(startNaviBean2);
            }
        } catch (JSONException e8) {
            e = e8;
            z = true;
            i = 0;
            i2 = 0;
            poiBean = null;
            str3 = null;
        }
        StartNaviBean startNaviBean22 = new StartNaviBean(poiBean, str3, str4);
        startNaviBean22.setNaviType(i);
        startNaviBean22.setRouteSelectRef(i2);
        startNaviBean22.setFullSceneSwitch(z2);
        startNaviBean22.setSpeechInfo(jSONObject);
        LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean22.toString());
        doControlStart(startNaviBean22);
    }

    private void doControlStart(StartNaviBean startNaviBean) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlStart(startNaviBean);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_SIMPLE)
    public void onControlSpeechSimple(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechSimple();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_DETAIL)
    public void onControlSpeechDetail(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechDetail();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_NORTH)
    public void onControlDisPlayNorth(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisPlayNorth();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_CAR)
    public void onControlDisPlayCar(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisPlayCar();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_3D)
    public void onControlDisplay3D(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay3D();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[LOOP:0: B:11:0x0027->B:13:0x002a, LOOP_START, PHI: r2 
      PHI: (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:10:0x0025, B:13:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[ORIG_RETURN, RETURN] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.CONTROL_VOL_ON)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onControlVolOn(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r5 = "mode"
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r0 = r4.mListenerList
            java.lang.Object[] r0 = r0.collectCallbacks()
            r1 = 1
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L20
            r3.<init>(r6)     // Catch: java.lang.Exception -> L20
            java.lang.String r6 = "pullUpNavi"
            boolean r1 = r3.optBoolean(r6, r2)     // Catch: java.lang.Exception -> L20
            boolean r6 = r3.has(r5)     // Catch: java.lang.Exception -> L20
            if (r6 == 0) goto L24
            int r5 = r3.optInt(r5)     // Catch: java.lang.Exception -> L20
            goto L25
        L20:
            r5 = move-exception
            r5.printStackTrace()
        L24:
            r5 = r2
        L25:
            if (r0 == 0) goto L34
        L27:
            int r6 = r0.length
            if (r2 >= r6) goto L34
            r6 = r0[r2]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r6 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r6
            r6.onControlVolOn(r1, r5)
            int r2 = r2 + 1
            goto L27
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onControlVolOn(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[LOOP:0: B:11:0x0027->B:13:0x002a, LOOP_START, PHI: r2 
      PHI: (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:10:0x0025, B:13:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[ORIG_RETURN, RETURN] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.CONTROL_VOL_OFF)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onControlVolOff(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r5 = "mode"
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r0 = r4.mListenerList
            java.lang.Object[] r0 = r0.collectCallbacks()
            r1 = 1
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L20
            r3.<init>(r6)     // Catch: java.lang.Exception -> L20
            java.lang.String r6 = "pullUpNavi"
            boolean r1 = r3.optBoolean(r6, r2)     // Catch: java.lang.Exception -> L20
            boolean r6 = r3.has(r5)     // Catch: java.lang.Exception -> L20
            if (r6 == 0) goto L24
            int r5 = r3.optInt(r5)     // Catch: java.lang.Exception -> L20
            goto L25
        L20:
            r5 = move-exception
            r5.printStackTrace()
        L24:
            r5 = r2
        L25:
            if (r0 == 0) goto L34
        L27:
            int r6 = r0.length
            if (r2 >= r6) goto L34
            r6 = r0[r2]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r6 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r6
            r6.onControlVolOff(r1, r5)
            int r2 = r2 + 1
            goto L27
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onControlVolOff(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = NaviEvent.ROUTE_NEARBY_SEARCH)
    public void onRouteNearbySearch(String str, String str2) {
        NearbySearchBean fromJson = NearbySearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onRouteNearbySearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.PARKING_SELECT)
    public void onParkingSelect(String str, String str2) {
        SelectParkingBean fromJson = SelectParkingBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onParkingSelect(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONFIRM_OK)
    public void onConfirmOk(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onConfirmOk();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONFIRM_CANCEL)
    public void onConfirmCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onConfirmCancel();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROUTE_SELECT)
    public void onRouteSelect(String str, String str2) {
        SelectRouteBean fromJson = SelectRouteBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onRouteSelect(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SELECT_PARKING_COUNT)
    public void onSelectParkingCount(String str, String str2) {
        SelectParkingBean fromJson = SelectParkingBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSelectParkingCount(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SELECT_ROUTE_COUNT)
    public void onSelectRouteCount(String str, String str2) {
        SelectRouteBean fromJson = SelectRouteBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSelectRouteCount(fromJson);
            }
        }
    }

    public void onDataControlDisplay3dTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplay3dTts();
            }
        }
    }

    public void onDataControlDisplayCarTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplayCarTts();
            }
        }
    }

    public void onDataControlDisplayNorthTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplayNorthTts();
            }
        }
    }

    public void onDriveAdvoidTrafficControl(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAdvoidTrafficControl();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.WAYPOINT_SEARCH)
    public void onWaypointSearch(String str, String str2) {
        WaypointSearchBean fromJson = WaypointSearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onWaypointSearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_WAYPOINT_START)
    public void onControlWaypointStart(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        PathBean fromJson = PathBean.fromJson(str2);
        LogUtils.d("NaviNode, pathBean =%s", fromJson == null ? "data is null" : fromJson.toString());
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlWaypointStart(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OPEN_SMALL_MAP)
    public void onControlOpenSmallMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOpenSmallMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE_SMALL_MAP)
    public void onControlCloseSmallMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCloseSmallMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OPEN_RIBBON_MAP)
    public void onControlOpenRibbonMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOpenRibbonMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE_RIBBON_MAP)
    public void onControlCloseRibbonMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCloseRibbonMap();
            }
        }
    }

    public void selectParking(String str) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isLocalSkill", true).put("intent", SELECT_PARKING_INTENT).put("isAsrModeOffline", false).put(WakeupResult.REASON_COMMAND, "native://navi.select.parking.count#command://navi.parking.select#command://navi.confirm.cancel").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, ACTIVE_VOICE_TASK, SELECT_PARKING_INTENT, str2);
    }

    @Deprecated
    public void selectRoute(String str) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isLocalSkill", true).put("intent", SELECT_ROUTE_INTENT).put("isAsrModeOffline", false).put(WakeupResult.REASON_COMMAND, "native://navi.select.route.count#command://navi.route.select#command://navi.confirm.cancel").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, ACTIVE_VOICE_TASK, SELECT_ROUTE_INTENT, str2);
    }

    public void selectRoute(List<RouteSelectBean> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
    }

    public void updateRouteSelect(List<RouteSelectBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        LogUtils.i("updateRouteSelect", "updateRouteSelect:" + listWidget.toString());
        SpeechClient.instance().getAgent().sendEvent(ContextEvent.WIDGET_LIST, listWidget.toString());
    }

    private SpeechWidget getRouteSelectWidget(List<RouteSelectBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        return listWidget;
    }

    public void stopSpeechDialog() {
        SpeechClient.instance().getWakeupEngine().stopDialog();
    }

    public void stopSpeechDialog(int i) {
        LogUtils.i(this, "stopDialog option: " + i);
        if (i == 0) {
            SpeechClient.instance().getWakeupEngine().stopDialog();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", "Navigation");
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startSpeechDialog() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", "Navigation");
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void directNavigation() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", "Navigation");
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_HISTORY)
    public void onControlHistory(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHistory();
            }
        }
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
            jSONObject.put("poiInfo", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z, String str2, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
            jSONObject.put("poiInfo", str2);
            jSONObject.put("isChargeRoute", z2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRouteToInfoFlow(List<RouteSelectBean> list, String str, boolean z) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (z) {
            listWidget.setWidgetId(str + BASE_ROUTE_WIDGET_ID);
        } else {
            listWidget.setWidgetId(str + ALL_ROUTE_WIDGET_ID);
        }
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        LogUtils.i("NaviNode", "syncRouteToInfoFlow:" + listWidget.toString());
        SpeechClient.instance().getAgent().sendEvent(ContextEvent.WIDGET_LIST, listWidget.toString());
    }

    @SpeechAnnotation(event = NaviEvent.SETTINGS_INFO_GET)
    public void onGetSettingsInfo(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onGetSettingsInfo();
            }
        }
    }

    public void postSettingsInfo(String str) {
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.SETTINGS_INFO_GET).setResult(str));
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_PARK_RECOMMEND_ON)
    public void onControlParkRecommendOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlParkRecommendOn();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_PARK_RECOMMEND_OFF)
    public void onControlParkRecommendOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlParkRecommendOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SCALE_LEVEL_SET)
    public void onSetScaleLevel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("level")) {
                int i = jSONObject.getInt("level");
                for (Object obj : collectCallbacks) {
                    ((NaviListener) obj).onSetScaleLevel(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.MOVE_NAV_METRE_SET)
    public void onSetOritentionMetre(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("metre") && jSONObject.has("oritention")) {
                double d = jSONObject.getDouble("metre");
                int i = jSONObject.getInt("oritention");
                for (Object obj : collectCallbacks) {
                    ((NaviListener) obj).onSetOritentionMetre(d, i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.ALERTS_PREFERENCE_SET)
    public void onAlertsPreferenceSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onAlertsPreferenceSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.AVOID_ROUTE_SET)
    public void onAvoidRouteSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onAvoidRouteSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_BETTER_ROUTE)
    public void onAutoRerouteBetterRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteBetterRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_ASK_FIRST)
    public void onAutoRerouteAskFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteAskFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_NEVER)
    public void onAutoRerouteNever(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteNever();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_SHOW_SET)
    public void onMapShowSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onMapShowSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_POI_DETAILS_FAVORITE_ADD)
    public void onPoiDetailsFavoriteAdd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiDetailsFavoriteAdd();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_POI_DETAILS_FAVORITE_DEL)
    public void onPoiDetailsFavoriteDel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiDetailsFavoriteDel();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SETTINGS_CLOSE)
    public void onControlSettingsCLose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSettingsClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_HISTORY_CLOSE)
    public void onControlHistoryCLose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHistoryClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.LIST_ITEM_SELECTED)
    public void onListItemSelected(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("index")) {
                int optInt = jSONObject.optInt("index");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onListItemSelected(optInt);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_GAODE_ACCOUNT_BING_PAGE_OPEN)
    public void onControlGaodeAccountPageOpen(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("pageId")) {
                int optInt = jSONObject.optInt("pageId");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onControlGaodeAccountPageOpen(optInt);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_SERVICE_OPEN)
    public void onControlChargeServiceOpen(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("stationId")) {
                String optString = jSONObject.optString("stationId");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onControlChargeServiceOpen(optString);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_STANDARD)
    public void onControlSpeechStandard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechStandard();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_CRUISE)
    public void onControlSpeechCruise(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechCruise();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_CRUISE_OFF)
    public void onControlSpeechCruiseOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechCruiseOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_CHARGING_ROUTE)
    public void onControlSmartChargingRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartChargingRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_CHARGING_ROUTE_OFF)
    public void onControlSmartChargingRouteOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartChargingRouteOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_RECOMMEND)
    public void onControlSmartRecommend(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartRecommend();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_RECOMMEND_OFF)
    public void onControlSmartRecommendOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartRecommendOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_CHARGE_LESS)
    public void onControlDriveChargeLess(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDriveChargeLess();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_CHARGE_LESS_OFF)
    public void onControlDriveChargeLessOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDriveChargeLessOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_HIGHROAD_FIRST)
    public void onControlHighroadFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHighroadFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_HIGHROAD_FIRST_OFF)
    public void onControlHighroadFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHighroadFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_TIME_FIRST)
    public void onControlTimeFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTimeFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_TIME_FIRST_OFF)
    public void onControlTimeFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTimeFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_COMMUTER_ROUTE_SHOW)
    public void onControlCommuterRouteShow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCommuterRouteShow();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_COMMUTER_ROUTE_SHOW_OFF)
    public void onControlCommuterRouteShowOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCommuterRouteShowOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_2D_HEAD_UP)
    public void onControlDisplay2DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay2DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_3D_HEAD_UP)
    public void onControlDisplay3DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay3DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_DASHBOARD_2D_HEAD_UP)
    public void onControlDashboardDisplay2DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDashboardDisplay2DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_DASHBOARD_3D_HEAD_UP)
    public void onControlDashboardDisplay3DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDashboardDisplay3DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_REALTIME_TRAFFIC_OFF)
    public void onControlRealtimeTrafficOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRealtimeTrafficOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_REALTIME_TRAFFIC)
    public void onControlRealtimeTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRealtimeTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_TRAFFIC_INCIDENT)
    public void onControlTrafficIncident(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTrafficIncident();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_TRAFFIC_INCIDENT_OFF)
    public void onControlTrafficIncidentOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTrafficIncidentOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_BRIDGE_3D_MAP)
    public void onControlBridge3DMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlBridge3DMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_BRIDGE_3D_MAP_OFF)
    public void onControlBridge3DMapOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlBridge3DMapOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH)
    public void onNaviSearch(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (collectCallbacks != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject3 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject3.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            jSONObject2.put(next, jSONObject3.get(next));
                        }
                        jSONObject.put(next, jSONObject3.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearch:searchInfo:" + jSONObject2 + ",speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearch(jSONObject2.toString(), jSONObject.toString());
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH_ON_WAY)
    public void onNaviSearchOnWay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (collectCallbacks != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject3 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject3.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            jSONObject2.put(next, jSONObject3.get(next));
                        }
                        jSONObject.put(next, jSONObject3.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearchOnWay:searchInfo:" + jSONObject2 + ",speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearchOnWay(jSONObject2.toString(), jSONObject.toString());
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH_FILTER)
    public void onNaviSearchFilter(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        if (collectCallbacks != null) {
            String[] strArr = null;
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            if ("idList".equals(next)) {
                                JSONArray jSONArray = jSONObject2.getJSONArray("idList");
                                strArr = new String[jSONArray.length()];
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    strArr[i] = (String) jSONArray.get(i);
                                }
                            }
                        }
                        jSONObject.put(next, jSONObject2.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearchFilter:speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearchFilter(strArr, jSONObject.toString());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071 A[Catch: Exception -> 0x0124, TryCatch #0 {Exception -> 0x0124, blocks: (B:3:0x000d, B:6:0x0024, B:8:0x0033, B:10:0x0039, B:12:0x003f, B:13:0x004a, B:15:0x0050, B:16:0x005b, B:18:0x0061, B:20:0x006b, B:22:0x0071, B:24:0x0080, B:26:0x0086, B:28:0x008c, B:30:0x009f, B:32:0x00a5, B:33:0x00b0, B:35:0x00b6, B:37:0x00c3, B:38:0x00ca, B:29:0x0098, B:47:0x00f0, B:48:0x00f7, B:40:0x00d0, B:42:0x00d9, B:44:0x00df, B:45:0x00e4), top: B:51:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f0 A[Catch: Exception -> 0x0124, TryCatch #0 {Exception -> 0x0124, blocks: (B:3:0x000d, B:6:0x0024, B:8:0x0033, B:10:0x0039, B:12:0x003f, B:13:0x004a, B:15:0x0050, B:16:0x005b, B:18:0x0061, B:20:0x006b, B:22:0x0071, B:24:0x0080, B:26:0x0086, B:28:0x008c, B:30:0x009f, B:32:0x00a5, B:33:0x00b0, B:35:0x00b6, B:37:0x00c3, B:38:0x00ca, B:29:0x0098, B:47:0x00f0, B:48:0x00f7, B:40:0x00d0, B:42:0x00d9, B:44:0x00df, B:45:0x00e4), top: B:51:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void syncPoiList(java.util.List<com.xiaopeng.speech.protocol.node.navi.bean.XpPoi> r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            r9 = this;
            java.lang.String r0 = "pageSize"
            java.lang.String r1 = "totalPage"
            java.lang.String r2 = "dialogueInfo"
            java.lang.String r3 = "totalSize"
            java.lang.String r4 = "triggerApi"
            com.xiaopeng.speech.protocol.bean.XpListWidget r5 = new com.xiaopeng.speech.protocol.bean.XpListWidget     // Catch: java.lang.Exception -> L124
            r5.<init>()     // Catch: java.lang.Exception -> L124
            com.xiaopeng.speech.protocol.bean.XpListTypeEnum r6 = com.xiaopeng.speech.protocol.bean.XpListTypeEnum.POI_LIST     // Catch: java.lang.Exception -> L124
            r5.setListType(r6)     // Catch: java.lang.Exception -> L124
            r5.setContents(r10)     // Catch: java.lang.Exception -> L124
            boolean r6 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> L124
            r7 = -1
            java.lang.String r8 = "soundArea"
            if (r6 != 0) goto L6a
            com.google.gson.JsonParser r6 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L124
            r6.<init>()     // Catch: java.lang.Exception -> L124
            com.google.gson.JsonElement r12 = r6.parse(r12)     // Catch: java.lang.Exception -> L124
            com.google.gson.JsonObject r12 = r12.getAsJsonObject()     // Catch: java.lang.Exception -> L124
            if (r12 == 0) goto L6a
            int r6 = r12.size()     // Catch: java.lang.Exception -> L124
            if (r6 <= 0) goto L6a
            boolean r6 = r12.has(r4)     // Catch: java.lang.Exception -> L124
            if (r6 == 0) goto L4a
            com.google.gson.JsonElement r4 = r12.get(r4)     // Catch: java.lang.Exception -> L124
            java.lang.String r4 = r4.getAsString()     // Catch: java.lang.Exception -> L124
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L124
        L4a:
            boolean r4 = r12.has(r2)     // Catch: java.lang.Exception -> L124
            if (r4 == 0) goto L5b
            com.google.gson.JsonElement r2 = r12.get(r2)     // Catch: java.lang.Exception -> L124
            com.google.gson.JsonObject r2 = r2.getAsJsonObject()     // Catch: java.lang.Exception -> L124
            r5.setDialogueInfo(r2)     // Catch: java.lang.Exception -> L124
        L5b:
            boolean r2 = r12.has(r8)     // Catch: java.lang.Exception -> L124
            if (r2 == 0) goto L6a
            com.google.gson.JsonElement r12 = r12.get(r8)     // Catch: java.lang.Exception -> L124
            int r12 = r12.getAsInt()     // Catch: java.lang.Exception -> L124
            goto L6b
        L6a:
            r12 = r7
        L6b:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch: java.lang.Exception -> L124
            if (r2 != 0) goto Lce
            com.google.gson.JsonParser r2 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L124
            r2.<init>()     // Catch: java.lang.Exception -> L124
            com.google.gson.JsonElement r13 = r2.parse(r13)     // Catch: java.lang.Exception -> L124
            com.google.gson.JsonObject r13 = r13.getAsJsonObject()     // Catch: java.lang.Exception -> L124
            if (r13 == 0) goto Lee
            int r2 = r13.size()     // Catch: java.lang.Exception -> L124
            if (r2 <= 0) goto Lee
            boolean r2 = r13.has(r3)     // Catch: java.lang.Exception -> L124
            if (r2 == 0) goto L98
            com.google.gson.JsonElement r10 = r13.get(r3)     // Catch: java.lang.Exception -> L124
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L124
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L124
            goto L9f
        L98:
            int r10 = r10.size()     // Catch: java.lang.Exception -> L124
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L124
        L9f:
            boolean r10 = r13.has(r1)     // Catch: java.lang.Exception -> L124
            if (r10 == 0) goto Lb0
            com.google.gson.JsonElement r10 = r13.get(r1)     // Catch: java.lang.Exception -> L124
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L124
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L124
        Lb0:
            boolean r10 = r13.has(r0)     // Catch: java.lang.Exception -> L124
            if (r10 == 0) goto Lc1
            com.google.gson.JsonElement r10 = r13.get(r0)     // Catch: java.lang.Exception -> L124
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L124
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L124
        Lc1:
            if (r12 == r7) goto Lca
            java.lang.Integer r10 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Exception -> L124
            r13.addProperty(r8, r10)     // Catch: java.lang.Exception -> L124
        Lca:
            r5.setExtra(r13)     // Catch: java.lang.Exception -> L124
            goto Lee
        Lce:
            if (r10 == 0) goto Ld7
            int r10 = r10.size()     // Catch: java.lang.Exception -> L124
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L124
        Ld7:
            if (r12 == r7) goto Lee
            com.google.gson.JsonObject r10 = r5.getExtra()     // Catch: java.lang.Exception -> L124
            if (r10 != 0) goto Le4
            com.google.gson.JsonObject r10 = new com.google.gson.JsonObject     // Catch: java.lang.Exception -> L124
            r10.<init>()     // Catch: java.lang.Exception -> L124
        Le4:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Exception -> L124
            r10.addProperty(r8, r12)     // Catch: java.lang.Exception -> L124
            r5.setExtra(r10)     // Catch: java.lang.Exception -> L124
        Lee:
            if (r11 == 0) goto Lf7
            java.lang.String r10 = r5.getTriggerApi()     // Catch: java.lang.Exception -> L124
            android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L124
        Lf7:
            com.google.gson.Gson r10 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L124
            r10.<init>()     // Catch: java.lang.Exception -> L124
            java.lang.String r10 = r10.toJson(r5)     // Catch: java.lang.Exception -> L124
            java.lang.String r11 = "NaviNode"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L124
            r12.<init>()     // Catch: java.lang.Exception -> L124
            java.lang.String r13 = "syncPoiList:"
            r12.append(r13)     // Catch: java.lang.Exception -> L124
            r12.append(r10)     // Catch: java.lang.Exception -> L124
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> L124
            com.xiaopeng.speech.common.util.LogUtils.i(r11, r12)     // Catch: java.lang.Exception -> L124
            com.xiaopeng.speech.SpeechClient r11 = com.xiaopeng.speech.SpeechClient.instance()     // Catch: java.lang.Exception -> L124
            com.xiaopeng.speech.proxy.AgentProxy r11 = r11.getAgent()     // Catch: java.lang.Exception -> L124
            java.lang.String r12 = "list.upload"
            r11.triggerEvent(r12, r10)     // Catch: java.lang.Exception -> L124
        L124:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.syncPoiList(java.util.List, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0077 A[Catch: Exception -> 0x0121, TryCatch #0 {Exception -> 0x0121, blocks: (B:3:0x000d, B:6:0x0024, B:8:0x0033, B:10:0x0039, B:12:0x003f, B:14:0x0050, B:16:0x0056, B:17:0x0061, B:19:0x0067, B:21:0x0071, B:23:0x0077, B:25:0x0086, B:27:0x008c, B:29:0x0092, B:31:0x00a5, B:33:0x00ab, B:34:0x00b6, B:36:0x00bc, B:38:0x00c9, B:39:0x00d0, B:30:0x009e, B:47:0x00f4, B:41:0x00d6, B:43:0x00df, B:45:0x00e5, B:46:0x00ea, B:13:0x004b), top: B:50:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void syncRouteList(java.util.List<com.xiaopeng.speech.protocol.node.navi.bean.XpRoute> r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.String r0 = "pageSize"
            java.lang.String r1 = "totalPage"
            java.lang.String r2 = "dialogueInfo"
            java.lang.String r3 = "totalSize"
            java.lang.String r4 = "triggerApi"
            com.xiaopeng.speech.protocol.bean.XpListWidget r5 = new com.xiaopeng.speech.protocol.bean.XpListWidget     // Catch: java.lang.Exception -> L121
            r5.<init>()     // Catch: java.lang.Exception -> L121
            com.xiaopeng.speech.protocol.bean.XpListTypeEnum r6 = com.xiaopeng.speech.protocol.bean.XpListTypeEnum.ROUTE_LIST     // Catch: java.lang.Exception -> L121
            r5.setListType(r6)     // Catch: java.lang.Exception -> L121
            r5.setContents(r10)     // Catch: java.lang.Exception -> L121
            boolean r6 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Exception -> L121
            r7 = -1
            java.lang.String r8 = "soundArea"
            if (r6 != 0) goto L70
            com.google.gson.JsonParser r6 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L121
            r6.<init>()     // Catch: java.lang.Exception -> L121
            com.google.gson.JsonElement r11 = r6.parse(r11)     // Catch: java.lang.Exception -> L121
            com.google.gson.JsonObject r11 = r11.getAsJsonObject()     // Catch: java.lang.Exception -> L121
            if (r11 == 0) goto L70
            int r6 = r11.size()     // Catch: java.lang.Exception -> L121
            if (r6 <= 0) goto L70
            boolean r6 = r11.has(r4)     // Catch: java.lang.Exception -> L121
            if (r6 == 0) goto L4b
            com.google.gson.JsonElement r4 = r11.get(r4)     // Catch: java.lang.Exception -> L121
            java.lang.String r4 = r4.getAsString()     // Catch: java.lang.Exception -> L121
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L121
            goto L50
        L4b:
            java.lang.String r4 = "NavigationAutoRoute"
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L121
        L50:
            boolean r4 = r11.has(r2)     // Catch: java.lang.Exception -> L121
            if (r4 == 0) goto L61
            com.google.gson.JsonElement r2 = r11.get(r2)     // Catch: java.lang.Exception -> L121
            com.google.gson.JsonObject r2 = r2.getAsJsonObject()     // Catch: java.lang.Exception -> L121
            r5.setDialogueInfo(r2)     // Catch: java.lang.Exception -> L121
        L61:
            boolean r2 = r11.has(r8)     // Catch: java.lang.Exception -> L121
            if (r2 == 0) goto L70
            com.google.gson.JsonElement r11 = r11.get(r8)     // Catch: java.lang.Exception -> L121
            int r11 = r11.getAsInt()     // Catch: java.lang.Exception -> L121
            goto L71
        L70:
            r11 = r7
        L71:
            boolean r2 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> L121
            if (r2 != 0) goto Ld4
            com.google.gson.JsonParser r2 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L121
            r2.<init>()     // Catch: java.lang.Exception -> L121
            com.google.gson.JsonElement r12 = r2.parse(r12)     // Catch: java.lang.Exception -> L121
            com.google.gson.JsonObject r12 = r12.getAsJsonObject()     // Catch: java.lang.Exception -> L121
            if (r12 == 0) goto Lf4
            int r2 = r12.size()     // Catch: java.lang.Exception -> L121
            if (r2 <= 0) goto Lf4
            boolean r2 = r12.has(r3)     // Catch: java.lang.Exception -> L121
            if (r2 == 0) goto L9e
            com.google.gson.JsonElement r10 = r12.get(r3)     // Catch: java.lang.Exception -> L121
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L121
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L121
            goto La5
        L9e:
            int r10 = r10.size()     // Catch: java.lang.Exception -> L121
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L121
        La5:
            boolean r10 = r12.has(r1)     // Catch: java.lang.Exception -> L121
            if (r10 == 0) goto Lb6
            com.google.gson.JsonElement r10 = r12.get(r1)     // Catch: java.lang.Exception -> L121
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L121
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L121
        Lb6:
            boolean r10 = r12.has(r0)     // Catch: java.lang.Exception -> L121
            if (r10 == 0) goto Lc7
            com.google.gson.JsonElement r10 = r12.get(r0)     // Catch: java.lang.Exception -> L121
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L121
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L121
        Lc7:
            if (r11 == r7) goto Ld0
            java.lang.Integer r10 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Exception -> L121
            r12.addProperty(r8, r10)     // Catch: java.lang.Exception -> L121
        Ld0:
            r5.setExtra(r12)     // Catch: java.lang.Exception -> L121
            goto Lf4
        Ld4:
            if (r10 == 0) goto Ldd
            int r10 = r10.size()     // Catch: java.lang.Exception -> L121
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L121
        Ldd:
            if (r11 == r7) goto Lf4
            com.google.gson.JsonObject r10 = r5.getExtra()     // Catch: java.lang.Exception -> L121
            if (r10 != 0) goto Lea
            com.google.gson.JsonObject r10 = new com.google.gson.JsonObject     // Catch: java.lang.Exception -> L121
            r10.<init>()     // Catch: java.lang.Exception -> L121
        Lea:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Exception -> L121
            r10.addProperty(r8, r11)     // Catch: java.lang.Exception -> L121
            r5.setExtra(r10)     // Catch: java.lang.Exception -> L121
        Lf4:
            com.google.gson.Gson r10 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L121
            r10.<init>()     // Catch: java.lang.Exception -> L121
            java.lang.String r10 = r10.toJson(r5)     // Catch: java.lang.Exception -> L121
            java.lang.String r11 = "NaviNode"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L121
            r12.<init>()     // Catch: java.lang.Exception -> L121
            java.lang.String r0 = "syncRouteList:"
            r12.append(r0)     // Catch: java.lang.Exception -> L121
            r12.append(r10)     // Catch: java.lang.Exception -> L121
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> L121
            com.xiaopeng.speech.common.util.LogUtils.i(r11, r12)     // Catch: java.lang.Exception -> L121
            com.xiaopeng.speech.SpeechClient r11 = com.xiaopeng.speech.SpeechClient.instance()     // Catch: java.lang.Exception -> L121
            com.xiaopeng.speech.proxy.AgentProxy r11 = r11.getAgent()     // Catch: java.lang.Exception -> L121
            java.lang.String r12 = "list.upload"
            r11.triggerEvent(r12, r10)     // Catch: java.lang.Exception -> L121
        L121:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.syncRouteList(java.util.List, java.lang.String, java.lang.String):void");
    }

    public void onListDisappear(String str, String str2, String str3) {
        String xpListTypeEnum;
        try {
            JSONObject jSONObject = new JSONObject();
            if ("poi".equals(str)) {
                xpListTypeEnum = XpListTypeEnum.POI_LIST.toString();
            } else {
                xpListTypeEnum = XpListTypeEnum.ROUTE_LIST.toString();
            }
            jSONObject.put("listType", xpListTypeEnum);
            jSONObject.put("speechInfo", str2);
            jSONObject.put("extra", str3);
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.LIST_DISAPPEAR, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    public void subscribeMultiTask(String str) {
        SpeechClient.instance().getWakeupEngine().subscribeMultiTask(str, this.binder);
    }
}
