package com.smyr.showmeyourrecipe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Data
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CommentLikeKey.class)
@Table(name = "commentLike")
public class CommentLike{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentid")
    private Comment comment;
}
