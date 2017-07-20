package com.example.dmitryz.todo.presentation.view.fragment;

import android.content.Context;

import com.example.dmitryz.todo.presentation.model.ToDoSetModel;
import com.example.dmitryz.todo.presentation.presenter.ToDoSetsPresenter;
import com.example.dmitryz.todo.presentation.view.ToDoSetsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by q on 7/19/17.
 */

public class ToDoSetsFragment extends BaseFragment implements ToDoSetsView {

    @Inject
    ToDoSetsPresenter todoListPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void renderToDoSetsList(List<ToDoSetModel> toDoSetModelsList) {

    }
}
