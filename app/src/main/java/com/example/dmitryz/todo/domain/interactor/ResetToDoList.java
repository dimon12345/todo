package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;
import com.example.dmitryz.todo.presentation.mapper.ToDoModelDataMapper;
import com.example.dmitryz.todo.presentation.model.ToDoModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by dmitryz on 7/8/17.
 */

public class ResetToDoList extends UseCase<List<ToDoItem>, Void> {

    private final ToDoRepository itemsRepository;
    private final ToDoModelDataMapper toDoModelDataMapper;

    @Inject
    ResetToDoList(ToDoRepository itemsRepository,
                  ThreadExecutor threadExecutor,
                  PostExecutionThread postExecutionThread,
                  ToDoModelDataMapper toDoModelDataMapper
    ) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
        this.toDoModelDataMapper = toDoModelDataMapper;
    }

    @Override
    Observable<List<ToDoItem>> buildUseCaseObservable(Void unused) {
        return Observable.defer(new Callable<Observable<List<ToDoItem>>>() {
            @Override
            public Observable<List<ToDoItem>> call() throws Exception {
                return itemsRepository.reset()
                .flatMap(new Function<Boolean, Observable<List<ToDoItem>>>() {
                    @Override
                    public Observable<List<ToDoItem>> apply(Boolean unused) throws Exception {
                        return itemsRepository.getElements();
                    }
                });
            }
        });
    }
}
