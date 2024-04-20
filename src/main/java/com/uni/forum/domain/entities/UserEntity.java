package com.uni.forum.domain.entities;

import com.uni.forum.domain.enums.UserRole;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

}
