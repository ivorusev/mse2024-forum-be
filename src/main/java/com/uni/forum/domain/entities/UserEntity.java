package com.uni.forum.domain.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

// TODO: create a base entity that contain date created, date modified, id
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
    private String role;

}
