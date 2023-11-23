package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.post.PostRequest;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.entity.User;
import com.smyr.showmeyourrecipe.entity.post.PostLike;
import com.smyr.showmeyourrecipe.entity.post.PostLikeId;
import com.smyr.showmeyourrecipe.repository.post.PostLikeRepository;
import com.smyr.showmeyourrecipe.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    /**
     * Service for post
     * */
    @Transactional
    public Post createPost(User user, PostRequest request) {
        return postRepository.save(
                Post.builder()
                        .user(user)
                        .title(request.getTitle())
                        .content(request.getContent()).build()
        );
    }
    @Transactional
    public Post updatePost(User user, PostRequest request, Long postId) {
        Post post = postRepository.findByIdAndUser(postId, user)
                .orElseThrow(NoSuchElementException::new);
        return post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deletePost(User user, Long postId) {
        Post post = postRepository.findByIdAndUser(postId, user)
                .orElseThrow(NoSuchElementException::new);
        postRepository.delete(post);
    }



    /**
     *  Service for postLike
     * */
    @Transactional
    public PostLike createPostLike(Long userId, Long postId) {
        return postLikeRepository.save(new PostLike(
                PostLikeId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        ));
    }

    @Transactional
    public void deletePostLike (Long userId, Long postId) {
        PostLike postLike = postLikeRepository.findById(
                PostLikeId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        ).orElseThrow(NoSuchElementException::new);
        postLikeRepository.delete(postLike);
    }
}
