package com.example.dmitryz.todo.domain.interactor;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.executor.PostExecutionThread;
import com.example.dmitryz.todo.domain.executor.ThreadExecutor;
import com.example.dmitryz.todo.domain.repository.ToDoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dmitryz on 5/31/17.
 */

public class GetToDoDetails extends UseCase<ToDoItem, GetToDoDetails.Params> {
    private final ToDoRepository itemsRepository;

    @Inject
    GetToDoDetails(ToDoRepository itemsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemsRepository = itemsRepository;
    }

    @Override
    Observable<ToDoItem> buildUseCaseObservable(Params params) {
        return itemsRepository.getElement(params.id);
    }

    public static final class Params {
        private Params(String id) {
            this.id = id;
        }
        private String id;

        public static Params forToDoId(String id) {
            return new Params(id);
        }
    }
}
