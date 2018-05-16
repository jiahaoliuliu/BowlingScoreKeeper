package com.jiahaoliuliu.bowlingscorekeeper.model;

import java.security.cert.PolicyNode;

/**
 * Each one of the frames. A frame is composed by maximum 3 rolls, each roll has a point assigned
 * For most of the frames, it only allow 2 rolls.
 * If the current frame is the last frame and the player has scored a {@link Point#STRIKE} or
 * {@link Point#SPARE} for the one of the first two rolls, the he is awared with an extra roll
 */
public class Frame implements IFrame{

    /**
     * The points earned on the first roll
     */
    private Point firstRoll = Point.EMPTY;

    /**
     * The points earned on the second roll
     */
    private Point secondRoll = Point.EMPTY;
    /**
     * The final score
     */
    private int finalScore;

    public Frame() {
    }

    // Helper methods
    public boolean hasFinished() {
        return (firstRoll == Point.STRIKE) ||
                (firstRoll != Point.EMPTY && secondRoll != Point.EMPTY);
    }

    /**
     * Check if the current frame is a strike
     * @return
     *      True if the first roll is a strike
     *      False otherwise
     */
    @Override
    public boolean isStrike() {
        return Point.STRIKE == firstRoll;
    }

    /**
     * Check if the current from is a spare
     * @return
     *      True if the second roll is a spare
     *      False otherwise
     */
    @Override
    public boolean isSpare() {
        return Point.SPARE == secondRoll;
    }

    /**
     * Trying to add a new point to this frame
     * @param point New point to be added
     * @return
     *      True if a new point has been added
     *      False if it failed ot add a new point
     */
    @Override
    public boolean addPoint(Point point) {
        if (!canAddPoint()) {
            return false;
        }

        if (firstRoll == Point.EMPTY) {
            setFirstRoll(point);
            return true;
        }

        if (secondRoll == Point.EMPTY) {
            setSecondRoll(point);
            return true;
        }

        return false;
    }

    /**
     * If a new point could be added
     * @return
     *      True if the frame has not finished
     *      False otherwise
     */
    protected boolean canAddPoint() {
        return !hasFinished();
    }

    /**
     * Get the calculated current score. This might change in the future
     * @return
     */
    public int getCurrentScore() {
        if (isStrike()) {
            return Point.STRIKE.getValue();
        }

        if (isSpare()) {
            return Point.SPARE.getValue();
        }

        int currentScore = 0;
        if (firstRoll != Point.EMPTY) {
            currentScore += firstRoll.getValue();
        }

        if (secondRoll != Point.EMPTY) {
            currentScore += secondRoll.getValue();
        }

        return currentScore;
    }

    public Point getFirstRoll() {
        return firstRoll;
    }

    private void setFirstRoll(Point firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Point getSecondRoll() {
        return secondRoll;
    }

    public Point getThirdRoll() {
        return Point.EMPTY;
    }

    private void setSecondRoll(Point secondRoll) {
        this.secondRoll = secondRoll;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

}
