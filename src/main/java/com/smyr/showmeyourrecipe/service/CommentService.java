package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.comment.CommentRequestDto;
import com.smyr.showmeyourrecipe.dto.comment.CommentResponseDto;

import com.smyr.showmeyourrecipe.entity.*;
import com.smyr.showmeyourrecipe.entity.comment.Comment;
import com.smyr.showmeyourrecipe.entity.comment.CommentLike;
import com.smyr.showmeyourrecipe.entity.comment.CommentLikeKey;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.repository.comment.CommentRepository;
import com.smyr.showmeyourrecipe.repository.post.PostRepository;
import com.smyr.showmeyourrecipe.repository.comment.CommentLikeRepository;

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

        Comment comment = commentRepository.save(Comment.commentBuilder()
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
    public CommentLike createCommentLike(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NoSuchElementException::new);

        return commentLikeRepository.save(
                CommentLike.builder()
                        .user(user)
                        .comment(comment)
                        .build()
        );
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

    public List<CommentResponseDto> getCommentDetail(Long postId, Long commentId) {
        List<Comment> commentList = commentRepository.findAllByPost_Id(postId);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            responseDtoList.add(new CommentResponseDto( comment ));
        }

        return responseDtoList;
    }
    @Transactional
    public CommentResponseDto createReply(User user, Long postId, Long parentCommentId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(NoSuchElementException::new);

        Comment comment = commentRepository.save(Comment.replyBuilder()
                .user(user)
                .requestDto(requestDto)
                .post(parentComment.getPost())
                .parentCommentId(parentCommentId)
                .build());

        return new CommentResponseDto(comment);
    }
}