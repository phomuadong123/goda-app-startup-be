package com.goda.ai.model;

public enum MediaType {
    IMAGE(1),
    VIDEO(2);

    private final int value;

    private MediaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
