package com.example.endsem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<Task> taskList;

    // Constructor
    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each list item
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_item, null);
        }

        // Get the task for this position
        Task task = taskList.get(position);

        // Find the views for the task name, description, and deadline
        TextView taskNameTextView = convertView.findViewById(R.id.taskName);
        TextView taskDescriptionTextView = convertView.findViewById(R.id.taskDescription);
        TextView taskDeadlineTextView = convertView.findViewById(R.id.taskDeadline);

        // Set the text for the views
        taskNameTextView.setText(task.getTaskName());
        taskDescriptionTextView.setText(task.getDescription());
        taskDeadlineTextView.setText(task.getDeadline());

        return convertView;
    }
}
