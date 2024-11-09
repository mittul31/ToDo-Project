// AddTaskActivity.java
package com.example.t;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dbHelper = new SQLiteHelper(this);
        EditText taskNameInput = findViewById(R.id.taskNameInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        EditText deadlineInput = findViewById(R.id.deadlineInput);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            String taskName = taskNameInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String deadline = deadlineInput.getText().toString();

            if (taskName.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(this, "Task Name and Deadline are required.", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addTask(taskName, description, deadline);
                Toast.makeText(this, "Task added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
