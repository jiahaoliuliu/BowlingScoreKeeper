package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseView;

public class ScoreKeeperPresenter implements ScoreKeeperContract.Presenter {

    private ScoreKeeperContract.View view;
    private final ScoreKeeperContract.Model model;
    private final AutomaticBowlingScorer scorer;

    public ScoreKeeperPresenter() {
        this.model = new ScoreKeeperModel();
        this.scorer = new AutomaticBowlingScorer();
    }

    @Override
    public void setView(BaseView baseView) {
        this.view = (ScoreKeeperContract.View)baseView;
    }

    @Override
    public void onViewCreated() {
        // Add scores list
    }

    @Override
    public void onViewVisible() {
        // Do nothing yet
    }

    @Override
    public void onViewNonVisible() {
        // Do nothing yet
    }

    @Override
    public void onViewDestroyed() {
        // Do nothing yet
    }
}
