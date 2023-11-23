package com.smyr.showmeyourrecipe.dto;

import com.smyr.showmeyourrecipe.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long parentCommentId;
    private Long postId;
    private Long depth;
    private Long writerId;
    private String writer;
    private String content;
    private LocalDateTime lastModifiedDate;


    public CommentResponseDto( Comment comment ) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.parentCommentId = comment.getParentCommentId();
        this.postId = comment.getPost().getId();
        this.depth = comment.getDepth();
        this.writerId = comment.getWriterId();
        this.writer = comment.getWriterName();
        this.lastModifiedDate = comment.getLastModifiedDate();
    }
}