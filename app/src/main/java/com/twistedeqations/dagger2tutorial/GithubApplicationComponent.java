package com.twistedeqations.dagger2tutorial;

import com.squareup.picasso.Picasso;
import com.twistedeqations.dagger2tutorial.network.GithubService;

import dagger.Component;

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubApplicationComponent {

    GithubService getGithubService();

    Picasso getPicasso();
}
