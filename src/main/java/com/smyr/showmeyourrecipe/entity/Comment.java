package com.smyr.showmeyourrecipe.entity;

import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "comment")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private Long parentcommentid;

    @Column(nullable = true)
    private Long depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postid", nullable = false)
    private Post post;

    @Column(nullable = true)
    private Boolean commentLike;


    public Comment(CommentRequestDto requestDto, Post post) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        //this.like = like;
        this.post = post;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public void delete() {
        this.content = "삭제된 댓글입니다.";
    }

    public void like() {
        this.commentLike = true;
    }

    public void dislike() {
        this.commentLike = false;
    }


}
