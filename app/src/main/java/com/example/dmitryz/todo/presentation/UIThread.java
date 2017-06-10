package com.example.dmitryz.todo.presentation;

import com.example.dmitryz.todo.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by q on 10.06.17.
 */

public class UIThread implements PostExecutionThread {
    @Inject
    UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
