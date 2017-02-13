package com.twistedeqations.dagger2tutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;
import com.twistedeqations.dagger2tutorial.network.GithubService;

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

        GithubApplicationComponent applicationComponent = DaggerGithubApplicationComponent
                .builder()
                .contextModule(new ContextModule(this))
                //                .githubServiceModule(new GithubServiceModule())   //not necessary
                //                .networkModule(new NetworkModule())               //not necessary
                //                .picassoModule(new PicassoModule())               //not necessary
                .build();

        githubService = applicationComponent.getGithubService();
        picasso = applicationComponent.getPicasso();

    }

    public GithubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}