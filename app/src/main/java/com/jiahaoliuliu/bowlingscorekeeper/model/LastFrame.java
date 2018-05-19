package com.jiahaoliuliu.bowlingscorekeeper.model;

public class LastFrame extends Frame {

    private Point thirdRoll = Point.EMPTY;

    public LastFrame() {}

    public Point getThirdRoll() {
        return thirdRoll;
    }

    public void setThirdRoll(Point thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    @Override
    public boolean hasThirdRoll() {
        return getThirdRoll() != Point.EMPTY;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public boolean hasFinished() {
        if (super.hasFinished()) {
            if (isStrike() || isSpare()) {
                return getThirdRoll() != Point.EMPTY;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addPoint(Point point) {
        if (canAddPoint()) {
            // Trying to add point to the first two positions
            if (super.addPoint(point)) {
                return true;
            }

            this.thirdRoll = point;
            return true;
        }

        return false;
    }

    @Override
    public int getCurrentScore() {
        if (isStrike()) {
            return getFirstRoll().getValue() + getSecondRoll().getValue() +
                    getThirdRoll().getValue();
        }

        if (isSpare()) {
            return Point.SPARE.getValue() + getThirdRoll().getValue();
        }

        return super.getCurrentScore();
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "firstRoll=" + getFirstRoll() +
                ", secondRoll=" + getSecondRoll() +
                ", thirdRoll=" + thirdRoll +
                ", finalScore=" + getFinalScore() +
                '}';
    }
}
