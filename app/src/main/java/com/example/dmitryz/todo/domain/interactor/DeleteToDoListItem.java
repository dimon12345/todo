package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by dmitryz on 7/16/17.
 */

public class DeleteToDoListItem extends UseCase<Void, DeleteToDoListItem.Params>  {
    private final ToDoRepository itemsRepository;

    @Inject
    DeleteToDoListItem(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }
    @Override
    Observable<Void> buildUseCaseObservable(final Params params) {

        final Params localParams = params;
        return Observable.defer(new Callable<ObservableSource<Void>>() {
            @Override
            public ObservableSource<Void> call() throws Exception {
                return itemsRepository.deleteById(Long.toString(params.id));
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
