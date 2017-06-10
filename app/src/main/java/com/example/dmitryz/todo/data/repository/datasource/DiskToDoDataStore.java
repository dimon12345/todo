package com.example.dmitryz.todo.data.repository.datasource;

import com.example.dmitryz.todo.data.cache.ToDoCache;
import com.example.dmitryz.todo.data.entity.ToDoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by q on 03.06.17.
 */

class DiskToDoDataStore implements ToDoDataStore {

    private final ToDoCache toDoCache;

    public DiskToDoDataStore(ToDoCache todoCache) {
        this.toDoCache = todoCache;
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {

        //TODO: implement simple cache for storing/retrieving collections of todo items.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<ToDoEntity> todoEntityDetails(String id) {
        return this.toDoCache.get(id);
    }
}
