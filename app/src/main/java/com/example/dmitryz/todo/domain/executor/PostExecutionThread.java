package com.example.dmitryz.todo.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by dmitryz on 5/31/17.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
