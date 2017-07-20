package com.example.dmitryz.todo.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.ToDoSet;
import com.example.dmitryz.todo.domain.exception.DefaultErrorBundle;
import com.example.dmitryz.todo.domain.exception.ErrorBundle;
import com.example.dmitryz.todo.domain.interactor.DefaultObserver;
import com.example.dmitryz.todo.domain.interactor.GetToDoSets;
import com.example.dmitryz.todo.presentation.mapper.ToDoSetModelDataMapper;
import com.example.dmitryz.todo.presentation.model.ToDoSetModel;
import com.example.dmitryz.todo.presentation.view.ToDoSetsView;

import java.util.List;

/**
 * Created by q on 7/19/17.
 */

public class ToDoSetsPresenter implements Presenter {
    private ToDoSetsView todoSetsView;

    private final GetToDoSets getToDoSetsUseCase;
    private final ToDoSetModelDataMapper todoSetModelDataMapper;

    private List<ToDoSetModel> cachedList;

    public ToDoSetsPresenter(GetToDoSets getToDoSetsUseCase,
                             ToDoSetModelDataMapper todoSetModelDataMapper) {
        this.getToDoSetsUseCase = getToDoSetsUseCase;
        this.todoSetModelDataMapper = todoSetModelDataMapper;
    }

    public void setView(@NonNull ToDoSetsView todoSetsView) {
        this.todoSetsView = todoSetsView;
    }

    @Override
    public void resume() {
        getToDoSets();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getToDoSetsUseCase.dispose();
        todoSetsView = null;
    }

    public void initialize() {
        loadToDoSets();
    }

    private void loadToDoSets() {
        hideViewRetry();
        showViewLoading();
        getToDoSets();
    }

    public void retry() {
        loadToDoSets();
    }

    private void showViewLoading() {
        todoSetsView.showLoading();
    }

    private void hideViewLoading() {
        todoSetsView.hideLoading();
    }

    private void showViewRetry() {
        todoSetsView.showRetry();
    }

    private void hideViewRetry() {
        todoSetsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(todoSetsView.context(),
                errorBundle.getException());
        todoSetsView.showError(errorMessage);
    }

    private void showToDoCollectionInView(List<ToDoSet> todoSetsList) {
        final List<ToDoSetModel> todoSetsModelsCollection =
                todoSetModelDataMapper.transform(todoSetsList);
        todoSetsView.renderToDoSetsList(todoSetsModelsCollection );
        cachedList = todoSetsModelsCollection ;
    }

    private void getToDoSets() {
        getToDoSetsUseCase.execute(new ToDoSetLoadingObserver(), null);
    }

    private final class ToDoSetLoadingObserver extends DefaultObserver<List<ToDoSet>> {
        @Override
        public void onComplete() {
            ToDoSetsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ToDoSetsPresenter.this.hideViewLoading();
            ToDoSetsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ToDoSetsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ToDoSet> todoItems) {
            ToDoSetsPresenter.this.showToDoCollectionInView(todoItems);
        }
    }
}
