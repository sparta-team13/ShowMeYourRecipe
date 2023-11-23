package com.smyr.showmeyourrecipe.entity;

import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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