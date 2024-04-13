package com.uni.forum.domain.coverters;

import com.uni.forum.domain.dtos.ReplyDto;
import com.uni.forum.domain.dtos.TopicDto;
import com.uni.forum.domain.entities.ReplyEntity;
import com.uni.forum.domain.entities.TopicEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReplyConverter {

    ReplyEntity toEntity(ReplyDto dto);
    ReplyDto toDto(ReplyEntity dto);

}
