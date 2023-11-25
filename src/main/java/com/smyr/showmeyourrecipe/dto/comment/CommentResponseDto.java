package com.smyr.showmeyourrecipe.dto.comment;

import com.smyr.showmeyourrecipe.entity.comment.Comment;
import com.smyr.showmeyourrecipe.entity.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private  Long parentCommentId;
    private  String content;
    private  Long postId;
    private  Long depth;
    private  Long writerId;
    private  String writer;
    private  int likeCount;
    private  String recentLikeUser;
    private  boolean myLike;
    private  LocalDateTime lastModifiedDate;


    @Builder(builderClassName = "commentResponseDtoBuilder", builderMethodName = "commentResponseDtoBuilder")
    public CommentResponseDto(CommentQueryResponse res, int likeCount) {
        Comment comment = res.getCommentLike().getComment();
        User user = res.getCommentLike().getUser();
        User commentUser = comment.getPost().getUser();

        this.commentId = comment.getCommentId();
        this.parentCommentId = comment.getParentCommentId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getId();
        this.depth = comment.getDepth();
        this.writerId = commentUser.getId();
        this.writer = commentUser.getUsername();
        this.likeCount = likeCount;
        this.recentLikeUser = user.getUsername();
        this.myLike = res.isMyLike();
        this.lastModifiedDate = comment.getLastModifiedDate();
    }

    @Builder(builderClassName = "commentNoResponseDtoBuilder", builderMethodName = "commentNoResponseDtoBuilder")
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.parentCommentId = comment.getParentCommentId();
        this.postId = comment.getPost().getId();
        this.depth = comment.getDepth();
        this.writerId = comment.getWriterId();
        this.writer = comment.getWriterName();
        this.likeCount = 0;
        this.recentLikeUser = null;
        this.myLike = false;
        this.lastModifiedDate = comment.getLastModifiedDate();
    }
}