package com.example.dmitryz.todo.data.repository.datasource;

import android.content.Context;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.ToDoEntityList;
import com.example.dmitryz.todo.data.exception.ToDoItemNotFound;
import com.example.dmitryz.todo.data.repository.datasource.ToDoDataStore;
import com.example.dmitryz.todo.data.repository.utils.AssetLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 6/24/17.
 */

public class AssetToDoDataStore implements ToDoDataStore {

    private final Context context;
    private final Gson gson;
    private final String filename;

    AssetToDoDataStore(Context context, String filename) {
        this.context = context;
        this.gson = new Gson();
        this.filename = filename;
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {
        final String json = AssetLoader.loadJSONFromAsset(context, filename);
        try {
            ToDoEntityList todoEntityList = gson.fromJson(json, ToDoEntityList.class);
            return Observable.just(todoEntityList.getToDoEntities());
        } catch (JsonSyntaxException exception) {
            return Observable.error(exception);
        }
    }

    @Override
    public Observable<ToDoEntity> todoEntityDetails(String todoId) {
        final String json = AssetLoader.loadJSONFromAsset(context, filename);
        try {
            ToDoEntityList todoEntityList = gson.fromJson(json, ToDoEntityList.class);
            for( ToDoEntity entity : todoEntityList.getToDoEntities() ) {
                if( entity.getID().equals(todoId)) {
                    return Observable.just(entity);
                }
            }
            return Observable.error(new ToDoItemNotFound());
        } catch (JsonSyntaxException exception) {
            return Observable.error(exception);
        }
    }
}
