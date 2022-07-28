package com.tackedev.springboottemplate.controller;

import com.tackedev.springboottemplate.model.domain.User;
import com.tackedev.springboottemplate.model.request.PostTimeRequest;
import com.tackedev.springboottemplate.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public User getMe(Principal principal) {
        UUID id = UUID.fromString(principal.getName());
        return userService.get(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/time")
    public ResponseEntity<PostTimeRequest> postTime(@RequestBody PostTimeRequest request) {
        return ResponseEntity.ok(request);
    }

}
