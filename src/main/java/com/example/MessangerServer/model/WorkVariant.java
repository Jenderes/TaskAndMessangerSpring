package com.example.MessangerServer.model;

public enum WorkVariant {

    DESIGN("design"),
    DEVELOP("develop"),
    BUSINESS("business"),
    TESTING("testing");

    private final String work;

    WorkVariant(String work) {
        this.work = work;
    }

    public String getWork() {
        return work;
    }
}
