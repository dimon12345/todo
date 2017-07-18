package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by dmitryz on 6/26/17.
 */

public class AddToDoItem extends UseCase<Boolean, ToDoItem> {
    private final ToDoRepository itemsRepository;


    @Inject
    AddToDoItem(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(ToDoItem toDoItem) {
        final ToDoItem localToDoItem = toDoItem;
        return Observable.defer(new Callable<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() throws Exception {
                return itemsRepository.addToDoItem(localToDoItem);
            }
        });
    }
}
