package com.tackedev.springboottemplate.service;

import com.tackedev.springboottemplate.model.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> get(UUID id);

}
