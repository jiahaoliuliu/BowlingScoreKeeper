package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import android.support.v7.widget.RecyclerView;
import com.jiahaoliuliu.bowlingscorekeeper.databinding.FramesListItemBinding;
import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;

public class FrameViewHolder extends RecyclerView.ViewHolder implements FrameRowView {

    private FramesListItemBinding framesListItemBinding;

    public FrameViewHolder(FramesListItemBinding framesListItemBinding) {
        super(framesListItemBinding.getRoot());
        this.framesListItemBinding = framesListItemBinding;
    }

    @Override
    public void setFrame(IFrame frame) {
        framesListItemBinding.setIframe(frame);
        framesListItemBinding.executePendingBindings();
    }
}
