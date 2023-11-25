package com.smyr.showmeyourrecipe.dto.comment;

import com.smyr.showmeyourrecipe.dto.post.PostQueryResponse;
import com.smyr.showmeyourrecipe.entity.comment.Comment;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final Long parentCommentId;
    private final String content;
    private final Long postId;
    private final Long depth;
    private final Long writerId;
    private final String writer;
    private final int likeCount;
    private final String recentLikeUser;
    private final boolean myLike;
    private final LocalDateTime lastModifiedDate;

    @Builder
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
}