package com.example.endsem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    private SQLiteHelper dbHelper;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new SQLiteHelper(this);
        ListView taskListView = findViewById(R.id.taskListView);
        taskAdapter = new TaskAdapter(this, dbHelper.getAllTasks());
        taskListView.setAdapter(taskAdapter);

        FloatingActionButton fab = findViewById(R.id.addTaskButton);
        fab.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AddTaskActivity.class)));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.history) {
            startActivity(new Intent(HomeActivity.this, HistoryTaskActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
