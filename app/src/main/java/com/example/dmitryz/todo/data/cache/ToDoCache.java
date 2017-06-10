package com.example.dmitryz.todo.data.cache;


import com.example.dmitryz.todo.data.entity.ToDoEntity;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 6/2/17.
 */

public interface ToDoCache {
    Observable<ToDoEntity> get(final String id);

    void put(ToDoEntity item);

    boolean isCached(final String id);

    boolean isExpired();

    void evictAll();
}
