package com.smyr.showmeyourrecipe.entity;

import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import com.smyr.showmeyourrecipe.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "comment")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = true)
    private Long parentCommentId;

    @Column(nullable = true)
    private Long depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    @Builder
    public Comment(User user, CommentRequestDto requestDto, Post post) {
        this.writerId = user.getId();
        this.writerName = user.getUsername();
        this.content = requestDto.getContent();
        this.post = post;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public void delete() {
        this.content = "삭제된 댓글입니다.";
    }
}
