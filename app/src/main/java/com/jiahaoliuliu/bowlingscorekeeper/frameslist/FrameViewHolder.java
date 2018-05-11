package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jiahaoliuliu.bowlingscorekeeper.R;
import com.jiahaoliuliu.bowlingscorekeeper.databinding.FramesListItemBinding;
import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;

public class FrameViewHolder extends RecyclerView.ViewHolder implements FrameRowView {

    private FramesListItemBinding framesListItemBinding;

    public FrameViewHolder(FramesListItemBinding framesListItemBinding) {
        super(framesListItemBinding.getRoot());
        this.framesListItemBinding = framesListItemBinding;
    }

    @Override
    public void setFrame(Frame frame) {
        framesListItemBinding.setFrame(frame);
        framesListItemBinding.executePendingBindings();
    }
}
