package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that calculates the automatic bowling scores
 */
public class AutomaticBowlingScorer {

    private static final int MAXIMUM_SCORE_NUMBER = 10;
    private List<Integer> scoresList;

    public AutomaticBowlingScorer() {
        scoresList = new ArrayList<>();
    }

    /**
     * It returns the number of the frame containing the ball about to be rolled
     * @return
     */
    public int frameNumber() {
        // TODO: implement this
        return 0;
    }

    /**
     * Return the current score
     * @return
     *      The current score
     */
    public int scoreSoFar() {
        // The last score
        return scoresList.get(scoresList.size() - 1);
    }

    /**
     * If the current game is over. This is the total number of
     * @return
     *      True if the current game is over
     *      False otherwise
     */
    public boolean gameIsOver() {
        return scoresList.size() == MAXIMUM_SCORE_NUMBER;
    }

    /**
     * Method used to add a new roll's points. If the game is over, a new game will start
     * @param point
     * @return
     *      An array whose length is the number of frames COMPLETELY scored, whose contents are
     *      the cumulative scores for those frames
     */
    public List<Integer> roll(Point point) {
        List<Integer> scoresList = new ArrayList<>();
        // TODO: Implement this

        return scoresList;
    }

    private void startNewGame() {
        // TODO: Implement this
    }
}
