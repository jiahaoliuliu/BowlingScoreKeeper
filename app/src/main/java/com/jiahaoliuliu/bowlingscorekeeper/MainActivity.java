package com.jiahaoliuliu.bowlingscorekeeper;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiahaoliuliu.bowlingscorekeeper.databinding.ActivityMainBinding;
import com.jiahaoliuliu.bowlingscorekeeper.model.Frame;
import com.jiahaoliuliu.bowlingscorekeeper.frameslist.FramesListAdapter;
import com.jiahaoliuliu.bowlingscorekeeper.model.IFrame;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ScoreKeeperContract.View {

    // Internal variables
    @Inject
    ScoreKeeperContract.Presenter presenter;
    private FramesListAdapter framesListAdapter;

    // Views
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inject the variables
        MyApplication.getSimpleComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Prepare the views
        framesListAdapter = new FramesListAdapter();
        // Set up the horizontal list view
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(framesListAdapter);

        // Init the presenter
        presenter = new ScoreKeeperPresenter();
        presenter.setView(this);
        presenter.onViewCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewVisible();
    }

    @Override
    protected void onPause() {
        presenter.onViewNonVisible();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public void updateView(List<IFrame> framesList) {
        framesListAdapter.setFramesList(framesList);
    }
}
