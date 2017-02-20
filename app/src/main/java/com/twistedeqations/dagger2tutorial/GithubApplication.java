package com.twistedeqations.dagger2tutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.twistedeqations.dagger2tutorial.network.GithubService;

import timber.log.Timber;

public class GithubApplication extends Application {

    public static final String DAGGER_2 = "dagger2";
    private Picasso picasso;
    private GithubApplicationComponent applicationComponent;

    public static GithubApplication get(Activity activity) {
        return (GithubApplication) activity.getApplication();
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

        //                .githubServiceModule(new GithubServiceModule())   //not necessary
//                .networkModule(new NetworkModule())               //not necessary
//                .picassoModule(new PicassoModule())               //not necessary
        applicationComponent = DaggerGithubApplicationComponent
                .builder()
                .contextModule(new ContextModule(this))
                //                .githubServiceModule(new GithubServiceModule())   //not necessary
                //                .networkModule(new NetworkModule())               //not necessary
                //                .picassoModule(new PicassoModule())               //not necessary
                .build();

        githubService = applicationComponent.getGithubService();
        picasso = applicationComponent.getPicasso();

        GithubService githubService2 = applicationComponent.getGithubService();
        Picasso picasso2 = applicationComponent.getPicasso();

        Log.i(DAGGER_2, "githubService" + githubService);
        Log.i(DAGGER_2, "picasso" + picasso);

        Log.i(DAGGER_2, "githubService2" + githubService2);
        Log.i(DAGGER_2, "picasso2" + picasso2);

    }

    public GithubApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}