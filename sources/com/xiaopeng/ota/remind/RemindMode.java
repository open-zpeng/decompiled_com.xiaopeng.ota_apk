package com.xiaopeng.ota.remind;
/* loaded from: classes2.dex */
public class RemindMode {
    public static final int MODE_BELT = 4;
    public static final int MODE_CLICKED = 1;
    public static final int MODE_GEAR = 3;
    public static final int MODE_POWER = 0;
    public static final int MODE_PUSHED = 2;
    public static final int MODE_UNKNOWN = -1;
    public static final int MODE_USB = 5;
    private VehicleLocation location;
    private int remindMode;
    private DrivingRoutePoint routePoint;

    public RemindMode(int i) {
        this.remindMode = i;
    }

    public RemindMode(int i, DrivingRoutePoint drivingRoutePoint) {
        this.remindMode = i;
        this.routePoint = drivingRoutePoint;
    }

    public RemindMode(int i, VehicleLocation vehicleLocation) {
        this.remindMode = i;
        this.location = vehicleLocation;
    }

    public int getRemindMode() {
        return this.remindMode;
    }

    public void setRemindMode(int i) {
        this.remindMode = i;
    }

    public DrivingRoutePoint getRoutePoint() {
        return this.routePoint;
    }

    public void setRoutePoint(DrivingRoutePoint drivingRoutePoint) {
        this.routePoint = drivingRoutePoint;
    }

    public VehicleLocation getLocation() {
        return this.location;
    }

    public void setLocation(VehicleLocation vehicleLocation) {
        this.location = vehicleLocation;
    }
}
