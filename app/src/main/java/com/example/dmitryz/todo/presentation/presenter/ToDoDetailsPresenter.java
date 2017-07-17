package com.example.dmitryz.todo.presentation.presenter;


import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.exception.DefaultErrorBundle;
import com.example.dmitryz.todo.domain.exception.ErrorBundle;
import com.example.dmitryz.todo.domain.interactor.DefaultObserver;
import com.example.dmitryz.todo.domain.interactor.GetToDoDetails;
import com.example.dmitryz.todo.presentation.mapper.ToDoModelDataMapper;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.view.ToDoDetailsView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by q on 09.06.17.
 */

public class ToDoDetailsPresenter implements Presenter {
    private ToDoDetailsView todoDetailsView;

    private final GetToDoDetails getToDoDetailsUseCase;
    private final ToDoModelDataMapper todoModelDataMapper;
    private long todoId;

    @Inject
    public ToDoDetailsPresenter(GetToDoDetails getToDoDetailsUseCase,
                                ToDoModelDataMapper todoModelDataMapper) {
        this.getToDoDetailsUseCase = getToDoDetailsUseCase;
        this.todoModelDataMapper = todoModelDataMapper;
    }

    public void setView(@NonNull ToDoDetailsView todoDetailsView) {
        this.todoDetailsView = todoDetailsView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getToDoDetailsUseCase.dispose();
        todoDetailsView = null;
    }

    public void initialize(long todoId) {
        loadToDoDetails(todoId);
    }

    private void loadToDoDetails(long todoId) {
        hideViewRetry();
        showViewLoading();
        this.todoId = todoId;
        getToDoDetails(todoId);
    }

    private void getToDoDetails(long todoId) {
        getToDoDetailsUseCase.execute(new ToDoDetailsObserver(), GetToDoDetails.Params.forToDoId(todoId));
    }

    private void showViewLoading() {
        todoDetailsView.showLoading();
    }

    private void hideViewLoading() {
        todoDetailsView.hideLoading();
    }

    private void showViewRetry() {
        todoDetailsView.showRetry();
    }

    private void hideViewRetry() {
        todoDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(todoDetailsView.context(),
                errorBundle.getException());
        todoDetailsView.showError(errorMessage);
    }

    private void showToDoDetailsInView(ToDoItem todoItem) {
        final ToDoModel todoModel = todoModelDataMapper.transform(todoItem);
        todoDetailsView.renderToDo(todoModel);
    }

    public void retry() {
        if(todoId < 0) {
            return;
        }
        loadToDoDetails(todoId);
    }

    private class ToDoDetailsObserver extends DefaultObserver<ToDoItem> {
        @Override
        public void onComplete() {
            ToDoDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ToDoDetailsPresenter.this.hideViewLoading();
            ToDoDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ToDoDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ToDoItem todoItem) {
            ToDoDetailsPresenter.this.showToDoDetailsInView(todoItem);
        }
    }
}
