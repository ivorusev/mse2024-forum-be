package com.uni.forum.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicRest {

    @GetMapping
    public String getAllTopics() {
        return "asd";
    }

}
