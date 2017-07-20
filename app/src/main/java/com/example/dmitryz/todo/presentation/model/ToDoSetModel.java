package com.example.dmitryz.todo.presentation.model;

/**
 * Created by q on 7/19/17.
 */

public class ToDoSetModel {
    long id;
    String name;
    String filename;

    public ToDoSetModel(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
