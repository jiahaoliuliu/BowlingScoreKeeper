package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;

import java.util.ArrayList;
import java.util.List;

public class FramesListPresenterImpl implements FramesListPresenter {

    private final List<IFrame> framesList;

    public FramesListPresenterImpl() {
        this.framesList = new ArrayList<>();
    }

    @Override
    public void onBindFrameRowViewAtPosition(FrameRowView frameRowView, int position) {
        IFrame frame = framesList.get(position);
        frameRowView.setFrame(frame);
    }

    @Override
    public int getFramesListCount() {
        return framesList.size();
    }

    @Override
    public void setFramesList(List<IFrame> framesList) {
        this.framesList.clear();
        this.framesList.addAll(framesList);
    }

}
