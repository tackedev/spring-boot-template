package com.tackedev.springboottemplate.model.domain;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN(1),
    USER(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static final Map<Integer, Role> cache = new HashMap<>();

    static {
        for (Role role : Role.values()) {
            cache.put(role.value, role);
        }
    }

    public static Role fromValue(int value) {
        return cache.get(value);
    }


}
