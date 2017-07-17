package com.example.dmitryz.todo.presentation.presenter;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.exception.DefaultErrorBundle;
import com.example.dmitryz.todo.domain.exception.ErrorBundle;
import com.example.dmitryz.todo.domain.interactor.DefaultObserver;
import com.example.dmitryz.todo.domain.interactor.DeleteToDoListItem;
import com.example.dmitryz.todo.domain.interactor.GetToDoList;
import com.example.dmitryz.todo.presentation.mapper.ToDoModelDataMapper;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.view.ToDoListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by q on 08.06.17.
 */

public class ToDoListPresenter implements Presenter {

    private ToDoListView todoListView;

    private final GetToDoList getToDoListUseCase;
    private final DeleteToDoListItem deleteToDoListItemUseCase;
    private final ToDoModelDataMapper todoModelDataMapper;
    private Collection<ToDoItem> cachedList;

    @Inject
    ToDoListPresenter(GetToDoList getToDoListUseCase,
                      DeleteToDoListItem deleteToDoListItemUseCase,
                      ToDoModelDataMapper todoModelDataMapper) {
        this.getToDoListUseCase = getToDoListUseCase;
        this.deleteToDoListItemUseCase = deleteToDoListItemUseCase;
        this.todoModelDataMapper = todoModelDataMapper;
    }

    public void setView(@NonNull ToDoListView view) {
        this.todoListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getToDoListUseCase.dispose();
        todoListView = null;
    }

    public void initialize() {
        loadToDoList();
    }

    private void loadToDoList() {
        hideViewRetry();
        showViewLoading();
        getToDoList();
    }

    public void onToDoClicked(ToDoModel todoModel) {
        todoListView.viewToDo(todoModel);
    }

    public void retry() {
        loadToDoList();
    }

    private void showViewLoading() {
        todoListView.showLoading();
    }

    private void hideViewLoading() {
        todoListView.hideLoading();
    }

    private void showViewRetry() {
        todoListView.showRetry();
    }

    private void hideViewRetry() {
        todoListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(todoListView.context(),
                errorBundle.getException());
        todoListView.showError(errorMessage);
    }

    private void showToDoCollectionInView(Collection<ToDoItem> todoItemsCollection) {
        final Collection<ToDoModel> todoModelsCollection =
                todoModelDataMapper.transform(todoItemsCollection);
        todoListView.renderToDoList(todoModelsCollection);
        cachedList = todoItemsCollection;
    }

    private void getToDoList() {
        getToDoListUseCase.execute(new ToDoListObserver(), null);
    }

    public void removeItem(long id) {
        deleteToDoListItemUseCase.execute(this, new DeleteToDoListItem.Params(id));
    }

    private final class ToDoListObserver extends DefaultObserver<List<ToDoItem>> {

        @Override
        public void onComplete() {
            ToDoListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ToDoListPresenter.this.hideViewLoading();
            ToDoListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ToDoListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ToDoItem> todoItems) {
            ToDoListPresenter.this.showToDoCollectionInView(todoItems);
        }
    }

}
