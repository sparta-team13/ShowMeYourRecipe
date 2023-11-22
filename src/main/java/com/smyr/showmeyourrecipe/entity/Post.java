package com.smyr.showmeyourrecipe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Post {
    @Id
    private Long postid;

}
