package com.example.dmitryz.todo.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by dmitryz on 5/31/17.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {
        // no op
    }

    @Override
    public void onComplete() {
        // no op
    }

    @Override
    public void onError(Throwable exception) {
        // no op
    }
}
