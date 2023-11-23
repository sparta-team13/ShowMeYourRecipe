package com.smyr.showmeyourrecipe.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data
@Getter
@Entity
@NoArgsConstructor
public class CommentLike{

    @EmbeddedId
    private CommentLikeKey id;

    public CommentLike(CommentLikeKey id) {
        this.id = id;
    }
}