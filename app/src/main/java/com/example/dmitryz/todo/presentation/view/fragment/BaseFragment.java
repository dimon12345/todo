package com.example.dmitryz.todo.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.example.dmitryz.todo.presentation.internal.di.HasComponent;
import com.example.dmitryz.todo.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by q on 05.06.17.
 */

public class BaseFragment extends Fragment {

    @Inject
    Navigator navigator;

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
