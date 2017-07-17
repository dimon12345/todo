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
 * Created by dmitryz on 7/16/17.
 */

public class DeleteToDoListItem extends UseCase<List<ToDoItem>, DeleteToDoListItem.Params>  {
    private final ToDoRepository itemsRepository;

    @Inject
    DeleteToDoListItem(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<List<ToDoItem>> buildUseCaseObservable(final Params params) {

        return Observable.defer(new Callable<Observable<List<ToDoItem>>>() {
            @Override
            public Observable<List<ToDoItem>> call() throws Exception {
                return itemsRepository.deleteById(params.id)
                        .flatMap(new Function<Boolean, Observable<List<ToDoItem>>>() {
                            @Override
                            public Observable<List<ToDoItem>> apply(Boolean aBoolean) throws Exception {
                                return itemsRepository.getElements();
                            }
                        });
            }
        });
    }

    public static class Params {
        private final long id;

        public Params(long id) {
            this.id = id;
        }
    }
}
