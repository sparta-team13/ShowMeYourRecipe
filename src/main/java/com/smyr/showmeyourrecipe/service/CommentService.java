package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.CommentRequestDto;
import com.smyr.showmeyourrecipe.dto.CommentResponseDto;
import com.smyr.showmeyourrecipe.entity.Comment;
import com.smyr.showmeyourrecipe.entity.Post;
import com.smyr.showmeyourrecipe.entity.User;
import com.smyr.showmeyourrecipe.repository.CommentRepository;
import com.smyr.showmeyourrecipe.repository.PostRepository;
import com.smyr.showmeyourrecipe.repository.UserRepository;
import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


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
//    @Transactional
//    public CommentResponseDto likeComment(Long postid, Long commentid) {
////        Post post = postRepository.findByPostid(postid).orElseThrow(() ->
////                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
////        );
//        Comment comment = commentRepository.findByCommentid(commentid).orElseThrow(() ->
//                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
//        );
//
//        comment.like();
//        return new CommentResponseDto(comment);
//    }
//
//    @Transactional
//    public CommentResponseDto deleteLikeComment(Long postid, Long commentid) {
//        Comment comment = commentRepository.findByCommentid(commentid).orElseThrow(() ->
//                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
//        );
//
//        comment.dislike();
//        return new CommentResponseDto(comment);
//    }



}
