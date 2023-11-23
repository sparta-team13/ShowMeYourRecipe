package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.Post;
import com.smyr.showmeyourrecipe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndUser(Long postId, User user);
}