package com.smyr.showmeyourrecipe.dto;

import com.smyr.showmeyourrecipe.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long parentCommentId;
    private Long postId;
    private int depth;
    private Long writerId;
    private String writer;
    private String content;
    private Date lastModifiedDate;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentid();
        this.content = comment.getContent();
////        this.parentCommentId = comment.getParentcommentid();
////        this.postId = comment.getPostId();
////        this.depth = comment.getDepth();
////        this.writerId = comment.getWriterId();
////        this.writer = comment.getWriter();
////        this.lastModifiedDate = comment.getLastModifiedDate();
    }

}

//[
//        {
//    “commentId”:long,
//    “parentCommentId”:long,
//    “postId”:long,
//    “depth”:int,
//    “writerId”:userId,
//    “writer”:userName,
//    “content”:string,
//    “likeUsers”: [
//            { userId:int, userName:string },
//    ],
//    “lastModifiedDate”:”YYYY-MM-DD hh:mm:ss”
//        },
//]