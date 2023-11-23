package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findAllByPost_postId(Long id);

    List<Comment> findAllByPost_Id(Long postId);
    Optional<Comment> findByCommentId(Long commentId);
}
