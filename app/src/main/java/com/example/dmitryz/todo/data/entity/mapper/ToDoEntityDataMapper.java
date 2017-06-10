package com.example.dmitryz.todo.data.entity.mapper;

import com.example.dmitryz.todo.data.entity.ToDoEntity;
import com.example.dmitryz.todo.domain.ToDoItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dmitryz on 6/2/17.
 */

public class ToDoEntityDataMapper {

    @Inject
    ToDoEntityDataMapper() {}

    public ToDoItem transform(ToDoEntity entity) {
        ToDoItem item = null;
        if (entity != null) {
            item = new ToDoItem(entity.getID());
            item.setTitle(entity.getTitle());
            item.setBody(entity.getBody());
        }
        return item;
    }

    public List<ToDoItem> transform(Collection<ToDoEntity> entitiesCollection) {
        final List<ToDoItem> items = new ArrayList<ToDoItem>();
        for (ToDoEntity entity : entitiesCollection) {
            final ToDoItem item = transform(entity);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }
}
