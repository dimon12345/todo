package com.example.dmitryz.todo.data.cache;

import android.content.Context;

import com.example.dmitryz.todo.data.cache.serializer.Serializer;
import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.data.exception.ToDoItemNotFound;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by dmitryz on 6/2/17.
 */

public class ToDoCacheImpl implements ToDoCache {

    private static final String SETTINGS_FILE_NAME = "com.example.dmitryz.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "todo_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final Serializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    @Inject
    ToDoCacheImpl(Context context, Serializer serializer, FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }

        this.context = context;
        this.cacheDir = context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<ToDoEntity> get(final String id) {
        return Observable.create(new ObservableOnSubscribe<ToDoEntity>() {
            @Override
            public void subscribe(ObservableEmitter<ToDoEntity> emitter) throws Exception {
                final File todoEntityFile = ToDoCacheImpl.this.buildFile(id);
                final String fileContent = ToDoCacheImpl.this.fileManager.readFileContent(todoEntityFile);
                final ToDoEntity toDoEntity = ToDoCacheImpl.this.serializer.deserialize(fileContent, ToDoEntity.class);

                if (toDoEntity != null) {
                    emitter.onNext(toDoEntity);
                    emitter.onComplete();
                } else {
                    emitter.onError(new ToDoItemNotFound());
                }
            }
        });
    }

    @Override
    public void put(ToDoEntity entity) {
        if(entity != null) {
            final File todoItemFile = this.buildFile(entity.getID());
            if (!isCached(entity.getID())) {
                final String jsonString = this.serializer.serialize(entity, ToDoEntity.class);
                this.executeAsynchronously(new CacheWriter(this.fileManager, todoItemFile, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(String id) {
        final File todoItemFile = this.buildFile(id);
        return this.fileManager.exists(todoItemFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);
        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    private File buildFile(String id) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(id);

        return new File(fileNameBuilder.toString());
    }

    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }
        @Override
        public void run() {
            this.fileManager.clearDirectory(cacheDir);
        }
    }
}
