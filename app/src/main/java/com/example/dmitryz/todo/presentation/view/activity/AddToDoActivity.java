package com.example.dmitryz.todo.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.HasComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.DaggerToDoComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.view.fragment.AddToDoItemFragment;

/**
 * Created by dmitryz on 6/25/17.
 */

public class AddToDoActivity extends BaseActivity implements HasComponent<ToDoComponent> {
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AddToDoActivity.class);
    }

    private ToDoComponent todoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);
        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new AddToDoItemFragment());
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
//            outState.putString(INSTANCE_STATE_PARAM_TODO_ID, todoId);
        }
        super.onSaveInstanceState(outState);
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
