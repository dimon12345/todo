package com.example.dmitryz.todo.data.repository.datasource.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by dmitryz on 7/16/17.
 */

@Database(entities = {ToDoRoomEntity.class}, version=1)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract ToDoRoomEntityDao toDoEntityDao();
}
