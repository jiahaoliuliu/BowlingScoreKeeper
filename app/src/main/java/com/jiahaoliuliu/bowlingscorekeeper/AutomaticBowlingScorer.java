package com.jiahaoliuliu.bowlingscorekeeper;

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
    private final List<Integer> scoresList;

    /**
     * A double-linked list for the frames. This is because when the player scores a
     * {@link Point#SPARE} or {@link Point#STRIKE}, the frame need to be filled but not so the
     * scores. After 1 or 2 more rolls, the system need to come back and fill the scores
     */
    private final LinkedList<Frame> framesLinkedList;

    public AutomaticBowlingScorer() {
        scoresList = new ArrayList<>();
        framesLinkedList = new LinkedList<>();
    }

    /**
     * It returns the number of the frame containing the ball about to be rolled
     * @return
     */
    public int frameNumber() {
        return framesLinkedList.size();
    }

    /**
     * Return the current score
     * @return
     *      The current score
     */
    public int scoreSoFar() {
        if (scoresList.isEmpty()) {
            return 0;
        }

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
        if (gameIsOver()) {
            restartNewGame();
        }

        // The late last frame or the penultimate frame
        Frame lastFrame = framesLinkedList.pollLast();
        Frame penultimateFrame = framesLinkedList.peekLast();

        // Calculate the case
        // First case: When it is firs score
        if (lastFrame == null && penultimateFrame == null) {
            return rollForFirstFrameFirstRoll(point);
        }

        if (lastFrame != null && penultimateFrame == null) {
            if (!lastFrame.hasFinished()) {
                return rollForFirstFrameSecondRoll(lastFrame, point);
            } else {
                return rollForSecondFrameFirstRoll(lastFrame, point);
            }
        }

//        if (lastFrame != null && penultimateFrame != null) {
//            return rollForNormalFrame(lastFrame, penultimateFrame, point);
//        }

        return scoresList;
    }

    private List<Integer> rollForFirstFrameFirstRoll(Point point) {
        // Create a new Frame
        Frame newFrame = new Frame();
        newFrame.setFirstRoll(point);

        framesLinkedList.addLast(newFrame);

        // Do not update the score list yet. It will be updated later
        return scoresList;
    }

    private List<Integer> rollForFirstFrameSecondRoll(Frame currentFrame, Point point) {
        // Update the current frame
        currentFrame.setSecondRoll(point);

        if (point != Point.SPARE) {
            // Update the score
            scoresList.add(currentFrame.getFirstRoll().getValue() +
                    currentFrame.getSecondRoll().getValue());
        }

        // Adding it back
        framesLinkedList.addLast(currentFrame);

        return scoresList;
    }

    private List<Integer> rollForSecondFrameFirstRoll(Frame lastFrame, Point point) {
        // Check the last frame
        if (lastFrame.getFirstRoll() == Point.STRIKE) {
            // Do not do anything
            return scoresList;
        } else if (lastFrame.getSecondRoll() == Point.SPARE) {
            scoresList.add(lastFrame.getSecondRoll().getValue() + point.getValue());
            return scoresList;
        } else {
            if (point == Point.STRIKE || point == Point.SPARE) {
                // Not do anything
                return scoresList;
            } else {
                scoresList.add(point.getValue());
                return scoresList;
            }
        }
    }

//    private List<Integer> rollForNormalFrame(Frame lastFrame, Frame penultimateFrame, Point point) {
//
//    }

    private void restartNewGame() {
        scoresList.clear();
        framesLinkedList.clear();
    }
}
