package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.IOException;
/* loaded from: classes.dex */
public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "AssetPathFetcher";
    private final AssetManager assetManager;
    private final String assetPath;
    private T data;

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    protected abstract void close(T t) throws IOException;

    protected abstract T loadResource(AssetManager assetManager, String str) throws IOException;

    public AssetPathFetcher(AssetManager assetManager, String str) {
        this.assetManager = assetManager;
        this.assetPath = str;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            this.data = loadResource(this.assetManager, this.assetPath);
            dataCallback.onDataReady((T) this.data);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to load data from asset manager", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        T t = this.data;
        if (t == null) {
            return;
        }
        try {
            close(t);
        } catch (IOException unused) {
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
