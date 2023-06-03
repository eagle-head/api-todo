package com.kohn.apitodo.enums;

public enum TaskStatus {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    PENDING("PENDING"),
    ARCHIVED("ARCHIVED"),
    DELETED("DELETED");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
