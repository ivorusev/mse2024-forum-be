package com.uni.forum.services;

import com.uni.forum.domain.coverters.UserConverter;
import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.domain.entities.UserEntity;
import com.uni.forum.exceptions.ExistingEntityException;
import com.uni.forum.repositories.UserPagingRepository;
import com.uni.forum.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    protected static final Logger logger = LogManager.getLogger();

    private final UserRepository userRepository;
    private final UserPagingRepository pagingRepository;
    private final UserConverter converter;

    /***
     *
     * @param user
     * @return
     */
    public UserDto persist(UserDto user) {
        logger.trace("Entered method UserService.persist()");

        Optional<UserEntity> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            throw new ExistingEntityException();
        }

        UserEntity entity = converter.toEntity(user);
        UserEntity savedUser = userRepository.save(entity);
        return converter.toDto(savedUser);
    }

    /**
     *
     * @param username
     * @return
     */
    public UserDto findByUsername(String username) {
        logger.trace("Entered method TopicService.findByEmail()");

        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            // TODO: log this
            logger.error("");
            return null;
        }
        return converter.toDto(user.get());
    }

    /**
     *
     * @param username
     * @param user
     * @return
     */
    public UserDto updateUser(String username, UserDto user) {
        logger.trace("Entered method TopicService.updateUser()");

        Optional<UserEntity> byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername.isEmpty()) {
            // TODO: throw an error
        }
        UserEntity userEntity = byUsername.get();
        UserEntity updatedEntity = converter.toEntity(user);
        updatedEntity.setId(userEntity.getId());
        updatedEntity.setCreated(userEntity.getCreated());
        UserEntity save = userRepository.save(updatedEntity);
        return converter.toDto(save);
    }

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    public List<UserDto> getAllUsers(int page, int pageSize) {
        logger.trace("Entered method TopicService.getAllUsers()");

        // if (int )
        Page<UserEntity> all = pagingRepository.findAll(PageRequest.of(page, pageSize));
        return all.getContent().stream().map(converter::toDto).collect(Collectors.toList());
    }
}
