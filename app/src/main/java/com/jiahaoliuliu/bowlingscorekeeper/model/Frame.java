package com.jiahaoliuliu.bowlingscorekeeper.model;

/**
 * Each one of the frames. A frame is composed by maximum 3 rolls, each roll has a point assigned
 * For most of the frames, it only allow 2 rolls.
 * If the current frame is the last frame and the player has scored a {@link Point#STRIKE} or
 * {@link Point#SPARE} for the one of the first two rolls, the he is awared with an extra roll
 */
public class Frame {

    /**
     * The points earned on the first roll
     */
    private Point firstRoll;

    /**
     * The points earned on the second roll
     */
    private Point secondRoll;
    /**
     * The points earned on the third roll
     */
    private Point thirdRoll;

    /**
     * By default this is not the last frame
     */
    private boolean isLastFrame;

    /**
     * The final score
     */
    private int score;

    public Frame() {
    }

    // Helper methods
    public boolean hasFinished() {
        if (!isLastFrame) {
            return (firstRoll == Point.STRIKE) || (firstRoll != null && secondRoll != null);
        } else {
            return firstRoll != null && secondRoll != null && thirdRoll != null;
        }
    }

    /**
     * Check if the current frame is a strike
     * @return
     *      True if the first roll is a strike
     *      False otherwise
     */
    public boolean isStrike() {
        return Point.STRIKE == firstRoll;
    }

    /**
     * Check if the current from is a spare
     * @return
     *      True if the second roll is a spare
     *      False otherwise
     */
    public boolean isSpare() {
        return Point.SPARE == secondRoll;
    }

    /**
     * Check if it is possible to add a third roll. This is possible if
     *     - Current frame is the last frame
     *     - The player has archived a strike on the first roll or a spare on the second roll
     * Note the reason why it does not check if the second roll is a Strike is because it is not
     * necessary. The unique way that a player can archive a Strike on the second roll is because
     * he already have archived a Strike on the first roll, which is a valid condition to add a
     * third roll
     * @return
     */
    public boolean canAddAThirdRoll() {
        return (isLastFrame() && thirdRoll == null &&
                (Point.STRIKE == firstRoll || Point.SPARE == secondRoll));
    }

    public void addPoint(Point point) {
        if (firstRoll == null) {
            setFirstRoll(point);
            return;
        }

        if (secondRoll == null) {
            setSecondRoll(point);
            return;
        }

        if (!canAddAThirdRoll()) {
            throw new IllegalStateException("The current frame cannot add a third roll");
        }

        setThirdRoll(point);
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
        if (firstRoll != null) {
            currentScore += firstRoll.getValue();
        }

        if (secondRoll != null) {
            currentScore += secondRoll.getValue();
        }

        if (thirdRoll != null) {
            currentScore += thirdRoll.getValue();
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

    private void setSecondRoll(Point secondRoll) {
        this.secondRoll = secondRoll;
    }

    public Point getThirdRoll() {
        return thirdRoll;
    }

    private void setThirdRoll(Point thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    public boolean isLastFrame() {
        return isLastFrame;
    }

    public void setLastFrame(boolean lastFrame) {
        isLastFrame = lastFrame;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (isLastFrame != frame.isLastFrame) return false;
        if (score != frame.score) return false;
        if (firstRoll != frame.firstRoll) return false;
        if (secondRoll != frame.secondRoll) return false;
        return thirdRoll == frame.thirdRoll;
    }

    @Override
    public int hashCode() {
        int result = firstRoll != null ? firstRoll.hashCode() : 0;
        result = 31 * result + (secondRoll != null ? secondRoll.hashCode() : 0);
        result = 31 * result + (thirdRoll != null ? thirdRoll.hashCode() : 0);
        result = 31 * result + (isLastFrame ? 1 : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "firstRoll=" + firstRoll +
                ", secondRoll=" + secondRoll +
                ", thirdRoll=" + thirdRoll +
                ", isLastFrame=" + isLastFrame +
                ", score=" + score +
                '}';
    }
}
