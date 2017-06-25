package com.example.dmitryz.todo.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.dmitryz.todo.domain.interactor.AddToDoItem;
import com.example.dmitryz.todo.presentation.view.AddToDoItemView;

import javax.inject.Inject;

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
}
