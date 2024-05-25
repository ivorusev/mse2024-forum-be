package com.uni.forum.repositories;

import com.uni.forum.domain.entities.TopicEntity;
import com.uni.forum.domain.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: add paging support (PagingAndSortingRepository)
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    Optional<TopicEntity> findByTitle(String title);

}
