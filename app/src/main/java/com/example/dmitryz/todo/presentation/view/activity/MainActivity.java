package com.example.dmitryz.todo.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.dmitryz.todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_LoadData)
    Button btn_LoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_LoadData)
    void navigateToToDoList() {
        navigator.navigateToToDoList(this);
    }
}
