package com.example.dmitryz.todo.data.repository.datasource.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dmitryz on 7/15/17.
 */

@Entity(tableName = "todo")
public class ToDoRoomEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;
    public String body;
}
