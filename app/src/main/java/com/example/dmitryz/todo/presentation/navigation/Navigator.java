package com.example.dmitryz.todo.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.dmitryz.todo.presentation.view.activity.ToDoDetailsActivity;
import com.example.dmitryz.todo.presentation.view.activity.ToDoListActivity;

import javax.inject.Inject;

/**
 * Created by q on 05.06.17.
 */

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToToDoList(Context context) {
        if (context != null) {
            Intent intentToLaunch = ToDoListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToToDoDetails(Context context, String id) {
        if (context != null) {
            Intent intentToLaunch = ToDoDetailsActivity.getCallingIntent(context, id);
            context.startActivity(intentToLaunch);
        }
    }
}
