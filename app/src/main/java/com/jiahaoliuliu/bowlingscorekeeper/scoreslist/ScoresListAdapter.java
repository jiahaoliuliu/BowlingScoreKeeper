package com.jiahaoliuliu.bowlingscorekeeper.scoreslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiahaoliuliu.bowlingscorekeeper.R;

import java.util.List;

public class ScoresListAdapter extends RecyclerView.Adapter<ScoreViewHolder> {

    private final ScoresListPresenter scoresListPresenter;

    public ScoresListAdapter() {
        this.scoresListPresenter = new ScoresListPresenterImpl();
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_list_item, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        scoresListPresenter.onBindScoreRowViewAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return scoresListPresenter.getScoresListCount();
    }

    public void addNewScore(int score) {
        scoresListPresenter.addScore(score);
        notifyDataSetChanged();
    }
}
