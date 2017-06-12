package com.example.dmitryz.todo.presentation.internal.di.components;

import com.example.dmitryz.todo.presentation.internal.di.PerActivity;
import com.example.dmitryz.todo.presentation.internal.di.modules.ActivityModule;
import com.example.dmitryz.todo.presentation.internal.di.modules.ToDoModule;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoDetailsFragment;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoListFragment;

import dagger.Component;

/**
 * Created by q on 08.06.17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ToDoModule.class})
public interface ToDoComponent {
    void inject(ToDoListFragment todoListFragment);
    void inject(ToDoDetailsFragment todoDetailsFragment);
}
