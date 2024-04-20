package com.uni.forum.services;

import com.uni.forum.domain.coverters.UserConverter;
import com.uni.forum.domain.dtos.UserDto;
import com.uni.forum.domain.entities.UserEntity;
import com.uni.forum.domain.enums.UserRole;
import com.uni.forum.exceptions.ExistingEntityException;
import com.uni.forum.repositories.UserPagingRepository;
import com.uni.forum.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserPagingRepository pagingRepository;
  private final UserConverter converter;

  public UserDto persist(UserDto user) {
    Optional<UserEntity> byUsername = userRepository.findByUsername(user.getUsername());
    if (byUsername.isPresent()) {
      throw new ExistingEntityException();
    }

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

  public UserDto updateUser(String username, UserDto user) {
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

  public List<UserDto> getAllUsers(int page, int pageSize) {
    // if (int )
    Page<UserEntity> all = pagingRepository.findAll(PageRequest.of(page, pageSize));
    return all.getContent().stream().map(converter::toDto).collect(Collectors.toList());
  }
}
