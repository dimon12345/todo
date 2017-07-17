package com.example.dmitryz.todo.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.presenter.ToDoDetailsPresenter;
import com.example.dmitryz.todo.presentation.view.ToDoDetailsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by q on 08.06.17.
 */

public class ToDoDetailsFragment extends BaseFragment implements ToDoDetailsView {

    private static final String PARAM_TODO_ID = "param_todo_id";

    @Inject
    ToDoDetailsPresenter todoDetailsPresenter;
    Unbinder unbinder;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.bt_retry)
    Button bt_retry;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_body)
    TextView tv_body;

    public static ToDoDetailsFragment forToDo(long todoId) {
        final ToDoDetailsFragment todoDetailsFragment = new ToDoDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putLong(PARAM_TODO_ID, todoId);
        todoDetailsFragment.setArguments(arguments);
        return todoDetailsFragment;
    }

    public ToDoDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ToDoComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_todo_details, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            loadToDoDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        todoDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        todoDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        todoDetailsPresenter.destroy();
    }

    @Override
    public void renderToDo(ToDoModel todoModel) {
        if (todoModel != null) {
            tv_title.setText(todoModel.getTitle());
            tv_body.setText(todoModel.getBody());
        }
    }
    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void loadToDoDetails() {
        if (this.todoDetailsPresenter != null) {
            todoDetailsPresenter.initialize(currentToDoId());
        }
    }

    private long currentToDoId() {
        final Bundle arguments = getArguments();
        return arguments.getLong(PARAM_TODO_ID);
    }

    @OnClick(R.id.bt_retry)
    void reloadDetails() {
        todoDetailsPresenter.retry();
    }
}
