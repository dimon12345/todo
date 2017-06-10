package com.example.dmitryz.todo.data.repository;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.mapper.ToDoEntityDataMapper;
import com.example.dmitryz.todo.data.repository.datasource.ToDoDataStore;
import com.example.dmitryz.todo.data.repository.datasource.ToDoDataStoreFactory;
import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by q on 03.06.17.
 */

public class ToDoDataRepository implements ToDoRepository {

    private final ToDoDataStoreFactory toDoDataStoreFactory;
    private final ToDoEntityDataMapper toDoEntityDataMapper;

    @Inject
    ToDoDataRepository(ToDoDataStoreFactory dataStoreFactory,
                       ToDoEntityDataMapper toDoEntityDataMapper) {
        this.toDoDataStoreFactory = dataStoreFactory;
        this.toDoEntityDataMapper = toDoEntityDataMapper;
    }


    @Override
    public Observable<List<ToDoItem>> getElements() {
        final ToDoDataStore toDoDataStore = this.toDoDataStoreFactory.createCloudDataStore();
        return toDoDataStore.todoEntityList().map(new Function<List<ToDoEntity>, List<ToDoItem>>() {
            @Override
            public List<ToDoItem> apply(List<ToDoEntity> toDoEntities) throws Exception {
                return toDoEntityDataMapper.transform(toDoEntities);
            }
        });
    }

    @Override
    public Observable<ToDoItem> getElement(String id) {
        final ToDoDataStore toDoDataStore = this.toDoDataStoreFactory.create(id);
        return toDoDataStore.todoEntityDetails(id).map(new Function<ToDoEntity, ToDoItem>() {
            @Override
            public ToDoItem apply(ToDoEntity todoEntity) throws Exception {
                return toDoEntityDataMapper.transform(todoEntity);
            }
        });
    }
}
