package com.example.dmitryz.todo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dmitryz on 6/24/17.
 */

public class ToDoEntityList {
    @SerializedName("todo")
    public List<ToDoEntity> toDoEntities;

    public List<ToDoEntity> getToDoEntities() {
        return toDoEntities;
    }
}
