package com.example.dmitryz.todo.presentation.view;

import com.example.dmitryz.todo.presentation.model.ToDoSetModel;

import java.util.List;

/**
 * Created by q on 7/19/17.
 */

public interface ToDoSetsView extends LoadDataView {
    void renderToDoSetsList(List<ToDoSetModel> toDoSetModelsList);
}
