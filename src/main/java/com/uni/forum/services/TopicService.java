package com.uni.forum.services;

import com.uni.forum.domain.coverters.TopicConverter;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.exceptions.NonExistingEntityException;
import com.uni.forum.repositories.TopicPagingRepository;
import com.uni.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    protected static final Logger logger = LogManager.getLogger();

    private final TopicRepository topicRepository;
    private final TopicConverter topicConverter;
    private final TopicPagingRepository topicPagingRepository;

    /**
     * @param topic
     * @return
     */
    public TopicDto persist(TopicDto topic) {
        logger.trace("Entered method TopicService.persist()");

        TopicEntity entity = topicConverter.toEntity(topic);
        TopicEntity savedEntity = topicRepository.save(entity);
        return topicConverter.toDto(savedEntity);
    }

    /**
     * @param id
     * @return
     */
    public TopicDto getTopic(long id) {
        logger.trace("Entered method TopicService.getTopic()");

        // TODO: don't use join, just query topic
        Optional<TopicEntity> topic = topicRepository.findById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic not found: " + id);
        }
        TopicEntity topicEntity = topic.get();
        return topicConverter.toDto(topicEntity);
    }

    /**
     * @param page
     * @param pageSize
     * @return
     */
    public List<TopicDto> getAllTopics(int page, int pageSize) {
        logger.trace("Entered method TopicService.getAllTopics()");

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("created").descending());
        Page<TopicEntity> allTopics = topicPagingRepository.findAll(pageRequest);
        return allTopics.getContent().stream().map(topicConverter::toDto).collect(Collectors.toList());
    }

    public TopicDto updateTopic(Long id, TopicDto newTopicDto) {
        Optional<TopicEntity> foundTopicEntity = repository.findById(id);

        if (foundTopicEntity.isEmpty()) {
            throw new NonExistingEntityException();
        }
        TopicEntity topicEntity = foundTopicEntity.get();
        TopicEntity newTopic = converter.toEntity(newTopicDto);

        if ( newTopic.getUsername() == null && newTopic.getTitle() == null) {
            throw new IllegalArgumentException(); // At least one of the optional args is present
        }
        // Keep original topic marking fields
        newTopic.setId(topicEntity.getId());
        newTopic.setCreated(topicEntity.getCreated());
        if (newTopic.getUsername() == null) {
            newTopic.setUsername(topicEntity.getUsername());
        }
        if (newTopic.getTitle() == null) {
            newTopic.setTitle(topicEntity.getTitle());
        }

        return converter.toDto(repository.save(newTopic));
    }
}
