package com.jiahaoliuliu.bowlingscorekeeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
