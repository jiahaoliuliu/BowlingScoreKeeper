package com.jiahaoliuliu.bowlingscorekeeper;

import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;
import com.jiahaoliuliu.bowlingscorekeeper.model.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AutomaticBowlingScorerTest {

    private AutomaticBowlingScorer automaticBowlingScorer;

    @Before
    public void setUp() {
        automaticBowlingScorer = new AutomaticBowlingScorer();
    }

    @After
    public void tearDown() {
        // Nothing
    }

    @Test
    public void test_InitState() {
        assertEquals(0, automaticBowlingScorer.frameNumber());
        assertEquals(0, automaticBowlingScorer.scoreSoFar());
        assertEquals(false, automaticBowlingScorer.gameIsOver());
    }

    @Test
    public void test_score() {
        // First frame
        rollAndCheckScore(Point.NINE, 0);
        rollAndCheckScore(Point.SPARE, 0);

        // Second frame
        rollAndCheckScore(Point.ZERO, 10);
        rollAndCheckScore(Point.SPARE, 10);

        // Third frame
        rollAndCheckScore(Point.STRIKE, 30);

        // Fourth frame
        rollAndCheckScore(Point.STRIKE, 30);

        // Fifth frame
        rollAndCheckScore(Point.SIX, 56);
        rollAndCheckScore(Point.TWO, 82);

        // Sixth frame
        rollAndCheckScore(Point.SEVEN, 82);
        rollAndCheckScore(Point.SPARE, 82);

        // Seventh frame
        rollAndCheckScore(Point.EIGHT, 100);
        rollAndCheckScore(Point.SPARE, 100);

        // Eighth frame
        rollAndCheckScore(Point.STRIKE, 120);

        // Ninth frame
        rollAndCheckScore(Point.NINE, 120);
        rollAndCheckScore(Point.ZERO, 148);

        // Tenth frame
        rollAndCheckScore(Point.STRIKE, 148);
        rollAndCheckScore(Point.STRIKE, 148);
        rollAndCheckScore(Point.EIGHT, 176);
    }

    @Test
    public void test_scorePerfectGame() {
        // First frame
        rollAndCheckScore(Point.STRIKE, 0);

        // Second frame
        rollAndCheckScore(Point.STRIKE, 0);

        // Third frame
        rollAndCheckScore(Point.STRIKE, 30);

        // Fourth frame
        rollAndCheckScore(Point.STRIKE, 60);

        // Fifth frame
        rollAndCheckScore(Point.STRIKE, 90);

        // Sixth frame
        rollAndCheckScore(Point.STRIKE, 120);

        // Seventh frame
        rollAndCheckScore(Point.STRIKE, 150);

        // Eighth frame
        rollAndCheckScore(Point.STRIKE, 180);

        // Ninth frame
        rollAndCheckScore(Point.STRIKE, 210);

        // Tenth frame
        rollAndCheckScore(Point.STRIKE, 240);
        rollAndCheckScore(Point.STRIKE, 270);
        rollAndCheckScore(Point.STRIKE, 300);
    }

    @Test
    public void test_scoreWorseGame() {
        // First frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Second frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Third frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Fourth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Fifth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Sixth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Seventh frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Eighth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Ninth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);

        // Tenth frame
        rollAndCheckScore(Point.EMPTY, 0);
        rollAndCheckScore(Point.EMPTY, 0);
    }

    private void rollAndCheckScore(Point point, int expectedScore) {
        automaticBowlingScorer.roll(point);
        assertEquals(expectedScore, automaticBowlingScorer.scoreSoFar());
    }
}
