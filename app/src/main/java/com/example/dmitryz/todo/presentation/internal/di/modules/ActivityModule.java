package com.example.dmitryz.todo.presentation.internal.di.modules;

import android.app.Activity;
import android.content.Context;

import com.example.dmitryz.todo.presentation.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by q on 08.06.17.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
