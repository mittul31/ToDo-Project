package com.example.endsem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        // Find views in the layout
        EditText taskNameInput = findViewById(R.id.taskName);
        EditText descriptionInput = findViewById(R.id.taskDescription);
        EditText deadlineInput = findViewById(R.id.deadlineDate);

        Button submitButton = findViewById(R.id.submitTask);
        submitButton.setOnClickListener(v -> {
            String taskName = taskNameInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String deadline = deadlineInput.getText().toString();

            // Validate inputs
            if (taskName.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(this, "Task Name and Deadline are required.", Toast.LENGTH_SHORT).show();
            } else {
                // Create a Task object (No need to use cursor here)
                Task newTask = new Task(taskName, description, deadline);

                // Save the task to Firestore
                db.collection("tasks")  // Replace "tasks" with your Firestore collection name
                        .add(newTask)  // Adds the task to Firestore
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(this, "Task added successfully!", Toast.LENGTH_SHORT).show();
                            finish();  // Close the activity
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Failed to add task.", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
