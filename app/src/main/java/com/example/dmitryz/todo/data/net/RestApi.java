package com.example.dmitryz.todo.data.net;

import com.example.dmitryz.todo.data.entity.ToDoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 6/2/17.
 */

public interface RestApi {

    String API_BASE_URL = "http://static.agoradoxa.net/android/todo/";

    String API_URL_GET_TODO_LIST = API_BASE_URL + "todo.json";
    String API_URL_GET_TODO_DETAILS = API_BASE_URL + "user_";

    Observable<List<ToDoEntity>> todoEntityList();

    Observable<ToDoEntity> todoEntityById(final String id);
}
