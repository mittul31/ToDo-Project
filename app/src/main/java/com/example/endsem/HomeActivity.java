package com.example.endsem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore
        RecyclerView taskRecyclerView = findViewById(R.id.taskRecyclerView);

        // Set up RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch tasks from Firestore
        fetchTasksFromFirestore(taskRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fabAddTask);
        fab.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AddTaskActivity.class)));
    }

    private void fetchTasksFromFirestore(RecyclerView taskRecyclerView) {
        db.collection("tasks")  // Replace "tasks" with your Firestore collection name
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Task> taskList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String taskName = document.getString("taskName");
                            String description = document.getString("description");
                            String deadline = document.getString("deadline");

                            // Create Task object and add to list
                            Task taskObj = new Task(taskName, description, deadline);
                            taskList.add(taskObj);
                        }
                        // Set up adapter with fetched data
                        taskAdapter = new TaskAdapter(HomeActivity.this, taskList);
                        taskRecyclerView.setAdapter(taskRecyclerView.getAdapter());
                    } else {
                        Toast.makeText(HomeActivity.this, "Error getting documents: " + task.getException(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnTaskHistory) {
            startActivity(new Intent(HomeActivity.this, HistoryTaskActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
