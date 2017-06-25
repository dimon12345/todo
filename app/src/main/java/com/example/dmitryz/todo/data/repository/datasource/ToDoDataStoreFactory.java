package com.example.dmitryz.todo.data.repository.datasource;

import android.content.Context;

import com.example.dmitryz.todo.data.cache.ToDoCache;
import com.example.dmitryz.todo.data.entity.mapper.ToDoEntityJsonMapper;
import com.example.dmitryz.todo.data.net.RestApi;
import com.example.dmitryz.todo.data.net.RestApiImpl;
import com.google.gson.Gson;

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
        ToDoDataStore toDoDataStore;

        if (!this.todoCache.isExpired() && this.todoCache.isCached(id)) {
            toDoDataStore = new DiskToDoDataStore(this.todoCache);
        } else {
            //toDoDataStore = createCloudDataStore();
            toDoDataStore = createAssetDataStore();
        }
        return toDoDataStore;
    }

    public ToDoDataStore createCloudDataStore() {
        final ToDoEntityJsonMapper todoEntityJsonMapper = new ToDoEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, todoEntityJsonMapper);

        return new CloudToDoDataStore(restApi, this.todoCache);
    }

    public ToDoDataStore createAssetDataStore() {
        return new AssetToDoDataStore(context, "todo.json");
    }
}
