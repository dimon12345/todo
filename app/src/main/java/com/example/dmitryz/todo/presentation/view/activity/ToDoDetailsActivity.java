package com.example.dmitryz.todo.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.HasComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.DaggerToDoComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoDetailsFragment;

/**
 * Created by q on 10.06.17.
 */

public class ToDoDetailsActivity extends BaseActivity implements HasComponent<ToDoComponent> {
    private static final String INTENT_EXTRA_PARAM_TODO_ID = "com.example.dmitryz.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_TODO_ID = "com.example.dmitryz.INSTANCE_STATE_PARAM_USER_ID";

    public static Intent getCallingIntent(Context context, long todoId) {
        Intent callingIntent = new Intent(context, ToDoDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TODO_ID, todoId);
        return callingIntent;
    }

    private long todoId;
    private ToDoComponent todoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        initializeActivity(savedInstanceState);
        initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putLong(INSTANCE_STATE_PARAM_TODO_ID, todoId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            todoId = getIntent().getLongExtra(INTENT_EXTRA_PARAM_TODO_ID, -1);
            addFragment(R.id.fragmentContainer, ToDoDetailsFragment.forToDo(todoId));
        } else {
            todoId = savedInstanceState.getLong(INSTANCE_STATE_PARAM_TODO_ID);
        }
    }

    private void initializeInjector() {
        todoComponent = DaggerToDoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public ToDoComponent getComponent() {
        return todoComponent;
    }
}
