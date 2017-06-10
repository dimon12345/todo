package com.example.dmitryz.todo.domain.exception;

/**
 * Created by dmitryz on 5/31/17.
 */

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
