package com.uni.forum.services;

import com.uni.forum.domain.coverters.ReplyConverter;
import com.uni.forum.domain.dtos.ReplyDto;
import com.uni.forum.domain.entities.ReplyEntity;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.repositories.ReplyRepository;
import com.uni.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final TopicRepository topicRepository;
    private final ReplyConverter converter;

    public ReplyDto persist(ReplyDto dto) {
        Optional<TopicEntity> topic = topicRepository.findByTitle(dto.getTopicTitle());
        if (topic.isEmpty()){
            // TODO: ....
        }
        TopicEntity topicEntity = topic.get();
        ReplyEntity entity = converter.toEntity(dto);
        entity.setTopic(topicEntity);
        ReplyEntity savedEntity = replyRepository.save(entity);
        return converter.toDto(savedEntity);
    }
}
