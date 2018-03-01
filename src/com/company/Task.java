package com.company;


public class Task {
    private String title;
    private String dueDate;
    private String details;
    private boolean complete;
    private String completeDate;

    public Task(String title, String dueDate, String details, boolean complete, String completeDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.details = details;
        this.complete = complete;
        this.completeDate = completeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }
}
