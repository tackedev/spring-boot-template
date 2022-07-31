package com.tackedev.springboottemplate.controller;

import com.tackedev.springboottemplate.common.Endpoint;
import com.tackedev.springboottemplate.model.domain.User;
import com.tackedev.springboottemplate.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(Endpoint.User.ROOT)
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(Endpoint.User.CURRENT_USER)
    public User getMe(Principal principal) {
        UUID id = UUID.fromString(principal.getName());
        return userService.get(id).orElseThrow(ResourceNotFoundException::new);
    }

}
