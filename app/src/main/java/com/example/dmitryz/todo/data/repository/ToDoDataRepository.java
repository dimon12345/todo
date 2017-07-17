package com.example.dmitryz.todo.data.repository;

import android.util.Log;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.mapper.ToDoEntityDataMapper;
import com.example.dmitryz.todo.data.entity.mapper.ToDoItemDataMapper;
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
    private final ToDoItemDataMapper toDoItemDataMapper;

    @Inject
    ToDoDataRepository(ToDoDataStoreFactory dataStoreFactory,
                       ToDoEntityDataMapper toDoEntityDataMapper,
                       ToDoItemDataMapper toDoItemDataMapper) {
        this.toDoDataStoreFactory = dataStoreFactory;
        this.toDoEntityDataMapper = toDoEntityDataMapper;
        this.toDoItemDataMapper = toDoItemDataMapper;
    }


    @Override
    public Observable<List<ToDoItem>> getElements() {
        return getToDoDataStore().todoEntityList().map(new Function<List<ToDoEntity>, List<ToDoItem>>() {
            @Override
            public List<ToDoItem> apply(List<ToDoEntity> toDoEntities) throws Exception {
                return toDoEntityDataMapper.transform(toDoEntities);
            }
        });
    }

    @Override
    public Observable<ToDoItem> getElement(String id) {
        return getToDoDataStore().todoEntityDetails(id).map(new Function<ToDoEntity, ToDoItem>() {
            @Override
            public ToDoItem apply(ToDoEntity todoEntity) throws Exception {
                return toDoEntityDataMapper.transform(todoEntity);
            }
        });
    }

    @Override
    public Observable<Void> addToDoItem(ToDoItem toDoItem) {
        ToDoEntity toDoEntity = toDoItemDataMapper.transform(toDoItem);
        return getToDoDataStore().addEntity(toDoEntity);
    }

    @Override
    public Observable<Void> reset() {
        return getToDoDataStore().reset();
    }

    @Override
    public Observable<Void> deleteById(String id) {
        return getToDoDataStore().deleteById(id);
    }

    ToDoDataStore getToDoDataStore() {
        return this.toDoDataStoreFactory.createRoomDataStore();
    }
}
