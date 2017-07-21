package com.example.dmitryz.todo.presentation.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.internal.di.HasComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.DaggerToDoComponent;
import com.example.dmitryz.todo.presentation.internal.di.components.ToDoComponent;
import com.example.dmitryz.todo.presentation.model.ToDoModel;
import com.example.dmitryz.todo.presentation.view.adapter.TopPagerAdapter;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoListFragment;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoSetsFragment;

import java.util.List;
import java.util.Vector;

public class MainActivity  extends BaseActivity implements HasComponent<ToDoComponent>,
        ToDoListFragment.ToDoListListener {

    private ToDoComponent todoComponent;
    private TopPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.viewpager_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
        }

        initialisePaging();
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

    private void initialisePaging() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, ToDoSetsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, ToDoListFragment.class.getName()));
        this.pagerAdapter = new TopPagerAdapter(super.getFragmentManager(), fragments);

        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.pagerAdapter);
    }
}
