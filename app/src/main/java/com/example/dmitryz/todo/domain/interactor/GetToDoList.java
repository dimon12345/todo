package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 5/31/17.
 */

public class GetToDoList extends UseCase<List<ToDoItem>, Void> {

    private final ToDoRepository itemsRepository;

    @Inject
    GetToDoList(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<List<ToDoItem>> buildUseCaseObservable(Void unused) {
        return itemsRepository.getElements();
    }
}
