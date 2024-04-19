package com.xiaopeng.speech.protocol.query.music;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryMusicEvent;
/* loaded from: classes2.dex */
public class MusicQuery_Processor implements IQueryProcessor {
    private MusicQuery mTarget;

    public MusicQuery_Processor(MusicQuery musicQuery) {
        this.mTarget = musicQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1560481235:
                if (str.equals(QueryMusicEvent.IS_KUGOU_AUTHED)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1316092175:
                if (str.equals(QueryMusicEvent.GET_INFO_QUERY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -697358738:
                if (str.equals(QueryMusicEvent.IS_COLLECT_EMPTY)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -565545210:
                if (str.equals(QueryMusicEvent.MUSIC_GET_USB_STATE)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -481069680:
                if (str.equals(QueryMusicEvent.IS_CAN_COLLECTED)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -289541544:
                if (str.equals(QueryMusicEvent.IS_HISTORY_EMPTY)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 196105311:
                if (str.equals(QueryMusicEvent.MUSIC_ACCOUNT_LOGIN)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 291286140:
                if (str.equals(QueryMusicEvent.IS_XIMALAYA_ACCOUNT_LOGIN)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 506081708:
                if (str.equals(QueryMusicEvent.IS_PLAY_SIMILAR)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1084784151:
                if (str.equals(QueryMusicEvent.GET_MUSIC_PLAYTYPE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1356318952:
                if (str.equals(QueryMusicEvent.IS_BT_CONNECTED)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1377565924:
                if (str.equals(QueryMusicEvent.GET_INFO_ARTIST)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1429715250:
                if (str.equals(QueryMusicEvent.GET_INFO_ALBUM)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1447189787:
                if (str.equals(QueryMusicEvent.GET_INFO_TITLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1732874385:
                if (str.equals(QueryMusicEvent.IS_CAN_OPEN_QUALITY_PAGE)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 2071296723:
                if (str.equals(QueryMusicEvent.IS_PLAYING)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2146212582:
                if (str.equals(QueryMusicEvent.IS_HAS_BLUETOOTH_MUSICLIST)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return this.mTarget.getPlayInfo(str, str2);
            case 1:
                return this.mTarget.getInfoTite(str, str2);
            case 2:
                return this.mTarget.getInfoArtist(str, str2);
            case 3:
                return this.mTarget.getInfoAlbum(str, str2);
            case 4:
                return Integer.valueOf(this.mTarget.getPlayType(str, str2));
            case 5:
                return Boolean.valueOf(this.mTarget.isPlaying(str, str2));
            case 6:
                return Boolean.valueOf(this.mTarget.hasBluetoothMusicList(str, str2));
            case 7:
                return Boolean.valueOf(this.mTarget.isHistoryEmpty(str, str2));
            case '\b':
                return Boolean.valueOf(this.mTarget.isPlaySimilar(str, str2));
            case '\t':
                return Boolean.valueOf(this.mTarget.isCollectListEmpty(str, str2));
            case '\n':
                return Boolean.valueOf(this.mTarget.isCanCollected(str, str2));
            case 11:
                return Boolean.valueOf(this.mTarget.isBtConnected(str, str2));
            case '\f':
                return Boolean.valueOf(this.mTarget.isKuGouAuthed(str, str2));
            case '\r':
                return Integer.valueOf(this.mTarget.getUsbState(str, str2));
            case 14:
                return Boolean.valueOf(this.mTarget.isMusicAccountLogin(str, str2));
            case 15:
                return Boolean.valueOf(this.mTarget.isQualityPageOpend(str, str2));
            case 16:
                return Boolean.valueOf(this.mTarget.isXimalayaAccountLogin(str, str2));
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryMusicEvent.GET_INFO_QUERY, QueryMusicEvent.GET_INFO_TITLE, QueryMusicEvent.GET_INFO_ARTIST, QueryMusicEvent.GET_INFO_ALBUM, QueryMusicEvent.GET_MUSIC_PLAYTYPE, QueryMusicEvent.IS_PLAYING, QueryMusicEvent.IS_HAS_BLUETOOTH_MUSICLIST, QueryMusicEvent.IS_HISTORY_EMPTY, QueryMusicEvent.IS_PLAY_SIMILAR, QueryMusicEvent.IS_COLLECT_EMPTY, QueryMusicEvent.IS_CAN_COLLECTED, QueryMusicEvent.IS_BT_CONNECTED, QueryMusicEvent.IS_KUGOU_AUTHED, QueryMusicEvent.MUSIC_GET_USB_STATE, QueryMusicEvent.MUSIC_ACCOUNT_LOGIN, QueryMusicEvent.IS_CAN_OPEN_QUALITY_PAGE, QueryMusicEvent.IS_XIMALAYA_ACCOUNT_LOGIN};
    }
}
