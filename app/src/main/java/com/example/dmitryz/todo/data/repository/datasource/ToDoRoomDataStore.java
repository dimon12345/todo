package com.example.dmitryz.todo.data.repository.datasource;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.ToDoEntityList;
import com.example.dmitryz.todo.data.repository.datasource.room.ApplicationDatabase;
import com.example.dmitryz.todo.data.repository.datasource.room.ToDoRoomEntity;
import com.example.dmitryz.todo.data.repository.datasource.room.ToDoRoomEntityMapper;
import com.example.dmitryz.todo.data.repository.utils.AssetLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by dmitryz on 7/15/17.
 */

public class ToDoRoomDataStore implements ToDoDataStore {

    final ApplicationDatabase db;
    final ToDoRoomEntityMapper toDoRoomEntityMapper = new ToDoRoomEntityMapper();
    final Context context;

    public ToDoRoomDataStore(Context context) {
        this.context = context;
        //db = Room.databaseBuilder(context, ApplicationDatabase.class, "tododb").allowMainThreadQueries().build();
        db = Room.databaseBuilder(context, ApplicationDatabase.class, "tododb").build();
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {
        return Observable.defer(new Callable<ObservableSource<List<ToDoRoomEntity>>>() {
            @Override
            public ObservableSource<List<ToDoRoomEntity>> call() throws Exception {
                return Observable.just(db.toDoEntityDao().getAll());
            }
        }).map(new Function<List<ToDoRoomEntity>, List<ToDoEntity>>() {
            @Override
            public List<ToDoEntity> apply(List<ToDoRoomEntity> toDoRoomEntities) throws Exception {
                List<ToDoEntity> result = new ArrayList<>();
                for (ToDoRoomEntity roomEntity : toDoRoomEntities) {
                    result.add(toDoRoomEntityMapper.convert(roomEntity));
                }
                return result;
            }
        });
    }

    @Override
    public Observable<ToDoEntity> todoEntityDetails(final long id) {

        return Observable.defer(new Callable<ObservableSource<ToDoEntity>>() {
            @Override
            public ObservableSource<ToDoEntity> call() throws Exception {
                return Observable.just(db.toDoEntityDao().loadById(id))
                        .map(new Function<ToDoRoomEntity, ToDoEntity>() {
                            @Override
                            public ToDoEntity apply(ToDoRoomEntity toDoRoomEntity) throws Exception {
                                return toDoRoomEntityMapper.convert(toDoRoomEntity);
                            }
                        });
            }
        });
    }

    @Override
    public Observable<Void> addEntity(ToDoEntity entity) {
        final ToDoEntity finalEntity = entity;
        return Observable.defer(new Callable<ObservableSource<Void>>() {
            @Override
            public ObservableSource<Void> call() throws Exception {
                db.toDoEntityDao().insert(toDoRoomEntityMapper.convert(finalEntity));
                return Observable.empty();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteById(final long id) {

        return Observable.defer(new Callable<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() throws Exception {
                db.toDoEntityDao().deleteById(id);
                return Observable.just(true);
            }
        });
    }

    @Override
    public Observable<Boolean> reset() {
        return Observable.defer(new Callable<ObservableSource<Boolean>>() {
            @Override
            public ObservableSource<Boolean> call() throws Exception {
                db.toDoEntityDao().dropTable();
                fillData();
                return Observable.just(true);
            }
        });
    }

    private List<ToDoEntity> getData() {
        final String json = AssetLoader.loadJSONFromAsset(context, "todo.json");
        Gson gson = new Gson();
        ToDoEntityList todoEntityList = gson.fromJson(json, ToDoEntityList.class);
        return todoEntityList.getToDoEntities();
    }

    private void fillData(){
        List<ToDoEntity> data = getData();

        for(ToDoEntity entity:data){
            db.toDoEntityDao().insert(toDoRoomEntityMapper.convert(entity));
        }
    }
}
