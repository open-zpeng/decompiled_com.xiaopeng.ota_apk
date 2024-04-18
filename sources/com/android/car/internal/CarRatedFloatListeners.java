package com.android.car.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class CarRatedFloatListeners<T> {
    private float mUpdateRate;
    private final Map<T, Float> mListenersToRate = new HashMap(4);
    protected long mLastUpdateTime = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public CarRatedFloatListeners(float f) {
        this.mUpdateRate = -2.14748365E9f;
        this.mUpdateRate = f;
    }

    public boolean contains(T t) {
        return this.mListenersToRate.containsKey(t);
    }

    public float getRate() {
        return this.mUpdateRate;
    }

    public boolean remove(T t) {
        this.mListenersToRate.remove(t);
        if (this.mListenersToRate.isEmpty()) {
            return false;
        }
        Float f = (Float) Collections.max(this.mListenersToRate.values());
        if (f.floatValue() != this.mUpdateRate) {
            this.mUpdateRate = f.floatValue();
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.mListenersToRate.isEmpty();
    }

    public boolean addAndUpdateRate(T t, float f) {
        Float put = this.mListenersToRate.put(t, Float.valueOf(f));
        if (this.mUpdateRate < f) {
            this.mUpdateRate = f;
            return true;
        } else if (put == null || put.floatValue() != this.mUpdateRate) {
            return false;
        } else {
            this.mUpdateRate = ((Float) Collections.max(this.mListenersToRate.values())).floatValue();
            return false;
        }
    }

    public Collection<T> getListeners() {
        return this.mListenersToRate.keySet();
    }
}
