package com.example.dmitryz.todo.data.repository.datasource;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;


/**
 * Created by q on 03.06.17.
 */

public class ToDoDataStoreFactory {
    private final Context context;

    @Inject
    ToDoDataStoreFactory(@NonNull Context context) {
        this.context = context;
    }

    public ToDoDataStore create(String id) {
        return createSQLiteDataStore();
    }

    public ToDoDataStore createSQLiteDataStore() {
        return new SQLiteToDoDataStore(context);
    }
}
