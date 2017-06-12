package com.example.dmitryz.todo.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.example.dmitryz.todo.presentation.internal.di.HasComponent;

/**
 * Created by q on 05.06.17.
 */

public class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
