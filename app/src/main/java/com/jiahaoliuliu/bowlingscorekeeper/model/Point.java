package com.jiahaoliuliu.bowlingscorekeeper.model;

/**
 * All the possible points that a player can enter per roll
 */
public enum Point {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), STRIKE(10), SPARE(10);

    private int value;

    public int getValue() {
        return this.value;
    }

    Point(int value) {
        this.value = value;
    }
}
