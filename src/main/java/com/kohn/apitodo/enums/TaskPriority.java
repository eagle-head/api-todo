package com.kohn.apitodo.enums;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return this.priority;
    }
}

