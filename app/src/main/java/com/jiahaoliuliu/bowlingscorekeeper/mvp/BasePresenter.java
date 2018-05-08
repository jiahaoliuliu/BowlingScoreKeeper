package com.jiahaoliuliu.bowlingscorekeeper.mvp;

/**
 * The base presenter that all the presenter need to implement
 */
public interface BasePresenter {

    /**
     * Set the current view
     */
    void setView(BaseView baseView);

    /**
     * Method called when the view is created
     */
    void onViewCreated();

    /**
     * Method called when the view is visible
     */
    void onViewVisible();

    /**
     * Method called when the view is not visible
     */
    void onViewNonVisible();

    /**
     * Method called when the view has been destroyed
     */
    void onViewDestroyed();
}
