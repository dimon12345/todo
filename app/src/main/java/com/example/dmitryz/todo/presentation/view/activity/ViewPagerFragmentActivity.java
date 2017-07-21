package com.example.dmitryz.todo.presentation.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.view.adapter.TopPagerAdapter;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoListFragment;
import com.example.dmitryz.todo.presentation.view.fragment.ToDoSetsFragment;

import java.util.List;
import java.util.Vector;

/**
 * Created by q on 7/20/17.
 */

public class ViewPagerFragmentActivity extends BaseActivity {
    private TopPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager_layout);
        //initialsie the pager
        this.initialisePaging();
    }

    private void initialisePaging() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, ToDoSetsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, ToDoListFragment.class.getName()));
        this.mPagerAdapter  = new TopPagerAdapter(super.getFragmentManager(), fragments);

        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }
}
