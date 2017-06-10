package com.example.dmitryz.todo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitryz on 5/30/17.
 */

public class ToDoItem {
    String id;

    public ToDoItem(String id) {
        this.id = id;
    }

    String title;
    String body;

    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getID() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}
