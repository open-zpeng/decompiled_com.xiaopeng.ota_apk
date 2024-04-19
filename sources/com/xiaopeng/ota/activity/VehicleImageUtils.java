package com.xiaopeng.ota.activity;

import android.car.Car;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xiaopeng.ota.OTAManager;
import com.xiaopeng.ota.R;
import com.xiaopeng.ota.VehicleFeature;
import com.xiaopeng.ota.presenter.config.ConfigManager;
import com.xiaopeng.ota.utils.LogUtils;
import com.xiaopeng.xui.widget.XImageView;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class VehicleImageUtils {
    private static final String TAG = "VehicleImageUtils";
    private static Map<Integer, Integer> sVehicleImages = new HashMap();
    private static String KEY_VEHICLE_BODY_IMAGES = "VEHICLE_BODY_IMAGES";

    static {
        char c;
        String cduType = VehicleFeature.getCduType();
        int hashCode = cduType.hashCode();
        if (hashCode != 2562) {
            if (hashCode == 79487 && cduType.equals(Car.CAR_CDU_TYPE_Q3_D55A)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (cduType.equals("Q3")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return;
            }
            sVehicleImages.put(3, Integer.valueOf(R.mipmap.d55a_vehicle_black));
            sVehicleImages.put(4, Integer.valueOf(R.mipmap.d55a_vehicle_gray));
            sVehicleImages.put(12, Integer.valueOf(R.mipmap.d55a_vehicle_white));
            sVehicleImages.put(13, Integer.valueOf(R.mipmap.d55a_vehicle_green));
            sVehicleImages.put(14, Integer.valueOf(R.mipmap.d55a_vehicle_silvery));
            return;
        }
        sVehicleImages.put(1, Integer.valueOf(R.mipmap.vehicle_red));
        sVehicleImages.put(2, Integer.valueOf(R.mipmap.vehicle_white));
        sVehicleImages.put(3, Integer.valueOf(R.mipmap.vehicle_black));
        sVehicleImages.put(4, Integer.valueOf(R.mipmap.vehicle_gray));
        sVehicleImages.put(5, Integer.valueOf(R.mipmap.vehicle_silvery));
        sVehicleImages.put(8, Integer.valueOf(R.mipmap.vehicle_purple));
        sVehicleImages.put(10, Integer.valueOf(R.mipmap.vehicle_green));
        sVehicleImages.put(12, Integer.valueOf(R.mipmap.vehicle_new_white));
    }

    public static void setVehicleImage(XImageView xImageView, int i) {
        LogUtils.d(TAG, "Set vehicle image by color = " + i);
        Integer num = sVehicleImages.get(Integer.valueOf(i));
        if (num == null) {
            ConfigManager configManager = OTAManager.getConfigManager();
            String string = configManager.getString(KEY_VEHICLE_BODY_IMAGES + "." + i, null);
            if (string == null) {
                xImageView.setImageResource(getDefaultImageResource());
                return;
            } else {
                Glide.with(OTAManager.getContext()).load(string).apply(new RequestOptions().placeholder(getDefaultImageResource())).into(xImageView);
                return;
            }
        }
        xImageView.setImageResource(num.intValue());
    }

    private static int getDefaultImageResource() {
        char c;
        String cduType = VehicleFeature.getCduType();
        int hashCode = cduType.hashCode();
        if (hashCode != 2562) {
            if (hashCode == 79487 && cduType.equals(Car.CAR_CDU_TYPE_Q3_D55A)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (cduType.equals("Q3")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return R.mipmap.d55a_vehicle_white;
            }
            return R.mipmap.logo_main;
        }
        return R.mipmap.logo_main;
    }
}
