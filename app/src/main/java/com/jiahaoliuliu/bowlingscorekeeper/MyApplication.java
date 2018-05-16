package com.jiahaoliuliu.bowlingscorekeeper;

import android.app.Application;

public class MyApplication extends Application {

    private static SimpleComponent simpleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Create the builder. There is not need for passing parameters to the modules
        simpleComponent = DaggerSimpleComponent.create();
    }

    static SimpleComponent getSimpleComponent() {
        return simpleComponent;
    }
}
