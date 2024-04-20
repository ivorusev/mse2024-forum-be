package com.uni.forum.api;

import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.exceptions.ExistingEntityException;
import com.uni.forum.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: exception handling to not show backend errors
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRest {

  private final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);

  private final UserService userService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
    LOGGER.info("test {}", user.getEmail());
    UserDto persist;
    try {
      persist = userService.persist(user);
    } catch (ExistingEntityException e) {
      LOGGER.warn("User with username {} already exists", user.getUsername());
      LOGGER.warn("Root cause: ", e);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(persist);
  }

  @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUser(@PathVariable String username) {
    return ResponseEntity.ok(userService.findByUsername(username));
  }

  @PutMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> updateUser(
      @PathVariable String username, @RequestBody UserDto user) {
    return ResponseEntity.ok(userService.updateUser(username, user));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserDto>> getAllUsers(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
    // TODO: add check for NULL page and pageSize
    return ResponseEntity.ok(userService.getAllUsers(page, pageSize));
  }

}
