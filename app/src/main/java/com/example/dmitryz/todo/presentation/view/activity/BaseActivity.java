package com.example.dmitryz.todo.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.dmitryz.todo.presentation.AndroidApplication;
import com.example.dmitryz.todo.presentation.internal.di.components.ApplicationComponent;
import com.example.dmitryz.todo.presentation.internal.di.modules.ActivityModule;
import com.example.dmitryz.todo.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by q on 03.06.17.
 */

public class BaseActivity extends Activity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() { return new ActivityModule(this);}
}
