package com.example.dmitryz.todo.presentation.internal.di.modules;

import android.content.Context;

import com.example.dmitryz.todo.data.executor.JobExecutor;
import com.example.dmitryz.todo.data.repository.ToDoDataRepository;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;
import com.example.dmitryz.todo.presentation.AndroidApplication;
import com.example.dmitryz.todo.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by q on 10.06.17.
 */

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication androidApplication) {
        application = androidApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() { return application;}

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ToDoRepository provideToDoRepository(ToDoDataRepository todoDataRepository) {
        return todoDataRepository;
    }
}
