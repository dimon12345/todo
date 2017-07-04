package com.example.dmitryz.todo.data.entity.mapper;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.domain.ToDoItem;

import javax.inject.Inject;

/**
 * Created by dmitryz on 7/2/17.
 */

public class ToDoItemDataMapper {

    @Inject
    ToDoItemDataMapper() {
    }

    public ToDoEntity transform(ToDoItem toDoItem) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setID(toDoItem.getID());
        toDoEntity.setTitle(toDoItem.getTitle());
        toDoEntity.setBody(toDoItem.getBody());

        return toDoEntity;
    }
}
