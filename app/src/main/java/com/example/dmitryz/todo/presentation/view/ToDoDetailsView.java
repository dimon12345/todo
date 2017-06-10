package com.example.dmitryz.todo.presentation.view;

import com.example.dmitryz.todo.presentation.model.ToDoModel;

/**
 * Created by q on 08.06.17.
 */

public interface ToDoDetailsView extends LoadDataView {

    void renderToDo(ToDoModel todoModel);
}
