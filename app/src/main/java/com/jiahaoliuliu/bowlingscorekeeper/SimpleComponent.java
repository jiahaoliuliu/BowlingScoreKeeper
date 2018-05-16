package com.jiahaoliuliu.bowlingscorekeeper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BasicModule.class)
interface SimpleComponent {

    // View
    void inject(MainActivity mainActivity);

    // Presenter
    void inject(ScoreKeeperPresenter scoreKeeperPresenter);
}
