package com.xiaopeng.speech.protocol.query.navi;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryNaviEvent;
import com.xiaopeng.speech.protocol.node.navi.bean.AddressBean;
/* loaded from: classes2.dex */
public class MapQuery extends SpeechQuery<IMapQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_CRUISE)
    public boolean isCruise(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isCruise();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_EXPLORE)
    public boolean isExplorePath(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isExplorePath();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_NAVIGATION)
    public boolean isNavigation(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isNavigation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.GET_ADDRESS)
    public String getAddress(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getCommonAddress(AddressBean.fromJson(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.GET_NAVIGATION_INFO)
    public String getNavigationInfo(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getNavigationInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_ZOOMIN_MAX)
    public boolean isZoominMax(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isZoominMax();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_ZOOMOUT_MIN)
    public boolean isZoomoutMax(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isZoomoutMin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_CALCULATE_PATH)
    public boolean isCalculatePath(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isCalculatePath();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.REPLY_FAVORITE_OPEN_STATUS)
    public int getFavoriteOpenStatus(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getFavoriteOpenStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.REPLY_OPEN_CONTROLS_STATUS)
    public int getOpenControlStatus(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getOpenControlStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.REPLY_MAIN_ROAD_STATUS)
    public int getSwitchMainRoadStatus(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getSwitchMainRoadStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.REPLY_SIDE_ROAD_STATUS)
    public int getSwitchSideRoadStatus(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getSwitchSideRoadStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.SCALE_CURRENT_LEVEL)
    public int getCurrentScaleLevel(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getCurrentScaleLevel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.GET_POI_DETAILS_FAVORITE_STATUS)
    public int getPoiDetailsFavoriteStatus(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).getPoiDetailsFavoriteStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryNaviEvent.IS_SR_MAP)
    public boolean isSRMap(String str, String str2) {
        return ((IMapQueryCaller) this.mQueryCaller).isSRMap();
    }
}
