package com.twistedeqations.dagger2tutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.twistedeqations.dagger2tutorial.network.DateTimeConverter;
import com.twistedeqations.dagger2tutorial.network.GithubService;

import org.joda.time.DateTime;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

public class GithubApplication extends Application {

    private Picasso picasso;

    public static GithubApplication get(Activity activity) {
        return (GithubApplication)activity.getApplication();
    }

    private GithubService githubService;

    @Override
    public void onCreate() {
        super.onCreate();

        //           Activity
        //           /       \
        // GithubService      Picasso
        //      /              /
        // retrofit       OkHttp3Downloader
        //     /    \     /
        //   gson    okhttp
        //            /    \
        //         logger  cache
        //          /         \
        //        timber      file

        // CONTEXT
        Context context = this;

        Timber.plant(new Timber.DebugTree());


        // PICASSO
        picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        // GSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();

    }

    public GithubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}