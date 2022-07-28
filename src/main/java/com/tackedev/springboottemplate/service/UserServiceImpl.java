package com.tackedev.springboottemplate.service;

import com.tackedev.springboottemplate.model.domain.User;
import com.tackedev.springboottemplate.model.domain.UserMapper;
import com.tackedev.springboottemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> get(UUID id) {
        return userRepository.findById(id).map(userMapper::toDomain);
    }

}
