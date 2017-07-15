package com.example.dmitryz.todo.data.repository.datasource;

import android.content.Context;

import com.example.dmitryz.todo.data.cache.ToDoCache;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;


/**
 * Created by q on 03.06.17.
 */

public class ToDoDataStoreFactory {
    private final Context context;
    private final ToDoCache todoCache;

    @Inject
    ToDoDataStoreFactory(@NonNull Context context, @NonNull ToDoCache todoCache) {
        this.context = context;
        this.todoCache = todoCache;
    }

    public ToDoDataStore create(String id) {
        return createSQLiteDataStore();
    }

    public ToDoDataStore createSQLiteDataStore() {
        return new SQLiteToDoDataStore(context);
    }
}
