package com.jiahaoliuliu.bowlingscorekeeper;

import android.util.Log;

import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;
import com.jiahaoliuliu.bowlingscorekeeper.model.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class that calculates the automatic bowling scores
 */
public class AutomaticBowlingScorer {

    private static final int MAXIMUM_SCORE_NUMBER = 10;

    public final List<Frame> framesList;
    public int lastNonScorePosition;

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
        return framesList.get(lastNonScorePosition - 1).getScore();
    }

    /**
     * If the current game is over. This is the total number of
     * @return
     *      True if the current game is over
     *      False otherwise
     */
    public boolean gameIsOver() {
        return lastNonScorePosition == MAXIMUM_SCORE_NUMBER;
    }

    /**
     * Method used to add a new roll's points. If the game is over, a new game will start
     * @param point
     * @return
     *      An array whose length is the number of frames COMPLETELY scored, whose contents are
     *      the cumulative scores for those frames
     */
    public List<Integer> roll(Point point) {
        if (gameIsOver()) {
            restartNewGame();
        }

        Frame frameToFill = getFrameToFill();
        frameToFill.addPoint(point);
        updateScores();
        return generateCurrentScoresList();
    }

    private Frame getFrameToFill() {
        // For the first frame
        if (framesList.isEmpty()) {
            Frame firstFrame = new Frame();
            framesList.add(firstFrame);
            return firstFrame;
        }

        // Get the last frame
        Frame lastFrame = framesList.get(framesList.size() - 1);
        if (!lastFrame.hasFinished()) {
            return lastFrame;
        }

        // The last frame has finished
        Frame nextFrame = new Frame();
        nextFrame.setLastFrame(framesList.size() == MAXIMUM_SCORE_NUMBER - 1);
        framesList.add(nextFrame);
        return nextFrame;
    }

    private void restartNewGame() {
        framesList.clear();
        lastNonScorePosition = 0;
    }

    private List<Integer> generateCurrentScoresList() {
        Log.v("FramesList", "The content of the frameslist is " + framesList);

        List<Integer> currentScoresList = new ArrayList<>();
        for (int i = 0; i < lastNonScorePosition - 1; i++) {
            currentScoresList.add(framesList.get(i).getScore());
        }

        return currentScoresList;
    }

    /**
     * Update all the possible scores
     */
    private void updateScores() {
//        Frame lastNonScoredFrame = getNextFrame(lastNonScorePosition);
        Frame lastNonScoredFrame = framesList.get(lastNonScorePosition);
        if (lastNonScoredFrame == null) {
            // It is the limit of the frame
            return;
        }

        if (lastNonScoredFrame.isStrike()) {
            Frame nextFrame = getNextFrame(lastNonScorePosition);

            // If the next frame does not exists, not do anything
            if (nextFrame == null) {
                return;
            }

            if (nextFrame.isStrike()) {
                Frame nextNextFrame = getNextFrame(lastNonScorePosition + 1);

                // If the next next frame does not exists, not do anything
                if (nextNextFrame == null) {
                    return;
                }

                int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getCurrentScore()
                        + nextNextFrame.getFirstRoll().getValue();
                lastNonScoredFrame.setScore(score);
                lastNonScorePosition++;
            } else {
                // Only count the score if it has finished
                if (nextFrame.hasFinished()) {
                    int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getCurrentScore();
                    lastNonScoredFrame.setScore(score);
                    lastNonScorePosition++;
                }
            }

            return;
        }

        if (lastNonScoredFrame.isSpare()) {
            Frame nextFrame = getNextFrame(lastNonScorePosition);
            if (nextFrame == null) {
                return;
            }

            int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore() + nextFrame.getFirstRoll().getValue();
            lastNonScoredFrame.setScore(score);
            lastNonScorePosition++;
            return;
        }

        // For normal cases. The score is added only if the current frame has finished.
        // This is an implicit condition for Strike and Spare
        if (lastNonScoredFrame.hasFinished()) {
            int score = scoreSoFar() + lastNonScoredFrame.getCurrentScore();
            lastNonScoredFrame.setScore(score);
            lastNonScorePosition++;
        }
    }

    private Frame getNextFrame(int currentFramePosition) {
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
