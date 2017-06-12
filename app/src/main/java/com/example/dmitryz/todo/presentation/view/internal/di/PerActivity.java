package com.example.dmitryz.todo.presentation.internal.di;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by q on 08.06.17.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
