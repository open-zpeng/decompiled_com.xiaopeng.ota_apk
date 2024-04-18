package com.xiaopeng.fota.sdk;

import android.car.hardware.hvac.CarHvacManager;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public enum EcuType {
    BMS(4, "电池管理模块"),
    IPUR(5, "电机控制单元-后驱"),
    IPU(261, "电机控制单元"),
    IPUF(6, "电机控制单元-前驱"),
    OBC(10, "车载充电器"),
    CCS(266, "充电控制器"),
    DCDC(11, "直流逆变器"),
    EVCC(12, "电动车通信模块"),
    ESP(16, "电子稳定系统"),
    EPBR(17, "电子驻车-右"),
    CDC(19, "连续阻尼控制系统"),
    EPS(20, "电动助力转向"),
    IBT(21, "智能刹车系统"),
    XPU(22, "自动驾驶模块"),
    XPUHDMAP(278, "自动驾驶资源模块"),
    LIDARL(534, "激光雷达-左"),
    LIDARR(790, "激光雷达-右"),
    XPUCNGPMAP(1046, "城市NGP地图"),
    EPBL(23, "电子驻车-左"),
    TPMS(29, "胎压监测系统"),
    BCM(32, "车身控制器"),
    CGW(33, "网关"),
    HVAC(36, "空调控制器"),
    TMC(292, "热管理控制器"),
    AVAS(37, "低速提示音系统"),
    MSB(38, "电动马达安全带"),
    SRS(39, "安全气囊"),
    ICM(40, "组合仪表"),
    MCU(296, "大屏微控制模块"),
    ICMDMCU(552, "仪表显示模块"),
    BCMSLAVE(41, "车身控制器"),
    PSU(42, "安全控制器"),
    AMCU(43, "大屏微控制模块"),
    T4G(44, "4G模块"),
    CDU(45, "大屏系统"),
    TMCU(46, "4G微控制模块"),
    AMP(47, "音响功放"),
    MRR(48, "中距测距雷达"),
    VPM(49, "视觉感知模块"),
    SRRRL(50, "毫米波雷达-左后"),
    SRRRR(51, "毫米波雷达-右后"),
    MSM(55, "座椅调节记忆模块"),
    AVM(57, "全景摄像头"),
    SRRFL(58, "毫米波雷达-左前"),
    SRRFR(59, "毫米波雷达-右前"),
    IMU(60, "惯性测量单元"),
    SCU(63, "智能控制器"),
    PAS(64, "泊车辅助系统"),
    ATLS(65, "氛围灯控制器"),
    NFC(66, "近距离无线通讯模块"),
    CWC(67, "无线充电模块"),
    LLU(68, "灯语控制器"),
    ALS(69, "自动大灯高度调节系统"),
    MSMD(70, "主驾座椅调节记忆模块"),
    MSMP(71, "副驾座椅调节记忆模块"),
    DHC(72, "门把手模块"),
    BLE(73, "蓝牙模块"),
    SDCL(74, "剪刀门控制器-左"),
    SDCR(75, "剪刀门控制器-右"),
    BLEFL(83, "蓝牙从模块-左"),
    BLEFR(84, "蓝牙从模块-右"),
    BLER(85, "蓝牙从模块-后"),
    VCU(CarHvacManager.HVAC_WIND_OFF, "整车控制器"),
    ACU(241, "安全气囊"),
    APP(256, "SOTA");
    
    private static final String GW = "GW";
    private String desc;
    private int id;
    private static final SparseArray<EcuType> sIds = new SparseArray<>();
    private static final Map<String, EcuType> sNames = new HashMap();
    private static final EcuType[] SUPPORTED_ECUS = new EcuType[values().length];

    static {
        EcuType[] values;
        int i = 0;
        for (EcuType ecuType : values()) {
            sIds.put(ecuType.id, ecuType);
            sNames.put(ecuType.name(), ecuType);
            if (ecuType.name().equals(CGW.name())) {
                sNames.put(GW, ecuType);
            }
            SUPPORTED_ECUS[i] = ecuType;
            i++;
        }
    }

    EcuType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int id() {
        return this.id;
    }

    public String desc() {
        return this.desc;
    }

    public static EcuType valueByName(String str) {
        if (str.equals("4G")) {
            str = "T4G";
        }
        EcuType ecuType = sNames.get(str);
        if (ecuType != null) {
            return ecuType;
        }
        throw new IllegalArgumentException("Unknown ECU name " + str);
    }

    public static EcuType valueById(int i) {
        EcuType ecuType = sIds.get(i);
        if (ecuType != null) {
            return ecuType;
        }
        throw new IllegalArgumentException("Unknown ECU id " + i);
    }

    public static EcuType[] getSupportedEcus() {
        return SUPPORTED_ECUS;
    }

    public static List<EcuType> getAllTypes() {
        return Arrays.asList(values());
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.format("%s(0x%X)", name(), Integer.valueOf(this.id));
    }
}
