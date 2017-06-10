package com.example.dmitryz.todo.presentation.mapper;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.presentation.model.ToDoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        final ToDoModel todoModel = new ToDoModel(todoItem.getID());
        todoModel.setTitle(todoItem.getTitle());
        todoModel.setBody(todoItem.getBody());
        return todoModel;
    }

    public Collection<ToDoModel> transform(Collection<ToDoItem> todoCollection) {
        Collection<ToDoModel> todoModelsCollection;

        if (todoCollection != null && !todoCollection.isEmpty()) {
            todoModelsCollection = new ArrayList<>();
            for (ToDoItem todoItem : todoCollection) {
                todoModelsCollection.add(transform(todoItem));
            }
        } else {
            todoModelsCollection = Collections.emptyList();
        }
        return todoModelsCollection;
    }

}
