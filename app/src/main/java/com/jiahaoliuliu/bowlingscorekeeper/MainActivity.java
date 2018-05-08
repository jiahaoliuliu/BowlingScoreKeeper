package com.jiahaoliuliu.bowlingscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ScoreKeeperContract.View {

    private ScoreKeeperContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
