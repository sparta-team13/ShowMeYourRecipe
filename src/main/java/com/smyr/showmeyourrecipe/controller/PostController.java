package com.smyr.showmeyourrecipe.controller;

import com.smyr.showmeyourrecipe.dto.post.PostRequest;
import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import com.smyr.showmeyourrecipe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
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


    /**
     * Controller for postLike
     * */
    @PostMapping("/{postId}/like")
    public void createPostLike(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                               @PathVariable("postId") Long postId) {
        postService.createPostLike(userDetailsImpl.getUser().getId(), postId);
    }

    @DeleteMapping("/{postId}/like")
    public void deletePostLike(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                               @PathVariable("postId") Long postId) {
        postService.deletePostLike(userDetailsImpl.getUser().getId(), postId);
    }
}