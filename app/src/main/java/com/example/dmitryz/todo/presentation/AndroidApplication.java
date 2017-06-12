package com.example.dmitryz.todo.presentation;

import android.app.Application;

import com.example.dmitryz.todo.BuildConfig;
import com.example.dmitryz.todo.presentation.internal.di.components.ApplicationComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.DaggerApplicationComponent;
import com.example.dmitryz.todo.presentation.internal.di.modules.ApplicationModule;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by q on 03.06.17.
 */

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
