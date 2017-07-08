package com.example.dmitryz.todo.presentation.presenter;

import com.example.dmitryz.todo.domain.interactor.ResetToDoList;

import javax.inject.Inject;

/**
 * Created by dmitryz on 7/8/17.
 */

public class FloatingActionPresenter implements Presenter {
    private final ResetToDoList resetToDoListUseCase;

    @Inject
    FloatingActionPresenter(ResetToDoList resetToDoListUseCase) {
        this.resetToDoListUseCase = resetToDoListUseCase;
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
    }

    public void reset() {
        resetToDoListUseCase.execute(new FloatingActionObserver(), null);

    }

    private class FloatingActionObserver extends io.reactivex.observers.DisposableObserver<Void> {
        @Override
        public void onNext(Void aVoid) {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
