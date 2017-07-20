package com.example.dmitryz.todo.presentation.mapper;

import com.example.dmitryz.todo.domain.ToDoSet;
import com.example.dmitryz.todo.presentation.model.ToDoSetModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by q on 7/19/17.
 */

public class ToDoSetModelDataMapper {

    @Inject
    public ToDoSetModelDataMapper() {
    }

    public List<ToDoSetModel> transform(List<ToDoSet> todoSetsList) {
        List<ToDoSetModel> todoSetModelsList;

        if (todoSetsList != null && !todoSetsList.isEmpty()) {
            todoSetModelsList = new ArrayList<>();
            for (ToDoSet todoSet : todoSetsList) {
                todoSetModelsList.add(transform(todoSet));
            }
        } else {
            todoSetModelsList = new ArrayList<>();
        }
        return todoSetModelsList;
    }

    private ToDoSetModel transform(ToDoSet todoSet) {
        if (todoSet == null) {
            throw new IllegalArgumentException("Cannot transform a null value of todo set");
        }

        long id = Long.parseLong(todoSet.getId());
        final ToDoSetModel todoModel = new ToDoSetModel(id);

        todoModel.setName(todoSet.getName());
        todoModel.setFilename(todoSet.getFilename());

        return todoModel;
    }
}
