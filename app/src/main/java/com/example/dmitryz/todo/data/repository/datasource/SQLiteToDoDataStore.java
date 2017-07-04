package com.example.dmitryz.todo.data.repository.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.repository.utils.ToDoSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 7/2/17.
 */

public class SQLiteToDoDataStore implements ToDoDataStore {

    Context context;

    final private String COLUMN_NAME_ID = "_id";
    final private String COLUMN_NAME_TITLE = "title";
    final private String COLUMN_NAME_BODY = "body";
    final private String TABLE_NAME = "todo";

    final private String[] columnsNames = {
            COLUMN_NAME_ID,
            COLUMN_NAME_TITLE,
            COLUMN_NAME_BODY
    };

    public SQLiteToDoDataStore(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {
        ToDoSQLiteOpenHelper sqLiteOpenHelper = new ToDoSQLiteOpenHelper(context);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();


        Cursor cursor = db.query(
                TABLE_NAME,
                columnsNames,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        List<ToDoEntity> result = new ArrayList<ToDoEntity>();
        while(true) {
            ToDoEntity entity = new ToDoEntity();
            entity.setID(Integer.toString(cursor.getInt(0)));
            entity.setTitle(cursor.getString(1));
            entity.setBody(cursor.getString(2));

            result.add(entity);
            if( !cursor.moveToNext() )
                break;
        }

        return Observable.just(result);
    }

    @Override
    public Observable<ToDoEntity> todoEntityDetails(String id) {
        ToDoSQLiteOpenHelper sqLiteOpenHelper = new ToDoSQLiteOpenHelper(context);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                columnsNames,
                "_id=?",
                new String[] {id},
                null,
                null,
                null);

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return Observable.error(new Throwable("item not founded with id=" + id));
        }

        ToDoEntity entity = new ToDoEntity();
        entity.setID(Integer.toString(cursor.getInt(0)));
        entity.setTitle(cursor.getString(1));
        entity.setBody(cursor.getString(2));

        return Observable.just(entity);
    }

    @Override
    public Observable<Void> addEntity(ToDoEntity entity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, entity.getID());
        values.put(COLUMN_NAME_TITLE, entity.getTitle());
        values.put(COLUMN_NAME_BODY, entity.getBody());

        ToDoSQLiteOpenHelper sqLiteOpenHelper = new ToDoSQLiteOpenHelper(context);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        long newRowId;
        newRowId = db.insert(
                TABLE_NAME,
                null,
                values);
        return Observable.empty();
    }
}
