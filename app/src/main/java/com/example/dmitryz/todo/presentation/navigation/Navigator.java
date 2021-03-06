package com.example.dmitryz.todo.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.dmitryz.todo.presentation.view.activity.AddToDoActivity;
import com.example.dmitryz.todo.presentation.view.activity.ToDoDetailsActivity;

import javax.inject.Inject;

/**
 * Created by q on 05.06.17.
 */

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToToDoDetails(Context context, long id) {
        if (context != null) {
            Intent intentToLaunch = ToDoDetailsActivity.getCallingIntent(context, id);
            context.startActivity(intentToLaunch);
        }
    }

    public void addNewItem(Context context) {
        if (context != null) {
            Intent intentToLaunch = AddToDoActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
