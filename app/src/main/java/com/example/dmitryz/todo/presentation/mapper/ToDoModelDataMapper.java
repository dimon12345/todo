package com.example.dmitryz.todo.presentation.mapper;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.presentation.model.ToDoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by q on 08.06.17.
 */

public class ToDoModelDataMapper {
    @Inject
    public ToDoModelDataMapper() {
    }

    public ToDoModel transform(ToDoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("Cannot transform a null value of todo item");
        }

        long id = Long.parseLong(todoItem.getID());
        final ToDoModel todoModel = new ToDoModel(id);

        todoModel.setTitle(todoItem.getTitle());
        todoModel.setBody(todoItem.getBody());
        return todoModel;
    }

    public List<ToDoModel> transform(List<ToDoItem> todoItemsList) {
        List<ToDoModel> todoModelsList;

        if (todoItemsList != null && !todoItemsList.isEmpty()) {
            todoModelsList = new ArrayList<>();
            for (ToDoItem todoItem : todoItemsList) {
                todoModelsList.add(transform(todoItem));
            }
        } else {
            todoModelsList = new ArrayList<>();
        }
        return todoModelsList;
    }

}
