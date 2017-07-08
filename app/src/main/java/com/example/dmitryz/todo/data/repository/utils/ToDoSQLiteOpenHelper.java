package com.example.dmitryz.todo.data.repository.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.ToDoEntityList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dmitryz on 7/4/17.
 */

public class ToDoSQLiteOpenHelper extends SQLiteOpenHelper {


    final static int DB_VER = 1;
    final static String DB_NAME = "todo.db";
    final String TABLE_NAME = "todo";
    final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            "( _id INTEGER PRIMARY KEY , "+
            " title TEXT, body TEXT)";
    final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private final String DEBUG_TAG = "DBHelper";

    Context context;

    public ToDoSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);

        Log.d(DEBUG_TAG,"constructor called");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_TAG,"onCreate() called");
        db.execSQL(CREATE_TABLE);
        fillData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void reset(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    private List<ToDoEntity> getData() {
        //InputStream stream = null;
        ArrayList<ToDoEntity> list = new ArrayList<ToDoEntity>();
        final String json = AssetLoader.loadJSONFromAsset(context, "todo.json");
        Gson gson = new Gson();
        ToDoEntityList todoEntityList = gson.fromJson(json, ToDoEntityList.class);
        return todoEntityList.getToDoEntities();
    }

    private void fillData(SQLiteDatabase db){
        List<ToDoEntity> data = getData();

        if( db != null ){
            ContentValues values;

            for(ToDoEntity entity:data){
                values = new ContentValues();
                values.put("title", entity.getTitle());
                values.put("body", entity.getBody());
                db.insert(TABLE_NAME, null, values);
            }
        }
        else {
            Log.d(DEBUG_TAG,"db null");
        }
    }
}
