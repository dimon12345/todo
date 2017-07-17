package com.example.dmitryz.todo.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitryz.todo.R;
import com.example.dmitryz.todo.presentation.model.ToDoModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by q on 09.06.17.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    public interface OnItemClickListener {
        void onToDoItemClicked(ToDoModel todoModel);
    }

    private List<ToDoModel> todoItemsList;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    ToDoAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        todoItemsList = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (todoItemsList != null) ? todoItemsList.size() : 0;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_todo, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, final int position) {
        final ToDoModel todoModel = this.todoItemsList.get(position);
        holder.textViewTitle.setText(todoModel.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ToDoAdapter.this.onItemClickListener != null) {
                    ToDoAdapter.this.onItemClickListener.onToDoItemClicked(todoModel);
                }
            }
        });
    }

    private ToDoModel getItem(int position) {
        return todoItemsList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void setTodoItemsList(List<ToDoModel> todoItemsCollection) {
        validateToDoList(todoItemsCollection);
        this.todoItemsList = todoItemsCollection;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateToDoList(Collection<ToDoModel> todoCollection) {
        if (todoCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView textViewTitle;

        ToDoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
