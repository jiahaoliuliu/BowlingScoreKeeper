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
            return firstRoll != null && secondRoll != null;
        } else {
            return firstRoll != null && secondRoll != null && thirdRoll != null;
        }
    }

    public Point getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(Point firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Point getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(Point secondRoll) {
        this.secondRoll = secondRoll;
    }

    public Point getThirdRoll() {
        return thirdRoll;
    }

    public void setThirdRoll(Point thirdRoll) {
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
