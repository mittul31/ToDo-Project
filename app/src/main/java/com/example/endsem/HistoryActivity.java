// HistoryTaskActivity.java
package com.example.endsem;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryTaskActivity extends AppCompatActivity {
    private SQLiteHelper dbHelper;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);

        dbHelper = new SQLiteHelper(this);
        ListView historyListView = findViewById(R.id.historyListView);
        taskAdapter = new TaskAdapter(this, dbHelper.getCompletedTasks());
        historyListView.setAdapter(taskAdapter);
    }
}
