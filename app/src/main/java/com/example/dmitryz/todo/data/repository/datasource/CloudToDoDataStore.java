package com.example.dmitryz.todo.data.repository.datasource;

import com.example.dmitryz.todo.data.cache.ToDoCache;
import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by q on 03.06.17.
 */

class CloudToDoDataStore implements ToDoDataStore {

    private final RestApi restApi;
    private final ToDoCache todoCache;

    public CloudToDoDataStore(RestApi restApi, ToDoCache todoCache) {
        this.restApi = restApi;
        this.todoCache = todoCache;
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {
        return this.restApi.todoEntityList();
    }

    @Override
    public Observable<ToDoEntity> todoEntityDetails(final String id) {
        // this.toDoCache::put
        return this.restApi.todoEntityById(id).doOnNext(new Consumer<ToDoEntity>() {
            @Override
            public void accept(ToDoEntity toDoEntity) throws Exception {
                CloudToDoDataStore.this.todoCache.put(toDoEntity);
            }
        });
    }

    private void onNext() {
    }
}
