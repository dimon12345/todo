package com.example.dmitryz.todo.presentation.internal.di.components;

import android.content.Context;

import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;
import com.example.dmitryz.todo.presentation.internal.di.modules.ApplicationModule;
import com.example.dmitryz.todo.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by q on 03.06.17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ToDoRepository todoRepository();
}
