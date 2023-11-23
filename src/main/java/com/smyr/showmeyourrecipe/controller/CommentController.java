package com.smyr.showmeyourrecipe.controller;


import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import com.smyr.showmeyourrecipe.dto.CommentResponseDto;
import com.smyr.showmeyourrecipe.repository.CommentLikeRepository;
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
    public List<CommentResponseDto> getComment(@PathVariable Long postId) {
        return commentService.getComment(postId);
    }

    @PostMapping("/{postId}/comments")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(userDetails.getUser(), postId, requestDto);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public CommentResponseDto deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PostMapping("/{postId}/comments/{commentId}/likes")
    public void likeComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @PathVariable Long postId) {
        commentService.createCommentLike(userDetails.getUser().getId(), commentId);
    }

    @DeleteMapping("/{postId}/comments/{commentId}/likes")
    public void deleteLikeComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @PathVariable Long postId) {
        commentService.deleteCommentLike(userDetails.getUser().getId(), commentId);
    }

}
