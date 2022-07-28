package com.tackedev.springboottemplate.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private UUID id;
    private String email;
    private String fullName;
    private Role role;
}
