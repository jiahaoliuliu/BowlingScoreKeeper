package com.jiahaoliuliu.bowlingscorekeeper.model;

/**
 * Basic interface for the frame
 */
public interface IFrame {

    /**
     * Get the points of the first roll
     * @return
     *      The point of the first roll
     */
    Point getFirstRoll();

    /**
     * Check if it has the first roll
     * @return
     *      True if the first roll has been filled
     *      False otherwise
     */
    boolean hasFirstRoll();

    /**
     * Get the points of the second roll
     * @return
     *      The point of the second roll
     */
    Point getSecondRoll();

    /**
     * Check if the second roll has been filled
     * @return
     *      True if the second roll has been filled
     *      False otherwise
     */
    boolean hasSecondRoll();

    /**
     * Get the third roll
     * @return
     *      The third roll if exist
     */
    Point getThirdRoll();

    /**
     * Check if the third roll has been filled
     * @return
     *      True if the third roll has been filled
     *      False otherwise
     */
    boolean hasThirdRoll();

    /**
     * If the current frame is a strike
     * @return
     */
    boolean isStrike();

    /**
     * If the current frame is a spare
     * @return
     */
    boolean isSpare();

    /**
     * Add a point to the current frame, if possible
     * @param point
     *      The point to be added
     * @return
     *      True if the point was added
     *      False otherwise
     */
    boolean addPoint(Point point);

    boolean hasFinished();

    /**
     * Get a temporally score until the final one is set
     * @return
     *      The temporally score
     */
    int getCurrentScore();

    /**
     * Get the final score of this frame
     * @return
     */
    int getFinalScore();

    /**
     * Set the last score
     * @param finalScore
     */
    void setFinalScore(int finalScore);

    boolean hasFinalScore();

    /**
     * Check if it is the last frame.
     * @return
     *      True if it is the last frame.
     *      False if it is not the last frame.
     */
    boolean isLastFrame();
}
