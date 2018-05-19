package com.jiahaoliuliu.bowlingscorekeeper;

import android.util.Log;

import com.jiahaoliuliu.bowlingscorekeeper.collection.LinkedList;
import com.jiahaoliuliu.bowlingscorekeeper.collection.Node;
import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;
import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;
import com.jiahaoliuliu.bowlingscorekeeper.model.LastFrame;
import com.jiahaoliuliu.bowlingscorekeeper.model.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main class that calculates the automatic bowling scores
 */
public class AutomaticBowlingScorer {
    private static final String TAG = "AutomaticBowlingScorer";

    private static final int MAXIMUM_SCORE_NUMBER = 10;

    public final List<IFrame> framesList;
    public int lastNonScorePosition;
    public boolean isGameOver;
    public final LinkedList<Node<IFrame>> linkedFrameList;

    public AutomaticBowlingScorer() {
        linkedFrameList = new LinkedList<>();
        framesList = new ArrayList<>();
        lastNonScorePosition = 0;
    }

    /**
     * It returns the number of the frame containing the ball about to be rolled
     * @return
     */
    public int frameNumber() {
        return linkedFrameList.size();
    }

    /**
     * Return the current score
     * @return
     *      The current score
     */
    public int scoreSoFar() {
        if (linkedFrameList.isEmpty()) {
            return 0;
        }

        // Go throw the list backward to get the last scored node
        Node<IFrame> lastScoredNode = linkedFrameList.getHead();
        while(lastScoredNode.hasPreviousNode() && !lastScoredNode.getData().hasFinalScore()) {
            lastScoredNode = lastScoredNode.getPreviousNode();
        }

        // If there is non score yet, return empty
        if (lastScoredNode.getData().getFinalScore() == -1) {
            return 0;
        }

        // Return the score from the last scored node
        return lastScoredNode.getData().getFinalScore();
    }

    /**
     * If the current game is over. This is the total number of
     * @return
     *      True if the current game is over
     *      False otherwise
     */
    public boolean gameIsOver() {
        // TODO: Check when to set the game as over
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
        // TODO: Replace this for Log. This is used only for unit tests
        System.out.println("Adding point " + point);

        if (gameIsOver()) {
            restartNewGame();
        }

        // Normal frames
//        IFrame frameToFill = getFrameToFill();
//        frameToFill.addPoint(point);
//        updateScores();
//        // Deprecate the second update
//        updateScores();

        // Linked list
        IFrame frameToFill = getNextFrameFromLinkedList();
        frameToFill.addPoint(point);
        updateScores();
        updateScores();
        return generateFramesList();
    }

//    private IFrame getFrameToFill() {
//        // For the first frame
//        if (framesList.isEmpty()) {
//            Frame firstFrame = new Frame();
//            framesList.add(firstFrame);
//            return firstFrame;
//        }
//
//        // Get the last frame in the list
//        IFrame lastFrame = framesList.get(framesList.size() - 1);
//        if (!lastFrame.hasFinished()) {
//            return lastFrame;
//        }
//
//        // The last frame in the list has finished
//        IFrame nextFrame = (framesList.size() == MAXIMUM_SCORE_NUMBER - 1)?
//                new LastFrame() : new Frame();
//        framesList.add(nextFrame);
//        return nextFrame;
//    }

    private void restartNewGame() {
        framesList.clear();
        lastNonScorePosition = 0;
    }

    /**
     * Update all the possible scores
     * TODO: All this logic should be in the Frame itself. In the Add point method
     */
    private void updateScores() {
        // Find the last non scored frame
        Node<IFrame> currentNode = linkedFrameList.getHead();
        while(currentNode.hasPreviousNode() && !currentNode.getPreviousNode().getData().hasFinalScore()) {
            currentNode = currentNode.getPreviousNode();
        }

        IFrame lastNonScoredFrame = currentNode.getData();
        // TODO: Replace this for Log. This is used only for unit tests
        System.out.println("The last non scored frame is " + lastNonScoredFrame);
        if (lastNonScoredFrame.isLastFrame()) {
            if (!lastNonScoredFrame.hasFinished() || lastNonScoredFrame.hasFinalScore()) {
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
            if (!currentNode.hasNextNode()) {
                return;
            }

            Node<IFrame> nextNode = currentNode.getNextNode();
            IFrame nextFrame = nextNode.getData();
            // If the next frame does not exists, not do anything
            if (nextFrame == null) {
                return;
            }

            if (nextFrame.isStrike()) {
                // If it is the last frame, get the first two scores if exists
                if (nextFrame.isLastFrame() && nextFrame.hasFirstRoll() &&
                        nextFrame.hasSecondRoll()) {
                    lastNonScoredFrame.setFinalScore(scoreSoFar() + Point.STRIKE.getValue() +
                        nextFrame.getFirstRoll().getValue() + nextFrame.getSecondRoll().getValue());
                    lastNonScorePosition++;
                    return;
                }

                // Check if next next node exists
                if (!nextNode.hasNextNode()) {
                    return;
                }
                IFrame nextNextFrame = nextNode.getNextNode().getData();

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
            if (!currentNode.hasNextNode()) {
                return;
            }

            IFrame nextFrame = currentNode.getNextNode().getData();
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

//    private IFrame getNextFrame(int currentFramePosition) {
//        // Precondition
//        if (framesList.isEmpty()) {
//            return null;
//        }
//
//        // If it is the last frame, return null
//        if (framesList.size() - 1 <= currentFramePosition) {
//            return null;
//        }
//
//        return framesList.get(currentFramePosition + 1);
//    }
//
    private IFrame getNextFrameFromLinkedList() {
        if (linkedFrameList.isEmpty()) {
            // Create a new Node
            // TODO: Create a model class called first frame
            Frame frame = new Frame();
            Node<IFrame> frameNode = new Node<>(frame);
            linkedFrameList.add(frameNode);
            return frame;
        } else {
            Node<IFrame> frameNode = linkedFrameList.getHead();
            IFrame lastFrame = frameNode.getData();
            if (!lastFrame.hasFinished()) {
                return lastFrame;
            } else {
                // The last frame in the list has finished
                IFrame nextFrame = (linkedFrameList.size() == MAXIMUM_SCORE_NUMBER - 1)?
                        new LastFrame() : new Frame();
                Node<IFrame> newFrameNode = new Node<>(nextFrame);
                linkedFrameList.add(newFrameNode);
                return nextFrame;
            }
        }
    }

    private List<IFrame> generateFramesList() {
        List<IFrame> framesList = new ArrayList();
        Node<IFrame> previousNode = linkedFrameList.getHead();
        do {
            framesList.add(previousNode.getData());
            previousNode = previousNode.getPreviousNode();
        } while (previousNode != null);

        // Reverse the order
        Collections.reverse(framesList);
        return framesList;
    }
}
