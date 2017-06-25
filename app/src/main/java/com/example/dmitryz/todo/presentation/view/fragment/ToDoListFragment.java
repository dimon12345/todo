package com.example.dmitryz.todo.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.presenter.ToDoListPresenter;
import com.example.dmitryz.todo.presentation.view.ToDoListView;
import com.example.dmitryz.todo.presentation.view.adapter.ToDoAdapter;
import com.example.dmitryz.todo.presentation.view.adapter.ToDoLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by q on 05.06.17.
 */

public class ToDoListFragment extends BaseFragment implements ToDoListView {

    public interface ToDoListListener {
        void onToDoClicked(final ToDoModel todoModel);
    }

    @Inject
    ToDoListPresenter todoListPresenter;

    private Unbinder unbinder;

    @Inject
    ToDoAdapter todoAdapter;

    @BindView(R.id.rv_todo)
    RecyclerView rv_todo;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.bt_retry)
    Button bt_retry;

    ToDoListListener todoListListener;

    public ToDoListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ToDoListListener) {
            todoListListener = (ToDoListListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(ToDoComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle saveInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoListPresenter.setView(this);
        if (savedInstanceState == null) {
            loadToDoList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        todoListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        todoListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_todo.setAdapter(null);
        unbinder.unbind();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        todoListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        todoListListener = null;
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
    public void renderToDoList(Collection<ToDoModel> todoModelCollection) {
        if (todoModelCollection != null) {
            todoAdapter.setTodoItemsCollection(todoModelCollection);
        }
    }

    @Override
    public void viewToDo(ToDoModel todoModel) {
        if (todoListListener != null) {
            todoListListener.onToDoClicked(todoModel);
        }
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        todoAdapter.setOnItemClickListener(onItemClickListener);
        rv_todo.setLayoutManager(new ToDoLayoutManager(context()));
        rv_todo.setAdapter(todoAdapter);
    }

    private void loadToDoList() {
        todoListPresenter.initialize();
    }

    private ToDoAdapter.OnItemClickListener onItemClickListener =
            new ToDoAdapter.OnItemClickListener() {
                @Override
                public void onToDoItemClicked(ToDoModel todoModel) {
                    if (ToDoListFragment.this.todoListPresenter != null && todoModel != null) {
                        ToDoListFragment.this.todoListPresenter.onToDoClicked(todoModel);
                    }
                }
            };

    @OnClick(R.id.bt_retry)
    void reloadToDoData() {
        todoListPresenter.retry();
    }

    @OnClick(R.id.fab)
    void doSomentingPromotional() {
        navigator.addNewItem(context());
    }
}
