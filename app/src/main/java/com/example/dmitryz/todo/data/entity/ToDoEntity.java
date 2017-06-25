package com.example.dmitryz.todo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dmitryz on 6/2/17.
 */

public class ToDoEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    String title;

    @SerializedName("body")
    String body;

    @SerializedName("subitems")
    List<String> subitems;

    public ToDoEntity() {
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public List<String> getSubItems() {
        return subitems;
    }

    public void setSubItems(List<String> subitems) {
        this.subitems = subitems;
    }


}
