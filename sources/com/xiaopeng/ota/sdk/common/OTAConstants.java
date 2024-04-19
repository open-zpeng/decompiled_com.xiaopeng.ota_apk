package com.xiaopeng.ota.sdk.common;
/* loaded from: classes2.dex */
public class OTAConstants {
    public static final String AND = "&";
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final long CHECK_WAIT_INTERVAL = 30000;
    public static final String COMMA = ",";
    public static final String DOWNLOAD_DIRECTORY = "download";
    public static final String EQ = "=";
    public static final String FILE_NAME_MCU_BIN = "mcu.bin";
    public static final String FILE_SEPARATOR = "/";
    public static final String INSTALL_DIRECTORY = "/cache/ota/";
    public static final char LINE = '\n';
    public static final String LINKER_UNDER_LINE = "_";
    public static final String OTA_APP_NAME = "OTA";
    public static final String PSU_KEY_PATH = "/mnt/vendor/private/sec/xp0208.png";
    public static final String SECURITY_DIR = "/mnt/vendor/private/sec";
    public static final int STATE_IDLE = 2;
    public static final int STATE_UPGRADING = 1;
    public static final String SUFFIX_MCU_ENCRYPT = "_mcu.tmp";
    public static final int SWITCH_STATUS_OFF = 0;
    public static final int SWITCH_STATUS_ON = 1;
    public static final String TMP_INSTALL_FILE = "/cache/ota/update.tmp";

    /* loaded from: classes2.dex */
    public static class Action {
        public static final String CAMPAIGN_HOST_CHANGED = "com.xiaopeng.ota.intent.action.CAMPAIGN_HOST_CHANGED";
        public static final String CHECK_CAMPAIGN_COMPLETED = "com.xiaopeng.ota.intent.action.CHECK_CAMPAIGN_COMPLETED";
        public static final String CHECK_CAMPAIGN_COMPLETED_CONNECTED = "com.xiaopeng.ota.intent.action.CHECK_CAMPAIGN_COMPLETED_CONNECTED";
        public static final String CHECK_CAMPAIGN_COMPLETED_POWER_OFF = "com.xiaopeng.ota.intent.action.CHECK_CAMPAIGN_COMPLETED_POWER_OFF";
        public static final String CHECK_CAMPAIGN_COMPLETED_POWER_ON = "com.xiaopeng.ota.intent.action.CHECK_CAMPAIGN_COMPLETED_POWER_ON";
        public static final String OTA_STATE = "com.xiaopeng.broadcast.ACTION_OTA_STATE";
        public static final String RESUME_INSTALLATION = "com.xiaopeng.ota.intent.action.RESUME_INSTALLATION";
    }

    /* loaded from: classes2.dex */
    public static class CheckSource {
        public static final String TRIGGER_ACTION_BOOT_COMPLETED = "BOOT_COMPLETED";
        public static final String TRIGGER_ACTION_CONNECTED = "CONNECTED";
        public static final String TRIGGER_ACTION_POWER_OFF = "POWER_OFF";
        public static final String TRIGGER_ACTION_POWER_ON = "POWER_ON";
        public static final String TRIGGER_ACTION_PUSH = "PUSH";
        public static final String TRIGGER_ACTION_USER = "USER";
    }

