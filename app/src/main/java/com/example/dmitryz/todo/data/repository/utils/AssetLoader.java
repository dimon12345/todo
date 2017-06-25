package com.example.dmitryz.todo.data.repository.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmitryz on 6/24/17.
 */

public class AssetLoader {
    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getResources().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
