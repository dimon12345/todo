package com.example.dmitryz.todo.data.repository.datasource.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by dmitryz on 7/15/17.
 */

@Dao
public interface ToDoRoomEntityDao {
    @Query("SELECT * FROM todo")
    List<ToDoRoomEntity> getAll();


    @Query("SELECT * FROM todo WHERE id = :id")
    ToDoRoomEntity loadById(int id);

    @Insert
    void insert(ToDoRoomEntity entity);

    @Query("DELETE FROM todo WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM todo")
    void dropTable();
}
