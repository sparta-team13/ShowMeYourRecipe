package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostid(Long id);
}
