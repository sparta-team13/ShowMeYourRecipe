package com.smyr.showmeyourrecipe.controller;


import com.smyr.showmeyourrecipe.dto.comment.CommentRequestDto;
import com.smyr.showmeyourrecipe.dto.comment.CommentResponseDto;
import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import com.smyr.showmeyourrecipe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public List<CommentResponseDto> getComment(@PathVariable ("postId") Long postId) {
        return commentService.getComment(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public List<CommentResponseDto> getCommentDetail(@PathVariable ("postId") Long postId,
                                                     @PathVariable ("commentId") Long commentId) {
        return commentService.getCommentDetail(postId,commentId);
    }

    @PostMapping("/{postId}/comments")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable ("postId") Long postId,
                                            @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(userDetails.getUser(), postId, requestDto);
    }

    @PostMapping("/{postId}/{parentCommentId}/comments")
    public CommentResponseDto createReply(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @PathVariable ("postId") Long postId,
                                          @PathVariable ("parentCommentId") Long parentCommentId,
                                          @RequestBody CommentRequestDto requestDto) {
        return commentService.createReply(userDetails.getUser(), postId, parentCommentId,requestDto);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable ("postId") Long postId,
                                            @PathVariable ("commentId") Long commentId,
                                            @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public CommentResponseDto deleteComment(@PathVariable ("postId") Long postId,
                                            @PathVariable ("commentId")Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PostMapping("/{postId}/comments/{commentId}/likes")
    public void likeComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @PathVariable ("postId") Long postId,
                            @PathVariable ("commentId") Long commentId) {
        commentService.createCommentLike(userDetails.getUser(), commentId);
    }

    @DeleteMapping("/{postId}/comments/{commentId}/likes")
    public void deleteLikeComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @PathVariable ("postId") Long postId,
                                  @PathVariable ("commentId") Long commentId) {
        commentService.deleteCommentLike(userDetails.getUser().getId(), commentId);
    }


}
