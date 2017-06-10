package com.example.dmitryz.todo.presentation.presenter;

import android.content.Context;

import com.example.dmitryz.todo.data.exception.NetworkConnectionException;
import com.example.dmitryz.todo.data.exception.ToDoItemNotFound;

/**
 * Created by q on 09.06.17.
 */

class ErrorMessageFactory {
    private ErrorMessageFactory() {

    }

    public static String create(Context context, Exception exception) {
        String message = "There was an application error";

        if (exception instanceof NetworkConnectionException) {
            message = "There is no internet connection";
        } else if (exception instanceof ToDoItemNotFound) {
            message = "Cannot retrieve user data. Check your internet connection";
        }

        return message;
    }
}
