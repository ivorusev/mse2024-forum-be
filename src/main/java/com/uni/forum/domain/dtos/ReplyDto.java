package com.uni.forum.domain.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private String text;
    private String username;
    private Date dateCreated;
    private Date dateModified;
    private String topicTitle;

}
