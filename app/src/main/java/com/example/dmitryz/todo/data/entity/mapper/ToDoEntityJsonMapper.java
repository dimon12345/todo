package com.example.dmitryz.todo.data.entity.mapper;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dmitryz on 6/2/17.
 */

public class ToDoEntityJsonMapper {
    private final Gson gson;

    @Inject
    public ToDoEntityJsonMapper() {
        this.gson = new Gson();
    }

    public ToDoEntity transformToDoEntity(String todoJsonResponse) throws JsonSyntaxException {
        final Type todoEntityType = new TypeToken<ToDoEntity>() {}.getType();
        return this.gson.fromJson(todoJsonResponse, todoEntityType);
    }

    public List<ToDoEntity> transformToDoEntityCollection(String todoListJsonResponse) {
        final Type listOfToDoEntityType = new TypeToken<List<ToDoEntity>>() {}.getType();
        return this.gson.fromJson(todoListJsonResponse, listOfToDoEntityType);
    }


}
