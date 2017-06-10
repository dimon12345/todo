package com.example.dmitryz.todo.presentation.view;

import com.example.dmitryz.todo.presentation.model.ToDoModel;

import java.util.Collection;

/**
 * Created by q on 05.06.17.
 */

public interface ToDoListView extends LoadDataView {

    void renderToDoList(Collection<ToDoModel> toDoModelCollection);

    void viewToDo(ToDoModel todoModel);
}
