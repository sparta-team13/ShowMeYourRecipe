package com.smyr.showmeyourrecipe.entity.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class PostLike {

    @EmbeddedId
    private PostLikeId id;

    public PostLike(PostLikeId id) {
        this.id = id;
    }
}