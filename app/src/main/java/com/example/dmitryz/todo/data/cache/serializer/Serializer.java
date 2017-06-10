package com.example.dmitryz.todo.data.cache.serializer;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dmitryz on 5/31/17.
 */

@Singleton
public class Serializer {
    private final Gson gson = new Gson();

    @Inject
    Serializer() {}

    public String serialize(Object object, Class clazz) { return gson.toJson(object, clazz);}

    public <T> T deserialize(String string, Class<T> clazz) { return gson.fromJson(string, clazz);}
}
