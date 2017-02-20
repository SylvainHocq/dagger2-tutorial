package com.twistedeqations.dagger2tutorial.screens;

import com.twistedeqations.dagger2tutorial.GithubApplicationComponent;
import com.twistedeqations.dagger2tutorial.network.GithubService;
import com.twistedeqations.dagger2tutorial.screens.home.AdapterRepos;

import dagger.Component;

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubApplicationComponent.class) //, dependencies = GithubApplicationComponent.class
public interface HomeActivityComponent {

    AdapterRepos adapterRepos();

    GithubService githubService();

}
