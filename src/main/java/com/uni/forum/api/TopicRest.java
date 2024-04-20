package com.uni.forum.api;

import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.exceptions.ExistingEntityException;
import com.uni.forum.services.TopicService;
import jakarta.transaction.NotSupportedException;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicRest {
  // TODO: Yordan
  private final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);

  private final TopicService topicService;
  @PostMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topic) {
    TopicDto persist = topicService.persist(topic);
    return ResponseEntity.ok(persist);
  }

  // TODO: implement
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getTopic(@PathVariable("id") Long id) {
    topicService.getTopic(id);
    throw new UnsupportedOperationException();
  }

  // TODO: implement
  // TODO: implement pagination
  @GetMapping
  public String getAllTopics() {
    throw new UnsupportedOperationException();
  }

  // TODO: implement update topic

}
