package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class FileLoader<Data> implements ModelLoader<File, Data> {
    private static final String TAG = "FileLoader";
    private final FileOpener<Data> fileOpener;

    /* loaded from: classes.dex */
    public interface FileOpener<Data> {
        void close(Data data) throws IOException;

        Class<Data> getDataClass();

        Data open(File file) throws FileNotFoundException;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull File file) {
        return true;
    }

    public FileLoader(FileOpener<Data> fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(@NonNull File file, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new FileFetcher(file, this.fileOpener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class FileFetcher<Data> implements DataFetcher<Data> {
        private Data data;
        private final File file;
        private final FileOpener<Data> opener;

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
        }

        FileFetcher(File file, FileOpener<Data> fileOpener) {
            this.file = file;
            this.opener = fileOpener;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.data = this.opener.open(this.file);
                dataCallback.onDataReady((Data) this.data);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable(FileLoader.TAG, 3)) {
                    Log.d(FileLoader.TAG, "Failed to open file", e);
                }
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            Data data = this.data;
            if (data != null) {
                try {
                    this.opener.close(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public Class<Data> getDataClass() {
            return this.opener.getDataClass();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    /* loaded from: classes.dex */
    public static class Factory<Data> implements ModelLoaderFactory<File, Data> {
        private final FileOpener<Data> opener;

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public final void teardown() {
        }

        public Factory(FileOpener<Data> fileOpener) {
            this.opener = fileOpener;
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public final ModelLoader<File, Data> build(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new FileLoader(this.opener);
        }
    }

    /* loaded from: classes.dex */
    public static class StreamFactory extends Factory<InputStream> {
        public StreamFactory() {
            super(new FileOpener<InputStream>() { // from class: com.bumptech.glide.load.model.FileLoader.StreamFactory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public InputStream open(File file) throws FileNotFoundException {
                    return new FileInputStream(file);
                }

                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public void close(InputStream inputStream) throws IOException {
                    inputStream.close();
                }

                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class FileDescriptorFactory extends Factory<ParcelFileDescriptor> {
        public FileDescriptorFactory() {
            super(new FileOpener<ParcelFileDescriptor>() { // from class: com.bumptech.glide.load.model.FileLoader.FileDescriptorFactory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public ParcelFileDescriptor open(File file) throws FileNotFoundException {
                    return ParcelFileDescriptor.open(file, 268435456);
                }

                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public void close(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                    parcelFileDescriptor.close();
                }

                @Override // com.bumptech.glide.load.model.FileLoader.FileOpener
                public Class<ParcelFileDescriptor> getDataClass() {
                    return ParcelFileDescriptor.class;
                }
            });
        }
    }
}
