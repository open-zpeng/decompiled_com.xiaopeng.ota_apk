package com.xiaopeng.ota.helper;

import android.content.Context;
import android.text.TextUtils;
import com.xiaopeng.ota.Config;
import com.xiaopeng.ota.Constants;
import com.xiaopeng.ota.LocaleConfig;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.bean.UpgradePopupConfig;
import com.xiaopeng.ota.presenter.update.bean.Campaign;
import com.xiaopeng.ota.sdk.exception.NotFoundException;
import com.xiaopeng.ota.utils.ResourceUtils;
import com.xiaopeng.ota.utils.VersionUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes2.dex */
public class ConfigHelper {
    public static final String SPECIAL_CHAR = "#";
    private static final String TARGET_CHAR = "%s";

    public static String formatWithCampaign(String str, Campaign campaign) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int indexOf = str.indexOf(36, 0);
            if (indexOf >= 0) {
                int indexOf2 = str.indexOf(123, indexOf);
                int indexOf3 = str.indexOf(125, indexOf);
                if (indexOf2 >= indexOf && indexOf3 >= indexOf) {
                    sb.append(str.substring(0, indexOf2 - 1));
                    String substring = str.substring(indexOf2 + 1, indexOf3);
                    char c = 65535;
                    switch (substring.hashCode()) {
                        case -1989150940:
                            if (substring.equals("SERVICE_PHONE")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -521049336:
                            if (substring.equals("FULL_VERSION")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 457917572:
                            if (substring.equals("ESTIMATE_COST")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1069590712:
                            if (substring.equals("VERSION")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 1624159228:
                            if (substring.equals("AUTO_SCHEDULE_HOUR")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1881291308:
                            if (substring.equals("AUTO_SCHEDULE_MINUTE")) {
                                c = 5;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        sb.append(VersionUtils.getSimpleVersion(campaign.getReleaseVersion()));
                    } else if (c == 1) {
                        sb.append(campaign.getReleaseVersion());
                    } else if (c == 2) {
                        sb.append(getString(Constants.ConfigKey.DEFAULT_SERVICE_PHONE));
                    } else if (c == 3) {
                        sb.append(campaign.getEstimateCost());
                    } else if (c == 4) {
                        sb.append(getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME).split(":")[0]);
                    } else if (c == 5) {
                        String[] split = getString(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME).split(":");
                        if (split.length > 1) {
                            sb.append(split[1]);
                        }
                    }
                    str = str.substring(indexOf3 + 1);
                }
            }
        }
        sb.append(str);
        return sb.toString();
    }

    public static String getFormattedWithCampaign(String str, Campaign campaign) {
        return formatWithCampaign(getString(str), campaign);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getString(String str) {
        char c;
        Context context = OTAManager.getContext();
        switch (str.hashCode()) {
            case -2091754106:
                if (str.equals(Constants.ConfigKey.DEFAULT_SERVICE_PHONE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -2071939098:
                if (str.equals(Constants.ConfigKey.UPGRADE_TIME_FORMAT)) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case -2027328197:
                if (str.equals(Constants.ConfigKey.SCHEDULE_BUTTON_CONFIRM)) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case -2012191654:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_BUTTON_CANCEL_TEXT)) {
                    c = 145;
                    break;
                }
                c = 65535;
                break;
            case -1997809615:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CUSTOMER_SERVICE_CONTENT)) {
                    c = 152;
                    break;
                }
                c = 65535;
                break;
            case -1933105077:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_VIEW_VERSION_DETAIL)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -1910609881:
                if (str.equals(Constants.ConfigKey.BUTTON_CANCEL)) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case -1878109698:
                if (str.equals(Constants.ConfigKey.BUTTON_DETAIL)) {
                    c = 136;
                    break;
                }
                c = 65535;
                break;
            case -1866533928:
                if (str.equals(Constants.ConfigKey.UPGRADE_MAIN_TIP)) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case -1778864444:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_AUTO_TTS)) {
                    c = 130;
                    break;
                }
                c = 65535;
                break;
            case -1770442425:
                if (str.equals(Constants.ConfigKey.DIALOG_TITLE_EXPIRED)) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case -1740539840:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_SCHEDULE_TIME)) {
                    c = 146;
                    break;
                }
                c = 65535;
                break;
            case -1722776335:
                if (str.equals(Constants.ConfigKey.BUTTON_ALL_RIGHT)) {
                    c = 134;
                    break;
                }
                c = 65535;
                break;
            case -1709144120:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TITLE)) {
                    c = 'g';
                    break;
                }
                c = 65535;
                break;
            case -1699614019:
                if (str.equals(Constants.ConfigKey.DIALOG_TITLE_CONDITION)) {
                    c = Matrix.MATRIX_TYPE_RANDOM_LT;
                    break;
                }
                c = 65535;
                break;
            case -1660006449:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_CONTENT)) {
                    c = 132;
                    break;
                }
                c = 65535;
                break;
            case -1658448526:
                if (str.equals(Constants.ConfigKey.SCHEDULE_SUB_TIPS1)) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case -1658448525:
                if (str.equals(Constants.ConfigKey.SCHEDULE_SUB_TIPS2)) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case -1658448524:
                if (str.equals(Constants.ConfigKey.SCHEDULE_SUB_TIPS3)) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case -1635776033:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_CONTENT)) {
                    c = 's';
                    break;
                }
                c = 65535;
                break;
            case -1599708072:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_CONTENT_RETRYABLE)) {
                    c = 'l';
                    break;
                }
                c = 65535;
                break;
            case -1587539258:
                if (str.equals(Constants.ConfigKey.TEXT_MORE_INFO)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1585692071:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TTS)) {
                    c = 'u';
                    break;
                }
                c = 65535;
                break;
            case -1554406157:
                if (str.equals(Constants.ConfigKey.NEW_V2_BUTTON_UPGRADE)) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case -1496764919:
                if (str.equals(Constants.ConfigKey.BUTTON_OK)) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case -1433938266:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_CONTENT_REMOTE)) {
                    c = 't';
                    break;
                }
                c = 65535;
                break;
            case -1362063995:
                if (str.equals(Constants.ConfigKey.NEW_BUTTON_UPGRADE_NOW)) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case -1348821259:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_POSITIVE_BUTTON)) {
                    c = 155;
                    break;
                }
                c = 65535;
                break;
            case -1345291132:
                if (str.equals(Constants.ConfigKey.DIALOG_TITLE_SOTA_UPGRADE)) {
                    c = ']';
                    break;
                }
                c = 65535;
                break;
            case -1339538861:
                if (str.equals("SERVER_URL")) {
                    c = 140;
                    break;
                }
                c = 65535;
                break;
            case -1329127737:
                if (str.equals(Constants.ConfigKey.MAIN_SCHEDULE_TIME_FORMAT)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1274426037:
                if (str.equals(Constants.ConfigKey.TEXT_VERSION_PREFIX)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1259041195:
                if (str.equals(Constants.ConfigKey.TITLE_INFO)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1258934432:
                if (str.equals(Constants.ConfigKey.TITLE_MAIN)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1234428466:
                if (str.equals(Constants.ConfigKey.UPGRADE_MAIN_TIP_TEMP_PARK)) {
                    c = '<';
                    break;
                }
                c = 65535;
                break;
            case -1165260435:
                if (str.equals(Constants.ConfigKey.TITLE_HISTORY)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1131444034:
                if (str.equals(Constants.ConfigKey.TITLE_SCHEDULE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1130658382:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_TTS)) {
                    c = 127;
                    break;
                }
                c = 65535;
                break;
            case -1106376785:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_TTS)) {
                    c = 'm';
                    break;
                }
                c = 65535;
                break;
            case -1102524420:
                if (str.equals(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_TITLE)) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case -1083973410:
                if (str.equals(Constants.ConfigKey.MAIN_NEW_VERSION_PREFIX)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1083108596:
                if (str.equals(Constants.ConfigKey.MAIN_VIEW_VERSION_DETAIL)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -1005569618:
                if (str.equals(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_NOTE)) {
                    c = Matrix.MATRIX_TYPE_ZERO;
                    break;
                }
                c = 65535;
                break;
            case -981644151:
                if (str.equals(Constants.ConfigKey.DIALOG_CANCEL_UPGRADE_CONFIRM_CONTENT)) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case -953419592:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CONTENT)) {
                    c = '~';
                    break;
                }
                c = 65535;
                break;
            case -907952625:
                if (str.equals(Constants.ConfigKey.DIALOG_CLOSE_AUTO_CONTENT_FORMAT)) {
                    c = '\\';
                    break;
                }
                c = 65535;
                break;
            case -893775871:
                if (str.equals(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TTS)) {
                    c = 'b';
                    break;
                }
                c = 65535;
                break;
            case -883817880:
                if (str.equals(Constants.ConfigKey.DIALOG_CANCEL_UPGRADE_CONFIRM_TITLE)) {
                    c = Matrix.MATRIX_TYPE_RANDOM_REGULAR;
                    break;
                }
                c = 65535;
                break;
            case -856102599:
                if (str.equals(Constants.ConfigKey.MAIN_FIND_NEW_VERSION)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -848734186:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CONTENT)) {
                    c = 154;
                    break;
                }
                c = 65535;
                break;
            case -796546940:
                if (str.equals(Constants.ConfigKey.MAIN_NEW_VERSION_TIPS)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -790030960:
                if (str.equals(Constants.ConfigKey.TITLE_UPGRADE_WARN)) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case -772558376:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_TTS)) {
                    c = '{';
                    break;
                }
                c = 65535;
                break;
            case -696223479:
                if (str.equals(Constants.ConfigKey.USB_UPDATE_FOLDER)) {
                    c = 138;
                    break;
                }
                c = 65535;
                break;
            case -641289940:
                if (str.equals(Constants.ConfigKey.BUTTON_CONFIRM_CLOSE)) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case -634539155:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_CONTENT)) {
                    c = 'o';
                    break;
                }
                c = 65535;
                break;
            case -629473207:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_TTS)) {
                    c = 133;
                    break;
                }
                c = 65535;
                break;
            case -605762951:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_UPGRADE_AUTO)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -574515082:
                if (str.equals(Constants.ConfigKey.BUTTON_CONFIRM_OPEN)) {
                    c = Matrix.MATRIX_TYPE_RANDOM_UT;
                    break;
                }
                c = 65535;
                break;
            case -574499306:
                if (str.equals(Constants.ConfigKey.BUTTON_CONFIRM_PARK)) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case -573994133:
                if (str.equals(Constants.ConfigKey.TEXT_HISTORY_INFO)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -568940614:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_SYNC_OS_VERSION)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -559570466:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_CONTENT)) {
                    c = 'z';
                    break;
                }
                c = 65535;
                break;
            case -415804274:
                if (str.equals(Constants.ConfigKey.BUTTON_NOT_SURE)) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case -412928971:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_CONTENT)) {
                    c = 'k';
                    break;
                }
                c = 65535;
                break;
            case -392271032:
                if (str.equals(Constants.ConfigKey.MAIN_NO_NEW)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -381292511:
                if (str.equals(Constants.ConfigKey.TITLE_CHECK)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -371925166:
                if (str.equals(Constants.ConfigKey.SCHEDULE_UPGRADE_TIME_PREDICT)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -334916991:
                if (str.equals(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_CONTENT)) {
                    c = 'e';
                    break;
                }
                c = 65535;
                break;
            case -332760801:
                if (str.equals(Constants.ConfigKey.INSTALL_SUCCESS)) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case -322287414:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_AUTO_CONTENT)) {
                    c = 129;
                    break;
                }
                c = 65535;
                break;
            case -302674635:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_TITLE)) {
                    c = 153;
                    break;
                }
                c = 65535;
                break;
            case -210728534:
                if (str.equals(Constants.ConfigKey.PARK_MAIN_TIP)) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case -168373684:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_TITLE)) {
                    c = 'n';
                    break;
                }
                c = 65535;
                break;
            case -126110055:
                if (str.equals(Constants.ConfigKey.UPGRADE_SUB_TIP)) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case -120013783:
                if (str.equals(Constants.ConfigKey.SCHEDULE_TIME_FOR_UPDATE)) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -93223506:
                if (str.equals(Constants.ConfigKey.ECU_GROUP_HEADER_TITLE)) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -92071191:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_AUTO_TITLE)) {
                    c = 128;
                    break;
                }
                c = 65535;
                break;
            case -81318519:
                if (str.equals(Constants.ConfigKey.UPGRADE_CONDITION_MISS_TEXT)) {
                    c = '|';
                    break;
                }
                c = 65535;
                break;
            case -38237325:
                if (str.equals(Constants.ConfigKey.MAIN_NEW_VERSION)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -34217244:
                if (str.equals(Constants.ConfigKey.APP_GROUP_HEADER_TITLE)) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -10459494:
                if (str.equals(Constants.ConfigKey.TITLE_USB_UPGRADE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 63696471:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_TITLE)) {
                    c = '}';
                    break;
                }
                c = 65535;
                break;
            case 74522854:
                if (str.equals(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_TITLE)) {
                    c = '`';
                    break;
                }
                c = 65535;
                break;
            case 86805346:
                if (str.equals(Constants.ConfigKey.INSTALL_FAIL)) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case 86907220:
                if (str.equals(Constants.ConfigKey.INSTALL_INIT)) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case 155215756:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_VIEW_VERSION_DETAIL_NEW)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 158959749:
                if (str.equals(Constants.ConfigKey.TEXT_SWITCH_AUTO_UPGRADE)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 198764671:
                if (str.equals(Constants.ConfigKey.SCHEDULE_BUTTON_CANCEL)) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 280187932:
                if (str.equals(Constants.ConfigKey.BUTTON_UPGRADE_LATER)) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 293193571:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TTS)) {
                    c = 'i';
                    break;
                }
                c = 65535;
                break;
            case 363543367:
                if (str.equals(Constants.ConfigKey.TIPS_SOTA_UPGRADE_WAIT)) {
                    c = '^';
                    break;
                }
                c = 65535;
                break;
            case 394757503:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_UPGRADE_TO_NEW_VERSION)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 426044887:
                if (str.equals(Constants.ConfigKey.DEFAULT_SCHEDULE_TIME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 427238923:
                if (str.equals(Constants.ConfigKey.UPGRADE_TIME_V2)) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case 448788969:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_CONTENT)) {
                    c = 'h';
                    break;
                }
                c = 65535;
                break;
            case 460020022:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_TITLE)) {
                    c = 147;
                    break;
                }
                c = 65535;
                break;
            case 497693375:
                if (str.equals(Constants.ConfigKey.SCHEDULE_BUTTON_MODIFY)) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 502159705:
                if (str.equals(Constants.ConfigKey.DIALOG_AUTO_UPGRADE_CONTENT_FORMAT)) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case 511713764:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_BUTTON_POSITIVE)) {
                    c = 150;
                    break;
                }
                c = 65535;
                break;
            case 522364754:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_TTS_RETRYABLE)) {
                    c = 'q';
                    break;
                }
                c = 65535;
                break;
            case 528648160:
                if (str.equals(Constants.ConfigKey.SCHEDULE_TIPS)) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case 600418557:
                if (str.equals(Constants.ConfigKey.PROMPT_SCHEDULE_UPGRADE_TIMES_UP_TITLE)) {
                    c = 'y';
                    break;
                }
                c = 65535;
                break;
            case 612768849:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_TTS)) {
                    c = 149;
                    break;
                }
                c = 65535;
                break;
            case 652126969:
                if (str.equals(Constants.ConfigKey.TITLE_NEW)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 661846940:
                if (str.equals(Constants.ConfigKey.SCHEDULE_UPGRADE_DESC)) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case 666312494:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_CONDITION_MISS_CUSTOMER_SERVICE_TITLE)) {
                    c = 131;
                    break;
                }
                c = 65535;
                break;
            case 693753187:
                if (str.equals(Constants.ConfigKey.MAIN_CHECK_OS_VERSION_FAIL)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case 705771689:
                if (str.equals(Constants.ConfigKey.SCHEDULE_HAS_SCHEDULE_INFO)) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 712691048:
                if (str.equals(Constants.ConfigKey.MAIN_BUTTON_HAS_SCHEDULED_WITH_TIME)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 751870072:
                if (str.equals(Constants.ConfigKey.TITLE_CHECKING_CANCEL)) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case 758587015:
                if (str.equals(Constants.ConfigKey.PARK_SUB_TIP)) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case 833054304:
                if (str.equals(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_TITLE)) {
                    c = 'd';
                    break;
                }
                c = 65535;
                break;
            case 856236879:
                if (str.equals(Constants.ConfigKey.UPGRADE_SUB_TIP_TEMP_PARK)) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case 860799879:
                if (str.equals(Constants.ConfigKey.PROMPT_LOCAL_UPGRADE_CONTENT)) {
                    c = 'a';
                    break;
                }
                c = 65535;
                break;
            case 862985534:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TITLE)) {
                    c = 'r';
                    break;
                }
                c = 65535;
                break;
            case 884955152:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_CONDITION_MISS_CUSTOMER_SERVICE_TITLE)) {
                    c = 151;
                    break;
                }
                c = 65535;
                break;
            case 899081700:
                if (str.equals(Constants.ConfigKey.BUTTON_CALL_CS)) {
                    c = 137;
                    break;
                }
                c = 65535;
                break;
            case 932876707:
                if (str.equals(Constants.ConfigKey.TIPS_SOTA_UPGRADE_DETAIL)) {
                    c = '_';
                    break;
                }
                c = 65535;
                break;
            case 1060420384:
                if (str.equals(Constants.ConfigKey.TEXT_MODIFY_TIME)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1085350039:
                if (str.equals(Constants.ConfigKey.SCHEDULE_BUTTON_UPGRADE)) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 1097952336:
                if (str.equals(Constants.ConfigKey.BUTTON_CONFIRM_UPGRADE)) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 1102720609:
                if (str.equals(Constants.ConfigKey.PROMPT_VERIFY_FAILED_TITLE)) {
                    c = 'w';
                    break;
                }
                c = 65535;
                break;
            case 1103375361:
                if (str.equals(Constants.ConfigKey.TITLE_CHECKING)) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case 1116364354:
                if (str.equals(Constants.ConfigKey.PROMPT_VERIFY_FAILED_CONTENT)) {
                    c = 'x';
                    break;
                }
                c = 65535;
                break;
            case 1117611110:
                if (str.equals(Constants.ConfigKey.BUTTON_UPGRADE_NOW)) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 1197764882:
                if (str.equals(Constants.ConfigKey.SCHEDULE_TIME_CONSUME)) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case 1238854651:
                if (str.equals(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_TTS)) {
                    c = 'f';
                    break;
                }
                c = 65535;
                break;
            case 1254169592:
                if (str.equals(Constants.ConfigKey.BUTTON_STILL_UPGRADE)) {
                    c = '?';
                    break;
                }
                c = 65535;
                break;
            case 1255310449:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_POPUP_CONTENT)) {
                    c = 143;
                    break;
                }
                c = 65535;
                break;
            case 1279353184:
                if (str.equals(Constants.ConfigKey.NEW_SCHEDULE_TIME_FORMAT)) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 1301537427:
                if (str.equals(Constants.ConfigKey.BUTTON_CONFIRM)) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case 1305975953:
                if (str.equals(Constants.ConfigKey.MAIN_LATEST_VERSION_TIPS)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 1320234655:
                if (str.equals(Constants.ConfigKey.TITLE_VERSION_DETAIL)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1398925641:
                if (str.equals(Constants.ConfigKey.PROMPT_AUTO_UPGRADE_ENABLED)) {
                    c = 'c';
                    break;
                }
                c = 65535;
                break;
            case 1400897690:
                if (str.equals(Constants.ConfigKey.DEFAULT_VERSION)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1449993862:
                if (str.equals(Constants.ConfigKey.DIALOG_CLOSE_AUTO_TITLE)) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case 1455358606:
                if (str.equals(Constants.ConfigKey.DIALOG_HELP_CONTENT_MISS_CONDITIONS)) {
                    c = 139;
                    break;
                }
                c = 65535;
                break;
            case 1462501612:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_SUCCESS_TTS_REMOTE)) {
                    c = 'v';
                    break;
                }
                c = 65535;
                break;
            case 1575162116:
                if (str.equals(Constants.ConfigKey.BUTTON_SCHEDULE)) {
                    c = '>';
                    break;
                }
                c = 65535;
                break;
            case 1637895736:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_BUTTON_OK_TEXT)) {
                    c = 144;
                    break;
                }
                c = 65535;
                break;
            case 1683355380:
                if (str.equals(Constants.ConfigKey.INSTALL_DISABLE_SPEECH_INFO)) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 1812668217:
                if (str.equals(Constants.ConfigKey.BUTTON_CHANGE_SCHEDULE)) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case 1826886736:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_POPUP_TITLE)) {
                    c = 142;
                    break;
                }
                c = 65535;
                break;
            case 1880949724:
                if (str.equals(Constants.ConfigKey.SCHEDULE_AUTO_UPGRADE_DESC)) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 1881106937:
                if (str.equals(Constants.ConfigKey.SCHEDULE_AUTO_UPGRADE_INFO)) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case 1899280805:
                if (str.equals(Constants.ConfigKey.BUTTON_MODIFY_TIME)) {
                    c = 135;
                    break;
                }
                c = 65535;
                break;
            case 1917306143:
                if (str.equals(Constants.ConfigKey.EXPIRED_CONTENT)) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case 1923474708:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_TITLE)) {
                    c = 'j';
                    break;
                }
                c = 65535;
                break;
            case 1942653752:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_CAMPAIGN_LIST)) {
                    c = 141;
                    break;
                }
                c = 65535;
                break;
            case 1956390871:
                if (str.equals(Constants.ConfigKey.PROMPT_DIALOG_UPGRADE_FAIL_CONTENT)) {
                    c = 148;
                    break;
                }
                c = 65535;
                break;
            case 1971113608:
                if (str.equals(Constants.ConfigKey.SCHEDULE_SELECT_TIME)) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 1971601141:
                if (str.equals(Constants.ConfigKey.TITLE_UPGRADE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2040610846:
                if (str.equals(Constants.ConfigKey.NEW_V2_INTRODUCTION)) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 2091442919:
                if (str.equals(Constants.ConfigKey.PROMPT_UPGRADE_FAILED_AUTO_TTS)) {
                    c = 'p';
                    break;
                }
                c = 65535;
                break;
            case 2107599762:
                if (str.equals(Constants.ConfigKey.BUTTON_SCHEDULE_FORMAT)) {
                    c = '3';
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
                return null;
            case 1:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.default_service_phone));
            case 2:
                return OTAManager.getConfigManager().getString(str, ResourceUtils.getString(R.string.default_schedule_time));
            case 3:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_title));
            case 4:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.history_title));
            case 5:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_title));
            case 6:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.info_title));
            case 7:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.check_title));
            case '\b':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_title));
            case '\t':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_title));
            case '\n':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.usb_title));
            case 11:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.version_detail_title));
            case '\f':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.version_prefix));
            case '\r':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_more_info));
            case 14:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_history_version));
            case 15:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_no_new));
            case 16:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_new_version));
            case 17:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_new_version_prefix));
            case 18:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_find_new_version));
            case 19:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_schedule_time));
            case 20:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.desc_auto_upgrade_title));
            case 21:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_modify_time));
            case 22:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_new_version_tips));
            case 23:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_latest_version_tips));
            case 24:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_view_version_detail));
            case 25:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_check_os_version_fail));
            case 26:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_btn_upgrade_to_new_version));
            case 27:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_btn_has_schedule_with_time));
            case 28:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_btn_upgrade_auto));
            case 29:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_check_os_version));
            case 30:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_view_version_detail));
            case 31:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.main_version_detail));
            case ' ':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.info_title_apps));
            case '!':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.info_title_ecus));
            case '\"':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_desc));
            case '#':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_select_time));
            case '$':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_time_predict));
            case '%':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_info));
            case '&':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_time_consume));
            case '\'':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_time_for_upgrade));
            case '(':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_btn_confirm));
            case ')':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_btn_modify));
            case '*':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_btn_cancel));
            case '+':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_btn_upgrade));
            case ',':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_auto_upgrade_desc));
            case '-':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_auto_upgrade_info));
            case '.':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_tips));
            case '/':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_sub_tips_1));
            case '0':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_sub_tips_2));
            case '1':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.schedule_sub_tips_3));
            case '2':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_upgrade_now));
            case '3':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_schedule_upgrade));
            case '4':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_schedule_time));
            case '5':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_v2_introduction));
            case '6':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_v2_button_upgrade));
            case '7':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_warn));
            case '8':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_main_tip));
            case '9':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_sub_tip));
            case ':':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_time_format));
            case ';':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_time_v2));
            case '<':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_main_tip_temp_park));
            case '=':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_sub_tip_temp_park));
            case '>':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_schedule));
            case '?':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_button_still_upgrade));
            case '@':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_button_upgrade_later));
            case 'A':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_button_confirm_upgrade));
            case 'B':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.park_main_tip));
            case 'C':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.park_sub_tip));
            case 'D':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.park_button_confirm_park));
            case 'E':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.park_button_not_sure));
            case 'F':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.check_desc));
            case 'G':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.check_cancel_desc));
            case 'H':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.install_init));
            case 'I':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.install_success));
            case 'J':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.install_fail));
            case 'K':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.install_disable_speech_info));
            case 'L':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_condition_title));
            case 'M':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_expired_title));
            case 'N':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_ok));
            case 'O':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_confirm));
            case 'P':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_cancel));
            case 'Q':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_now));
            case 'R':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_cancel_upgrade_confirm_title));
            case 'S':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_cancel_upgrade_confirm_content));
            case 'T':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.new_modify_time));
            case 'U':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_confirm_open));
            case 'V':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_confirm_close));
            case 'W':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.expired_content));
            case 'X':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_auto_upgrade_title));
            case 'Y':
                return replaceSpecialChar(OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_auto_upgrade_content)));
            case 'Z':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_auto_upgrade_note));
            case '[':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_close_auto_title));
            case '\\':
                return replaceSpecialChar(OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_close_auto_content)));
            case ']':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_sota_title));
            case '^':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_sota_wait_tips));
            case '_':
                return replaceSpecialChar(OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.dialog_sota_detail)));
            case '`':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_local_upgrade_title));
            case 'a':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_local_upgrade_content));
            case 'b':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_local_upgrade_tts));
            case 'c':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), "1");
            case 'd':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_auto_upgrade_title));
            case 'e':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_auto_upgrade_content));
            case 'f':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_auto_upgrade_tts));
            case 'g':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_upgrade_title));
            case 'h':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_upgrade_content));
            case 'i':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_upgrade_tts));
            case 'j':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_title));
            case 'k':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_content));
            case 'l':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_content_retryable));
            case 'm':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_tts));
            case 'n':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_auto_title));
            case 'o':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_auto_content));
            case 'p':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_auto_tts));
            case 'q':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_failed_tts_retryable));
            case 'r':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_success_title));
            case 's':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_success_content));
            case 't':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_success_content_remote));
            case 'u':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_success_tts));
            case 'v':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_success_tts_remote));
            case 'w':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_verify_failed_title));
            case 'x':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_verify_failed_content));
            case 'y':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_times_up_title));
            case 'z':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_times_up_content));
            case '{':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_schedule_times_up_tts));
            case '|':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.upgrade_condition_miss_text));
            case '}':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_title));
            case '~':
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_content));
            case CertificateBody.profileType /* 127 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_tts));
            case 128:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_auto_title));
            case 129:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_auto_content));
            case 130:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_auto_tts));
            case TarConstants.PREFIXLEN_XSTAR /* 131 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_customer_service_title));
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_customer_service_content));
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA /* 133 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.prompt_upgrade_condition_miss_customer_service_tts));
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA /* 134 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_all_right));
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA /* 135 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_modify_time));
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /* 136 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_detail));
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.button_call_cs));
            case CipherSuite.TLS_PSK_WITH_RC4_128_SHA /* 138 */:
                return OTAManager.getConfigManager().getString(str, Config.OTA_USB_FOLDER);
            case CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA /* 139 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), ResourceUtils.getString(R.string.help_miss_conditions));
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                try {
                    String serverUrl = OTAManager.getConfigManager().getServerUrl();
                    if (!TextUtils.isEmpty(serverUrl)) {
                        return serverUrl;
                    }
                } catch (NotFoundException unused) {
                }
                return LocaleConfig.getHttpHost();
            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                return OTAManager.getConfigManager().getString(str, "");
            case CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA /* 142 */:
                return OTAManager.getConfigManager().getString(str, context.getString(R.string.upgrade_popup_title));
            case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                return OTAManager.getConfigManager().getString(str, context.getString(R.string.upgrade_popup_content));
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                return OTAManager.getConfigManager().getString(str, context.getString(R.string.upgrade_popup_button_ok));
            case 145:
                return OTAManager.getConfigManager().getString(str, context.getString(R.string.upgrade_popup_button_cancel));
            case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                return OTAManager.getConfigManager().getString(str, UpgradePopupConfig.DEFAULT_SCHEDULE_TIME);
            case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_upgrade_fail_title));
            case 148:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_upgrade_fail_content));
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_upgrade_fail_tts));
            case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_upgrade_fail_button_positive));
            case CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA /* 151 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_condition_miss_customer_service_title));
            case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_condition_miss_customer_service_content));
            case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA /* 153 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_condition_miss_title));
            case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_condition_miss_content));
            case 155:
                return OTAManager.getConfigManager().getString(getKeyWithLocalePrefix(context, str), context.getString(R.string.prompt_dialog_condition_miss_positive_button));
            default:
                return OTAManager.getConfigManager().getString(str, null);
        }
    }

    public static String getFormat(String str) {
        return ((str.hashCode() == 220262143 && str.equals(Constants.ConfigKey.ECU_VERSION_SUMMARY_FORMAT)) ? (char) 0 : (char) 65535) != 0 ? "" : replaceSpecialChar(ResourceUtils.getString(R.string.info_ecu_summary_format));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getInt(String str) {
        char c;
        switch (str.hashCode()) {
            case -1679928741:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_POPUP_STRATEGY)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1100533798:
                if (str.equals(Constants.ConfigKey.INSTALL_UI_TIMEOUT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1034410659:
                if (str.equals(Constants.ConfigKey.UPGRADE_COUNTDOWN_IN_SECOND)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1000318759:
                if (str.equals(Constants.ConfigKey.AI_FAILED_MESSAGE_VALID_SECONDS)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -747599830:
                if (str.equals(Constants.ConfigKey.UPGRADE_UNSAFE_COUNTDOWN_IN_SECOND)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -495977782:
                if (str.equals(Constants.ConfigKey.AI_UPGRADE_MESSAGE_VALID_SECONDS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -355500467:
                if (str.equals(Constants.ConfigKey.CHECK_UI_TIMEOUT)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -195382444:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_POPUP_FREQUENCY)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 117940486:
                if (str.equals(Constants.ConfigKey.KEY_LOG_CAPACITY)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 348742131:
                if (str.equals(Constants.ConfigKey.AI_SCHEDULE_MESSAGE_VALID_SECONDS)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 550597885:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_DISMISS_STRATEGY)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 645146993:
                if (str.equals(Constants.ConfigKey.AI_SUCCESS_MESSAGE_VALID_SECONDS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 661704812:
                if (str.equals(Constants.ConfigKey.SOTA_UPGRADE_TIMEOUT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 707974524:
                if (str.equals(Constants.ConfigKey.AUTO_UPGRADE_COUNTDOWN_SECONDS)) {
                    c = '\r';
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
                return OTAManager.getConfigManager().getInt(str, 30);
            case 1:
                return OTAManager.getConfigManager().getInt(str, 60);
            case 2:
                return OTAManager.getConfigManager().getInt(str, 600000);
            case 3:
                return OTAManager.getConfigManager().getInt(str, 1800);
            case 4:
                return OTAManager.getConfigManager().getInt(str, 300);
            case 5:
            case 6:
                return OTAManager.getConfigManager().getInt(str, Config.SECONDS_A_DAY);
            case 7:
                return OTAManager.getConfigManager().getInt(str, 480000);
            case '\b':
                return OTAManager.getConfigManager().getInt(str, 30);
            case '\t':
                return OTAManager.getConfigManager().getInt(str, 300000);
            case '\n':
                return OTAManager.getConfigManager().getInt(str, UpgradePopupConfig.PopupStrategy.POWER_ON.getCode());
            case 11:
                return OTAManager.getConfigManager().getInt(str, UpgradePopupConfig.DismissStrategy.GEAR_DRIVE_REVERS.getCode());
            case '\f':
                return OTAManager.getConfigManager().getInt(str, 1);
            case '\r':
                return OTAManager.getConfigManager().getInt(str, 10);
            default:
                return -1;
        }
    }

    public static boolean getBoolean(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1711976101) {
            if (hashCode == 799460395 && str.equals(Constants.ConfigKey.SCHEDULE_ENABLE)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(Constants.ConfigKey.AI_ASSISTANT_ENABLE)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return false;
            }
            return OTAManager.getConfigManager().getBoolean(str, true);
        }
        return OTAManager.getConfigManager().getBoolean(str, true);
    }

    private static String getKeyWithLocalePrefix(Context context, String str) {
        if ("CN".equals(context.getResources().getConfiguration().locale.getCountry())) {
            return "LOCALE_ZH_CN." + str;
        }
        return str;
    }

    private static String replaceSpecialChar(String str) {
        return str.replace("#", TARGET_CHAR);
    }
}
