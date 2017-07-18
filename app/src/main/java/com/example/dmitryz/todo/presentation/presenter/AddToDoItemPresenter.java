package com.example.dmitryz.todo.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.interactor.AddToDoItem;
import com.example.dmitryz.todo.presentation.view.AddToDoItemView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by dmitryz on 6/26/17.
 */

public class AddToDoItemPresenter implements Presenter {

    private AddToDoItemView addToDoItemView;

    AddToDoItem addToDoItemUseCase;

    @Inject
    AddToDoItemPresenter(AddToDoItem addToDoItemUseCase) {
        this.addToDoItemUseCase = addToDoItemUseCase;
    }

    public void setView(@NonNull AddToDoItemView view) {
        this.addToDoItemView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        addToDoItemUseCase.dispose();
        addToDoItemView = null;
    }

    public void addToDoItem(String title, String body) {
        ToDoItem item = new ToDoItem(null);
        item.setTitle(title);
        item.setBody(body);

        addToDoItemUseCase.execute(new ToDoItemAddObserver(), item);
    }

    private class ToDoItemAddObserver extends DisposableObserver<Boolean> {
        @Override
        public void onNext(Boolean unused) {
            addToDoItemView.done();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
