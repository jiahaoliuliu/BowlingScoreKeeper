package com.jiahaoliuliu.bowlingscorekeeper.frameslist;

import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;

import java.util.List;

public interface FramesListPresenter {

    /**
     * Used to bind the frame to each one of the frame row views
     * @param frameRowView
     *      The frame row view to be bind
     * @param position
     *      The position of the frame row view
     */
    void onBindFrameRowViewAtPosition(FrameRowView frameRowView, int position);

    /**
     * Get the number of frames
     * @return
     */
    int getFramesListCount();

    /**
     * Set the frame list
     * @param framesList
     */
    void setFramesList(List<IFrame> framesList);
}
