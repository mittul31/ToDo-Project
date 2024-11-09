package com.example.endsem;

public class Task {
    private int id;  // Not needed if Firestore auto-generates the ID
    private String taskName;
    private String description;
    private String deadline;
    private String status;

    // Constructor
    public Task(int anInt, String taskName, String description, String deadline) {
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }

    public Task(String taskName, String description, String deadline) {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


}
