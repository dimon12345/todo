package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    Observable<Void> buildUseCaseObservable(Params params) {

        return null;
    }

    public class Params {
        private final String id;

        private Params(String id) {
            this.id = id;
        }

    }
}
