package com.example.dmitryz.todo.presentation.internal.di.components;

import android.app.Activity;

import com.example.dmitryz.todo.presentation.internal.di.PerActivity;
import com.example.dmitryz.todo.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by q on 08.06.17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class )
interface ActivityComponent {
    Activity activity();
}
