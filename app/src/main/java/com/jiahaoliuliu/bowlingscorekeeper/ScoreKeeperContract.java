package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseModel;
import com.jiahaoliuliu.bowlingscorekeeper.mvp.BasePresenter;
import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseView;

import java.util.List;

/**
 * The contract for the MVP of this feature
 */
public class ScoreKeeperContract {

    public interface View extends BaseView {

        /**
         * Update the view with a new scores list
         */
        void updateView(List<Integer> scoresList);
    }

    public interface Presenter extends BasePresenter {

    }

    public interface Model extends BaseModel {

    }
}
