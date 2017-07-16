package com.example.dmitryz.todo.data.repository.datasource.room;

import com.example.dmitryz.todo.data.entity.ToDoEntity;

import java.util.List;

import io.reactivex.ObservableSource;

/**
 * Created by dmitryz on 7/16/17.
 */

public class ToDoRoomEntityMapper {
    public ToDoEntity convert(ToDoRoomEntity roomEntity) {
        ToDoEntity result = new ToDoEntity();

        result.setID(Integer.toString(roomEntity.id));
        result.setTitle(roomEntity.title);
        result.setBody(roomEntity.body);

        return result;
    }

    public ToDoRoomEntity convert(ToDoEntity entity) {
        ToDoRoomEntity result = new ToDoRoomEntity();
        result.id = Integer.parseInt(entity.getID());
        result.title = entity.getTitle();
        result.body = entity.getBody();
        return result;
    }
}
