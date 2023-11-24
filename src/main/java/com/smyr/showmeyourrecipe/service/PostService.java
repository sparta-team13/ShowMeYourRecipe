package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.post.PostQueryResponse;
import com.smyr.showmeyourrecipe.dto.post.PostRequest;
import com.smyr.showmeyourrecipe.dto.post.PostResponse;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.entity.user.User;
import com.smyr.showmeyourrecipe.entity.post.PostLike;
import com.smyr.showmeyourrecipe.repository.post.PostLikeRepository;
import com.smyr.showmeyourrecipe.repository.post.PostQueryRepository;
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
    private final PostQueryRepository postQueryRepository;
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

    public PostResponse readPost(Long userId, Long postId) {
        List<PostQueryResponse> res = postQueryRepository.readPost(userId, postId);
        return PostResponse.builder()
                .res(res.get(0))
                .likeCount(res.size())
                .build();
    }



    /**
     *  Service for postLike
     * */
    @Transactional
    public PostLike createPostLike(User user, Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);

        return postLikeRepository.save(
                PostLike.builder()
                    .user(user)
                    .post(findPost)
                    .build()
        );
    }

    @Transactional
    public void deletePostLike (User user, Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);
        postLikeRepository.deletePostLikeByUserAndPost(user, findPost);
    }
}
