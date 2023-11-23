package com.smyr.showmeyourrecipe.repository.post;

import com.smyr.showmeyourrecipe.entity.post.PostLike;
import com.smyr.showmeyourrecipe.entity.post.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
}