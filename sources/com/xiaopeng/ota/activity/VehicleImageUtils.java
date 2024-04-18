package com.xiaopeng.ota.activity;

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
        sVehicleImages.put(5, Integer.valueOf(R.mipmap.vehicle_silvery));
        sVehicleImages.put(3, Integer.valueOf(R.mipmap.vehicle_black));
        sVehicleImages.put(2, Integer.valueOf(R.mipmap.vehicle_white));
        sVehicleImages.put(1, Integer.valueOf(R.mipmap.vehicle_red));
        sVehicleImages.put(4, Integer.valueOf(R.mipmap.vehicle_gray));
        sVehicleImages.put(8, Integer.valueOf(R.mipmap.vehicle_purple));
        sVehicleImages.put(10, Integer.valueOf(R.mipmap.vehicle_green));
        if ("E28".equals(VehicleFeature.getCarType())) {
            sVehicleImages.put(12, Integer.valueOf(R.mipmap.vehicle_new_red));
            sVehicleImages.put(13, Integer.valueOf(R.mipmap.vehicle_new_white));
            sVehicleImages.put(14, Integer.valueOf(R.mipmap.vehicle_new_black));
        } else if ("D55".equals(VehicleFeature.getCarType())) {
            sVehicleImages.put(12, Integer.valueOf(R.mipmap.vehicle_new_white));
        }
    }

    public static void setVehicleImage(XImageView xImageView, int i) {
        LogUtils.d(TAG, "Set vehicle image by color = " + i);
        Integer num = sVehicleImages.get(Integer.valueOf(i));
        if (num == null) {
            ConfigManager configManager = OTAManager.getConfigManager();
            String string = configManager.getString(KEY_VEHICLE_BODY_IMAGES + "." + i, null);
            if (string == null) {
                xImageView.setImageResource(R.mipmap.logo_main);
                return;
            } else {
                Glide.with(OTAManager.getContext()).load(string).apply(new RequestOptions().placeholder(R.mipmap.logo_main)).into(xImageView);
                return;
            }
        }
        xImageView.setImageResource(num.intValue());
    }
}
