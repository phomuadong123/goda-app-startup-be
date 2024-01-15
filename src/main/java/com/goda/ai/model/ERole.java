package com.goda.ai.model;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN;

    public static boolean isValidRole(String role) {
        for (ERole validRole : values()) {
            if (validRole.name().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
