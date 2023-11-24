package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.CommentLike;
import com.smyr.showmeyourrecipe.entity.CommentLikeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeKey> {
}
