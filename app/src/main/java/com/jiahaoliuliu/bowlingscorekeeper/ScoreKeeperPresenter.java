package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.model.Point;
import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseView;

import javax.inject.Inject;

public class ScoreKeeperPresenter implements ScoreKeeperContract.Presenter {

    private ScoreKeeperContract.View view;

    @Inject
    AutomaticBowlingScorer scorer;

    @Inject
    ScoreKeeperContract.Model model;

    public ScoreKeeperPresenter() {
        MyApplication.getSimpleComponent().inject(this);
    }

    @Override
    public void setView(BaseView baseView) {
        this.view = (ScoreKeeperContract.View)baseView;
    }

    @Override
    public void onViewCreated() {
        // Add scores list

        // Randomly roll the points to the system
        //  Frame 1
        view.updateView(scorer.roll(Point.NINE));
        view.updateView(scorer.roll(Point.SPARE));

        // Frame 2
        view.updateView(scorer.roll(Point.ZERO));
        view.updateView(scorer.roll(Point.SPARE));

        // Frame 3
        view.updateView(scorer.roll(Point.STRIKE));

        // Frame 4
        view.updateView(scorer.roll(Point.STRIKE));

        // Frame 5
        view.updateView(scorer.roll(Point.SIX));
        view.updateView(scorer.roll(Point.TWO));

        // Frame 6
        view.updateView(scorer.roll(Point.SEVEN));
        view.updateView(scorer.roll(Point.SPARE));

        // Frame 7
        view.updateView(scorer.roll(Point.EIGHT));
        view.updateView(scorer.roll(Point.SPARE));

        // Frame 8
        view.updateView(scorer.roll(Point.STRIKE));

        // Frame 9
        view.updateView(scorer.roll(Point.NINE));
        view.updateView(scorer.roll(Point.ZERO));

        // Frame 10
        view.updateView(scorer.roll(Point.STRIKE));
        view.updateView(scorer.roll(Point.STRIKE));
        view.updateView(scorer.roll(Point.EIGHT));
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
