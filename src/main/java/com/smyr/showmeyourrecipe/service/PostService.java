package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.post.PostRequest;
import com.smyr.showmeyourrecipe.entity.Post;
import com.smyr.showmeyourrecipe.entity.User;
import com.smyr.showmeyourrecipe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

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
}
