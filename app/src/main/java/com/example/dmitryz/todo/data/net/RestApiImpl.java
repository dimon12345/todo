package com.example.dmitryz.todo.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.entity.mapper.ToDoEntityJsonMapper;
import com.example.dmitryz.todo.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by dmitryz on 6/2/17.
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final ToDoEntityJsonMapper todoEntityJsonMapper;

    public RestApiImpl(Context context, ToDoEntityJsonMapper todoEntityJsonMapper) {
        if (context == null || todoEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }

        this.context = context;
        this.todoEntityJsonMapper = todoEntityJsonMapper;
    }

    @Override
    public Observable<List<ToDoEntity>> todoEntityList() {
        return Observable.create( new ObservableOnSubscribe<List<ToDoEntity>>() {

            @Override
            public void subscribe(ObservableEmitter<List<ToDoEntity>> emitter) throws Exception {
               if (isThereInternetConnection()) {
                   try {
                       String responseEntities = getToDoEntitiesFromApi();
                       if (responseEntities != null) {
                           emitter.onNext(todoEntityJsonMapper.transformToDoEntityCollection(responseEntities));
                           emitter.onComplete();
                       } else {
                           emitter.onError(new NetworkConnectionException());
                       }
                   } catch (Exception e) {
                       emitter.onError(new NetworkConnectionException(e.getCause()));
                   }
               } else {
                   emitter.onError(new NetworkConnectionException());
               }

            }
        });
    }

    @Override
    public Observable<ToDoEntity> todoEntityById(final String id) {
        return Observable.create(new ObservableOnSubscribe<ToDoEntity>() {

            @Override
            public void subscribe(ObservableEmitter<ToDoEntity> emitter) throws Exception {
                if (isThereInternetConnection()) {
                    try {
                        String responseUserDetails = getUserDetailsFromApi(id);
                        if (responseUserDetails != null) {
                            emitter.onNext(todoEntityJsonMapper.transformToDoEntity(responseUserDetails));
                            emitter.onComplete();
                        } else {
                            emitter.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        emitter.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    emitter.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getToDoEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_TODO_LIST).requestSyncCall();
    }

    private String getUserDetailsFromApi(String id) throws MalformedURLException {
        String apiUrl = API_URL_GET_TODO_DETAILS + id + ".json";
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return false;
    }
}
