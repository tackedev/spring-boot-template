package com.tackedev.springboottemplate.common;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class Endpoint {

    public static final String PREFIX = "/api";
    public static final String VERSION = "/v1";

    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static final class User {
        public static final String ROOT = PREFIX + VERSION + "/users";
        public static final String CURRENT_USER = "/me";
    }
}
