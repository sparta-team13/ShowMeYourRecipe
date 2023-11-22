package com.smyr.showmeyourrecipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode // 필수
public class CommentLikeKey implements Serializable {
    private User user;
    private Comment comment;
}
