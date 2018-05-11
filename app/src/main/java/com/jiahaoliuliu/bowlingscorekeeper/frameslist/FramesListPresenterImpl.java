package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;

import java.util.ArrayList;
import java.util.List;

public class FramesListPresenterImpl implements FramesListPresenter {

    private final List<Frame> framesList;

    public FramesListPresenterImpl() {
        this.framesList = new ArrayList<>();
    }

    @Override
    public void onBindFrameRowViewAtPosition(FrameRowView frameRowView, int position) {
        Frame frame = framesList.get(position);
        frameRowView.setFrame(frame);
    }

    @Override
    public int getFramesListCount() {
        return framesList.size();
    }

    @Override
    public void setFramesList(List<Frame> framesList) {
        this.framesList.clear();
        this.framesList.addAll(framesList);
    }

}
