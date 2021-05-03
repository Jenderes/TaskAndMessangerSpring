package com.example.task_manager.model;

public enum Status {

    ACTIVE("active"),
    DISABLE("disable"),
    EXPIRED("expired"),
    COMPLETE("complete");

    private final String statusName;

    Status(String complete) {
        statusName = complete;
    }

    public Status getStatus(){
        return Status.valueOf(statusName);
    }
}
