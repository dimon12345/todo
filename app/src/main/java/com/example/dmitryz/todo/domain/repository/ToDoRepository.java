package com.example.dmitryz.todo.domain.repository;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.ToDoSet;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by dmitryz on 5/30/17.
 */

public interface ToDoRepository {
    Observable<List<ToDoItem>> getElements();

    Observable<ToDoItem> getElement(long id);

    Observable<Boolean> addToDoItem(ToDoItem toDoItem);

    Observable<Boolean> reset();

    Observable<Boolean> deleteById(long id);

    Observable<List<ToDoSet>> getSets();
}
