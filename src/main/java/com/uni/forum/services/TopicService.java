package com.uni.forum.services;

import com.uni.forum.domain.coverters.TopicConverter;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.entities.ReplyEntity;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.domain.entities.UserEntity;
import com.uni.forum.repositories.ReplyRepository;
import com.uni.forum.repositories.TopicPagingRepository;
import com.uni.forum.repositories.TopicRepository;
import com.uni.forum.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository repository;
    private final TopicPagingRepository pagingRepository;
    private final TopicConverter converter;
    private final UserRepository userRepository;

    public TopicDto persist(TopicDto topic) {
        UserEntity user = UserService.getUserOrThrowException(topic.getUsername(), userRepository);
        TopicEntity entity = converter.toEntity(topic);
        entity.setUser(user);
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

    public List<TopicDto> getAllTopicsByUsername(String username, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("created").descending());
        List<TopicEntity> allByUsername = pagingRepository.findByUsername(username, pageRequest);
        return allByUsername.stream().map(converter::toDto).collect(Collectors.toList());
    }
}
