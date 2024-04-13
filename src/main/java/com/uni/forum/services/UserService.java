package com.uni.forum.services;

import com.uni.forum.domain.coverters.UserConverter;
import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.domain.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;

    public UserDto persist(UserDto user) {
        UserEntity entity = converter.toEntity(user);
        UserEntity savedUser = userRepository.save(entity);
        return converter.toDto(savedUser);
    }

    public UserDto findByEmail(String username) {
        Optional<UserEntity> byEmail = userRepository.findByUsername(username);
        if (byEmail.isEmpty()) {
            // TODO: log this
            return null;
        }
        return converter.toDto(byEmail.get());
    }
}
