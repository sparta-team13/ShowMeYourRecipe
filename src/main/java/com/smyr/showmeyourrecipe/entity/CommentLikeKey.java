package com.smyr.showmeyourrecipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_id")
    private Long commentId;

    @Builder
    public CommentLikeKey(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
