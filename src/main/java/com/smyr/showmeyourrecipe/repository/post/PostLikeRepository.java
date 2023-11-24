package com.smyr.showmeyourrecipe.repository.post;

import com.smyr.showmeyourrecipe.entity.user.User;
import com.smyr.showmeyourrecipe.entity.post.Post;
import com.smyr.showmeyourrecipe.entity.post.PostLike;
import com.smyr.showmeyourrecipe.entity.post.PostLikePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostLikeRepository extends JpaRepository<PostLike, PostLikePK> {
    void deletePostLikeByUserAndPost(User user, Post post);

    @Query("SELECT u.id, u.username, p.title, p.content, COUNT(*), " +
            "(SELECT u3.username FROM PostLike pl3 LEFT JOIN pl3.user u3 WHERE pl3.post.id = :post_id ORDER BY pl3.createdAt DESC LIMIT 1), " +
            "(SELECT CASE WHEN COUNT(u2.id) > 0 THEN 1 ELSE 0 END FROM PostLike pl2 LEFT JOIN pl2.user u2 WHERE pl2.post.id = :post_id AND u2.id = :user_id) " +
            "FROM PostLike pl " +
            "LEFT JOIN pl.post p " +
            "LEFT JOIN p.user u " +
            "WHERE p.id = :post_id " +
            "GROUP BY p.id")
    List<Object[]> readPost(@Param("user_id") Long userId, @Param("post_id") Long postId);
}