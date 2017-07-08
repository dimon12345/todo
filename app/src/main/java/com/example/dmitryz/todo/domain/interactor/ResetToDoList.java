package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 7/8/17.
 */

public class ResetToDoList extends UseCase<Void, Void> {

    private final ToDoRepository itemsRepository;

    @Inject
    ResetToDoList(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Void unused) {
        return itemsRepository.reset();
    }
}