    /* loaded from: classes2.dex */
    public static class Config {
        public static final String CHECK_FOR_UPDATE_INTERVAL = "RETRY_TIMEOUT.CHECK_FOR_UPDATE_INTERVAL";
        public static final String CHECK_TOO_FREQUENTLY_MINUTE = "RETRY_TIMEOUT.CHECK_TOO_FREQUENTLY_MINUTE";
        public static final String CIU_VERSION_SYNC_DELAY = "RETRY_TIMEOUT.CIU_VERSION_SYNC_DELAY";
        public static final String CONNECTOR = ".";
        public static final String DEFAULT_UPGRADE_PROMPT = "本次更新大约需要#%s分钟#，在更新过程中，你将无法驾驶车辆。";
        public static final String DEFAULT_UPGRADE_PROMPT_PREFIX = "提示：";
        public static final String DVR_VERSION_VERIFY_DELAY = "RETRY_TIMEOUT.DVR_VERSION_VERIFY_DELAY";
        public static final String ECU_VERSION_SYNC_DELAY = "RETRY_TIMEOUT.ECU_VERSION_SYNC_DELAY";
        public static final String ECU_VERSION_SYNC_PERIOD = "RETRY_TIMEOUT.ECU_VERSION_SYNC_PERIOD";
        public static final String EVENT_UPLOAD_INTERVAL = "COMMON_CONSTANTS.EVENT_UPLOAD_INTERVAL";
        public static final String ICM_CONNECT_MAX_TIMES = "RETRY_TIMEOUT.ICM_CONNECT_MAX_TIMES";
        public static final String ICM_CONNECT_RETRY_DELAY = "RETRY_TIMEOUT.ICM_CONNECT_RETRY_DELAY";
        public static final String ICM_SEND_DATA_TIMEOUT = "RETRY_TIMEOUT.ICM_SEND_DATA_TIMEOUT";
        public static final String INSTALL_RETRY_TIMES = "RETRY_TIMEOUT.INSTALL_RETRY_TIMES";
        public static final String MCU_SEND_DATA_TIMEOUT = "RETRY_TIMEOUT.MCU_SEND_DATA_TIMEOUT";
        public static final String OTA_SERVER_URL = "OTA_SERVER_URL";
        public static final String PSU_SEND_MESSAGE_RETRY_DELAY = "RETRY_TIMEOUT.PSU_SEND_MESSAGE_RETRY_DELAY";
        public static final String PSU_SEND_MESSAGE_RETRY_TIMES = "RETRY_TIMEOUT.PSU_SEND_MESSAGE_RETRY_TIMES";
        public static final String READ_VERSION_RETRY_DELAY = "RETRY_TIMEOUT.READ_VERSION_RETRY_DELAY";
        public static final String READ_VERSION_RETRY_TIMES = "RETRY_TIMEOUT.READ_VERSION_RETRY_TIMES";
        public static final String RECOVER_TIMES = "RETRY_TIMEOUT.RECOVER_TIMES";
        public static final String SERVER_URL = "SERVER_URL";
        public static final String TIPS_UPGRADE_PROMPT = "TIPS_UPGRADE_PROMPT";
        public static final String TIPS_UPGRADE_PROMPT_PREFIX = "TIPS_UPGRADE_PROMPT_PREFIX";
        public static final String UDS_STM_USEC = "UDS_STM_USEC";
        public static final String UPLOAD_EVENT_BATCH_SIZE = "RETRY_TIMEOUT.UPLOAD_EVENT_BATCH_SIZE";
        public static final String WAIT_CDU_AB_INSTALL_TIMEOUT = "RETRY_TIMEOUT.WAIT_CDU_AB_INSTALL_TIMEOUT";
        public static final String WAIT_ICM_INSTALL_TIMEOUT = "RETRY_TIMEOUT.WAIT_ICM_INSTALL_TIMEOUT";
        public static final String WAIT_MCU_INSTALL_TIMEOUT = "RETRY_TIMEOUT.WAIT_MCU_INSTALL_TIMEOUT";
        public static final String WAIT_MCU_REBOOT_TIMEOUT = "RETRY_TIMEOUT.WAIT_MCU_REBOOT_TIMEOUT";
        public static final String WAIT_UDS_INSTALL_TIMEOUT = "RETRY_TIMEOUT.WAIT_UDS_INSTALL_TIMEOUT";
        public static final String WAIT_UDS_REBOOT_TIMEOUT = "RETRY_TIMEOUT.WAIT_UDS_REBOOT_TIMEOUT";
    }

