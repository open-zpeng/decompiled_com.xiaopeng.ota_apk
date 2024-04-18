package com.xiaopeng.speech.protocol.node.charge;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.ChargeEvent;
/* loaded from: classes2.dex */
public class ChargeNode_Processor implements ICommandProcessor {
    private ChargeNode mTarget;

    public ChargeNode_Processor(ChargeNode chargeNode) {
        this.mTarget = chargeNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1965971708:
                if (str.equals(ChargeEvent.RESTART)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1734769001:
                if (str.equals(ChargeEvent.START)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1609014560:
                if (str.equals(ChargeEvent.CHARGE_CHANGE_WLTP_MILEAGE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1237470650:
                if (str.equals(ChargeEvent.CHARGE_TRUNK_POWER_POWER_ON)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1025791187:
                if (str.equals(ChargeEvent.STOP)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -898173837:
                if (str.equals(ChargeEvent.MODE_SMART_CLOSE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -879718580:
                if (str.equals(ChargeEvent.PORT_OPEN)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -41146710:
                if (str.equals(ChargeEvent.MODE_SMART_OFF)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 189571877:
                if (str.equals(ChargeEvent.MODE_PERCENT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 277827503:
                if (str.equals(ChargeEvent.MODE_FULL)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 293115368:
                if (str.equals(ChargeEvent.CHARGE_TRUNK_POWER_POWER_OFF)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 511246920:
                if (str.equals(ChargeEvent.CHARGE_CHANGE_DYNAMIC_MILEAGE)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 552862020:
                if (str.equals(ChargeEvent.MODE_SMART_ON)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 747198041:
                if (str.equals(ChargeEvent.UI_OPEN)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 970243195:
                if (str.equals(ChargeEvent.DISCHARGE_LIMIT_VALUE_SET)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1677111241:
                if (str.equals(ChargeEvent.UI_CLOSE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2089286213:
                if (str.equals(ChargeEvent.CHARGE_CHANGE_NEDC_MILEAGE)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 2126369895:
                if (str.equals(ChargeEvent.CHARGE_CHANGE_CLTC_MILEAGE)) {
                    c = 16;
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
                this.mTarget.onPortOpen(str, str2);
                return;
            case 1:
                this.mTarget.onStart(str, str2);
                return;
            case 2:
                this.mTarget.onRestart(str, str2);
                return;
            case 3:
                this.mTarget.onStop(str, str2);
                return;
            case 4:
                this.mTarget.onUiOpen(str, str2);
                return;
            case 5:
                this.mTarget.onUiClose(str, str2);
                return;
            case 6:
                this.mTarget.onModePercent(str, str2);
                return;
            case 7:
                this.mTarget.onModeFull(str, str2);
                return;
            case '\b':
                this.mTarget.onModeSmartOn(str, str2);
                return;
            case '\t':
                this.mTarget.onModeSmartClose(str, str2);
                return;
            case '\n':
                this.mTarget.onModeSmartOff(str, str2);
                return;
            case 11:
                this.mTarget.onChangeWltpMileageMode(str, str2);
                return;
            case '\f':
                this.mTarget.onChangeNedcMileageMode(str, str2);
                return;
            case '\r':
                this.mTarget.onChargeTrunkPowerOpen(str, str2);
                return;
            case 14:
                this.mTarget.onChargeTrunkPowerClose(str, str2);
                return;
            case 15:
                this.mTarget.setDischargeLimit(str, str2);
                return;
            case 16:
                this.mTarget.onChangeCltcMileageMode(str, str2);
                return;
            case 17:
                this.mTarget.onChangeDynamicMileageMode(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{ChargeEvent.PORT_OPEN, ChargeEvent.START, ChargeEvent.RESTART, ChargeEvent.STOP, ChargeEvent.UI_OPEN, ChargeEvent.UI_CLOSE, ChargeEvent.MODE_PERCENT, ChargeEvent.MODE_FULL, ChargeEvent.MODE_SMART_ON, ChargeEvent.MODE_SMART_CLOSE, ChargeEvent.MODE_SMART_OFF, ChargeEvent.CHARGE_CHANGE_WLTP_MILEAGE, ChargeEvent.CHARGE_CHANGE_NEDC_MILEAGE, ChargeEvent.CHARGE_TRUNK_POWER_POWER_ON, ChargeEvent.CHARGE_TRUNK_POWER_POWER_OFF, ChargeEvent.DISCHARGE_LIMIT_VALUE_SET, ChargeEvent.CHARGE_CHANGE_CLTC_MILEAGE, ChargeEvent.CHARGE_CHANGE_DYNAMIC_MILEAGE};
    }
}
