package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import com.smyr.showmeyourrecipe.dto.CommentResponseDto;

import com.smyr.showmeyourrecipe.entity.*;
import com.smyr.showmeyourrecipe.repository.CommentRepository;
import com.smyr.showmeyourrecipe.repository.post.PostRepository;
import com.smyr.showmeyourrecipe.repository.CommentLikeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikeRepository commentLikeRepository;


    public List<CommentResponseDto> getComment(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPost_Id(postId);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            responseDtoList.add(new CommentResponseDto( comment ));
        }

        return responseDtoList;
    }

    @Transactional
    public CommentResponseDto createComment(User user, Long postId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        Comment comment = commentRepository.save(Comment.builder()
                .user(user)
                .requestDto(requestDto)
                .post(post)
                .build());
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );

        comment.delete();
        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentLike createCommentLike(Long userId, Long commentId) {
        return commentLikeRepository.save(new CommentLike(
                CommentLikeKey.builder()
                        .userId(userId)
                        .commentId(commentId)
                        .build()
        ));
    }

    @Transactional
    public void deleteCommentLike(Long userId, Long commentId) {
        CommentLike commentLike = commentLikeRepository.findById(
                CommentLikeKey.builder()
                        .userId(userId)
                        .commentId(commentId)
                        .build()
        ).orElseThrow(NoSuchElementException::new);
        commentLikeRepository.delete(commentLike);
    }
}