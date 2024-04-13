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
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String name;
    private String role;

    @Column
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date created;

    @Column
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date modified;
}
