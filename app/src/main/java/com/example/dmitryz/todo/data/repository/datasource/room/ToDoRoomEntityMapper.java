package com.example.dmitryz.todo.data.repository.datasource.room;

import android.text.TextUtils;

import com.example.dmitryz.todo.data.entity.ToDoEntity;

import java.util.List;

import io.reactivex.ObservableSource;

/**
 * Created by dmitryz on 7/16/17.
 */

public class ToDoRoomEntityMapper {
    public ToDoEntity convert(ToDoRoomEntity roomEntity) {
        ToDoEntity result = new ToDoEntity();

        result.setID(Long.toString(roomEntity.id));
        result.setTitle(roomEntity.title);
        result.setBody(roomEntity.body);

        return result;
    }

    public ToDoRoomEntity convert(ToDoEntity entity) {
        ToDoRoomEntity result = new ToDoRoomEntity();
        if (!TextUtils.isEmpty(entity.getID())) {
            result.id = Integer.parseInt(entity.getID());
        } else {
            // Room field with autoGenerate
            // insert methods treat 0 as not-set while inserting the item.
            result.id = 0;
        }

        result.title = entity.getTitle();
        result.body = entity.getBody();
        return result;
    }
}
