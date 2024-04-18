package com.xiaopeng.ota.auto.service;

import com.xiaopeng.ota.auto.CarApi;
import com.xiaopeng.ota.auto.callback.CarServiceCallback;
/* loaded from: classes2.dex */
public abstract class BaseService implements ICarService {
    protected ICarService mICarService;

    protected abstract ICarService createD20Service();

    protected abstract ICarService createD21Service();

    protected abstract ICarService createD25Service();

    protected abstract ICarService createE28Service();

    protected abstract String getName();

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public BaseService() {
        char c;
        String carVersion = CarApi.getCarVersion();
        switch (carVersion.hashCode()) {
            case 66946:
                if (carVersion.equals("D20")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 66947:
                if (carVersion.equals("D21")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 67044:
                if (carVersion.equals("D55")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 67915:
                if (carVersion.equals("E28")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.mICarService = createD20Service();
        } else if (c == 1) {
            this.mICarService = createD21Service();
        } else if (c == 2 || c == 3) {
            this.mICarService = createE28Service();
        }
        if (this.mICarService != null) {
            return;
        }
        throw new RuntimeException("Error strategy: " + getName() + " carVersion: " + carVersion);
    }

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public void registerCarService() {
        this.mICarService.registerCarService();
    }

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public void addCarServiceCallback(CarServiceCallback carServiceCallback) {
        this.mICarService.addCarServiceCallback(carServiceCallback);
    }

    @Override // com.xiaopeng.ota.auto.service.ICarService
    public void removeCarServiceCallback(CarServiceCallback carServiceCallback) {
        this.mICarService.removeCarServiceCallback(carServiceCallback);
    }
}
