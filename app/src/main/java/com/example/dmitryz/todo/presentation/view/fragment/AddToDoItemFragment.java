package com.example.dmitryz.todo.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.presenter.AddToDoItemPresenter;
import com.example.dmitryz.todo.presentation.view.AddToDoItemView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dmitryz on 6/25/17.
 */

public class AddToDoItemFragment  extends BaseFragment implements AddToDoItemView {

    @BindView(R.id.title)
    EditText titleTextView;

    @BindView(R.id.body)
    EditText bodyTextView;

    @Inject
    AddToDoItemPresenter addToDoPresenter;
    private Unbinder unbinder;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ToDoComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_add_item, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addToDoPresenter.setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.add_button)
    public void onAddButtonClick() {
        addToDoPresenter.addToDoItem(titleTextView.getText().toString(), bodyTextView.getText().toString());
    }

    @Override
    public void done() {
        getActivity().finish();
    }
}
