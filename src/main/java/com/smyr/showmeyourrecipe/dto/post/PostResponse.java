package com.smyr.showmeyourrecipe.dto.post;

import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.entity.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostResponse {
    private final Long postId;
    private final Long writerId;
    private final String writer;
    private final String title;
    private final String content;
    private final int likeCount;
    private final String recentLikeUser;
    private final boolean myLike;
    private final String lastModifiedDate;

    @Builder
    public PostResponse(PostQueryResponse res, int likeCount) {
        Post post = res.getPostLike().getPost();
        User user = res.getPostLike().getUser();
        User postUser = post.getUser();

        this.postId = post.getId();
        this.writerId = postUser.getId();
        this.writer = postUser.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = likeCount;
        this.recentLikeUser = user.getUsername();
        this.myLike = res.isMyLike();
        this.lastModifiedDate = post.getLastModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}