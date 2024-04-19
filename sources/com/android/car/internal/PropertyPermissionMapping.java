package com.android.car.internal;

import android.car.Car;
import android.car.VehiclePropertyIds;
import android.util.SparseArray;
/* loaded from: classes.dex */
public final class PropertyPermissionMapping {
    private static final int VENDOR_MASK = 536870912;
    private static final Permission VENDOR_PERMISION = Permission.of(Car.PERMISSION_VENDOR_EXTENSION);
    private final SparseArray<Permission> mPermissions = new SparseArray<>();

    private static boolean isVendorExtension(int i) {
        return (i & 536870912) == 536870912;
    }

    public PropertyPermissionMapping() {
        map(Car.PERMISSION_CONTROL_CAR_DOORS, 373295872, 373295873, 371198722);
        map(Car.PERMISSION_CONTROL_CAR_MIRRORS, 339741504, 339741505, 339741506, 339741507, 287312708, 287312709);
        map(Car.PERMISSION_CONTROL_CAR_SEATS, 356518784, 356518785, 354421634, 356518787, 356518788, 356518789, 356518790, 356518791, 356518792, 356518793, 356518794, 356518795, 356518796, 356518797, 356518798, 356518799, 356518800, 356518801, 356518802, 356518803, 356518804, 289409941, 356518806, 356518807, 356518808, 356518809, 356518810);
        map(Car.PERMISSION_CONTROL_CAR_WINDOWS, 322964416, 322964417, 320867268);
        map(Car.PERMISSION_CONTROL_CAR_CLIMATE, 356517120, 356517121, 358614274, 358614275, 320865540, 354419973, 354419974, 354419975, Integer.valueOf((int) VehiclePropertyIds.HVAC_RECIRC_ON), 354419977, 354419978, 356517131, 339739916, 289408269, 289408270, 356517135, 354419984, 356582673, 354419986, 356517139);
        map(Car.PERMISSION_IDENTIFICATION, 286261504);
        map(Car.PERMISSION_CAR_INFO, 286261505, 286261506, 289407235, 291504388, 289472773, 291504390, 289472775, 289407240, 289407241, 356516106);
        map(Car.PERMISSION_MILEAGE, 291504644);
        map(Car.PERMISSION_SPEED, 291504647, 291504648, 290521862);
        map(Car.PERMISSION_CAR_ENGINE_DETAILED, 291504897, 289407747, 291504900, 291504901);
        map(Car.PERMISSION_ENERGY, 291504903, 291504905, 291504908, 291504904, 287310853);
        map(Car.PERMISSION_ENERGY_PORTS, 287310600, 287310602, 287310603);
        map(Car.PERMISSION_TIRES, 392168201);
        map(Car.PERMISSION_POWERTRAIN, 289408000, 289408001, 287310850, 287310851, 289408009);
        map(Car.PERMISSION_EXTERIOR_ENVIRONMENT, 287310855, 291505923);
        map(Car.PERMISSION_CAR_DYNAMICS_STATE, 287310858, 287310859);
        map(Car.PERMISSION_EXTERIOR_LIGHTS, 289408008, 289410560, 289410561, 289410562, 289410563);
        map(Car.PERMISSION_EXTERIOR_LIGHTS, Car.PERMISSION_CONTROL_EXTERIOR_LIGHTS, 289410576, 289410577, 289410578, 289410579);
    }

    public String getReadPermission(int i) {
        Permission permission = getPermission(i);
        if (permission == null) {
            return null;
        }
        return permission.mReadPermission;
    }

    public String getWritePermission(int i) {
        Permission permission = getPermission(i);
        if (permission == null) {
            return null;
        }
        return permission.mWritePermission;
    }

    private Permission getPermission(int i) {
        return isVendorExtension(i) ? VENDOR_PERMISION : this.mPermissions.get(i);
    }

    private void map(String str, String str2, Integer... numArr) {
        map(Permission.of(str, str2), numArr);
    }

    private void map(String str, Integer... numArr) {
        map(Permission.of(str), numArr);
    }

    private void map(Permission permission, Integer[] numArr) {
        for (Integer num : numArr) {
            this.mPermissions.put(num.intValue(), permission);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Permission {
        private final String mReadPermission;
        private final String mWritePermission;

        private Permission(String str, String str2) {
            this.mReadPermission = str;
            this.mWritePermission = str2;
        }

        static Permission of(String str, String str2) {
            return new Permission(str, str2);
        }

        static Permission of(String str) {
            return new Permission(str, str);
        }
    }
}
