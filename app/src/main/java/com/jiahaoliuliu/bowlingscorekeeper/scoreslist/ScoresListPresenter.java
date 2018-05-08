package com.jiahaoliuliu.bowlingscorekeeper.scoreslist;

import java.util.List;

public interface ScoresListPresenter {

    /**
     * Used to bind the score to each one of the score row views
     * @param scoreRowView
     *      The score row view to be bind
     * @param position
     *      The position of the score row view
     */
    void onBindScoreRowViewAtPosition(ScoreRowView scoreRowView, int position);

    /**
     * Get the number of scores
     * @return
     */
    int getScoresListCount();

    /**
     * Set scores list
     * @param scoresList
     */
    void setScoresList(List<Integer> scoresList);
}
