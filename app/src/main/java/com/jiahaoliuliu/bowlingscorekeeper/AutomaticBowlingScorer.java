package com.jiahaoliuliu.bowlingscorekeeper;

import android.util.Log;

import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;
import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;
import com.jiahaoliuliu.bowlingscorekeeper.model.LastFrame;
import com.jiahaoliuliu.bowlingscorekeeper.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that calculates the automatic bowling scores
 */
public class AutomaticBowlingScorer {

    private static final int MAXIMUM_SCORE_NUMBER = 10;

    public final List<IFrame> framesList;
    public int lastNonScorePosition;
    public boolean isGameOver;

    public AutomaticBowlingScorer() {
        framesList = new ArrayList<>();
        lastNonScorePosition = 0;
    }

    /**
     * It returns the number of the frame containing the ball about to be rolled
     * @return
     */
    public int frameNumber() {
        return framesList.size();
    }

    /**
     * Return the current score
     * @return
     *      The current score
     */
    public int scoreSoFar() {
        if (framesList.isEmpty() || lastNonScorePosition == 0) {
            return 0;
        }

        // The score from the last scored position
        return framesList.get(lastNonScorePosition - 1).getFinalScore();
    }

    /**
     * If the current game is over. This is the total number of
     * @return
     *      True if the current game is over
     *      False otherwise
     */
    public boolean gameIsOver() {
        return isGameOver;
    }

    /**
     * Method used to add a new roll's points. If the game is over, a new game will start
     * @param point
     * @return
     *      An array whose length is the number of frames COMPLETELY scored, whose contents are
     *      the cumulative scores for those frames
     */
    public List<IFrame> roll(Point point) {
        if (gameIsOver()) {
            restartNewGame();
        }

        IFrame frameToFill = getFrameToFill();
        frameToFill.addPoint(point);
        updateScores();
        // Deprecate the second update
        updateScores();
        return framesList;
    }

    private IFrame getFrameToFill() {
        // For the first frame
        if (framesList.isEmpty()) {
            Frame firstFrame = new Frame();
            framesList.add(firstFrame);
            return firstFrame;
        }

        // Get the last frame in the list
        IFrame lastFrame = framesList.get(framesList.size() - 1);
        if (!lastFrame.hasFinished()) {
            return lastFrame;
        }

        // The last frame in the list has finished
        IFrame nextFrame = (framesList.size() == MAXIMUM_SCORE_NUMBER - 1)?
                new LastFrame() : new Frame();
        framesList.add(nextFrame);
        return nextFrame;
    }

    private void restartNewGame() {
        framesList.clear();
        lastNonScorePosition = 0;
    }

    /**
     * Update all the possible scores
     * TODO: All this logic should be in the Frame itself. In the Add point method
     */
    private void updateScores() {
        // Precondition. the current non score position cannot be larger than the frames list size
        if (lastNonScorePosition >= framesList.size()) {
            return;
        }

        // Precondition. The last non scored frame shouldn't be null
        IFrame lastNonScoredFrame = framesList.get(lastNonScorePosition);
        if (lastNonScoredFrame == null) {
            // It is the limit of the frame
            return;
        }

        if (lastNonScoredFrame.isLastFrame()) {
            if (!lastNonScoredFrame.hasFinished()) {
                return;
            }

            if (lastNonScoredFrame.isStrike()) {
                // Calculate the point
                int score = scoreSoFar() + lastNonScoredFrame.getFirstRoll().getValue();
                // If the second roll is strike as well
                if (lastNonScoredFrame.getSecondRoll() == Point.STRIKE) {
                    score += lastNonScoredFrame.getSecondRoll().getValue();
                    score += lastNonScoredFrame.getThirdRoll().getValue();
                    lastNonScoredFrame.setFinalScore(score);
                    return;
                }
            } else if (lastNonScoredFrame.isSpare()) {
                lastNonScoredFrame.setFinalScore(scoreSoFar() + lastNonScoredFrame.getSecondRoll().getValue() +
                        lastNonScoredFrame.getThirdRoll().getValue());
                return;
            } else {
                lastNonScoredFrame.setFinalScore(scoreSoFar() + lastNonScoredFrame.getFirstRoll().getValue() +
                lastNonScoredFrame.getSecondRoll().getValue());
                return;
            }
        }

        if (lastNonScoredFrame.isStrike()) {

            IFrame nextFrame = getNextFrame(lastNonScorePosition);

            // If the next frame does not exists, not do anything
            if (nextFrame == null) {
                return;
            }

            if (nextFrame.isStrike()) {
                IFrame nextNextFrame = getNextFrame(lastNonScorePosition + 1);

                // If the next next frame does not exists, not do anything
                if (nextNextFrame == null) {
                    return;
                }

                int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getCurrentScore()
                        + nextNextFrame.getFirstRoll().getValue();
                lastNonScoredFrame.setFinalScore(score);
                lastNonScorePosition++;
            } else {
                // Only count the score if it has finished
                if (nextFrame.hasFinished()) {
                    int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getCurrentScore();
                    lastNonScoredFrame.setFinalScore(score);
                    lastNonScorePosition++;
                }
            }

            return;
        }

        if (lastNonScoredFrame.isSpare()) {
            IFrame nextFrame = getNextFrame(lastNonScorePosition);
            if (nextFrame == null) {
                return;
            }

            int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getFirstRoll().getValue();
            lastNonScoredFrame.setFinalScore(score);
            lastNonScorePosition++;
            return;
        }

        // For normal cases. The score is added only if the current frame has finished.
        // This is an implicit condition for Strike and Spare
        if (lastNonScoredFrame.hasFinished()) {
            int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore();
            lastNonScoredFrame.setFinalScore(score);
            lastNonScorePosition++;
        }
    }

    private IFrame getNextFrame(int currentFramePosition) {
        // Precondition
        if (framesList.isEmpty()) {
            return null;
        }

        // If it is the last frame, return null
        if (framesList.size() - 1 <= currentFramePosition) {
            return null;
        }

        return framesList.get(currentFramePosition + 1);
    }
}
