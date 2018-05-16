package com.jiahaoliuliu.bowlingscorekeeper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BasicModule {

    @Provides
    @Singleton
    static AutomaticBowlingScorer provideAutomaticBowlingScorer() {
        return new AutomaticBowlingScorer();
    }

    @Provides
    @Singleton
    static ScoreKeeperContract.Model provideScoreKeeperContractModel() {
        return new ScoreKeeperModel();
    }

    @Provides
    @Singleton
    static ScoreKeeperContract.Presenter provideScoreKeeperContractPresenter() {
        return new ScoreKeeperPresenter();
    }
}
