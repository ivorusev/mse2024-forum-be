package com.uni.forum.api;

import com.uni.forum.domain.dtos.ReplyDto;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.services.ReplyService;
import com.uni.forum.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyRest {

  private final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);

  private final ReplyService replyService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ReplyDto> createUser(@RequestBody ReplyDto reply) {
    ReplyDto persist = replyService.persist(reply);
    return ResponseEntity.ok(persist);
  }
}
