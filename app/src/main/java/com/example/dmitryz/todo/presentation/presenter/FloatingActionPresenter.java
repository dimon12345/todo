package com.example.dmitryz.todo.presentation.presenter;

import com.example.dmitryz.todo.domain.ToDoItem;
import com.example.dmitryz.todo.domain.interactor.ResetToDoList;
import com.example.dmitryz.todo.presentation.mapper.ToDoModelDataMapper;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.view.ToDoListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by dmitryz on 7/8/17.
 */

public class FloatingActionPresenter implements Presenter {
    private final ResetToDoList resetToDoListUseCase;
    private final ToDoModelDataMapper todoModelDataMapper;
    private ToDoListView toDoListView;

    @Inject
    FloatingActionPresenter(ResetToDoList resetToDoListUseCase,
        ToDoModelDataMapper todoModelDataMapper) {

        this.resetToDoListUseCase = resetToDoListUseCase;
        this.todoModelDataMapper = todoModelDataMapper;
    }

    public void bindView(ToDoListView toDoListView) {
        this.toDoListView = toDoListView;
    }

    public void unbindView() {
        toDoListView = null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        resetToDoListUseCase.dispose();
        unbindView();
    }

    public void reset() {
        resetToDoListUseCase.execute(new FloatingActionObserver(), null);

    }

    private class FloatingActionObserver extends io.reactivex.observers.DisposableObserver<List<ToDoItem>> {
        @Override
        public void onNext(List<ToDoItem> items) {
            if (toDoListView != null) {
                final List<ToDoModel> todoModelsCollection =
                        todoModelDataMapper.transform(items);
                toDoListView.renderToDoList(todoModelsCollection);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
        }
    }
}
