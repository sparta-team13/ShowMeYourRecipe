package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.Comment;
import com.smyr.showmeyourrecipe.entity.CommentLike;
import com.smyr.showmeyourrecipe.entity.CommentLikeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeKey> {
    //Optional<CommentLikeKey> findByCommentid(Long commentid);
}
