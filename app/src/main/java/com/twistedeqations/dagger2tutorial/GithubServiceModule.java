package com.twistedeqations.dagger2tutorial;


import com.google.gson.Gson;
import com.twistedeqations.dagger2tutorial.network.GithubService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubServiceModule {

    @Provides
    public GithubService githubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build();
    }

}