    /* loaded from: classes2.dex */
    public static class ConfigProvider {
        public static final String AUTHORITY = "com.xiaopeng.ota.sdk.manager.config.ConfigProvider";
        public static final String CONTENT = "content://";
        public static final String CONTENT_URI = "content://com.xiaopeng.ota.sdk.manager.config.ConfigProvider";
        public static final String DEFAULT_VALUE_DOUBLE = "-1.0";
        public static final String DEFAULT_VALUE_FLOAT = "-1.0";
        public static final String DEFAULT_VALUE_INT = "-1";
        public static final String DEFAULT_VALUE_LONG = "-1";
        public static final String SEPARATOR = "/";
    }

    /* loaded from: classes2.dex */
    public static final class ConfigServer {
        public static final String AVAILABLE_PACKAGES = "AVAILABLE_PACKAGES";
        public static final String BID = "bid";
        public static final String BR = "Xiaopeng";
        public static final int CLIENT_TYPE_XMART = 3;
        public static final String COMMON_CONSTANTS = "COMMON_CONSTANTS";
        public static final String CONFIG = "OTA_CONFIG";
        public static final String CONFIG_COMMON = "COMMON";
        public static final String CONFIG_ECU_PROPERTIES = "ECU_PROPERTIES";
        public static final String CONFIG_LOCALE_ZH_CN = "LOCALE_ZH_CN";
        public static final String CONFIG_RETRY_TIMEOUT = "RETRY_TIMEOUT";
        public static final String CONFIG_SERVER_URL = "SERVER_URL";
        public static final String CONFIG_UDS_STM_USEC = "UDS_STM_USEC";
        public static final String DATA_FORMAT = "MAP";
        public static final String MO = "XMARTA1";
        public static final String ST = "3";
    }

    /* loaded from: classes2.dex */
    public static class DownloadMode {
        public static final int BOMB_BOX = 0;
        public static final int SILENT = 1;
        public static final int WIFI_SILENT = 2;
    }

    /* loaded from: classes2.dex */
    public static class Extra {
        public static final String HAS_NEW_CAMPAIGN = "hasNewCampaign";
        public static final String HOST = "host";
        public static final String STATE = "state";
    }

    /* loaded from: classes2.dex */
    public static class HttpHeader {
        public static final String CONTENT_CONNECTOR = "|";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String XP_APP_ID = "XP-APPID";
        public static final String XP_CFC_INFO = "XP-CFC-INFO";
        public static final String XP_CHECK_SOURCE = "XP-CHECK-SOURCE";
        public static final String XP_CLIENT = "XP-CLIENT";
        public static final String XP_CLIENT_TYPE = "XP-CLIENT-TYPE";
        public static final String XP_ENCRYPTION_TYPE = "XP-ENCRYPTION-TYPE";
        public static final String XP_NONCE = "XP-NONCE";
        public static final String XP_OTA_DATA_FORMAT = "XP-OTA-DATA-FORMAT";
        public static final String XP_OTA_FULL_SYNC = "XP-OTA-FULL-SYNC";
        public static final String XP_OTA_INSTALLED_PACKAGES = "XP-OTA-INSTALLED-PACKAGES";
        public static final String XP_OTA_OSS_KEY = "XP-OTA-OSS-KEY";
        public static final String XP_OTA_TRACE_ID = "XP-OTA-TID";
        public static final String XP_OTA_VERSION_HASH = "XP-OTA-VERSION-HASH";
        public static final String XP_SIGNATURE = "XP-SIGNATURE";
        public static final String XP_VIN = "XP-VIN";
        public static final String X_CA_CLIENT = "X-CA-Client";
        public static final String X_CA_SIGNATURE = "X-CA-Signature";
        public static final String X_CA_TIMESTAMP = "X-CA-Timestamp";
    }

    /* loaded from: classes2.dex */
    public static class HttpMethod {
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static final String PUT = "PUT";
    }

