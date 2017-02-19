package com.twistedeqations.dagger2tutorial;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Context context;

    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @GithubApplicationScope
    public Context context(){
        return context;
    }
}
