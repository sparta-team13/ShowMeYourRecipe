package com.smyr.showmeyourrecipe.dto.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {
    private final Long postId;
    private final Long writerId;
    private final String writer;
    private final String title;
    private final String content;
    private final Long likeCount;
    private final String recentLikeUsername;
    private final int myLike;

    @Builder
    public PostResponse(Long postId, Object[] queryResponse) {
        this.postId = postId;
        this.writerId = (Long) queryResponse[0];
        this.writer = (String) queryResponse[1];
        this.title = (String) queryResponse[2];
        this.content = (String) queryResponse[3];
        this.likeCount = (Long) queryResponse[4];
        this.recentLikeUsername = (String) queryResponse[5];
        this.myLike = (int) queryResponse[6];
    }
}
