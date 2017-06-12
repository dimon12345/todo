package com.example.dmitryz.todo.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.HasComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.DaggerToDoComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoListFragment;

/**
 * Created by q on 05.06.17.
 */

public class ToDoListActivity extends BaseActivity implements HasComponent<ToDoComponent>,
        ToDoListFragment.ToDoListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ToDoListActivity.class);
    }

    private ToDoComponent todoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new ToDoListFragment());
        }
    }

    private void initializeInjector() {
        this.todoComponent = DaggerToDoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public ToDoComponent getComponent() {
        return todoComponent;
    }

    @Override
    public void onToDoClicked(ToDoModel todoModel) {
        this.navigator.navigateToToDoDetails(this, todoModel.getId());
    }

}
