package com.twistedeqations.dagger2tutorial;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    public OkHttpClient okHttpClient(Interceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    public Interceptor interceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }

    @Provides
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

}