    /* loaded from: classes2.dex */
    public static class HttpParam {
        public static final String KEY = "key";
    }

    /* loaded from: classes2.dex */
    public static class HttpResponseCode {
        public static final int CODE_ERROR_DIFF_PACKAGE_NOT_FOUND = 14000017;
        public static final int CODE_ERROR_PACKAGE_NOT_FOUND = 14000018;
        public static final int CODE_ERROR_PARENT_PACKAGE_NOT_FOUND = 14000019;
        public static final int CODE_ERROR_SUSPENDED = 14000015;
        public static final int CODE_ERROR_TIMEOUT_CANCEL = 14000028;
        public static final int CODE_ERROR_VEHICLE_VERSION_MISMATCH = 14000016;
        public static final int CODE_NO_CAMPAIGN = 14000013;
        public static final int CONTINUE_DOWNLOADED_SUCCESS = 206;
        public static final int ERROR = 0;
        public static final int OK = 200;
    }

    /* loaded from: classes2.dex */
    public static class InstallMode {
        public static final int BOMB_BOX = 2;
        public static final int RED_DOT = 0;
        public static final int SILENT = 1;
    }

    /* loaded from: classes2.dex */
    public static class JsonKey {
        public static final String BID = "bid";
        public static final String CAMPAIGN_ID = "campaignId";
        public static final String CAMPAIGN_NAME = "campaignName";
        public static final String CHECK_TIME = "checkTime";
        public static final String CODE = "code";
        public static final String CONTEXT = "context";
        public static final String DATA = "data";
        public static final String DOWNLOAD_URLS = "downloadUrls";
        public static final String ECUS = "ecus";
        public static final String ERROR_MESSAGE = "errorMsg";
        public static final String EVENT = "event";
        public static final String EVENTS = "events";
        public static final String EXTRAS = "extras";
        public static final String FINGER_PRINT = "fingerprint";
        public static final String HASH = "hash";
        public static final String HV = "hv";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PACKAGE = "package";
        public static final String PACKAGE_ID = "packageId";
        public static final String PACKAGE_NAME = "packageName";
        public static final String RELEASE_NOTES = "releaseNotes";
        public static final String SIZE = "size";
        public static final String STATUS = "status";
        public static final String SV = "sv";
        public static final String SYNC_TIME = "syncTime";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "traceId";
        public static final String UPGRADE_MODE = "upgradeMode";
        public static final String VERSION_CODE = "versionCode";
        public static final String VERSION_NAME = "versionName";
    }

    /* loaded from: classes2.dex */
    public static class Network {
        public static final int TYPE_MOBILE = 0;
        public static final int TYPE_NONE = -1;
        public static final int TYPE_WIFI = 1;
    }

    /* loaded from: classes2.dex */
    public static class Server {
        public static final String CONFIG_CONTEXT = "/config/v2/config";
        public static final String OTA_BASE = "/ota2/";
        public static final String OTA_EVENT_URI = "v4/event";
        public static final String OTA_PERMISSION_URI = "v4/verifyKey";
        public static final String OTA_PULL_OS_VERSION_URI = "v4/renew/osVersion";
        public static final String OTA_PUSH_FEEDBACK_URI = "v4/notification/feedback";
        public static final String OTA_REQUEST_DRIVING_ROUTES_URI = "v4/vehicle/routes";
        public static final String OTA_REQUEST_OS_VERSION_URI = "v4/vehicle/osVersion";
        public static final String OTA_SYNC_ECU_URI = "v4/syncEcu";
        public static final String OTA_UPGRADE_URI = "v4/upgrade";
        public static final String OTA_UPLOAD_URI = "v4/upload";
        public static final String OTA_URI_VERSION = "v4";
    }

    /* loaded from: classes2.dex */
    public static class ServerPush {
        public static final String CONNECTOR = ":";
        public static final String FROM = "from";
        public static final String FROM_PHONE = "app";
        public static final String TRACE_ID = "traceId";
    }
}
