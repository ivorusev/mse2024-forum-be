package com.uni.forum.services;

import com.uni.forum.domain.coverters.ReplyConverter;
import com.uni.forum.domain.dtos.ReplyDto;
import com.uni.forum.domain.entities.ReplyEntity;
import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.exceptions.NonExistingEntityException;
import com.uni.forum.repositories.ReplyPagingRepository;
import com.uni.forum.repositories.ReplyRepository;
import com.uni.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    // TODO: Stanislav will review and fix (or break everything)
    protected static final Logger logger = LogManager.getLogger();

    private final ReplyRepository replyRepository;
    private final ReplyPagingRepository replyPagingRepository;
    private final TopicRepository topicRepository;
    private final ReplyConverter replyConverter;

    /**
     * Saves a new reply to the database
     *
     * @param reply data to be saved
     * @return the saved reply
     */
    public ReplyDto persist(ReplyDto reply) {
        logger.trace("Entered method ReplyService.persist()");

        TopicEntity topicEntity = getTopicOrThrowException(reply.getTopicId());
        ReplyEntity entity = replyConverter.toEntity(reply);
        entity.setTopic(topicEntity);
        ReplyEntity savedEntity = replyRepository.save(entity);
        ReplyDto savedReply = replyConverter.toDto(savedEntity);

        logger.info("Added new Reply: " + savedReply.hashCode());
        return savedReply;
    }

    /**
     * This
     * @param id - topic
     * @param page which page to be returned (starting from 0)
     * @param pageSize number of items in the page
     * @return
     */
    public List<ReplyDto> getAllRepliesByTopic(Long id, Integer page, Integer pageSize) {
        logger.trace("Entered method ReplyService.getAllRepliesByTopic()");

        TopicEntity topic = getTopicOrThrowException(id);
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("created").descending());
        List<ReplyEntity> allRepliesByTopic = replyPagingRepository.findAllByTopic(topic, pageRequest);
        List<ReplyDto> replies = allRepliesByTopic.stream().map(replyConverter::toDto).collect(Collectors.toList());

        logger.info("Queried all replies for topic: " + topic.getTitle());
        return replies;
    }

    /**
     * @param id
     * @return
     */
    private TopicEntity getTopicOrThrowException(Long id) {
        logger.trace("Entered method ReplyService.getTopicOrThrowException()");

        Optional<TopicEntity> topic = topicRepository.findById(id);
        if (topic.isEmpty()) {
            throw new NonExistingEntityException();
        }
        return topic.get();
    }
}
