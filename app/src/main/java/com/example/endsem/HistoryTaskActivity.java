package com.example.endsem;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HistoryTaskActivity extends AppCompatActivity {

    private SQLiteHelper dbHelper;
    private ListView taskListView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);

        // Initialize SQLiteHelper and ListView
        dbHelper = new SQLiteHelper(this);
        taskListView = findViewById(R.id.completedTasksRecyclerView);

        // Get all tasks from the database (no status filtering)
        List<Task> allTasks = dbHelper.getAllTasks();

        // Check if there are tasks to display
        if (allTasks.isEmpty()) {
            Toast.makeText(this, "No tasks found.", Toast.LENGTH_SHORT).show();
        } else {
            // Use an adapter to display tasks in the ListView
            taskAdapter = new TaskAdapter(this, allTasks);
            taskListView.setAdapter(taskAdapter);
        }
    }
}
