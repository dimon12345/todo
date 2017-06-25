package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 6/26/17.
 */

public class AddToDoItem  extends UseCase<Boolean, ToDoItem>  {
    private final ToDoRepository itemsRepository;


    @Inject
    AddToDoItem(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(ToDoItem toDoItem) {
        return itemsRepository.addToDoItem(toDoItem);
    }
}
