package com.uni.forum.services;

import com.uni.forum.domain.coverters.TopicConverter;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
