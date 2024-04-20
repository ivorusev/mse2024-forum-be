package com.uni.forum.repositories;

import com.uni.forum.domain.entities.TopicEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicPagingRepository extends PagingAndSortingRepository<TopicEntity, Long> {
}
