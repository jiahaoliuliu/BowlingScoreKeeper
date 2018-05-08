package com.jiahaoliuliu.bowlingscorekeeper.scoreslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jiahaoliuliu.bowlingscorekeeper.R;

public class ScoreViewHolder extends RecyclerView.ViewHolder implements ScoreRowView {

    private TextView scoreTV;

    public ScoreViewHolder(View itemView) {
        super(itemView);

        scoreTV = itemView.findViewById(R.id.score_tv);
    }

    @Override
    public void setScore(int score) {
        scoreTV.setText(String.valueOf(score));
    }
}
