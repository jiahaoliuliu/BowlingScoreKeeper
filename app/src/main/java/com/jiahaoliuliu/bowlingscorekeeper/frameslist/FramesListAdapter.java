package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiahaoliuliu.bowlingscorekeeper.R;
import com.jiahaoliuliu.bowlingscorekeeper.databinding.FramesListItemBinding;
import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;

import java.util.List;

public class FramesListAdapter extends RecyclerView.Adapter<FrameViewHolder> {

    private final FramesListPresenter framesListPresenter;

    public FramesListAdapter() {
        this.framesListPresenter = new FramesListPresenterImpl();
    }

    @NonNull
    @Override
    public FrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        FramesListItemBinding framesListItemBinding =
                FramesListItemBinding.inflate(layoutInflater, parent, false);
        return new FrameViewHolder(framesListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameViewHolder holder, int position) {
        framesListPresenter.onBindFrameRowViewAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return framesListPresenter.getFramesListCount();
    }

    public void setFramesList(List<Frame> framesList) {
        framesListPresenter.setFramesList(framesList);
        notifyDataSetChanged();
    }
}
