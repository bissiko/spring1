package com.javarush.controller;

import com.javarush.domain.Status;

public class TaskInfo {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;
    public TaskInfo(String description, Status status) {}
}
