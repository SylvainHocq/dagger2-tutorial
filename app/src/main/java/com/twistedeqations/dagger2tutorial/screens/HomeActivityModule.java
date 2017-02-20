package com.twistedeqations.dagger2tutorial.screens;

import com.squareup.picasso.Picasso;
import com.twistedeqations.dagger2tutorial.screens.home.AdapterRepos;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {


    private final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @HomeActivityScope
    @Provides
    AdapterRepos adapterRepos(Picasso picasso) {
        return new AdapterRepos(homeActivity, picasso);
    }

}
