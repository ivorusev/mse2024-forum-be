package com.uni.forum.services;

import com.uni.forum.domain.coverters.TopicConverter;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.entities.ReplyEntity;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.repositories.ReplyRepository;
import com.uni.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository repository;
    private final TopicConverter converter;

    public TopicDto persist(TopicDto topic) {
        TopicEntity entity = converter.toEntity(topic);
        TopicEntity savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }

    public TopicDto getTopic(long id) {
        // TODO: don't use join, just query topic
        Optional<TopicEntity> topic = repository.findById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException(  "Topic not found: " + id);
        }
        TopicEntity topicEntity = topic.get();
        return converter.toDto(topicEntity);
    }
}
