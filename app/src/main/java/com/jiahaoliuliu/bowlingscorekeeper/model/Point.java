package com.jiahaoliuliu.bowlingscorekeeper.model;

/**
 * All the possible points that a player can enter per roll
 */
public enum Point {
    ZERO(0, "0"), ONE(1, "1"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"),
    SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), STRIKE(10, "X"),
    SPARE(10, "/"), EMPTY(0, ""); // Points not added yet. This could be ignored on kotlin

    private int value;
    private String representation;

    public int getValue() {
        return this.value;
    }

    public String getRepresentation() {
        return representation;
    }

    Point(int value, String representation) {
        this.value = value;
        this.representation = representation;
    }
}
