package com.jiahaoliuliu.bowlingscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiahaoliuliu.bowlingscorekeeper.scoreslist.ScoresListAdapter;

public class MainActivity extends AppCompatActivity implements ScoreKeeperContract.View {

    // Internal variables
    private ScoreKeeperContract.Presenter presenter;
    private ScoresListAdapter scoresListAdapter;

    // Views
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare the views
        recyclerView = findViewById(R.id.recycler_view);
        scoresListAdapter = new ScoresListAdapter();
        // Set up the horizontal list view
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(scoresListAdapter);

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
}
