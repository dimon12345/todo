package com.example.dmitryz.todo.presentation.view;

import android.content.Context;

/**
 * Created by q on 05.06.17.
 */

public interface LoadDataView {
    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String message);

    Context context();
}
