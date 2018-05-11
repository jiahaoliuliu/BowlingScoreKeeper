package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;
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
         * Update the view with the new list of frames
         */
        void updateView(List<Frame> framesList);
    }

    public interface Presenter extends BasePresenter {

    }

    public interface Model extends BaseModel {

    }
}
