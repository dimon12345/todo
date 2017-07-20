package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoSet;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by q on 7/19/17.
 */

public class GetToDoSets extends UseCase<List<ToDoSet>, Void> {

    private final ToDoRepository itemsRepository;

    @Inject
    GetToDoSets(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<List<ToDoSet>> buildUseCaseObservable(Void aVoid) {
        return itemsRepository.getSets();
    }
}
