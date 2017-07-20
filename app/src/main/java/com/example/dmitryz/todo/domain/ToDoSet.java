package com.example.dmitryz.todo.domain;

/**
 * Created by q on 7/19/17.
 */

public class ToDoSet {
    private final String id;
    private String name;
    private String filename;

    public ToDoSet(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
