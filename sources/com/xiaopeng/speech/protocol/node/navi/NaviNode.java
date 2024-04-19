package com.xiaopeng.speech.protocol.node.navi;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.actorapi.ResultActor;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.FeedUIEvent;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.bean.FeedListUIValue;
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
import com.xiaopeng.xuimanager.userscenario.UserScenarioManager;
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
        textWidget.setText(z ? UserScenarioManager.RET_SUCCESS : "fail");
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

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.ADDRESS_SET)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAddressSet(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            com.xiaopeng.speech.protocol.node.navi.bean.AddressBean r9 = new com.xiaopeng.speech.protocol.node.navi.bean.AddressBean
            r9.<init>()
            r0 = 0
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L40
            r2.<init>(r10)     // Catch: org.json.JSONException -> L40
            java.lang.String r10 = "addressType"
            java.lang.String r10 = r2.optString(r10)     // Catch: org.json.JSONException -> L40
            r9.setAddressType(r10)     // Catch: org.json.JSONException -> L40
            java.lang.String r10 = "poi"
            java.lang.String r10 = r2.optString(r10)     // Catch: org.json.JSONException -> L40
            com.xiaopeng.speech.protocol.node.navi.bean.PoiBean r10 = com.xiaopeng.speech.protocol.node.navi.bean.PoiBean.fromJson(r10)     // Catch: org.json.JSONException -> L40
            java.lang.String r3 = "pref"
            java.lang.String r3 = r2.optString(r3)     // Catch: org.json.JSONException -> L3d
            java.lang.String r4 = "type"
            java.lang.String r0 = r2.optString(r4)     // Catch: org.json.JSONException -> L3b
            java.lang.String r4 = "naviType"
            int r4 = r2.optInt(r4)     // Catch: org.json.JSONException -> L3b
            java.lang.String r5 = "routeSelectRef"
            int r2 = r2.optInt(r5)     // Catch: org.json.JSONException -> L39
            goto L48
        L39:
            r2 = move-exception
            goto L44
        L3b:
            r2 = move-exception
            goto L43
        L3d:
            r2 = move-exception
            r3 = r0
            goto L43
        L40:
            r2 = move-exception
            r10 = r0
            r3 = r10
        L43:
            r4 = r1
        L44:
            r2.printStackTrace()
            r2 = r1
        L48:
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r5 = r8.mListenerList
            java.lang.Object[] r5 = r5.collectCallbacks()
            if (r5 == 0) goto L5e
            r6 = r1
        L51:
            int r7 = r5.length
            if (r6 >= r7) goto L5e
            r7 = r5[r6]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r7 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r7
            r7.onAddressSet(r9, r10)
            int r6 = r6 + 1
            goto L51
        L5e:
            boolean r9 = r8.mAddressPendingRoute
            if (r9 == 0) goto L70
            com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean r9 = new com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean
            r9.<init>(r10, r3, r0)
            r9.setNaviType(r4)
            r9.setRouteSelectRef(r2)
            r8.doControlStart(r9)
        L70:
            r8.mAddressPendingRoute = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onAddressSet(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_START)
    public void onControlStart(String str, String str2) {
        int i;
        PoiBean poiBean;
        String str3;
        int i2;
        String str4 = null;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            poiBean = PoiBean.fromJson(jSONObject.optString("poi"));
            try {
                str3 = jSONObject.optString("pref");
                try {
                    str4 = jSONObject.optString("type");
                    i = jSONObject.optInt("naviType");
                    try {
                        i2 = jSONObject.optInt("routeSelectRef");
                    } catch (JSONException e) {
                        e = e;
                        e.printStackTrace();
                        i2 = 0;
                        StartNaviBean startNaviBean = new StartNaviBean(poiBean, str3, str4);
                        startNaviBean.setNaviType(i);
                        startNaviBean.setRouteSelectRef(i2);
                        LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean.toString());
                        doControlStart(startNaviBean);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    i = 0;
                }
            } catch (JSONException e3) {
                e = e3;
                i = 0;
                str3 = null;
            }
        } catch (JSONException e4) {
            e = e4;
            i = 0;
            poiBean = null;
            str3 = null;
        }
        StartNaviBean startNaviBean2 = new StartNaviBean(poiBean, str3, str4);
        startNaviBean2.setNaviType(i);
        startNaviBean2.setRouteSelectRef(i2);
        LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean2.toString());
        doControlStart(startNaviBean2);
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
        } else {
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.SCRIPT_QUIT, null);
        }
    }

    public void startSpeechDialog() {
        SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, null);
    }

    public void directNavigation() {
        SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, null);
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
}
