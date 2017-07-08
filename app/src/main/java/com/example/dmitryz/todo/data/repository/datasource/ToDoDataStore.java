package com.example.dmitryz.todo.data.repository.datasource;

import com.example.dmitryz.todo.data.entity.ToDoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by q on 03.06.17.
 */

public interface ToDoDataStore {
    Observable<List<ToDoEntity>> todoEntityList();
    Observable<ToDoEntity> todoEntityDetails(final String id);
    Observable<Void> addEntity(ToDoEntity entity);
    Observable<Void> reset();
}
