package com.jiahaoliuliu.bowlingscorekeeper.scoreslist;

import java.util.ArrayList;
import java.util.List;

public class ScoresListPresenterImpl implements ScoresListPresenter {

    private final List<Integer> scoresList;

    public ScoresListPresenterImpl() {
        this.scoresList = new ArrayList<>();
    }

    @Override
    public void onBindScoreRowViewAtPosition(ScoreRowView scoreRowView, int position) {
        int score = scoresList.get(position);
        scoreRowView.setScore(score);
    }

    @Override
    public int getScoresListCount() {
        return scoresList.size();
    }

    @Override
    public void addScore(int score) {
        scoresList.add(score);
    }
}
