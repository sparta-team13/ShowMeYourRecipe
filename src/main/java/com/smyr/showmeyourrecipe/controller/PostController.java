package com.smyr.showmeyourrecipe.controller;

import com.smyr.showmeyourrecipe.dto.post.PostRequest;
import com.smyr.showmeyourrecipe.dto.post.PostResponse;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.etc.response.ApiResponse;
import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import com.smyr.showmeyourrecipe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    /**
     * Controller for post
     * */
    @PostMapping("")
    public void createPost(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                           @RequestBody PostRequest request) {
        postService.createPost(userDetailsImpl.getUser(), request);
    }

    @PatchMapping("/{postId}")
    public void updatePost(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                           @RequestBody PostRequest request, @PathVariable("postId") Long postId) {
        postService.updatePost(userDetailsImpl.getUser(), request, postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                           @PathVariable("postId") Long postId) {
        postService.deletePost(userDetailsImpl.getUser(), postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> readPost(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                               @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPost(userDetailsImpl.getUser().getId(), postId)
        ));
    }

    /**
     * Controller for postLike
     * */
    @PostMapping("/{postId}/likes")
    public void createPostLike(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                               @PathVariable("postId") Long postId) {
        postService.createPostLike(userDetailsImpl.getUser(), postId);
    }

    @DeleteMapping("/{postId}/likes")
    public void deletePostLike(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                               @PathVariable("postId") Long postId) {
        postService.deletePostLike(userDetailsImpl.getUser(), postId);
    }
}