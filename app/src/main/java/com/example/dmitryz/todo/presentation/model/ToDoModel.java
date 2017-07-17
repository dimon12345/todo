package com.example.dmitryz.todo.presentation.model;

/**
 * Created by q on 05.06.17.
 */

public class ToDoModel {

    private final long id;

    public ToDoModel(long id) {
        this.id = id;
    }

    private String title;
    private String body;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
