package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseModel;
import com.jiahaoliuliu.bowlingscorekeeper.mvp.BasePresenter;
import com.jiahaoliuliu.bowlingscorekeeper.mvp.BaseView;

/**
 * The contract for the MVP of this feature
 */
public class ScoreKeeperContract {

    public interface View extends BaseView {

    }

    public interface Presenter extends BasePresenter {

    }

    public interface Model extends BaseModel {

    }
}
