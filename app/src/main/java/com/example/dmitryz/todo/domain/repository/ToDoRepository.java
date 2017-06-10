package com.example.dmitryz.todo.domain.repository;

import com.example.dmitryz.todo.domain.ToDoItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by dmitryz on 5/30/17.
 */

public interface ToDoRepository {
    Observable<List<ToDoItem>> getElements();

    Observable<ToDoItem> getElement(String id);
}
