package com.uni.forum.api;

import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.services.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// TODO: exception handling to not show backend errors
// TODO: Add a logger (log4j?)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRest {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.persist(user));
    }

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByEmail(username));
    }



}
